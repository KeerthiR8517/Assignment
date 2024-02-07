

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Info
 */
@WebServlet("/Info")
public class Info extends HttpServlet {
	 private static final String query = "INSERT INTO info(name, number, friend_name,friend_number) VALUES (?, ?, ?, ?)";

       
	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        PrintWriter pw = res.getWriter();
	        res.setContentType("text/html");

	        String name = req.getParameter("name");
	        String number = req.getParameter("number");
	        String friend_name = req.getParameter("friend_name");
	        String friend_number = req.getParameter("friend_number");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException cnf) {
	            cnf.printStackTrace();
	        }

	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/formdata", "root", "1234");
	             PreparedStatement ps = con.prepareStatement(query)) {

	            ps.setString(1, name);
	            ps.setString(2, number);
	            ps.setString(3, friend_name);
	            ps.setString(4, friend_number);

	            int count = ps.executeUpdate();
	            if (count == 1) {
	                //req.getRequestDispatcher("question4.html").include(req, res);
	                pw.println("<h2> Thank you for the Refferal..</h2>");
	               
	            } else {
	                req.getRequestDispatcher("question4.html").include(req, res);
	                pw.println("<h2>something wrong Try again</h2>");
	            }

	        } catch (SQLException se) {
	            se.printStackTrace();
	            pw.println("<h1>" + se.getMessage() + "</h2>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>" + e.getMessage() + "</h2>");
	        }
	    }
	}

