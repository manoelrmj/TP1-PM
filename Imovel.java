public abstract class Imovel {

	private int price;
	private boolean sold = false;
	private int tax; // Value given in '%'
	
	public Imovel(int price, int tax){
		this.price = price;
		this.tax = tax;
	}

	public final void setPrice(int price){
		this.price = price;
	}

	public final int getPrice(){
		return this.price;
	}

	public final void setSold(boolean sold){
		this.sold = sold;
	}

	public final boolean isSold(){
		return this.sold;
	}

	public final void setTax(int tax){
		this.tax = tax;
	}

	public final int getTax(){
		return this.tax;
	}
	
	public abstract String getType();
}