import java.io.FileNotFoundException;
import java.util.Scanner;

public class hw2 {
    /*
       1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
          и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
          вместо этого, необходимо повторно запросить у пользователя ввод данных.

       2. Если необходимо, исправьте данный код
          (задание 2 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

       3. Дан следующий код, исправьте его там, где требуется
          (задание 3 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

       4. Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
          Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
    */
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
    }



    private static void task1() {
        System.out.println(">>>> Задание 1:");
        System.out.println("Вы ввели: " + getNumFromUser());
    }
    private static double getNumFromUser(){
        Scanner scanner = new Scanner(System.in);
        double res;
        System.out.print("Введите вещественное число через точку: ");
        while(true) {
            try {
                res = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное значение!");
            }
        }
        return res;
    }

    private static void task2(){
        System.out.println(">>>> Задание 2:");
        int[] intArray = new int[10]; // не был объявлен массив
        try {
            int d = 0; // деление на 0 ловит, если поменять на 1, то отрабатывает ожидаемо
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }

    private static void task3() {
        System.out.println(">>>> Задание 3:");
        // переместил catch (Throwable ex) вниз, чтобы могли отработать исключения потомки, поменял на Exception
        // так как мы же не можем обрабатывать Errors

        // printSum кидает тип исключения FileNotFoundException, который там невозможен, убрал

        // NullPointerException я тоже не понял как может возникнуть, поменял на ArithmeticException

        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = {1, 2};
            abc[1] = 9;
        } catch (ArithmeticException ex) {
            System.out.println("Деление на 0!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Exception ex) {
            System.out.println("Что-то пошло не так...");
        }
    }
    private static void printSum(Integer a, Integer b){
        System.out.println(a + b);
    }

    private static void task4() {
        System.out.println(">>>> Задание 4:");
        Scanner scanner = new Scanner(System.in);
        String res;
        while (true) {
            try {
                System.out.print("Введите строку: ");
                res = scanner.nextLine();
                if (res.length() > 0) break;
                else throw new MyEmptyStringException("Строка должна содержать хотя бы один символ!");
            } catch (MyEmptyStringException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Вы ввели: " + res);
    }


}

class MyEmptyStringException extends Exception{
    public MyEmptyStringException(String message){
        super(message);
        }
    }

