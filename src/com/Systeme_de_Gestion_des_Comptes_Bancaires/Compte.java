package com.Systeme_de_Gestion_des_Comptes_Bancaires;
import java.util.Scanner;

public class Compte {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	      System.out.println(menu());
	      System.out.println("choisi votre choix par les nombres:");
	      if(scanner.hasNextInt()){
	    	  int VotreChoix = scanner.nextInt();
	      }else{
	    	  System.out.println("Erreur : vous devez entrer un nombre entier !");
	          scanner.close();
	      }
	}
	public static String menu(){
	    return "voila les action possible :\n"
	        + "1-Créer un compte\n"
	        + "2-Effectuer un versement dans un compte\n"
	        + "3-Effectuer un retrait d'un compte\n"
	        + "4-Effectuer un virement entre comptes\n"
	        + "5-Consulter le solde du compte\n"
	        + "6-Consulter la liste des opérations effectuées sur un compte\n";
	}


}
