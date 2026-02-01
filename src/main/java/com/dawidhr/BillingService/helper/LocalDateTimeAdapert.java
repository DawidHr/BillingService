package com.dawidhr.BillingService.helper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class LocalDateTimeAdapert extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
       return LocalDateTime.ofEpochSecond(jsonReader.nextLong(), 0, ZoneOffset.UTC);
    }
}
