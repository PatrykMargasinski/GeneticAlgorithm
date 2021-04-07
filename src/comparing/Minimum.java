package comparing;

import someMethods.SomeMethods;

import java.util.List;

public class Minimum implements IExtrema {
    @Override
    public Float getExtrema(List<Float> population) {
        Float min = Float.MAX_VALUE;
        for (Float f : population) {
            if (min==Float.MAX_VALUE||SomeMethods.fun(f) < SomeMethods.fun(min)) {
                min = f;
            }
        }
        return min;
    }
}
