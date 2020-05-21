package Generators;

import Exeptions.InvalidStringException;

import java.util.ArrayList;

public class AdvancedGenerator implements IGenerator {
    private static final String key = "Advanced Generator";
    private static final boolean dictionaryUsage = false;

    @Override
    public ArrayList<String> compute(String word) throws InvalidStringException {
        ArrayList<String> outputList = new ArrayList<String>(1);
        outputList.add(word);
        return outputList;
    }

    @Override
    public String getGenerationInfo() {
        return "Generation Information:\n\nThis is advanced...\n\n\nActually has big cancer";
    }

    @Override
    public boolean getDictionaryUsage() {
        return dictionaryUsage;
    }

    @Override
    public String getKey() {
        return key;
    }
}
