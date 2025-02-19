package projet.abou.sa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.abou.sa_backend.entites.Client;
import projet.abou.sa_backend.repository.ClientRepository;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {


    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void creer(Client client){
        Client clientDansLaBDD = this.clientRepository.findByEmail(client.getEmail());
        if(clientDansLaBDD == null){
            this.clientRepository.save(client);
        }
    }

    public List<Client> rechercher(){
        return this.clientRepository.findAll();
    }


    public Client lire(int id ) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElse(null);//ca renvoie  optionalclient si non null
        /*
        if(optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;*/
    }

    public Client LireOuCreer(Client clientAcreer) {
        Client clientDansLaBDD = this.clientRepository.findByEmail(clientAcreer.getEmail());
        if(clientDansLaBDD == null){
            clientDansLaBDD = this.clientRepository.save(clientAcreer);
        }
        return clientDansLaBDD;
    }

    public void modifier(int id, Client client) {

        Client clientDansLaBdd = this.lire(id);
        if(clientDansLaBdd.getId() == client.getId()){
            clientDansLaBdd.setEmail(client.getEmail());
            clientDansLaBdd.setTelephone(client.getTelephone());
            this.clientRepository.save(clientDansLaBdd);
        }



    }
}
