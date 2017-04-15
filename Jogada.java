import java.util.ArrayList;

public final class Jogada {

	private int id;
	private int playerId;
	private int diceValue;
	private boolean dump = false;
	
	/**
	 * Construtor da classe Jogada. Ao receber todos os parametros com o valor 0,
	 * a jogada é classificada como sendo do tipo 'DUMP' e as estatísticas do jogo
	 * serao impressas pelo metodo responsavel.
	 * @param id - Id da jogada
	 * @param playerId - Id do jogador que realiza a jogada
	 * @param diceValue - Valor que saiu no dado
	 */
	public Jogada(int id, int playerId, int diceValue) {
		if(id == 0 && playerId == 0 && diceValue == 0){
			this.dump = true;
		}else{
			this.id = id;
			this.playerId = playerId;
			this.diceValue = diceValue;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getDiceValue() {
		return diceValue;
	}

	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	
	public boolean isDump() {
		return dump;
	}

	public void setDump(boolean dump) {
		this.dump = dump;
	}

	/**
	 * Metodo que executa as jogadas. A partir das informacoes do estado do jogo (fornecido pelos
	 * objetos 'Tabuleiro' e pela lista de jogadores) e com os dados da jogada realizada, o estado
	 * desses objetos e alterado para refletir as acoes da jogada executada
	 * @param board - Objeto do tipo 'Tabuleiro', que contem as informacoes das posicoes
	 * @param players - Lista de objetos do tipo 'Jogador'
	 * @param move - Objeto do tipo 'Jogada', que possui informacoes da jogada a ser processada
	 */
	public static void runMove(Tabuleiro board, ArrayList<Jogador> players, Jogada move){
		if(move.isDump()){
			printDump(board, players);
		}else{
			//System.out.println("Jogada " + move.getId());
			int playerId = move.getPlayerId()-1;
			int diceNumber = move.getDiceValue();
			players.get(playerId).updateDiceCounter(diceNumber);
			int playerPosition = players.get(playerId).getPosition(board.getNumPositions());

			if(players.get(playerId).getLaps(board.getNumPositions()) != players.get(playerId).getPreviousLap()){
				// Verifica se deu uma volta, para que receba os 500 reais da posição start.
				int aux = players.get(playerId).getLaps(board.getNumPositions()) - players.get(playerId).getPreviousLap();
				for(int j=0; j<aux; j++){
					players.get(playerId).receiveStartMoney();
				}
				//System.out.println(" Atual: " + players.get(playerId).getLaps(board.getNumPositions()) + " anterior: " + players.get(playerId).getPreviousLap());
				players.get(playerId).updatePreviosLap(aux);
			}

			if(board.getPosition(playerPosition).getType() == 2){ // Se o jogador caiu em 'passe a vez'
				players.get(playerId).updatePassTurn();
			}
			if(board.getPosition(playerPosition).getType() == 3){ // Se o jogador caiu em um imóvel
				if(board.getPosition(playerPosition).getProperty().isSold()){ // Se o imóvel já tem dono
					if(board.getPosition(playerPosition).getProperty().getOwner() != playerId){ // Se o imóvel não for dele mesmo
						if(players.get(playerId).getBalance() >= board.getPosition(playerPosition).getProperty().getTax()){
							// Se o jogador tem dinheiro para pagar o aluguel
							players.get(playerId).payRent(board.getPosition(playerPosition).getProperty().getTax());
							players.get(board.getPosition(playerPosition).getProperty().getOwner()).receiveRent(board.getPosition(playerPosition).getProperty().getTax());
						}
						else{ // Se o jogar não tem dinheiro para pagar -> ele perde o jogo
							players.get(playerId).setPlaying(false);
							// Tem que diminuir o dinheiro dele?
						}
					}
				}
				else{
					if(players.get(playerId).getBalance() >= board.getPosition(playerPosition).getProperty().getPrice()){
						// Se tiver dinheiro pra comprar:
						board.getPosition(playerPosition).getProperty().setSold(true);
						players.get(playerId).buyProperty(board.getPosition(playerPosition).getProperty().getPrice());
						board.getPosition(playerPosition).getProperty().setOwner(playerId);
					}
				}
			}
		}
	}

	/**
	 * Metodo que imprime as informacoes do estado do jogo, tal como definido em especificacao.
	 * @param board - Objeto do tipo 'Tabuleiro', que contem as informacoes das posicoes
	 * @param players - Lista de objetos do tipo 'Jogador'
	 */
	private static void printDump(Tabuleiro board, ArrayList<Jogador> players){
		int numPlayers = players.size();
		// Quantas rodadas o jogo teve?
		System.out.println("1:");
		for(int i = 0; i < numPlayers; i++){
			
		}
		
		// Quantas voltas foram dadas no tabuleiro por cada jogador?
		System.out.print("2:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getLaps(board.getNumPositions()));
			else
				System.out.print((i+1) + "-" + players.get(i).getLaps(board.getNumPositions()) + ";");		
		
		// Quanto de dinheiro cada jogador ficou (colocacao)?
		System.out.print("3:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getBalance());
			else
				System.out.print((i+1) + "-" + players.get(i).getBalance() + ";");
		
		// Qual foi a quantidade de aluguel recebida por cada jogador?
		System.out.print("4:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getRentEarned());
			else
				System.out.print((i+1) + "-" + players.get(i).getRentEarned() + ";");
		
		// Qual foi o valor pago de aluguel por cada jogador?
		System.out.print("5:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getRentPaid());
			else
				System.out.print((i+1) + "-" + players.get(i).getRentPaid() + ";");
		
		// Qual foi o valor gasto na compra de imóveis por cada jogador?
		System.out.print("6:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getPurchasedPropertyMoney());
			else
				System.out.print((i+1) + "-" + players.get(i).getPurchasedPropertyMoney() + ";");
		
		// Quantos “passa a vez” cada jogador teve?
		System.out.print("7:");
		for(int i = 0; i < numPlayers; i++)
			if(i == (numPlayers-1))
				System.out.println((i+1) + "-" + players.get(i).getPassTurn());
			else
				System.out.print((i+1) + "-" + players.get(i).getPassTurn() + ";");
	}
}
