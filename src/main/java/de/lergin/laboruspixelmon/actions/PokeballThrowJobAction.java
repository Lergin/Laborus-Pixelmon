package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.ThrowPokeballEvent;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PokeballJobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class PokeballThrowJobAction extends PixelmonJobAction<PokeballJobItem> {
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
        super.onEvent(event, event.player, ()->true, ()-> new PokeballJobItem(event.type));
    }
}

