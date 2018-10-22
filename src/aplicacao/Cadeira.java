package aplicacao;

import java.util.ArrayList;
import java.util.List;

public class Cadeira {
	private String nome;
	private String codigo;
	private boolean cursando;
	private List<Cadeira> preRequisitos;
	
	Cadeira(List<Cadeira> prq){
		this.preRequisitos = new ArrayList<Cadeira>();
		this.preRequisitos.addAll(prq);
	}
}
