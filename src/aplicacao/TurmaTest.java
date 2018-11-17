package aplicacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TurmaTest {

	Turma t;
	Cadeira c;
	Prova p;
	Trabalho trab;

	@Before
	public void start() {
		c = new Cadeira("TCP", "INF0000");
		t = new Turma(c);
		p = new Prova("P1", 0.35, 22, 9, c);
		trab = new Trabalho("T1", 0.3, 27, 11, c);
	}
	
	@Test
	public void testNovaTurma() {
		assertTrue(t.getProvas().isEmpty());
		assertTrue(t.getTrabalhos().isEmpty());
	}
	
	@Test
	public void testProvaETrabalho() {
		t.addProva(p);
		t.addTrabalho(trab);
		assertTrue(t.getTrabalhos().contains(trab) && t.getProvas().contains(p));
	}
}
