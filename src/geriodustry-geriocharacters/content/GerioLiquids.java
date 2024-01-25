package geriodustrycharacters.content;

import arc.graphics.*;
import mindustry.type.*;

public class GerioLiquids{
    public static Liquid blueBlood, shieldEfficiencyLiquid;

    public static void load(){

        blueBlood = new Liquid("blue-blood", Color.valueOf("40B8D8")){{
            viscosity = 0.6f;
        }};

        shieldEfficiencyLiquid = new Liquid("shield-efficiency-liquid", Color.valueOf("DDDDDD")){{
            viscosity = 0.9f;
        }};
    }
}