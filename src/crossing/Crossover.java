package crossing;

import selection.Selection;

public enum Crossover {
    OnePoint,TwoPoint, Homogeneous;

    public static Crossover valueOf(int selectedIndex) {
        return Crossover.values()[selectedIndex];
    }
}
