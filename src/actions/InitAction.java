package actions;

import config.EntityFactory;
import config.EntityType;
import game.*;

import java.util.List;
import java.util.Random;

public class InitAction extends Action{

    public void settleMap(WorldMap worldMap) {

        int mapSize = worldMap.getMapRow()* worldMap.getMapColumn();

        int herbivoresQuantity = (int) (mapSize*0.15);
        int predatorsQuantity = (int) (mapSize*0.03);
        int treesQuantity = (int) (mapSize*0.1);
        int grassQuantity = (int) (mapSize*0.2);
        int rocksQuantity = (int) (mapSize*0.2);

        randomSetEntityOnMapByQuantity(herbivoresQuantity,EntityType.HERBIVORE,worldMap);
        randomSetEntityOnMapByQuantity(predatorsQuantity,EntityType.PREDATOR,worldMap);
        randomSetEntityOnMapByQuantity(treesQuantity,EntityType.TREE,worldMap);
        randomSetEntityOnMapByQuantity(grassQuantity,EntityType.GRASS,worldMap);
        randomSetEntityOnMapByQuantity(rocksQuantity,EntityType.ROCK,worldMap);
    }

    private void randomSetEntityOnMapByQuantity(Integer quantity, EntityType entityType, WorldMap worldMap){

        Random random = new Random();
        List<Coordinates> freeCoordinates = worldMap.getFreeCoordinates();

        for (int i = 0; i < quantity; i++) {
            int randomIndex = random.nextInt(freeCoordinates.size());
            worldMap.setEntity(freeCoordinates.get(randomIndex),EntityFactory.getNewEntity(entityType));
        }

    }

}
