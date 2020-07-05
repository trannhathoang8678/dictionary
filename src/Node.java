import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Node {
    private String key;
    private LinkedList<String> words = new LinkedList<>();
    //initialization

    public Node(String key, LinkedList<String> words) {
        this.key = key;
        this.words = words;
    }

    public Node() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LinkedList<String> getWords() {
        return words;
    }

    public void add(String word) {
        words.add(word);
    }

    public void saveEV() {
        Collections.sort(words);
        String file = new File("data/English-Vietnamese").getAbsolutePath();
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fileWriter)) {
            pw.append(key + " :");
            String lastWord = words.getLast();
            for (String s : words)
                if (s != lastWord)
                    pw.append(" " + s + ",");
                else
                    pw.append(" " + s);
            pw.append('\n');
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void saveVE() {
        Collections.sort(words);
        String file = new File("data/Vietnamese-English").getAbsolutePath();
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fileWriter)) {

            pw.append(key + " :");
            String lastWord = words.getLast();
            for (String s : words)
                if (s != lastWord)
                    pw.append(" " + s + ",");
                else
                    pw.append(" " + s);
            pw.append('\n');
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
