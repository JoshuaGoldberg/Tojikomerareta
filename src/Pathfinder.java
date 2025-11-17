import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinder {

  IGameBoard gameBoard;
  int startX, startY, endX, endY;

  public Pathfinder(IGameBoard gameBoard, int startX, int startY, int endX, int endY) {
    this.gameBoard = gameBoard;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  private boolean containsPos(ArrayList<LinkedPos> list, LinkedPos pos) {
    for (LinkedPos p : list) {
      if(p.equal(pos)) {
        return true;
      }
    }

    return false;
  }


  public LinkedPos getNextOptimal() {

    ArrayList<LinkedPos> visited = new ArrayList<>();
    Queue<LinkedPos> queue = new LinkedList<>();

    visited.add(new PositionList(startX, startY, null));
    queue.add(new PositionList(startX, startY, null));

    while (!queue.isEmpty()) {
      LinkedPos current = queue.poll();

      int x = current.getX();
      int y = current.getY();

      if (x == endX && y == endY) {
        return current.getFirst(current);
      }

      if(gameBoard.validPosition(x + 1, y) && !containsPos(visited, new PositionList(x + 1, y, null))) {
        queue.add(new PositionList(x + 1, y, current));
        visited.add(new PositionList(x + 1, y, current));
      }

      if(gameBoard.validPosition(x - 1, y) && !containsPos(visited, new PositionList(x - 1, y, null))) {
        queue.add(new PositionList(x - 1, y, current));
        visited.add(new PositionList(x - 1, y, current));
      }

      if(gameBoard.validPosition(x, y + 1) && !containsPos(visited, new PositionList(x, y + 1, null))) {
        queue.add(new PositionList(x, y + 1, current));
        visited.add(new PositionList(x, y + 1, current));
      }

      if(gameBoard.validPosition(x, y  - 1) && !containsPos(visited, new PositionList(x, y - 1, null))) {
        queue.add(new PositionList(x, y - 1, current));
        visited.add(new PositionList(x, y - 1, current));
      }

    }

    System.out.println("error...");
    throw new IllegalStateException("no path found!");
  }

}
