package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    @JsonProperty("parking_positions")
    private List<Position> positions;
    @JsonProperty("entries")
    private List<Entry> entries;
    @JsonProperty("entry_position_map")
    private Map<String, List<String>> entryPositionMap;
}
