public class Comercio extends Imovel {

	public Comercio(int price, int tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Comercio";		
	}
}