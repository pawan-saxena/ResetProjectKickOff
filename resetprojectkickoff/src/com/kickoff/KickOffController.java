package com.kickoff;


import java.io.IOException;

import mypack.Project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.database.DatabaseManagement;

/**
 * 
 * Controller Class
 * 
 * **/
@Controller
public class KickOffController {

	@Resource
	@Qualifier("database")
	DatabaseManagement databaseManagement; /* creating database management object to use method */
	
	/**
	 * Method to show all the user available
	 */
	@RequestMapping(value = {"/","/showall"}, method = RequestMethod.GET)
	public ModelAndView showall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/* getting all users form the DatabaseManagemet */
		List<Project> Projectlist = databaseManagement.getRecordAll();
		ModelAndView modelAndView = new ModelAndView("showall");
		modelAndView.addObject("project", Projectlist); /* Adding object to the view showall.jsp */
		return modelAndView;
	}

	/**
	 * Method to show the user with id available
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/showid", method = RequestMethod.GET)
	public ModelAndView showid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("show");
		request.setAttribute("verified", "Show");
		Project  project=null;
		int id;
		try
		{
		 id = Integer.parseInt(request.getParameter("id"));/* getting id for which we have to get user */
		 project = databaseManagement.getRecordByid(id); /* getting projects  form the  DatabaseManagemet */
		}
		catch(Exception exception)
		{
			modelAndView.addObject("project", project);/* adding object to the view show.jsp*/
			return modelAndView;
		}
		
		
     	
		//List<Project> list = new ArrayList();	
		//list.add(project);
		
		modelAndView.addObject("project", project);/* adding object to the view show.jsp*/
		return modelAndView;
	}
	
	
	/**
	 * Controller to run after clicking the reset button on showall.jsp
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetVerification", method = RequestMethod.GET)
	public String enableVerification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("showall"); 
		int id;
		System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
		Project  project=null;
		try
		{
	
		 id = Integer.parseInt(request.getParameter("primaryId")); /* getting hidden field data */
		/* rendering view */
		 project = databaseManagement.getRecordByid(id);
		 project.setApprove(false);
			project.setVerifiedBy("");
			databaseManagement.updateRecord(project);
			List<Project> Projectlist = databaseManagement.getRecordAll();
			modelAndView.addObject("project", Projectlist); /* Adding object to the view showall.jsp */
		}
		catch(Exception exception)
		{
			List<Project> Projectlist = databaseManagement.getRecordAll();
			modelAndView.addObject("project", Projectlist); /* Adding object to the view showall.jsp */
			modelAndView.addObject("Reset", "no");
		}
		return "redirect:/showall";
	}
	
	/**
	 * Method to show the project available in the database for full text search
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */	 
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String search = request.getParameter("name");
		List<Project> Projectlist = databaseManagement.getRecord(search); /* getting  the list  of  available  user from DatabaseManagement */
		ModelAndView modelAndView = new ModelAndView("showall");/* setting show */ 
	
		request.setAttribute("showallprojects", "yes");
		modelAndView.addObject("project", Projectlist);/* adding object to the view show.jsp */
		return modelAndView; /* returning model and view */

	}

	
}
