package eu.sffi.webandpaper.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CharacterServiceResult implements IsSerializable {

	public static final byte OK = 0;
	
	public static final byte GENERIC_ERROR = 1;
	
	private byte returnCode;
	private String message;
	
	public CharacterServiceResult(){
		this.returnCode = OK;
		this.message = "";
	}

	public byte getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(byte returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
