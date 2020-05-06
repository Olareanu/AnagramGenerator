package Controllers;

import Generators.AdvancedGnerator;
import Generators.BasicGenerator;
import Generators.GeneratorRegistry;
import Generators.IGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class HomeController {

    public TextField wordBox;
    public ComboBox<String> generatorsComboBox;
    public ComboBox dictionaryComboBox;
    public CheckBox dictionaryCheckBox;
    public MenuItem helpMenuItem;
    public MenuItem aboutMenuItem;
    public Label generationInfoLabel;
    public TextFlow resultsTextFlow;
    public MenuItem exportMenuItem;
    public MenuItem importDictionaryMenuItem;

    private Text resultsText;

    IGenerator currentGenerator;


    public void initialize() {

        /* Generator selection */

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

        /* resultsText styling */

        //resultsText.setFont(Font.font("Arial", 12));


    }

    public void onSelectGenerator() {
        currentGenerator = GeneratorRegistry.getGenerator(generatorsComboBox.getValue());
    }

    public void onActivateDictionary() {
        dictionaryComboBox.setDisable(!dictionaryCheckBox.isSelected());
    }

    public void onSelectDictionary() {
        //nothing
    }

    // the most important fucking function in this whole god dam class
    public void generateAnagrams() {
        System.out.println(currentGenerator.compute(wordBox.getText()));

        System.out.println("---------------------------------------------------------------------");

        resultsText = new Text(currentGenerator.compute(wordBox.getText()));
        resultsTextFlow.getChildren().clear();
        resultsTextFlow.getChildren().add(resultsText);

        System.out.println("---------------------------------------------------------------------");

        generationInfoLabel.setText(currentGenerator.getGenerationInfo());
    }

    public void onPressHelp() {
        try {
            Stage helpStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Fxml/help.fxml"));
            helpStage.setTitle("Anagram Generator Help");
            helpStage.setScene(new Scene(root, 480, 360));
            helpStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onPressAbout() {
        try {
            Stage aboutStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Fxml/about.fxml"));
            aboutStage.setTitle("About Anagram Generator");
            aboutStage.setScene(new Scene(root, 480, 360));
            aboutStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPressExport() {

    }

    public void onPressImportDictionary() {

    }
}
