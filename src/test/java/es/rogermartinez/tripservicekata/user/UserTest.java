package es.rogermartinez.tripservicekata.user;

import es.rogermartinez.tripservicekata.trip.UserBuilder;
import javafx.beans.binding.BooleanBinding;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by rmartinez on 30/11/2016.
 */
public class UserTest {

    private static final User BOB = new User();
    private static final User PAUL = new User();

    @Test
    public void should_inform_when_users_are_not_friends() throws Exception {
        User user = UserBuilder.aUser()
                                    .friendWith(BOB)
                                    .build();

        assertThat(user.isFriendWith(PAUL), is(false));
    }
}