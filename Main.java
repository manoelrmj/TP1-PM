import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import imoveis.*;

public class Main {
	// Variaveis de manipulação de arquivos
	private static BufferedReader readArq;
	private static FileReader arq;
	/**
	 * Metodo responsavel pela leitura do arquivo 'tabuleiro.txt'
	 * @param boardFilePath - Caminho do arquivo de entrada com informacoes do tabuleiro
	 * @return Lista das strings lidas do arquivo (linha a linha) 
	 */
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

	
	/**
	 * Metodo responsavel pela leitura do arquivo 'jogadas.txt'
	 * @param movesFilePath - Caminho do arquivo de entrada com informaçoes das jogadas
	 * @return Lista de strings lidas do arquivo (linha a linha)
	 */
	public static ArrayList<String> readMovesFile(String movesFilePath){
		ArrayList<String> movesDescription = new ArrayList<String>();
		String line = "";
		// Manipulação do arquivo jogadas
		try {
			arq = new FileReader(movesFilePath);
			readArq = new BufferedReader(arq);
			while(line != null){
				line = readArq.readLine();
				//System.out.println(line);
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

	/**
	 * Metodo responsavel pela criacao do objeto Tabuleiro a partir das strings lidas do arquivo de entrada.
	 * @param boardDescription - Lista de strings lidas do arquivo
	 * @return Objeto do tipo Tabuleiro, já com as devidas posicoes e imoveis
	 */
	public static Tabuleiro buildBoard(ArrayList<String> boardDescription){
		String[] positionInput;
		Posicao auxPosition;
		Imovel auxProperty = null;
		int id, position, type, propertyType, propertyValue, propertyTax;
		
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
				auxPosition = new Posicao(id, position, type);
				board.addPosition(auxPosition);
			}else{ // Se a posição é um 'Imovel'
				propertyType = Integer.parseInt(positionInput[3]);
				propertyValue = Integer.parseInt(positionInput[4]);
				propertyTax = Integer.parseInt(positionInput[5]);
				switch (propertyType){
					case 1: // Residência
						auxProperty = new Residencia(propertyValue, propertyTax);
						break;
					case 2: // Comércio
						auxProperty = new Comercio(propertyValue, propertyTax);
						break;
					case 3: // Indústria
						auxProperty = new Industria(propertyValue, propertyTax);
						break;
					case 4: // Hotel
						auxProperty = new Hotel(propertyValue, propertyTax);
						break;
					case 5: // Hospital
						auxProperty = new Hospital(propertyValue, propertyTax);
						break;
				}
					
				auxPosition = new Posicao(id, position, type, auxProperty);
				board.addPosition(auxPosition);
			}
		}
		board.sortPositions();
		return board;
	}

	/**
	 * Metodo responsavel por criar uma lista de objetos do tipo 'Jogada'.
	 * Cada objeto nessa lista representa uma jogada lida da entrada.
	 * @param movesDescription - Lista de strings que descrevem as jogadas, lidas do arquivo de entrada.
	 * @return Lista de objetos do tipo 'Jogada'
	 */
	public static ArrayList<Jogada> buildMoves(ArrayList<String> movesDescription){
		ArrayList<Jogada> jogadas = new ArrayList<Jogada>();
		Jogada auxJogada;
		String[] auxMove;
		int auxId, auxPlayerId, auxDiceValue;
		// Cria o arraylist de jogadas
		for (int i=1; i<movesDescription.size(); i++){
			//System.out.println(movesDescription.get(i));
			if(movesDescription.get(i) == null){
				break;
			}else if(movesDescription.get(i).equals("DUMP")){
				auxJogada = new Jogada(0, 0, 0); // Constroi jogada do tipo "DUMP"
				jogadas.add(auxJogada);
			}else{
				auxMove = movesDescription.get(i).split(";");
				auxId = Integer.parseInt(auxMove[0]);
				auxPlayerId = Integer.parseInt(auxMove[1]);
				auxDiceValue = Integer.parseInt(auxMove[2]);
				auxJogada = new Jogada(auxId, auxPlayerId, auxDiceValue);
				jogadas.add(auxJogada);	
			}
		}
		return jogadas;
	}

	public static void main(String[] args) {
		// Leitura dos aquivos
		String boardFilePath = "testes/tabuleiro_2.txt";
		String movesFilePath = "testes/jogadas_2.txt";
		ArrayList<String> boardDescription = readBoardFile(boardFilePath);
		ArrayList<String> movesDescription = readMovesFile(movesFilePath);
				
		// Construcao do tabuleiro
		Tabuleiro board = buildBoard(boardDescription);
		
		// Construcao das jogadas e dados da partida
		String[] matchInfo; // Leitura de informacoes da partida
		int numPlayers, initialBalance;
		matchInfo = movesDescription.get(0).split("%"); 
		numPlayers = Integer.parseInt(matchInfo[1]);
		initialBalance = Integer.parseInt(matchInfo[2]);
		ArrayList<Jogada> moves = buildMoves(movesDescription);
		
		// Criacao dos jogadores
		ArrayList<Jogador> players = new ArrayList<Jogador>();
		for (int i = 1; i<=numPlayers; i++){
			Jogador auxJogador = new Jogador(i, initialBalance);
			players.add(auxJogador);
		}
		
		Jogada.setPlayersPlaying(players.size());
		// Execucao das jogadas
		for(int i = 0; i<moves.size(); i++)
			Jogada.runMove(board, players, moves.get(i));
	}
}

