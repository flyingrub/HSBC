package banque;

import banque.compte.Compte;
import banque.compte.CompteCourrant;
import banque.compte.remunere.CompteEpargne;
import banque.enumP.Nature;
import banque.enumP.Status;
import banque.exception.OperationBancaireException;
import banque.exception.OrdreVirementException;

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
            compteCourrant.crediter(2000);
            compteEpargne.crediter(30);
            compteCourrant.debiter(500);
            compteCourrant.debiter(2500);
        } catch (OperationBancaireException e) {
            System.out.println(e);
        }
        System.out.println("\nVoici les operations de Compte Courrant:\n"+ compteCourrant.getOperations());
        System.out.println("\nVoici les operations de Compte Epargne:\n"+ compteEpargne.getOperations());
        try {
            compteCourrant.debiter(new Operation(Nature.DEBIT, Status.ATTENTE, compteCourrant, compteCourrant.getTitulaire(), 123));
            compteCourrant.crediter(new Operation(Nature.CREDIT, Status.ATTENTE, compteCourrant, compteCourrant.getTitulaire(), 1.55f));
            C.ordreVirement(compteCourrant, compteEpargne, 666);
            C.ordreVirement(compteCourrant, compteEpargne, 10000);
        } catch (OrdreVirementException e) {
            System.out.println(e);
        }
        try {
            C.ordreVirement(compteCourrant, compteEpargne, 10);
        } catch (OrdreVirementException e) {
            e.printStackTrace();
        }
        System.out.println("\nVoici les operations de Compte Courrant:\n"+ compteCourrant.getOperations());
        System.out.println("\nVoici les operations de Compte Epargne:\n"+ compteEpargne.getOperations());
        System.out.println("\nVoici les operations en attente de l'attaché:\n"+ attacheClient1.getOperationsEnAttente());
    }
}
