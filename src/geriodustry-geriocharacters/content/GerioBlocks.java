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
	
	// random things
	ultimateTunnelBelt,
	// electrical factories
	pcbFactory, capacitorFactory, stepperMotorFactory, transformerFactory, inductorFactory, resistorFactory, diodeFactory, transistorFactory, icChipFactory, cpuFactory, gpuFactory,
	// pre-crumps
	robotPcbFactory, headFabricator, torsoFabricator, hipFabricator, armFabricator, legFabricator, handFabricator,
	// crumps
	crumpFactory,
	// lmao no
    antumbruh
	;

    public static void load(){
		// random things
		
        ultimateTunnelBelt = new BidirectionalConveyorTunnel("ultimate-belttunnel"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
			speed = 1f/32f;
			range = 400;
            //outputItem = new ItemStack(GerioItems.pcb, 1);
            //craftTime = 30f;
            //hasPower = true;
            //hasLiquids = false;

            //consumeItems(with(Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 2));
            //consumePower(1f);
        }};
		
		// electrical factories
		
        pcbFactory = new GenericCrafter("pcb-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.pcb, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 4, Items.plastanium, 5));
            consumePower(1f);
        }};
        capacitorFactory = new GenericCrafter("capacitor-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.capacitor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 2, Items.plastanium, 1));
            consumePower(1f);
        }};
        stepperMotorFactory = new GenericCrafter("stepper-motor-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.stepperMotor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 4, Items.plastanium, 1));
            consumePower(1f);
        }};
        transformerFactory = new GenericCrafter("transformer-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.transformer, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 3));
            consumePower(1f);
        }};
        inductorFactory = new GenericCrafter("inductor-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 2;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.inductor, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 1, Items.plastanium, 1));
            consumePower(1f);
        }};
        resistorFactory = new GenericCrafter("resistor-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
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
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
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
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
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
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.icChip, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.transistor, 80));
            consumePower(1f);
			itemCapacity = 160;
        }};
        cpuFactory = new GenericCrafter("cpu-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.cpu, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.icChip, 20));
            consumePower(1f);
			itemCapacity = 40;
        }};
        gpuFactory = new GenericCrafter("gpu-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.gpu, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.icChip, 30));
            consumePower(1f);
			itemCapacity = 60;
        }};
		
		//pre-crumps
		
        robotPcbFactory = new GenericCrafter("robot-pcb-factory"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 5;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotPcb, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.pcb, 1, GerioItems.transformer, 1, GerioItems.cpu, 1, GerioItems.gpu, 1, GerioItems.capacitor, 60, GerioItems.resistor, 150, GerioItems.diode, 80, GerioItems.transistor, 100));
            consumePower(1f);
			itemCapacity = 300;
        }};
		
        headFabricator = new GenericCrafter("robot-head-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotHead, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 6));
            consumePower(1f);
        }};
        torsoFabricator = new GenericCrafter("robot-torso-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotTorso, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 6));
            consumePower(1f);
        }};
        hipFabricator = new GenericCrafter("robot-hip-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotHip, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 6));
            consumePower(1f);
        }};
        armFabricator = new GenericCrafter("robot-arm-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotArm, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.stepperMotor, 2, GerioItems.robotHand, 1, Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 6));
            consumePower(1f);
        }};
        legFabricator = new GenericCrafter("robot-leg-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotLeg, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.stepperMotor, 2, Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 6));
            consumePower(1f);
        }};
        handFabricator = new GenericCrafter("robot-hand-fabricator"){{
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium, 150));
            size = 3;
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            outputItem = new ItemStack(GerioItems.robotHand, 1);
            craftTime = 30f;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(GerioItems.stepperMotor, 14, Items.copper, 2, Vars.content.item("trrestghgytcftgbcxszdcheatdustry-gerio-alloy"), 2));
            consumePower(1f);
			itemCapacity = 30;
        }};
		
		//crumps
		
        crumpFactory = new UnitMixMaxer("crump-factory"){{
            requirements(Category.units, with(Items.silicon, 200, Items.thorium, 150));
            size = 5;
            configurable = false;
            plans.add(
			new GerioPayloadItemUnitPlan(GerioUnits.crump, 18f * 60f,with(GerioItems.robotPcb, 1, GerioItems.stepperMotor, 6, Items.copper, 20, GerioItems.robotLeg, 2, GerioItems.robotArm, 2, GerioItems.robotHead, 1, GerioItems.robotTorso, 1, GerioItems.robotHip, 1),LiquidStack.with(GerioLiquids.blueBlood, 24),PayloadStack.list(Blocks.liquidRouter, 1))
			);
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};
		
		//lmao no
		
        antumbruh = new UnitMixMaxer("antumbruh"){{
            requirements(Category.units, with(Items.silicon, 200, Items.thorium, 150));
            size = 9;
            plans.add(
			new GerioPayloadItemUnitPlan(UnitTypes.antumbra, 1f * 60f,with(Items.beryllium, 15, Items.tungsten, 15, Items.oxide, 15,Items.carbide, 15),LiquidStack.with(Liquids.water, 60, Liquids.slag, 60),PayloadStack.list(UnitTypes.flare, 5, UnitTypes.horizon, 5, UnitTypes.zenith, 5))
			);
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};
    }
    public static void overwrite(){
		//Blocks.microProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 1, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 80);
		//Blocks.logicProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 3, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 150);
		//Blocks.hyperProcessor.requirements = with(GerioItems.pcb, 1, GerioItems.cpu, 6, GerioItems.gpu, 1, GerioItems.resistor, 40, GerioItems.capacitor, 45, Items.plastanium, 230);
    }
}