package banque;

import banque.attacheclientele.AttacheClient;
import banque.client.Client;
import banque.compte.Compte;
import banque.compte.CompteCourrant;
import banque.compte.remunere.CompteEpargne;

import java.util.ArrayList;

/**
 * Created by Ronan Timinello.
 */
public class Banque {

    /**
     * Permet de rendre le projet executable
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue dans notre Banque!\n");
        AttacheClient attacheClient1 = new AttacheClient(new ArrayList<>());
        AttacheClient attacheClient2 = new AttacheClient(new ArrayList<>());
        Client C = new Client("C", new ArrayList<>(), attacheClient1);
        Client client2 = new Client("Jean", new ArrayList<>(), attacheClient2);
        Compte compteEpargne = new CompteEpargne("CompteEpargne", C, 0, 700);
        Compte compteCourrant = new CompteCourrant("CompteCourrant", C);
        System.out.println("Voici nos attache clientele:\n"+ attacheClient1 + "\n" + attacheClient2 + "\n" );
        System.out.println("Voici nos Clients:\n"+ C + "\n" + client2 + "\n" );
        System.out.println("Voici nos Comptes:\n"+ compteCourrant + "\n" + compteEpargne + "\n" );
    }
}
