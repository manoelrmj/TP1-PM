package imoveis;

public class Industria extends Imovel {

	public Industria(double price, double tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hotel";
		
	}
}
