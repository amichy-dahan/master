import java.util.Scanner;

public class Nmain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int per;
        float price;

        per = scanner.nextInt();
        price = scanner.nextFloat();

        float newPrice = (per/100) * price;

        System.out.println(newPrice);


    }
}
