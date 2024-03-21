package entity;

import config.EntityType;

public class Tree extends Entity {
    public Tree() {
        this.image = EntityType.TREE.getImage();
    }
}
