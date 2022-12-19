package org.la.sse.tile.futur;

public class FutureObject {

	private boolean notEmpty;
	private String[] array;
	
	public boolean getNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(boolean empty) {
		this.notEmpty = empty;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}
	
	public void clear() {
		array = null;
		notEmpty = false;
	}
}
