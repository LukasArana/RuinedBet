package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passField;
	private JTextField ageField;
	private BlFacade businessLogic;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationGUI frame = new RegistrationGUI();
					frame.setVisible(true);
					frame.setBusinessLogic(new BlFacadeImplementation());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;		
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

	/**
	 * Create the frame.
	 */
	public RegistrationGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel titleLabel = new JLabel("Enter your information to register");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		
		passField = new JPasswordField();
		
		ageField = new JTextField();
		ageField.setColumns(10);
		
		JTextPane answerPane = new JTextPane();
		
		JButton alreadyRegisteredButton = new JButton("I have an account");
		alreadyRegisteredButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInGUI lig = new LogInGUI();
				lig.setVisible(true);
				lig.setBusinessLogic(businessLogic);
			}
		});
		alreadyRegisteredButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().equals("")) {
					answerPane.setText("You must enter a username.");
				}
				else if(passField.getPassword().length == 0) {
					answerPane.setText("You must enter a password.");
				}
				else if(ageField.getText().equals("")) {
					answerPane.setText("You must enter an age.");
				}
				else if(!isInteger(ageField.getText())) {
					answerPane.setText("Your age must be a number.");
				}
				else if(Integer.parseInt(ageField.getText()) <= 18){
					answerPane.setText("You must be 18 or older to create an account.");
				}
				else if(nameField.getText().equals("")) {
					answerPane.setText("You must enter a name.");
				}
				else if(surnameField.getText().equals("")) {
					answerPane.setText("You must enter a surname.");
				}
				else if(emailField.getText().equals("")) {
					answerPane.setText("You must enter an email.");
				}
				else if(!businessLogic.emailIsFree(emailField.getText())) {
					answerPane.setText("Email is already in use.");
				}
				else if(!businessLogic.usernameIsFree(usernameField.getText())){
					answerPane.setText("Username is already in use.");
				}
				else {
					businessLogic.registerNewUser(Integer.parseInt(ageField.getText()) , usernameField.getText(), new String(passField.getPassword()), nameField.getText(), surnameField.getText(), emailField.getText());
					answerPane.setText("You have correctly created an account.");
					alreadyRegisteredButton.setText("Go to login");
				}
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		
		
		JLabel nameLbl = new JLabel("Name:");
		
		JLabel surnameLbl = new JLabel("Surname:");
		
		JLabel emailLbl = new JLabel("Email:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(passLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(ageLabel)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(alreadyRegisteredButton)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
													.addGap(74))
												.addComponent(answerPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)))
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(passField, Alignment.LEADING)
												.addComponent(usernameField, Alignment.LEADING))
											.addGap(18)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(12)
													.addComponent(nameLbl)
													.addGap(18)
													.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(8)
													.addComponent(surnameLbl)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(8)
													.addComponent(emailLbl, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
									.addGap(96))
								.addComponent(titleLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
							.addGap(68))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(ageField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameLabel)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLbl))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(passLabel)
								.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ageLabel)
								.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(emailLbl)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(surnameLbl))))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(registerButton)
						.addComponent(alreadyRegisteredButton))
					.addGap(22)
					.addComponent(answerPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
