import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class RedisListTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void listSet() {
        redisTemplate.boundListOps("myList").leftPush("001");
        redisTemplate.boundListOps("myList").leftPush("002");
        redisTemplate.boundListOps("myList").leftPush("003");
    }

    @Test
    public void listGet() {
        List<String> myList = redisTemplate.boundListOps("myList").range(0, -1);
        System.out.println(myList);
    }

}
