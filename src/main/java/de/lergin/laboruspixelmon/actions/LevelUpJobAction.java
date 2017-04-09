package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.LevelUpEvent;
import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobActionState;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

@ConfigSerializable
public class LevelUpJobAction extends JobAction<PixelmonJobItem> {
    public LevelUpJobAction() {}

    @Setting(value = "items")
    private List<PixelmonJobItem> jobItems = ImmutableList.of();

    @Override
    public String getId() {
        return "PIXELMON_LEVELUP";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    public void onEvent(LevelUpEvent event) throws Exception {
        JobActionState state = super.onEvent((Player) event.player, ()->true, ()->
                new PixelmonJobItem(event.pokemon.baseStats.pokemon));

        if(state == JobActionState.BLOCK || state == JobActionState.BLOCK_OTHER){
            event.setCanceled(true);
        }
    }
}
