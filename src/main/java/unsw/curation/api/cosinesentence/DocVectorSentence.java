package unsw.curation.api.cosinesentence;

import java.util.Map;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.RealVectorFormat;

public class DocVectorSentence 
{

    public Map<String, Integer> terms;
    public RealVector vector;
    public DocVectorSentence(Map<String, Integer> terms) {
        this.terms = terms;
        this.vector = new ArrayRealVector(terms.size());
    }

    public void setEntry(String term, int freq) {
        if (terms.containsKey(term)) {
            int pos = terms.get(term);
            vector.setEntry(pos, (double) freq);
        }
    }

    public void normalize() {
        double sum = vector.getL1Norm();
        vector = (RealVector) vector.mapDivide(sum);
    }

    @Override
    public String toString() {
        RealVectorFormat formatter = new RealVectorFormat();
        return formatter.format(vector);
    }
}
