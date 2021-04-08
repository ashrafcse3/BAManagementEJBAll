package servlet;

import dao.CustomerDTORemote;
import java.io.PrintWriter;
import java.util.Map;

import javax.ejb.EJB;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// injecting the session bean
		@EJB
	    private CustomerDTORemote cDTO; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		
		switch(param_action) {
		case "logout": // deposit £10 to the account
			HttpSession session = request.getSession(false);
			System.out.println("before session remove " + session.getAttribute("customerID"));
	        if (session != null) {
	            session.removeAttribute("customerID");
	             
	            response.setContentType("text/html");  
		        PrintWriter out = response.getWriter();  
		        out.print("<div class=\"successColor\">You logged out successfully.</div>"); 
	            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	            dispatcher.include(request, response);
	            System.out.println("after logout " + session.getAttribute("customerID"));
	        }
			break;
		default: break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    
	    int returnResult = cDTO.checkLogin(userName, password);
	    
	    if (returnResult == 0) {
	    	response.setContentType("text/html");  
	        PrintWriter out = response.getWriter();  
	        out.print("<div class=\"warningColor\">Your username or password did not match.</div>");  
	        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
	        rd.include(request,response);
	    } else {
	    	// save logged user id
	    	HttpSession session = request.getSession();
	    	session.setAttribute("customerID", returnResult);
	    	
	    	// get full details of customer with the ID
	    	Map<String, String> cDetails = cDTO.currentCustomerDetails(returnResult);
	    	
	    	String tableStr = new String();
	    	tableStr += "<table border='1'>";
			tableStr += "<tr><td>Name</td><td>Passport</td><td>Address</td><td>Phone Number</td><td>Email</td></tr>";
			tableStr+="<tr><td>"+cDetails.get("name")
					+"</td>"+"<td>"+cDetails.get("passport")
					+"</td>"+"<td>"+cDetails.get("address")
					+"</td>"+"<td>"+cDetails.get("phoneNumber")
					+"</td>"+"<td>"+cDetails.get("email")+"</td></tr>";
			tableStr += "</table>";
	    
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Customer | Profile</title>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"topnav\">\n"
					+ "  <a class=\"active\" href=\"#home\">Home</a>\n"
					+ "  <a href=\"CustomerServlet?action=logout\">Logout</a>\n"
					+ "</div>");
			out.println("<div class=\"customerTable\">");
			out.println("<h3>Customer details</h3>");
			out.println(tableStr);
			out.println("<h3>" + cDetails.get("name") + " account details</h3>");
			out.println("<a href=\"BankAccountServlet?action=viewAllAccountDetails\">All account by this customer and balances</a></br>");
			out.println("<a href=\"TransactionTypesServlet?action=readyDoWForm\">Deposit, Withdraw or Transfer</a></br>");
			out.println("Transfer to external account (future feature)</br>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			out.close();
			
	    	// show the profile page with his/her details
//	    	request.setAttribute("data", cDetails);
//	    	
//	    	RequestDispatcher rDispatcher = request.getRequestDispatcher("customerIndex.jsp");
//	    	rDispatcher.forward(request, response);
	    	
	    	//System.out.println(cDetails);
	    }
	}

}
