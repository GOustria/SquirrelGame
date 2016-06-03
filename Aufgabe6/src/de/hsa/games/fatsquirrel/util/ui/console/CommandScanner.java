package de.hsa.games.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;
import de.hsa.games.fatsquirrel.util.ui.consoletest.Command;

public class CommandScanner {
	
	private CommandTypeInfo[] commandTypeInfos;
	private BufferedReader inputReader;
	private PrintStream outputStream;
	
	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
		this.commandTypeInfos = commandTypes;
		this.inputReader = inputReader;
	}
	
	public boolean validateParams(Class<?>[] paramTypes, Object[] params, String[] tokens) {
		for(int i=0;i<paramTypes.length;i++) {
			if(paramTypes[i].equals(int.class)) {
//				Versuch als int zu casten
				params[i]=Integer.parseInt(tokens[i+1]);
			}
			else if(paramTypes[i].equals(float.class)) {
//				Versuch als float zu casten
				params[i]=Float.parseFloat(tokens[i+1]);
			}
			else if(paramTypes[i].equals(String.class)) {
//				Versuch als String zu casten
				params[i]=tokens[i+1];
			}
			else {
//				wenn keins der Versuche klappt wird Kommando nicht angenommen
				return false;
			}
		}
		return true;
	}
	
	public Command next() throws ScanException, IOException {
		
		String input=null;
		
//		Eingabe einlesen, führende und abschliessende Leerzeichen entfernen
		System.out.print("Kommando eingeben: ");
		
		input=(inputReader.readLine()).trim();
		
//		Zeile nach Leerzeichen splitten
		String[] tokens = input.split(" ");
		
//		Laufe commandTypeInfos durch und liefere zum Kommandonamen tokens[0] neues Command mit Kommandonamen und Parameterliste
		for(CommandTypeInfo i : commandTypeInfos) {
			if(i.getName().equals(tokens[0])) {
//				Kommando gefunden, Parameter prüfen
				if(i.getParamTypes().length==(tokens.length-1)) {
//					wenn Anzahl der Argumente gleich
					Object[] params=new Object[i.getParamTypes().length];
					if(validateParams(i.getParamTypes(),params,tokens)) {
						return new Command(i,params);
					}
				}
			}
		}
		
		throw new ScanException("FEHLER: Kommando existiert nicht");
	}

}
