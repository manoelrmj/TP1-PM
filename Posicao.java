import imoveis.*;

public class Posicao {
	
	private int id;
	private int type;
	private int position;
	private Imovel property = null;
	
	/**
	 * Construtor da classe posicao sem objeto 'Imovel'. Utilizado para instanciar
	 * objetos que representam posicoes do tipo 'Start' e 'Passe a vez'
	 * @param id - id da posicao
	 * @param position - indice do posicionamento no tabuleiro
	 * @param type - tipo da posicao
	 */
	public Posicao(int id, int position, int type) {
		this.id = id;
		this.position = position;
		this.type = type;		
	}
	
	/**
	 * Construtor da classe posicao com objeto 'Imovel'. Utilizado para instanciar
	 * objetos que representam posicoes do tipo 'Imovel', que possuem um objeto do tipo Imovel	
	 * @param id - id da posicao
	 * @param position - indice do posicionamento no tabuleiro
	 * @param type - tipo da posicao
	 * @param property - Objeto do tipo Imovel
	 */
	public Posicao(int id, int position, int type, Imovel property) {
		this.id = id;
		this.type = type;
		this.position = position;
		this.property = property;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Imovel getProperty() {
		return property;
	}

	public void setProperty(Imovel property) {
		this.property = property;
	}

}
