import java.awt.image.BufferedImage;

public class Enemy implements IPlayer {
  private final IGameBoard gameBoard;
  private int xPos;
  private int yPos;

  public Enemy(IGameBoard gameBoard, int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.gameBoard = gameBoard;
  }

  @Override
  public void move(int x, int y) {

    if(gameBoard.validPosition(xPos + x, yPos + y)) {
      xPos = xPos + x;
      yPos = yPos + y;
    }

  }

  @Override
  public double getX() {
    return xPos;
  }

  @Override
  public double getY() {
    return yPos;
  }

  @Override
  public void setSprite(int direction) {

  }

  @Override
  public BufferedImage getSprite() {
    return null;
  }


}
