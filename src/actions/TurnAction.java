package actions;
import entity.Grass;
import entity.Herbivore;
import entity.Predator;
import game.WorldMap;
import java.util.List;

public class TurnAction extends Action {

    public void makeMove(WorldMap worldMap){

        List<Herbivore> herbivores = worldMap.getEntitiesByClass(Herbivore.class);
        List<Predator> predators = worldMap.getEntitiesByClass(Predator.class);

        for (Herbivore herbivore : herbivores) {
            herbivore.makeMove(Grass.class,worldMap);
        }

        for (Predator predator : predators) {
            predator.makeMove(Herbivore.class,worldMap);
        }
    }
}
