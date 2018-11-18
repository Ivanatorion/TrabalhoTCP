package aplicacao;


import java.util.List;
import java.util.Scanner;

public class principal {

	private static Usuario usuario;
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	private enum Operacao {
		ADD_CADEIRA(1), ADD_TURMA(2), TERMINA_CADEIRA(3), LISTAR_CADEIRAS(4), LISTAR_TURMAS(5), ATIV_PROX(6), VER_HIST(7), SAVE_QUIT(8);
		
		private final String[] nomes = {
				"Adicionar Cadeira", 
				"Adicionar Turma", 
				"Finalizar Cadeira",
				"Listar Cadeiras",
				"Listar Turmas",
				"Atividades Proximas",
				"Ver Historico",
				"Salvar e Sair"
		};
		
		private final int valor;
		
	    Operacao(int valorOpcao){
	        valor = valorOpcao;
	    }
	    
	    public int getNumero(){
	        return valor;
	    }

		@Override
		public String toString() {
			return nomes[valor-1];
		}
	}
	
	private static Operacao obtem_operacao() {
		
		for(Operacao opcao : Operacao.values()) {
			System.out.println(opcao.getNumero() + " - " + opcao.toString());
		}
		
		boolean ok = false;
		while(!ok){
			try{
				ok = true;
				return Operacao.values()[Integer.parseInt(principal.keyboard.nextLine())-1];
			}
			catch(ArrayIndexOutOfBoundsException e){
				ok = false;
			}
		}
		return null; //Nao vai acontecer
	}
	
	public static void inicializa() {
		usuario = Persistencia.carrega_usuario();
		
		System.out.println("Nome: " + usuario.getNome());
		System.out.println("Cartao: " + usuario.getNumeroCartao());
	}
	
	private static void listaCadeiras() {
		for(Cadeira c : usuario.getCadeiras()) {
			System.out.println("Cadeira: " + c.getNome() + " | Codigo: " + c.getCodigo());
		}
	}
	
	//Mostra a lista de Turmas ativas e lista as opcoes da Turma selecionada
	private static void listaTurmas() {
		int i = 1;
		int escolha = -1;
		
		if(usuario.getTurmasAtivas().isEmpty()) {
			System.out.println("Sem turmas ativas!");
			return;
		}
		
		for(Turma c : usuario.getTurmasAtivas()) {
			System.out.println(i + ") " + c.getCadeira().getNome());
			i++;
		}
		System.out.println(i + ") " + "Voltar");
		
		while(escolha != i-1 && (escolha < 0 || escolha >= i))
			escolha = Integer.parseInt(keyboard.nextLine())-1;
		
		if(escolha == i-1)
			return;
		
		usuario.getTurmasAtivas().get(escolha).listarOpcoes();
	}
	
	//Lista as Provas e Trabalhos próximos (Dentro de 8 dias) e a Cadeira relacionada
	private static void listaAtividadesProximas(){
		List<Prova> provasProximas = usuario.getProvasProximas();
		List<Trabalho> trabalhosProximos = usuario.getTrabalhosProximos();
		
		if(provasProximas.isEmpty() && trabalhosProximos.isEmpty()){
			System.out.println("Sem atividades nos proximos " + Usuario.DIAS_PARA_SER_PROXIMO + " dias!");
		}
		
		for(Prova p : provasProximas){
			System.out.println(p.getNome() + " (" + p.getCadeira().getNome() + "): " + p.getDia() + "/" + p.getMes());
		}
		for(Trabalho t: trabalhosProximos){
			System.out.println(t.getNome() + " (" + t.getCadeira().getNome() + "): " + t.getDia() + "/" + t.getMes());
		}
	}
	
	//Mostra as cadeiras finalizadas
	private static void verHistorico(){
		if(usuario.getHistorico().getCadeirasFinalizadas().isEmpty()){
			System.out.println("Sem cadeiras no historico!");
		}
		for(CadeiraFinalizada cf: usuario.getHistorico().getCadeirasFinalizadas()){
			System.out.println("Cadeira: " + cf.getCadeira().getNome() + String.format(" | Nota: %.2f", cf.getNotaFinal()) + " | Ano: " + cf.getAno() + " | Semestre: " + cf.getSemestre());
		}
	}
	
	public static void main(String[] args) {
		boolean loop = true;
		 
		inicializa();
	
		while(loop) {
			switch(obtem_operacao()) {
				case ADD_CADEIRA:
					usuario.adiciona_cadeira();
					break;
				case ADD_TURMA:
					usuario.adiciona_turma();
					break;
				case TERMINA_CADEIRA:
					usuario.terminaCadeira();
					break;
				case LISTAR_CADEIRAS:
					listaCadeiras();
					break;
				case LISTAR_TURMAS:
					listaTurmas();
					break;
				case ATIV_PROX:
					listaAtividadesProximas();
					break;
				case VER_HIST:
					verHistorico();
					break;
				case SAVE_QUIT:
					Persistencia.salva_usuario(usuario);
					loop = false;
					break;
			}
		}
		
		keyboard.close();
	}

}
