package config;

import entity.*;

public class EntityFactory {

    public static Entity getNewEntity(EntityType entityType) {
        return switch (entityType) {
            case HERBIVORE -> new Herbivore();
            case PREDATOR -> new Predator();
            case GRASS -> new Grass();
            case ROCK -> new Rock();
            case TREE -> new Tree();
        };
    }

}
