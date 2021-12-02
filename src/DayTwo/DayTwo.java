package DayTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DayTwo {

    public static void main(String[] args) {
        List<String[]> temp = new ArrayList<>();
        for (String s : Objects.requireNonNull(DayTwo.fileReader())) {
            temp.add(s.split("\\s+"));
        }
        partOne(temp);
        partTwo(temp);
    }

    protected static List<String> fileReader() {
        try {
            return Files.lines(Paths.get("src/DayTwo/input.txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void partOne(List<String[]> list) {
        int xCor = 0;
        int zCor = 0;
        for (String[] arr : list) {
            switch (arr[0]) {
                case "forward" -> xCor += Integer.parseInt(arr[1]);
                case "up" -> zCor -= Integer.parseInt(arr[1]);
                case "down" -> zCor += Integer.parseInt(arr[1]);
            }
        }
        System.out.println("Solution part one: " + xCor * zCor);
    }

    protected static void partTwo(List<String[]> list) {
        int aim = 0;
        int xCor = 0;
        int zCor = 0;
        for (String[] arr : list) {
            switch (arr[0]) {
                case "forward" -> {
                    xCor += Integer.parseInt(arr[1]);
                    zCor += aim * Integer.parseInt(arr[1]);
                }
                case "up" -> aim -= Integer.parseInt(arr[1]);
                case "down" -> aim += Integer.parseInt(arr[1]);
            }
        }
        System.out.println("Solution part two: " + xCor * zCor);
    }
}