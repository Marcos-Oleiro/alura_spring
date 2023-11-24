package med.voll.api.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class EnumCustomDeserializer<T extends Enum<T>> extends FromStringDeserializer<T> {

    private final Class<T> enumClass;

    public EnumCustomDeserializer(Class<T> enumClass) {
        super(enumClass);
        this.enumClass = enumClass;
    }

    @Override
    protected T _deserialize(String value, DeserializationContext ctxt) {
        return Enum.valueOf(enumClass, value.toUpperCase());
    }
}
