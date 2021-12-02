package DayOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Depth {
    public static void main(String[] args) {
        partOne(Objects.requireNonNull(fileReader()));
        partTwo(Objects.requireNonNull(fileReader()));
    }

    protected static List<Integer> fileReader() {
        try {
            return Files.lines(Paths.get("src/DayOne/input.txt"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void partOne(List<Integer> list) {
        int count = 0;
        for (int i = 0; i < list.size()-1;i++) {
            int temp = list.get(i+1);
            if(list.get(i) < temp) count++;
        }
        System.out.println("Puzzel 1: " + count);
    }

    protected static void partTwo(List<Integer> list) {
        int count = 0;
        for (int i = 0; i < list.size()-3;i++) {
            if (list.get(i+3) > list.get(i)) count++;
        }
        System.out.println("Puzzel 2: " + count);
    }
}