import cmd.Cmd;
import com.google.common.base.Strings;
import org.fusesource.jansi.Ansi;
import utils.CmdUtils;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * van使用 [vim ~/.bashrc][source ~/.bashrc]
 *
 * @author yaoyuan
 */
public class Main {

    private static ServiceLoader<Cmd> cmds;

    public static void welcome() {
        Ansi.Color color = Ansi.Color.values()[new Random().nextInt(9)];
        System.out.println(Ansi.ansi().fg(color).bold().a(Constant.LOGO).reset());
    }

    static {
        cmds = ServiceLoader.load(Cmd.class);
        init();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            welcome();
            cmdList();
            while (true) {
                System.out.print("请选择执行命令:\n");
                String input = getInput();
                runCmd(input.split(" "));
            }
        } else {
            runCmd(args);
        }

    }

    private static void cmdList() {
        for (Cmd cmd : cmds) {
            System.out.printf("[%s]", cmd.cmdName());
        }
        System.out.println();
    }

    private static void runCmd(String[] args) {
        for (Cmd cmd : cmds) {
            if (Arrays.stream(cmd.cmdAlias()).collect(Collectors.toList()).contains(args[0])) {
                String[] params = CmdUtils.deleteFirst(args);
                cmd.run(params);
            }
        }
    }

    private static String getInput() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (!Strings.isNullOrEmpty(input)) {
                return input;
            }
        }
    }

    public static void init() {
        try {
            String userHomeBash = System.getProperty("user.home") + "/.bashrc";
            File file = new File(userHomeBash);
            if (!file.exists()) {
                file.createNewFile();
            }
            String alias = "alias van='java -jar /Users/yaoyuan/.m2/repository/com/vanadis/van-cmd-tools/1.0.0-SNAPSHOT/van-cmd-tools-1.0.0-SNAPSHOT.jar'";
            List<String> allLines = Files.readAllLines(Paths.get(userHomeBash));
            if (allLines.contains(alias)) {
                return;
            }
            Files.write(Paths.get(userHomeBash), Collections.singletonList(alias), StandardOpenOption.APPEND);
            CmdUtils.exec(new String[]{"source", userHomeBash});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
