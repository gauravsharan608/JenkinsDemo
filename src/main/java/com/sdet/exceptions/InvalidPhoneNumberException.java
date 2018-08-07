package com.sdet.exceptions;

@SuppressWarnings("serial")
public class InvalidPhoneNumberException extends Exception
{  
	public InvalidPhoneNumberException(String message)
	{  
		super(message);  
	}
	
	public InvalidPhoneNumberException(String message,Throwable cause)
	{
		super(message,cause);
	}
}