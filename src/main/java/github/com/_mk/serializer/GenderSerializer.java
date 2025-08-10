package github.com._mk.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String gender, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String formattedGender = Boolean.parseBoolean(String.valueOf(
                "Male".equals(gender))) ? "F" : "M";
        jsonGenerator.writeString(formattedGender);


    }
}
