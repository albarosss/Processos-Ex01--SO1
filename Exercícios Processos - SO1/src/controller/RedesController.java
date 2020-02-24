package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {
	
	
	public void ip(String SO) {
		String command;
		String Adap;
		String Linha = " ";
		String ip;
		if(SO.contains("Windows")) {
			command="ipconfig";
			Adap = "Adaptador";
			ip = "IPv4";
		}else {
			command="ifconfig";
			Adap="flags";
			ip= "inet";
		}
		try {
			Process p = Runtime.getRuntime().exec(command);
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			while (line != null) {
				if(line.contains(Adap)) {
					Linha = line;
				}
				if(line.contains(ip)) {
					System.out.println(Linha + ": \n" +  line);
				}
				line = buffer.readLine();
			}
			buffer.close();
			reader.close();
			stream.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void ping(String SO) {
		String dominio = JOptionPane.showInputDialog("Digite o dominio a qual deseja verificar o ping");
		String command;
		if(SO.contains("Windows")) {
			command="ping -n 10 ";
		}else {
			command="ping -c 10 ";
		}
		try {
			
			Process p = Runtime.getRuntime().exec(command + dominio);
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			while (line != null) {
				if(line.contains("time") || line.contains("TLL")) {
					if(SO.contains("Windows")) {
						line=line.substring(line.indexOf(" t"), line.lastIndexOf(" "));
					}else {
						line=line.substring(line.indexOf("tim"), line.length());
					}
				}
				System.out.println(line);
				line = buffer.readLine();
			}
			buffer.close();
			reader.close();
			stream.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
