package banque.attacheclientele;

import banque.client.Client;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ronan Timinello.
 */
public class AttacheClient {
    private ArrayList<Client> clients;

    public AttacheClient(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void ajouterClient(Client... clients){
        this.clients.addAll(Arrays.asList(clients));
    }

    public void supprimerClient(int numeroId, String nom){
        for (Client client: clients){
            if (client.getNumeroId() == numeroId && client.getNom().equals(nom)){
                this.clients.remove(client);
                return;
            }
        }
    }

    public String allClientName(){
        String allClientName = "";
        for (Client c: clients){
            allClientName += c.getNom() + " ";
        }
        return allClientName;
    }

    @Override
    public String toString() {
        return "AttacheClient{" +
                "clients=" + allClientName() +
                '}';
    }
}
