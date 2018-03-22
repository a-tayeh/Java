import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SongReader {
    public static void main(String [] args){
        System.out.println(matchedTags("playlist(1).data"));    }

    public static boolean matchedTags(String fileName) {
        StackInterface<String> tagStack = new ArrayStack<>();

        try {
            Scanner reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()){
                String line = reader.nextLine().trim();
                int j = line.indexOf('<');
                while (j != -1) {
                    int k = line.indexOf('>', j + 1);
                    if (k == -1) {
                        return false;
                    }
                    String tag = line.substring(j, k + 1);
                    if (!tag.startsWith("</")) {
                        tagStack.push(tag);
                    } else {
                        String temp = tag.replace("/", "");

                        if (temp.equals(tagStack.peek())) {
                            tagStack.pop();
                        }

                    }
                    j = line.indexOf('<', k + 1);
                }
        }
    }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return tagStack.isEmpty();
    }
}
