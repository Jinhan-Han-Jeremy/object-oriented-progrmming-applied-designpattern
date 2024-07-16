package org.example.unit;

import org.example.attack.Attack;
import org.example.attribute.MovementType;

import java.util.List;

public class AirGroundUnit extends unit {
    public AirGroundUnit(String name, List<Attack> attacks) {
        super(name, attacks);
    }

    @Override
    protected double getAttackMultiplier(unit target) {
        if (target.getMovementType() == MovementType.AIR) {
            return 1.5;
        } else if (target.getMovementType() == MovementType.GROUND) {
            return 1.5;
        } else {
            return 1.0;
        }
    }
}