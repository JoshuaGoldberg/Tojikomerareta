public class EnemyAIMoveRunner implements Runnable{

  EnemyAI eAI;

  public EnemyAIMoveRunner(EnemyAI eAI) {
    this.eAI = eAI;
  }

  @Override
  public void run() {
    eAI.executeMovement();
  }
}
