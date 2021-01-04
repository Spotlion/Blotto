/*
Leon Deng
12/3/2020
Blotto is a warmup competition for MIT's Pokerbots class.
Rules:
There are 9 piles of gold, with 1-9 pieces of gold in them. Every player has 1000 soldiers to assign to each of the 9 piles.
For each pile, the player with the more soldiers assigned to it wins that pile.
The players compete 1v1 in a round-robin format. Whomever has the most gold at the end wins.

The goal of this program is to simulate this process and hopefully gain insight into what strategy will do the best.
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Blotto {

    public static class Player { // Simulates a distribution of soldiers
        private int[] soldiers;

        public Player(int[] soldiers) {
            this.soldiers = soldiers;
        }

        public int compare(Player p) { // Returns the gold gained from competing against another player
            int output = 0;
            for (int i = 0; i < 9; i++) {
                if (soldiers[i] > p.getSoldiers()[i]) {
                    output += i + 1;
                }
            }
            return output;
        }

        public boolean equals(Player p) { // Returns if two players are equal
            for (int i = 0; i < 9; i++) {
                if (soldiers[i] != p.getSoldiers()[i]) {
                    return false;
                }
            }
            return true;
        }

        public int[] getSoldiers() { // Returns the player's soldier distribution
            return soldiers;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Players.txt");
        Scanner s = new Scanner(file); //Players.txt has the player distributions

        int count = 0;
        while(s.hasNextLine()){ //Assigns the number of lines in the file to the variable count
            count++;
            s.nextLine();
        }
        s.close();
        
        int[][] pool = new int[count][10]; //Stores all the player distributions in a 2d array
        String distribution;

        s = new Scanner(file); //This reads all the soldier distributions from Player.txt and puts them into the 2d array "pool"
        count = 0;
        String[] temp = new String[9];
        while(s.hasNextLine()){
            distribution = s.nextLine();
            temp = distribution.split(" ");
            for(int i = 0; i < 9; i++){
                pool[count][i] = Integer.parseInt(temp[i]);
            }
            count++;
        }
        s.close();

        Player p; //Simulates all the comparisons
        for(int i = 0; i < pool.length; i++){
            for(int j = 0; j < pool.length; j++){
                if(i != j){
                    p = new Player(pool[i]);
                    pool[i][9] += p.compare(new Player(pool[j]));
                }
            }
        }

        
        for(int i = 0; i < pool.length; i++){  //This prints the results
            for(int j = 0; j < pool[0].length; j++){
                System.out.print(pool[i][j] + "\t");
            }
            System.out.println();
        }
        
    }
}