import cmd.Cmd;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;
import utils.CmdUtils;

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
public class Main extends Constant {

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
            System.out.println(cmd.cmdName());
        }
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
                System.out.println(userHomeBash + " is not excited !");
                file.createNewFile();
            }
            String alias = "/Users/yaoyuan/.m2/repository/vanadis/van/1.0-SNAPSHOT/van-1.0-SNAPSHOT.jar";
            Files.write(Paths.get(userHomeBash), Collections.singletonList(alias), StandardOpenOption.APPEND);
            CmdUtils.exec(new String[]{"source", userHomeBash});
            List<String> alise = Files.lines(Paths.get(userHomeBash)).collect(java.util.stream.Collectors.toList());
            System.out.println(alise);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
