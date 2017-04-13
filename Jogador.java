public class Jogador {

	private boolean isOut; // se saldo < 0 - jogador está fora
	private int money;

	public void setMoney(int money){
		this.money = money;
	}

	public int getMoney(){
		return this.money;
	}

	public int earnMoney(int value){
		return this.money = this.money + value;
	}

	public int loseMoney(int value){
		return this.money = this.money - value;
	}

	public void setIsOut(boolean isOut){
		this.isOut = isOut;
	}

	public boolean getIsOut(){
		return this.isOut;
	}
}