package google;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
 * 重试
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/4/25 5:13 下午
 */
public class RetryTest {

    static Retryer<Boolean> retryer;

    static {
        retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfException() // 抛出异常会进行重试
            .retryIfResult(Predicates.equalTo(false)) // 如果接口返回的结果不符合预期,也需要重试
            .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS)) // 重试策略, 此处设置的是重试间隔时间
            .withStopStrategy(StopStrategies.stopAfterAttempt(5)) // 重试次数
            .build();
    }

    public static void main(String[] args) {
        uploadFile("123");
    }

    public static boolean uploadFile(String fileName) {
        try {

            return retryer.call(new Callable<Boolean>() {
                int count = 0;

                @Override
                public Boolean call() throws Exception {
                    System.out.println(fileName + " " + count++);
                    return false;
                }
            });
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
