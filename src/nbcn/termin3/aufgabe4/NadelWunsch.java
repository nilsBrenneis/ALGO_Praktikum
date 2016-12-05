package nbcn.termin3.aufgabe4;

public class NadelWunsch {
	private static int[][] cluster;
	private static String sequenz1;
	private static String sequenz2;
	private static String[][] pathCluster;

	private static void initialisiere() {
		sequenz1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGA";
		sequenz2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
		cluster = new int[sequenz1.length() + 1][sequenz2.length() + 1];
		pathCluster = new String[sequenz1.length() + 1][sequenz2.length() + 1];

		cluster[0][0] = 0;

		for (int i = 1; i <= sequenz1.length(); i++)
			cluster[i][0] = -i;
		for (int j = 1; j <= sequenz2.length(); j++)
			cluster[0][j] = -j;
	}

	private static int equal(char a, char b) {
		if (a == b) {
			return 1;
		} else {
			return -1;
		}
	}

	private static void compare() {
		for (int i = 1; i <= sequenz1.length(); i++) {
			for (int j = 1; j <= sequenz2.length(); j++) {
				cluster[i][j] = Math.max(cluster[i - 1][j - 1] + equal(sequenz1.charAt(i - 1), sequenz2.charAt(j - 1)),
						Math.max(cluster[i - 1][j] - 1, cluster[i][j - 1] - 1));
			}
		}
	}

	private static void printTable(String[][] table) {
		for (int i = 0; i <= sequenz1.length(); i++) {
			for (int j = 0; j <= sequenz2.length(); j++) {
				System.out.print("[" + table[i][j] + "]" + " ");
			}
			System.out.println(System.lineSeparator());
		}
	}

	private static void drawPath(int i, int j) {
		pathCluster[i][j] = "_" + pathCluster[i][j] + "_";

		if (!(i == 1 || j == 1)) {
			int left = cluster[i - 1][j];
			int diagonal = cluster[i - 1][j - 1];
			int above = cluster[i][j - 1];

			int nextElement = Math.max(Math.max(left, diagonal), above);

			if (nextElement == diagonal) {
				drawPath(i - 1, j - 1);
			} else if (nextElement == left) {
				drawPath(i - 1, j);
			} else if (nextElement == above) {
				drawPath(i, j - 1);
			}
		} else if (i == 1 && j > 1) {
			drawPath(i, j - 1);
		} else if (j == 1 && i > 1) {
			drawPath(i - 1, j);
		}
	}
	
	private static void fillPathCluster() {
		for (int i = 0; i <= sequenz1.length(); i++) {
			for (int j = 0; j <= sequenz2.length(); j++) {
				pathCluster[i][j] = Integer.toString(cluster[i][j]);
			}
		}
	}

	public static void main(String[] args) {
		initialisiere();
		compare();
		fillPathCluster();
		drawPath(sequenz1.length(), sequenz2.length());
		printTable(pathCluster);
	}
}