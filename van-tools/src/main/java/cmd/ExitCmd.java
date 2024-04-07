package cmd;

import com.google.auto.service.AutoService;

/**
 * ExitCmd
 *
 * @author yaoyuan
 * @date 2024/4/7 11:56
 */
@AutoService(Cmd.class)
public class ExitCmd implements Cmd {

    @Override
    public String cmdName() {
        return "exit";
    }

    @Override
    public String[] cmdAlias() {
        return new String[]{"exit", "quit", "q", "0"};
    }

    @Override
    public void run(String[] param) {
        System.exit(0);
    }
}
