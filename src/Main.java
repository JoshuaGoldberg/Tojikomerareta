import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {


    ITile X = new WallTile();
    ITile O = new EmptyTile();


    ITile[][] demoTiles = {
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, O, O, X, X, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, O, X, O, X, X, X, X, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, O, O, X, X, O, O, O, X, X, X, X, O, X, X, X, X, X, X, X, O, O, O, X, X, X, X, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, O, O, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, O, O, O, X, X, X, O, O, O, X, X, X, X, X, X, O, X, O, X, X, O, O, O, O, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, X, X, X, X, O, O, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, O, X, X, O, X, X, X, X, O, O, O, X, X, X, X, X, X, O, O, O, X, X, O, X, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, O, X, O, X, X, O, X, X, X, X, O, O, O, X, X, X, X, X, X, O, X, O, X, X, O, X, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, O, O, O, O, X, X, X, X, X, X, X, X, X},
            {X, O, O, O, O, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, O, O, O, O, X, O, O, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, O, X, O, O, O, X, X, X, X, X, X, O, X, X, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, O, O, O, O, O, O, O, O, O, O, X, O, O, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, O, O, O, X, O, O, O, X, X, X, X, X, X, O, X, X, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, O, X, X, X, X, X, O, O, O, O, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, X, X, X, X, O, O, O, X, X, O, O, X, O, X, O, X, X, X, X, X, X, X, X, O, O, O, O, O, X, X, X, X, X, O, O, O, O, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, X, X, X, X, O, X, X, X, X, O, O, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, X, X, X, O, O, X, X, X, X, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, O, O, O, O, O, X, X, O, O, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, O, O, X, X, X, O, O, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, O, X, X, X, X, X, X, O, O, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, O, O, O, O, O, O, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, O, O, X, X, X, X},
            {X, X, X, X, O, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, O, X, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, O, O, O, X, X, X, X, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
    };

    ITile[][] demoTiles2 = {
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, X, X, X, X, X, O, O, X, X, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, O, O, O, O, X, X, X, X, O, X, O, O, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, O, O, X, X, X, X, O, O, O, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, O, X, X, X, X, X, X, X, X, X, O, O, O, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, O, O, O, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, O, O, O, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
    };

    IGameBoard board = new GameBoard(demoTiles);
    IPlayer player = new Player(board, 1, 1);
    //4, 3

    //6, 12
    IPlayer enemy = new Enemy(board, 49 , 6);
    IGenerator G = new Generator(1, 1, player);
    IGenerator G2 = new Generator(6, 12, player);
    IGenerator G3 = new Generator(49, 6, player);
    IGenerator G4 = new Generator(48, 8, player);

    IGenerator[] generators = new IGenerator[] {G, G2, G3, G4};

    GameRenderer gameDemo = new GameRenderer(board, player, enemy, generators);
    EnemyAI eAI = new EnemyAI(enemy, generators, player, gameDemo, board);
    PlayerController pc = new PlayerController(player, gameDemo);
  }
}