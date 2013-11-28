package cz.pfreiberg.knparser.parser;

import java.util.Scanner;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;


public class ParserParcely {
	
	public static Parcely parse(String actualLine, Scanner scanner)
	{
		Parcely parcela = new Parcely();
		System.out.println(actualLine);
		
		while(scanner.hasNext())
		{
			System.out.println(scanner.next());
		}
		
		return parcela;
	}

}
