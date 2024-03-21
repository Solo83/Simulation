package game;
import entity.Entity;
import java.util.*;


public class WorldMap {

    private final Integer mapRow;
    private final Integer mapColumn;

    public WorldMap(Integer mapRow, Integer mapColumn) {
        this.mapRow = mapRow;
        this.mapColumn = mapColumn;
    }

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();
    public boolean isSquareEmpty(Coordinates coordinate){
        return !entities.containsKey(coordinate);
    }
    public void removeEntity(Coordinates coordinate) {
        entities.remove(coordinate);
    }
    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to,entity);
    }

    public Entity getEntity(Coordinates coordinate){
        return entities.get(coordinate);
    }
    public Integer getMapRow() {
        return mapRow;
    }
    public Integer getMapColumn() {
        return mapColumn;
    }
    public void setEntity(Coordinates coordinate,Entity entity) {
        entity.setCoordinates(coordinate);
        entities.put(coordinate,entity);
    }

    public <T> List<T> getEntitiesByClass(Class<? extends Entity> clazz) {

        List<T> entitiesList = new ArrayList<>();

        for (Entity entity : entities.values()) {

            if (clazz.isInstance(entity)) {
                entitiesList.add((T) entity);
            }
        }
        return entitiesList;
    }

    public List<Coordinates> getFreeCoordinates() {

        List<Coordinates> freeCoordinates = new ArrayList<>();

        for (int i = 0; i < mapRow ; i++) {
            for (int j = 0; j < mapColumn; j++) {
                Coordinates coordinate = new Coordinates(i,j);
                if(isSquareEmpty(coordinate)){
                    freeCoordinates.add(coordinate);
                }

            }
        }
        return freeCoordinates;
    }

}
