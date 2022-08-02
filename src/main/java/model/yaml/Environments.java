package model.yaml;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Environments {

    private EnvironmentStructure env_int;
    private EnvironmentStructure env_test;

    public List<EnvironmentStructure> listOfEnvironments(){
        List<EnvironmentStructure> list = new ArrayList<>();
        list.add(getEnv_int());
        list.add(getEnv_test());
        return list;
    }
}
