import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class Tabuleiro {
	
	private int numPositions;
	private ArrayList<Posicao> positions = new ArrayList<Posicao>(); 
	
	public Tabuleiro() {
		
	}
	
	public Tabuleiro(int numPositions){
		this.setNumPositions(numPositions);
	}
	
	public int getNumPositions() {
		return numPositions;
	}

	public void setNumPositions(int numPositions) {
		this.numPositions = numPositions;
	}

	public void addPosition(Posicao position){
		this.positions.add(position);
	}
	
	public Posicao getPosition(int positionId){
		return this.positions.get(positionId-1);
	}
	
	/**
	 * Este metodo ordena as posicoes do tabuleiro a partir do atributo position em
	 * ordem crescente. 
	 */
	public void sortPositions(){
		Collections.sort(this.positions, new Comparator<Posicao>() {
	        @Override 
	        public int compare(Posicao p1, Posicao p2) {
	            return p1.getPosition() - p2.getPosition(); // Crescente
	        }

	    });
	}
}
