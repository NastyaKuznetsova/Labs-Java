package nastya;

import java.io.*;
import java.nio.file.Files;

public class FileParser {
    private File file;

    public FileParser(String path) {
        file = new File(path);
    }

    public void parseFile() {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.length() > 2) {
                    String s1 = line;
                    String s2 = s1.replaceAll("[^a-zA-Z ]", " ");
                    String s3 = s2.replaceAll("/s/s+", " ");
                    String[] words = s3.split(" ");
                    String[] wordsRework = new String[words.length];
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].length() > 2) {
                            System.out.println("Rework word: " + words[i]);
                            wordsRework[i] = words[i].toLowerCase();
                        } else {
                            System.out.println("replace empty");
                            wordsRework[i] = words[i];
                        }
                    }
                    for (int i = 0; i < words.length; i++) {
                        s1 = s1.replaceAll(words[i], wordsRework[i]);
                    }

                    builder.append(s1).append("\n");
                }
            }
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)))) {
                builder.append("}");
                out.append(builder.toString());
            } catch (IOException e) {
                System.err.format("Sth went wrong");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
