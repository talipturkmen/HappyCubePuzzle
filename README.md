# Happy Cube Puzzle

> **Archived.** This is a 2018 course/learning project and is no longer maintained.

A Java program that solves a six-piece Happy Cube puzzle (the blue cube) by brute force. It tries the piece orientations edge by edge, checks consistency, and draws each complete solution it finds.

The original task asked for a solver written in plain Java SE, with no solver logic taken from the web, and with the puzzle pieces encoded directly in the source.

## Tech stack

- Java
- Maven (`pom.xml`, JUnit 3.8.1 for tests)

## Repository structure

```
pom.xml                                   Maven build file
src/main/java/org/talipturkmen/cube/
  App.java            entry point; solves the BLUE puzzle
  Cube.java           cube model
  CubeSolver.java     recursive brute-force solver and drawing of the result
  Piece.java          piece model and orientations
  Edge.java, Corner.java, Toolkit.java, PUZZLE.java
src/test/java/        JUnit test (AppTest)
```

## Build and run

Requires a JDK and Maven.

```
mvn package
java -cp target/classes org.talipturkmen.cube.App
```

The solver prints its output to standard output. The entry point is currently wired to the BLUE puzzle only; `PUZZLE` also lists RED, PURPLE, YELLOW and DEFAULT, which are not selected by `App`.

Build and run were not re-verified during this cleanup.
