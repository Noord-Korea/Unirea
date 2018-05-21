import com.dbal.repository.PlayerRepository;
import com.models.Player;
import com.restserver.handler.AccountHandler;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.request.account.Login;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repotests.AbstractRepoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.logging.Level;

public class AccountHandlerTest {

    private PlayerRepository repo;
    private IAccountHandler handler;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
        AbstractRepoTest.emptyTable("Player");
        repo = new PlayerRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    private void savePlayerCreateHandler() {
        repo.save(new Player("Bas", "bas@gmail.com", "passwd"));
        handler = new AccountHandler(repo);
    }

    @Test
    public void testLogin() {
        savePlayerCreateHandler();
        Login login = new Login("bas@gmail.com", "passwd");
        Reply reply = handler.login(login);
        assertEquals(Status.Ok, reply.getStatus());
    }


    @Test
    public void testLoginIncorrect() {
        savePlayerCreateHandler();
        Login loginIncorrectPasswd = new Login("bas@gmail.com", "wrong");
        Reply reply = handler.login(loginIncorrectPasswd);
        assertEquals(Status.NoAccess, reply.getStatus());
        Login loginNonExistent = new Login("test@gmail.com", "test");
        reply = handler.login(loginNonExistent);
        assertEquals(Status.NotFound, reply.getStatus());
    }
}
