package models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

public class PlayerTest {

    @DataPoints
    public Player GetPlayer(){
        Player player = new Player();
        player.setName("Henk");
        player.setId(1);
        return player;
    }

    @Theory
    public void ValidConstructorTest(){
        Player player = new Player();
        Assert.assertNotNull(player);
    }

    @Test
    public void getNameTest(){
        Player player = GetPlayer();
        Assert.assertEquals("Henk", player.getName());
    }

    @Test
    public void setNameTest(){
        Player player = GetPlayer();
        player.setName("Piet");
        Assert.assertEquals("Piet", player.getName());
    }

    @Test
    public void getIdTest(){
        Player player = GetPlayer();
        Assert.assertEquals(1, player.getId());
    }

    @Test
    public void setIdTest(){
        Player player = GetPlayer();
        player.setId(2);
        Assert.assertEquals(2, player.getId());
    }
}
