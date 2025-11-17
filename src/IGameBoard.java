public interface IGameBoard {

  boolean validPosition(int row, int col);

  boolean validPositionPathFind(int row, int col);


  ITile[][] getLevel();

}
