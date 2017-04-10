package de.lergin.laboruspixelmon;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class CustomEnumTypeSerializer implements TypeSerializer<Enum> {
    @Override
    public Enum deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
        String enumConstant = value.getString();
        if (enumConstant == null) {
            throw new ObjectMappingException("No value present in node " + value);
        }

        Enum ret;
        try {
            ret = Enum.valueOf(type.getRawType().asSubclass(Enum.class), value.getString());
        } catch (IllegalArgumentException ex) {
            try {
                enumConstant = enumConstant.toUpperCase();

                ret = Enum.valueOf(type.getRawType().asSubclass(Enum.class), value.getString().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ObjectMappingException("Invalid enum constant provided for " + value.getKey() + ": Expected a value of enum " + type + ", got " + enumConstant);
            }
        }

        return ret;
    }

    @Override
    public void serialize(TypeToken<?> type, Enum obj, ConfigurationNode value) throws ObjectMappingException {
        value.setValue(obj.name());
    }
}
