package imoveis;

public class Comercio extends Imovel {

	public Comercio(double price, double tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Comercio";		
	}
}
