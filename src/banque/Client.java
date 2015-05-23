package banque;

import banque.AttacheClient;
import banque.compte.Compte;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ronan Timinello.
 */
public class Client {
    private int numeroId;
    private String nom;
    private ArrayList<Compte> comptes = new ArrayList<>();
    private AttacheClient attacheClient;
    private static int currentNumeroId;

    public Client(String nom, AttacheClient attacheClient) {
        this.numeroId = ++currentNumeroId;
        this.nom = nom;
        this.attacheClient = attacheClient;
        attacheClient.ajouterClient(this);
    }

    public void ajouterCompte(Compte... comptes){
        this.comptes.addAll(Arrays.asList(comptes));
    }

    public void supprimerCompte(int numeroId, String libelle){
        for(Compte compte: comptes){
            if (compte.getNumeroId() == numeroId && compte.getLibelle().equals(libelle)){
                this.comptes.remove(compte);
                return;
            }
        }
    }

    public int getNumeroId() {
        return numeroId;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroId=" + numeroId +
                ", nom='" + nom + '\'' +
                ", comptes=" + comptes +
                ", attacheClient=" + attacheClient +
                '}';
    }
}
