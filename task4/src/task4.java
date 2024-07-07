import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) throws IOException {
        try (Scanner in = new Scanner(System.in)){
            System.out.println("Укажите путь к файлу со значениями массива - ");
            String path = in.next();

            List<String> list = readFile(path);
            List<Integer> arr = new ArrayList<>();
            int max = 0;
            for (String s : list) {
                int i = Integer.parseInt(s);
                arr.add(Integer.parseInt(s));
                if (Math.abs(i) > max){
                    max = i;
                }
            }

            int count = findMinMoves(arr, max);
            System.out.println(count);
        }

    }

    public static int findMinMoves(List<Integer> nums, int max) {
        int minMoves = Integer.MAX_VALUE;

        for (int target = 0; target <= max; target++) {
            int moves = 0;
            for (int num : nums) {
                moves += Math.abs(num - target);
            }
            if (moves < minMoves) {
                minMoves = moves;
            }
        }
        return minMoves;
    }

    private static List<String> readFile(String pathFile) throws IOException {
        List<String> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
        String line = bufferedReader.readLine();
        while (line != null){
            list.add(line);
            line = bufferedReader.readLine();
        }
        return list;
    }
}