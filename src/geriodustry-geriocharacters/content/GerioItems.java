package geriodustrycharacters.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class GerioItems{
    public static Item
    pcb, robotPcb, stepperMotor, capacitor, inductor, resistor, transformer, diode, transistor, icChip, cpu, gpu,
	
	// robot parts
    robotHip, robotHead, robotLeg, robotArm, robotTorso, robotHand
	;

    public static void load(){
        pcb = new Item("printed-circuit-board", Color.valueOf("009800")){{
			flammability = 0.2f;
        }};
        robotPcb = new Item("robot-prefabricated-pcb", Color.valueOf("009800")){{
			flammability = 0.2f;
        }};
        stepperMotor = new Item("stepper-motor", Color.valueOf("AAAAAA")){{
        }};
        capacitor = new Item("capacitor", Color.valueOf("AAAAAA")){{
			charge = 0.2f;
        }};
        inductor = new Item("inductor", Color.valueOf("AAAAAA")){{
			charge = 0.3f;
        }};
        resistor = new Item("resistor", Color.valueOf("AAAAAA")){{
        }};
        transformer = new Item("transformer", Color.valueOf("AAAAAA")){{
        }};
        diode = new Item("diode", Color.valueOf("AAAAAA")){{
        }};
        transistor = new Item("transistor", Color.valueOf("AAAAAA")){{
        }};
        icChip = new Item("integrated-circuit", Color.valueOf("AAAAAA")){{
        }};
        cpu = new Item("central-processing-unit", Color.valueOf("AAAAAA")){{
        }};
        gpu = new Item("graphics-processing-unit", Color.valueOf("AAAAAA")){{
        }};
        robotHip = new Item("robot-hip", Color.valueOf("666666")){{
        }};
        robotHead = new Item("robot-head", Color.valueOf("666666")){{
        }};
        robotLeg = new Item("robot-leg", Color.valueOf("666666")){{
        }};
        robotArm = new Item("robot-arm", Color.valueOf("666666")){{
        }};
        robotTorso = new Item("robot-torso", Color.valueOf("666666")){{
        }};
        robotHand = new Item("robot-hand", Color.valueOf("666666")){{
        }};
    }
}