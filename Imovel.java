public class Imovel {

	private int price;
	private boolean isSold;
	private int tax; // in %

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return this.price;
	}

	public void setIsSold(boolean isSold){
		this.isSold = isSold;
	}

	public boolean getIsSold(){
		return this.isSold;
	}

	public void setTax(int tax){
		this.tax = tax;
	}

	public int getTax(){
		return this.tax;
	}
}