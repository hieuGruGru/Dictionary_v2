package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Dictionary dictionary1 = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    TextField SearchText = new TextField();
    @FXML
    TextField EText = new TextField();
    @FXML
    TextField TText = new TextField();
    @FXML
    TextField VText = new TextField();
    @FXML
    TextField StatusText = new TextField();
    @FXML
    Button buttonSearch;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonFix;
    @FXML
    Button buttonDel;
    @FXML
    Button buttonSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            dictionaryManagement.insertFromFile(dictionary1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_target());
            listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_explain());
        }

    }

    public void autoCompelete(KeyEvent keyEvent) {
        listView.getItems().clear();
        String s = SearchText.getText();
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            if (Search.prefixSearch(dictionary1, s)  == 0) {
                listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_target());
            }
        }
    }


    public void Show_word(MouseEvent event) {
        String s = listView.getSelectionModel().getSelectedItem();
        Word word_show = dictionaryManagement.getWord(dictionary1,s);
        EText.setText(word_show.getWord_target());
        VText.setText(word_show.getWord_explain());
        StatusText.clear();
    }


    public void Look_up(ActionEvent event) {
        EText.clear();
        VText.clear();
        StatusText.clear();
        listView.getItems().clear();
        String s = SearchText.getText();
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            if (dictionary1.arrayOfWord.get(i).getWord_target().startsWith(s)) {
                listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_target());
            }
        }
    }

    public void AddWord(ActionEvent event) {
        String word = EText.getText();
        String mean = VText.getText();
        int kt = 0;
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            if (word.equalsIgnoreCase(dictionary1.arrayOfWord.get(i).getWord_target())) {
                kt = 1;
                mean = dictionary1.arrayOfWord.get(i).getWord_explain() + ", " + mean;
                dictionaryManagement.modifyWord(dictionary1.arrayOfWord.get(i), word, mean);
            }
        }
        StatusText.setText("Your word has been added!");
        if (kt == 0) dictionaryManagement.insertFromText(dictionary1, word, mean);
        EText.clear();
        VText.clear();
        StatusText.clear();
        //Show_word();
    }

    public void DelWord() {
        String a = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(a);
        EText.clear();
        VText.clear();
        StatusText.clear();
        StatusText.setText("Your word has been deleted!");
        dictionaryManagement.removeWord(dictionary1, a);
    }

    public void Fix_word(ActionEvent event) {
        String a = listView.getSelectionModel().getSelectedItem();
        Word word = dictionaryManagement.getWord(dictionary1, a);
        StatusText.setText("Your word has been fixed!");
        dictionaryManagement.modifyWord(dictionaryManagement.getWord(dictionary1, a), EText.getText(), VText.getText());
        EText.clear();
        VText.clear();
        //Show_word();
    }

    public void end_game(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        dictionaryManagement.saveFile(dictionary1);
    }
}