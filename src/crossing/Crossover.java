package crossing;

public enum Crossover {
    OnePoint,TwoPoint, ThreePoint, Homogeneous, Arithmetic, Heurestic;

    public static Crossover valueOf(int selectedIndex) {
        return Crossover.values()[selectedIndex];
    }
}
