package pers.mine.nookblog.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.mine.nookblog.utils.StringX;

import java.util.Date;

/**
 * @author yebukong
 * @description 定时任务
 * @since 2019-01-25 03:05
 */
@Slf4j
@Component
public class ScheduledService {
//fixedRate：定义一个按一定频率执行的定时任务
//@Scheduled(fixedRate = 5000)
//fixedDelay：定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间
//@Scheduled(fixedDelay = 5000)
//    一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。按顺序依次为：
//    秒（0~59）
//    分钟（0~59）
//    小时（0~23）
//    天（0~31）
//    月（0~11）
//    星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
//    年份（1970－2099）
    //cron：通过表达式来配置任务执行时间
    @Scheduled(cron = "* 0-23/1 * * * *")
    public void scheduled() {
        log.debug("=====>>>>>使用cron  {}", StringX.toString(new Date(),"yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * 自动更新博客页面任务[维护首页，时间轴文章，验证文章是否缺失,统计访问数量，评论数，log是否有报错等]
     */
    @Scheduled(cron = "* 0-23/1 * * * *")
    public void autoUpdateBlogPageTask() {
        //TODO 后续完成
        log.debug("AutoUpdateBlogPageTask任务运行...");
    }
}
