# Dungeon Crawler

A small 2D game in Java (Swing). A hero walks around a tile map, animated from a sprite sheet, and stops when it runs into a tree or a rock. I wrote it between February and April 2025 for the object-oriented design course at ENSEA in Cergy, France, during my exchange semester.

## How it works

- **The map** is the text file `img/level1.txt`: `T` is a tree and `R` is a rock (both solid), and a space is grass.
- **The hero** is created with the Builder pattern (`DynamicSpriteBuilder`). It keeps walking, and the arrow keys change its direction. It animates from a sprite sheet with ten frames for each of the four directions.
- **Three engines** run on 50 ms Swing timers. `RenderEngine` draws everything. `PhysicsEngine` moves the hero unless the next step would overlap a solid sprite (a rectangle collision check). `GameEngine` reads the keyboard.
- **Class design:** `Sprite` (anything drawn) is extended by `SolidSprite` (blocks movement), which is extended by `DynamicSprite` (moves and animates). `Displayable` and `Engine` are the interfaces.

## Running it

Java 17 or newer. Run it from the project folder, because the game loads its images from `./img`:

```sh
javac -d out $(ls src/*.java | grep -v Test)
java -cp out Main
```

## Not finished

- The trap tile has an image (`img/trap.png`) but isn't placed on the map yet.
- `GameEngineTest` sets up JUnit 5 and Mockito, but its tests are still empty.

---

*The game is my own code from 2025 (see the commit history). This README was added in November 2026 with help from Claude Code, an AI coding assistant.*
