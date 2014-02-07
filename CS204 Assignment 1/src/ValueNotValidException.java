public class ValueNotValidException extends RuntimeException
{
		private static final long serialVersionUID = 1L;

	public ValueNotValidException()
	{}
	
	public ValueNotValidException(String message)
	{
		super(message);
	}
}