package tp.pr4;

import java.util.ArrayList;
import java.util.List;

/*
* @author José Javier Martínez Pagés
* @author Cristina Valentina Espinosa Victoria
*/
public class Observable<T> {
	
	private List<Observer<T>> observers =	new ArrayList<Observer<T>>();
	
	public void addObserver(Observer<T> obs) {
		if (obs == null) {
		throw new IllegalArgumentException();
		}
		if (!this.observers.contains(obs)) this.observers.add(obs);
	}
	
	
	protected void removeObserver(Observer<T> obs){
		if (obs == null) {
			throw new IllegalArgumentException();
		}
		if (this.observers.contains(obs)) this.observers.remove(obs);
	}

	
	interface Observer<T> {
		public void atualizar(Observable<T> entity, T arg);
		}
}
