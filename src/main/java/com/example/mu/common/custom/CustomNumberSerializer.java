package com.example.mu.common.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 가격 포맷 Serializer (JSON)
 */
public class CustomNumberSerializer extends JsonSerializer<Integer> {

    private static final DecimalFormat df = new DecimalFormat("#,###");

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(df.format(value));
    }
}