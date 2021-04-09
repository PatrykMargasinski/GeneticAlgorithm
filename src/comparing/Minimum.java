package comparing;

import someMethods.SomeMethods;

import java.util.List;

public class Minimum implements IExtrema {
    @Override
    public Float[] getExtrema(List<Float[]> population) {
        Float[] min = null;
        for (Float[] f : population) {
            if (min==null||SomeMethods.fun(f) < SomeMethods.fun(min)) {
                min = f;
            }
        }
        return min;
    }
}
