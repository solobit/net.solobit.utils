package uk.co.mccann.socialpeek.interfaces;

import java.util.List;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;


/**
 * <b>Generator</b><br/>
 * Data generator interface
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 */
public interface Generator {
	
	/**
     *  Generate a String from the PeekData object 
     *  @param dataIn the PeekData object containing all your wonderful information
     *  @return XML, RSS or JSON data depending on the generator
     *  @throws SocialPeekException if generation fails.
     *  @see Data
     */
	public String generate(Data dataIn) throws SocialPeekException;
	
	/**
     *  Generate a String from the list of PeekData Objects 
     *  @param dataIn the PeekData object containing all your wonderful information
     *  @return XML, RSS or JSON data depending on the generator
     *  @throws SocialPeekException is generation fails
     *  @see Data
     */
	public String generate(List<Data> dataIn) throws SocialPeekException;
	
	
}

