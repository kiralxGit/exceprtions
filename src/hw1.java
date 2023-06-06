import java.util.Arrays;
import java.util.Random;

public class hw1 {
    public static void main(String[] args) {

        System.out.println(division(10, 1)); // ожидаем результат если второе не 0
        System.out.println(intNotNull(1)); // ожидаем Not null если значение не null
        System.out.println(checkAge(18)); // ожидаем true если 18 и выше


        System.out.println("---");


        int[] arr1 = getIntArray(16,-5, 10);
        int[] arr2 = getIntArray(16,1, 10);

        System.out.println("Массив 1: " + Arrays.toString(arr1));
        System.out.println("Массив 2: " + Arrays.toString(arr2));
        System.out.println("Массив -: " + Arrays.toString(diffArrays(arr1, arr2)));


        System.out.println("---");

        System.out.println("Массив /: " + Arrays.toString(divArrays(arr1, arr2)));


    }
    public static double division(int a, int b){
        if(b == 0) throw new RuntimeException("Деление на ноль!");
        return a / b;
    }

    public static String intNotNull(Integer a){
        if(a == null) throw new RuntimeException("Null вместо значения!");
        return "Not null";
    }
    public static boolean checkAge(int a){
        if(a < 18) throw new RuntimeException("Рано ещё!");
        return true;
    }

    public static int[] getIntArray(int length, int min, int max){
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Random().nextInt((max - min) + 1) + min;
        }
        return arr;
    }
    public static boolean checkArraysSize(int[] a, int[] b){
        if(a.length == b.length) return true;
        return false;
    }
    public static int[] diffArrays(int[] a, int[] b){
        if(!checkArraysSize(a, b)) throw new RuntimeException("Длины массивов не равны!");
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if(a[i] > b[i]) res[i] = a[i] - b[i]; // вместо Math.abs()
            else res[i] = b[i] - a[i];
        }
        return res;
    }
    public static double[] divArrays(int[] a, int[] b){
        if(!checkArraysSize(a, b)) throw new RuntimeException("Длины массивов не равны!");
        double[] res = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            if(b[i] == 0) throw new RuntimeException("Деление на 0");
            res[i] = roundDivisionDouble(a[i], b[i], 4);
        }
        return res;
    }
    public static double roundDivisionDouble(int a, int b, int digits){ // округляем без math
        double div = a * 1.0 / b;
        div *= 10 * digits;
        int res = (int) div;
        return (double) res / (10 * digits);

    }

}
