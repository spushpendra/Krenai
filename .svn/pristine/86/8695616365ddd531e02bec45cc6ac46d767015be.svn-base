package com.bugfree.common;

import javax.servlet.ServletContext;

public class RawImageData {

	public int toSaveId;
	public String imageNamePrefix;
	public String extendImageFolderName;
	public String rootPath;
	public String toShowImagePath;
	public String findPathInContext;
	public ServletContext servletContext;
	
	/*
	 * toSaveId-- is what you want to append with image name
	 * imageNamePrefix-- is what you want to prepend with toSaveId
	 * extendImageFolderName-- is the name of folder you want to create after the
	 * path find from web.xml file
	 * findPathInContext-- is the web.xml entry for the root path where you want to create folder 
	 * for your image.
	 * toShowPathContext-- is the web.xml entry for the same image to view that
	 * means the path with context name and this path is returned through the method
	 * in order to save it in database. 
	 * servlerContext-- get from HttpServletRequest object of every request.
	 * */
	public RawImageData(int toSaveId, String imageNamePrefix,
			String extendImageFolderName,  String findPathInContext,String toShowPathContext, ServletContext servletContext) {
		String rootPath;
		String uploadProductFolderPath=(String)servletContext.getInitParameter(findPathInContext);
		if(extendImageFolderName!=null)
		rootPath=servletContext.getRealPath(uploadProductFolderPath);
		else
			rootPath=servletContext.getRealPath(uploadProductFolderPath);
		this.toSaveId = toSaveId;
		this.imageNamePrefix = imageNamePrefix;
		this.extendImageFolderName = extendImageFolderName;
		this.rootPath = rootPath;
		this.toShowImagePath = (String)servletContext.getInitParameter(toShowPathContext);
		this.findPathInContext = findPathInContext;
		this.servletContext = servletContext;
	}
	
	
	
	
	
	
	
	
}
