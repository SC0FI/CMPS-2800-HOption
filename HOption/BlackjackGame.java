package HOption;

import java.util.Scanner;

public class BlackjackGame {
    private final Deck deck;
    private final Player player;
    private final Player dealer;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Player();
    }

    public void startGame() {
        dealInitialCards();

        System.out.println("Welcome to Blackjack!");

        //Players Turn
        playerTurn();

        //Dealers Turn
        dealerTurn();

        //determine winner
        determineWinner();
    }

    private void dealInitialCards() {
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());

        System.out.println("Your hand: " + player.getHand());
        System.out.println("Dealers hand: " + dealer.getHand().split(",")[0] + ", [hidden]");
    }

    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerTurn = true;

        while (isPlayerTurn && player.getScore() < 21) {
            System.out.println("Your score: " + player.getScore());
            System.out.println("Would you like to hit or stand? (h/s)");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("h") || choice.equalsIgnoreCase("hit")) {
                player.addCard((deck.dealCard()));
                System.out.println("You drew: " + player.getHand());
            } else if (choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("stay") || choice.equalsIgnoreCase("stand")) {
                isPlayerTurn = false;
            }
        }
    }
    private void dealerTurn() {
        System.out.println("Dealers turn...");
        while (dealer.getScore()  < 21) {
            if (player.getScore() > dealer.getScore() && player.getScore() < 21) {
                dealer.addCard(deck.dealCard());
            } else if (player.getScore() == 21 && dealer.getScore() < 21) {
                dealer.addCard(deck.dealCard());
            } else {
                break;
            }
            System.out.println("Dealer drew: " + dealer.getHand());
        }
    }

    private void determineWinner() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        System.out.println("Your score: " + playerScore);
        System.out.println("Dealers score: " + dealerScore);

        if (playerScore > 21) {
            System.out.println("You lost! You busted.");
        } else if (dealerScore > 21) {
            System.out.println("You win! Dealer busted.");
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
        }else if (dealerScore > playerScore) {
            System.out.println("You lose!");
        }else {
            System.out.println("Its a tie.");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}

