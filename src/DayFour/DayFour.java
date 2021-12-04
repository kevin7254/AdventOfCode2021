package DayFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayFour {
    public static void main(String[] args) {
        partOne(getBoards());
    }

    private static List<String> getBoards() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/DayFour/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> words = new ArrayList<>();
        while (Objects.requireNonNull(scanner).hasNext()) {
            words.add(scanner.nextLine());
        }
        return words;
    }

    protected static void partOne(List<String> list) {
        String[] strings = list.remove(0).split(",");
        List<Integer> bingodata = new ArrayList<>();
        for (String s : strings) {
            bingodata.add(Integer.parseInt(s));
        }

        list.remove(0);
        System.out.println(bingodata);
        Bingo[][] boards = new Bingo[5][5];
        List<Bingo[][]> boardList = new ArrayList<>();
        int i = 0;
        for (String s : list) {
            if (!s.equals("")) {
                String[] arr = s.trim().split("\\s+");
                for (int j = 0; j < 5; j++) {
                    if (!arr[j].equals("")) boards[i][j] = new Bingo(Integer.parseInt(arr[j]));
                }
                i++;
            } else {
                boardList.add(boards);
                boards = new Bingo[5][5];
                i = 0;
            }
        }

        for (Integer number : bingodata) {
            for (Iterator<Bingo[][]> it = boardList.iterator(); it.hasNext(); ) {
                Bingo[][] bingos = it.next();
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        Bingo bingo = bingos[row][col];
                        if (bingo.getNum().equals(number)) {
                            bingo.setTaken(true);
                            if (checkForBingo(bingos)) {
                                partOneHelper(bingos, number);
                                it.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    private static void partOneHelper(Bingo[][] bingos, Integer number) {
        int sum = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (!bingos[r][c].getTaken()) sum += bingos[r][c].getNum();
            }
        }
        System.out.println(sum * number);
    }

    protected static boolean checkForBingo(Bingo[][] bingos) {
        int count = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (bingos[row][col].getTaken()) count++;
                if (count == 5) return true;
            }
            count = 0;
        }

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (bingos[col][row].getTaken()) count++;
                if (count == 5) return true;
            }
            count = 0;
        }
        return false;
    }
}

class Bingo {
    private Integer num;
    private boolean taken = false;

    Bingo(Integer num) {
        this.num = num;
    }

    protected boolean getTaken() {
        return taken;
    }

    protected void setTaken(boolean taken) {
        this.taken = taken;
    }

    protected Integer getNum() {
        return num;
    }
}