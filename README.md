<h1 align=center>Boids</h1>
<p align=center><i>A flocking simulator written in Java</i></p>
<p align="center"><img src="img/preview.png"/></p>

## About The Project

Developed by Craig Reynolds in 1986, boids are a form of artificial life that mimic the flocking model.behaviour of birds. Boids are able to form beautiful, mesmerizing patterns by following these three model.behaviour.rules: 
- Separation: Steer to avoid crowding local flockmates
- Alignment: Steer towards the average heading of local flockmates
- Cohesion: Steer to move towards the average position (center of mass) of local flockmates

[Further reading](https://en.wikipedia.org/wiki/Boids)

## User Stories

- [x] As a user, I want to be able to generate a world
- [x] As a user, I want to be able to add a body to a world
- [x] As a user, I want to be able to move all boids in a world
- [x] As a user, I want to be able to view the list of all boids in a world
- [x] As a user, I want to be able to automatically save a world when the program quits
- [x] As a user, I want to be able to optionally load my world file when the program starts

## Instructions for Grader

- You can generate a world by starting the program and following the prompt
- You can adjust the dimensions of a world by changing the size of the window
- You can add a boid to the world by clicking anywhere in the window
- You can move the boids by leaving the application open as there is a main loop that runs in the background
- You can view all of the boids in the main application
- You can save the state of the world by closing the application
- You can then optionally load the state of the world when reopening the application
- *NOTE: There are predefined values in the "Boid Simulation Settings" section of the WorldApp class that you can try adjusting to discover new behaviour patterns*

## License
[MIT](https://choosealicense.com/licenses/mit/)
