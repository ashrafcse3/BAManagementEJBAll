package servlet;

import dao.TransactionDetailsDTORemote;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransactionDetailsServlet
 */
@WebServlet("/TransactionDetailsServlet")
public class TransactionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// injecting the session bean
	@EJB
	private TransactionDetailsDTORemote tdRemote;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			int transactionTypes = Integer.parseInt(request.getParameter("transactionTypes"));
		    Double amount = Double.parseDouble(request.getParameter("amount"));
		    int frombankaccountnumber = Integer.parseInt(request.getParameter("frombankaccountnumber"));
		    int tobankaccountnumber = Integer.parseInt(request.getParameter("tobankaccountnumber"));
		    
		    String fromTableStr = new String();
		    String toTableStr =  new String();
		    // send the data to the DTO
		    double returnResult[] = tdRemote.insertTransactionDetails(transactionTypes, amount, frombankaccountnumber, tobankaccountnumber);
		    System.out.println(Arrays.toString(returnResult));
		    
		    // initialize the html contents
		    response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Customer | Trnasaction results</title>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"topnav\">\n"
					+ "  <a class=\"active\" href=\"#home\">Home</a>\n"
					+ "  <a href=\"CustomerServlet?action=logout\">Logout</a>\n"
					+ "</div>");
			out.println("<div class=\"successColor\">Your transaction was successful.</div>");
			out.println("<div class=\"customerTable\">");
			
		    	if (transactionTypes == 2) {
		    		fromTableStr += "<table border='1'>";
		    		fromTableStr += "<tr><td>Account Number</td><td>Balance</td></tr>";
		    		fromTableStr+="<tr><td>"+ frombankaccountnumber
							+"</td>"+"<td>"+ returnResult[2] + "</td></tr>"
									+ "<tr><td>" + frombankaccountnumber
									+"</td><td class=\"newAmount\">" + returnResult[3] + "</td></tr>";
		    		fromTableStr += "</table>";
		    		
		    		out.println("<div class=\"oldBankTable\">");
					out.println("<h3>Debited Bank Account Details</h3>");
					out.println(fromTableStr);
					out.println("</div>");
		    	}
		    	
		    	// add a table to show the old and new account value
		    	toTableStr += "<table border='1'>";
	    		toTableStr += "<tr><td>Account Number</td><td>Balance</td></tr>";
	    		toTableStr+="<tr><td>"+ tobankaccountnumber
						+"</td>"+"<td>"+ returnResult[0] + "</td></tr>"
								+ "<tr><td>" + tobankaccountnumber
								+"</td><td class=\"newAmount\">" + returnResult[1] + "</td></tr>";
	    		toTableStr += "</table>";
	    		
	    		out.println("<div class=\"oldBankTable\">");
				out.println("<h3>Credited Bank Account Details</h3>");
				out.println(toTableStr);
				out.println("</div>");
				
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
				out.close();
		} catch (Exception e) {
			
		}
		
	}

}
