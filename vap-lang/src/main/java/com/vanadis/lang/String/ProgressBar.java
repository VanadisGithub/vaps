package com.vanadis.lang.String;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-12 10:03
 */

public class ProgressBar {

    private int index = 0;
    private String finish;
    private String unFinish;


    // 进度条粒度
    private final int PROGRESS_SIZE = 50;
    private int BITE = 2;

    public static void main(String[] args) throws InterruptedException {
        new ProgressBar().printProgress();
    }

    private String getNChar(int num, char ch) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }


    public void printProgress() throws InterruptedException {
        System.out.print("Progress:");

        finish = getNChar(index / BITE, '█');
        unFinish = getNChar(PROGRESS_SIZE - index / BITE, '─');
        String target = String.format("%3d%%[%s%s]", index, finish, unFinish);
        System.out.print(target);

        while (index <= 100) {
            finish = getNChar(index / BITE, '█');
            unFinish = getNChar(PROGRESS_SIZE - index / BITE, '─');

            target = String.format("%3d%%├%s%s┤", index, finish, unFinish);
            System.out.print(getNChar(PROGRESS_SIZE + 6, '\b'));
            System.out.print(target);

            Thread.sleep(50);
            index++;
        }
    }
}



