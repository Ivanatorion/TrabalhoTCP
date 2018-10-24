package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cadeira implements Serializable {
	private String nome;
	private String codigo;
	private boolean cursando;
	private List<Cadeira> preRequisitos;
	
	Cadeira(String cNome, String cCod, List<Cadeira> prq){
		this.nome = cNome;
		this.codigo = cCod;
		this.cursando = false;
		this.preRequisitos = new ArrayList<Cadeira>();
		this.preRequisitos.addAll(prq);
	}
	
	Cadeira(String cNome, String cCod){
		this.nome = cNome;
		this.codigo = cCod;
		this.cursando = false;
		this.preRequisitos = new ArrayList<Cadeira>();
	}

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public boolean isCursando() {
		return cursando;
	}

	public List<Cadeira> getPreRequisitos() {
		return preRequisitos;
	}
}
