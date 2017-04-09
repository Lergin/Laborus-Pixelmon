package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.ThrowPokeballEvent;
import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobActionState;
import de.lergin.laboruspixelmon.items.PokeballJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

@ConfigSerializable
public class PokeballThrowJobAction extends JobAction<PokeballJobItem> {
    public PokeballThrowJobAction() {}

    @Setting(value = "items")
    private List<PokeballJobItem> jobItems = ImmutableList.of();

    @Override
    public String getId() {
        return "PIXELMON_THROW_POKEBALL";
    }

    @Override
    public List<PokeballJobItem> getJobItems() {
        return jobItems;
    }

    public void onEvent(ThrowPokeballEvent event) throws Exception {
        JobActionState state = super.onEvent((Player) event.player, ()->true, ()-> new PokeballJobItem(event.type));

        if(state == JobActionState.BLOCK || state == JobActionState.BLOCK_OTHER){
            event.setCanceled(true);
        }
    }
}

