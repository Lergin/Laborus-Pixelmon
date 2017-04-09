package de.lergin.laboruspixelmon;

import com.google.inject.Inject;
import de.lergin.laborus.api.JobService;
import de.lergin.laboruspixelmon.actions.CatchJobAction;
import de.lergin.laboruspixelmon.actions.EvolveJobAction;
import de.lergin.laboruspixelmon.actions.PokeballThrowJobAction;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;

@Plugin(
        id = "laboruspixelmon",
        name = "Laborus - Pixelmon",
        description = "A extenision to Laborus to add support for Pixelmon specific stuff",
        authors = {
                "Lergin"
        }
)
public class Laboruspixelmon {
    @Inject
    private Logger logger;


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

        JobService service = optional.get();

        service.registerJobAction(CatchJobAction.class, "pixelmon_catch");
        service.registerJobAction(EvolveJobAction.class, "pixelmon_evolve");
        service.registerJobAction(PokeballThrowJobAction.class, "pixelmon_throw");
    }
}
