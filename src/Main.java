public class Main {

    public static void main(String args[]) {
        Benzier3D czajnik = new Benzier3D(0.05);
        czajnik.loadModel("teacup.bpt");
        czajnik.saveCalcModel("teacup_xyz.txt");

        Benzier3D trim = new Benzier3D(0.05);
        trim.loadModel("teapotrim.bpt");
        trim.saveCalcModel("teapotrim.xyz.txt");

        Benzier3D spoon = new Benzier3D(0.05);
        spoon.loadModel("teaspoon.bpt");
        spoon.saveCalcModel("teaspoon_xyz.txt");
    }

}