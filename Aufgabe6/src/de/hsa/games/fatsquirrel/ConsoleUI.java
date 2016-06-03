package de.hsa.games.fatsquirrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;
import de.hsa.games.fatsquirrel.util.ui.consoletest.Command;

public class ConsoleUI implements UI{
	
	CommandScanner commandScanner=new CommandScanner(GameCommandType.values(),new BufferedReader(new InputStreamReader(System.in)));

	@Override
	public void render(BoardView view) {
		// TODO Auto-generated method stub
		String temp="Inhalt in 2D\n";
		for(int y=0;y<view.getSize().getY();y++) {
			for(int x=0;x<view.getSize().getX();x++) {
				if(view.getEntityType(x,y)==null) {
					temp+="..";
				}
				else {
					temp+=view.getEntityType(x,y).getSymbol();
				}
			}
			temp+="\n";
		}
		if(temp=="Inhalt in 2D\n") {
			temp="Keine Elemente enthalten...";
		}
		else {
			System.out.println(temp);
		}
	}

	@Override
	public Command getCommand() {
		// TODO Auto-generated method stub
		
		try {
			return commandScanner.next();
		} catch (ScanException e) {
//			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("FEHLER: falsches Argument");
		}
		return null;
	}

	@Override
	public void message(String msg) {
		// TODO Auto-generated method stub
		
	}

}
