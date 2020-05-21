package Controllers;

import Generators.AdvancedGnerator;
import Generators.BasicGenerator;
import Generators.GeneratorRegistry;
import Generators.IGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;


public class HomeController {

    public TextField wordBox;
    public ComboBox<String> generatorsComboBox;
    public ComboBox<String> dictionaryComboBox;
    public MenuItem helpMenuItem;
    public MenuItem aboutMenuItem;
    public Label generationInfoLabel;
    public TextArea resultsTextArea;
    public MenuItem exportMenuItem;
    public MenuItem importDictionaryMenuItem;

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
    }

    public void onSelectGenerator() {
        currentGenerator = GeneratorRegistry.getGenerator(generatorsComboBox.getValue());
        dictionaryComboBox.setDisable(!currentGenerator.getDictionaryUsage());
    }

    public void onSelectDictionary() {
        //nothing right now, to be implemented
    }

    // the most important fucking function in this whole god dam class
    public void generateAnagrams() {
//        System.out.println(currentGenerator.compute(wordBox.getText()));
//        System.out.println("---------------------------------------------------------------------");


        //some multithread magic maybe
//        String inputString = wordBox.getText();
//        Thread generatorThread = new Thread(() -> currentGenerator.compute(inputString));
//        generatorThread.start();
//        //fa un threabpool
//        var e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        // e.submit()


        //aici paseaza si dicitionarul daca trebuie
        resultsTextArea.setText(currentGenerator.compute(wordBox.getText()));
        generationInfoLabel.setText(currentGenerator.getGenerationInfo());


        System.out.println(currentGenerator.getGenerationInfo());
    }

    public void onPressHelp() {
        try {
            Stage helpStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/Fxml/help.fxml"));
            helpStage.setTitle("Anagram Generator Help");
            helpStage.setScene(new Scene(root, 480, 360));
            helpStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onPressAbout() {
        try {
            Application app = new Application() {
                @Override
                public void start(Stage stage) throws Exception {

                }
            };
            app.getHostServices().showDocument("https://github.com/Olareanu/AnagramGenerator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPressExport() {

    }

    public void onPressImportDictionary() {
        Stage importStage = new Stage();
        FileChooser dictionaryFileChooser = new FileChooser();
        dictionaryFileChooser.setTitle("NOT IMPLEMENTED JET");
        dictionaryFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File dictionaryFile = dictionaryFileChooser.showOpenDialog(importStage);
        if (dictionaryFile != null) {
            try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(dictionaryFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

