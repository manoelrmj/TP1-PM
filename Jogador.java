public class Jogador {

	private boolean playing; // se saldo < 0 - jogador está fora
	private int balance;

	public Jogador(int value){
		this.balance = value;
	}

	public void setMoney(int value){
		this.balance = value;
	}

	public int getMoney(){
		return this.balance;
	}

	public int earnMoney(int value){
		return this.balance = this.balance + value;
	}

	public int loseMoney(int value){
		return this.balance = this.balance - value;
	}

	public void setPlaying(boolean playing){
		this.playing = playing;
	}

	public boolean isPlaying(){
		return this.playing;
	}
}