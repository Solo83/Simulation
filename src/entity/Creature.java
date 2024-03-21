package entity;
import actions.FindPathBFS;
import game.Coordinates;
import game.WorldMap;
import java.util.List;
import java.util.Set;

abstract class Creature extends Entity {
    protected Integer speed;
    protected double hp;
    private double HEALPOINT = 0.25;

    private boolean isSquareAvailableForMove (Coordinates coordinate, WorldMap worldMap) {
        return worldMap.isSquareEmpty(coordinate);
    }

    public void makeMove(Class<? extends Entity> targetClass, WorldMap worldMap) {

        if(worldMap.isSquareEmpty(this.getCoordinate())){
            return;
        }

        FindPathBFS findPathBFS = new FindPathBFS();
        List<Coordinates> pathToTarget = findPathBFS.findPath(this.getCoordinate(),targetClass,worldMap);
        Coordinates currentCoordinate = this.getCoordinate();
        Coordinates targetInNeighbourSquare = targetCoordinateInNeighbourSquare(currentCoordinate,worldMap,targetClass);
        Coordinates nextStep;

        if (targetClass.isInstance(worldMap.getEntity(targetInNeighbourSquare))){
            eat(targetInNeighbourSquare,currentCoordinate,worldMap);
            this.hp+=HEALPOINT;
            return;
        }


        if (pathToTarget.size()<=1) {
            nextStep = findPathBFS.getRandomEmptyCoordinateForMove(currentCoordinate,this.speed,worldMap);
            worldMap.moveEntity(this.getCoordinate(),nextStep);
            this.hp-=HEALPOINT;
        }

        else {
            nextStep = pathToTarget.get(this.speed-1);

            if (!isSquareAvailableForMove(nextStep,worldMap) && targetClass.isInstance(Herbivore.class)){
                Coordinates coordinateToAttack = pathToTarget.get(0);
                worldMap.moveEntity(currentCoordinate,coordinateToAttack);
                eat(coordinateToAttack,nextStep,worldMap);
                this.hp+=HEALPOINT;
                return;
            }

            worldMap.moveEntity(currentCoordinate,nextStep);
            this.hp-=HEALPOINT;
        }

           if (hp<=0) {
               dead(this.getCoordinate(),worldMap);
           }
    }

    private void eat(Coordinates target, Coordinates hunter, WorldMap worldMap) {

        Entity targetEntity = worldMap.getEntity(target);

        if (targetEntity instanceof Herbivore) {
            Entity predator = worldMap.getEntity(hunter);
            ((Herbivore)targetEntity).hp-=((Predator)predator).ap;

            if (((Herbivore)targetEntity).hp <= 0 ) {
                worldMap.removeEntity(target);
            }

        } else if (targetEntity instanceof Grass) {
            worldMap.removeEntity(target);
        }
    }

    private void dead(Coordinates coordinate, WorldMap worldMap) {
        worldMap.removeEntity(coordinate);
    }

    private Coordinates targetCoordinateInNeighbourSquare(Coordinates currentCoordinate, WorldMap worldMap, Class<? extends Entity> targetClass){

        Set<Coordinates> coordinateNeighbours = new FindPathBFS().getNeighbours(currentCoordinate,1,worldMap);

        for (Coordinates c : coordinateNeighbours) {
            if (targetClass.isInstance(worldMap.getEntity(c))) {
                return c;
            }
        }

        return currentCoordinate;
    }

}

