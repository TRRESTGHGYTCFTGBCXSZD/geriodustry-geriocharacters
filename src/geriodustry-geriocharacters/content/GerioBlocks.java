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
	
    crumpFactory, antumbruh,
	
	capacitorFactory, resistorFactory, diodeFactory, transistorFactory, icChipFactory, cpuFactory, gpuFactory
	;

    public static void load(){
        crumpFactory = new UnitMixMaxer("crump-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 5;
            configurable = false;
            plans.add(
			new GerioPayloadItemUnitPlan(UnitTypes.poly, 18f * 60f,with(GerioItems.pcb, 1, GerioItems.stepperMotor, 6, Items.copper, 15, GerioItems.robotLeg, 2, GerioItems.robotArm, 2, GerioItems.robotHead, 1, GerioItems.robotTorso, 1, GerioItems.robotHip, 1),LiquidStack.with(GerioLiquids.blueBlood, 24),PayloadStack.list(Blocks.liquidRouter, 1))
			);
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};
        antumbruh = new UnitMixMaxer("antumbruh"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 9;
            plans.add(
			new GerioPayloadItemUnitPlan(UnitTypes.antumbra, 1f * 60f,with(Items.beryllium, 15, Items.tungsten, 15, Items.oxide, 15,Items.carbide, 15),LiquidStack.with(Liquids.water, 60, Liquids.slag, 60),PayloadStack.list(UnitTypes.flare, 5, UnitTypes.horizon, 5, UnitTypes.zenith, 5))
			);
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};
        capacitorFactory = new GenericCrafter("capacitor-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.capacitor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 2, Items.plastanium, 1));
            consumePower(1f);
        }};
        resistorFactory = new GenericCrafter("resistor-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.resistor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.titanium, 1));
            consumePower(1f);
        }};
        diodeFactory = new GenericCrafter("diode-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.diode, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.silicon, 1, Items.copper, 1, Items.lead, 1));
            consumePower(1f);
        }};
        transistorFactory = new GenericCrafter("transistor-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.transistor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.diode, 2));
            consumePower(1f);
        }};
        icChipFactory = new GenericCrafter("ic-chip-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.icChip, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.transistor, 80));
            consumePower(1f);
        }};
        cpuFactory = new GenericCrafter("cpu-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.cpu, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.transistor, 20));
            consumePower(1f);
        }};
        gpuFactory = new GenericCrafter("gpu-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.gpu, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.transistor, 30));
            consumePower(1f);
        }};
    }
    public static void overwrite(){
		//Blocks.microProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 1, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 80);
		//Blocks.logicProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 3, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 150);
		//Blocks.hyperProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 6, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 230);
    }
}