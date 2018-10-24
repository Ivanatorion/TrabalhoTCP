package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turma implements Serializable{
	Cadeira cadeira;
	private int ano;
	private int semestre;
	private int quantidadeDeFaltas;
	private String horarios; //Por quê isso é string?
	private String professor;
	
	private List<Trabalho> trabalhos;
	private List<Prova> provas;
	
	Turma(){
		trabalhos = new ArrayList<Trabalho>();
	}
}
