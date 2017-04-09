package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class EvolveJobAction extends PixelmonJobAction<PixelmonJobItem> {
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
        super.onEvent(event, event.player, ()->true, ()-> new PixelmonJobItem(event.postEvo));
    }
}
