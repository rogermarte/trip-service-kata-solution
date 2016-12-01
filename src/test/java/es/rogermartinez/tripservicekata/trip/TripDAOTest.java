package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.DependendClassCallDuringUnitTestException;
import es.rogermartinez.tripservicekata.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rmartinez on 01/12/2016.
 */
public class TripDAOTest {

    @Test(expected = DependendClassCallDuringUnitTestException.class)
    public void should_throw_exception() throws Exception {
        new TripDAO().tripsBy(new User());
    }
}