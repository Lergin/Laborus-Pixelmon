package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.AnvilEvent;
import de.lergin.laborus.job.Job;
import de.lergin.laborus.job.items.ItemJobItem;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.List;

@ConfigSerializable
public class AnvilJobAction extends PixelmonJobAction<ItemJobItem> {
    public AnvilJobAction() {}

    @Setting(value = "items")
    private List<ItemJobItem> jobItems = ImmutableList.of();

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

    @Override
    public String getId() {
        return "PIXELMON_ANVIL";
    }

    @Override
    public List<ItemJobItem> getJobItems() {
        return jobItems;
    }

    @SubscribeEvent
    public void onEvent(AnvilEvent.BeatAnvil event) throws Exception {
        System.out.println(event.item);

        super.onEvent(event, event.player, ()-> null != event.item,
                ()->ItemJobItem.fromItemStack(ItemStack.of((ItemType) event.item, 1)));
    }
}
