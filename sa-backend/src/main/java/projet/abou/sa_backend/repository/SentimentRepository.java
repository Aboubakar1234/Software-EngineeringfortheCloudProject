package projet.abou.sa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.abou.sa_backend.entites.Sentiment;
import projet.abou.sa_backend.enums.TypeSentiment;

import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment, Integer> {

    List<Sentiment> findByType(TypeSentiment type);

}
