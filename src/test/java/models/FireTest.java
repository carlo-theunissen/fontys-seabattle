package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import models.Fire;
import models.Orientation;
import models.Ship;
import models.ShipType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

public class FireTest {

    @DataPoints
    public Fire GetFire(){
        return new Fire(1,1);

    }

    @Theory
    public void ValidConstructorTest(){
        Fire fire = new Fire(1,1);
        Assert.assertNotNull(fire);
    }

    @Test(expected = InvalidArgumentException.class)
    public void InValidConstructorTest(){
        Fire fire = new Fire(-1,-1);
    }

    @Test
    public void getYTest(){
        Fire fire = GetFire();
        Assert.assertEquals(1, fire.getY());
    }

    @Test
    public void getXTest(){
        Fire fire = GetFire();
        Assert.assertEquals(1, fire.getX());
    }
}
