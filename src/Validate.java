import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    
		String date = request.getParameter("date");
		
		if (isDateValid(date)) {
			//response.sendRedirect("Valid.jsp");
			out.print("Valid Date!");  
	        RequestDispatcher rd=request.getRequestDispatcher("/Form.jsp");  
	        rd.include(request, response);  
		} else {
			//response.sendRedirect("Error.jsp");
			out.print("Invalid Date!");  
	        RequestDispatcher rd=request.getRequestDispatcher("/Form.jsp");  
	        rd.include(request, response);
		}
	}
	
	private static boolean isDateValid(String Date) {
		
		String dateParts[] = Date.split("/"); 
		
		int date = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);	
		
		if (month < 1 || month > 12)
			return false;
		
		if (date < 1 || date > 31)
			return false;
		 
		if (month == 2)
		{
			if (isLeap(year))
				return (date <= 29);
		    else
		    	return (date <= 28);
		}
		 
		if (month==4 || month==6 || month==9 || month==11)
			return (date <= 30);
		 
		return true;
	}
	
	private static boolean isLeap(int year)
	{
		  return (((year%4==0) && (year%100!=0)) || (year%400==0));
	}	 
}
