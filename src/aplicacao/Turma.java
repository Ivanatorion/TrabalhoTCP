package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Turma implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cadeira cadeira;
	private int ano;
	private int semestre;
	private int quantidadeDeFaltas;
	private String horarios; //Por quÃª isso Ã© string? 
	private String professor;
	int faltas;
	
	private List<Trabalho> trabalhos;
	private List<Prova> provas;
	
	private enum Operacao {
		ADD_PROVA(1), ADD_TRABALHO(2), VER_PROVAS(3), VER_TRABALHOS(4), NOTA_PROVA(5), NOTA_TRABALHO(6), ESTIMA_MEDIA(7), ADICIONA_FALTA(8), VER_FALTAS(9), VOLTAR(10);
		
		private final String[] nomes = {
				"Adicionar Prova",
				"Adicionar Trabalho",
				"Ver Provas",
				"Ver Trabalhos",
				"Inserir nota de Prova",
				"Inserir nota de Trabalho",
				"Estima MÃ©dia",
				"Adiciona Falta",
				"Ver faltas",
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
	
	Turma(Cadeira c, int an, int sem, String horario, String professor){
		this.provas = new ArrayList<Prova>();
		this.trabalhos = new ArrayList<Trabalho>();
		this.cadeira = c;
		this.ano = an;
		this.semestre = sem;
		this.horarios = horario;
		this.professor = professor;
		this.faltas = 0;
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
		
		this.getProvas().add(new Prova(n, peso, dia, mes, cadeira));
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
		
		this.getTrabalhos().add(new Trabalho(n, peso, dia, mes, cadeira));
		System.out.println("Trabalho Adicionado!");
		
	}
	
	private void verProvas() {
		int i = 1;
		int escolha = -1;
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
		System.out.println("Professora: " + this.getProfessor());
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
				case NOTA_PROVA:
					this.insereNotaProva();
					break;
				case NOTA_TRABALHO:
					this.insereNotaTrabalho();
					break;
				case ESTIMA_MEDIA:
					System.out.println("Estimativa: " + this.estimaMedia());
					break;
				case ADICIONA_FALTA:
					this.addFalta();
					System.out.println("Adicionada falta. Faltas: " + this.getFaltas());
					break;
				case VER_FALTAS:
					System.out.println("Faltas: " + this.getFaltas());
					break;
				case VOLTAR:
					loop = false;
					break;
				default:
					break;
			}
		}
	}


	//Insere nota de avaliações;
	public void insereNotaProva() {
		Scanner reader = new Scanner(System.in);
		int i = 0;
		double n = 0;
		int j = 0;
		if(provas.size() != 0) {
			this.verProvas();
			i = Integer.parseInt(principal.keyboard.nextLine())-1;
			for(Prova p : provas) {
				if(j==i) {
					System.out.println("Digite a nota da prova");
					n = reader.nextDouble();
					p.setNota(n);
					j++;
				}
				else
					j++;
			}
		}
		else
			System.out.println("Não há provas nessa turma!");
	}
	
	public void insereNotaTrabalho() {
		Scanner reader = new Scanner(System.in);
		int i = 0;
		double n = 0;
		int j = 0;
		if(trabalhos.size() != 0) {
			this.verTrabalhos();
			i = Integer.parseInt(principal.keyboard.nextLine())-1;
			for(Trabalho t : trabalhos) {
				if(j==i) {
					System.out.println("Digite a nota do trabalho");
					n = reader.nextDouble();
					t.setNota(n);
					j++;
				}
				else
					j++;
			}
		}
		else
			System.out.println("Não há trabalhos nessa turma!");
	}
	
	//Calcula a media final;
	public double calculaMedia() {
		double media =0;
		double somaPeso=0;
				
		for(Prova p : provas) {
			if(p.getNota()>=0) 
				media += p.getPeso()*p.getNota();
			somaPeso += p.getPeso();
		}
		
		for(Trabalho t : trabalhos) {
			if(t.getNota() >= 0)
				media += t.getPeso()*t.getNota();
			somaPeso += t.getPeso();
		}
		
		if(somaPeso == 0)
			media = 0;
		else
			media = media/somaPeso;
		return media;
	}
	
	public double estimaMedia() {
		double media = 0;
		double somaPeso = 0;
				
		for(Prova p : provas) {
			if(p.getNota()>=0) {
			media += p.getPeso()*p.getNota();
			somaPeso += p.getPeso();		
			}
		}
		
		for(Trabalho t : trabalhos) {
			if(t.getNota()>=0) {
			media += t.getPeso()*t.getNota();
			somaPeso += t.getPeso();
			}
		}
		
		if(somaPeso == 0)
			media = 0;
		else
			media = media/somaPeso;
		return media;
	}

	public void addProva(Prova p){
		this.getProvas().add(p);
	}
	
	public void addTrabalho(Trabalho t){
		this.getTrabalhos().add(t);
	}
	
	//Getters
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
	
	public int getFaltas() {
		return faltas;
	}
	
	public void addFalta() {
		faltas += 1;
	}
}

