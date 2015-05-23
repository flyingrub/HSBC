package banque;

import banque.compte.Compte;
import banque.compte.CompteCourrant;
import banque.compte.remunere.CompteEpargne;

/**
 * Created by Ronan Timinello.
 */
public class Banque {

    /**
     * Permet de rendre le projet executable
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue dans notre Banque!\n");

        AttacheClient attacheClient1 = new AttacheClient();
        AttacheClient attacheClient2 = new AttacheClient();

        Client C = new Client("C", attacheClient1);
        Client client2 = new Client("Jean", attacheClient2);

        Compte compteEpargne = new CompteEpargne("CompteEpargne", C, 0, 700);
        Compte compteCourrant = new CompteCourrant("CompteCourrant", C);

        System.out.println("Voici nos attache clientele:\n"+ attacheClient1 + "\n" + attacheClient2 + "\n" );
        System.out.println("Voici nos Clients:\n"+ C + "\n" + client2 + "\n" );
        System.out.println("Voici nos Comptes:\n"+ compteCourrant + "\n" + compteEpargne + "\n" );

        try {
            Operation operation1 = new Operation(Nature.CREDIT, compteCourrant, C, 2000);
            Operation operation2 = new Operation(Nature.CREDIT, compteEpargne, C, 33);
            Operation operation3 = new Operation(Nature.DEBIT, compteCourrant, C, 500);
            System.out.println("Voici nos Operations:\n"+ operation1 + "\n" + operation2 + "\n" + operation3 + "\n");
            Operation operation4 = new Operation(Nature.DEBIT, compteCourrant, C, 2500);
        } catch (Operation.OperationBancaireException e) {

        }
        System.out.println("\nVoici les operations de Compte Courrant:\n"+ compteCourrant.getOperations() );

    }
}
