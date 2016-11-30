package es.rogermartinez.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import es.rogermartinez.tripservicekata.exception.UserNotLoggedInException;
import es.rogermartinez.tripservicekata.user.User;
import es.rogermartinez.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedInUser = getLoggedUser();
        if (loggedInUser != null) {
            if (user.isFriendWith(loggedInUser)) {
                tripList = tripsBy(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}