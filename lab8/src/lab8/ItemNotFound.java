package lab8;

public class ItemNotFound extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFound(String msg) {
        super(msg);
    }

    public ItemNotFound() {
        super();
    }
}
