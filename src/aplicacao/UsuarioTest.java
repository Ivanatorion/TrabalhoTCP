package aplicacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Usuario usr;
	
	@Before
	public void setUp(){
		usr = new Usuario("Harry Potter", 123456);
	}
	
	@Test
	public void testNovoUsuario() {
		assertTrue(usr.getCadeiras().isEmpty() && usr.getTurmasAtivas().isEmpty() && usr.getHistorico().getCadeirasFinalizadas().isEmpty());
	}

	
	
}
