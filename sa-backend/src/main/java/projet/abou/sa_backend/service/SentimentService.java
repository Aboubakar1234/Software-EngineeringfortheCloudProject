package projet.abou.sa_backend.service;

import org.springframework.stereotype.Service;
import projet.abou.sa_backend.entites.Client;
import projet.abou.sa_backend.entites.Sentiment;
import projet.abou.sa_backend.enums.TypeSentiment;
import projet.abou.sa_backend.repository.SentimentRepository;
import java.util.List;

@Service
public class SentimentService {

    private ClientService clientService;
    private SentimentRepository sentimentRepository;
    //private SentimentAnalyzer sentimentAnalyzer;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void creer(Sentiment sentiment) {
        Client client = this.clientService.LireOuCreer(sentiment.getClient());
        sentiment.setClient(client);

        //int score = sentimentAnalyzer.analyzeSentiment(sentiment.getTexte());

        //Analyse 2
        /*
        if (score <= 1) {
            sentiment.setType(TypeSentiment.NEGATIF);
        } else {
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);
         */
        //Analyse1
        if(sentiment.getTexte().contains("pas")){
            sentiment.setType(TypeSentiment.NEGATIF);
        }else{
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> rechercher(TypeSentiment type) {
        if(type == null){
            return this.sentimentRepository.findAll();
        }else{
            return this.sentimentRepository.findByType(type);
        }

    }


    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }
}
