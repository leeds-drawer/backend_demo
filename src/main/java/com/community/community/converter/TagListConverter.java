// ────────────────── converter/TagListConverter.java ──────────────────
package com.community.community.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.List;

@Converter(autoApply = true)
public class TagListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try { return om.writeValueAsString(attribute); }
        catch (Exception e) { throw new IllegalArgumentException("Failed to convert tags to JSON string", e); }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try { return om.readValue(dbData, new TypeReference<>() {}); }
        catch (Exception e) { throw new IllegalArgumentException("Failed to convert JSON string to tags", e); }
    }
}