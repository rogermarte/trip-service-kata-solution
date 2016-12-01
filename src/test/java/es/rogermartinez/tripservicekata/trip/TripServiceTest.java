package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static es.rogermartinez.tripservicekata.trip.UserBuilder.aUser;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final Trip TO_LONDON = new Trip();
    @Mock
    private TripDAO tripDAO;
    @InjectMocks
    private TripService tripService;

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
        tripService.getTripsByUser(UNUSED_USER, GUEST);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends() throws Exception {
        User friend = aUser()
                .friendWith(ANOTHER_USER)
                .withTrips(TO_BRAZIL)
                .build();

        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(trips.size(), is(0));
    }

    @Test
    public void should_return_trips_when_users_are_friends() throws Exception {
        User friend = aUser()
                .friendWith(ANOTHER_USER, REGISTERED_USER)
                .withTrips(TO_BRAZIL, TO_LONDON)
                .build();
        given(tripDAO.tripsBy(any(User.class))).willReturn(friend.trips());

        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(trips.size(), is(2));
    }

}