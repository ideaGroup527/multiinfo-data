package org.forecast.parse;

import org.forecast.exception.ObjectToObjectException;

public interface ObjectToObject {
	public Object transform(Object obj) throws ObjectToObjectException;
}
