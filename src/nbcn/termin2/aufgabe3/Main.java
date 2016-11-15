package nbcn.termin2.aufgabe3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFileChooser;

import nbcn.termin2.shared.RedBlackTree;
import nbcn.termin2.shared.RedBlackTreeOC;

public class Main {

	private static Set<Long> testData = new HashSet<>();

	
	public static void main(String[] args) {
		createTestData();

		countRuntime();
		
		//countOperations();
	}

	
	
	public static void createTestData(){
		Random r = new Random();
		
		while (testData.size() < 500000) {
			long val = r.nextLong();
			testData.add(val);
		}
				
		System.out.println("Generierte Testdaten: " + testData.size() + " St.");
	}
	
	
	
	public static void countRuntime(){
		RedBlackTree tree = new RedBlackTree();

		long start = System.currentTimeMillis();
		for (long l : testData){
			tree.insert(l);
		}
		long stop = System.currentTimeMillis();
		System.out.println("Verarbeitungszeit: " + (stop-start) + " ms\n");
	}
	
	
	
	public static void countOperations(){
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
        BufferedWriter output = null;
        RedBlackTreeOC tree = new RedBlackTreeOC();

        try {
           File file = fc.getSelectedFile();
           output = new BufferedWriter(new FileWriter(file));
         
           int cnt = 1;
           for (long l : testData){
        	   	tree.insert(l);
        	   	String data = cnt + ";" + tree.cnt + System.lineSeparator();
        	   	tree.cnt = 0;
        	   	if (cnt == 1 ||  ((cnt%2500)==0))
        	   		output.write(data);
   				cnt++;
           }
	    
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
          }
        }
        
        System.out.println("DONE");
	}
}
