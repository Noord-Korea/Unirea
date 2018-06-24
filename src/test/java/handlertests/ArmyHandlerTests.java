package handlertests;

import com.google.gson.Gson;
import com.restserver.handler.ArmyHandler;
import com.restserver.json.request.army.TrainArmy;
import org.junit.Before;
import org.junit.Test;

public class ArmyHandlerTests {
    private ArmyHandler handler;
    private Gson gson = new Gson();
    @Before
    public void init() {
        handler = new ArmyHandler();
    }
    @Test
    public void testTrainArmy() {
        TrainArmy trainArmy = new TrainArmy(1, 1, 1, "test");
        handler.trainArmy(trainArmy);
    }
}
