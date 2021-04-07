package comparing;

import someMethods.SomeMethods;

import java.util.List;

public class Maximum implements IExtrema {
    @Override
    public Float getExtrema(List<Float> population) {
        Float max = Float.MIN_VALUE;
        for (Float f : population) {
            if (max==Float.MIN_VALUE||SomeMethods.fun(f) > SomeMethods.fun(max)) {
                max = f;
            }
        }
        return max;
    }
}
