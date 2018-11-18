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
	public void adiciona_cadeira(Cadeira cadeira) {
		cadeiras.add(cadeira);
	}
	

	// adiciona turma
	public void adiciona_turma(Turma turma) {
		this.getTurmasAtivas().add(turma);
	}
	
	//Verifica se uma prova esta proxima
	private boolean estaProximo(Prova p, int aDia, int aMes){
		if((p.getMes() - aMes)*30 + p.getDia() - aDia < DIAS_PARA_SER_PROXIMO && (p.getMes() - aMes)*30 + p.getDia() - aDia >= 0)
			return true;
		else
			return false;
	}
	
	//Verifica se um Trabalho esta proximo
	private boolean estaProximo(Trabalho t, int aDia, int aMes){
		if((t.getMes() - aMes)*30 + t.getDia() - aDia < DIAS_PARA_SER_PROXIMO && (t.getMes() - aMes)*30 + t.getDia() - aDia >= 0)
			return true;
		else
			return false;
	}
	
	//Lista as provas proximas
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
	
	//Lista os Trabalhos proximos
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
