import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task2 {
    public static void main(String[] args) throws IOException {
        int[] circleParams = getCircleParameters("task2/src/file1");
        List<int[]> listPoints = getCoordinatesPoints("task2/src/file2");

        for (int[] listPoint : listPoints) {
            int lengthX = Math.abs(circleParams[0] - listPoint[0]);
            int lengthY = Math.abs(circleParams[1] - listPoint[1]);
            double squareHypotenuse = Math.pow(lengthX, 2) + Math.pow(lengthY, 2);
            if (Math.pow(circleParams[2], 2) > squareHypotenuse) {
                System.out.println(1);
            } else if (Math.pow(circleParams[2], 2) == squareHypotenuse) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
    }

    private static int[] getCircleParameters(String pathFile) throws IOException{

        List<String> list = readFile(pathFile);
        int[] array = new int[3];

        array[0] = Integer.parseInt(String.valueOf(list.get(0).charAt(0)));
        array[1] = Integer.parseInt(String.valueOf(list.get(0).charAt(1)));
        array[2] = Integer.parseInt(String.valueOf(list.get(1).charAt(0)));

        return array;
    }

    private static List<int[]> getCoordinatesPoints(String pathFile) throws IOException{
        List<int[]> listPoints = new ArrayList<>();
        List<String> list = readFile(pathFile);

        for (String s : list) {
            int x = Integer.parseInt(String.valueOf(s.charAt(0)));
            int y = Integer.parseInt(String.valueOf(s.charAt(1)));
            listPoints.add(new int[]{x, y});
        }

        return listPoints;
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