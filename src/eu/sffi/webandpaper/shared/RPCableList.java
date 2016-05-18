package eu.sffi.webandpaper.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class RPCableList<E> extends ArrayList<E> implements Serializable{

	public RPCableList(){};
	
	public RPCableList(Collection<E> values){
		this.addAll(values);
	}
}
