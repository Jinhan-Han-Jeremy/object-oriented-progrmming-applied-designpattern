package org.example.unit;

import org.example.attack.Attack;
import org.example.attribute.MovementType;

import java.util.List;

public class AirUnit extends unit {
    public AirUnit(String name, List<Attack> attacks) {
        super(name, MovementType.AIR, attacks);
    }

    @Override
    protected double getAttackMultiplier(unit target) {
        return target.getMovementType() == MovementType.GROUND ? 1.5 : 1.0;
    }
}
