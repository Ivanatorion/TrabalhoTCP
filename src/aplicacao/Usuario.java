package aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Usuario implements Serializable {
	public static final int DIAS_PARA_SER_PROXIMO = 8;
	private static final long serialVersionUID = 1L;
	private String nome;
	private int numeroCartao;
	private List<Cadeira> cadeiras;
	private List<Turma> turmasAtivas;
	private Historico historico;
	
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
	

	//Adiciona Turma
	public void adiciona_turma(Turma turma) {
		this.getTurmasAtivas().add(turma);
	}
	
	//Verifica se uma avaliacao esta proxima
	private boolean estaProximo(Avaliacao p, int aDia, int aMes){
		if((p.getMes() - aMes)*30 + p.getDia() - aDia < DIAS_PARA_SER_PROXIMO && (p.getMes() - aMes)*30 + p.getDia() - aDia >= 0)
			return true;
		else
			return false;
	}
	
	
	//Lista as avaliacoes proximas
	public List<Avaliacao> getAvaliacoesProximas(){
		Calendar cal = Calendar.getInstance();
		int aDia = cal.get(Calendar.DAY_OF_MONTH);
		int aMes = cal.get(Calendar.MONTH) + 1;
		List<Avaliacao> result = new ArrayList<Avaliacao>();
		
		for(Turma t: turmasAtivas){
			for(Avaliacao a: t.getAvaliacoes()){
				if(estaProximo(a, aDia, aMes))
					result.add(a);
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
