package game;

/**
 * Created by David Recuenco on 30/12/2014.
 */
public class Game {
    private int size;
    private int[][] state;
    private int[] playerPos;
    private int[] targetPos;
    private final int X = 0;
    private final int Y = 1;
    public final static int EMPTY = 0;
    public final static int PLAYER = 1;
    public final static int TARGET = 2;
    public final static int WALL = 3;
    private boolean endGame = false;

    public Game(int size) {
        this.size = size;

        state = new int[size][size];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                state[i][j] = EMPTY;
            }
        }

        playerPos = new int[2];
        playerPos[X] = 0;
        playerPos[Y] = 0;
        targetPos = new int[2];
        targetPos[X] = size - 1;
        targetPos[Y] = size - 1;
        state[playerPos[X]][playerPos[Y]] = PLAYER;
        state[targetPos[X]][targetPos[Y]] = TARGET;

        state = setWalls(state);

    }

    private int[][] setWalls(int[][] state){
        state[0][1] = WALL;
		state[0][2] = WALL;
        state[1][1] = WALL;
        state[1][2] = WALL;
		state[1][4] = WALL;
        state[1][5] = WALL;
        state[1][6] = WALL;
        state[1][7] = WALL;
        state[1][8] = WALL;
        state[1][9] = WALL;
        state[1][10] = WALL;
        state[2][1] = WALL;
        state[2][2] = WALL;
		state[2][4] = WALL;
        state[2][5] = WALL;
        state[2][6] = WALL;
        state[2][7] = WALL;
        state[2][8] = WALL;
        state[2][9] = WALL;
        state[2][10] = WALL;
        state[3][4] = WALL;
        state[3][5] = WALL;
        state[4][0] = WALL;
        state[4][1] = WALL;
		state[4][2] = WALL;
		state[4][3] = WALL;
        state[4][4] = WALL;
        state[4][5] = WALL;
        state[4][11] = WALL;
        state[4][10] = WALL;
        state[4][9] = WALL;
        state[4][8] = WALL;
        state[4][7] = WALL;
		state[5][0] = WALL;
		state[5][1] = WALL;
		state[5][2] = WALL;
		state[5][3] = WALL;
        state[5][4] = WALL;
        state[5][5] = WALL;
        state[5][11] = WALL;
        state[5][10] = WALL;
        state[5][9] = WALL;
        state[5][8] = WALL;
        state[5][7] = WALL;
		
        return state;
    }

    public int getSize() {
        return size;
    }

    public int getPlayerX(){
        return playerPos[X];
    }

    public int getPlayerY(){
        return playerPos[Y];
    }

    public int[][] getState() {
        return state;
    }

    public boolean isLegalMove(int i, int j) {
        try {
            return (state[i][j] == EMPTY || state[i][j] == TARGET) &&
                    (
                            (i == playerPos[X] + 1) ||
                                    (i == playerPos[X] - 1) ||
                                    (i == playerPos[X])
                    ) &&
                    (
                            (j == playerPos[Y] + 1) ||
                                    (j == playerPos[Y] - 1) ||
                                    (j == playerPos[Y])
                    );
        } catch (Exception e) {
            return false;
        }
    }

    public void move(int i, int j) {
        if (isLegalMove(i, j)) {
            state[playerPos[X]][playerPos[Y]] = EMPTY;
            playerPos[Y] = j;
            playerPos[X] = i;
            state[i][j] = PLAYER;
            checkSolution();
        }
    }

    public boolean isGameEnded() {
        return endGame;
    }

    private void checkSolution() {
        if ((playerPos[X] == targetPos[X]) && (playerPos[Y] == targetPos[Y])) {
            try {
                System.out.println("--End of game detected.");
                endGame = true;
                showEndGame();

            } catch (Throwable throwable) {
                throwable.printStackTrace();
                System.exit(-1);
            }
        }
    }

    private void showEndGame() {

    }
}
