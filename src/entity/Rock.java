package entity;

import config.EntityType;

public class Rock extends Entity {
    public Rock() {
        this.image = EntityType.ROCK.getImage();
    }
}
