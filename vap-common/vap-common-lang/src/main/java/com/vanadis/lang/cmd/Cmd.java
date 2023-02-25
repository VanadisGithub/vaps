package com.vanadis.lang.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * Cmd
 *
 * @author yaoyuan
 * @date 2022/2/15 10:16 AM
 */
public class Cmd {

    public static void main(String[] args) throws IOException {
        String command = "l -l";
        ByteArrayOutputStream susStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        CommandLine commandLine = CommandLine.parse(command);
        DefaultExecutor exec = new DefaultExecutor();
        //设置一分钟超时
        ExecuteWatchdog watchdog = new ExecuteWatchdog(3 * 1000);
        exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);
        try {
            int code = exec.execute(commandLine);
            System.out.println("result code: " + code);
            // 不同操作系统注意编码，否则结果乱码
            String suc = susStream.toString("GBK");
            String err = errStream.toString("GBK");
            System.out.println(suc + err);
        } catch (ExecuteException e) {
            if (watchdog.killedProcess()) {
                //被watchdog故意杀死
                System.err.println("超时了");
            }
        }

    }
}
