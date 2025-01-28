import java.util.*;

import static com.sun.tools.javac.util.ArrayUtils.ensureCapacity;
/*
 * Этот класс демонстрирует работу с коллекциями в Java, а также включает алгоритм сортировки QuickSort
 * для списка любых типов, используя Comparator.
 */

public class Homework2 {

    // Главный метод программы, который выполняет демонстрацию работы с коллекциями и сортировкой.
    public static void main(String[] args) {
        MyArrayList<Object> list = new MyArrayList<>();

        System.out.println("Добавляем элементы в список методом add:");
        list.add(5); // индекс 0
        list.add(2); // индекс 1

        list.getList(list);

        System.out.println("Добавляем элементы в список по индексу:");
        list.add(2,3);
        list.add(3,1);
        list.add(4,2);
        list.add(5,4);
        list.getList(list);

        System.out.println("Список после удаления элемента по индексу:");
        list.remove(4);

        list.getList(list);

        System.out.println("Отсортированный список:");

        //Создаем компоратор для сравнения примитивных типов
        Comparator<Object> comparator = (o1, o2) -> {
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable<Object>) o1).compareTo(o2);
            }
            throw new IllegalArgumentException("Objects are not comparable");
        };

        list.sort(comparator);
        list.getList(list);

        // Очищаем список
        list.clear();
        System.out.println("Список после очистки методом remove");
        list.getList(list);

        // Добавляем элементы в список для сортировки методом quicksort
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(5);
        System.out.println("Список до сортировки методом quicksort:");
        list.getList(list);

        System.out.println("Список отсортированный методом quicksort:");
        quickSort(list,0, list.size-1,comparator);
        list.getList(list);
        list.clear();

        System.out.println("Неотсортированный список типа String");
        list.add("Banana");
        list.add("Apple");
        list.add("Strowberry");
        list.add("Kiwi");
        list.add("Orange");
        list.getList(list);

        System.out.println("Отсортированный список String");
        quickSort(list,0, list.size()-1,comparator);
        list.getList(list);
    }


    /*Демонстрация работы с ArrayList. Включает добавление, удаление, сортировку и очистку элементов.
    Также выполняется сортировка коллекции с помощью алгоритма QuickSort.
     */
    public static class MyArrayList<T> {
        private Object[] elements;
        private int size;

        //Создаем конструктор класса MyArrayList
        public MyArrayList(){
            elements = new Object[5];
            size = 0;
        }

        //Метод add позваоляет добавлять элемент в конец списка
        public void add(T element){
            ensureCapacity();
            elements[size++] = element;
        }

        //Метод add позваоляет добавлять элемент по индексу
        public void add(int index, T element) {
            if (index < 0 ) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
            while( index >= elements.length){
                ensureCapacity();
            }
            if(index >= size){
                Arrays.fill(elements, size, index,null);
                size = index + 1;
            }
            elements[index] = element;
        }

        //Метод get позваоляет получить элемент по индексу
        public T get (int index){
            if(index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Invalid index");
            return (T) elements[index];
        }

        //Метод size возвращает размер списка
        public int size(){
            return size;
        }

        //Внутренний метод ensureCapacity позволяет увеличить список, если мы достигли лимита
        private void ensureCapacity() {
            elements = java.util.Arrays.copyOf(elements,elements.length * 2);
        }

        //Внутренний метод getList выводит переданный список
        private static void getList(MyArrayList list){
            for (int i = 0; i < list.size; i++) {
                System.out.println(list.get(i) + " ");
            }
        }

        //Метод remove удаляет элемент по индексу, сдвигает элементы после удаленного влева и очищает
        // ссылку крайнего правого (пустого) элемента
        public void remove(int index){
            if(index < 0 || index>= size)
                throw new IndexOutOfBoundsException("Invalid index");
            for (int i = index; i < size - 1 ; i++) {
                elements[i] = elements[i+1];
                elements[size-1]= null;
            }
            size--;
        }

        //Метод clear очищает ссылки элементов и уменьшает размер до нуля
        public void clear(){
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
            size = 0;
        }

        //Метод sort сортирует элементы в массиве elements с использованием переданного компаратора
        public void sort(Comparator<T> comporator){
            if (size <= 1){
                return;
            }
            T[] typedElements = (T[]) Arrays.copyOf(elements,size);
            Arrays.sort(typedElements,comporator);

            for (int i = 0; i < size; i++) {
                elements[i] = typedElements[i];
            }
        }
    }

    // Создаем метод quickSort, который принимает параметрами: List любого типа. левую границу List, правую границу List и компаратор
    public static <T> void quickSort(MyArrayList<T> list, int leftIndex, int rightIndex, Comparator<T> comparator) {

        // Если левое значение по индексу меньше правого рекурсивно вызываем quickSort
        if (leftIndex <= rightIndex) {

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
                    list.add(leftMarker, list.get(rightMarker));
                    list.add(rightMarker, swap);

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

