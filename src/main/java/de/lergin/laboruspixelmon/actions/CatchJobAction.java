package de.lergin.laboruspixelmon.actions;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobActionState;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

@ConfigSerializable
public class CatchJobAction extends JobAction<PixelmonJobItem> {
    public CatchJobAction() {}

    @Setting(value = "items")
    private List<PixelmonJobItem> jobItems;

    @Override
    public String getId() {
        return "PIXELMON_CATCH";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    public void onEvent(CaptureEvent event) throws Exception {
        JobActionState state = super.onEvent((Player) event.player, ()->true, ()->
                new PixelmonJobItem(event.pokemon.baseStats.pokemon));

        if(state == JobActionState.BLOCK || state == JobActionState.BLOCK_OTHER){
            event.setCanceled(true);
        }
    }
}
