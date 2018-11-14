package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Turma implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cadeira cadeira;
	private int ano;
	private int semestre;
	private int quantidadeDeFaltas;
	private String horarios; //Por quê isso é string?
	private String professor;
	
	private List<Trabalho> trabalhos;
	private List<Prova> provas;
	
	private enum Operacao {
		ADD_PROVA(1), ADD_TRABALHO(2), VER_PROVAS(3), VER_TRABALHOS(4), VOLTAR(5);
		
		private final String[] nomes = {
				"Adicionar Prova",
				"Adicionar Trabalho",
				"Ver Provas",
				"Ver Trabalhos",
				"Voltar"
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
		
		return Operacao.values()[Integer.parseInt(principal.keyboard.nextLine())-1]; //Deve precisar de um try/catch
	}
	
	Turma(Cadeira c){
		this.provas = new ArrayList<Prova>();
		this.trabalhos = new ArrayList<Trabalho>();
		this.cadeira = c;
	}

	public Cadeira getCadeira() {
		return cadeira;
	}

	public int getAno() {
		return ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public int getQuantidadeDeFaltas() {
		return quantidadeDeFaltas;
	}

	public String getHorarios() {
		return horarios;
	}

	public String getProfessor() {
		return professor;
	}

	public List<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	private void addProva() {
		String n;
		double peso;
		int dia, mes;
		
		try {
			System.out.println("Digite o nome da prova: ");
			n = principal.keyboard.nextLine();
			System.out.println("Digite o peso da prova (0 - 1): ");
			peso = Double.parseDouble(principal.keyboard.nextLine());
			System.out.println("Digite a Data da prova");
			System.out.print("Mes: ");
			mes = Integer.parseInt(principal.keyboard.nextLine());
			System.out.print("Dia: ");
			dia = Integer.parseInt(principal.keyboard.nextLine());
		}
		catch (Exception e) {
			System.out.println("ERRO! Tente denovo...");
			return;
		}
		
		this.getProvas().add(new Prova(n, peso, dia, mes));
		System.out.println("Prova Adicionada!");
		
	}
	
	private void addTrabalho() {
		String n;
		double peso;
		int dia, mes;
		
		try {
			System.out.println("Digite o nome do trabalho: ");
			n = principal.keyboard.nextLine();
			System.out.println("Digite o peso do trabalho (0 - 1): ");
			peso = Double.parseDouble(principal.keyboard.nextLine());
			System.out.println("Digite a Data do trabalho");
			System.out.print("Mes: ");
			mes = Integer.parseInt(principal.keyboard.nextLine());
			System.out.print("Dia: ");
			dia = Integer.parseInt(principal.keyboard.nextLine());
		}
		catch (Exception e) {
			System.out.println("ERRO! Tente denovo...");
			return;
		}
		
		this.getTrabalhos().add(new Trabalho(n, peso, dia, mes));
		System.out.println("Trabalho Adicionado!");
		
	}
	
	private void verProvas() {
		int i = 1;
		for(Prova p : this.getProvas()) {
			System.out.println(i + ") " + p.getNome() + " " + p.getDia() + "/" + p.getMes());
			i++;
		}
	}
	
	private void verTrabalhos() {
		int i = 1;
		for(Trabalho t : this.getTrabalhos()) {
			System.out.println(i + ") " + t.getNome() + " " + t.getDia() + "/" + t.getMes());
			i++;
		}
	}
	
	public void listarOpcoes() {
		boolean loop = true;
		System.out.println("Turma de " + this.getCadeira().getNome());
		System.out.println("Horarios: " + this.getHorarios());
		while(loop) {
			switch(obtem_operacao()) {
				case ADD_PROVA:
					this.addProva();
					break;
				case ADD_TRABALHO:
					this.addTrabalho();
					break;
				case VER_PROVAS:
					this.verProvas();
					break;
				case VER_TRABALHOS:
					this.verTrabalhos();
					break;
				case VOLTAR:
					loop = false;
					break;
			}
		}
	}
}
