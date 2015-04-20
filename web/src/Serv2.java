


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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Serv2 extends HttpServlet {
	ServletConfig ctx;
	private static final long serialVersionUID = 1L;
    public Serv2() {
        super();
    }
    //ServletContext ctx;
public void init(ServletConfig config)
{
	ctx=config;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://192.168.29.178:3306/newdb", "root", "ibha");
		//Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "ibha");
		String str = "select * from creden2 where id=?";
		PreparedStatement ps = cn.prepareStatement(str);
		ctx.getServletContext().setAttribute("ps", ps);
		ctx.getServletContext().setAttribute("cn", cn);
		System.out.println("hello in init");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PreparedStatement ps=(PreparedStatement)ctx.getServletContext().getAttribute("ps");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		final String id1 = request.getParameter("id");
		String pwd1 = request.getParameter("pwd");
		ctx.getServletContext().setAttribute("out", out);
		
		out.println("hello in do post");
		Runnable r=new  Runnable() {
			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				testClient(id1);
			}
		};
		Thread th[]=new Thread[10];
		for(int y=0;y<=9;y++)
		{
			th[y]=new Thread(r);
		th[y].start();
		}
			
			/*ps.setString(1, id1);
			ps.setString(2, pwd1);
			ps.executeUpdate();*/
			
			
		 	
           		}
	public void testClient(String id1)
	{
		PrintWriter out=(PrintWriter)ctx.getServletContext().getAttribute("out");
		
			try
		{
				out.println("hello in testclient");
			PreparedStatement ps=(PreparedStatement)ctx.getServletContext().getAttribute("ps");
		    ps.setString(1, id1);
			ResultSet rs=ps.executeQuery();
		
					while(rs.next()) 
					{
						String s1=rs.getString(1);
						out.println(s1);
						out.println("  ");
					}  
					
		} 
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			}
			catch(Exception e)
			{
				out.println("skshjgash");
			}
			
		
			
		}
	}

