package com.vanadis;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main
 *
 * @author yaoyuan
 * @date 2021/1/17 5:50 下午
 */
public class Main {

    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JLabel label = new JLabel("Hello World");
        JLabel label2 = new JLabel("你好");
        frame.getContentPane().add(label);
        frame.getContentPane().add(label2);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}
