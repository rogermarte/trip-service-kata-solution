package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
        TripService tripService = new TestableTripService();

        tripService.getTripsByUser(null);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}