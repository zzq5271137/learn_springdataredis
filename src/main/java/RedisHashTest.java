import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class RedisHashTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void hashSet() {
        redisTemplate.boundHashOps("myHash").put("k1", "v1");
        redisTemplate.boundHashOps("myHash").put("k2", "v2");
        redisTemplate.boundHashOps("myHash").put("k3", "v3");
    }

    @Test
    public void hashGet() {
        System.out.println(redisTemplate.boundHashOps("myHash").get("k1"));
        System.out.println(redisTemplate.boundHashOps("myHash").get("k2"));
        System.out.println(redisTemplate.boundHashOps("myHash").get("k3"));
    }

    @Test
    public void hashEntries() {
        Map<Object, Object> myHash = redisTemplate.boundHashOps("myHash").entries();
        System.out.println(myHash);
    }

}
