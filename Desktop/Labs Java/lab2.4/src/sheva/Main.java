package sheva;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("Список: ");
        System.out.println(list);
        System.out.println("Введите число длины смещения: ");
        int length = Integer.parseInt(scanner.nextLine());
        Collections.rotate(list, length);
        System.out.println("Список после смещения: ");
        System.out.println(list);
        scanner.close();
    }
}
