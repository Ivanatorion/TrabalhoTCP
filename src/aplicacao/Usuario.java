package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Usuario implements Serializable {
	public static final int DIAS_PARA_SER_PROXIMO = 8;
	private static final long serialVersionUID = 1L;
	private String nome;
	private int numeroCartao;
	private List<Cadeira> cadeiras;
	private List<Turma> turmasAtivas;
	private Historico historico;
	
	private static final Scanner keyboard = principal.keyboard;;
	
	Usuario(String nomeUs, int nCartao){
		this.nome = nomeUs;
		this.numeroCartao = nCartao;
		this.cadeiras = new ArrayList<Cadeira>();
		this.turmasAtivas = new ArrayList<Turma>();
		this.historico = new Historico();
	}

	//Adiciona uma cadeira na lista de cadeiras
	public void adiciona_cadeira() {
		String tempNome;
		String tempCod;
		int escolha;
		List<Cadeira> prq = new ArrayList<Cadeira>();
		
		System.out.print("Digite o nome da cadeira: ");
		tempNome = keyboard.nextLine();
		System.out.print("Digite o codigo da cadeira: ");
		tempCod = keyboard.nextLine();
		
		//Verifica se ja existe cadeira com o mesmo codigo
		for(Cadeira c: this.getCadeiras()){
			if(c.getCodigo().equals(tempCod)){
				System.out.println("Erro: Ja existe uma cadeira com codigo " + tempCod + "! (" + c.getNome() + ")");
				return;
			}
		}
		
		if(!this.getCadeiras().isEmpty()){
			System.out.println("Escolha quais cadeiras sao pre requisitos da nova (0 para terminar): ");
			
			int i = 1;
			for(Cadeira c : this.getCadeiras()) {
				System.out.println(i + ") " + c.getNome());
				i++;
			}
			
			escolha = Integer.parseInt(principal.keyboard.nextLine())-1;
			while(escolha != -1) {
				if(escolha >= 0 && escolha < this.getCadeiras().size()) {
					prq.add(this.getCadeiras().get(escolha));
					System.out.println(this.getCadeiras().get(escolha).getNome() + " adicionada aos pre-requisitos");
				}
				escolha = Integer.parseInt(principal.keyboard.nextLine())-1;
			}
		}
		
		cadeiras.add(new Cadeira(tempNome, tempCod, prq));
	}
	
	//Finaliza uma cadeira com uma Turma ativa.
	//A turma � removida da lista de Turmas ativas e a cadeira � adicionada ao hist�rico.
	public void terminaCadeira(){
		int i = 1;
		int escolha = -1;
		for(Turma t : this.getTurmasAtivas()) {
			System.out.println(i + ") " + t.getCadeira().getNome());
			i++;
		}
		System.out.println(i + ") Cancelar");
		
		while(escolha < 0 || escolha >= i) {
			escolha = Integer.parseInt(principal.keyboard.nextLine())-1;
		}
		
		if(escolha == i-1)
			return;
		
		Turma turmaFinalizada = this.getTurmasAtivas().get(escolha);
		CadeiraFinalizada cf = new CadeiraFinalizada(turmaFinalizada.getCadeira(), turmaFinalizada.calculaMedia(), turmaFinalizada.getAno(), turmaFinalizada.getSemestre());
		this.getHistorico().addCadeiraFinalizada(cf);
		this.getTurmasAtivas().remove(escolha);
		
		System.out.println("Finalizada a cadeira de " + turmaFinalizada.getCadeira().getNome() + "!");
	}
	
	//Adiciona uma Turma de uma cadeira ainda n�o finalizada e n�o cursando � lista de Turmas ativas.
	public void adiciona_turma() {
		int i = 1;
		int escolha = -1;
		int tAno, tSem;
		String horario;
		System.out.println("Escolha uma cadeira: ");
		for(Cadeira c : this.getCadeiras()) {
			System.out.println(i + ") " + c.getNome());
			i++;
		}
		System.out.println(i + ") " + "Cancelar");
		
		while(escolha != i-1 && (escolha < 0 || escolha >= i))
			escolha = Integer.parseInt(keyboard.nextLine())-1;
		
		if(escolha == i-1)
			return;
		
		Cadeira c = this.getCadeiras().get(escolha);
		
		if(this.historico.finalizou(c)) {
			System.out.println("Voce ja finalizou a cadeira de " + c.getNome());
			return;
		}
		if(!this.getHistorico().finalizouPreReq(c)) {
			System.out.println("Voce nao finalizou todos os pre-requisitos da cadeira " + c.getNome());
			return;
		}
		if(c.isCursando()) {
			System.out.println("Voce ja esta cursando " + c.getNome());
			return;
		}
		
		try{
			System.out.print("Digite o Ano: ");
			tAno = Integer.parseInt(keyboard.nextLine());
			System.out.print("Digite o Semestre: ");
			tSem = Integer.parseInt(keyboard.nextLine());
			System.out.print("Digite os horários: ");
			horario = keyboard.nextLine();
		}
		catch(Exception e){
			System.out.println("Erro! Tente denovo...");
			return;
		}
		c.setCursando(true);
		this.getTurmasAtivas().add(new Turma(c, tAno, tSem, horario));
		System.out.println("Criada nova turma de " + c.getNome());
		
	}
	
	//Verifica se uma prova est� pr�xima
	private boolean estaProximo(Prova p, int aDia, int aMes){
		if((p.getMes() - aMes)*30 + p.getDia() - aDia < DIAS_PARA_SER_PROXIMO)
			return true;
		else
			return false;
	}
	
	//Verifica se um Trabalho est� pr�ximo
	private boolean estaProximo(Trabalho t, int aDia, int aMes){
		if((t.getMes() - aMes)*30 + t.getDia() - aDia < DIAS_PARA_SER_PROXIMO)
			return true;
		else
			return false;
	}
	
	//Lista as provas pr�ximas
	public List<Prova> getProvasProximas(){
		Calendar cal = Calendar.getInstance();
		int aDia = cal.get(Calendar.DAY_OF_MONTH);
		int aMes = cal.get(Calendar.MONTH) + 1;
		List<Prova> result = new ArrayList<Prova>();
		
		for(Turma t: turmasAtivas){
			for(Prova p: t.getProvas()){
				if(estaProximo(p, aDia, aMes))
					result.add(p);
			}
		}
		
		return result;
	}
	
	//Lista os Trabalhos pr�ximos
	public List<Trabalho> getTrabalhosProximos(){
		Calendar cal = Calendar.getInstance();
		int aDia = cal.get(Calendar.DAY_OF_MONTH);
		int aMes = cal.get(Calendar.MONTH) + 1;
		List<Trabalho> result = new ArrayList<Trabalho>();
		
		for(Turma t: turmasAtivas){
			for(Trabalho tr: t.getTrabalhos()){
				if(estaProximo(tr, aDia, aMes))
					result.add(tr);
			}
		}
		
		return result;
	}
	
	//Getters
	public String getNome() {
		return nome;
	}

	public int getNumeroCartao() {
		return numeroCartao;
	}

	public List<Cadeira> getCadeiras() {
		return cadeiras;
	}

	public List<Turma> getTurmasAtivas() {
		return turmasAtivas;
	}
	
	public Historico getHistorico(){
		return historico;
	}
}
