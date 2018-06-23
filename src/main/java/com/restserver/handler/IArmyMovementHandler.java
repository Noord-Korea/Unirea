package com.restserver.handler;

import com.models.ArmyMovementQueue;
import com.restserver.json.request.MoveArmy;
import com.restserver.json.response.Reply;

public interface IArmyMovementHandler {
    Reply moveArmy(MoveArmy moveArmy);
    double calcDistanceToTargetByTownIds(int homeTownId, int targetTownId);
    Boolean calcArmyBattle(ArmyMovementQueue queue);
    void updateTroopsInTown(ArmyMovementQueue queue);
}
