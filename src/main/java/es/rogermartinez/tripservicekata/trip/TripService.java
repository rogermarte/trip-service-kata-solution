package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import es.rogermartinez.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if ( getLoggedUser() == null) {
            throw new UserNotLoggedInException();
        }

        return user.isFriendWith( getLoggedUser())
                        ? tripsBy(user)
                        : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}