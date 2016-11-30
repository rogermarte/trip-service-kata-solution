package es.rogermartinez.tripservicekata.trip;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import es.rogermartinez.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedInUser = getLoggedUser();
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        List<Trip> tripList = new ArrayList<Trip>();
        if (user.isFriendWith(loggedInUser)) {
            tripList = tripsBy(user);
        }
        return tripList;

    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}