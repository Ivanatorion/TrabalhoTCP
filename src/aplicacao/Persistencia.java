package aplicacao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Persistencia {
	
	private static final String NOMEARQUIVODADOS = "dados.bin";
	
	private static Scanner keyboard = principal.keyboard;
	
	private static Usuario novo_usuario() {
		Usuario usuario;
		String tempNome;
		int tempCartao = 0;
		boolean ok = false;
		
		System.out.println("Usuario nao declarado. Por favor registre um novo");
		System.out.print("Digite seu nome: ");
		tempNome = keyboard.nextLine();
		
		while(!ok){
			try{
				ok = true;
				System.out.print("Digite seu cartao: ");
				tempCartao = Integer.parseInt(keyboard.nextLine());
			}
			catch(NumberFormatException e){
				System.out.println("Erro: Cartao invalido!");
				ok = false;
			}
		}
		
		usuario = new Usuario(tempNome, tempCartao);
		
		return usuario;
	}
	
	public static Usuario carrega_usuario() {
		Usuario usuario;
		
		try {
			FileInputStream fis = new FileInputStream(NOMEARQUIVODADOS);
			ObjectInputStream ois = new ObjectInputStream(fis);
			usuario = (Usuario) ois.readObject();
			ois.close();
		} catch (Exception e) {
			usuario = novo_usuario();
		}
		
		return usuario;
	}
	
	public static void salva_usuario(Usuario usuario) {
		try {
			FileOutputStream fos = new FileOutputStream(NOMEARQUIVODADOS);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(usuario);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
