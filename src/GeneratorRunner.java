public class GeneratorRunner implements Runnable {

  IGenerator g;
  public GeneratorRunner(IGenerator g) {
    this.g = g;
  }

  public void run() {
    g.execute();
  }

}
