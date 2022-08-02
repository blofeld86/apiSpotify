package configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import model.yaml.EnvironmentStructure;
import model.yaml.Pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

@Slf4j
public class YamlReader {

    public static Pojo getProperties(){
        final ObjectMapper om = new ObjectMapper(new YAMLFactory());
        try{
            return om.readValue(new File(getProperty("user.dir")+"\\src\\main\\resources\\properties.yml"),Pojo.class);
        }catch (IOException e){
            log.error("Can not read a properties due to IOException");
        }
        return null;
    }

    public static void setPropertiesFromYAML(){
        List<EnvironmentStructure> list = getProperties().getEnvironments().listOfEnvironments();
        boolean isActive = false;
        for(EnvironmentStructure structure : list){
            if(structure.isActive()){
                isActive = true;
                Map<String,Object> envProperties = structure.getStructure();
                for(Map.Entry entry : envProperties.entrySet()){
                    setProperty(entry.getKey().toString(),entry.getValue().toString());
                    log.info("Loaded environment property: {} - {}",
                            entry.getKey().toString(),entry.getValue().toString());
                }
                log.info("Loaded environment properties total: {}",envProperties.size());
            }
        }
    }
}
