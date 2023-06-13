import java.io.*;
import java.util.*;

public class hw3 {
    public static void main(String[] args) {
        System.out.println(">>> Введите пункт: <<<");
        while(true){
            System.out.println("1. Добавить контакт\n" +
                    "2. Посмотреть все контакты\n" +
                    "0. Выход");
            int command = getUserCommand();
            if(command == 0) break;
            else if(command == 1) writeContact();
            else if(command == 2) showContacts();
            else System.out.println("Введите корректное значение!");
        }
        System.out.println(">>> Программа завершена! <<<");
    }

    private static int getUserCommand(){
        Scanner sc = new Scanner(System.in);
        int res;
        while(true) {
            try {
                res = parseInt(sc.nextLine());
                break;
            } catch (MyNumberFormatException e) {
                System.out.println("Вы ввели: " + e.getStr() + " - " + e.getMessage());
            }
        }
        return res;
    }

    private static int parseInt(String s) throws MyNumberFormatException{
        int res;
        try {
            res = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException("Должно быть целое число!", s);
        }
        return res;
    }
    private static void writeContact(){
        System.out.println("Введите данные в формате\n" +
                "\tФамилия Имя Отчество 89001234567\n" +
                "Разделитель пробел, если нет отчества поставить дефис, номер телефона без символов");
        Contact contact = null;
        try{
            contact = getUserContact();
        } catch (MySizeArrayException e){
            System.out.println(e.getMessage() + " размер: " + e.getSize() + ". Вы ввели: " + e.getStr());
        } catch (MyNumberFormatException e){
            System.out.println("Вы ввели: " + e.getStr() + " - " + e.getMessage());
        }

        if(contact != null){
            if(writeContactToFile(contact)) System.out.println("Записано успешно!");;
        }
    }
    private static Contact getUserContact() throws MySizeArrayException, MyNumberFormatException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] dataArray = str.split(" ");
        if(dataArray.length != 4) throw new MySizeArrayException("Введено неверное кол-во данных: ", dataArray.length, str);
        return new Contact(dataArray[0], dataArray[1], dataArray[2], parseInt(dataArray[3]));
    }
    private static boolean writeContactToFile(Contact contact){
        String path = "./";
        String fileExtension = ".mypb.txt";
        try(FileWriter fw = new FileWriter(
                path + contact.getFileName() +fileExtension,true)){
            fw.append(contact + "\n");
        } catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
    private static void showContacts(){
        String path = "./";
        String fileExtension = ".mypb.txt";
        System.out.println(new MyPhoneBook(path, fileExtension));
    }

    static class Contact{
        private String lastname;
        private String firstname;
        private String middlename;
        private int phone;

        public Contact(String lastname, String firstname, String middlename, int phone) {
            this.lastname = lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase();
            this.firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1).toLowerCase();
            this.middlename = middlename.substring(0, 1).toUpperCase() + middlename.substring(1).toLowerCase();
            this.phone = phone;
        }
        public String getFileName(){
            return this.lastname.toLowerCase();
        }

        @Override
        public String toString() {
            return lastname + ' ' +
                   firstname + ' ' +
                   middlename + ' ' +
                   phone;
        }

    }
    static class MyPhoneBook{
        private String path;
        private String fileExtension;
        private List<File> files = new ArrayList<>();
        public MyPhoneBook(String path, String fileExtension){
            this.path = path;
            this.fileExtension = fileExtension;
            loadContacts();
        }
        private void loadContacts(){
            File dir = new File(this.path);
            if(dir.isDirectory()){
                File[] dirFiles = dir.listFiles();
                if(dirFiles != null){
                    for (File file: dirFiles){
                        if (file.getName().endsWith(fileExtension)) this.files.add(file);
                    }
                }
            }
        }
        private void printFile(File file){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                    br.lines().forEach(System.out::println);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        public String getContacts(){
            if (this.files.size() > 0){
                for (File file : files) {
                    printFile(file);
                }
                return "---";
            }
            return "Нет записей!";
        }

        @Override
        public String toString() {
            return getContacts();
        }
    }
}

class MyNumberFormatException extends NumberFormatException{
    private String str;
    public MyNumberFormatException(String message, String str){
        super(message);
        this.str = str;
    }
    public String getStr() {
        return str;
    }
}
class MySizeArrayException extends Exception{
    private int size;
    private String str;
    public MySizeArrayException(String message, int size, String str){
        super(message);
        this.size = size;
        this.str = str;
    }
    public int getSize() {
        return size;
    }
    public String getStr() {
        return str;
    }
}
