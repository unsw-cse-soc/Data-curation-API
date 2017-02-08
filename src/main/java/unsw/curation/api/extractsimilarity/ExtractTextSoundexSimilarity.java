package unsw.curation.api.extractsimilarity;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

import unsw.curation.api.domain.abstraction.ITextSoundexSimilarity;



public class ExtractTextSoundexSimilarity implements ITextSoundexSimilarity {

	@Override
	public int SoundexDifference(String word1, String word2) throws EncoderException {
		Soundex soundee=new Soundex();
		return soundee.difference(word1, word2);
	}

}
