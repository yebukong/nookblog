package pers.mine.nookblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes= NookblogApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NookblogApplicationTests {

    @Test
    public void contextLoads() {
    }

}
