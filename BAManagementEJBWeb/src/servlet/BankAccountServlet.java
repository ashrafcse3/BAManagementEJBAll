package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BankAccountDTORemote;

/**
 * Servlet implementation class BankAccountServlet
 */
@WebServlet("/BankAccountServlet")
public class BankAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// injecting the session bean
			@EJB
		    private BankAccountDTORemote baDTO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountServlet() {
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
		// get the current user session
		HttpSession session = request.getSession(false);
		int userID = (int) session.getAttribute("customerID");
		
		switch(param_action) {
		case "viewAllAccountDetails": // View the balance of the current account
			// get the current user session
//			HttpSession session = request.getSession(false);
//			int userID = (int) session.getAttribute("customerID");
			
			// bring the details from database
			List<Map<String, String>> baDetails = baDTO.allAccountDetails(userID);
			
			String tableStr = new String();
	    	tableStr += "<table border='1'>";
			tableStr += "<tr><td>No.</td><td>Name</td><td>Account Number</td><td>Balance</td><td>Type</td><td>Branch Name</td><td>IBAN</td></tr>";
			for(int i=0; i<baDetails.size();i++) {
			tableStr+="<tr><td>"+ Integer.toString(i + 1)
					+"</td>"+"<td>"+baDetails.get(i).get("customerName")
					+"</td>"+"<td>"+baDetails.get(i).get("accountNumber")
					+"</td>"+"<td>"+baDetails.get(i).get("accountMoney")
					+"</td>"+"<td>"+baDetails.get(i).get("accountType")
					+"</td>"+"<td>"+baDetails.get(i).get("branchName")
					+"</td>"+"<td>"+baDetails.get(i).get("IBAN")+"</td></tr>";
			}
			tableStr += "</table>";

			//show the account money in a table
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Customer | Accounts</title>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"topnav\">\n"
					+ "  <a class=\"active\" href=\"#home\">Home</a>\n"
					+ "  <a href=\"CustomerServlet?action=logout\">Logout</a>\n"
					+ "</div>");
			out.println("<div class=\"customerTable\">");
			out.println("<h3>Account details</h3>");
			out.println(tableStr);
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			out.close();
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
