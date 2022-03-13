package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BlFacade;
import domain.Event;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPane;
	protected JLabel selectOptionLbl;
	private JButton browseQuestionsBtn;
	private JButton createQuestionBtn;
	private JPanel localePane;
	private JRadioButton euskaraRbtn;
	private JRadioButton castellanoRbtn;
	private JRadioButton englishRbtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private BlFacade businessLogic;
	private JButton createEventsButton;
	private JButton setFeesButton;

	public BlFacade getBusinessLogic(){
		return businessLogic;
	}

	public void setBussinessLogic (BlFacade afi){
		businessLogic = afi;
	}


	public MainGUI() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				}
				catch (Exception e1) {
					System.out.println("Error: " + e1.toString() + " , likely problems "
							+ "with Business Logic or Data Accesse");
				}
				System.exit(1);
			}
		});

		this.setBounds(100, 100, 449, 262);

		this.initializeMainPane();
		this.setContentPane(mainPane);

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeMainPane() {
		mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(6, 1, 0, 0));

		selectOptionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
				getString("SelectUseCase"));
		selectOptionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainPane.add(selectOptionLbl);

		initializeBrowseQuestionsBtn();
		mainPane.add(browseQuestionsBtn);
		initializeCreateQuestionBtn();
		mainPane.add(createQuestionBtn);
		initializeCreateEventsButton();
		mainPane.add(createEventsButton);
		initializeSetFeesButton();
		mainPane.add(setFeesButton);
		
		initializeLocalePane();
//		{
//		}
//		{
//		}
		mainPane.add(localePane);
	}
	
	private void initializeSetFeesButton() {
		setFeesButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private void initializeCreateEventsButton() {
		createEventsButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		createEventsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEventGUI createQuestionWindow = new CreateEventGUI(businessLogic,
						new Vector<Event>());
				createQuestionWindow.setBusinessLogic(businessLogic);
				createQuestionWindow.setVisible(true);
			}
		});
	}

	private void initializeBrowseQuestionsBtn() {
		browseQuestionsBtn = new JButton();
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("BrowseQuestions"));
		browseQuestionsBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BrowseQuestionsGUI findQuestionsWindow = new BrowseQuestionsGUI(businessLogic);
				findQuestionsWindow.setVisible(true);
			}
		});
	}

	private void initializeCreateQuestionBtn() {
		createQuestionBtn = new JButton();
		createQuestionBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("CreateQuestion"));
		createQuestionBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				CreateQuestionGUI createQuestionWindow = new CreateQuestionGUI(businessLogic,
						new Vector<Event>());
				createQuestionWindow.setBusinessLogic(businessLogic);
				createQuestionWindow.setVisible(true);
			}
		});
	}

	private void initializeLocalePane() {
		localePane = new JPanel();

		initializeEuskaraRbtn();
		localePane.add(euskaraRbtn);

		initializeCastellanoRbtn();
		localePane.add(castellanoRbtn);

		initializeEnglishRbtn();
		localePane.add(englishRbtn);
	}

	private void initializeEuskaraRbtn() {
		euskaraRbtn = new JRadioButton("Euskara");
		euskaraRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(euskaraRbtn);
	}

	private void initializeCastellanoRbtn() {
		castellanoRbtn = new JRadioButton("Castellano");
		castellanoRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(castellanoRbtn);
	}

	private void initializeEnglishRbtn() {
		englishRbtn = new JRadioButton("English");
		englishRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();				}
		});
		buttonGroup.add(englishRbtn);
	}

	private void redraw() {
		selectOptionLbl.setText(ResourceBundle.getBundle("Etiquetas").
				getString("SelectUseCase"));
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("BrowseQuestions"));
		createQuestionBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("CreateQuestion"));
		createEventsButton.setText(ResourceBundle.getBundle("Etiquetas").
				getString("CreateEvent"));
		setFeesButton.setText(ResourceBundle.getBundle("Etiquetas").
				getString("SetFee"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
}