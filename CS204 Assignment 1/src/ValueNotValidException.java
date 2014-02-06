public class ValueNotValidException extends RuntimeException
{
	public ValueNotValidException() {}
	
	public ValueNotValidException(String message)
	{
		super(message);
	}
}