package crossing;

public enum Crossover {
    OnePoint,TwoPoint, ThreePoint, Homogeneous;

    public static Crossover valueOf(int selectedIndex) {
        return Crossover.values()[selectedIndex];
    }
}
