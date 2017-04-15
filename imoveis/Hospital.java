package imoveis;

public class Hospital extends Imovel {

	public Hospital(double price, double tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hospital";		
	}
}
