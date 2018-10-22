package aplicacao;

import java.util.Calendar;

public class Prova {
	private Calendar data;
	private double peso;
	private double nota;
	
	Prova(int dia, int mes){
		data = Calendar.getInstance();
		
		data.set(Calendar.MONTH, mes);
		data.set(Calendar.DAY_OF_MONTH, dia);
	}
	
}
