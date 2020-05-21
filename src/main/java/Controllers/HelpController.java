package Controllers;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class HelpController {

    public TextArea helpTextarea;

    public void initialize() {
        helpTextarea.setWrapText(true);
        helpTextarea.setFont(new Font(18));

        helpTextarea.setText("Select a generator type and then type a word into the wordbox to generate some" +
                "anagrams.\n For more information visit https://github.com/Olareanu/AnagramGenerator");

    }
}
