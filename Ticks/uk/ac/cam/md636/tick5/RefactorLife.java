package uk.ac.cam.md636.tick5;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RefactorLife {
    public static void play(World world) throws IOException {
        int userResponse = 0;
        Writer w = new OutputStreamWriter(System.out);
        while (userResponse != 'q') {
            world.print(w);
            userResponse = System.in.read();
            world = world.nextGeneration(0);
        }
    }

    public static void main(String[] args) {

        List<Pattern> results = null;

        try {
            if (args[0].startsWith("http://")) {
                results = PatternLoader.loadFromURL(args[0]);
            } else {
                results = PatternLoader.loadFromDisk(args[0]);
            }

            if (args.length == 2) {
                Pattern p = results.get(Integer.parseInt(args[1])); 
                ArrayWorld world = new ArrayWorld(p.getWidth(), p.getHeight());
                p.initialise(world);
                play(world);
            } else {
                int i = 0; // For producing the list
                for (Pattern p : results) {
                    System.out.println(i + ")" + p.getOriginal());
                    i++;
                }

            }

        } catch (PatternFormatException e) {
            System.out.println(e.getMsg());
        } catch (IOException e) {
            System.out.println("Wrong type of file"); // This may need to change
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Bad index");
        }
    }
}