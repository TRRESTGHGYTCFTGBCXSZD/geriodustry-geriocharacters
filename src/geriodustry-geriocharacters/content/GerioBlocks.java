package geriodustrycharacters.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import geriodustrycharacters.blocks.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class GerioBlocks{
    public static Block
	
    crumpFactory
	;

    public static void load(){
        crumpFactory = new UnitMixMaxer("crump-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 3;
            configurable = false;
            plans.add(
			new GerioPayloadItemUnitPlan(UnitTypes.poly, 1f * 1f,with(GerioItems.pcb, 1, GerioItems.stepperMotor, 14),LiquidStack.with(GerioLiquids.blueBlood, 24),PayloadStack.list(Blocks.liquidRouter, 6))
			);
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};
    }
}