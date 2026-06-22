package com.ecommerce.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class RemoveSessionc implements RemoveSession {

	@Override
	public void removesession() {
		
   HttpServletRequest req=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		// TODO Auto-generated method stub
	HttpSession session=req.getSession();
	session.removeAttribute("succMsg");
	session.removeAttribute("errorMsg");

	}

}
