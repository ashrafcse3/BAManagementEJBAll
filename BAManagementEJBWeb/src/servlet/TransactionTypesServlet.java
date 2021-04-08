package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TransactionTypesDTORemote;

/**
 * Servlet implementation class TransactionTypesServlet
 */
@WebServlet("/TransactionTypesServlet")
public class TransactionTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// injecting the session bean
			@EJB
		    private TransactionTypesDTORemote ttDTO; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionTypesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		
		switch(param_action) {
		case "readyDoWForm": // deposit £10 to the account
	        // grab the transaction types from derby
			String[] tTypesName = ttDTO.getTransactionTypesName();
				
				// redirect them on deposit or withdraw form with the transaction types
	            response.setContentType("text/html");  
		        request.setAttribute("tTypes", tTypesName);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("transaction.jsp");
	            dispatcher.forward(request, response);
			break;
		default: break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
