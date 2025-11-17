public class GeneratorRunnerPing implements Runnable {
  IGenerator generator;

  public GeneratorRunnerPing(IGenerator generator) {
    this.generator = generator;
  }

  public void run() {
    generator.ping();
  }
}
