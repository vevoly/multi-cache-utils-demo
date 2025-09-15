package com.github.vevoly.demo.config;

import com.github.vevoly.plug.CachePreloadable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MultiCachePreLoad implements CommandLineRunner {

    private final List<CachePreloadable> preloadableServices;
    private static final String LOG_PREFIX = "[MultiCache Preload] ";

    @Override
    public void run(String... args) throws Exception {
        log.info(LOG_PREFIX + " ====== 开始执行应用启动缓存预热 ======");
        StopWatch stopWatch = new StopWatch("Total Cache Preload");
        stopWatch.start();

        for (int i = 0; i < preloadableServices.size(); i++) {
            CachePreloadable service = preloadableServices.get(i);
            String serviceName = service.getPreloadName();

            try {
                StopWatch taskStopWatch = new StopWatch();
                taskStopWatch.start();

                int count = service.preLoadCache();

                taskStopWatch.stop();
                log.info(LOG_PREFIX + " {}. {}: {} 条数据 (耗时: {} ms)",
                        i + 1, serviceName, count, taskStopWatch.getTotalTimeMillis());
            } catch (Exception e) {
                log.error(LOG_PREFIX + " [ERROR] {}. {} 预热失败!", i + 1, serviceName, e);
            }
        }

        stopWatch.stop();
        log.info(LOG_PREFIX + " ====== 所有缓存预热任务执行完毕，总耗时: {} ms ======", stopWatch.getTotalTimeMillis());
	}
}
