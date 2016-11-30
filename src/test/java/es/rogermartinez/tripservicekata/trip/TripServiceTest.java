package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private User loggedInUser;

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
        TripService tripService = new TestableTripService();

        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }
    }
}