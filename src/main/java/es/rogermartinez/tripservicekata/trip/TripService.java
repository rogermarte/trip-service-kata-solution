package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import es.rogermartinez.tripservicekata.user.UserSession;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TripService {

    @Resource
    private TripDAO tripDAO;

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        validate(loggedInUser);

        return user.isFriendWith( loggedInUser)
                        ? tripsBy(user)
                        : noTrips();
    }

    private void validate(User loggedInUser) throws UserNotLoggedInException {
        if ( loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    private List<Trip> tripsBy(User user) {
        return tripDAO.tripsBy(user);
    }

}