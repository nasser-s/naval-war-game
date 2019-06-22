package com.ns.test;

import com.ns.eval.websocket.Application;
import com.ns.eval.websocket.service.Location;
import com.ns.eval.websocket.service.SceneService;
import com.ns.eval.websocket.service.Sprite;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
@RunWith(SpringJUnit4ClassRunner.class)


@SpringBootTest(classes = {Application.class}, properties = {"server.port=1234",
        "management.port=4321"})
public class TestMove {

    @Autowired
    private SceneService service;
    @Test
    public void testAdd(){
        String title1 = "A1";
        service.addPlayer(RandomStringUtils.randomAlphabetic(10), title1);
        Assert.assertEquals(1,service.getScene().getPlayers().size());
        service.addPlayer(RandomStringUtils.randomAlphabetic(10),"B2");
        Assert.assertEquals(2,service.getScene().getPlayers().size());
        Assert.assertEquals(title1,service.getScene().getPlayers().values().iterator().next().getTitle());
    }

}
