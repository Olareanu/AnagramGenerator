package Generators;

public class BasicGenerator implements IGenerator {
    private static final String key = "Basic Generator";
    private int stringLength;
    private String generatedList;

    private void backtrack(int k) {
        for (int i = 0; i < stringLength; ++i) {
            if (isSolution(k)) {


            } else {
                backtrack(k + 1);
            }


        }
    }

    private boolean isSolution(int k) {
        return true;
    }

    @Override
    public String compute(String word) {
        this.stringLength = word.length();


        return word;
    }

    @Override
    public String getKey() {
        return key;
    }

}
