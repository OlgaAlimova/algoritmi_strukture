import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Track1 {
    public static void main(String[] args) {
        // 1
        System.out.println(findSumOfArray (10));
        // 2
        List<Integer> simpleNumber = searchForPrimeNumbers(150);
        for (int m = 0; m < simpleNumber.size(); m++) {
            System.out.println(simpleNumber.get(m));
        }
        System.out.println(EdgeCounter(6));
        // 3
        System.out.println(combinationCount(3, 2));
        // 4
        System.out.println(lineaFindFibonachi(8));
        test();
    }

// чисел от 1 до N. Согласно свойствам линейной сложности,
// количество итераций цикла будет линейно изменяться
// относительно изменения размера N.

    public static int findSumOfArray(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) result +=i;
        return result;
    }

//Написать алгоритм поиска простых чисел (делятся только на себя
// и на 1) в диапазоне от 1 до N. В алгоритме будет использоваться
// вложенный for, что явно говорит о квадратичной сложности,
// на этом стоит акцентировать внимание

    public static List<Integer> searchForPrimeNumbers (int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n ; i++) {
            boolean isSimple = true;
            for (int j = 2; j < i; j++) {
                if(i % j == 0) {
                    isSimple = false;
                    break;
                }
            }
            if(isSimple){
                result.add(i);
            }
        }
        return result;
    }

// Необходимо написать алгоритм поиска всех доступных комбинаций
// (посчитать количество) для количества кубиков K с количеством
// граней N.
// У вас есть 2 варианта на выбор – количество кубиков может быть
// строго ограничено (4 кубика, например), либо их количество
// будет динамическим. Выбор за вами.
// Если вы реализуете простой вариант, обращает внимание, что
// данное решение имеет сложность O(n4), но если количество
// кубиков сделать переменной, то она трансформируется в O(nk),
// что будет представлять собой экспоненциальную сложность.
// Для второго решения очевидно, что его сложность O(nk) с
// самого начала.

    public static int EdgeCounter (int adges){
        int count = 0;
        for (int i = 0; i < adges; i++) {
            for (int j = 0; j < adges; j++) {
                for (int k = 0; k < adges; k++) {
                    for (int l = 0; l < adges; l++) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public static int combinationCount(int count, int faces){
        if (count > 0){
            return recursiveCounter(1, count, faces);
        } else {
            return 0;
        }
    }

    // depth = 1, maxDepth = 3, faces = 2, count = 8
    // 0 (faces^count)
    private static int recursiveCounter(int depth, int maxDepth, int faces){
        int count = 0;
        for (int i = 1; i <= faces ; i++) {
            if (depth == maxDepth){
                count++;
            } else {
                count += recursiveCounter(depth + 1, maxDepth, faces);
            }
        }
        return count;
    }

// Пишем алгоритм поиска нужного числа последовательности Фибоначчи.
// Считаем, что 1 и 2 значения последовательности равны 1.
// Искать будем по формуле On=On-1+On-2 что предполагает использовать
// рекурсивного алгоритма.

    public static int findFibonachi(int n){
        if (n <=2){
            return 1;
        }
        return findFibonachi(n - 1) + findFibonachi(n - 2);
    }

    public static int lineaFindFibonachi(int n){
        int result = 1;
        int previous = 1;
        int prePrevious = 1;
        for (int i = 2; i < n; i++) {
            result = previous + prePrevious;
            prePrevious = previous;
            previous = result;
        }
        return result;
    }

    // вариант Кирилла

    public static void test(){
        for (int i = 10; i <= 50; i = i + 10) {
           Date startDate = new Date();
           lineaFindFibonachi(i);
           Date endDate = new Date();
           long lineDuration = endDate.getTime() - startDate.getTime();
           startDate = new Date();
           findFibonachi(i);
           endDate = new Date();
           long recursiveDuration = endDate.getTime() - startDate.getTime();
           System.out.printf("i: %s, line duration: %s, recursive duration: %s%n", i, lineDuration, recursiveDuration);

        }
    }
}
