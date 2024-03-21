package actions;

import entity.Entity;
import game.Coordinates;
import game.CoordinatesShift;
import game.WorldMap;

import java.util.*;

public class FindPathBFS extends Action {

    private final Integer SEARCHSPEED = 1;

    public List<Coordinates> findPath(Coordinates start, Class<? extends Entity> target, WorldMap worldMap) {

        Set<Coordinates> visited = new HashSet<>();
        Queue<ArrayList<Coordinates>> queue = new ArrayDeque<>();
        ArrayList<Coordinates> path = new ArrayList<>();

        path.add(start);
        queue.add(path);

        while (!queue.isEmpty()) {

            path = queue.poll();
            Coordinates currentNode = path.get(path.size()-1);

            if (target.isInstance(worldMap.getEntity(currentNode))) {
                path.remove(start);
                return path;

            } else if (!visited.contains(currentNode)) {
                Set<Coordinates> neighboursSet = getNeighbours(currentNode, SEARCHSPEED, worldMap);
                for (Coordinates neighbourCoordinate : neighboursSet) {
                    if ((worldMap.isSquareEmpty(neighbourCoordinate) && !visited.contains(neighbourCoordinate))
                            || (!worldMap.isSquareEmpty(neighbourCoordinate) && target.isInstance(worldMap.getEntity(neighbourCoordinate)))) {
                        ArrayList<Coordinates> newPath = new ArrayList<>(path);
                        newPath.add(neighbourCoordinate);
                        queue.add(newPath);
                    }
                }
                visited.add(currentNode);
            }
        }
        return Collections.emptyList();
    }

    public Coordinates getRandomEmptyCoordinateForMove(Coordinates coordinate, Integer speed, WorldMap worldMap) {

        Set<Coordinates> neighbours = getNeighbours(coordinate, speed, worldMap);

        Coordinates randomCoordinate = coordinate;
        ArrayList<Coordinates> freeSquares = new ArrayList<>();

        for (Coordinates neighbour : neighbours) {
            if (worldMap.isSquareEmpty(neighbour)) {
                freeSquares.add(neighbour);
            }
        }

        if (!freeSquares.isEmpty()) {
            Random rnd = new Random();
            int i = rnd.nextInt(freeSquares.size());
            randomCoordinate = freeSquares.get(i);
        }

        return randomCoordinate;
    }


    public Set<Coordinates> getNeighbours(Coordinates coordinate, Integer speed, WorldMap worldMap) {

        int[][] directionShifts = {{-speed, 0}, {speed, 0}, {0, -speed}, {0, speed}};

        Set<Coordinates> neighbours = new HashSet<>();

        for (int[] directionShift : directionShifts) {
            CoordinatesShift shift = new CoordinatesShift(directionShift[0], directionShift[1]);
            if (coordinate.canShift(shift, worldMap)) {
                Coordinates shiftedCoordinate = coordinate.shift(shift);
                neighbours.add(shiftedCoordinate);
            }
        }
        return neighbours;
    }

}
