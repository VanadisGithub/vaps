package google;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * CacheTest
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/10/21 2:03 下午
 */
public class CacheTest {

    public static void main(String[] args) {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(10000)
            .build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer key) {
                    return "hello" + key;
                }
            });
        System.out.println(cache.getUnchecked(1));
        try {
            System.out.println(cache.get(1));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
