package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Entry {
    @JsonProperty("id")
    private final String id;

    @JsonCreator
    public Entry(@JsonProperty("id") String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
