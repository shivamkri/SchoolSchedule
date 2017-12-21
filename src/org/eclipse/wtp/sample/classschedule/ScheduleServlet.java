package org.eclipse.wtp.sample.classschedule;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
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
		String title = request.getParameter("title");
		int starttime = Integer.parseInt(request.getParameter("starttime")); 
		int endtime = Integer.parseInt(request.getParameter("endtime")); 
		String[] days = request.getParameterValues("day");
		
		SchoolSchedule schedule = (SchoolSchedule)request.getSession(true).getAttribute("schoolschedule"); 
		if(schedule == null)
		{
			schedule = new SchoolSchedule();
		}
		
		if(days != null)
		{
			for(int i = 0; i < days.length; i++)
			{
				String dayString = days[i];
				int day;
				if(dayString.equalsIgnoreCase("SUN")) day = 0;
				else if(dayString.equalsIgnoreCase("MON")) day = 1; 
				else if(dayString.equalsIgnoreCase("TUE")) day = 2; 
				else if(dayString.equalsIgnoreCase("WED")) day = 3; 
				else if(dayString.equalsIgnoreCase("THU")) day = 4; 
				else if(dayString.equalsIgnoreCase("FRI")) day = 5; 
				else day = 6;
				SchoolClass clazz = new SchoolClass(title, starttime, endtime, day); 
				schedule.addClass(clazz);
			}
		}
		
		request.getSession().setAttribute("schoolschedule", schedule); 
		getServletContext().getRequestDispatcher("/Schedule.jsp").forward(request, response);
	}
}
