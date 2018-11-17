package aplicacao;

import java.io.Serializable;

public class CadeiraFinalizada implements Serializable{
	private Cadeira cadeira;
	private double notaFinal;
	private int ano;
	private int semestre;
	
	CadeiraFinalizada(Cadeira c, double nota, int an, int sem){
		this.cadeira = c;
		
		if(nota > -0.001 && nota < 10.001) this.notaFinal = nota;
		else this.notaFinal = 0.0;
		
		if(an > 0) this.ano = an;
		else this.ano = 0;
		
		if(sem > 0 && sem < 3) this.semestre = sem;
		else this.semestre = 1;
	}

	public Cadeira getCadeira() {
		return cadeira;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double nota) {
		if(nota > -0.001 && nota < 10.001) this.notaFinal = nota;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int an) {
		if(ano > 0) this.ano = an;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int sem) {
		if(semestre > 0 && semestre < 3) this.semestre = sem;
	}
	
	
}
