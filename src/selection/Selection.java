package selection;

import comparing.Extrema;

public enum Selection {
    Best,Roulette,Tournament;

    public static Selection valueOf(int selectedIndex) {
        return Selection.values()[selectedIndex];
    }
}
