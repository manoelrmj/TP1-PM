public class Main {
	public static void main(String[] args) {
		Imovel p = new Imovel();
		p.meuNome();
		p.setIsSold(true);
		System.out.println(p.getIsSold());

		Hotel e = new Hotel();
		e.setPrice(100);
		e.meuNome();
		System.out.println(e.getPrice());
	}
}