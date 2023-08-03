package dev.shadowsoffire.despawntimers;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.LongValue;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobSpawnEvent.AllowDespawn;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(MobDespawnTimers.MODID)
public class MobDespawnTimers {

    public static final String MODID = "despawntimers";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public MobDespawnTimers() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    @SubscribeEvent
    public void onDespawn(AllowDespawn e) {
        Entity ent = e.getEntity();
        if (Config.getDespawnDelay() >= ent.tickCount) {
            e.setResult(Result.DENY);
        }
    }

    public static class Config {
        public static final ForgeConfigSpec SPEC;
        public static final Config INSTANCE;
        static {
            Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
            SPEC = specPair.getRight();
            INSTANCE = specPair.getLeft();
        }

        public final LongValue despawnDelay;

        public Config(ForgeConfigSpec.Builder b) {
            this.despawnDelay = b.comment("The time, in ticks, that a mob may not despawn for after spawning.").defineInRange("despawn_delay", 600, 0, Long.MAX_VALUE);
        }

        public static long getDespawnDelay() {
            return INSTANCE.despawnDelay.get();
        }
    }
}
