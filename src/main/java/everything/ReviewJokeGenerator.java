package everything;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

//description
//corrected code sended from https://github.com/KrystianRybicki/simple-joke-generator/blob/main/jokeChuckNorris.java

public class ReviewJokeGenerator {
    public static void main(String[] args) {
        printGameRules();
        runJokeGenerator();
    }

    public static void runJokeGenerator() {
        Scanner inputFromUser = new Scanner(System.in);
        String answer = "";

        do {
            answer = inputFromUser.nextLine().trim();

            printJokeIfAnswerIsYes(answer);
            printMessageIfUserGiveUnexpectedCommand(answer);

        } while (!answer.equalsIgnoreCase("no"));
    }

    public static void printGameRules() {
        System.out.println(
                "Welcome to Chuck Norris joke generator." +
                        " \nAuthor of the program Krystian Rybicki." +
                        "\nDo you want hear a joke?" +
                        "\nSay 'yes' to hear joke" +
                        "\nSay 'no' to exit a game"
        );
    }

    public static void printMessageIfUserGiveUnexpectedCommand(String answer) {
        if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
            System.out.println("unexpected command, write 'yes' or 'no'");
        }
    }

    public static void printJokeIfAnswerIsYes(String answer) {
        if (answer.equalsIgnoreCase("yes")) {
            System.err.println(drawJokeFromApi());
            System.out.println("Do u wanna hear more?");
        }
    }

    public static String drawJokeFromApi() {
        StringBuilder builder = new StringBuilder();
        String jokeGeneratorUrl = "https://api.chucknorris.io/jokes/random";

        try {
            String line = "";
            URL url = new URL(jokeGeneratorUrl);
            InputStream is = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                int index = builder.indexOf("value");
                builder.delete(0, index + 8);
                return builder.delete(builder.length() - 2, builder.length()).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sorry, there is some issue with connection with my jokes provider :(";
    }

}
