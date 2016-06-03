package de.hsa.games.fatsquirrel.util.ui.consoletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;

public class MyFavoriteCommandsProcessor {

//	Input: Liste der erlaubten Kommandos von Typ CommandTypeInfo
	CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(),new BufferedReader(new InputStreamReader(System.in)));
	
	public void process() {
//		IOException, ScanException, NumberFormatException
		
		Command command=null;
		
		while(true) {
			
			try {
				command=commandScanner.next();
				
				MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
				
				Object[] params=command.getParams();
				
				switch(commandType) {
				case EXIT: {
					System.out.println("exit...");
					System.exit(0);
				}
					break;
				case HELP: {
					for(CommandTypeInfo cti: MyFavoriteCommandType.values()) {
						System.out.println(">> "+cti.getName()+cti.getHelpText());
					}
				}
					break;
				case ADDI: {
					int summe = 0;
					for(int i=0;i<params.length;i++) {
						summe+=(int)params[i];
						
					}
					System.out.println("Summe: "+summe);
				}
					break;
				case ADDF: {
					float summe=0;
					for(int i=0;i<params.length;i++) {
						summe+=(float)params[i];
					}
					System.out.println("Summe: "+summe);
				}
					break;
				case ECHO: {
					for(int i=0; i<(int)params[1];i++) {
						System.out.println((String)params[0]);
					}
				}
					break;
				}
			} catch (ScanException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.err.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.err.println("FEHLER: falsches Argument");
			}
		}
	}
	
	public static void main(String[] args) {
		
		MyFavoriteCommandsProcessor mfcp=new MyFavoriteCommandsProcessor();
		
		mfcp.process();
	}

}
