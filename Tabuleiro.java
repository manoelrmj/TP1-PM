import java.util.ArrayList;

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
}
