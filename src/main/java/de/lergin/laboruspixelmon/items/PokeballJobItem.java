package de.lergin.laboruspixelmon.items;

import com.pixelmonmod.pixelmon.enums.items.EnumPokeballs;
import de.lergin.laborus.api.JobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.Text;

import java.util.Locale;

@ConfigSerializable
public class PokeballJobItem extends JobItem {
    @Setting(value = "item", comment = "a pokeball type")
    private EnumPokeballs item = EnumPokeballs.PokeBall;

    public PokeballJobItem() {}

    public PokeballJobItem(EnumPokeballs item) {
        this.item = item;
    }

    /**
     * returns the item
     *
     * @return the item
     */
    @Override
    public EnumPokeballs getItem() {
        return item;
    }

    /**
     * decides if the item matches the current object
     *
     * @param item
     */
    @Override
    public boolean matches(JobItem item) {
        return item.getItem() instanceof EnumPokeballs && matches((EnumPokeballs) item.getItem());
    }

    public boolean matches(EnumPokeballs item) {
        return getItem().equals(item);
    }

    @Override
    public Text getName(Locale locale) {
        return Text.of(getItem().getItem().getLocalizedName());
    }
}
