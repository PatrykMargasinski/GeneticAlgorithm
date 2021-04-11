package comparing;

import java.util.List;

public interface IExtrema {
    Float[] getExtrema(List<Float[]> population);
    List<Float[]> getSomeBest(List<Float[]> population, int bestAmount);
}
