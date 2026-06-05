package net.denmoth.createstructuresoverhaul;

import com.mojang.logging.LogUtils;
import net.denmoth.createstructuresoverhaul.worldgen.ModPlacementTypes;
import org.slf4j.Logger;

public class CreateStructuresOverhaulMod {
    public static final String MODID = "cso";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        ModPlacementTypes.init();
    }
}