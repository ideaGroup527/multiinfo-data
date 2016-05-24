package com.kxm.parse;

import com.kxm.exception.ObjectToObjectException;

public interface ObjectToObject {
	public Object transform(Object obj) throws ObjectToObjectException;
}
