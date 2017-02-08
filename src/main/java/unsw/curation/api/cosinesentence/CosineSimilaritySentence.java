package unsw.curation.api.cosinesentence;


public class CosineSimilaritySentence {    
    public static double CosineSimilarity(DocVectorSentence d1,DocVectorSentence d2) {
        double cosinesimilarity;
        try {
            cosinesimilarity = (d1.vector.dotProduct(d2.vector))
                    / (d1.vector.getNorm() * d2.vector.getNorm());
        } catch (Exception e) {
            return 0.0;
        }
        return cosinesimilarity;
    }
}