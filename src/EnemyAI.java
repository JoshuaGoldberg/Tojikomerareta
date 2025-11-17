import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EnemyAI {

  IPlayer enemy;
  IGenerator[] generators;
  IPlayer player;
  GameRenderer gameRenderer;
  IGameBoard gameBoard;
  FloatControl volume;
  int downtime = 500;
  int pathStrength = 5;
  int downtimeReducer = 0;
  int lastXDir;
  int lastYDir;
  boolean playerNearGen = false;
  ArrayList<int[]> noXMovement = new ArrayList<>();
  ArrayList<int[]> noYMovement = new ArrayList<>();

  ArrayList<Integer> tempVisitX = new ArrayList<>();
  ArrayList<Integer> tempVisitY = new ArrayList<>();




  public EnemyAI(IPlayer enemy, IGenerator[] generators, IPlayer player, GameRenderer gameRenderer, IGameBoard gameBoard) {
    this.enemy = enemy;
    this.generators = generators;
    this.player = player;
    this.gameRenderer = gameRenderer;
    this.gameBoard = gameBoard;

    Thread thread = new Thread(new EnemyAIRunner(this));
    Thread thread2 = new Thread(new EnemyAIMoveRunner(this));

    thread.start();
    thread2.start();

  }



  public void execute() {

    while(true){

      System.out.println("苦しむ。\n");

      if(player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
        System.out.println("dead.");
        gameRenderer.jumpscare();
      }


    }

  }

  public void executeMovement() {


    Random random = new Random();

    int currX = (int) enemy.getX();
    int currY = (int) enemy.getY();
    lastXDir = (int) enemy.getX() - currX;
    lastYDir = (int) enemy.getY() - currY;

    System.out.println("苦しむ。\n");

    while(true) {

      playerNearGen = false;

      for(IGenerator g : generators) {
        if(Math.abs(player.getY() - g.getY()) <= 1 && Math.abs(player.getX() - g.getX()) <= 1) {
          playerNearGen = true;
        }
      }


      System.out.println(enemy.getX() + " , " + enemy.getY());

      Pathfinder pathfinder = new Pathfinder(gameBoard, (int) enemy.getX(), (int) enemy.getY(), (int) player.getX(), (int) player.getY());
      LinkedPos nextBest = pathfinder.getNextOptimal();

      String soundName = "footsteps.wav";
      AudioInputStream audioInputStream = null;

      try {
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
      } catch (UnsupportedAudioFileException | IOException ex) {
        throw new RuntimeException(ex);
      }
      Clip clip = null;
      try {
        clip = AudioSystem.getClip();
      } catch (LineUnavailableException ex) {
        throw new RuntimeException(ex);
      }
      try {
        clip.open(audioInputStream);
      } catch (LineUnavailableException | IOException ex) {
        throw new RuntimeException(ex);
      }


      if(Math.abs(player.getX() - enemy.getX()) <= 6 && Math.abs(player.getY() - enemy.getY()) <= 6 || playerNearGen) {

        downtime = 300 - downtimeReducer;
        System.out.println("DISCOVERED");

          int xVector = nextBest.getX() - (int) enemy.getX();
          int yVector = nextBest.getY() - (int) enemy.getY();

          enemy.move(xVector, yVector);



//        int index = getIndex();
//
//        if(index == 0) {
//          enemy.move(1, 0);
//        } else if(index == 1) {
//          enemy.move(-1, 0);
//        } else if(index == 2) {
//          enemy.move(0, 1);
//        } else if(index == 3) {
//          enemy.move(0, -1);
//        }
//
//        int dirX = player.getX() - enemy.getX();
//        int dirY = player.getY() - enemy.getY();
//
//        if(currX == enemy.getX() && currY == enemy.getY() && player.getX() != enemy.getX()) {
//          enemy.move(dirX / Math.abs(dirX), 0);
//        }
//
//        if(currX == enemy.getX() && currY == enemy.getY() && player.getY() != enemy.getY()) {
//          System.out.println("trying Y");
//          enemy.move(0, dirY/Math.abs(dirY));
//        }
//
//        walkDirection(random, currX, currY);
        currX = (int) enemy.getX();
        currY = (int) enemy.getY();


        if(downtime > 200) {
          downtimeReducer ++;
        }

      } else {

        downtime = 1000;
        downtimeReducer = 0;
        noXMovement.clear();
        noYMovement.clear();

        int xVector = nextBest.getX() - (int) enemy.getX();
        int yVector = nextBest.getY() - (int) enemy.getY();

        enemy.move(xVector, yVector);

//        if(random.nextInt(pathStrength) != 1 && (lastXDir != 0 || lastYDir != 0)) {
//          enemy.move(lastXDir, lastYDir);
//        }
//
//        walkDirection(random, currX, currY);
//        lastXDir = (int) enemy.getX() - currX;
//        lastYDir = (int) enemy.getY() - currY;
//        currX = (int) enemy.getX();
//        currY = (int) enemy.getY();
      }

      float value = -(Math.abs(((int) enemy.getX() + (int) enemy.getY()) - ((int) player.getX() + (int) player.getY())) * 1F);
      if(value <= -80) {
        value = -80;
      }

      volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      volume.setValue(value);

      clip.start();

      try {
        TimeUnit.MILLISECONDS.sleep(downtime);
      } catch (Exception e){
        System.out.println("oops");
      }
    }

  }

  private int getIndex() {
    double eDist = Math.sqrt(Math.abs(enemy.getX() + 1 - player.getX()) * Math.abs(enemy.getX() + 1 - player.getX()) +
            (Math.abs(enemy.getY() - player.getY()) * Math.abs(enemy.getY() - player.getY())));
    double  wDist = Math.sqrt(Math.abs(enemy.getX() - 1 - player.getX()) * Math.abs(enemy.getX() - 1 - player.getX()) +
            (Math.abs(enemy.getY() - player.getY()) * Math.abs(enemy.getY() - player.getY())));
    double nDist = Math.sqrt(Math.abs(enemy.getX() - player.getX()) * Math.abs(enemy.getX() - player.getX()) +
            (Math.abs(enemy.getY() + 1 - player.getY()) * Math.abs(enemy.getY() + 1 - player.getY())));
    double sDist = Math.sqrt(Math.abs(enemy.getX() - player.getX()) * Math.abs(enemy.getX() - player.getX()) +
            (Math.abs(enemy.getY() - 1 - player.getY()) * Math.abs(enemy.getY() - 1 - player.getY())));

    double[] values = {eDist, wDist, nDist, sDist};

    double max = values[0];
    int index = 0;

    for(int i = 0; i < values.length; i++) {
      if(values[i] <= max) {
        max = values[i];
        index = i;
      }
    }

    return index;
  }

//  private int getIndex() {
//    double eDist = Math.sqrt(Math.abs(enemy.getX() + 1 - player.getX()) * Math.abs(enemy.getX() + 1 - player.getX()) +
//            (Math.abs(enemy.getY() - player.getY()) * Math.abs(enemy.getY() - player.getY())));
//    double  wDist = Math.sqrt(Math.abs(enemy.getX() - 1 - player.getX()) * Math.abs(enemy.getX() - 1 - player.getX()) +
//            (Math.abs(enemy.getY() - player.getY()) * Math.abs(enemy.getY() - player.getY())));
//    double nDist = Math.sqrt(Math.abs(enemy.getX() - player.getX()) * Math.abs(enemy.getX() - player.getX()) +
//            (Math.abs(enemy.getY() + 1 - player.getY()) * Math.abs(enemy.getY() + 1 - player.getY())));
//    double sDist = Math.sqrt(Math.abs(enemy.getX() - player.getX()) * Math.abs(enemy.getX() - player.getX()) +
//            (Math.abs(enemy.getY() - 1 - player.getY()) * Math.abs(enemy.getY() - 1 - player.getY())));
//
//
//    if((tempVisitX.contains(enemy.getX() + 1) && tempVisitY.contains(enemy.getY())) || !gameBoard.getLevel()[enemy.getX() + 1][enemy.getY()].validMove()) {
//      eDist = -1;
//    }
//
//    if((tempVisitX.contains(enemy.getX() - 1) && tempVisitY.contains(enemy.getY())) || !gameBoard.getLevel()[enemy.getX() - 1][enemy.getY()].validMove()) {
//      wDist = -1;
//    }
//
//    if((tempVisitX.contains(enemy.getX()) && tempVisitY.contains(enemy.getY() + 1)) || !gameBoard.getLevel()[enemy.getX()][enemy.getY() + 1].validMove()) {
//      nDist = -1;
//    }
//
//    if((tempVisitX.contains(enemy.getX()) && tempVisitY.contains(enemy.getY() - 1)) || !gameBoard.getLevel()[enemy.getX()][enemy.getY() - 1].validMove()) {
//      sDist = -1;
//    }
//
//    double[] values = {eDist, wDist, nDist, sDist};
//
//    double max = 999999999;
//    int index = 0;
//
//    if (eDist == -1 && wDist == -1 && nDist == -1 && sDist == -1) {
//      tempVisitX.clear();
//      tempVisitY.clear();
//    }
//
//    for(int i = 0; i < values.length; i++) {
//      if(values[i] <= max && values[i] != -1) {
//        max = values[i];
//        index = i;
//      }
//    }
//
//    return index;
//  }

  private void walkDirection(Random random, int currX, int currY) {
    while (currX == enemy.getX() && currY == enemy.getY()) {
      int direction = random.nextInt(4) + 1;

      if (direction == 1) {
        enemy.move(1, 0);
      } else if (direction == 2) {
        enemy.move(-1, 0);
      } else if (direction == 3) {
        enemy.move(0, 1);
      } else {
        enemy.move(0, -1);
      }
    }
  }

}
