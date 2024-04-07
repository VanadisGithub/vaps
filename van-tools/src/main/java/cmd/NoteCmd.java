package cmd;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ExitCmd
 *
 * @author yaoyuan
 * @date 2024/4/7 11:56
 */
@AutoService(Cmd.class)
public class NoteCmd implements Cmd {

    private static List<String> commands = new ArrayList<>();

    private static String cmdFile = "./commands.txt";

    @Override
    public String cmdName() {
        return "note";
    }

    @Override
    public String[] cmdAlias() {
        return new String[]{"note"};
    }

    @Override
    public void run(String[] param) {
        System.exit(0);
    }

    private static void showCmd() {
        for (int i = 0; i < commands.size(); i++) {
            System.out.println(String.format("[%d] %s", i + 1, commands.get(i)));
        }
    }

    private static void readCmd() {
        try {
            commands = Files.lines(Paths.get(cmdFile)).collect(java.util.stream.Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCmd(String command) {
        try {
            Files.write(Paths.get(cmdFile), Collections.singletonList(command), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeCmd(int no) {
        try {
            String cmd = commands.remove(no);
            Files.write(Paths.get(cmdFile), commands);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
