package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cadeira implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String codigo;
	private boolean cursando;
	private Set<Cadeira> preRequisitos;
	
	Cadeira(String cNome, String cCod, List<Cadeira> prq){
		this.nome = cNome;
		this.codigo = cCod;
		this.cursando = false;
		this.preRequisitos = new HashSet<Cadeira>();
		this.preRequisitos.addAll(prq);
	}
	
	Cadeira(String cNome, String cCod){
		this.nome = cNome;
		this.codigo = cCod;
		this.cursando = false;
		this.preRequisitos = new HashSet<Cadeira>();
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

	public void setCursando(boolean b) {
		this.cursando = b;
	}
	
	public Set<Cadeira> getPreRequisitos() {
		return preRequisitos;
	}
	
	public void addPreRequisitos(Cadeira cadeira) {
		this.preRequisitos.add(cadeira);
	}

}
