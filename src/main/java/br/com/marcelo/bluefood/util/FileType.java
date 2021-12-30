package br.com.marcelo.bluefood.util;

public enum FileType {
	
	PNG("image/png","png"),
	JPG("image/jpeg","jpg");
	
	String mimeType;
	String extension;
	
	FileType(String mimeType, String extension){
		
		this.mimeType = mimeType;
		this.extension = extension;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	
	public boolean sameOf(String mineType) {
		return this.mimeType.equals(mineType);		
	}
	
	public static FileType of(String mineType) {
		for (FileType fileType: values()) {
			if(fileType.sameOf(mineType)) {
				return fileType;
			}
		}
		
		return null;
		
	}
	
	

}
