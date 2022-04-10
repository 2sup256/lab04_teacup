import java.io.*;
import java.util.ArrayList;

public class Benzier3D {
    private Punkt3D[][][] punkty;
    public int sum = -1;
    public double dokladnosc;

    public Benzier3D(double dokladnosc) {
        this.dokladnosc = dokladnosc;
    }

    public void loadModel(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (this.sum == -1) {
                    this.sum = Integer.parseInt(line);
                    punkty = new Punkt3D[this.sum][4][4];
                    continue;
                }
                line = line.replace("     ", " ");
                line = line.replace("    ", " ");
                line = line.replace("   ", " ");
                line = line.replace("  ", " ");
                String[] data = line.split(" ");
                if (data.length == 3) {
                    lines.add(line);
                }
            }

            int count = 0;
            for (int i = 0; i < this.sum; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        String[] data = lines.get(count).split(" ");
                        punkty[i][j][k] = new Punkt3D(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                    }
                    count++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCalcModel(String filepath) {
        double x;
        double y;
        double z;

        try {
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < sum; i++) {
                for (double j = 0.0; j <= 1.0; j += dokladnosc) {
                    for (double k = 0.0; k <= 1.0; k += dokladnosc) {
                        x = 0;
                        y = 0;
                        z = 0;

                        for (int l = 0; l < 4; l++) {
                            for (int m = 0; m < 4; m++) {
                                x += punkty[i][m][l].x * bernstein(l, 3, j) * bernstein(m, 3, k);
                                y += punkty[i][m][l].y * bernstein(l, 3, j) * bernstein(m, 3, k);
                                z += punkty[i][m][l].z * bernstein(l, 3, j) * bernstein(m, 3, k);
                            }
                        }

                        fw.write(x + " " + y + " " + z + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double bernstein(int i, int n, double x) {
        double f1 = silnia(n) / (silnia(n - i) * silnia(i));
        double f2 = Math.pow((1 -x), (n - i));
        double f3 = Math.pow(x, i);
        return f1 * f2 * f3;
    }

    public static double silnia(int n) {
        long buff = 1;
        for (int i = 2; i <= n; i++) {
            buff = buff * i;
        }
        return buff;
    }

}
