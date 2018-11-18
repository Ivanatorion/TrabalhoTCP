package aplicacao;

import java.io.Serializable;

public class Trabalho implements Serializable{
	private static final long serialVersionUID = 1L;
	String nome;
	private int dia;
	private int mes;
	private double peso;
	private double nota;
	private Cadeira cadeira;
	
	Trabalho(String n, double p, int dia, int mes, Cadeira c){
		this.dia = dia;
		this.mes = mes;
		this.cadeira = c;
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
	
	public Cadeira getCadeira(){
		return cadeira;
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
