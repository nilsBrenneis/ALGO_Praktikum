package nbcn.termin2.runtime;


import java.util.HashSet;
import java.util.Random;

import javax.swing.JFrame;

import nbcn.termin2.shared.RedBlackTreeOC;

public class Main {

	public static final int NUMBER_OF_RUNS = 20;
	public static final int PROBLEM_SIZE = 500000;
	
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 600;
	
	
	private static int[][] data = new int[NUMBER_OF_RUNS][PROBLEM_SIZE];
	
	static int[] average = new int[PROBLEM_SIZE];
	static int[] minimum = new int[PROBLEM_SIZE];
	static int[] maximum = new int[PROBLEM_SIZE];
	static int[] logarithm = new int[PROBLEM_SIZE];
	
	
	
	public static void main(String[] args) {
		collectData();
		analyzeData();
		presentData();
	}
	
	
	
	private static void collectData(){
		Random r = new Random();
		
		for (int i = 0   ;   i < NUMBER_OF_RUNS   ;   i++){
			HashSet<Long> temp = new HashSet<>();
			RedBlackTreeOC tree = new RedBlackTreeOC();
						
			while (temp.size() < PROBLEM_SIZE){
				temp.add(r.nextLong());
			}
			
			int cnt = 0;
			for (long l : temp){
				tree.insert(l);
				data[i][cnt] = (int)tree.cnt;
				tree.cnt = 0;
				cnt++;
			}
			System.out.println((i+1) + ". Tree: COMPLETED");
		}
	}
	
	
	
	private static void analyzeData(){
		for (int i = 0   ;   i < PROBLEM_SIZE   ;   i++){
			int avg = 0;
			int min = data[0][i];
			int max = data[0][i];
			
			for (int j = 0   ;   j < NUMBER_OF_RUNS   ;   j++){
				avg += data[j][i];
				if (min > data[j][i])
					min = data[j][i];
				if (max < data[j][i])
					max = data[j][i];
				
			}
			average[i] = avg / NUMBER_OF_RUNS;
			minimum[i] = min;
			maximum[i] = max;
			logarithm[i] = (int) Math.round(((Math.log(i)/ Math.log(2)) * 100));
		}
		System.out.println("Data-Analyzing: COMPLETED");
	}
	
	
	private static void presentData(){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setResizable(false);
		window.getContentPane().add(new Diagram());
		window.setTitle("Laufzeitanalyse - Problemgr��e: " + PROBLEM_SIZE + ", Durchl�ufe: " + NUMBER_OF_RUNS + "           x: Elemente, y: Operationen");
		window.setLocationRelativeTo(null);;
		window.setVisible(true);
	}

}
