package geriodustrycharacters.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class GerioItems{
    public static Item
    pcb, stepperMotor;

    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
        pcb = new Item("printed-circuit-board", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 0.5f;
        }};
        stepperMotor = new Item("stepper-motor", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 0.5f;
        }};
    }
}