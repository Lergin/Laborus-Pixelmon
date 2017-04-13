package de.lergin.laboruspixelmon;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import de.lergin.laborus.*;
import de.lergin.laborus.api.JobService;
import de.lergin.laboruspixelmon.actions.*;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;

@Plugin(
        id = "laboruspixelmon",
        name = "Laborus - Pixelmon",
        description = "An extension to Laborus to add support for Pixelmon specific stuff",
        authors = {
                "Lergin"
        },
        dependencies = {
                @Dependency(id = "laborus")
        }
)
public class LaborusPixelmon {
    @Inject
    private Logger logger;

    @Inject
    private Metrics metrics;

    @Listener
    public void onGamePreInitialization(GamePreInitializationEvent event) {
        if(!Sponge.getServiceManager().isRegistered(JobService.class)){
            this.logger.warn("Laborus not installed deactivating LaborusPixelmon...");
            return;
        }

        Optional<JobService> optional = Sponge.getServiceManager().provide(JobService.class);

        if(!optional.isPresent()){
            this.logger.warn("Laborus not installed deactivating LaborusPixelmon...");
            return;
        }

        // we need to overwrite the TypeSerializer for enums because Configurate assumes Enum values are Uppercase only
        // but Pixelmons enum values are CamelCase (see https://github.com/zml2008/configurate/issues/77)
        TypeSerializers.getDefaultSerializers().registerType(new TypeToken<Enum<?>>() {}, new CustomEnumTypeSerializer());

        JobService service = optional.get();

        service.registerJobAction(CatchJobAction.class, "pixelmon_catch");
        service.registerJobAction(EvolveJobAction.class, "pixelmon_evolve");
        service.registerJobAction(PokeballThrowJobAction.class, "pixelmon_throw");
        service.registerJobAction(LevelUpJobAction.class, "pixelmon_levelup");
    }
}
