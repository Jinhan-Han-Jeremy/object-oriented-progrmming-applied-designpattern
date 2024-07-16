package org.example.unit;

import org.example.attack.Attack;
import org.example.attribute.MovementType;

import java.util.List;

public abstract class unit {
    private String name;
    private MovementType movementType;
    private List<Attack> attacks;

    public unit(String name, MovementType movementType, List<Attack> attacks) {
        this.name = name;
        this.movementType = movementType;
        this.attacks = attacks;
    }

    public String getName() {
        return name;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void performAttacks(unit target) {
        double multiplier = getAttackMultiplier(target);
        for (Attack attack : attacks) {
            System.out.println(name + " attacks " + target.getName() + " with multiplier: " + multiplier);
            attack.performAttack(target);
        }
    }

    protected abstract double getAttackMultiplier(unit target);
}


public class Bothunit extends unit {
    public Bothunit(String name, List<Attack> attacks) {
        super(name, MovementType.BOTH, attacks);
    }

    @Override
    protected double getAttackMultiplier(unit target) {
        return target.getMovementType() == MovementType.AIR ? 1.5 : 1.0;
    }
}