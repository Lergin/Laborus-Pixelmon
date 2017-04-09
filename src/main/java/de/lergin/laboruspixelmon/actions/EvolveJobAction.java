package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobActionState;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

@ConfigSerializable
public class EvolveJobAction extends JobAction<PixelmonJobItem> {
    public EvolveJobAction() {}

    @Setting(value = "items")
    private List<PixelmonJobItem> jobItems = ImmutableList.of();

    @Override
    public String getId() {
        return "PIXELMON_EVOLVE";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    public void onEvent(EvolveEvent event) throws Exception {
        JobActionState state = super.onEvent((Player) event.player, ()->true, ()-> new PixelmonJobItem(event.postEvo));

        if(state == JobActionState.BLOCK || state == JobActionState.BLOCK_OTHER){
            event.setCanceled(true);
        }
    }
}
