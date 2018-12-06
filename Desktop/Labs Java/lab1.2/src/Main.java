import java.util.Random;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.Z;

public class Main {

       public static void main(String[] args) {

           System.out.print("\n----Kuznetsova----\n\n");
           Scanner in = new Scanner(System.in); //обьявление сканера для считывания
            String text = processInput(in); //метод для чтения ввода
            int result=checkSum(text);
            System.out.println("The sum of the digits in the string ["+text+"] is equal to: "+result);

        }
        public static int checkSum(String text){
        int sum=0;//результат
           text = text.replaceAll("[^0-9]", "");//удаляет все буквы
            for (int i = 0; i < text.length(); i++) {//цикл по длине текста
                sum+=Integer.parseInt(text.charAt(i)+ "");//чарЭт(н), н - на какой позиции взять цифру
            }
            return sum;
       }
    private static String processInput(Scanner keyboard) { //для того, чтобы каждая предыдущая строка не была пустой, для переноса на новую
        System.out.println("Input the text: ");
        StringBuilder input = new StringBuilder();
        String line;
        while (keyboard.hasNextLine()) {
            line = keyboard.nextLine();
            if (line.isEmpty()) {
                break;
            }
            input.append(line).append("\n");
        }
        return input.toString();
    }
    }



