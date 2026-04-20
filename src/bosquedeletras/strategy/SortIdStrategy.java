package bosquedeletras.strategy;

import java.util.Comparator;
import java.util.List;

public class SortIdStrategy<T extends Sortable> implements SortStrategy<T> {
	
	private final boolean asc;
	
	public SortIdStrategy(boolean asc) {
		this.asc = asc;
	}
	
	@Override
	public void sort(List<T> lista) {
		Comparator<T> cmp = Comparator.comparingInt(T::getId);
		lista.sort(asc ? cmp : cmp.reversed());
	}
	
	@Override
	public String getNombre() {
		return asc ? "ID ascendente" : "ID descendente";
	}

	@Override
	public String toString() {
		return getNombre();
	}
	
}
