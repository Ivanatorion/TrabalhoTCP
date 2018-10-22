package aplicacao;

import java.util.List;

public class Usuario {
	private String nome;
	private int numeroCartao;
	private List<Cadeira> cadeiras;
	private List<Turma> turmasAtivas;
	//private Historico historico;
	
	Usuario(String nomeUs, int nCartao){
		this.nome = nomeUs;
		this.numeroCartao = nCartao;
	}
}
