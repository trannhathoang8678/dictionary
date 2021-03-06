import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Dictionary {
    LinkedList<Node> listEV = new LinkedList<>();
    LinkedList<Node> listVE = new LinkedList<>();
    //initialization

    public Dictionary() {
        String file1 = new File("data/English-Vietnamese").getAbsolutePath();
        String file2 = new File("data/Vietnamese-English").getAbsolutePath();
        try (FileReader fileReader1 = new FileReader(file1);
             BufferedReader bf1 = new BufferedReader(fileReader1);
             FileReader fileReader2 = new FileReader(file2);
             BufferedReader bf2 = new BufferedReader(fileReader2)
        ) {
            String s;
            Node node = new Node();
            while ((s = bf1.readLine()) != null) {
                String[] words1 = s.split(":");
                node = new Node();
                node.setKey(words1[0].trim());
                words1[1] = words1[1].trim();
                String[] words = words1[1].split(",");
                for (int i = 0; i < words.length; i++) {
                    node.add(words[i].trim());
                }
                listEV.add(node);
            }
            while ((s = bf2.readLine()) != null) {
                String[] words1 = s.split(":");
                node = new Node();
                node.setKey(words1[0].trim());
                words1[1] = words1[1].trim();
                String[] words = words1[1].split(",");
                for (int i = 0; i < words.length; i++) {
                    node.add(words[i].trim());
                }
                listVE.add(node);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void addEV(String key, String value) {
        // check whether this word have already existed or not
        boolean exist = false, check = false;
        int cnt = -1;
        for (Node node : listEV) {
            cnt++;
            if (node.getKey().equalsIgnoreCase(key)) {
                exist = true;
                for (String word : node.getWords())
                    if (word.equalsIgnoreCase(value)) {
                        check = true;
                        break;
                    }
                if (!check) {
                    Node newNode = listEV.get(cnt);
                    newNode.add(value);
                    listEV.set(cnt, newNode);
                }
                break;
            }

        }
        if (!exist) {

            Node newNode = new Node();
            newNode.setKey(key);
            newNode.add(value);
            listEV.add(newNode);
        }
    }

    public void addVE(String key, String value) {
        // check whether this word have already existed or not
        boolean exist = false, check = false;
        int cnt = -1;
        for (Node node : listVE) {
            cnt++;
            if (node.getKey().equalsIgnoreCase(key)) {
                exist = true;
                for (String word : node.getWords())
                    if (word.equalsIgnoreCase(value)) {
                        check = true;
                        break;
                    }
                if (!check) {
                    Node newNode = listVE.get(cnt);
                    newNode.add(value);
                    listVE.set(cnt, newNode);

                }
                break;
            }

        }
        if (!exist) {
            Node newNode = new Node();
            newNode.setKey(key);
            newNode.add(value);
            listVE.add(newNode);
        }
    }

    public void save() {
        String file1 = new File("data/English-Vietnamese").getAbsolutePath();
        String file2 = new File("data/Vietnamese-English").getAbsolutePath();
        try (FileWriter fileWriter = new FileWriter(file1, false);
             FileWriter fileWriter1 = new FileWriter(file2, false)) {

        } catch (IOException e) {
            System.out.println(e);
        }
        Collections.sort(listEV, new Comparator<Node>() {
                    @Override
                    public int compare(Node node, Node t1) {
                        return node.getKey().compareTo(t1.getKey());
                    }
                }
        );
        Collections.sort(listVE, new Comparator<Node>() {
                    @Override
                    public int compare(Node node, Node t1) {
                        return node.getKey().compareTo(t1.getKey());
                    }
                }
        );
        for (Node node : listEV)
            node.saveEV();
        for (Node node : listVE)
            node.saveVE();
    }

    // print dictionaries
    public void write() {
        System.out.println("English - Vietnamese dictionary: ");
        for (Node node : listEV) {
            System.out.print(node.getKey() + " :");
            for (String word : node.getWords())
                System.out.print(" " + word + ",");
            System.out.print('\b');
            System.out.println();
        }
        System.out.println("Vietnamese - English dictionary: ");
        for (Node node : listVE) {
            System.out.print(node.getKey() + " :");
            for (String word : node.getWords())
                System.out.print(" " + word + ",");
            System.out.print('\b');
            System.out.println();
        }
    }
}

