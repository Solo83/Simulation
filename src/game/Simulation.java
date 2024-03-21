package game;

import actions.TurnAction;
import actions.InitAction;
import entity.Herbivore;
import entity.Predator;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final WorldMap worldMap;
    private final Renderer renderer = new Renderer();
    private final InitAction initActions = new InitAction();
    private final TurnAction turnActions = new TurnAction();
    boolean isNotEnded = true;
    private int turnCounter;

    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(new WorldMap(15, 30));
        simulation.startSimulation();
    }

    synchronized void nextTurn() {
        renderer.clearScreen();
        turnActions.makeMove(worldMap);
        renderer.renderMap(worldMap);
        System.out.println("Turn " + turnCounter);
    }

    void startSimulation() {

        initActions.settleMap(worldMap);

        while (isNotEnded) {
            nextTurn();
            turnCounter++;
            pauseSimulation();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stopSimulation();
        }

    }

    void pauseSimulation() {

        boolean isPaused = true;
        Scanner scanner = new Scanner(System.in);

        while (isPaused) {
            System.out.print("Simulation paused. Press \"Enter\" to resume simulation.");
            String s = scanner.nextLine();
            if (s.isEmpty()) {
                isPaused = false;
            }
        }

    }

    void stopSimulation() {
        if (worldMap.getEntitiesByClass(Herbivore.class).isEmpty()) {
            System.out.println("All herbivores are dead. Simulation ended.");
            isNotEnded = false;
        } else if (worldMap.getEntitiesByClass(Predator.class).isEmpty()) {
            System.out.println("\n" +
                    "All predators died of starvation. Simulation ended.");
            isNotEnded = false;
        }
    }
}
