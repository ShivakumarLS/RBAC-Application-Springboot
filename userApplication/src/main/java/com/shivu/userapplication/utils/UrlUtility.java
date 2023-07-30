package com.shivu.userapplication.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtility {

	public static String getSiteURL(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
}
