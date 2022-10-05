public class EGERG {
    public static void show(int n) {
        for (int i = -n / 2; i < (n / 2) + 1; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = -n / 2; j < n / 2 + 1; j++) {
                if (j == 0) {
                    continue;
                }
                if (Math.abs(i) >= Math.abs(j)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        show(6);

    }
}

