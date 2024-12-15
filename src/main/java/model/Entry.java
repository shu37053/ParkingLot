package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import exceptions.OperationNotAllowed;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Entry {
    @JsonProperty("id")
    private final String id;
    private List<Position> positions;

    @JsonCreator
    public Entry(@JsonProperty("id") String id) {
        this.id = id;
        positions = null;
    }

    public void setPositions(List<Position> positions) {
        if(this.positions != null) {
            throw new OperationNotAllowed("position can be set only once");
        }
        this.positions = positions;
    }
}
