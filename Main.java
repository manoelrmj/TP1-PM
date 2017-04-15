import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	// Variaveis de manipulação de arquivos
	private static BufferedReader readArq;
	private static FileReader arq;
	
	public static ArrayList<String> readBoardFile(String boardFilePath){
		String line = "";
		ArrayList<String> boardDescription = new ArrayList<String>();
		// Manipulação do arquivo tabuleiro
		try {
			arq = new FileReader(boardFilePath);
			readArq = new BufferedReader(arq);
			while(line != null){
				line = readArq.readLine();
				boardDescription.add(line);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo tabuleiro.txt");
		} finally { // Garantia de que sempre será feita a tentativa de fechar o arquivo
			try {
				arq.close();
			} catch (IOException e) {
				System.out.println("Erro ao fechar o arquivo tabuleiro.txt");
			}
		}
		return boardDescription;
	}

	public static ArrayList<String> readMovesFile(String movesFilePath){
		ArrayList<String> movesDescription = new ArrayList<String>();
		String line = "";
		// Manipulação do arquivo jogadas
		try {
			arq = new FileReader(movesFilePath);
			readArq = new BufferedReader(arq);
			while(line != null){
				line = readArq.readLine();
				System.out.println(line);
				movesDescription.add(line);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo jogadas.txt");
		} finally { // Garantia de que sempre será feita a tentativa de fechar o arquivo
			try {
				arq.close();
			} catch (IOException e) {
				System.out.println("Erro ao fechar o arquivo jogadas.txt");
			}
		}
		return movesDescription;
	}

	
	public static void main(String[] args) {
		// Leitura dos aquivos
		String boardFilePath = "testes-alunos/entrada/tabuleiro.txt";
		String movesFilePath = "testes-alunos/entrada/jogadas.txt";
		ArrayList<String> boardDescription = readBoardFile(boardFilePath);
		ArrayList<String> movesDescription = readMovesFile(movesFilePath);
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>(); 
		
		// Variaveis para criação do tabuleiro e de jogadas		
		String[] positionInput;
		Posicao tmpPosition;
		Imovel tmpProperty = null;
		int id, position, type, propertyType, propertyValue, propertyTax;
		String[] movesInfo;
		String[] movesData;
		int numMoves, numPlayers, initialBalance;
		int playerId, diceNumber, playerPosition;
		
		Tabuleiro board = new Tabuleiro();
		board.setNumPositions(Integer.parseInt(boardDescription.get(0)));
		
		// Construcao do tabuleiro
		for (int i=1; i<boardDescription.size(); i++){
			if(boardDescription.get(i) == null)
				break;
			positionInput = boardDescription.get(i).split(";");
			id = Integer.parseInt(positionInput[0]);
			position = Integer.parseInt(positionInput[1]);
			type = Integer.parseInt(positionInput[2]);

			if(positionInput[2].equals("1") || positionInput[2].equals("2")){ // Se a posição é do tipo 'Start' ou 'Passe a vez'
				tmpPosition = new Posicao(id, position, type);
				board.addPosition(tmpPosition);
			}else{ // Se a posição é um 'Imovel'
				propertyType = Integer.parseInt(positionInput[3]);
				propertyValue = Integer.parseInt(positionInput[4]);
				propertyTax = Integer.parseInt(positionInput[5]);
				switch (propertyType){
					case 1: // Residência
						tmpProperty = new Residencia(propertyValue, propertyTax);
						break;
					case 2: // Comércio
						tmpProperty = new Comercio(propertyValue, propertyTax);
						break;
					case 3: // Indústria
						tmpProperty = new Industria(propertyValue, propertyTax);
						break;
					case 4: // Hotel
						tmpProperty = new Hotel(propertyValue, propertyTax);
						break;
					case 5: // Hospital
						tmpProperty = new Hospital(propertyValue, propertyTax);
						break;
				}
					
				tmpPosition = new Posicao(id, position, type, tmpProperty);
				board.addPosition(tmpPosition);
			}
		}
		
		// Leitura das jogadas
		movesInfo = movesDescription.get(0).split("%"); 
		numMoves = Integer.parseInt(movesInfo[0]);
		numPlayers = Integer.parseInt(movesInfo[1]);
		initialBalance = Integer.parseInt(movesInfo[2]);

		// Cria o arraylist com os jogadores
		for (int i = 1; i<=numPlayers; i++){
			Jogador jogador = new Jogador(i, initialBalance);
			jogadores.add(jogador);
		}

		// O jogo tambem tem que parar quando só 1 jogador tiver dinheiro!!!!!!!!!!!!!!!!!!!!!!!!!
		// Falta receber 500 quando passa no star!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// Falta devolver o imovel ao morrer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		for (int i=1; i<movesDescription.size(); i++){
			if(movesDescription.get(i) == null){ 
				break;
			}else if(movesDescription.get(i).equals("DUMP")){
				System.out.println("DUMP"); // DUMP results to file
			}else{
				// Executar jogadas
				movesData =  movesDescription.get(i).split(";");
				playerId = (Integer.parseInt(movesData[1]) - 1);
				diceNumber = Integer.parseInt(movesData[2]);
				jogadores.get(playerId).updateDiceCounter(diceNumber);
				playerPosition = jogadores.get(playerId).getPosition(board.getNumPositions());

				if(board.getPosition(playerPosition).getType() == 2){ // Se o jogador caiu em 'passe a vez'
					jogadores.get(playerId).updatePassTurn();
				}
				if(board.getPosition(playerPosition).getType() == 3){ // Se o jogador caiu em um imóvel
					if(board.getPosition(playerPosition).getProperty().isSold()){ // Se o imóvel já tem dono
						if(jogadores.get(playerId).getBalance() >= board.getPosition(playerPosition).getProperty().getTax()){
							// Se o jogador tem dinheiro para pagar o aluguel
							jogadores.get(playerId).payRent(board.getPosition(playerPosition).getProperty().getTax());
							jogadores.get(board.getPosition(playerPosition).getProperty().getOwner()).receiveRent(board.getPosition(playerPosition).getProperty().getTax());
						}
						else{ // Se o jogar não tem dinheiro para pagar -> ele perde o jogo
							jogadores.get(playerId).setPlaying(false);
							// Tem que diminuir o dinheiro dele?
						}

					}
					else{
						if(jogadores.get(playerId).getBalance() >= board.getPosition(playerPosition).getProperty().getPrice()){
							// Se tiver dinheiro pra comprar:
							board.getPosition(playerPosition).getProperty().setSold(true);
							jogadores.get(playerId).buyProperty(board.getPosition(playerPosition).getProperty().getPrice());
							board.getPosition(playerPosition).getProperty().setOwner(playerId);
						}
					}
				}
			}
		}


		// Saida:
		System.out.println("1:");
		for(int i = 0; i < numPlayers; i++){
			
		}
		System.out.print("2:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getLaps(board.getNumPositions()));
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getLaps(board.getNumPositions()) + ";");
			}
		}
		System.out.print("3:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getBalance());
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getBalance() + ";");
			}
		}
		System.out.print("4:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getRentEarned());
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getRentEarned() + ";");
			}
		}
		System.out.print("5:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getRentPaid());
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getRentPaid() + ";");
			}
		}
		System.out.print("6:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getPurchasedPropertyMoney());
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getPurchasedPropertyMoney() + ";");
			}
		}
		System.out.print("7:");
		for(int i = 0; i < numPlayers; i++){
			if(i == (numPlayers-1)){
				System.out.println((i+1) + "-" + jogadores.get(i).getPassTurn());
			}
			else{
				System.out.print((i+1) + "-" + jogadores.get(i).getPassTurn() + ";");
			}
		}
	}
}
