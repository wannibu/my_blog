package black.lyg.blog.config;

import black.lyg.blog.util.RedisKeyUtils;
import black.lyg.blog.po.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private black.lyg.blog.mapper.BlogMapper BlogMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Blog> list = BlogMapper.findBlogAll();
        for (Blog blog : list) {
            redisTemplate.opsForHash().put(RedisKeyUtils.ALL_BLOG, blog.getBlogId(), blog);
            log.info("将blog数据添加到redis的AllBlog中，其博客标题为：" + blog.getTitle());
        }
        redisTemplate.opsForValue().set(RedisKeyUtils.BLOG_COUNT, list.size());
        log.info("初始化redis中blogCount的数量为：" + list.size());
    }
}
