package cmd;

/**
 * BaseCmd
 *
 * @author yy287502@alibaba-inc.com
 * @date 2022/3/7 10:32 AM
 */
public interface Cmd {

    String cmdName();

    void run(String[] param);

}
