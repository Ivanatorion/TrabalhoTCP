package aplicacao;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Historico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public boolean finalizou(Cadeira c) {
		for(CadeiraFinalizada cf : this.getCadeirasFinalizadas()) {
			if(cf.getCadeira().getCodigo().equals(c.getCodigo()))
				return true;
		}
		return false;
	}
	
	public boolean finalizouPreReq(Cadeira c) {
		boolean b;
		for(Cadeira cr: c.getPreRequisitos()) {
			b = false;
			for(CadeiraFinalizada cf: this.getCadeirasFinalizadas()) {
				if(cf.getCadeira().getCodigo().equals(cr.getCodigo()))
					b = true;
			}
			if(!b)
				return false;
		}
		return true;
	}
}
