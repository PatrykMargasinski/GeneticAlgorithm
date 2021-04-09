package selection;

import comparing.Extrema;

import java.util.List;

public interface ISelection {
    public List<Float[]> select(List<Float[]> population, Extrema extrema);
}
