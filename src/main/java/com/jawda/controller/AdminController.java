package com.jawda.controller;

import org.thymeleaf.context.WebContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import com.huongdanjava.jakartaee.servlet.ThymeleafConfig;
import com.jawda.model.Admin;
import com.jawda.model.AdminType;
import com.jawda.service.UserService;


/**
 * Servlet implementation class UserController
 */
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private ITemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
    	userService = new UserService();
        ServletContext servletContext = getServletContext();
        this.templateEngine = (ITemplateEngine) servletContext.getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);

        if (this.templateEngine == null) {
            throw new ServletException("Thymeleaf template engine not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String action = request.getPathInfo();
        if (action == null || action.equals("/")) {
            listAdmins(request, response);
        } else {
            switch (action) {
                case "/listAdmins":
                    listAdmins(request, response);
                    break;
                case "/deleteAdmin":
                    deleteAdmin(request, response);
                    break;
                case "/search":
                	adminSearch(request, response);
                    break;    
                default:
                    response.sendRedirect(request.getContextPath() + "/admin");
                    break;
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String action = request.getPathInfo();
        System.out.println("action : "+ action);
        
        if (action == null || action.equals("/")) {
            listAdmins(request, response);
        } else {
            switch (action) {
                case "/insertAdmin":
                	insertAdmin(request, response);
                    break;
                case "/updateAdmin":
                	updateAdmin(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin");
                    break;
            }
        }
    }

    private void listAdmins(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    		    try {
    		        int currentPage = 1;
    		        int pageSize = 6; 
    		        
    		        String pageParam = request.getParameter("page");
    		        if (pageParam != null && !pageParam.isEmpty()) {
    		            currentPage = Integer.parseInt(pageParam);
    		        }
    		        
    		        List<Admin> allAdmins = userService.getAllAdmins();
    		        int totalAdmins = allAdmins.size();
    		        
    		        int totalPages = (int) Math.ceil((double) totalAdmins / pageSize);
    		        
    		        int startIndex = (currentPage - 1) * pageSize;
    		        int endIndex = Math.min(startIndex + pageSize, totalAdmins);
    		        
    		        List<Admin> currentPageAdmins = allAdmins.subList(startIndex, endIndex);
    		        
    		        WebContext context = new WebContext(
    		            request,
    		            response,
    		            getServletContext(),
    		            request.getLocale()
    		        );
    		        
    		        context.setVariable("admins", currentPageAdmins);
    		        context.setVariable("currentPage", currentPage);
    		        context.setVariable("totalPages", totalPages);
    		        context.setVariable("hasPrevious", currentPage > 1);
    		        context.setVariable("hasNext", currentPage < totalPages);
    		        
    		        response.setContentType("text/html;charset=UTF-8");
    		        templateEngine.process("users/admin", context, response.getWriter());
    		        
    		    } catch (Exception e) {
    		        e.printStackTrace();
    		        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch admins.");
    		    }
    		}
    
    private void insertAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String adminTypeparam = request.getParameter("adminType");

        if (adminTypeparam == null || adminTypeparam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Admin type is required.");
            return;
        }
        AdminType adminType = AdminType.valueOf(adminTypeparam);
        Admin admin = new Admin();
        admin.setNom(name);
        admin.setPrenom(prenom);
        admin.setEmail(email);
        admin.setMotDePasse(password);
        admin.setAdminType(adminType);

        boolean isCreated = userService.createAdmin(admin);

        if (isCreated) {
            response.sendRedirect("admin");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to create admin.");
        }
    }
    
    private void updateAdmin(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException {
    	int adminId = Integer.parseInt(request.getParameter("adminUpdate"));
    	String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String adminType = request.getParameter("adminType");
        AdminType adminType2 = AdminType.valueOf(adminType);
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setEmail(email);
        admin.setMotDePasse(motDePasse);
        admin.setAdminType(adminType2);
        
        boolean isUpdated = userService.updateAdmin(admin);
        
        if (isUpdated) {
            response.sendRedirect("admin");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to update Admin.");
        }
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("adminDelete"));
            boolean isDeleted = userService.deleteAdmin(id);
            if (isDeleted) {
                response.sendRedirect("admin");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete admin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid Client ID.");
        }
    }
    
    private void adminSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 int currentPage = 1;
	     int pageSize = 6; 
	     List<Admin> searchResults = new ArrayList<Admin>();
	        
	        String pageParam = request.getParameter("page");
	        if (pageParam != null && !pageParam.isEmpty()) {
	            currentPage = Integer.parseInt(pageParam);
	        }
	        
    	String terme = request.getParameter("searchTerm");
    	if (terme != null && !terme.isEmpty()) {
			searchResults = userService.searchAdmin(terme);
		}else {
			listAdmins(request, response);
		}
    	
    	 	int totalAdmins = searchResults.size();
	        
	        int totalPages = (int) Math.ceil((double) totalAdmins / pageSize);
	        
	        int startIndex = (currentPage - 1) * pageSize;
	        int endIndex = Math.min(startIndex + pageSize, totalAdmins);
	        
	        List<Admin> currentPageAdmins = searchResults.subList(startIndex, endIndex);
	        
    	WebContext context = new WebContext(
	            request,
	            response,
	            getServletContext(),
	            request.getLocale()
	        );
	        
	        context.setVariable("admins", currentPageAdmins);
	        context.setVariable("currentPage", currentPage);
	        context.setVariable("totalPages", totalPages);
	        context.setVariable("hasPrevious", currentPage > 1);
	        context.setVariable("hasNext", currentPage < totalPages);
	        
	        response.setContentType("text/html;charset=UTF-8");
	        templateEngine.process("users/admin", context, response.getWriter());
    	
    }
}
