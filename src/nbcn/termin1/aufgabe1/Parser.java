package nbcn.termin1.aufgabe1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<Friend> readFile(File f) throws IOException {
    	ArrayList<Friend> friends = new ArrayList<Friend>();
    	
    	try(BufferedReader br = new BufferedReader(new FileReader("invList.txt"))) {
    	    StringBuilder sb = new StringBuilder();
    	    String line = br.readLine();

    	    while (line != null) {
    	        sb.append(line);
    	        sb.append(System.lineSeparator());
    	        line = br.readLine();
    	    }
    	    String everything = sb.toString();
    	}


        return friends;
    }

    public static byte[] readFile(String path, String file) throws IOException {
        return readFile(new File(path, file));
    }

    public static byte[] readFile(String file) throws IOException {
        return readFile(new File(file));
    }
}

friendsToInv.add(new Friend(0, "Peters", "Peter"));
friendsToInv.add(new Friend(1, "Spex", "Richtael"));
friendsToInv.add(new Friend(2, "Meyer", "Michael"));
friendsToInv.add(new Friend(3, "Mustermann", "Max"));
friendsToInv.add(new Friend(4, "Musterfrau", "Erika"));

friendsToInv.get(0).addFriends(3);
friendsToInv.get(0).addFriends(2);

friendsToInv.get(1).addFriends(3);
friendsToInv.get(1).addFriends(5);

friendsToInv.get(2).addFriends(0);
friendsToInv.get(2).addFriends(4);

friendsToInv.get(3).addFriends(0);
friendsToInv.get(3).addFriends(4);

friendsToInv.get(4).addFriends(0);
friendsToInv.get(4).addFriends(2);