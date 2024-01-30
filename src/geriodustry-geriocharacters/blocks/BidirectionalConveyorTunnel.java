package geriodustrycharacters.blocks;

import mindustry.gen.*;
import mindustry.type.*;

public class BidirectionalConveyorTunnel extends BidirectionalBasicTunnel{
    public float speed = 53; //frames taken to go through this tunnel

    public BidirectionalConveyorTunnel(String name){
        super(name);

        itemCapacity = 32;
		range = 3;
        hasItems = true;
        underBullets = true;
    }

    public class BidirectionalConveyorTunnelBuild extends BidirectionalBasicTunnelBuild{
        public float progress = 0f;

        @Override
        public void updateTile(){
            var link = lastLink = findLink();
            if(link != null){
                link.occupied[rotation % 4] = this;
                if(items.any()){
                    progress += edelta();
                    while(progress > speed){
						if(items.empty()){break;}
                        Item next = items.first();
                        if(link.moveBackward(next)){
							items.remove(next,1);
						};
                        progress -= speed;
                    }
                }
            }

            for(int i = 0; i < 4; i++){
                if(occupied[i] == null || occupied[i].rotation != i || !occupied[i].isValid() || occupied[i].lastLink != this){
                    occupied[i] = null;
                }
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            //only accept if there's an output point.
            if(findLink() == null) return false;

            int rel = this.relativeToEdge(source.tile);
            return items.total() < itemCapacity && rel != rotation && occupied[(rel + 2) % 4] == null;
        }
    }
}