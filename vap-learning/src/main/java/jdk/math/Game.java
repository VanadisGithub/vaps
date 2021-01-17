package jdk.math;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Game
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/11 5:44 下午
 */
public class Game {

    private static int length = 20;
    private static int wight = 20;

    public static String[][] map = new String[length][wight];
    private static boolean flag = true;

    static {
        for (String[] strings : map) {
            Arrays.fill(strings, " ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (flag) {
            new GameThread(0, 0, "A").start();
            new GameThread(length - 1, wight - 1, "B").start();
            new GameThread(0, wight - 1, "C").start();
            new GameThread(length - 1, 0, "D").start();
            sout();
            Thread.sleep(10L);
        }
        int a[] = new int[] {0, 0, 0, 0};
        Arrays.stream(map).forEach(arr -> {
            Arrays.stream(arr).forEach(c -> {
                if (c == "A") { a[0] += 1; }
                if (c == "B") { a[1] += 1; }
                if (c == "C") { a[2] += 1; }
                if (c == "D") { a[3] += 1; }
            });
        });
        Arrays.stream(a).forEach(System.out::println);
    }

    private static void sout() {
        flag = false;
        Arrays.stream(map).forEach(arr -> {
            Arrays.stream(arr).forEach(c -> {
                if (c.equals(" ")) {
                    flag = true;
                }
                System.out.print(c + " ");
            });
            System.out.println("");
        });
        System.out.println("---------------------");
    }

    public static String go(int x, int y) {
        int go = ThreadLocalRandom.current().nextInt(0, 4);
        switch (go) {
            case 0:
                if (x + 1 < length) {
                    return String.format("%d,%d", x + 1, y);
                } else {
                    return "";
                }
            case 1:
                if (x - 1 > -1) {
                    return String.format("%d,%d", x - 1, y);
                } else {
                    return "";
                }
            case 2:
                if (y + 1 < wight) {
                    return String.format("%d,%d", x, y + 1);
                } else {
                    return "";
                }
            case 3:
                if (y - 1 > -1) {
                    return String.format("%d,%d", x, y - 1);
                } else {
                    return "";
                }
            default:
                return "";
        }
    }

    public static class GameThread extends Thread {
        private int x;
        private int y;
        private String name;
        private boolean flag;

        public GameThread(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.flag = true;
        }

        public GameThread(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public GameThread setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public GameThread setY(int y) {
            this.y = y;
            return this;
        }

        @Override
        public void run() {
            while (flag) {
                if (!map[x][y].equals(name)) {
                    map[x][y] = name;
                    break;
                }
                String go = go(x, y);
                if (go.length() == 0) {
                    flag = false;
                    break;
                }
                String[] xy = go.split(",");
                x = Integer.parseInt(xy[0]);
                y = Integer.parseInt(xy[1]);
            }
        }

    }

}
