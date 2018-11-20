package aplicacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AvaliacaoTest {

	Turma t;
	Cadeira c;
	Avaliacao p;
	Avaliacao trab;

	@Before
	public void start() {
		c = new Cadeira("TCP", "INF0000");
		t = new Turma(c, 2018, 2, "Segunda e Quarta, 10:30", "Kazuki");
		p = new Avaliacao("P1", 0.35, 22, 9, c);
		trab = new Avaliacao("T1", 0.3, 27, 11, c);
	}
	
	@Test
	public void testNovaTurma() {
		assertTrue(t.getAvaliacoes().isEmpty());
	}
	
	@Test
	public void testProvaETrabalho() {
		t.addAvaliacao(p);
		assertTrue(t.getAvaliacoes().contains(p));
	}
}
