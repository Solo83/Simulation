package entity;
import config.EntityType;


public class Predator extends Creature {
    protected Integer ap; // attack power

    public Predator() {
        this.image = EntityType.PREDATOR.getImage();
        this.speed = EntityType.PREDATOR.getSpeed();
        this.hp = EntityType.PREDATOR.getHp();
        this.ap = EntityType.PREDATOR.getAp();
    }


    }


