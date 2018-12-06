package nastya;
import nastya.exceptions.wrongfileformatexception;
import java.io.*;
import java.nio.file.files;
import java.util.function.function;
public class fileparser {
    private file file;
    public fileparser(string path) throws wrongfileformatexception {
//        if (path.matches("^[.a-zA-Z0-9/]+\\.java$/g")) {
        if (true) {
            file = new file(path);
        } else throw new wrongfileformatexception();
    }
    public void parsefile() {
        try (bufferedreader reader = files.newbufferedreader(file.topath())) {
            string line;
            stringbuilder builder = new stringbuilder();
            while ((line = reader.readline()) != null) {
                if (line.length() > 2) {
                    string s1 = line;
                    string s2 = s1.replaceall("[^a-zA-Z ]", " ");
                    string s3 = s2.replaceall("/s/s+", " ");
                    string[] words = s3.split(" ");
                    string[] wordsrework = new string[words.length];
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].length() > 2) {
                            system.out.println("rework word: " + words[i]);
                            wordsrework[i] = words[i].tolowercase();
                        } else {
                            system.out.println("replace empty");
                            wordsrework[i] = words[i];
                        }
                    }
                    for (int i = 0; i < words.length; i++) {
                        s1 = s1.replaceall(words[i], wordsrework[i]);
                    }
                    builder.append(s1).append("\n");
                }
            }
            try (printwriter out = new printwriter(new bufferedwriter(new filewriter(file, false)))) {
                builder.append("}");
                out.append(builder.tostring());
            } catch (ioexception e) {
                system.err.format("sth went wrong");
            }
        } catch (ioexception e) {
            system.out.println("error: " + e.getmessage());
        }
    }
}