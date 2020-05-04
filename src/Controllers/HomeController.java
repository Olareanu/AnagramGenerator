package Controllers;

import Generators.Generator;
import javafx.scene.control.*;

public class HomeController {

    public TextField wordBox;


    public void initialize() {
        Generator generator = new Generator();
    }

    public void generateAnagrams() {


        System.out.println(wordBox.getText());
        String word = wordBox.getText();
        System.out.println();

    }


}
