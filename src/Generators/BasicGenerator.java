package Generators;

import Generators.Factorial;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class BasicGenerator implements IGenerator {
    private static final String key = "Basic Generator";
    private int inputStringLength = 0;
    private int[] charIndexes;
    private long computeTime;
    private int nrOfGeneratedAnagrams = 0;
    private StringBuffer generatedOutput = new StringBuffer();
    private StringBuffer generationInfo;


    private void backtrack(int k) {
        for (int i = 0; i < inputStringLength; ++i) {
            charIndexes[k] = i;
            if (isSolution(k)) {
                addToOutput(buildAnagram());
            } else {
                backtrack(k + 1);
            }


        }
    }

    private boolean isSolution(int k) {
        return k == inputStringLength;
    }

    private String buildAnagram(){
        StringBuilder strBuild = new StringBuilder(inputStringLength);
        for (int i = 0; i < inputStringLength; ++i){
            strBuild.append(charIndexes[i]);
        }

        ++nrOfGeneratedAnagrams;
        return strBuild.toString();
    }

    private void addToOutput(String anagram){
        generatedOutput.append(anagram);
        generatedOutput.append('\n');
    }

    private void buildGenerationInfo(){
        generatedOutput.delete(0, generatedOutput.length());
        generatedOutput.append("Elapsed Time: ");
        generatedOutput.append(computeTime);
        generatedOutput.append("\nNumber of generated Anagrams: ");
        generatedOutput.append(nrOfGeneratedAnagrams);
    }


    @Override
    public String compute(String inputString) {
        //TODO: tis function catches java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3

        long initTime = System.currentTimeMillis();

        inputStringLength = inputString.length();
        charIndexes = new int[inputStringLength];

        nrOfGeneratedAnagrams = 0;
        generatedOutput.delete(0, generatedOutput.length());
        // in the generatedOutput we will have generated inputStringLength! anagrams. Multiply that times the
        // inputStringLength + 1 to compute nr of characters
        try {
            generatedOutput.setLength(Factorial.factorial(inputStringLength).intValue() * (inputStringLength + 1) + 1);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Attempted to instantiate a StringBuffer that's to big");
        }

        try {
            backtrack(0);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Backtracking Failed");
        }

        computeTime = initTime - System.currentTimeMillis();



        return generatedOutput.toString();
    }

    @Override
    public String getGenerationInfo(){
        generationInfo.insert(0, "Generation Information: \n\n");
        return generationInfo.toString();
    }


    @Override
    public String getKey() {
        return key;
    }

}
