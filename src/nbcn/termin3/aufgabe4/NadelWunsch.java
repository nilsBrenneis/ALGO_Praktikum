package nbcn.termin3.aufgabe4;

public class NadelWunsch {
	private static int[][] cluster;
	private static String sequenz1;
	private static String sequenz2;

	public static void initialisiere() {
		sequenz1 = "ATA";
		sequenz2 = "AGTA";
		cluster = new int[sequenz1.length() + 1][sequenz2.length() + 1];

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

	public static void compare() {
		for (int i = 1; i <= sequenz1.length(); i++) {
			for (int j = 1; j <= sequenz2.length(); j++) {
				cluster[i][j] = Math.max(cluster[i - 1][j - 1] + equal(sequenz1.charAt(i - 1), sequenz2.charAt(j - 1)),
						Math.max(cluster[i - 1][j] - 1, cluster[i][j - 1] - 1));
			}
		}
	}

	private static void printTable(int[][] table) {
		for (int i = 0; i <= sequenz1.length(); i++) {
			for (int j = 0; j <= sequenz2.length(); j++) {
				System.out.print("[" + table[i][j] + "]" + " ");
			}
			System.out.println(System.lineSeparator());
		}
	}

	public static void main(String[] args) {
		initialisiere();
		compare();
		printTable(cluster);
	}
}