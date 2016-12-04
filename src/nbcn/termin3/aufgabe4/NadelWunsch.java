package nbcn.termin3.aufgabe4;

public class NadelWunsch{
	   private int[][] cluster;
	   private String sequenz1;
	   private String sequenz2;
	  
	   public NadelWunsch(String seq1, String seq2){
		   	sequenz1 = seq1; 
		   	sequenz2 = seq2;
	        cluster = new int[sequenz1.length()+1][sequenz2.length()+1];
	        initialisiere();        
	   }
	   
	   public void initialisiere(){
	      // Starteintrag wird auf 0 gesetzt
	      cluster[0][0] = 0;
	      
	      // fülle die erste Zeile und erste Spalte mit 0-en
	      for (int i=1; i<=sequenz1.length(); i++)
	         cluster[i][0] = -i;            
	      for (int j=1; j<=sequenz2.length(); j++)
	         cluster[0][j] = -j;    
	   }
	   
	   private int equal(char a, char b){
	      if (a==b) return 1;
	      else return -1;
	   }
	   
	   public int compare(){
	      for (int i=1; i<=sequenz1.length(); i++)
	         for (int j=1; j<=sequenz2.length(); j++)
	            cluster[i][j] = Math.max(cluster[i-1][j-1] + equal(sequenz1.charAt(i-1),sequenz2.charAt(j-1))
	            						,Math.max(cluster[i-1][j] - 1, cluster[i][j-1] - 1)
	            						);
	      printTable(cluster);
	      return cluster[sequenz1.length()][sequenz2.length()];                                           
	   }
	   
		private void printTable(int[][] table) {
			for (int i = 0; i <= sequenz1.length(); i++) {
				for (int j = 0; j <= sequenz2.length(); j++) {
					System.out.print("[" + table[i][j] + "]" + " ");
				}
				System.out.println(System.lineSeparator());
			}
		}
	   
	   public static void main(String[] args){
	      
		  String seq1 = "ATA";
	      String seq2 = "AGTA";
	      
	      NadelWunsch NW = new NadelWunsch(seq1, seq2);         
	      System.out.println("Ergebnis: "+NW.compare());
	   }
	}