public class Jogador {

	private boolean playing; // se saldo < 0 - jogador está fora
	private double balance;
	private int id;
	private int diceCounter = 1;
	private double rentEarned = 0;
	private double rentPaid = 0;
	private double purchasedPropertyMoney = 0;
	private int passTurnNum = 0;

	public Jogador(int id, int value){
		this.id = id;
		this.balance = value;
	}

	public void setBalance(int value){
		this.balance = value;
	}

	public double getBalance(){
		return this.balance;
	}

	public void receiveStartMoney(int value){
		this.balance += value;
	}

	public void receiveRent(int value){
		this.rentEarned += value;
		this.balance += value;
	}

	public double getRentEarned(){
		return this.rentEarned;
	}

	public void payRent(int value){
		this.rentPaid += value;
		this.balance -= value;
	}

	public double getRentPaid(){
		return this.rentPaid;
	}

	public void buyProperty(int value){
		this.purchasedPropertyMoney += value;
		this.balance -= value;
	}

	public double getPurchasedPropertyMoney(){
		return this.purchasedPropertyMoney;
	}

	public void setPlaying(boolean playing){
		this.playing = playing;
	}

	public boolean isPlaying(){
		return this.playing;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return this.id;
	}

	public void updateDiceCounter(int value){
		this.diceCounter += value;
	}

	public int getPosition(int numPositions){
		if(this.diceCounter%numPositions == 0){
			return numPositions;
		}
		else{
			return this.diceCounter%numPositions;
		}
	}

	public int getLaps(int numPositions){
		if(this.diceCounter%numPositions == 0){
			return (this.diceCounter/numPositions - 1);
		}
		else{
			return this.diceCounter/numPositions;
		}
	}

	public void updatePassTurn(){
		this.passTurnNum++;
	}

	public int getPassTurn(){
		return this.passTurnNum;
	}
}
