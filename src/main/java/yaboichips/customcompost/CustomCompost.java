package yaboichips.customcompost;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES;
import static yaboichips.customcompost.CustomCompost.MOD_ID;

@Mod(MOD_ID)
public class CustomCompost {

    public static final String MOD_ID = "customcompost";
    private static final Logger LOGGER = LogManager.getLogger();

    public CustomCompost() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::loadFinish);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CompostConfig.getConfigSpec(), "customcompost.toml");
    }

    private void loadFinish(FMLLoadCompleteEvent event) {
        LOGGER.info("Registering Compostables WHEEEEE");
        event.enqueueWork(CustomCompost::addCompostables);
    }

    public static void addCompostables() {
        for (String string : CompostConfig.item30.get()) {
            registerCompostable(0.30f, string);
        }
        for (String string : CompostConfig.item50.get()) {
            registerCompostable(0.50f, string);
        }
        for (String string : CompostConfig.item65.get()) {
            registerCompostable(0.65f, string);
        }
        for (String string : CompostConfig.item85.get()) {
            registerCompostable(0.85f, string);
        }
        for (String string : CompostConfig.item100.get()) {
            registerCompostable(1.00f, string);
        }
    }

    private static void registerCompostable(float chance, String itemIn) {
        COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemIn)), chance);
    }
}
