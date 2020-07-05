import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String key, value;
        Dictionary dictionary = new Dictionary();
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of vocubalaries: ");
        int number = sc.nextInt();
        System.out.println("Write 1 for Endlish - Vietnamese and 0 for Vietnamese - English vocabulary");
        for (int i = 0; i < number; i++) {
            System.out.println("Type of vocabulary (Number): ");
            int type = sc.nextInt();
            System.out.print("Word: ");
            key = sc.nextLine();
            key = sc.nextLine();
            key = key.toLowerCase();
            key = key.trim();
            System.out.println("Write number of meanings: ");
            int numberMeanings = sc.nextInt();
            value = sc.nextLine();
            for (int j = 0; j < numberMeanings; j++) {
                System.out.print("Meaning " + (j + 1) + ": ");

                value = sc.nextLine();
                value = value.toLowerCase();
                value = value.trim();
                if (type == 1) {
                    dictionary.addEV(key, value);
                    dictionary.addVE(value, key);
                } else {
                    dictionary.addVE(key, value);
                    dictionary.addEV(value, key);
                }
            }
        }
        dictionary.save();
        dictionary.write();
    }
}
