public class Hotel extends Imovel {

	public Hotel(int price, int tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hotel";		
	}
}