package bosquedeletras.strategy;

import java.util.List;

public interface SortStrategy<T extends Sortable> {
	void sort(List<T> lista);

	String getNombre();
}