package domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.postgresql.jdbc2.TimestampUtils;

import dao.*;
import domain.exceptions.*;

public class Main {
	public static void main(String[] args) throws DAOException, LectureExistsException, PersonExistsException {
		try {
			
			Date dateLecture1 = new Date(2015-1900, 10, 25, 9,0);
			Date dateLecture2 = new Date(2015-1900, 10, 26, 10,0);
			Date dateLecture3 = new Date(2015-1900, 10, 25, 13,0);
			Date dateLecture4 = new Date(2015-1900, 10, 26, 18,30);
				
		ScheduleBoard sc = new ScheduleBoard();
		// new LectureDAO().getAllLectures() - return arraylist of all existed lectures; 
		List<Lecture> list = new LectureDAO().getAllLectures();
		// set all lectures to schedule board
		sc.setLectures(list);
		// we can print them using printLecturesToConsole() method 
		sc.printLecturesToConsole();
		// firstly, we extract lectures by date and personal id parameter
		// secondly, we put them to scedule board field
		sc.setLectures(sc.extractLecturesByIdAndDate(dateLecture1,((long)2008)));
		// then print
		sc.printLecturesToConsole();
		System.out.println();
		
		List<Professor> proflist = new ProfessorDAO().getAllProfessors();
		for (Person p : proflist) {
			System.out.println(p.getSecondName() + " " + p.getPersonId());
		}
		
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("Shoot (");
		}
		
	}

}
