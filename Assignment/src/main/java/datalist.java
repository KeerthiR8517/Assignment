

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class datalist
 */
@WebServlet("/datalist")
public class datalist extends HttpServlet {

	    private static final String query = "SELECT * FROM data";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        PrintWriter pw = res.getWriter();
	        res.setContentType("text/html");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException cnf) {
	            cnf.printStackTrace();
	        }

	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/formdata", "root", "1234");
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            pw.println("<html><body><table border='1'><tr><th>Name</th><th>Email</th><th>Phone</th></tr>");

	            while (rs.next()) {
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String phone = rs.getString("phone");

	                pw.println("<tr><td>" + name + "</td><td>" + email + "</td><td>" + phone + "</td></tr>");
	            }

	            pw.println("</table></body></html>");

	        } catch (SQLException se) {
	            se.printStackTrace();
	            pw.println("<h1>" + se.getMessage() + "</h1>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>" + e.getMessage() + "</h1>");
	        }
	    }
	}




 
 
