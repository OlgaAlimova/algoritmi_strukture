import java.util.Arrays;
import java.util.Random;
public class Track2 {
    public static void bubbleSort(int[] arr){
        for (int j = arr.length; j > 1; j--) {
            for (int i = 0; i < j-1; i++) {
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }
//    public static void main(String[] args) {
//        int[] arr = {7, 3, 4, 8, 1};
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
//    }

    public static void quickSort(int[] arr, int beginIndex, int endIndex){
        int pivot = arr[beginIndex + (endIndex - beginIndex)/2];
        int leftPosition = beginIndex;
        int rightPosition = endIndex;
        while (leftPosition <= rightPosition){
            while (arr[leftPosition] < pivot) leftPosition++;
            while (arr[rightPosition] > pivot) rightPosition--;
            if (leftPosition <= rightPosition){
                if (leftPosition < rightPosition){
                    int temp = arr[leftPosition];
                    arr[leftPosition] = arr[rightPosition];
                    arr[rightPosition] = temp;
                }
                leftPosition++;
                rightPosition--;
            }
        }
        if (leftPosition < endIndex)
            quickSort(arr, leftPosition, endIndex);
        if (rightPosition > beginIndex)
            quickSort(arr, beginIndex, rightPosition);
    }
//    public static void main(String[] args) {
//        int[] arr = {120, 7, 3, 130, 8, 1};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//    }

//    public static void main(String[] args){
//        Random random = new Random();
//        for (int i = 10_000; i <= 100_000; i+=10_000) {
//            int[] arr = new int[i];
//            for (int j = 0; j < arr.length; j++) {
//                arr[j] = random.nextInt();
//            }
//            long bubbleSortTime = System.currentTimeMillis();
//            bubbleSort(arr);
//            bubbleSortTime = System.currentTimeMillis() - bubbleSortTime;
//            for (int j = 0; j < arr.length; j++) {
//                arr[j] = random.nextInt();
//            }
//            long quickSortTime = System.currentTimeMillis();
//            quickSort(arr, 0, arr.length - 1);
//            quickSortTime = System.currentTimeMillis() - quickSortTime;
//            System.out.printf("Для %d: \n\tбыстрая сортировка: %d, \n\tпузырьковая сортировка: %d\n", i, quickSortTime, bubbleSortTime);
//        }
//
//    }

//    public static void main(String[] args){
//        int[] arr = {1, 2, 3, 4, 10, 20, 30};
//        System.out.println(binarySearch(arr, 10));
//    }
    public static int binarySearch(int[] arr, int item) {
        return binarySearch(arr, 0, arr.length - 1, item);
    }
    public static int binarySearch(int[] arr, int beginIndex, int endIndex, int item){
        if(beginIndex <= endIndex){
            int middle = beginIndex + (endIndex - beginIndex)/2;
            if (arr[middle] > item) return binarySearch(arr,beginIndex, middle - 1,item);
            else if (arr[middle] < item) return binarySearch(arr, middle - 1, endIndex, item);
            else return middle;
        }
        else return -1;
    }

    // Реализация пирамидальной сортировки на Java
    public void sort(int arr[])
        {
            int n = arr.length;

            // Построение кучи (перегруппируем массив)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // Один за другим извлекаем элементы из кучи
            for (int i=n-1; i>=0; i--)
            {
                // Перемещаем текущий корень в конец
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // Вызываем процедуру heapify на уменьшенной куче
                heapify(arr, i, 0);
            }
        }

        // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является
// индексом в arr[]. n - размер кучи
        public void heapify(int arr[], int n, int i)
        {
            int largest = i; // Инициализируем наибольший элемент как корень
            int l = 2*i + 1; // левый = 2*i + 1
            int r = 2*i + 2; // правый = 2*i + 2

            // Если левый дочерний элемент больше корня
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
            if (r < n && arr[r] > arr[largest])
                largest = r;
            // Если самый большой элемент не корень
            if (largest != i)
            {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
                heapify(arr, n, largest);
            }
        }

        /* Вспомогательная функция для вывода на экран массива размера n */
        static void printArray(int arr[])
        {
            int n = arr.length;
            for (int i=0; i<n; ++i)
                System.out.print(arr[i]+" ");
            System.out.println();
        }

        // Управляющая программа
        public static void main(String args[])
        {
            int arr[] = {12, 11, 13, 5, 6, 7};
            int n = arr.length;

            Track2 ob = new Track2();
            ob.sort(arr);

            System.out.println("Sorted array is");
            printArray(arr);
        }

}
