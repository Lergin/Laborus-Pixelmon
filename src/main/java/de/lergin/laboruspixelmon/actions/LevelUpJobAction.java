package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.LevelUpEvent;
import de.lergin.laborus.job.Job;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class LevelUpJobAction extends PixelmonJobAction<PixelmonJobItem> {
    public LevelUpJobAction() {}

    @Override
    public void init(Job job) {
        super.init(job);
        Pixelmon.EVENT_BUS.register(this);
    }

    @Override
    public void unload() {
        super.unload();
        Pixelmon.EVENT_BUS.unregister(this);
    }

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

    @SubscribeEvent
    public void onEvent(LevelUpEvent event) throws Exception {
        super.onEvent(event, event.player, ()->true, ()-> new PixelmonJobItem(event.pokemon.baseStats.pokemon));
    }
}
