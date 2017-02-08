package unsw.curation.api.domain.abstraction;

import org.apache.commons.codec.EncoderException;

public interface ITextSoundexSimilarity {

	int SoundexDifference(String word1,String word2) throws EncoderException;
	
}
