public interface LinkedPos {

  public int getX();
  public int getY();
  public LinkedPos prev();
  public boolean equal(LinkedPos pos);
  public LinkedPos getFirst(LinkedPos next);


}
