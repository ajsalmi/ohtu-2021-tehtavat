
import java.util.Scanner;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args){
        //System.out.println("Hello gradle!");
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(3);
        System.out.println("Anna luku: ");
        int luku = scanner.nextInt();
        System.out.println("Luku kertaa kolme on "+kolme.multipliedBy(luku));
        scanner.close();
    }
}
