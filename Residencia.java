public class Residencia extends Imovel {
	
	public Residencia(int price, int tax) {
		super(price, tax);
	}

	@Override
	public String getType() {
		return "Residencia";
		
	}
}