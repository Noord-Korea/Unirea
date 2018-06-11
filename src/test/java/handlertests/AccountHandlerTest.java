package handlertests;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.PlayerRepository;
import com.models.Player;
import com.restserver.handler.AccountHandler;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.request.account.Login;
import com.restserver.json.request.account.Register;
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
    private AccessTokenRepository repoToken;
    private IAccountHandler handler;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
        AbstractRepoTest.emptyTable("Player");
        repo = new PlayerRepository();
        repoToken = new AccessTokenRepository();
        handler = new AccountHandler(repo, repoToken);
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    private void savePlayer() {
        repo.save(new Player("Bas", "bas@gmail.com", "passwd"));
    }

    @Test
    public void testLogin() {
        savePlayer();
        Login login = new Login("bas@gmail.com", "passwd");
        Reply reply = handler.login(login);
        assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testLoginIncorrect() {
        savePlayer();
        Login loginIncorrectPasswd = new Login("bas@gmail.com", "wrong");
        Reply reply = handler.login(loginIncorrectPasswd);
        assertEquals(Status.NOACCESS, reply.getStatus());
        Login loginNonExistent = new Login("test@gmail.com", "test");
        reply = handler.login(loginNonExistent);
        assertEquals(Status.NOTFOUND, reply.getStatus());
    }

    @Test
    public void testLoginEmailNull() {
        exception.expect(IllegalArgumentException.class);
        savePlayer();
        Login loginEmailEmpty = new Login(null, "test");
        handler.login(loginEmailEmpty);
    }

    @Test
    public void testLoginPasswordNull() {
        exception.expect(IllegalArgumentException.class);
        Login loginPasswordEmpty = new Login("bas@gmail.com", null);
        handler.login(loginPasswordEmpty);
    }

    @Test
    public void testRegister() {
        Register register = new Register("bas@gmail.com", "testpass", "bas");
        Reply reply = handler.register(register);
        assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testRegisterEmailDuplicate() {
        Register register = new Register("test@gmail.com", "testpass", "bas");
        Reply reply = handler.register(register);
        assertEquals(Status.OK, reply.getStatus());
        register = new Register("test@gmail.com", "testpass", "test");
        reply = handler.register(register);
        assertEquals(Status.CONFLICT, reply.getStatus());

    }


    @Test
    public void testRegisterEmailNull() {
        exception.expect(IllegalArgumentException.class);
        Register registerEmailNull = new Register(null, "testpass", "bas");
        handler.register(registerEmailNull);
    }

    @Test
    public void testRegisterPasswordNull() {
        exception.expect(IllegalArgumentException.class);
        Register registerUsernameNull = new Register("bas@gmail.com", "password", null);
        handler.register(registerUsernameNull);
    }

    @Test
    public void testRegisterUsernameNull() {
        exception.expect(IllegalArgumentException.class);
        Register registerPasswordNull = new Register("bas@gmail.com", null, "bas");
        handler.register(registerPasswordNull);
    }
}