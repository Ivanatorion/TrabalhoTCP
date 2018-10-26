package aplicacao;

import java.util.List;
import java.util.ArrayList;

public class Historico {

	private List<CadeiraFinalizada> cadeirasFinalizadas;
	
	public Historico() {
		cadeirasFinalizadas = new ArrayList<CadeiraFinalizada>();
	}

	public void addCadeiraFinalizada(CadeiraFinalizada cf){
		cadeirasFinalizadas.add(cf);
	}
	
	public List<CadeiraFinalizada> getCadeirasFinalizadas(){
		return cadeirasFinalizadas;
	}
}
