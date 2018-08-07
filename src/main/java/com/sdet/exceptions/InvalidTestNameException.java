package com.sdet.exceptions;

@SuppressWarnings("serial")
public class InvalidTestNameException extends Exception
{  
	public InvalidTestNameException(String message)
	{  
		super(message);  
	}
	
	public InvalidTestNameException(String message,Throwable cause)
	{
		super(message,cause);  
	}
}