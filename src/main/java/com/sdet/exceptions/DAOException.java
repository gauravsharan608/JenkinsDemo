package com.sdet.exceptions;

@SuppressWarnings("serial")
public class DAOException extends Exception
{
	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
