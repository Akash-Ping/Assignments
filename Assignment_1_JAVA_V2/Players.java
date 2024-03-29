import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Players {

    public static void main(String[] args) {
        String csvFile = "Scores.csv";
        String line;

        // LinkedHashMap to preserve insertion order
        LinkedHashMap<String, Map<String, Integer>> playerScores = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Print the current line being processed
                System.out.println("Processing line: " + line);

                // Split the line by comma to extract player name and scores
                String[] parts = line.split(",");
                String player = parts[0];

                // Process scores for each player
                for (int i = 1; i < parts.length; i++) {
                    // Split score by underscore to extract country and score
                    String[] match = parts[i].split("_");
                    String country = match[0];
                    int score = Integer.parseInt(match[1]);

                    // Initialize scores map for the player if not present
                    playerScores.putIfAbsent(player, new HashMap<>());
                    Map<String, Integer> scores = playerScores.get(player);

                    // Update the maximum score for the country
                    scores.put(country, Math.max(scores.getOrDefault(country, Integer.MIN_VALUE), score));
                }
            }

            // Print the player name with the highest score for each country
            for (Map.Entry<String, Map<String, Integer>> entry : playerScores.entrySet()) {
                String player = entry.getKey();
                Map<String, Integer> scores = entry.getValue();
                int maxScore = Integer.MIN_VALUE;
                String country = "";

                // Find the country with the highest score for the player
                for (Map.Entry<String, Integer> scoreEntry : scores.entrySet()) {
                    if (scoreEntry.getValue() > maxScore) {
                        maxScore = scoreEntry.getValue();
                        country = scoreEntry.getKey();
                    }
                }

                // Print the player and their highest score country
                System.out.println(player + ": " + country + " - " + maxScore);
            }
        } catch (IOException e) {
            // Handle IOException by printing the error message
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handle NumberFormatException if score parsing fails
            System.err.println("Error parsing score: " + e.getMessage());
        }
    }
}
