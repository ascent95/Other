package uk.ac.cam.md636.tick5;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;

public class RefactorLife {
    
    public static void play(World world) throws IOException {
        WorldViewer viewer = new WorldViewer();
        int userResponse = 0;
        Writer w = new OutputStreamWriter(System.out);
        while (userResponse != 'q') {
            viewer.show(world);
            world.print(w);
            userResponse = System.in.read();
            world = world.nextGeneration(0);
        }
    }

    private static List<Pattern> getResults(String path) throws IOException, PatternFormatException {
        List<Pattern> results = null;
        if (path.startsWith("http://")) {
            results = PatternLoader.loadFromURL(path);
        } else {
            results = PatternLoader.loadFromDisk(path);
        }
        return results;
    }

    private static void printOptions(List<Pattern> results) {
        int i = 0; // For producing the list
        for (Pattern p : results) {
            System.out.println(i + ")" + p.getOriginal());
            i++;
        }
    }

    public static void main(String[] args) { //I didn't see the skeleton piece of code but I have independenty developed a similar solution.
        World world = null; //The type of ArrayWorld or PackedWorld is chosen later.
        try {
            if (args.length == 3) {
                if (args[0].startsWith("--")) {
                    Pattern p = getResults(args[1]).get(Integer.parseInt(args[2])); // The case
                                                                                    // where there
                                                                                    // is a storage
                                                                                    // mechanism
                                                                                    // defined.
                    if (args[0].equals("--array")) {
                        world = new ArrayWorld(p.getWidth(), p.getHeight());
                    } else if (args[0].equals("--long")) {
                        world = new PackedWorld();
                    } else if (args[0].equals("--aging")) {
                        world = new AgingWorld(p.getWidth(), p.getHeight());
                    } else { // --somthingrandom
                        System.out.println("You haven't supplied a valid storage mechanism.");
                    }
                    p.initialise(world);
                    play(world);
                }
            }

            else if (args.length == 2) {
                if (args[0].startsWith("--")) { // It doesn't matter what type because either
                                                // there is no list or no selector. If no list then an exception will be thrown which is fine.
                    printOptions(getResults(args[1]));
                } else {
                    Pattern p = getResults(args[0]).get(Integer.parseInt(args[1]));
                    world = new ArrayWorld(p.getWidth(), p.getHeight());
                    p.initialise(world);
                    play(world);
                }
            }

            else if (args.length == 1) {
                printOptions(getResults(args[0]));
            } 
            
            else if (args.length == 0) {
                System.out.println("You haven't entered any arguments.");
            }
            
            else {
                System.out.println("You've entered too many arguments.");
            }

        } catch (PatternFormatException e) {
            System.out.println(e.getMsg());
        } catch (IOException e) {
            System.out.println("Wrong type of file"); // This may need to change
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e);
            System.out.println("Bad index. Make sure it is a number that correctly selects a pattern.");
        }
    }
}