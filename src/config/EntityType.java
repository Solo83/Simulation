package config;

public enum EntityType {
    GRASS ("🌿",0,0,0),
    HERBIVORE("🐑",3,1,0),
    TREE("🌲",0,0,0),
    PREDATOR("🐺",2,2,1),
    ROCK("🗻",0,0,0);

    private final String image;
    private final Integer hp;
    private final Integer speed;
    private final Integer ap;

    EntityType(String image, Integer hp, Integer speed, Integer ap) {
        this.image = image;
        this.hp = hp;
        this.speed = speed;
        this.ap = ap;
    }

    public String getImage() {
        return image;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getAp() {
        return ap;
    }

}


