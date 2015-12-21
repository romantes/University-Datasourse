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
import dao.LectureDAO;

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
	private static final String PERSONAL = "/personal.jsp";
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

		String forward = "";
		String action = request.getParameter("action");
		RequestDispatcher rdError = request.getRequestDispatcher(ERROR);

		logger.info("if clouse defining the action");
		logger.trace(action);

		if (action.equalsIgnoreCase("personal")) {
			logger.info("im in personal if clause");

			forward = LIST_LECTURES;

			logger.info("doGet: get parameters");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			String dateString = request.getParameter("inputDate");
			logger.trace("Request date paramater: " + dateString);
			String personalId = request.getParameter("personalId");
			logger.trace("Request id parameter: " + personalId);
			logger.info("doGet: parameters recieved successfully");

			try {
				Date scheduleDate = df.parse(dateString);
				date = scheduleDate;
				logger.trace("Date provided for serch in DB: " + date);
			} catch (ParseException e) {
				logger.error(e);
				String mes = "Unfortunately you date input is incorrect, please try again";
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				logger.info("doGet: forwarding " + rdError);
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
				throw new ServletException("exception in doGet, delete if clause, DAO ", e);
			}

			logger.info("doGet: request.setAtributes");

			try {
				List<Lecture> lectures = sb.extractLecturesByIdAndDate(date,
						personid);
				request.setAttribute("lectures", lectures);
				request.setAttribute("person", sb.getPerson());
				logger.info("attributes was set: ");
				logger.trace(request.getAttribute("lectures"));
				logger.info("doGet: forward to LIST_LECTURES");

			} catch (PersonExistsException | DAOException e) {
				logger.error(e);
				String mes = new String(e.toString());
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				throw new ServletException("exception in doGet, delete if clause, DAO ", e);
			}
			logger.info("doGet: if clouse personal: done");

		}

		if (action.equalsIgnoreCase("viewall")) {
			logger.info("i'm in viewall clause");
			forward = LIST_LECTURES;
			logger.trace(forward);
			try {
				sb = new ScheduleBoard();
			} catch (DAOException e) {
				logger.error(e);
				String mes = new String(e.toString());
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				throw new ServletException("exception in doGet, delete if clause, DAO ", e);
			}
			request.setAttribute("lectures", sb.getLectures());
			logger.trace(request.getAttribute("lectures"));
		}

		if (action.equalsIgnoreCase("delete")) {
			logger.info("i'm in delete clause");
			forward = "/Servlet?action=viewall";
			String idString = request.getParameter("id");
			logger.info(idString);
			long id = Long.parseLong(idString);
			try {
				new LectureDAO().deleteLecture(id);
			} catch (DAOException e) {
				logger.error(e);
				String mes = new String(e.toString());
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				throw new ServletException("exception in doGet, delete if clause, DAO ", e);
			}
		}
		
		if(action.equalsIgnoreCase("edit")) {
			logger.info("i'm in edit clause");
			forward = "/Servlet?action=viewall";
			
			DateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm", Locale.ENGLISH);
			String dateString = request.getParameter("inputDate");
			logger.trace(dateString);
			Date date =  null;
			Long professorid = null;
			String subjecttitle = request.getParameter("inputSubject");
			String professoridString = request.getParameter("inputProfessor");
			String grup = request.getParameter("inputGroup");
			String roomnumber = request.getParameter("inputRoom");
			logger.info("all recieved parameters: " + dateString + " " + subjecttitle + " " + professoridString + " " + grup + " " + roomnumber );
			try {
				logger.info("parsing date");
				date = df.parse(dateString);  
				logger.info("professoridString: " + professoridString);
				professorid = Long.parseLong(professoridString);
			} catch (ParseException e) {
				logger.error(e);
				String mes = "Unfortunately you date input is incorrect, please try again";
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				logger.info("doGet: forwarding " + rdError);
			}
			
			try {
				new LectureDAO().createLecture(date, subjecttitle, professorid, grup, roomnumber);
			} catch (DAOException e) {
				logger.error(e);
				String mes = e.toString();
				request.setAttribute("errorMessage", mes);
				rdError.forward(request, response);
				logger.info("doGet: forwarding " + rdError);
			}
		}
		
		RequestDispatcher Forward = request.getRequestDispatcher(forward);
		Forward.forward(request, response);
	}

}
