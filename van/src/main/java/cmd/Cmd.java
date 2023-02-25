package cmd;

/**
 * BaseCmd
 *
 * @author yaoyuan
 * @date 2022/3/7 10:32 AM
 */
public interface Cmd {

    String cmdName();

    void run(String[] param);

}
