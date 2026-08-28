
import java.util.Scanner;

public class RockPaperScissor{

    static String playRound(String player, String computer) {
        if (player.equals(computer))
            return "Draw";
        if ((player.equals("Rock") && computer.equals("Scissors")) ||
            (player.equals("Paper") && computer.equals("Rock")) ||
            (player.equals("Scissors") && computer.equals("Paper")))
            return "Win";
        return "Loss";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] moves = {"Rock", "Paper", "Scissors"};

        int win = 0, loss = 0, draw = 0;

        System.out.print("Enter number of rounds: ");
        int n = sc.nextInt();

        // Arrays to store round details
        String[] playerMoves = new String[n];
        String[] computerMoves = new String[n];
        String[] results = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter your move: ");
            String player = sc.next();

            String computer = moves[(int)(Math.random() * 3)];

            String result = playRound(player, computer);

            playerMoves[i] = player;
            computerMoves[i] = computer;
            results[i] = result;

            if (result.equals("Win"))
                win++;
            else if (result.equals("Loss"))
                loss++;
            else
                draw++;
        }

        // Tabular Output
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Round\tPlayer\t\tComputer\tResult");
        System.out.println("-------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" +
                    playerMoves[i] + "\t\t" +
                    computerMoves[i] + "\t\t" +
                    results[i]);
        }

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Win: " + win);
        System.out.println("Loss: " + loss);
        System.out.println("Draw: " + draw);
        System.out.printf("Win Percentage: %.2f%%\n", (win * 100.0 / n));

        sc.close();
    }
}