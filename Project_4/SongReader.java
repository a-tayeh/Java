import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SongReader {
    public static void main(String [] args){
        System.out.println(matchedTags("playlist(1).data"));    }

    public static boolean matchedTags(String fileName) {
        StackInterface<String> tagStack = new ArrayStack<>();
        String temp = "";
        String tag = "";

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
                     tag = line.substring(j, k + 1);

                    if (!tag.startsWith("</")) {
                        tagStack.push(tag);
                    } else {
                        temp = tag.replace("/", "");
                        if(line.contains(tag) && line.contains(temp)) {
                            String data = line.replaceAll(tag, "");
                            data = data.replaceAll(temp, "");
                            System.out.println(temp.replaceAll("<|>","")+": "+data.trim());
                        }

                        if (temp.equals(tagStack.peek())) {
                            tagStack.pop();

//                            System.out.println(data);
                        }

                    }
                    j = line.indexOf('<', k + 1);


                }

                String data = line.replaceAll(tag,"");
                data = data.replaceAll(temp,"");
                if(data.length()>0){
                    data = data.trim();
                }
                if(tag.equals("<artist>") && !data.equals("")){
                    System.out.println("artist: "+data);
                }
                else if(tag.equals("<album>")&&!data.equals("")){
                    System.out.println("albums: "+data);
                }
                else if(tag.equals("<title>")&&!data.equals("")){
                    System.out.println("title: "+data);
                }

        }
    }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return tagStack.isEmpty();
    }
}
