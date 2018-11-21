package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cadeira cadeira;
	private int ano;
	private int semestre;
	// private int quantidadeDeFaltas;
	private String horarios;
	private String professor;
	private int faltas;
	
	private List<Avaliacao> avaliacoes;
	
	private enum Operacao {
		ADD_AVALIACAO(1), VER_AVALIACOES(2), NOTA_AVALIACAO(3), ESTIMA_MEDIA(4), ADICIONA_FALTA(5), VER_FALTAS(6), VOLTAR(7);
		
		private final String[] nomes = {
				"Adicionar Avaliacao",
				"Ver Avaliacoes",
				"Inserir nota de Avaliacao",
				"Estima Media",
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
		this.avaliacoes = new ArrayList<Avaliacao>();
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
   
	private void addAvaliacao() {
		String n;
		double peso;
		int dia, mes;
		
		try {
			System.out.println("Digite o nome da Avaliacao: ");
			n = principal.keyboard.nextLine();
			System.out.println("Digite o peso da Avaliacao (0 - 1): ");
			peso = Double.parseDouble(principal.keyboard.nextLine());
			System.out.println("Digite a Data da Avaliacao");
			System.out.print("Mes: ");
			mes = Integer.parseInt(principal.keyboard.nextLine());
			System.out.print("Dia: ");
			dia = Integer.parseInt(principal.keyboard.nextLine());
		}
		catch (Exception e) {
			System.out.println("ERRO! Tente denovo...");
			return;
		}
		
		this.getAvaliacoes().add(new Avaliacao(n, peso, dia, mes, cadeira));
		System.out.println("Avaliacao Adicionada!");
		
	}
	

	
	//Mostra as Avaliacoes
	private void verAvaliacoes() {
		int i = 1;
		for(Avaliacao a : this.getAvaliacoes()) {
			System.out.print(i + ") " + a.getNome() + " " + a.getDia() + "/" + a.getMes());
			if(a.getNota() > 0.0001)
				System.out.printf(" Nota: %.2f", a.getNota());
			System.out.printf("\n");
			i++;
		}
		
	}
	

	//Insere nota de Prova
	public void insereNotaAvaliacao() {
		int i = 0;
		double n = 0;
		if(this.getAvaliacoes().size() != 0) {
			this.verAvaliacoes();
			try{
				i = Integer.parseInt(principal.keyboard.nextLine())-1;
				if(i >= this.getAvaliacoes().size()){
					System.out.println("Erro! Tente denovo...");
					return;
				}
				System.out.print("Digite a nota da Avaliacao: ");
				n = Double.parseDouble(principal.keyboard.nextLine());
				this.getAvaliacoes().get(i).setNota(n);
			}
			catch(NumberFormatException e){
				System.out.println("Erro! Tente denovo...");
				return;
			}	
		}
		else
			System.out.println("N�o h� Avaliacao nessa turma!");
	}
	
	
	//Calcula a media final
	public double calculaMedia() {
		double media =0;
		double somaPeso=0;
				
		for(Avaliacao a : avaliacoes) {
			if(a.getNota()>=0) 
				media += a.getPeso()*a.getNota();
			somaPeso += a.getPeso();
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
				
		for(Avaliacao a : avaliacoes) {
			if(a.getNota()>=0) {
			media += a.getPeso()*a.getNota();
			somaPeso += a.getPeso();		
			}
		}
		
		if(somaPeso == 0)
			media = 0;
		else
			media = media/somaPeso;
		return media;
	}

	public void addAvaliacao(Avaliacao a){
		this.getAvaliacoes().add(a);
	}
	

	//Menu de opcoes relacionadas a Turma
	public void listarOpcoes() {
		boolean loop = true;
		System.out.println("Turma de " + this.getCadeira().getNome());
		System.out.println("Horarios: " + this.getHorarios());
		System.out.println("Professor: " + this.getProfessor());
		while(loop) {
			switch(obtem_operacao()) {
				case ADD_AVALIACAO:
					this.addAvaliacao();
					break;
				case VER_AVALIACOES:
					this.verAvaliacoes();
					break;
				case NOTA_AVALIACAO:
					this.insereNotaAvaliacao();
					break;
				case ESTIMA_MEDIA:
					System.out.printf("Estimativa: %.2f\n", this.estimaMedia());
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

	public String getHorarios() {
		return horarios;
	}

	public String getProfessor() {
		return professor;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	public int getFaltas() {
		return faltas;
	}
	
	public void addFalta() {
		faltas += 1;
	}
}

