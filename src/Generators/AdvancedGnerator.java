package Generators;

public class AdvancedGnerator implements IGenerator {
    private static final String key = "Advanced Generator";

    @Override
    public String compute(String word) {
        return word;
    }

    @Override
    public String getKey() {
        return key;
    }
}
