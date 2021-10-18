package sample;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromText(Dictionary dictionary1, String target, String explain)   {
        Word newWord = new Word(target, explain);
        dictionary1.arrayOfWord.add(newWord);
    }

    public void insertFromFile(Dictionary dictionary1) throws IOException { //Load các cặp từ từ file .txt vào mảng các Word

        File filename = new File("D:/java/Dictionary_v2/src/sample/dictionaries.txt");
        Scanner sc = new Scanner(filename);
        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();
            int indexOfTab = currentLine.indexOf("\t");
            //System.out.println("Vi tri dau tab la : " + indexOfTab);
            String target = currentLine.substring(0, indexOfTab);
            //System.out.println("Tu dau tien la : " + target);
            String explain = currentLine.substring(indexOfTab + 1, currentLine.length());
            //System.out.println("Tu thu hai la : " + explain);
            Word newWord = new Word(target, explain);
            dictionary1.arrayOfWord.add(newWord);
        }
        sc.close();
    }

    public Word getWord(Dictionary List, String a) {
        Word word = new Word();
        word.setWord_target("ERROR!!");
        word.setWord_explain("");
        for(int i=0; i<List.arrayOfWord.size();i++){
            if(a.equalsIgnoreCase(List.arrayOfWord.get(i).getWord_target())){
                return List.arrayOfWord.get(i);
            }
        }
        return word;
    }

    public int removeWord(Dictionary List, String remove_word) {

        for(int i=0;i<List.arrayOfWord.size();i++){
            if(remove_word.equalsIgnoreCase(List.arrayOfWord.get(i).getWord_target())){
                List.arrayOfWord.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public void modifyWord(Word word_fix, String target, String explain) {
        word_fix.setWord_target(target);
        word_fix.setWord_explain(explain);
    }

    public void saveFile(Dictionary dictionay1) throws FileNotFoundException, UnsupportedEncodingException {
        try{
            FileWriter fw = new FileWriter(
                    "C:\\Users\\Administrator\\Desktop\\HK I-2021\\LTHDT\\Test\\src\\sample\\dictionaries.txt");
            //writer.print("");
            for( int i = 0; i < dictionay1.arrayOfWord.size(); i ++){
                fw.write(dictionay1.arrayOfWord.get(i).getWord_target() + "\t");
                fw.write(dictionay1.arrayOfWord.get(i).getWord_explain() + "\n");
            }
            fw.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
