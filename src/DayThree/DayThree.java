package DayThree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DayThree {
    public static void main(String[] args) {
        List<String[]> temp = new ArrayList<>();
        for (String s : Objects.requireNonNull(fileReader())) {
            temp.add(s.split("(?!^)"));
        }
        partOne(temp);
    }

    protected static List<String> fileReader() {
        try {
            return Files.lines(Paths.get("src/DayThree/input.txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void partOne(List<String[]> list) {
        StringBuilder sb = new StringBuilder();
        int zeroSum = 0;
        int oneSum = 0;
        for (int i = 0; i < list.get(0).length; i++) {
            for (String[] arr : list) {
                switch (arr[i]) {
                    case "1" -> oneSum += 1;
                    case "0" -> zeroSum += 1;
                }
            }
            if (oneSum >= zeroSum) sb.append("1");
            else sb.append("0");
            oneSum = 0;
            zeroSum = 0;
        }
        String s1 = sb.toString();
        String s2 = s1.replaceAll("0", "x")
                .replaceAll("1", "0")
                .replaceAll("x", "1");
        System.out.println("Result part one: " + (Integer.parseInt(s1, 2) * Integer.parseInt(s2, 2)));
        partTwo(s1, list);
    }

    protected static void partTwo(String s1, List<String[]> list) {
        List<String[]> temp = new ArrayList<>(list);
        List<String[]> temp2 = new ArrayList<>(list);
        String o2 = iterating(temp, 1);
        String co2 = iterating(temp2, 0);
        System.out.println("Result part two: " + (Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2)));
    }

    protected static String iterating(List<String[]> list, int j) {
        List<String[]> itr = new ArrayList<>(list);
        List<String[]> temp = new ArrayList<>();
        int ones = 0;
        int zeros = 0;
        String most = "";
        for (int i = 0; i < itr.get(0).length;i++) {
            for (String[] arr : itr) {
                if (arr[i].equals("1")) ones++;
                else if (arr[i].equals("0")) zeros++;

            }
            if (j == 1) {
                if (ones >= zeros) {
                    most = "1";
                } else most = "0";
            } else if (j == 0) {
                if (ones >= zeros) {
                    most = "0";
                } else most = "1";
            }

            for (String[] arr : itr) {
                if (arr[i].equals(most)) temp.add(list.get(i));
            }
            itr = new ArrayList<>(temp);
            temp.clear();
            System.out.println(itr);
            if (itr.size() == 1) return "0";
        }



        System.out.println(list.size());
        return String.join("", list.get(0));
    }
}
