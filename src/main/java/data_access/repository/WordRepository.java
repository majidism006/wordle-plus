package data_access.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Performs operations on word retrieval.
 */
public class WordRepository {
    private final List<String> easyWords;
    private final List<String> mediumWords;
    private final List<String> hardWords;

    public WordRepository() {
        try {
            this.easyWords = loadWords("src/main/resources/easy_words");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.mediumWords = loadWords("src/main/resources/medium_words");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.hardWords = loadWords("src/main/resources/hard_words");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> loadWords(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    /**
     * Returns random word for a given difficulty.
     * @param difficulty indicates the difficulty level for the returned word.
     * @return String random word.
     * @throws RuntimeException if difficulty isn't supported.
     */
    public String getRandomWord(String difficulty) {
        Random random = new Random();
        switch (difficulty.toLowerCase()) {
            case "easy":
                return easyWords.get(random.nextInt(easyWords.size()));
            case "medium":
                return mediumWords.get(random.nextInt(mediumWords.size()));
            case "hard":
                return hardWords.get(random.nextInt(hardWords.size()));
            default:
                throw new RuntimeException("Unsupported difficulty: " + difficulty);
        }
    }

}
