# Simulation Project

Created according to the technical specifications presented in this [course.](https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/)

## Overview

This project is a 2D step-by-step simulation of a world populated by predators and herbivores. The simulation takes place on a map that also contains grass (food for herbivores), trees and rocks (obstacles).
The predators hunt the herbivores, while the herbivores search for grass to eat and avoid predators. The simulation continues until all the herbivores are eliminated.

![image](https://github.com/Solo83/Simulation/assets/72335401/71f1e503-2ff9-4bb2-88fc-12c6af7eefb4)

## Features

- World Generation: The simulation generates a world with a specified size and a random distribution of all entities.
- Movement: Herbivores and predators use the Breadth First Search(BFS) algorithm to find prey.
- Eating: Herbivores eat plants, which are randomly distributed on the grid and generated during the simulation. Predators eat herbivores.
- Death: Herbivores die if they go too many turns without eating.
- `Herbivores` can either go towards the `Grass` or eat it.
- `Predators` can either go towards the `Herbivores` or eat them.
- [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) used as pathfinding algorithm.
- Renderer displays the current state of the simulation in the console output.
