package mutation;

import crossing.Crossover;

public enum Mutation {
    OnePoint,TwoPoint,Border;

    public static Mutation valueOf(int selectedIndex) {
        return Mutation.values()[selectedIndex];
    }
}
