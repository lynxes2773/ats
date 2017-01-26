package ats.web.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ats.data.DAOProviderHibernateImpl;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class CandidateServiceServlet
 */
@WebServlet("/CandidateServiceServlet")
public class CandidateServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String HTML_FOOTER_CONTENT = "</body></html>";
	private static String CANDIDATES_PAGE = "/WEB-INF/jsp/candidates.jsp";
	
	private String exceptionMessage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		DAOProviderHibernateImpl manager = new DAOProviderHibernateImpl();
//		List candidates = manager.getCandidates();
//		request.setAttribute("candidates", candidates);
//		
//		ServletContext ctx = this.getServletContext();
//		RequestDispatcher dispatcher = ctx.getRequestDispatcher(this.CANDIDATES_PAGE);
//		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
