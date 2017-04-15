package imoveis;

public abstract class Imovel {

	private double price;
	private boolean sold = false;
	private double tax; // Value given in '%'
	private int owner;
	
	/**
	 * Construtor da classe Imovel.
	 * @param price - Preco do imovel
	 * @param tax - Taxa de aluguel sobre o imovel (dado em %)
	 */
	public Imovel(double price, double tax){
		this.price = price;
		this.tax = tax;
	}

	public final void setPrice(double price){
		this.price = price;
	}

	public final double getPrice(){
		return this.price;
	}

	public final void setSold(boolean sold){
		this.sold = sold;
	}

	public final boolean isSold(){
		return this.sold;
	}

	public final void setTax(double tax){
		this.tax = tax;
	}

	public final double getTax(){
		return ((this.price*this.tax)/100);
	}

	public final void setOwner(int owner){
		this.owner = owner;
	}

	public final int getOwner(){
		return this.owner;
	}
	
	public abstract String getType();
}
