package Generators;

import Generators.IGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneratorRegistry {
    static Map<String, IGenerator> generatorMap = new HashMap<String, IGenerator>();

    public static List<IGenerator> listGenerators() {
        return (List<IGenerator>) generatorMap.values();
    }

    public static Set<String> listGeneratorKeys(){
        return generatorMap.keySet();
    }


    //not a simple getter?
    public static IGenerator getGenerator(String generatorType) {
        return generatorMap.get(generatorType);
    }

    public static void addGenerator(IGenerator generator) {
        generatorMap.put(generator.getKey(), generator);
    }

}
