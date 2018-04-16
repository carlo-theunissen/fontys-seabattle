package models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

public class HitTest {

    @DataPoints
    public Hit GetHit(){
        return new Hit(1,1, HitType.Miss,false);

    }

    @Theory
    public void ValidConstructorTest(){
        Hit hit = new Hit(1,1, HitType.Miss);
        Assert.assertNotNull(hit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void InValidConstructorTest(){
        Hit hit = new Hit(-1,-1,null);
    }

    @Test
    public void getYTest(){
        Hit hit = GetHit();
        Assert.assertEquals(1, hit.getY());
    }

    @Test
    public void getXTest(){
        Hit hit = GetHit();
        Assert.assertEquals(1, hit.getX());
    }

    @Test
    public void getSunk(){
        Hit hit = GetHit();
        Assert.assertEquals(false, hit.getSunk());
    }

    @Test
    public void setSunk(){
        Hit hit = GetHit();
        hit.setSunk(true);
        Assert.assertEquals(true, hit.getSunk());
    }
}
