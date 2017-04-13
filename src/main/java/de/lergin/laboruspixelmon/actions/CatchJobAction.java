package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import de.lergin.laborus.job.Job;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import de.lergin.laboruspixelmon.items.PixelmonJobItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class CatchJobAction extends PixelmonJobAction<PixelmonJobItem> {
    public CatchJobAction() {}

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
        return "PIXELMON_CATCH";
    }

    @Override
    public List<PixelmonJobItem> getJobItems() {
        return jobItems;
    }

    @SubscribeEvent
    public void onEvent(CaptureEvent.SuccessfulCapture event) throws Exception {
        super.onEvent(event, event.player, ()->true, ()-> new PixelmonJobItem(event.pokemon.baseStats.pokemon));
    }
}
