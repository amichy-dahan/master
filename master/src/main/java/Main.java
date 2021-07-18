import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int points = 0;
        Robot site = getSiteSelection();
        // if the exception is being caught in getSiteSelection() so site will be null
        if (site != null) {
            points += guessCommonWords(site);
            String userText = getHeadlinesText();
            System.out.println("how many time it will appears?:");
            int quantity = scanner.nextInt();
            try {
                points += chuckText(quantity, userText, site);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("you achieved " + points + " points!");
        } else {
            System.out.println("cant access website, sorry.");
        }

    }

    private static int chuckText(int quantity, String userText,Robot site) throws IOException {
        int realQuantity = site.countInArticlesTitles(userText);
        if (Math.abs(quantity - realQuantity) <= 2) {
            return 250;
        }
        return 0;
    }

    private static String getHeadlinesText() {
        Scanner scanner = new Scanner(System.in);
        String userText = "";
        while (userText.length() < 1 || userText.length() > 20) {
            System.out.println("Enter any text that you think should appear in the headlines on the site");
            System.out.println("the text should be between 1 and 20 chars:");

            userText = scanner.nextLine();
        }
        return userText;
    }

    private static Robot getSiteSelection() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option < 1 || option > 3) {
            System.out.println("choose site do you want to scan?");
            System.out.println("1. Mako");
            System.out.println("2. Ynet");
            System.out.println("3. Walla");
            option = scanner.nextInt();
        }

        try {
            switch (option) {
                case 1:
                    return new MakoRobot();
                case 2:
                    return new YnetRobot();
                default:
                    return new WallaRobot();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int guessCommonWords(Robot site) {
        Scanner scanner = new Scanner(System.in);
        int points = 0;
        try {
            String longestArticle = site.getLongestArticleTitle();
            System.out.println("Guess what are the most common words on the site?");
            System.out.println("----------------------------------------------------------------");
            System.out.println("hint: " + longestArticle);
            System.out.println("----------------------------------------------------------------");


            Map<String, Integer> wordsInSite = site.getWordsStatistics();
            for (int i = 1; i <= 5; i++) {
                System.out.println("guess number " + i + ":");
                String guess = scanner.nextLine();
                points += wordsInSite.getOrDefault(guess, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;

    }}
