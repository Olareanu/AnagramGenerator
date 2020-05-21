package Generators;

import Exeptions.InvalidStringException;

import java.util.ArrayList;

public interface IGenerator {
    public ArrayList<String> compute(String string) throws InvalidStringException;
    public String getGenerationInfo();
    public String getKey();
    public boolean getDictionaryUsage();

}
