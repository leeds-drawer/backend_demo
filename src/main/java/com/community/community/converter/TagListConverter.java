// ────────────────── converter/TagListConverter.java ──────────────────
package com.community.community.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.List;

@Converter
public class TagListConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper om = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try { return om.writeValueAsString(attribute); }
        catch (Exception e) { return "[]"; }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try { return om.readValue(dbData, new TypeReference<>() {}); }
        catch (Exception e) { return Collections.emptyList(); }
    }
}