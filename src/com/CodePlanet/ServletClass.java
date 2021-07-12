package com.CodePlanet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletClass {
@SuppressWarnings("unused")
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val=req.getParameter("psw-repeat");
		String val1=req.getParameter("email");
		String val2=req.getParameter("psw");
		System.out.print(val1+" "+ val2);
		req.setAttribute("email",val);
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:8000/APP_2","root" ,"admin");
			Statement st=con.createStatement();
			//String query="insert into test values('abc@gmail.com','abc')";
			//int x=st.executeUpdate(query);
			//System.out.println(x);
			String query="select * from user";
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				System.out.println("your email is " +rs.getString("email"));
				System.out.println("your password is " +rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("first.jsp");
		dispatcher.forward(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val=req.getParameter("psw-repeat");
		String val1=req.getParameter("email");
		String val2=req.getParameter("psw");
		System.out.print(val1+" "+ val2);
		
		req.setAttribute("email",val);
		PrintWriter out=res.getWriter();
		out.print("<h1>HelloWelcome " + val + "ToCodePlanet post request</h1>");
		out.close();
		RequestDispatcher dispatcher = req.getRequestDispatcher("first.jsp");
		dispatcher.forward(req,res);
	}

}
