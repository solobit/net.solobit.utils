package winterwell.jtwitter;



/**
 * A runtime exception for when Twitter requests don't work. All
 * {@link Twitter} methods can throw this.
 * <p>
 * I believe unchecked exceptions are preferable to checked ones,
 * because they avoid the problems caused by swallowing exceptions.
 * But if you don't like runtime exceptions, just edit this class.
 *
 * @author Daniel Winterstein
 */
public class TwitterException extends RuntimeException {

	/**
	 * Wrap an exception as a TwitterException.
	 */
	TwitterException(Exception e) {
		super(e);
	}

	/**
	 * @param string
	 */
	public TwitterException(String string) {
		super(string);
	}

}
