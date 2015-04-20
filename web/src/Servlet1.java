import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.*;

public class Servlet1 extends HttpServlet 
{
	ServletConfig ctx;
	private static final long serialVersionUID = 1L;

	public Servlet1() 
	{
		super();
	}

	public void init(ServletConfig config)
	{
		ctx = config;

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter pr=response.getWriter();
		

		Thread th[] = new Thread[10];

		for (int y = 0; y <= 90; y++) 
		{
			th[y] = new Thread(new LoadThread(request.getParameter("id"),pr));
//			th[y] = new Thread(new LoadThread(request.getParameter("id")));
			th[y].start();
			
						
			
		}

		

	}

	
	class LoadThread implements Runnable {
		PrintWriter pr;
		String id;
		LoadThread(String user_id,PrintWriter pr)

		{
			this.id = user_id;
			this.pr=pr;
		}
		
		@Override
		public void run() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://192.168.29.178:3306/newdb", "root", "ibha");
				//Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "ibha");
				String str = "select * from creden2 where id=?";
				PreparedStatement ps = cn.prepareStatement(str);
				ctx.getServletContext().setAttribute("ps", ps);
				ctx.getServletContext().setAttribute("cn", cn);
				System.out.println("hello in init");
				this.testClient(pr);
//		
				
				
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		

//		public void testClient() 
		public void testClient(PrintWriter pr) 
		{
			

			try 
			{
				PreparedStatement ps = (PreparedStatement) ctx.getServletContext().getAttribute("ps");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) 
				{
					String s1=rs.getString(1);
					String s2=rs.getString(2);
					String s3=rs.getString(3);
					
					pr.print("hello"+s1+"\t"+s2+"\t"+s3);
					System.out.println(s1+"\t"+s2+"\t"+s3);
				}
	                                                             
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();

			} 
			catch (Exception e) 
			{
				System.out.println("Exception Block");
			}

		}
	}
}
