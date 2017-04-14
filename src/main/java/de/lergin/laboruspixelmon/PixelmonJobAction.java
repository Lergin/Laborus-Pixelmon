package de.lergin.laboruspixelmon;

import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobActionState;
import de.lergin.laborus.api.JobItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.api.entity.living.player.Player;

import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;

import static de.lergin.laborus.api.JobActionState.BLOCK;

public abstract class PixelmonJobAction<T extends JobItem> extends JobAction<T> {
    public JobActionState onEvent(Event event, EntityPlayer player, BooleanSupplier isWorking, Callable<T> getJobItem) throws Exception {
        JobActionState state = super.onEvent((Player) player, isWorking, getJobItem);

        if (state == BLOCK){
            JobItem jobItem = getJobItem.call();

            sendBlockMessage((Player) player, jobItem);

            event.setCanceled(true);
        }

        return state;
    }

    public JobActionState onBlockEvent(Event event, EntityPlayer player, BooleanSupplier isWorking, Callable<T> getJobItem) throws Exception {
        JobActionState state = super.onBlockEvent((Player) player, isWorking, getJobItem);

        if (state == BLOCK){
            JobItem jobItem = getJobItem.call();

            sendBlockMessage((Player) player, jobItem);

            event.setCanceled(true);
        }

        return state;
    }
}
