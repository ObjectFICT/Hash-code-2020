import java.io.*;
import java.util.*;

public class Pizza {
    String fileName;
    int sizePizza = 0;
    int countPizzaType = 0;
    ArrayList<Integer> pizzaType = new ArrayList<>();
    ArrayList<Integer> pizzaOrder = new ArrayList<>();
    ArrayList<String> files = new ArrayList<>();

    Pizza(String file) {
        for (String i : file.split(" ")) {
            files.add(i);
        }

        for (String i : files) {
            readInFile(i);
            searchOptimalOrder();
            writeOutFile();
            pizzaType.clear();
            pizzaOrder.clear();
        }
    }

    public void readInFile(String fileName) {
        this.fileName = fileName;

        try (FileReader fileReader = new FileReader("C:\\Users\\Mi\\Desktop\\Hash code 2020\\Data_in\\" + fileName)) {
            Scanner scanner = new Scanner(fileReader);

            this.sizePizza = scanner.nextInt();
            this.countPizzaType = scanner.nextInt();

            while (scanner.hasNextInt()) {
                pizzaType.add(scanner.nextInt());
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }

    }


    public void searchOptimalOrder() {
        int memory_sizePizza = sizePizza;

        for (int i = countPizzaType - 1; i >= 0; --i) {
            if (pizzaType.get(i) <= memory_sizePizza) {
                memory_sizePizza -= pizzaType.get(i);
                pizzaOrder.add(i);
            }
        }

        Collections.sort(pizzaOrder);
    }

    public void writeOutFile() {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\Mi\\Desktop\\Hash code 2020\\Data_out\\" + fileName.charAt(0) + ".out", false)) {
            fileWriter.write(pizzaOrder.size() + "\n");

            for (int i : pizzaOrder) {
                fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

}
