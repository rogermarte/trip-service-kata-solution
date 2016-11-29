package es.rogermartinez.tripservicekata.trip;

import java.util.List;

import es.rogermartinez.tripservicekata.user.User;
import es.rogermartinez.tripservicekata.exception.DependendClassCallDuringUnitTestException;

public class TripDAO {

    public static List<Trip> findTripsByUser(User user) {
        throw new DependendClassCallDuringUnitTestException(
                "TripDAO should not be invoked on an unit test.");
    }

}