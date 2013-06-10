package uk.co.mccann.socialpeek.exceptions;

/**
 * SocialPeekException
 * thrown by interfaces.
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 * @see Exception
 */

public class SocialPeekException extends Exception {

	private static final long serialVersionUID = 1L;

	public SocialPeekException() {
		super();
	}

	public SocialPeekException(String string) {
		super(string);
	}

}
