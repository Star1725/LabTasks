import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        int n;
        int m;

        try (Scanner in = new Scanner(System.in)){
            System.out.println("Введите значение n: ");
            n = in.nextInt();
            System.out.println("Введите значение m: ");
            m = in.nextInt();
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        List<Integer> path = new ArrayList<>();
        path.add(1);
        int current = 0;

        for (int i = 0; i < n; i++) {
            int buff = (current + m - 1);
            int next = buff % n;
            if (next == 0) break;
            path.add(array[next]);
            array[next] = array[current];
            current = next;
        }
        System.out.println("Путь: " + path);

    }
}