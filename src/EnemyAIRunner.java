public class EnemyAIRunner implements Runnable{

  EnemyAI eAI;

  public EnemyAIRunner(EnemyAI eAI) {
    this.eAI = eAI;
  }

  @Override
  public void run() {
    eAI.execute();
  }
}
