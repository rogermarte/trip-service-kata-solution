package es.rogermartinez.tripservicekata.user;

import org.junit.Test;

import static es.rogermartinez.tripservicekata.trip.UserBuilder.aUser;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private static final User BOB = new User();
    private static final User PAUL = new User();

    @Test
    public void should_inform_when_users_are_not_friends() throws Exception {
        User user = aUser()
                .friendWith(BOB)
                .build();

        assertThat(user.isFriendWith(PAUL), is(false));
    }

    @Test
    public void should_inform_when_users_are_friends() throws Exception {
        User user = aUser()
                .friendWith(BOB, PAUL)
                .build();

        assertThat(user.isFriendWith(PAUL), is(true));
    }
}