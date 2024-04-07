package cmd;

import com.google.auto.service.AutoService;
import yuque.Yuque;

/**
 * DoneCmd
 *
 * @author yaoyuan
 * @date 2022/3/7 10:35 AM
 */
@AutoService(Cmd.class)
public class DoneCmd implements Cmd {

    @Override
    public String cmdName() {
        return "done";
    }

    @Override
    public String[] cmdAlias() {
        return new String[]{"done"};
    }

    @Override
    public void run(String[] param) {
        if ("z".equals(param[0])) {
            Yuque.unDoDocBody();
        } else {
        }
    }
}
