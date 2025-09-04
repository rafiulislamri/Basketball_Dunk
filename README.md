## Basketball Dunk - Detailed Project Brief

**Project Files and Responsibilities**
. Core Package
  - GameFrame.java (Singleton)
1. Creates the main game window (600x600).
2. Implemented with Singleton Pattern so only one window instance exists during the whole game.

  GamePanel.java
1. Heart of the game.
2. Contains rendering (paint), updating, basket movement, ball updates, input handling, collision detection, and HUD.
3. Calls the Chain of Responsibility (collision → cleanup → game over).

  GameContext.java
1. Context object passed through the chain handlers.
2. Holds active balls, basket, and score system.

. Objects Package
  - GameFrame.java (Singleton)
  - GameObject.java → Interface for drawable/updateable objects.
  - Shootable.java → Interface for ball-like objects (x, y, dx, dy, radius, bounds).
  - Ball.java → The basketball itself. Implements Shootable.
  1. Supports movement, rendering, collision bounds.
  2. Implements Prototype (cloneable) for efficient duplication.
  - BallBuilder.java (Builder Pattern)
  1. Builds ball step by step (x, y, radius, speed). [Why: makes ball creation clean & flexible.]
  - BallPrototype.java (Prototype Pattern)
  1. Stores a “template ball” and clones it when new balls are needed. [Why: faster and avoids repetitive construction.]

. Patterns Package
  - Observer Pattern
    1. Observer.java → Observer interface.
    2. Subject.java → Subject interface.
    3. ScoreSubject.java → Holds score & balls left, notifies observers.
    4. ScoreBoardPanel.java → HUD panel that auto-updates whenever score/balls change.
    5. Why: Score HUD updates automatically when data changes.
  - Adapter Pattern
    1. InputAdapter.java → Maps keyboard (SPACE) into shoot() method call. [Why: decouples raw key handling from game logic.]
  - Decorator Pattern
    1. BallDecorator.java → Abstract wrapper around a Shootable.
    2. GlowBallDecorator.java → Adds a glowing effect for the last ball.
    3. Why: adds special effects without modifying Ball.java.
  - Chain of Responsibility
    1. Handler.java → Abstract handler.
    2. CollisionHandler.java → Detects ball-basket collision (adds score + refunds ball).
    3. CleanupHandler.java → Removes inactive balls from memory.
    4. GameOverHandler.java → Declares game over when no balls left.
    5. Why: splits different responsibilities into modular handlers.

. Logic Flow
  - Game Start → GameMain.main()
  1. Creates GameFrame (Singleton).
  2. Attaches GamePanel.

  - GamePanel Constructor
    1. Builds prototype ball using Builder.
    2. Sets up Observer (HUD auto-updates).
    3. Creates Chain of Responsibility.
    4. Sets up Adapter for SPACE input.
    5. Starts Timer loop (game ticks every 16ms).

  - Game Loop (Timer → tick())
    1. Basket updates its left-right motion.
    2. Balls update their positions.
    3. Chain executes:
       - CollisionHandler checks if any ball enters rim → add score.
       - CleanupHandler removes dead balls.
       - GameOverHandler checks if all balls used → stop timer.

  - User Action (Press SPACE)
    1. InputAdapter triggers → shoot().
    2. Balls left decremented.
    3. A new ball is cloned from Prototype template.
    4. If last ball → decorated with GlowBallDecorator.

- Observer Pattern → Scoreboard instantly reflects score and balls left.

. Logic Flow
  - GameMain → starts GameFrame.
  - GameFrame → attaches GamePanel.
  - GamePanel → initializes basket, ball prototype, score system, HUD, input.
  - Timer Loop (GamePanel) → calls update & repaint continuously.
  - User Input → Adapter → shootBall() → adds new ball.
  - Chain runs each frame (collision, cleanup, gameover).
  - Observer → updates HUD live.
  - End → GameOverHandler stops timer + HUD shows “Game Over”.

. Game User Manual
  - Start the game → window opens.
  - Basket moves left to right at top of screen.
  - Player has 5 balls total.
  - Press SPACE to shoot a ball upwards.
  - If the ball lands inside basket → +1 score and ball refunded.
  - If it misses → ball is lost.
  - Game ends when all balls are gone.
  - HUD shows live score + balls left.


## Folder Structure

<img width="435" height="619" alt="image" src="https://github.com/user-attachments/assets/348b633f-8d2b-44ca-a24d-233b37c5ef61" />


## Terminal Command

1. javac -d bin src/main/GameMain.java
2. java -cp bin main.GameMain

