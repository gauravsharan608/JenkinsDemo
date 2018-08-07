package com.sdet.exceptions;

@SuppressWarnings("serial")
public class UtilException extends Exception
{
	public UtilException(String message,Throwable cause)
	{
		super(message, cause);
	}
}
