import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	// Variaveis de manipulação de arquivos
	private static BufferedReader readArq;
	private static FileReader arq;
	
	public static void main(String[] args) {
		String path = "testes-alunos/entrada/tabuleiro.txt";
		String line = "";		

		// Variaveis para criação do tabuleiro
		Tabuleiro board = new Tabuleiro();
		ArrayList<String> boardDescription = new ArrayList<String>();
		Posicao tmpPosition;
		Imovel tmpProperty = null;
		int id, position, type, propertyType, propertyValue, propertyTax;
		
		// Manipulação de arquivo
		try {
			arq = new FileReader(path);
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
		
		board.setNumPositions(Integer.parseInt(boardDescription.get(0)));
		
		for (int i=1; i<boardDescription.size(); i++){
			if(boardDescription.get(i) == null)
				break;
			String[] positionInput = boardDescription.get(i).split(";");
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
		
		// Teste - impressão dos dados do imóvel da posição de ID 4
		Posicao p = board.getPosition(4);
		System.out.println("ID: " + p.getId());
		System.out.println("Position: " + p.getPosition());
		System.out.println("Position tpye: " + p.getType());
		System.out.println("Property type: " + p.getProperty().getType());
		System.out.println("Property value: " + p.getProperty().getPrice());
		System.out.println("Property tax: " + p.getProperty().getTax());
	}
}