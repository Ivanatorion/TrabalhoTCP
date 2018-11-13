package aplicacao;

import java.util.Calendar;

public class Trabalho {
	String nome;
	private int dia;
	private int mes;
	private double peso;
	private double nota;
	
	Trabalho(String n, double p, int dia, int mes){
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
}
