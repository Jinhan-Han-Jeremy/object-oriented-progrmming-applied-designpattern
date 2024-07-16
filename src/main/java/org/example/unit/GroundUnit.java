package org.example.unit;

import org.example.attack.Attack;
import org.example.attribute.MovementType;

import java.util.List;

public class GroundUnit extends unit {
    public GroundUnit(String name, List<Attack> attacks) {
        super(name, MovementType.GROUND, attacks);
    }

    @Override
    protected double getAttackMultiplier(unit target) {
        return target.getMovementType() == MovementType.BOTH ? 1.5 : 1.0;
    }
}