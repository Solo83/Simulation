package entity;

import config.EntityType;

public class Herbivore extends Creature {
    public Herbivore() {
        this.image = EntityType.HERBIVORE.getImage();
        this.speed = EntityType.HERBIVORE.getSpeed();
        this.hp = EntityType.HERBIVORE.getHp();
    }


}
