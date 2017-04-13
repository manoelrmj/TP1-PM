public class Industria extends Imovel {

	public Industria(int price, int tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hotel";
		
	}
}