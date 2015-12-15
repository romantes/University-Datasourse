package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import domain.Lecture;
import domain.ScheduleBoard;
import domain.exceptions.PersonExistsException;
import dao.DAOException;


/**
 * Servlet implementation class UniversityServlet
 */
@WebServlet(description = "ScheduleBoard", urlPatterns = { "/Servlet" })
public class UniversityController extends HttpServlet {
	
	private static final Logger logger = Logger
			.getLogger(UniversityController.class);
	private static final long serialVersionUID = 1L;
	private static final String LIST_LECTURES = "/lectures.jsp";
	private static final String ERROR = "/error.jsp";
	
	ScheduleBoard sb;
	long personid;
	Date date;
	
	
	public UniversityController() throws ServletException {
		try {
			sb = new ScheduleBoard();
		} catch (DAOException e) {
			logger.error(e);
			throw new ServletException("Exception in ScheduleBoard init.", e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		logger.info("doGet: get parameters");
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
		String dateString = request.getParameter("inputDate");
		logger.trace("Request date paramater: " + dateString);
		String personalId = request.getParameter("personalId");
		logger.trace("Request id parameter: " + personalId);
		logger.info("doGet: parameters recieved successfully");
		
		RequestDispatcher rdError = request.getRequestDispatcher(ERROR);
		
		try {
			Date scheduleDate = df.parse(dateString);
			date = scheduleDate;
			logger.trace("Date provided for serch in DB: " + date);
		} catch (ParseException e) {
			logger.error(e);
			String mes = "Unfortunately you date input is incorrect, please try again";
			request.setAttribute("errorMessage", mes);
			rdError.forward(request, response);
			logger.info("doGet: forwarding " +  rdError);
		}

		try {
			long id = Long.parseLong(personalId);
			this.personid = id;
			logger.trace(id);

		} catch (NumberFormatException e) {
			logger.error(e);
			String mes = e.toString();
			request.setAttribute("errorMessage", mes);
			rdError.forward(request, response);
			logger.info("doGet: forwarding " +  rdError);
		}
	
		logger.info("doGet: request.setAtributes");
		
		try {
			List<Lecture> lectures = sb.extractLecturesByIdAndDate(date, personid);
			request.setAttribute("lectures", lectures);
			request.setAttribute("person", sb.getPerson());
			logger.info("attributes was set: ");
			logger.trace(request.getAttribute("lectures"));
			logger.info("doGet: forward to LIST_LECTURES");
			RequestDispatcher Forward = request.getRequestDispatcher(LIST_LECTURES); 
			Forward.forward(request, response);
				
		} catch (PersonExistsException | DAOException e) {
			logger.error(e);
			PrintWriter writer = response.getWriter();
			writer.write("<p><center> unfortunately user with such id does not exist "
					+ "<p> <a href = 'index.jsp'> <b>Return</b> </a> </center>");
			writer.close();
		}
		logger.info("doGet: done");
	}

}
