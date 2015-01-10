package game;

/**
 * Created by David Recuenco on 30/12/2014.
 */

import no.geosoft.cc.geometry.Geometry;
import no.geosoft.cc.graphics.*;

import java.awt.*;

public class GameBoard extends GObject {

    private Game game;

    private GSegment board;
    private GSegment[] grid;
    private GSegment player;
    private GStyle[] pieceStyle;

    public GameBoard(Game game) {
        this.game = game;
        board = new GSegment();
        GStyle boardStyle = new GStyle();
        boardStyle.setBackgroundColor(new Color(255, 255, 255));
        board.setStyle(boardStyle);
        addSegment(board);

        GStyle gridStyle = new GStyle();
        gridStyle.setForegroundColor(new Color(0, 0, 0));
        gridStyle.setLineWidth(2);
        grid = new GSegment[(game.getSize() + 1) * 2];

        for (int i = 0; i < grid.length; i++) {
            grid[i] = new GSegment();
            grid[i].setStyle(gridStyle);
            addSegment(grid[i]);
        }

        pieceStyle = new GStyle[3];
        pieceStyle[0] = new GStyle();
        pieceStyle[0].setForegroundColor(new Color(255, 0, 0));
        pieceStyle[0].setBackgroundColor(new Color(255, 0, 0));

        pieceStyle[1] = new GStyle();
        pieceStyle[1].setForegroundColor(new Color(0, 255, 0));
        pieceStyle[1].setBackgroundColor(new Color(0, 255, 0));

        pieceStyle[2] = new GStyle();
        pieceStyle[2].setForegroundColor(new Color(0, 0, 0));
        pieceStyle[2].setBackgroundColor(new Color(0, 0, 0));
    }

    public void removePlayer() {
        removeSegment(player);
    }
	
	public void drawBase(){
        
	}

    public void draw() {
        int size = game.getSize();

        // Board
        board.setGeometryXy(new double[]{1.0, 1.0,
                size + 1.0, 1.0,
                size + 1.0, size + 1.0,
                1.0, size + 1.0,
                1.0, 1.0});

        // Grid lines
        for (int i = 0; i <= size; i++) {
            grid[i * 2 + 0].setGeometry(1.0, i + 1.0, size + 1.0, i + 1.0);
            grid[i * 2 + 1].setGeometry(i + 1.0, 1.0, i + 1.0, size + 1.0);
        }

        //Pieces
        int[][] state = game.getState();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == Game.PLAYER) {
                    double y = (i / size) + 1.5 + (j % size);
                    double x = (i % size) + 1.5 + (j / size);

                    int[] xy = getTransformer().worldToDevice(x, y);
                    player = new GSegment();
                    addSegment(player);

                    player.setStyle(pieceStyle[Game.PLAYER - 1]);
                    player.setGeometry(Geometry.createCircle(xy[0], xy[1], 15));
                } else if (state[i][j] == Game.TARGET) {
                    GSegment piece = new GSegment();
                    addSegment(piece);

                    piece.setStyle(pieceStyle[Game.TARGET - 1]);
                    piece.setGeometryXy(new double[]{i + 1.05, j + 1.05,
                            i + 1.95, j + 1.05,
                            i + 1.95, j + 1.95,
                            i + 1.05, j + 1.95,
                            i + 1.05, j + 1.05});
                } else if (state[i][j] == game.WALL) {
                    GSegment piece = new GSegment();
                    addSegment(piece);

                    piece.setStyle(pieceStyle[Game.WALL - 1]);
                    piece.setGeometryXy(new double[]{i + 1.05, j + 1.05,
                            i + 1.95, j + 1.05,
                            i + 1.95, j + 1.95,
                            i + 1.05, j + 1.95,
                            i + 1.05, j + 1.05});
                }
            }
        }
    }
}