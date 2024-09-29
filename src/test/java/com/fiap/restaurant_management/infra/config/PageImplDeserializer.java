package com.fiap.restaurant_management.infra.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fiap.restaurant_management.infra.dto.BookingDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public class PageImplDeserializer extends StdDeserializer<PageImpl<?>> {

    public PageImplDeserializer() {
        super(PageImpl.class);
    }

    @Override
    public PageImpl<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<?> content = ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, BookingDto.class));
        Pageable pageable = PageRequest.of(0, 10);
        return new PageImpl<>(content, pageable, content != null ? content.size() : 0);
    }
}