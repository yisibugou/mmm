package com.mmm;

import com.mmm.server.common.util.RedisUtil;
import com.mmm.server.user.entity.User;
import com.mmm.server.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MmmApplicationTests {
    @LocalServerPort
    private int port;
    private static final Logger log = LoggerFactory.getLogger(MmmApplicationTests.class);
    private URL propertiesUrl1;
    private URL propertiesUrl2;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private RedisUtil redisUtil;
    @Before
    public void setUp() throws Exception {
        this.propertiesUrl1 = new URL("http://localhost:" + port + "/mmm/1");
        this.propertiesUrl2 = new URL("http://localhost:" + port + "/mmm/2");
    }

    @Test
    public void test4() throws Exception {
        //userMapper.insertSelective(user1);
        User user = userMapper.selectByPrimaryKey(1);
        log.info("[id] - [{}]", user.getId());
        // userMapper.insertSelective(user2);
        log.info("[user_name] - [{}]", user.getPassword());
        // userMapper.insertSelective(user3);
        log.info("[password] - [{}]", user.getUserName());
    }

    @Test
    public void test6() throws Exception {
/*        //userMapper.insertSelective(user1);
        log.info("[put] - [{}]", redisUtil.put("test","1111"));
        // userMapper.insertSelective(user2);
        log.info("[get] - [{}]", redisUtil.get("test"));*/
    }
}
