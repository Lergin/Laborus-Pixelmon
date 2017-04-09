package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class CatchJobAction extends PixelmonJobAction<PixelmonJobItem> {
    public CatchJobAction() {}

    @Setting(value = "items")
    private List<PixelmonJobItem> jobItems = ImmutableList.of();

    @Override
    public String getId() {
        return "PIXELMON_CATCH";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    public void onEvent(CaptureEvent event) throws Exception {
        super.onEvent(event, event.player, ()->true, ()-> new PixelmonJobItem(event.pokemon.baseStats.pokemon));
    }
}
