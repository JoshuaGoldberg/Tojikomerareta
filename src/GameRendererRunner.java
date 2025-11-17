import java.util.concurrent.TimeUnit;

public class GameRendererRunner implements Runnable {
  GameRenderer gameRenderer;

  public GameRendererRunner(GameRenderer gameRenderer) {
    this.gameRenderer = gameRenderer;
  }

  public void run() {
    gameRenderer.run();
  }
}
