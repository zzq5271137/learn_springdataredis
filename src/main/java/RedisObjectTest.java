import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class RedisObjectTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void objectSet() {
        Person person = new Person("zzq", 100);  // 存入Redis的对象需要序列化(因为需要进行网络传输)
        redisTemplate.boundValueOps("person").set(person);
    }

    @Test
    public void objectGet() {
        Person person = (Person) redisTemplate.boundValueOps("person").get();
        System.out.println(person);
    }

    @Test
    public void objectListSet() {
        List<Person> allPerson = new ArrayList<>();
        allPerson.add(new Person("zzq1", 101));
        allPerson.add(new Person("zzq2", 102));
        allPerson.add(new Person("zzq3", 103));

        /*
         * 实际开发中我们一般不会轻易将数据存到一级key当中, 因为Redis中的key是非常宝贵的,
         * 我们一般会根据不同的业务, 将与某个业务相关联的所有数据存到同一个一级key中(以hash的方式存),
         * 然后再将这个业务中的不同子模块的数据存到这个hash的不同子key中;
         */
        redisTemplate.boundHashOps("userManagement").put("allPerson", allPerson);
    }

    @Test
    public void objectListGet() {
        List<Person> allPerson = (List<Person>) redisTemplate.boundHashOps("userManagement").get("allPerson");
        allPerson.forEach(System.out::println);
    }

}
