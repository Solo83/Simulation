package game;
import entity.Grass;
import entity.Herbivore;
import entity.Predator;

public class Renderer {

    public void renderMap(WorldMap worldMap)  {
        System.out.printf("\nWorld size is %dx%d\n", worldMap.getMapRow(),worldMap.getMapColumn());

        for (int i = 0; i < worldMap.getMapRow() ; i++) {

            for (int j = 0; j < worldMap.getMapColumn(); j++) {
                Coordinates coordinates = new Coordinates(i,j);
                if (!worldMap.isSquareEmpty(coordinates)){
                  System.out.print(worldMap.getEntity(coordinates).getImage());}
                else {
                    System.out.print("⬛");
                }
            }
            System.out.println();
        }

        System.out.printf("Herbivores count: %d,Predator count: %d, Grass count: %d\n",worldMap.getEntitiesByClass(Herbivore.class).size(),worldMap.getEntitiesByClass(Predator.class).size(),worldMap.getEntitiesByClass(Grass.class).size());

    }

    public void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("\033[0;0H");
        System.out.flush();
    }


}
