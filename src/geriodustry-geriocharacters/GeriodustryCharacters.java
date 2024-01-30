package geriodustrycharacters;

import arc.*;
import arc.util.*;
import geriodustrycharacters.content.*;
//import geriodustrycharacters.ui.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.net.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

public class GeriodustryCharacters extends Mod{

    public GeriodustryCharacters(){

    }
	
    @Override
    public void init(){
        LoadedMod itsmegerio = Vars.mods.locateMod("trrestghgytcftgbcxszdgeriodustry-characters");
    }
	
    @Override
    public void loadContent(){
        GerioItems.load();
        GerioLiquids.load();
        GerioUnits.load();
        GerioBlocks.load();
        GerioBlocks.overwrite(); // will make the game challenging
		Events.on(EventType.ClientLoadEvent.class, e -> {
			Core.atlas.find("error").set(Core.atlas.find("trrestghgytcftgbcxszdgeriodustry-characters-dud"));
		});
    }

}
