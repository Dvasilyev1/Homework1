import java.util.*;
/*
 * Этот класс демонстрирует работу с коллекциями в Java, а также включает алгоритм сортировки QuickSort
 * для списка любых типов, используя Comparator.
 */

public class Homework2 {

    // Главный метод программы, который выполняет демонстрацию работы с коллекциями и сортировкой.
    public static void main(String[] args) {
        myArrayList();
    }

    /*Демонстрация работы с ArrayList. Включает добавление, удаление, сортировку и очистку элементов.
    Также выполняется сортировка коллекции с помощью алгоритма QuickSort.
     */
    public static void myArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Добавляем элемент
        arrayList.add(3);
        System.out.println("После добавления 1 элемента: " + arrayList);

        // Добавляем элемент на определенную позицию
        arrayList.add(0, 33);
        System.out.println("После добавления элемента по индексу: " + arrayList);

        // Получаем элемент
        int firstElement = arrayList.get(0);
        System.out.println("Первый элемент списка: " + firstElement);

        // Удаляем элемент
        arrayList.remove(1);
        System.out.println("После удаления элемента: " + arrayList);

        // Очищаем список
        arrayList.clear();
        System.out.println("После очистки списка: " + arrayList);

        // Добавляем элементы снова
        arrayList.add(2);
        arrayList.add(33);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(3);
        arrayList.add(5);

        // Сортируем список
        Collections.sort(arrayList);
        System.out.println("После сортировки добавления: " + arrayList);

        // Очищаем список для добавления не упорядоченных элементов
        arrayList.clear();

        //Добавляем элементы
        arrayList.add(2);
        arrayList.add(33);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(3);
        arrayList.add(5);


        System.out.println("До сортировки методом quickSort"+"\n"+ arrayList);

        // Передаем параметры в наш метод quickSort
        quickSort(arrayList, 0, arrayList.size() - 1, Comparator.naturalOrder());

        // Передаем отсортированный список в метод, который его выводит
        printList(arrayList);

        // Создаем список типа String
        ArrayList <String> listFruit = new ArrayList<>();

        // Добавляем элементы
        listFruit.add("Banana");
        listFruit.add("Kiwi");
        listFruit.add("Apple");
        listFruit.add("Blueberry");
        listFruit.add("Orange");

        System.out.println("До сортировки методом  quickSort" +"\n"+ listFruit);
        // Передаем параметры в наш метод quickSort
        quickSort(listFruit, 0, listFruit.size() - 1, Comparator.naturalOrder());

        // Передаем отсортированный список в метод, который его выводит
        printList(listFruit);
    }

    // Создаем метод quickSort, который принимает параметрами: List любого типа. левую границу List, правую границу List и компаратор
    public static <T> void quickSort(List<T> list, int leftIndex, int rightIndex, Comparator<T> comparator) {

        // Если левое значение по индексу меньше правого рекурсивно вызываем quickSort
        if (leftIndex <= rightIndex || list.isEmpty()) {

            // Находим середину list'a и указываем опорный элемент
            int pivotIndex = (leftIndex + rightIndex) / 2;
            T pivot = list.get(pivotIndex);

            // Создаем бегунки и присваиваем им начальные положения в левой и правой границе
            int leftMarker = leftIndex;
            int rightMarker = rightIndex;

            // Создаем цикл в который попалаем пока маркеры не встретятся
            while (leftMarker <= rightMarker) {
                // Сравниваем значения с pivot, если значение больше останавливаемся на нем
                while (comparator.compare(list.get(leftMarker), pivot) < 0) {
                    leftMarker++;
                }
                // Сравниваем значения с pivot, если значение меньше останавливаемся на нем
                while (comparator.compare(list.get(rightMarker), pivot) > 0) {
                    rightMarker--;
                }
                // Если значение в левом маркере больше значения в правом маркере, меняем их местами
                if (leftMarker <= rightMarker) {
                    T swap = list.get(leftMarker);
                    list.set(leftMarker, list.get(rightMarker));
                    list.set(rightMarker, swap);

                // Двигаем маркеры (довольные собой шагаем дальше)
                    leftMarker++;
                    rightMarker--;
                }
            }
            // Рекурсивно вызываем quickSort для левой части списка до pivot
            quickSort(list, leftIndex, rightMarker, comparator);
            // Рекурсивно вызываем quickSort для правой части списка после pivot
            quickSort(list, leftMarker, rightIndex, comparator);
        }
    }
    // Метод в котором перебираем список и выводим его в консоль
    private static <T> void printList(List<T> list) {

        System.out.println("После сортировки методом quickSort ");

        for (T element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}    


