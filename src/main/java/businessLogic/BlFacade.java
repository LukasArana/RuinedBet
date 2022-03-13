package businessLogic;


import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BlFacade  {

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum) 
			throws EventFinished, QuestionAlreadyExist;
		
	/**
	 * This method retrieves all the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	@WebMethod public boolean usernameIsFree(String username);
	
	@WebMethod public User registerNewUser(int age, String username, String password, String name, String surname, String email);
	
	@WebMethod public boolean emailIsFree(String email);
	@WebMethod public boolean checkLogIn(String username, String password);
	@WebMethod public boolean isAdmin(String username);
	
	public Event createEvent(Integer eventNumber, String description,Date date);
}