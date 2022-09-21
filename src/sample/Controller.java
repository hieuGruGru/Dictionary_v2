package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    Audio audio1 = new Audio();
    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    TextField SearchText = new TextField();
    @FXML
    TextField EText = new TextField();
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
    @FXML
    Button Speech;
    @FXML
    Button Reset;
    @Override
    public void initialize(URL url, ResourceBundle resoucre) {
        try {
            dictionaryManagement.insertFromFile(dictionary1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_target());
        }
    }

    /*public void changeSource1() {
        String pathname1 = "D:\\1.Subjects\\OOP\\OOP_N1_BTL_N9\\soucre_code\\Dictionary_v2\\src\\sample\\dictionaries_test.txt";
        initialize(pathname1);
    }*/

    /*public void changeSource2() {
        String pathname2 = "D:\\1.Subjects\\OOP\\OOP_N1_BTL_N9\\soucre_code\\Dictionary_v2\\src\\sample\\dictionaries.txt";
        initialize(pathname2);
    }*/

    public void autoCompelete(KeyEvent keyEvent) {
        listView.getItems().clear();
        String s = SearchText.getText();
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            if (Search.prefixSearch(dictionary1.arrayOfWord.get(i).getWord_target(), s)  == 0) {
                listView.getItems().add(dictionary1.arrayOfWord.get(i).getWord_target());
            }
        }
    }


    public void showWord(MouseEvent event) {
        String s = listView.getSelectionModel().getSelectedItem();
        Word word_show = dictionaryManagement.getWord(dictionary1, s);
        EText.setText(word_show.getWord_target());
        VText.setText(word_show.getWord_explain());
        StatusText.clear();
    }


    public void lookUp(ActionEvent event) {
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

    public void addWord(ActionEvent event) {
        String word = EText.getText();
        String mean = VText.getText();
        int kt = 0;
        for (int i = 0; i < dictionary1.arrayOfWord.size(); i++) {
            if (word.equalsIgnoreCase(dictionary1.arrayOfWord.get(i).getWord_target())) {
                StatusText.setText("Từ này đã có trong từ điển rồi");
            }
        }
        StatusText.setText("Xong, đã thêm từ " + word + " vào từ điển");
        if (kt == 0) dictionaryManagement.insertFromText(dictionary1, word, mean);
        EText.clear();
        VText.clear();
        //Show_word();
    }

    public void pronounce_E(ActionEvent event) {
        String textPronounce1 = new String(EText.getText());
        audio1.Text_Speech(textPronounce1);
        //EText.clear();
        //VText.clear();
    }

    public void deletelWord() {
        String a = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(a);
        EText.clear();
        VText.clear();
        StatusText.clear();
        StatusText.setText("Tôi xóa cái từ đấy đi rồi 🤷‍");
        dictionaryManagement.removeWord(dictionary1, a);
    }

    public void updateWord(ActionEvent event) {
        String a = listView.getSelectionModel().getSelectedItem();
        Word word = dictionaryManagement.getWord(dictionary1, a);
        StatusText.clear();
        StatusText.setText("Đã chỉnh sửa xong 😃 ");
        dictionaryManagement.modifyWord(dictionaryManagement.getWord(dictionary1, a), EText.getText(), VText.getText());
        EText.clear();
        VText.clear();
    }

    public void saveFile(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        dictionaryManagement.saveFile(dictionary1);
        StatusText.clear();
        StatusText.setText("Đã lưu file 🤦‍");
    }

}