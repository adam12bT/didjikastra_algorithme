package didjikastra_algorithme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
	node[][] Node;
	 private int width;
	    private int height;
	    private boolean[][] visited;
	    private int[][] maze;
	    private Random random;
	    public MazeGenerator(int width, int height) {
	        this.width = width;
	        this.height = height;
	        random = new Random();

	        this.visited = new boolean[width][height];
	        this.maze = new int[width][height];
	        this.Node=new node[width][height];
	    }


	    public void generateMaze() {
	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {
	                maze[i][j] = 1; // Initialize maze with walls
	            }
	        }

	        Random rand = new Random();
	        int startX = rand.nextInt(width);
	        int startY = rand.nextInt(height);

	        depthFirstSearch(startX, startY);
	        setStartAndFinish();
	    }
	    private void depthFirstSearch(int x, int y) {
	        maze[y][x] = 0; // Set current cell as path

	        int[] directions = { 1, 2, 3, 4 };
	        shuffleArray(directions);

	        for (int direction : directions) {
	            int nextX = x;
	            int nextY = y;

	            switch (direction) {
	                case 1: // Up
	                    nextY -= 2;
	                    break;
	                case 2: // Down
	                    nextY += 2;
	                    break;
	                case 3: // Left
	                    nextX -= 2;
	                    break;
	                case 4: // Right
	                    nextX += 2;
	                    break;
	            }

	            if (nextX > 0 && nextX < width - 1 && nextY > 0 && nextY < height - 1 && maze[nextY][nextX] == 1) {
	                int wallX = x + (nextX - x) / 2;
	                int wallY = y + (nextY - y) / 2;
	                maze[wallY][wallX] = 0; // Break wall between cells
	                depthFirstSearch(nextX, nextY);
	            }
	        }
	    }

	    private void setStartAndFinish() {
	        // Set start point at top-left corner
	        maze[1][0] = 0;

	        // Set finish point at bottom-right corner
	        maze[height - 2][width - 1] = 0;
	    }

	    private void shuffleArray(int[] array) {
	        Random rand = new Random();
	        for (int i = array.length - 1; i > 0; i--) {
	            int index = rand.nextInt(i + 1);
	            int temp = array[index];
	            array[index] = array[i];
	            array[i] = temp;
	        }
	    }


	    public void displayMaze() {
	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {

	                if (maze[j][i] == 1) {
	                    Node[j][i] = new node(j, i); // Corrected indices for Node array
	                    Node[j][i].setasolide();
	                    //System.out.println( Node[j][i]);
	                } else {
	                    Node[j][i] = new node(j, i); // Corrected indices for Node array
	                    // Assuming an appropriate method to set open passages visually
	                    // Node[j][i].setAsOpen();
	                }
	            }
	        }
	    }

 
	    
	    
	    
}


	    

	
		   
	   
	    
	    

