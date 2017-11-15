package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.Database;

import java.util.Scanner;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
	    String input;
	    Database db = Database.getInstance();
	    db.parseDb("src/main/resources/rules.db");
		System.out.println("Ingrese la query que desea buscar");
        System.out.println("Ingrese exit para finalizar el programa");

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while(input.compareTo("exit") != 0){
            if(db.checkQuery(input))
                System.out.println("La query existe en la base de datos");
            else
                System.out.println("La query no existe en la base de datos");
            input = sc.nextLine();
        }
    }
}
