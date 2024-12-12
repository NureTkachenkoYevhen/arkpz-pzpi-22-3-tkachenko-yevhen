import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class JavaConventionsPract1 {
    // Іменування
    // Поганий приклад
    public class processDATA {
        public int NUMBER;

        public void doIt(int nUMBER) {
            this.NUMBER = nUMBER;
            System.out.println("Processing number: " + NUMBER);
        }
    }
    // Гарний приклад
public class DataProcessor {
    private int number;

    public void process(int inputNumber) {
        this.number = inputNumber;
        System.out.println("Processing number: " + number);
        }
    }

    //Обробка виключень
    // Поганий приклад
    public void readFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            System.out.println(bufferedReader.readLine());
        } catch (Exception e) {
            System.out.println("Error occurred.");
        }
    }
    // Гарний приклад
    public void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to read the file: " + fileName);
        }
    }

    //Обробка Null
    // Поганий приклад
    public void getUserName(User user) {
        if (user != null && user.getName() != null) {
            System.out.println(user.getName());
        } else {
            System.out.println("Unknown");
        }
    }
    // Гарний приклад
    public void printUserName(User user) {
        Optional<String> userName = Optional.ofNullable(user)
                .map(User::getName);

        userName.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Unknown")
        );
    }

    //Перевантаження методів та Varargs
    // Поганий приклад
    public void processOrder(String orderId, String... items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
        System.out.println("Processing order: " + orderId);
        for (String item : items) {
            System.out.println(" - Item: " + item);
        }
    }
    // Гарний приклад
    public void processOrder(String orderId, List<String> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
        System.out.println("Processing order: " + orderId);
        for (String item : items) {
            System.out.println(" - Item: " + item);
        }
    }

    //Рефакторинг за допомогою потоків і лямбд
    // Поганий приклад
    public double calculateTotalPrice(List<Order> orders) {
        double total = 0;
        for (Order order : orders) {
            if (order.getStatus().equals("COMPLETED")) {
                total += order.getPrice();
            }
        }
        return total;
    }
    // Гарний приклад
    public double calculateTotalPrice(List<Order> orders) {
        return orders.stream()
            .filter(order -> "COMPLETED".equals(order.getStatus()))
            .mapToDouble(Order::getPrice)
            .sum();
    }


    //Примітивні типи та класи обгорток
    // Поганий приклад
    public Integer calculateAge(Integer birthYear) {
        Integer currentYear = 2024;
        if (birthYear == null) {
            throw new IllegalArgumentException("Birth year must not be null");
        }
        return currentYear - birthYear;
    }
    // Гарний приклад
    public int calculateAge(int birthYear) {
        int currentYear = 2024;
        if (birthYear <= 0 || birthYear > currentYear) {
            throw new IllegalArgumentException("Invalid birth year");
        }
        return currentYear - birthYear;
    }
}
