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
		cadeiras = new ArrayList<Cadeira>();
	}

	public void adiciona_cadeira() {
		String tempNome;
		String tempCod;
		
		System.out.println("Digite o nome da cadeira: ");
		tempNome = keyboard.nextLine();
		System.out.print("Digite o codigo da cadeira: ");
		tempCod = keyboard.nextLine();
		
		//Prerequisitos?
		
		cadeiras.add(new Cadeira(tempNome, tempCod));
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
