package geriodustrycharacters.blocks;

// an unit factory extension that allows items, liquids and payloads

import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.style.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
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

public class UnitMixMaxer extends UnitBlock{
    public Effect incinerateEffect = Fx.blastExplosion;
    public Sound incinerateSound = Sounds.bang;
    public int[] capacities = {};
    public float[] liqcapacities = {};
    public int[] paycapacities = {};
    protected @Nullable ConsumePayloadDynamic consPayload;

    public Seq<GerioPayloadItemUnitPlan> plans = new Seq<>(4);

    public UnitMixMaxer(String name){
        super(name);
        update = true;
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        solid = true;
        configurable = true;
        clearOnDoubleTap = true;
        acceptsPayload = true;
        outputsPayload = true;
        rotate = true;
        regionRotated1 = 1;
        commandable = true;
        ambientSound = Sounds.respawning;

        config(Integer.class, (UnitMixMaxerBuild tile, Integer i) -> {
            if(!configurable) return;

            if(tile.currentPlan == i) return;
            tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
            tile.progress = 0;
        });

        config(UnitType.class, (UnitMixMaxerBuild tile, UnitType val) -> {
            if(!configurable) return;

            int next = plans.indexOf(p -> p.unit == val);
            if(tile.currentPlan == next) return;
            tile.currentPlan = next;
            tile.progress = 0;
        });

        consume(new ConsumeItemDynamic((UnitMixMaxerBuild e) -> e.currentPlan != -1 ? plans.get(Math.min(e.currentPlan, plans.size - 1)).itemrequirements : ItemStack.empty));
        consume(new ConsumeLiquidsDynamic((UnitMixMaxerBuild e) -> e.currentPlan != -1 ? LiquidStack.mult(plans.get(Math.min(e.currentPlan, plans.size - 1)).liquidrequirements, 1/plans.get(Math.min(e.currentPlan, plans.size - 1)).time) : LiquidStack.empty));
        consume(new ConsumePayloadDynamic((UnitMixMaxerBuild e) -> e.currentPlan != -1 ? plans.get(Math.min(e.currentPlan, plans.size - 1)).payloadrequirements : PayloadStack.list()));
    }

    @Override
    public void init(){
        capacities = new int[Vars.content.items().size];
        liqcapacities = new float[Vars.content.liquids().size];
        for(GerioPayloadItemUnitPlan plan : plans){
            for(ItemStack stack : plan.itemrequirements){
                capacities[stack.item.id] = Math.max(capacities[stack.item.id], stack.amount * 2);
                itemCapacity = Math.max(itemCapacity, stack.amount * 2);
            }
            for(LiquidStack liqstack : plan.liquidrequirements){
                liqcapacities[liqstack.liquid.id] = Math.max(liqcapacities[liqstack.liquid.id], liqstack.amount * 2);
                liquidCapacity = Math.max(liquidCapacity, liqstack.amount * 2);
            }
            /**for(PayloadStack paystack : plan.liquidrequirements){
                paycapacities[liqstack.liquid.id] = Math.max(liqcapacities[liqstack.liquid.id], liqstack.amount * 2);
                liquidCapacity = Math.max(liquidCapacity, liqstack.amount * 2);
            }**/
        }

        consumeBuilder.each(c -> c.multiplier = b -> state.rules.unitCost(b.team));

        super.init();
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("progress", (UnitMixMaxerBuild e) -> new Bar("bar.progress", Pal.ammo, e::fraction));

        addBar("units", (UnitMixMaxerBuild e) ->
        new Bar(
            () -> e.unit() == null ? "[lightgray]" + Iconc.cancel :
                Core.bundle.format("bar.unitcap",
                    Fonts.getUnicodeStr(e.unit().name),
                    e.team.data().countType(e.unit()),
                    e.unit() == null ? Units.getStringCap(e.team) : (e.unit().useUnitCap ? Units.getStringCap(e.team) : "∞")
                ),
            () -> Pal.power,
            () -> e.unit() == null ? 0f : (e.unit().useUnitCap ? (float)e.team.data().countType(e.unit()) / Units.getCap(e.team) : 1f)
        ));
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);

        stats.add(Stat.output, table -> {
            table.row();

            for(var plan : plans){
                table.table(Styles.grayPanel, t -> {

                    if(plan.unit.isBanned()){
                        t.image(Icon.cancel).color(Pal.remove).size(40);
                        return;
                    }

                    if(plan.unit.unlockedNow()){
                        t.image(plan.unit.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                        t.table(info -> {
                            info.add(plan.unit.localizedName).left();
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();
						int[] jjjjjjj = {0};
                        t.table(req -> {
                            req.right();
                            for(int i = 0; i < plan.itemrequirements.length; i++){
                                if(jjjjjjj[0] % 6 == 0){
                                    req.row();
                                }
								jjjjjjj[0]++;
                                ItemStack stack = plan.itemrequirements[i];
                                req.add(new ItemDisplay(stack.item, stack.amount, false)).pad(5);
                            }
                            for(int i = 0; i < plan.liquidrequirements.length; i++){
                                if(jjjjjjj[0] % 6 == 0){
                                    req.row();
                                }
								jjjjjjj[0]++;
                                LiquidStack liqstack = plan.liquidrequirements[i];
                                req.add(new LiquidDisplay(liqstack.liquid, liqstack.amount, false)).pad(5);
                            }
                            for(int i = 0; i < plan.payloadrequirements.size; i++){
                                if(jjjjjjj[0] % 6 == 0){
                                    req.row();
                                }
								jjjjjjj[0]++;
                                PayloadStack paystack = plan.payloadrequirements.get(i);
                                req.add(new ItemImage(paystack)).pad(5);
                            }
                        }).right().grow().pad(10f);
                    }else{
                        t.image(Icon.lock).color(Pal.darkerGray).size(40);
                    }
                }).growX().pad(5);
                table.row();
            }
        });
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, outRegion, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(outRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.rect(topRegion, plan.drawx(), plan.drawy());
    }

    public static class GerioPayloadItemUnitPlan{
        public UnitType unit;
        public ItemStack[] itemrequirements;
        public LiquidStack[] liquidrequirements;
        public Seq<PayloadStack> payloadrequirements;
        public float time;

        public GerioPayloadItemUnitPlan(UnitType unit, float time, ItemStack[] itemrequirements, LiquidStack[] liquidrequirements, Seq<PayloadStack> payloadrequirements){
            this.unit = unit;
            this.time = time;
            this.itemrequirements = itemrequirements;
            this.liquidrequirements = liquidrequirements;
            this.payloadrequirements = payloadrequirements;
        }

        GerioPayloadItemUnitPlan(){}
    }

    public class UnitMixMaxerBuild extends PayloadBlockBuild<Payload>{
        public @Nullable Vec2 commandPos;
        public int currentPlan = -1;
        public PayloadSeq shouldbenotbecast = new PayloadSeq();
		// issues
        public float progress, time, speedScl;

        public void spawned(){
            progress = 0f;
            payload = null;
        }

		//

        public float fraction(){
            return currentPlan == -1 ? 0 : progress / plans.get(currentPlan).time;
        }

        @Override
        public Vec2 getCommandPosition(){
            return commandPos;
        }

        @Override
        public void onCommand(Vec2 target){
            commandPos = target;
        }

        @Override
        public Object senseObject(LAccess sensor){
            if(sensor == LAccess.config) return currentPlan == -1 ? null : plans.get(currentPlan).unit;
            return super.senseObject(sensor);
        }

        @Override
        public boolean shouldActiveSound(){
            return shouldConsume();
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.progress) return Mathf.clamp(fraction());
            if(sensor == LAccess.itemCapacity) return Mathf.round(itemCapacity * state.rules.unitCost(team));
            return super.sense(sensor);
        }

        @Override
        public void buildConfiguration(Table table){
            Seq<UnitType> units = Seq.with(plans).map(u -> u.unit).retainAll(u -> u.unlockedNow() && !u.isBanned());

            if(units.any()){
                ItemSelection.buildTable(UnitMixMaxer.this, table, units, () -> currentPlan == -1 ? null : plans.get(currentPlan).unit, unit -> configure(plans.indexOf(u -> u.unit == unit)), selectionRows, selectionColumns);
            }else{
                table.table(Styles.black3, t -> t.add("@none").color(Color.lightGray));
            }
        }

        @Override
        public void display(Table table){
            super.display(table);

            TextureRegionDrawable reg = new TextureRegionDrawable();

            table.row();
            table.table(t -> {
                t.left();
                t.image().update(i -> {
                    i.setDrawable(currentPlan == -1 ? Icon.cancel : reg.set(plans.get(currentPlan).unit.uiIcon));
                    i.setScaling(Scaling.fit);
                    i.setColor(currentPlan == -1 ? Color.lightGray : Color.white);
                }).size(32).padBottom(-4).padRight(2);
                t.label(() -> currentPlan == -1 ? "@none" : plans.get(currentPlan).unit.localizedName).wrap().width(230f).color(Color.lightGray);
            }).left();
        }

        @Override
        public Object config(){
            return currentPlan;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            boolean fallback = true;
            for(int i = 0; i < 4; i++){
                if(blends(i) && i != rotation){
                    Draw.rect(inRegion, x, y, (i * 90) - 180);
                    fallback = false;
                }
            }
            Draw.rect(outRegion, x, y, rotdeg());

            if(currentPlan != -1){
                GerioPayloadItemUnitPlan plan = plans.get(currentPlan);
                Draw.draw(Layer.blockOver, () -> Drawf.construct(this, plan.unit, rotdeg() - 90f, progress / plan.time, speedScl, time));
            }

            Draw.z(Layer.blockOver);

            payRotation = rotdeg();
            drawPayload();

            Draw.z(Layer.blockOver + 0.1f);

            Draw.rect(topRegion, x, y);
        }

        @Override
        public void updateTile(){
            if(!configurable){
                currentPlan = 0;
            }

            if(currentPlan < 0 || currentPlan >= plans.size){
                currentPlan = -1;
            }

            if(efficiency > 0 && currentPlan != -1){
                time += edelta() * speedScl * Vars.state.rules.unitBuildSpeed(team);
                progress += edelta() * Vars.state.rules.unitBuildSpeed(team);
                speedScl = Mathf.lerpDelta(speedScl, 1f, 0.05f);
            }else{
                speedScl = Mathf.lerpDelta(speedScl, 0f, 0.05f);
            }

            if (currentPlan == -1 || (payload instanceof UnitPayload && payload.content() == plans.get(currentPlan).unit)){
				moveOutPayload();
            }else{
				if (moveInPayload()){
					shouldbenotbecast.add(payload.content(), 1);
					incinerateEffect.at(this);
					incinerateSound.at(this);
					payload = null;
				}
				if(currentPlan != -1 && payload == null){
					GerioPayloadItemUnitPlan plan = plans.get(currentPlan);

					//make sure to reset plan when the unit got banned after placement
					if(plan.unit.isBanned()){
					currentPlan = -1;
						return;
					}

					if(progress >= plan.time){
						progress %= 1f;

						Unit unit = plan.unit.create(team);
						if(commandPos != null && unit.isCommandable()){
							unit.command().commandPosition(commandPos);
						}
						payload = new UnitPayload(unit);
						payVector.setZero();
						consume();
						if (payload instanceof UnitPayload p){
							Events.fire(new UnitCreateEvent(p.unit, this));
						}
					}

					progress = Mathf.clamp(progress, 0, plan.time);
				}else{
					progress = 0f;
				}
			}
        }

        @Override
        public boolean shouldConsume(){
            if(currentPlan == -1) return false;
            return enabled && payload == null;
        }

        public boolean isUnitPayEqual(UnitType unittype,UnlockableContent pay){
			try {
				return unittype == pay;
			} catch(Exception a) {
				return false;
			}
        }

        @Override
        public int getMaximumAccepted(Item item){
            return Mathf.round(capacities[item.id] * state.rules.unitCost(team));
        }
		
        public int getLiquidMaximumAccepted(Liquid liquid){
            return Mathf.round(liqcapacities[liquid.id] * state.rules.unitCost(team));
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return currentPlan != -1 && items.get(item) < getMaximumAccepted(item) &&
                Structs.contains(plans.get(currentPlan).itemrequirements, stack -> stack.item == item);
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            return currentPlan != -1 && liquids.get(liquid) < getLiquidMaximumAccepted(liquid) &&
                Structs.contains(plans.get(currentPlan).liquidrequirements, liqstack -> liqstack.liquid == liquid);
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload){
			if(currentPlan != -1){
				var plan = plans.get(currentPlan);
				try{
				return this.payload == null && relativeTo(source) != rotation &&
					plan.payloadrequirements.contains(b -> b.item == payload.content() && shouldbenotbecast.get(payload.content()) < Mathf.round(b.amount * state.rules.unitCost(team)));
				}catch(Throwable h){
					return false;
				}
			}else{
				return false;
			}
        }

        @Override
        public PayloadSeq getPayloads(){
            return shouldbenotbecast;
        }
		
        public @Nullable UnitType unit(){
            return currentPlan == -1 ? null : plans.get(currentPlan).unit;
        }

        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
            write.s(currentPlan);
            TypeIO.writeVecNullable(write, commandPos);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
            currentPlan = read.s();
            if(revision >= 2){
                commandPos = TypeIO.readVecNullable(read);
            }
        }
    }
}