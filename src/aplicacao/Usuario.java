package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario implements Serializable {
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

	public void adiciona_cadeira() {
		String tempNome;
		String tempCod;
		int escolha;
		List<Cadeira> prq = new ArrayList<Cadeira>();
		
		System.out.println("Digite o nome da cadeira: ");
		tempNome = keyboard.nextLine();
		System.out.print("Digite o codigo da cadeira: ");
		tempCod = keyboard.nextLine();
		
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
		
		cadeiras.add(new Cadeira(tempNome, tempCod, prq));
	}
	
	public void adiciona_turma() {
		int i = 1;
		int escolha = -1;
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
		c.setCursando(true);
		this.getTurmasAtivas().add(new Turma(c));
		System.out.println("Criada nova turma de " + c.getNome());
		
	}
	
	public void terminaCadeira(Cadeira c){

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
