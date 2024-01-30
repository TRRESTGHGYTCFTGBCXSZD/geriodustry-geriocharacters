package geriodustrycharacters.blocks;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.core.*;
import mindustry.input.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class BidirectionalBasicTunnel extends Block{
    private static BuildPlan otherReq;
    private int otherDst = 0;

	public TextureRegion rightRegion;
	public TextureRegion downRegion;
	public TextureRegion leftRegion;
	public TextureRegion upRegion;

	@Override
	public void load(){
		super.load();
		rightRegion = Core.atlas.find(name + "-right");
		downRegion = Core.atlas.find(name + "-down");
		leftRegion = Core.atlas.find(name + "-left");
		upRegion = Core.atlas.find(name + "-up");
	}

    public int range = 4;

    public BidirectionalBasicTunnel(String name){
        super(name);
        update = true;
        solid = true;
        rotate = true;
        group = BlockGroup.transportation;
        noUpdateDisabled = true;
        priority = TargetPriority.transport;
        envEnabled = Env.space | Env.terrestrial | Env.underwater;
        drawArrow = false;
        allowDiagonal = false;
        regionRotated1 = 1;
    }

    @Override
    public void init(){
        updateClipRadius((range + 0.5f) * tilesize);
        super.init();
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
		if(plan.rotation==0){
			Draw.rect(leftRegion, plan.drawx(), plan.drawy());
		}else if(plan.rotation==1){
			Draw.rect(upRegion, plan.drawx(), plan.drawy());
		}else if(plan.rotation==2){
				Draw.rect(rightRegion, plan.drawx(), plan.drawy());
		}else if(plan.rotation==3){
			Draw.rect(downRegion, plan.drawx(), plan.drawy());
		}else{
			Draw.rect(region, plan.drawx(), plan.drawy()); // edge case, will not shown
		}
    }

    @Override
    public void drawPlanConfigTop(BuildPlan plan, Eachable<BuildPlan> list){
        otherReq = null;
        otherDst = range;
        Point2 d = Geometry.d4(plan.rotation);
        list.each(other -> {
            if(other.block == this && plan != other && Mathf.clamp(other.x - plan.x, -1, 1) == d.x && Mathf.clamp(other.y - plan.y, -1, 1) == d.y){
                int dst = Math.max(Math.abs(other.x - plan.x), Math.abs(other.y - plan.y));
                if(dst <= otherDst){
                    otherReq = other;
                    otherDst = dst;
                }
            }
        });
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
    }

    @Override
    public void changePlacementPath(Seq<Point2> points, int rotation){
        Placement.calculateNodes(points, this, rotation, (point, other) -> Math.max(Math.abs(point.x - other.x), Math.abs(point.y - other.y)) <= range);
    }

    public void drawPlace(int x, int y, int rotation, boolean valid, boolean line){
        int length = range;
        Building found = null;
        int dx = Geometry.d4x(rotation), dy = Geometry.d4y(rotation);

        //find the link
        for(int i = 1; i <= range; i++){
            Tile other = world.tile(x + dx * i, y + dy * i);

            if(other != null && other.build instanceof BidirectionalBasicTunnelBuild build && build.block == this && build.team == player.team()){
                length = i;
                found = other.build;
                break;
            }
        }

        if(line || found != null){
            Drawf.dashLine(Pal.placing,
            x * tilesize + dx * (tilesize / 2f + 2),
            y * tilesize + dy * (tilesize / 2f + 2),
            x * tilesize + dx * (length) * tilesize,
            y * tilesize + dy * (length) * tilesize
            );
        }

        if(found != null){
            if(line){
                Drawf.square(found.x, found.y, found.block.size * tilesize/2f + 2.5f, 0f);
            }else{
                Drawf.square(found.x, found.y, 2f);
            }
        }
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        drawPlace(x, y, rotation, valid, true);
    }

    public boolean positionsValid(int x1, int y1, int x2, int y2){
        if(x1 == x2){
            return Math.abs(y1 - y2) <= range;
        }else if(y1 == y2){
            return Math.abs(x1 - x2) <= range;
        }else{
            return false;
        }
    }

    public class BidirectionalBasicTunnelBuild extends Building{
        public BidirectionalBasicTunnelBuild[] occupied = new BidirectionalBasicTunnelBuild[4];
        public @Nullable BidirectionalBasicTunnelBuild lastLink;

        @Override
        public void draw(){
			if(rotation==0){
				Draw.rect(leftRegion, x, y);
			}else if(rotation==1){
				Draw.rect(upRegion, x, y);
			}else if(rotation==2){
				Draw.rect(rightRegion, x, y);
			}else if(rotation==3){
				Draw.rect(downRegion, x, y);
			}else{
				Draw.rect(block.region, x, y); // edge case, will not shown
			}
        }

        @Override
        public void drawSelect(){
            drawPlace(tile.x, tile.y, rotation, true, false);
            //draw incoming bridges
            for(int dir = 0; dir < 4; dir++){
                if(dir != rotation){
                    int dx = Geometry.d4x(dir), dy = Geometry.d4y(dir);
                    Building found = occupied[(dir + 2) % 4];

                    if(found != null){
                        int length = Math.max(Math.abs(found.tileX() - tileX()), Math.abs(found.tileY() - tileY()));
                        Drawf.dashLine(Pal.place,
                        found.x - dx * (tilesize / 2f + 2),
                        found.y - dy * (tilesize / 2f + 2),
                        found.x - dx * (length) * tilesize,
                        found.y - dy * (length) * tilesize
                        );

                        Drawf.square(found.x, found.y, 2f, 45f, Pal.place);
                    }
                }
            }
        }

        @Nullable
        public BidirectionalBasicTunnelBuild findLink(){
            for(int i = 1; i <= range; i++){
                Tile other = tile.nearby(Geometry.d4x(rotation) * i, Geometry.d4y(rotation) * i);
                if(other != null && other.build instanceof BidirectionalBasicTunnelBuild build && build.block == BidirectionalBasicTunnel.this && build.team == team && rotation == (build.rotation+2)%4){
                    return build;
                }
            }
            return null;
        }
		
		public boolean moveBackward(Item item){
			Building other = back();
			if(other != null && other.team == team && other.acceptItem(self(), item)){
				other.handleItem(self(), item);
				return true;
			}
			return false;
		}
    }
}