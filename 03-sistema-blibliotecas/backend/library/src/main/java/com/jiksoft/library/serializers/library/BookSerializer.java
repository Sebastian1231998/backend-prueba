package com.jiksoft.library.serializers.library;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jiksoft.library.models.library.BookEntity;

import java.io.IOException;
import java.io.Serial;

public class BookSerializer extends StdSerializer<BookEntity> {

    @Serial
    private static final long serialVersionUID = -73218900195707573L;

    protected BookSerializer(Class<BookEntity> t) {
        super(t);
    }

    protected BookSerializer() {
        this(null);
    }

    @Override
    public void serialize(BookEntity entity,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("id", entity.getId().toString());
        jsonGenerator.writeStringField("name", entity.getName());
        jsonGenerator.writeStringField("author", entity.getAuthor());
        jsonGenerator.writeBooleanField("available", entity.getAvailable());

        if (entity.getLibrary() != null) {
            jsonGenerator.writeStringField("library_id", entity.getLibrary().getId().toString());
        } else {
            jsonGenerator.writeNullField("library_id");
        }

        jsonGenerator.writeEndObject();
    }
}