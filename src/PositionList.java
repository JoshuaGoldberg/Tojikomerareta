public class PositionList implements LinkedPos {

  int xPos;
  int yPos;
  LinkedPos prev;

  public PositionList(int xPos, int yPos, LinkedPos prev) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.prev = prev;
  }


  @Override
  public int getX() {
    return xPos;
  }

  @Override
  public int getY() {
    return yPos;
  }

  @Override
  public LinkedPos prev() {
    return prev;
  }

  @Override
  public boolean equal(LinkedPos pos) {
    return this.xPos == pos.getX() && this.yPos == pos.getY();
  }

  @Override
  public LinkedPos getFirst(LinkedPos next) {

    if(this.prev == null) {
      return next;
    } else {
      return this.prev.getFirst(this);
    }
    }
}
