package com.vanadis.start.conf;

import com.vanadis.start.filter.TestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * VapFilterConf
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/8 5:08 下午
 */
@Configuration
public class VapFilterConf {

    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }
}
