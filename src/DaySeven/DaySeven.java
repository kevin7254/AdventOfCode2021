package DaySeven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DaySeven {
    private static final List<Integer> testList = new ArrayList<>(Arrays
            .stream(new int[]{16, 1, 2, 0, 4, 2, 7, 1, 2, 14}).boxed().toList());

    public static void main(String[] args) {
        String[] strings = Objects.requireNonNull(fileReader()).get(0).split(",");
        List<Integer> temp = new LinkedList<>();

        for (String s : strings) {
            temp.add(Integer.parseInt(s));
        }
        temp.sort(Integer::compareTo);
        partOne(temp);
        partTwo(temp);
    }

    protected static List<String> fileReader() {
        try {
            return Files.lines(Paths.get("src/DaySeven/input.txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void partOne(List<Integer> list) {
        int median = list.get(list.size() / 2);
        int sum = 0;
        for (Integer j : list) {
            sum += Math.abs(j - median);
        }
        System.out.println(sum);
    }

    protected static void partTwo(List<Integer> list) {
        int mean = (int) list.stream().mapToDouble(d -> d).average().orElse(0.0);
        int sum = 0;
        for (Integer j : list) {
            int n = Math.abs(j - mean);
            sum += (n * (n + 1)) / 2; //Gauss sum :)
        }
        System.out.println(sum);
    }
}