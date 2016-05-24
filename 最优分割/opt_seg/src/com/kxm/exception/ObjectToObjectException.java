package com.kxm.exception;

public class ObjectToObjectException extends Exception{
	public ObjectToObjectException(){
	}
	
	public ObjectToObjectException(String message){
		super(message) ;
	}
	
	public ObjectToObjectException(String message,Exception e){
		super(message,e) ;
	}
}

