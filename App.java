import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException{
        int[][] maze = readLabyrinth();
        Astar solver = new Astar(maze);
        solver.solveMaze();
    }

    public static int[][] readLabyrinth() throws FileNotFoundException{
        String directoryPath = new File(".").getAbsolutePath();
        String labyrinthFilePath = directoryPath.substring(0, directoryPath.length()-2) + "/maze.txt".trim();
        File file = new File(labyrinthFilePath);
        Scanner in = new Scanner(file);
        int labyrinthSize = in.nextInt();
        int[][] labyrinth = new int[labyrinthSize][labyrinthSize];
        int lineNumber = 0;
        while(in.hasNext()){

            String line = in.nextLine();

            for(int i = 0; i < line.length(); i += 2) {
                labyrinth[lineNumber-1][i/2] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            lineNumber++;
        }
        in.close();
        return labyrinth;
    }
}
