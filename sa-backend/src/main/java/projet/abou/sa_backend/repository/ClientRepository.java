package projet.abou.sa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.abou.sa_backend.entites.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);

}
