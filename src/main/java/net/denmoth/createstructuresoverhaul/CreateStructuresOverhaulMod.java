package net.denmoth.createstructuresoverhaul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod(CreateStructuresOverhaulMod.MODID)
public class CreateStructuresOverhaulMod {
    public static final String MODID = "create_structures_overhaul";
    public static final Logger LOGGER = LogManager.getLogger();

    public CreateStructuresOverhaulMod() {
        LOGGER.info("[CSO] Loaded Create: Structures Overhaul");
    }
}
