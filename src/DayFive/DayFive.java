package DayFive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DayFive {
    private static final int[][] corrSystem = new int[1000][1000];

    public static void main(String[] args) {
        List<int[]> temp = new ArrayList<>();
        for (String s : Objects.requireNonNull(fileReader())) {
            temp.add(Arrays.stream(s.split("\\D+"))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        }
        partOne(temp);
    }

    protected static List<String> fileReader() {
        try {
            return Files.lines(Paths.get("src/DayFive/input.txt")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void partOne(List<int[]> list) {
        for (int[] arr : list) {
            int x1 = arr[0];
            int y1 = arr[1];
            int x2 = arr[2];
            int y2 = arr[3];
            if (x1 == x2 || y1 == y2) {
                if (x1 == x2) {
                    for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) corrSystem[x1][i]++;
                } else {
                    for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) corrSystem[i][y1]++;
                }
            } else partTwo(x1, x2, y1, y2); //diagonal lines
        }
        System.out.println(result());
    }

    protected static int result() {
        int sum = 0;
        for (int i = 0; i < corrSystem.length; i++) {
            for (int j = 0; j < corrSystem.length; j++) {
                if (corrSystem[i][j] > 1) sum++;
            }
        }
        return sum;
    }

    protected static void partTwo(int x1, int x2, int y1, int y2) {
        int count = 0;
        if (x2 > x1) {
            if (y2 > y1) { // down right
                for (int i = x1; i <= x2; i++) corrSystem[i][y1 + count++]++;
            } else { // up right
                for (int i = x1; i <= x2; i++) corrSystem[i][y1 + count--]++;
            }
        } else { // down left
            if (y2 > y1) {
                for (int i = x2; i <= x1; i++) corrSystem[i][y2 + count--]++;
            } else { //up left
                for (int i = x2; i <= x1; i++) corrSystem[i][y2 + count++]++;
            }
        }
    }
}