package entity;

import game.Coordinates;

public abstract class Entity {
    protected String image;
    private Coordinates coordinate;
    public String getImage() {
        return image;
    }
    public Coordinates getCoordinate() {
        return coordinate;
    }
    public void setCoordinates(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

}
