package de.lergin.laboruspixelmon.actions;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.AnvilEvent;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import com.pixelmonmod.pixelmon.items.ItemPokeballDisc;
import de.lergin.laborus.job.Job;
import de.lergin.laborus.job.items.ItemJobItem;
import de.lergin.laboruspixelmon.PixelmonJobAction;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.living.player.Player;
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
    public void onEvent(AnvilEvent.FinishedSmith event) throws Exception {
        super.onEvent((Player) event.player, ()-> null != event.item,
                ()->ItemJobItem.fromItemStack(ItemStack.of((ItemType) event.item, 1)));
    }

    @SubscribeEvent
    public void onBlockEvent(AnvilEvent.MaterialChanged event) throws Exception {
        super.onBlockEvent(event, event.player, ()-> true,
                ()->ItemJobItem.fromItemStack(ItemStack.of((ItemType) getResultFromItem(event.material.getItem()), 1)));
    }

    @SubscribeEvent
    public void onBlockEvent(AnvilEvent.BeatAnvil event) throws Exception {
        super.onBlockEvent(event, event.player, ()-> null != event.item,
                ()->ItemJobItem.fromItemStack(ItemStack.of((ItemType) getResultFromItem(event.item), 1)));
    }

    private Item getResultFromItem(Item item){
        if(item == PixelmonItems.aluminiumIngot) {
            return PixelmonItems.aluminiumPlate;
        }else if(item == PixelmonItemsPokeballs.ironDisc) {
            return PixelmonItemsPokeballs.ironBase;
        } else if(item == PixelmonItemsPokeballs.aluDisc) {
            return PixelmonItemsPokeballs.aluBase;
        } else  if(item instanceof ItemPokeballDisc){
            return PixelmonItemsPokeballs.getLidFromEnum(((ItemPokeballDisc) item).pokeball);
        }

        return item;
    }
}
