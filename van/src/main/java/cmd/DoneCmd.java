package cmd;

import java.time.LocalDateTime;

import com.google.auto.service.AutoService;
import com.vanadis.lang.time.DateTimeUtils;
import yuque.Yuque;

import static com.vanadis.lang.time.DateTimeUtils.YYMMDD_HHMM;

/**
 * DoneCmd
 *
 * @author yy287502@alibaba-inc.com
 * @date 2022/3/7 10:35 AM
 */
@AutoService(Cmd.class)
public class DoneCmd implements Cmd {

    @Override
    public String cmdName() {
        return "done";
    }

    @Override
    public void run(String[] param) {
        if ("z".equals(param[0])) {
            Yuque.unDoDocBody();
        } else {
            Yuque.appendDocBody(DateTimeUtils.formatTime(LocalDateTime.now(), YYMMDD_HHMM) + " " + param[0]);
        }
    }
}
