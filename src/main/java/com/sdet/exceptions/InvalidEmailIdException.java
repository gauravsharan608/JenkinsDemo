package com.sdet.exceptions;

@SuppressWarnings("serial")
public class InvalidEmailIdException extends Exception
{  
	public InvalidEmailIdException(String message)
	{  
		super(message);  
	}
	
	public InvalidEmailIdException(String message,Throwable cause)
	{
		super(message, cause);
	}
}
