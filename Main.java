public class Main {
	public static void main(String[] args) {
		Imovel p = new Imovel();
		p.setIsSold(true);
		System.out.println(p.getIsSold());

		Hotel e = new Hotel();
		e.setPrice(100);
		System.out.println(e.getPrice());
	}
}