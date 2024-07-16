package org.example.attack;

import org.example.attribute.MeleeType;
import org.example.attribute.RangedType;
import org.example.unit.unit;

public interface Attack {
    void performAttack(unit target);
}

public class MeleeAttack implements Attack {
    private MeleeType type;

    public MeleeAttack(MeleeType type) {
        this.type = type;
    }

    @Override
    public void performAttack(unit target) {
        System.out.println("Performing melee attack: " + type + " on " + target.getName());
    }
}

public class RangedAttack implements Attack {
    private RangedType type;

    public RangedAttack(RangedType type) {
        this.type = type;
    }

    @Override
    public void performAttack(unit target) {
        System.out.println("Performing ranged attack: " + type + " on " + target.getName());
    }
}
