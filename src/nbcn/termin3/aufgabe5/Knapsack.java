package nbcn.termin3.aufgabe5;

public class Knapsack {

	static int dynamicKnapsack(int knapsackCapacity, int weight[], int value[]) {
		int valArrLength = value.length;
		int K[][] = new int[valArrLength + 1][knapsackCapacity + 1];

		for (int i = 0; i <= valArrLength; i++) {
			for (int j = 0; j <= knapsackCapacity; j++) {
				if (i == 0 || j == 0)
					K[i][j] = 0;
				else if (weight[i - 1] <= j)
					K[i][j] = maxInt(value[i - 1] + K[i - 1][j - weight[i - 1]], K[i - 1][j]);
				else
					K[i][j] = K[i - 1][j];
			}
		}
		return K[valArrLength][knapsackCapacity];
	}

	static int maxInt(int a, int b) {
		return (a > b) ? a : b;
	}

	public static void main(String args[]) {
		int weight[] = new int[] { 2, 2, 6, 5, 4 };
		int value[] = new int[] { 2, 3, 5, 4, 6 };
		int knapsackCapacity = 10;
		System.out.println(dynamicKnapsack(knapsackCapacity, weight, value));
	}
}