package projet.abou.sa_backend.service;
/*
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalyzer {

    private final StanfordCoreNLP stanfordCoreNLP;

    // Grâce à l'injection de dépendances (par constructeur ici),
    // Spring va nous fournir le bean StanfordCoreNLP défini dans StanfordNlpConfig.
    public SentimentAnalyzer(StanfordCoreNLP stanfordCoreNLP) {
        this.stanfordCoreNLP = stanfordCoreNLP;
    }

    // Méthode utilitaire pour analyser le sentiment d'un texte
    public int analyzeSentiment(String text) {
        CoreDocument doc = new CoreDocument(text);
        stanfordCoreNLP.annotate(doc);

        int totalScore = 0;
        int sentenceCount = doc.sentences().size();

        for (var sentence : doc.sentences()) {
            String sentimentLabel = sentence.sentiment();
            int score = convertLabelToScore(sentimentLabel);
            totalScore += score;
        }

        return (sentenceCount == 0) ? 2 : totalScore / sentenceCount; // 2 = neutre si 0 phrases
    }

    // Méthode pour convertir un label textuel ("Very Negative", etc.) en score numérique
    private int convertLabelToScore(String label) {
        switch (label) {
            case "Very Negative": return 0;
            case "Negative":      return 1;
            case "Neutral":       return 2;
            case "Positive":      return 3;
            case "Very Positive": return 4;
            default:              return 2; // neutre par défaut
        }
    }
}*/
