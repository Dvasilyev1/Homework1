import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Homework {
    public static void main(String[] args) {

        //1 метод
        String string = "I love Java";
        turnString(string);

        //2 метод
        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        getDistinctNumbers(ints);

        //3 метод
        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        System.out.println(findSecondMaxElement(arr));

        //4 метод
        String _string = "Hello world";
        String str = "    fly me    to the moon    ";
        System.out.println(lengthOfLastWord(_string));
        System.out.println(lengthOfLastWord(str));

        //5 метод
        String str1 = "abc";
        String str2 = "112233";
        String str3 = "aba";
        String str4 = "112211";
        System.out.println(isPalindrome(str1));
        System.out.println(isPalindrome(str2));
        System.out.println(isPalindrome(str3));
        System.out.println(isPalindrome(str4));

    }

    // Перевернуть строку и вывести на консоль
    public static void turnString(String string) {
        StringBuilder reverseStr = new StringBuilder(string); // Создаем объект типа StringBuilder
        System.out.println(reverseStr.reverse());             // Используем метод StringBuilder
    }


    // Удалить дубликаты из массива и вывести в консоль
    public static void getDistinctNumbers(int[] ints) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(); // Создаем объект типа LinkedHashSet
        for (int i = 0; i < ints.length; i++) {
            linkedHashSet.add(ints[i]);                               // Перебираем массив, который передаем в метод и помещаем его в хэшсет
        }
        System.out.println(linkedHashSet);
    }


        // Дан массив, заполненный уникальными значениями типа int.
        // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
        public static Integer findSecondMaxElement(int[] arr) {
            Arrays.sort(arr);                                   // сортируем массив
            int minMax = arr[arr.length-2];                     // выводим препоследний элемент
            return minMax;
        }


        // Найти длину последнего слова в строке. В строке только буквы и пробелы.
        public static Integer lengthOfLastWord(String string) {
            String[] splitStr = string.split(" "); // Разбиваем массив по регистру " " и помещаем в массив
            int index  = splitStr.length - 1;            // индекс последнего слова
            String lastWord = splitStr[index];
            return lastWord.length();
        }

        // Определить, что строка является палиндромом
        // Сложность по памяти O(1), не создавать новые String, StringBuilder
        public static boolean isPalindrome(String string) {

        //если количество букв четное, попадаем в этот if
            if (string.length() % 2 == 0) {
                for (int i = 0, n = 1; i < string.length() / 2; i++, n++) {
                    char initialLetter = string.charAt(i);
                    int indexLast = string.length() - n;
                    char lastLetter = string.charAt(indexLast);
                    if (initialLetter == lastLetter)
                        return true;
                    else
                        return false;
                }
                //если количество букв нечетное, попадаем в else
            } else {
                for (int i = 0, n = 1; i < (string.length() - 1) / 2; i++, n++) {
                    char initialLetter = string.charAt(i);
                    int indexLast = string.length() - n;
                    char lastLetter = string.charAt(indexLast);
                    if (initialLetter == lastLetter)
                       return true;
                    else
                        return false;
                }
            }
            return false;
        }
}