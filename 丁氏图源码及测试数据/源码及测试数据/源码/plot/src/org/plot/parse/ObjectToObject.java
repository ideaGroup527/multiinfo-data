package org.plot.parse;

import org.plot.exception.ObjectToObjectException;

public interface ObjectToObject {
	public Object transform(Object obj) throws ObjectToObjectException;
}
