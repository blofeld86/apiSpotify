package model.yaml;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class EnvironmentStructure {

    private boolean active;

    @JsonAnyGetter
    @JsonAnySetter
    private Map<String,Object> structure = new LinkedHashMap<>();
}
