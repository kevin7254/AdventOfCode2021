package DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DaySix {
    private static final List<Integer> testList = new ArrayList<>(Arrays
            .stream(new int[]{3, 4, 3, 1, 2}).boxed().toList());

    public static void main(String[] args) {
        String[] strings = Objects.requireNonNull(fileReader()).get(0).split(",");
        List<Integer> temp = new LinkedList<>();

        for (String s : strings) {
            temp.add(Integer.parseInt(s));
        }
        solution(temp, 80);
        solution(temp, 256);
    }

    protected static List<String> fileReader() {
        try {
            return Files.lines(Paths.get("src/DaySix/input.txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void solution(List<Integer> list, int days) {
        int c = 0;
        long[] state = new long[9];
        for (int i : list) state[i]++; // how many of each state, for example how many in 4
        while (c++ < days) {
            long l = state[0];
            for (int j = 0; j < state.length - 1; j++) {
                state[j] = state[j + 1]; //next day fish in 1 go to 0 and so on
            }
            state[6] += l; // parents get reset to 6 and added
            state[8] = l; //all fish in 0 gets a child added to 8
        }
        System.out.println(Arrays.stream(state).sum());
    }
}