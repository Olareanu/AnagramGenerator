package Controllers;

import Generators.AdvancedGnerator;
import Generators.BasicGenerator;
import Generators.GeneratorRegistry;
import Generators.IGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class HomeController {

    public TextField wordBox;
    public ComboBox<String> generatorsComboBox;

    IGenerator currentGenerator;


    public void initialize() {
        //Load all the generators in the hashMap
        GeneratorRegistry.addGenerator(new BasicGenerator());
        GeneratorRegistry.addGenerator(new AdvancedGnerator());

        //Create an array of keys of generators for displaying in comboBox
        ObservableList<String> generatorKeys = FXCollections.observableArrayList((GeneratorRegistry.listGeneratorKeys()));

        //load the list in the combobox
        generatorsComboBox.setItems(generatorKeys);

        // set the first value from the list as default in the combobox
        generatorsComboBox.setValue(new BasicGenerator().getKey());
        onSelectGenerator();
    }

    public void onSelectGenerator(){
        currentGenerator = GeneratorRegistry.getGenerator(generatorsComboBox.getValue());
    }


    public void generateAnagrams() {
        System.out.println(currentGenerator.compute(wordBox.getText()));
    }


}
