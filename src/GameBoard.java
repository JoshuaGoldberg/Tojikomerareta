public class GameBoard implements IGameBoard {

  ITile[][] wallTiles;

  public GameBoard(ITile[][] wallTiles) {
    this.wallTiles = wallTiles;
  }

  @Override
  public boolean validPosition(int row, int col) {
    return wallTiles[col][row].validMove();
  }

  @Override
  public boolean validPositionPathFind(int row, int col) {
    return wallTiles[row][col].validMove();
  }

  @Override
  public ITile[][] getLevel() {
    return wallTiles;
  }
}
