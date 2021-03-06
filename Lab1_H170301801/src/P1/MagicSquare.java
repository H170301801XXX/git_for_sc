package P1;

import java.io.*;
import java.sql.Time;
import java.util.Timer;

public class MagicSquare {

    public static void main(String[] args) {
        // TODO
        System.out.println("1.txt\t" + isLegalMagicSquare("./src/P1/txt/1.txt"));
        System.out.println("2.txt\t" + isLegalMagicSquare("./src/P1/txt/2.txt"));
        System.out.println("3.txt\t" + isLegalMagicSquare("./src/P1/txt/3.txt"));
        System.out.println("4.txt\t" + isLegalMagicSquare("./src/P1/txt/4.txt"));
        System.out.println("5.txt\t" + isLegalMagicSquare("./src/P1/txt/5.txt"));
        System.out.println("\n");
        if (generateMagicSquare((int)(Math.random() * 1000))) {
            System.out.println("6.txt\t" + isLegalMagicSquare("./src/P1/txt/6.txt"));
        } else {
            System.out.println(false);
        }
    }

    private static boolean isLegalMagicSquare(String fileName) {
        // TODO
        final int LENGTH = 1000;
        int[][] MagicSquare = new int[LENGTH][LENGTH];
        int[] ColumnSum = new int[LENGTH], RowSum = new int[LENGTH];
        int DiagonalPositive = 0, DiagonalNegative = 0;
        int count = 0;
        try {
            BufferedReader content = new BufferedReader(new FileReader(fileName));
            String line = content.readLine();
            int width = line.split("\t").length;
            while (line != null) {
                String[] ParseNum = line.split("\t");
                if (width != ParseNum.length) {
                    System.out.println("列数字不相同…");
                    return false;
                }
                for (int i = 0; i < ParseNum.length; i++) {
                    int temp = Integer.parseInt(ParseNum[i]);
                    if (temp <= 0) {
                        System.out.println("存在非正数…");
                        return false;
                    }
                    MagicSquare[count][i] = temp;
                    RowSum[count] += temp;
                    ColumnSum[i] += temp;
                    if (i == count) {
                        DiagonalNegative += temp;
                    }
                    if (i + count + 1 == ParseNum.length) {
                        DiagonalPositive += temp;
                    }
                }
                count++;
                line = content.readLine();
            }
            if (count != width) {
                System.out.println("行数不相同…");
                return false;
            }
            boolean flag = true;
            for (int i = 0; i < count - 1; i++) {
                if (ColumnSum[i] != ColumnSum[i + 1]) {
                    flag = false;
                    break;
                }
                if (RowSum[i] != RowSum[i + 1]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (RowSum[0] == DiagonalPositive) {
                    if (RowSum[0] == DiagonalNegative)
                        return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean generateMagicSquare(int n) {
        if (n <= 0) {
            System.out.println("错误！输入n不可以是非正数…");
            return false;
        }
        if (n % 2 == 0) {
            System.out.println("错误！输入n不可以是偶数…");
            return false;
        }
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0) row = n - 1;
                else row--;
                if (col == (n - 1)) col = 0;
                else col++;
            }
        }
        File file = new File("src/P1/txt/6.txt");
        try {
            FileWriter writer = new FileWriter(file);
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++)
                    writer.write(magic[i][j] + "\t", 0, (magic[i][j] + "\t").length());
                writer.write('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
