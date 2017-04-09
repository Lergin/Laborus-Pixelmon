package de.lergin.laboruspixelmon.items;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import de.lergin.laborus.api.JobItem;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.Text;

import java.util.Locale;

@ConfigSerializable
public class PixelmonJobItem extends JobItem {
    @Setting(value = "item", comment = "a pokemon type")
    private EnumPokemon item = EnumPokemon.Pikachu;

    public PixelmonJobItem() {}

    public PixelmonJobItem(EnumPokemon item) {
        this.item = item;
    }

    /**
     * returns the item
     *
     * @return the item
     */
    @Override
    public EnumPokemon getItem() {
        return item;
    }

    /**
     * decides if the item matches the current object
     *
     * @param item
     */
    @Override
    public boolean matches(JobItem item) {
        return item.getItem() instanceof EnumPokemon && matches((EnumPokemon) item.getItem());
    }

    public boolean matches(EnumPokemon item) {
        return getItem().equals(item);
    }

    @Override
    public Text getName(Locale locale) {
        return Text.of(getItem().name);
    }
}
