package Generators;

import Generators.Factorial;

import java.lang.*;

public class BasicGenerator implements IGenerator {
    private static final String key = "Basic Generator";
    private static final boolean dictionaryUsage = false;
    private String inputString;
    private StringBuilder outputBuilder;
    private int[] charIndexes;
    private long lastGenerationTime = 0;
    private int nrOfAnagrams = 0;

    void backtrack(int level) {
        for (int i = 0; i < inputString.length(); ++i) {
            charIndexes[level] = i;
            if (valid(level)) {
                if (isSolution(level)) {
                    adAnagramToOutput();
                } else {
                    backtrack(level + 1);
                }
            }
        }
    }

    private void adAnagramToOutput() {
        ++nrOfAnagrams;
        for (int i = 0; i < inputString.length(); ++i) {
            outputBuilder.append(inputString.charAt(charIndexes[i]));
        }
        outputBuilder.append('\n');
    }


    private boolean isSolution(int k) {
        return k == inputString.length() - 1;
    }


    private boolean valid(int k) {
        for (int i = 0; i < k; ++i) {
            if (charIndexes[i] == charIndexes[k]) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String compute(String inputString) {
        if (inputString.length() > 8) {
            return "String to long for Basic Generator.";
        }
        //TODO: make this an exception

        long lastTime = System.currentTimeMillis();
        nrOfAnagrams = 0;
        this.inputString = inputString;


        int length = inputString.length();
        charIndexes = new int[length + 1];
        outputBuilder = new StringBuilder(Factorial.factorial(inputString.length()).intValue() * (length + 1));

        try {
            backtrack(0);
        } catch (Exception e) {
            System.out.println("Somewhere in backtracking:\n");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        lastGenerationTime = System.currentTimeMillis() - lastTime;
        System.gc();
        return outputBuilder.toString();
    }

    @Override
    public String getGenerationInfo() {
        return "Generation info:\nNr of Anagrams: " + nrOfAnagrams +
                "\nElapsed time: " + lastGenerationTime + " millis";
    }

    @Override
    public boolean getDictionaryUsage(){
        return dictionaryUsage;
    }

    @Override
    public String getKey() {
        return key;
    }

}
