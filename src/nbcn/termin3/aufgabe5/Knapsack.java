package nbcn.termin3.aufgabe5;

public class Knapsack {

	private static int dynamicKnapsack(int knapsackCapacity, int weight[], int value[]) {
		int valArrLength = value.length;
		int v[][] = new int[valArrLength + 1][knapsackCapacity + 1];

		for (int i = 0; i <= valArrLength; i++) {
			for (int k = 0; k <= knapsackCapacity; k++) {
				if (i == 0 || k == 0) {
					v[i][k] = 0;
				}
				else if (weight[i - 1] <= k) {
					v[i][k] = maxInt(v[i - 1][k], value[i - 1] + v[i - 1][k - weight[i - 1]]);
				}
				else {
					v[i][k] = v[i - 1][k];
				}
			}
		}
		printTable(v);
		return v[valArrLength][knapsackCapacity];
	}

	private static int maxInt(int a, int b) {
		return (a > b) ? a : b;
	}
	
	private static void printTable(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j <= table.length; j++) {
				System.out.print("[" + table[i][j] + "]" + " ");
			}
			System.out.println(System.lineSeparator());
		}
	}

	public static void main(String args[]) {
		int weight[] = new int[] {3, 2, 4, 1};
		int value[] = new int[] { 100, 20, 60, 40};
		int knapsackCapacity = 5;
		System.out.println(dynamicKnapsack(knapsackCapacity, weight, value));
	}
}