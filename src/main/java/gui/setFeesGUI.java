package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Question;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class setFeesGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;
	private int i = -1;
	private int j = -1;
	private final JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("EventDate"));
	private final JLabel questionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Questions")); 
	private final JLabel eventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Events")); 

	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	
	private JButton setFeeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetFee"));
	
	JTextPane answerTextPane = new JTextPane();
	
	
	JLabel resultLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Result")); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	JLabel feeLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SetFee")); //$NON-NLS-1$ //$NON-NLS-2$
	
	

	// Code for JCalendar
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar;
	private Calendar currentCalendar;
	private JScrollPane eventScrollPane = new JScrollPane();
	private JScrollPane questionScrollPane = new JScrollPane();

	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();

	private JTable eventTable= new JTable();
	private JTable questionTable = new JTable();

	private DefaultTableModel eventTableModel;
	private DefaultTableModel questionTableModel;

	private String[] eventColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] questionColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QuestionN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question")
	};
	private JTextField resultTxtField;
	private JTextField feeTxtField;


	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public setFeesGUI(BlFacade bl) {
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isNumber(String s) {
	    try { 
	        Float.parseFloat(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}


	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));

		eventDateLbl.setBounds(new Rectangle(40, 15, 140, 25));
		questionLbl.setBounds(295, 248, 283, 14);
		eventLbl.setBounds(295, 19, 259, 16);

		this.getContentPane().add(eventDateLbl, null);
		this.getContentPane().add(questionLbl);
		this.getContentPane().add(eventLbl);
		
		answerTextPane.setBounds(40, 430, 173, 19);
		getContentPane().add(answerTextPane,null);

		closeBtn.setBounds(new Rectangle(274, 419, 130, 30));
		

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		
		setFeeBtn.setBounds(55, 380, 125, 21);
		
		setFeeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(resultTxtField.getText().equals("")) {
					answerTextPane.setText("You must enter a result.");
				}
				else if(feeTxtField.getText().equals("")) {
					answerTextPane.setText("You must enter a fee.");
				}
				else if(!isNumber(feeTxtField.getText())) {
					answerTextPane.setText("Fee must be a number.");
				}
				else if(isNumber(feeTxtField.getText()) && Float.parseFloat(feeTxtField.getText())<1) {
					answerTextPane.setText("Fee must be bigger than 1.");
				}
				else if(i == -1) {
					answerTextPane.setText("You must select an event.");
				}
				else if(j == -1) {
					answerTextPane.setText("You must select a question.");
				}
				else if(businessLogic.feeExists(resultTxtField.getText(),(String) questionTableModel.getValueAt(j,1))) {
					answerTextPane.setText("You must select another result.");
				}
				else {
					domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2);
					String quest = (String) questionTableModel.getValueAt(j,1);
					businessLogic.setFee(resultTxtField.getText(),Float.parseFloat(feeTxtField.getText()),quest,ev);
					answerTextPane.setText("Fee entered correctly.");
				}
			}
		});
		
		resultLbl.setBounds(40, 279, 45, 13);
		this.getContentPane().add(resultLbl,null);
		
		feeLbl.setBounds(40, 333, 45, 13);
		getContentPane().add(feeLbl,null);

		this.getContentPane().add(closeBtn, null);
		this.getContentPane().add(setFeeBtn, null);

		calendar.setBounds(new Rectangle(40, 50, 225, 150));

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				if (propertyChangeEvent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertyChangeEvent.getNewValue());
				}
				else if (propertyChangeEvent.getPropertyName().equals("calendar")) {
					previousCalendar = (Calendar) propertyChangeEvent.getOldValue();
					currentCalendar = (Calendar) propertyChangeEvent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());
					Date firstDay = UtilDate.trim(new Date(calendar.getCalendar().getTime().getTime()));

					int previousMonth = previousCalendar.get(Calendar.MONTH);
					int currentMonth = currentCalendar.get(Calendar.MONTH);

					if (currentMonth != previousMonth) {
						if (currentMonth == previousMonth + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, 
							// devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							currentCalendar.set(Calendar.MONTH, previousMonth + 1);
							currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}						

						calendar.setCalendar(currentCalendar);
						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.
								getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);

					try {
						eventTableModel.setDataVector(null, eventColumnNames);
						eventTableModel.setColumnCount(3); // another column added to allocate ev objects

						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty() ) eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("NoEvents") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						else eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("Events") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						for (domain.Event ev : events){
							Vector<Object> row = new Vector<Object>();
							System.out.println("Events " + ev);
							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); 	// ev object added in order to obtain it with 
							// tableModelEvents.getValueAt(i,2)
							eventTableModel.addRow(row);		
						}
						eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
						eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
						eventTable.getColumnModel().removeColumn(eventTable.getColumnModel().
								getColumn(2)); // not shown in JTable
					}
					catch (Exception e1) {
						questionLbl.setText(e1.getMessage());
					}
				}
			} 
		});

		this.getContentPane().add(calendar, null);

		eventScrollPane.setBounds(new Rectangle(292, 50, 346, 150));
		questionScrollPane.setBounds(new Rectangle(292, 272, 346, 116));

		eventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				j=-1;
				i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2); // obtain ev object
				Vector<Question> queries = ev.getQuestions();

				questionTableModel.setDataVector(null, questionColumnNames);

				if (queries.isEmpty())
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("NoQuestions") + ": " + ev.getDescription());
				else 
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("SelectedEvent") + " " + ev.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					questionTableModel.addRow(row);	
				}
				questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
				questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		eventScrollPane.setViewportView(eventTable);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);

		eventTable.setModel(eventTableModel);
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		questionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				j = questionTable.getSelectedRow();
//				domain.Question quest = (domain.Question)questionTableModel.getValueAt(i,2); // obtain ev object
//				String questions = quest.getQuestion();
			}
		});

		questionScrollPane.setViewportView(questionTable);
		questionTableModel = new DefaultTableModel(null, questionColumnNames);

		questionTable.setModel(questionTableModel);
		questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(eventScrollPane, null);
		this.getContentPane().add(questionScrollPane, null);
		
		
		
		resultTxtField = new JTextField();
		resultTxtField.setBounds(95, 276, 96, 19);
		getContentPane().add(resultTxtField);
		resultTxtField.setColumns(10);
		
		feeTxtField = new JTextField();
		feeTxtField.setBounds(95, 330, 96, 19);
		getContentPane().add(feeTxtField);
		feeTxtField.setColumns(10);
		
		
		
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}