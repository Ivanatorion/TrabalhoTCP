package aplicacao;

import java.util.Calendar;

public class Prova {
	String nome;
	private int dia;
	private int mes;
	private double peso;
	private double nota;
	
	Prova(String n, double p, int dia, int mes){
		this.dia = dia;
		this.mes = mes;
		
		this.peso = p;
		this.nome = n;
	}

	public String getNome() {
		return nome;
	}

	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}

	public double getPeso() {
		return peso;
	}

	public double getNota() {
		return nota;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	
	
}
