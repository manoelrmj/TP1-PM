package imoveis;

public class Residencia extends Imovel {
	
	public Residencia(double price, double tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Residencia";
		
	}
}
