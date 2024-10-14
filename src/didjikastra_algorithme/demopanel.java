package didjikastra_algorithme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.Timer;
import java.util.TimerTask;

public class demopanel extends JPanel {
    private static final int INF = Integer.MAX_VALUE;
    private Timer algorithmTimer;
    private int delay =500000;
    final int maxcol = 60;
    final int maxrow = 60;
    MazeGenerator t = new MazeGenerator(maxcol, maxrow);
    final int nodesize = 12;
    final int screenwidth = nodesize * maxrow;
    final int screenheight = nodesize * maxcol;
    node[][] Node = new node[maxcol][maxrow];
    node startnode, goalnode, currentnode;
    ArrayList<node> openlist = new ArrayList<>();
    ArrayList<node> checkedlist = new ArrayList<>();
    boolean goalreached = false;
    int step = 0;

    public demopanel() {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxrow, maxcol));
        t.generateMaze();
        t.displayMaze(); 
        initializeNodes();
        // Add nodes to the panel
        for (int i = 0; i < maxrow; i++) {
            for (int j = 0; j < maxcol; j++) {
                this.add(t.Node[j][i]); // Add nodes to the panel
            }
        }

        // Repaint the panel to show the maze
        this.revalidate();
        this.repaint();
    
        setstartingnode(2, 1);
        setgoalnode(maxrow - 3, maxcol-2);
    

        // Schedule the algorithm timer on the EDT
        dijikastra();
        updatePanel();

        
        
    }
        
        
    
    public void updatePanel() {
        this.revalidate();
        this.repaint();
    }

    public void initializeNodes() {
        // Clear the panel before adding nodes
       // this.removeAll();

        for (int i = 0; i < maxrow; i++) {
            for (int j = 0; j < maxcol; j++) {
                this.add(t.Node[i][j]);
                Node[i][j] = t.Node[i][j];
            }
        }
        
        // Repaint the panel to show the maze
    
    }

    public void setstartingnode(int row, int col) {
        Node[col][row].setastar();
        startnode = Node[col+1][row+1];
        currentnode = startnode;
    }

    public void setgoalnode(int row, int col) {
        Node[col][row].setagoal();
        goalnode = Node[col-1][row-1];
    }

	
	public void dijikastra() {
	    // Initialization
	    int[][] distance = new int[maxcol][maxrow];
	    boolean[][] visited = new boolean[maxcol][maxrow];
	    int[] dr = {-1, 0, 1, 0}; // For traversing in four directions: up, right, down, left
	    int[] dc = {0, 1, 0, -1};
	    
	    // Initialize distances to infinity for all nodes and mark all nodes as unvisited
	    for (int i = 0; i < maxcol; i++) {
	        for (int j = 0; j < maxrow; j++) {
	            distance[i][j] = INF;
	            visited[i][j] = false;
	        }
	    }
        int delayInSeconds = 5; // Set the delay in seconds

        // Create a Timer object
	    // Set distance of start node to 0 and add it to the open list
	    distance[startnode.col][startnode.row] = 0;
	    openlist.add(startnode);

    	TimerTask task = new TimerTask() {
            @Override
            public void run() {	    
            	while (!openlist.isEmpty()) {

	                // Code to be executed after the delay
	                System.out.println("Delayed task completed!");
	     
	        node current = openlist.get(0);
	        current.setvisited() ;
	        for (node n : openlist) {
	            if (distance[n.col][n.row] < distance[current.col][current.row]) {
	                current = n;
	            }
	        }
	        openlist.remove(current);
	        
	        visited[current.col][current.row] = true;
	        // Check if the goal node is reached
	        if (current == goalnode) {
	        	goalreached = true;
	            break;
	        }

	        // Explore neighbors of the current node
	        for (int i = 0; i < 4; i++) {
	            int newCol = current.col + dc[i];
	            int newRow = current.row + dr[i];

	            // Check if the new position is within bounds
	            if (newCol >= 0 && newCol < maxcol && newRow >= 0 && newRow < maxrow) {
	                node neighbor = Node[newCol][newRow];

	                // Skip solid nodes and already visited nodes
	                if (!neighbor.solide && !visited[newCol][newRow]) {
	                    int newDistance = distance[current.col][current.row] + 1; // Assuming uniform cost for all edges

	                    // Update distance if a shorter path is found
	                    if (newDistance < distance[newCol][newRow]) {
	                        distance[newCol][newRow] = newDistance;
	                        neighbor.parent = current; // Store the parent node for path reconstruction
	                        openlist.add(neighbor);
	                    }
	                }
	            }
	        }
	    }
 


	    // Path reconstruction if the goal is reached
	    if (goalreached) {
	        node current = goalnode;
	        while (current != startnode) {
	            current.setaspath(); // Mark the node as part of the shortest path
	            current = current.parent; // Move to the parent node
				current.setaspath();

	        }
	        startnode.setastar(); 
	        goalnode.setagoal();
	    }
            }
        };
        Timer timer = new Timer();

        timer.schedule(task, delayInSeconds * 0);

	}
  
	
	
	
	

}
