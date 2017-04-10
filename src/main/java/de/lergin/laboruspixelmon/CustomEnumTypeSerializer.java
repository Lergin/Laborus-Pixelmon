/**
 * Based on https://github.com/zml2008/configurate/blob/0b6b95818a2cf19a6d46d46c09ec7d637a17b7b9/configurate-core/src/main/java/ninja/leaping/configurate/objectmapping/serialize/TypeSerializers.java
 * by zml and Configurate contributors
 *
 * modification of the EnumValueSerializer class to support non uppercase only enums by Malte Laukötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
