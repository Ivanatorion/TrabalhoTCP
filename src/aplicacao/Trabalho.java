package aplicacao;

import java.util.Calendar;

public class Trabalho {
	private Calendar data;
	private double peso;
	private double nota;
	
	Trabalho(int dia, int mes){
		data = Calendar.getInstance();
		
		data.set(Calendar.MONTH, mes);
		data.set(Calendar.DAY_OF_MONTH, dia);
	}
}
