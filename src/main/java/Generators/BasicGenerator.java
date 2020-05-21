package Generators;

import Exeptions.InvalidStringException;

import java.lang.*;
import java.util.ArrayList;

public class BasicGenerator implements IGenerator {
    private static final String key = "Basic Generator";
    private static final boolean dictionaryUsage = false;
    private String inputString;
    private int[] charIndexes;
    private ArrayList<String> outputArray;
    private final StringBuilder stringBuilder = new StringBuilder();
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
        stringBuilder.delete(0, stringBuilder.capacity()-1);
        for (int i = 0; i < inputString.length(); ++i) {
            stringBuilder.append(inputString.charAt(charIndexes[i]));
        }
        outputArray.add(stringBuilder.toString());
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
    public ArrayList<String> compute(String inputString) throws InvalidStringException {

        if (inputString.length() > 8) {
            throw new InvalidStringException("The input String is to long for this type of generator.\n" +
                    "Input a String with a maximum of 8 characters or select another generator.");
        }

        /*
        Note:

        This algorithm has been tested with a String of 11 characters.
        The compute time was around 13 seconds with additional 5 seconds for displaying everything.
        12 characters cause the app to eat up 8 GB of RAM and then use all the computer's threads to 100%.
        Then it does nothing, just sits there. (Tested on an 2018 core i9 in a dell xps laptop w/ 32 GB of RAM)

        To make sure other computers don't crash we set the limit to 8 characters.

         */


        //logging of some parameters to generate some information
        long lastTime = System.currentTimeMillis();
        nrOfAnagrams = 0;

        //
        this.inputString = inputString;
        int length = inputString.length();
        charIndexes = new int[length + 1];

        outputArray = new ArrayList<>(Factorial.factorial(inputString.length()).intValue());


        //do the actual backtracking
        backtrack(0);


        lastGenerationTime = System.currentTimeMillis() - lastTime;
        System.gc();
        return outputArray;
    }

    @Override
    public String getGenerationInfo() {
        return "Generation info:\nNr of Anagrams: " + nrOfAnagrams +
                "\nElapsed time: " + lastGenerationTime + " millis\n\nBacktracking based generator";
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
