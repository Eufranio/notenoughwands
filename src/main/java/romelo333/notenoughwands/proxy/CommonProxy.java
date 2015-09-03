package romelo333.notenoughwands.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import romelo333.notenoughwands.*;
import romelo333.notenoughwands.Items.GenericWand;
import romelo333.notenoughwands.network.PacketHandler;

public abstract class CommonProxy {

    private Configuration mainConfig;

    public void preInit(FMLPreInitializationEvent e) {
        mainConfig = NotEnoughWands.config;
        ModItems.init();
        readMainConfig();
        ModCrafting.init();
        GenericWand.setupChestLoot();
        PacketHandler.registerMessages("notenoughwands");
    }

    private void readMainConfig() {
        Configuration cfg = mainConfig;
        try {
            cfg.load();
            cfg.addCustomCategoryComment(Config.CATEGORY_WANDS, "Configuration for wodlgen");
            Config.init(cfg);
        } catch (Exception e1) {
            NotEnoughWands.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (mainConfig.hasChanged()) {
                mainConfig.save();
            }
        }
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (mainConfig.hasChanged()) {
            mainConfig.save();
        }
        mainConfig = null;
    }

}
