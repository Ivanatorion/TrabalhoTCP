package aplicacao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HistoricoTest {

	Historico hst;
	Cadeira c;
	Cadeira c2;
	Cadeira c3;
	
	@Before
	public void start() {
		hst = new Historico();
		c = new Cadeira("TCP", "INF0000");
		c2 = new Cadeira("Fundamentos de Banco de Dados", "INF0001");
		
		List<Cadeira> prq = new ArrayList<Cadeira>();
		prq.add(c);
	
		c3 = new Cadeira("Org B", "INF0002", prq);
	}
	
	@Test
	public void testNovoHistorico() {
		assertTrue(hst.getCadeirasFinalizadas().isEmpty());
	}
	
	@Test
	public void testNovaCadeira() {
		
		CadeiraFinalizada cf = new CadeiraFinalizada(c, 9.8, 2018, 2);
		
		hst.addCadeiraFinalizada(cf);
		
		assertTrue(!hst.finalizou(c2) && hst.finalizou(c));
	}
	
	@Test
	public void testPreReq() {
		assertTrue(!hst.finalizouPreReq(c3));
		
		CadeiraFinalizada cf = new CadeiraFinalizada(c, 9.8, 2018, 2);
		hst.addCadeiraFinalizada(cf);
		
		assertTrue(hst.finalizouPreReq(c3));
	}

}
