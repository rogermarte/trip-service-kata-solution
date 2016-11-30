package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static es.rogermartinez.tripservicekata.trip.UserBuilder.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final Trip TO_LONDON = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @Before
    public void setUp() throws Exception {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends() throws Exception {
        loggedInUser = REGISTERED_USER;
        User friend = aUser()
                .friendWith(ANOTHER_USER)
                .withTrips(TO_BRAZIL)
                .build();

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(0));
    }

    @Test
    public void should_return_trips_when_users_are_friends() throws Exception {
        loggedInUser = REGISTERED_USER;
        User friend = aUser()
                .friendWith(ANOTHER_USER, loggedInUser)
                .withTrips(TO_BRAZIL, TO_LONDON)
                .build();

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(2));
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }

}