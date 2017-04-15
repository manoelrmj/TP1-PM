public class Hotel extends Imovel {

	public Hotel(double price, double tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hotel";		
	}
}
