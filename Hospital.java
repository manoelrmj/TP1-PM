public class Hospital extends Imovel {

	public Hospital(int price, int tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Hospital";		
	}
}