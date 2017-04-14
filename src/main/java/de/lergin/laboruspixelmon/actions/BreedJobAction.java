package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import de.lergin.laborus.job.Job;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.objectmapping.Setting;

import java.util.List;

public class BreedJobAction extends PixelmonJobAction<PixelmonJobItem> {
    public BreedJobAction() {}

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
        return "PIXELMON_BREED";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    @SubscribeEvent
    public void onEvent(BreedEvent.MakeEgg event) throws Exception {
        super.onEvent(event, event.owner, ()->true, ()-> new PixelmonJobItem(event.getEgg().baseStats.pokemon));
    }
}
