package aplicacao;

import java.io.IOException;
import java.util.Scanner;

public class principal {

	private static Usuario usuario;
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	private enum Operacao {
		ADD_CADEIRA(1), ADD_TURMA(2), TERMINA_CADEIRA(3), LISTAR_CADEIRAS(4), SAVE_QUIT(5);
		
		private final String[] nomes = {
				"Adicionar Cadeira", 
				"Adicionar Turma", 
				"Finalizar Cadeira",
				"Listar Cadeiras",
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
		
		return Operacao.values()[Integer.parseInt(keyboard.nextLine())-1]; //Deve precisar de um try/catch
	}
	
	public static void inicializa() {
		String tempNome;
		int tempCartao;
		
		usuario = Persistencia.carrega_usuario();
		
		System.out.println("Nome: " + usuario.getNome());
		System.out.println("Cartao: " + usuario.getNumeroCartao());
	}
	
	private static void listaCadeiras() {
		for(Cadeira c : usuario.getCadeiras()) {
			System.out.println(c.getNome());
		}
		System.out.println("");
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
					break;
				case TERMINA_CADEIRA:
					break;
				case LISTAR_CADEIRAS:
					listaCadeiras();
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
