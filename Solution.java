import java.util.*;

class Solution {
    public int slidingPuzzle(int[][] board) {
        // Convert the 2D board into a single string representation
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                start.append(num);
            }
        }
        
        // The target configuration
        String target = "123450";
        
        // Directions for moving 0 (up, down, left, right)
        int[][] directions = {
            {1, 3},    // Moves for index 0
            {0, 2, 4}, // Moves for index 1
            {1, 5},    // Moves for index 2
            {0, 4},    // Moves for index 3
            {1, 3, 5}, // Moves for index 4
            {2, 4}     // Moves for index 5
        };
        
        // Queue for BFS
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        // Initialize BFS
        queue.add(start.toString());
        visited.add(start.toString());
        
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all states at the current depth
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                // Check if solved
                if (current.equals(target)) {
                    return moves;
                }
                
                // Find the position of '0'
                int zeroPos = current.indexOf('0');
                
                // Explore all valid moves for '0'
                for (int dir : directions[zeroPos]) {
                    // Create a new configuration by swapping '0' with an adjacent tile
                    StringBuilder nextState = new StringBuilder(current);
                    nextState.setCharAt(zeroPos, nextState.charAt(dir));
                    nextState.setCharAt(dir, '0');
                    
                    String nextConfig = nextState.toString();
                    
                    // If not visited, add to the queue
                    if (!visited.contains(nextConfig)) {
                        visited.add(nextConfig);
                        queue.add(nextConfig);
                    }
                }
            }
            
            // Increment moves after processing all states at the current depth
            moves++;
        }
        
        // If we exhaust the queue without finding the solution, return -1
        return -1;
    }
}
