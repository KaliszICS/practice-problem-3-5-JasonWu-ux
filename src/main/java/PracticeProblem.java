import java.util.LinkedList;
import java.util.Queue;

public class PracticeProblem {

	public static void main(String args[]) {

	}
	    public static int searchMazeMoves(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        int startRow = -1, startCol = -1;

        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c].equals("S")) {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol, 0}); 
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], moves = curr[2];

            if (maze[r][c].equals("F")) return moves;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                    && !visited[nr][nc]
                    && !maze[nr][nc].equals("#")) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, moves + 1});
                }
            }
        }

        return -1; 
    }
	  public static int noOfPaths(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        int startRow = -1, startCol = -1;
        int finishRow = -1, finishCol = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c].equals("S")) {
                    startRow = r;
                    startCol = c;
                } else if (maze[r][c].equals("F")) {
                    finishRow = r;
                    finishCol = c;
                }
            }
        }

        int minDistance = searchMazeMoves(maze);
        if (minDistance == -1) return 0;

        boolean[][] visited = new boolean[rows][cols];
        return dfs(maze, startRow, startCol, finishRow, finishCol, visited, 0, minDistance);
    }

    private static int dfs(String[][] maze, int r, int c, int finishRow, int finishCol, boolean[][] visited, int moves, int minDistance) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length) return 0;
        if (visited[r][c] || maze[r][c].equals("#")) return 0;
        if (moves > minDistance) return 0;

        if (maze[r][c].equals("F")) {
            return (moves == minDistance) ? 1 : 0;
        }

        visited[r][c] = true; 

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int paths = 0;

        for (int[] dir : directions) {
            paths += dfs(maze, r + dir[0], c + dir[1], finishRow, finishCol, visited, moves + 1, minDistance);
        }

        visited[r][c] = false; 

        return paths;
    }
}


