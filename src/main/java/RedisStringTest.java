import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class RedisStringTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void stringSet() {
        redisTemplate.boundValueOps("myStr").set("hello");
    }

    @Test
    public void stringGet() {
        String myStr = redisTemplate.boundValueOps("myStr").get();
        System.out.println(myStr);
    }

}
