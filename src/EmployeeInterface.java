import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "deprecation", "serial" })
public class EmployeeInterface extends JFrame implements Observer {
	schoolModel sm;

	// Set<Year> yearSet;
	Set<Session> sessionSet;
	Set<Specialty> specialtySet;
	Set<Subject> subjectSet;
	Set<Exam> examSet;
	Set<Exercise> exerciseSet;
	Set<Student> studentSet;
	Set<Corrector> correctorSet;
	Set<Question> questionSet;
	ArrayList<Question> questionslist;

	JTabbedPane employeeTBP;
	JPanel p, rwp, p1, p2, p3, p4, p5, p6, p7, p8;
	JLabel yearLBL, year1LBL, year2LBL, sessionLBL, sessionTypeLBL, yearCMBLBL, specialtyLBL, specialtyNameLBL,
			specialtyCMBLBL, subjectLBL, subjectNameLBL, examLBL, examModalityLBL, examDateLBL, examCoefLBL,
			examSessionLBL, examSubjectLBL, exerciseExamLBL, exerciseLBL, exerciseNumLBL, userLBL, userFNLBL, userLNLBL,
			questionLBL, exerciseQuestionsLBL;
	JTextField year1TXT, year2TXT, sessionTXT, specialtyTXT, subjectTXT, examModalityTXT, examDateTXT, examCoefTXT,
			exerciseNumTXT, userFNTXT, userLNTXT;
	JButton readBTN, writeBTN, newYearBTN, saveYearBTN, newSessionBTN, saveSessionBTN, newSpecialtyBTN,
			saveSpecialtyBTN, newSubjectBTN, saveSubjectBTN, newExamBTN, saveExamBTN, newExerciseBTN, saveExerciseBTN,
			newUserBTN, saveUserBTN, clearRowsBTN, saveQuestionsBTN, addRowBTN;
	JRadioButton std, cor;
	ButtonGroup groupb;

	JList<Year> yearLST;
	JList<Session> sessionLST;
	JList<Specialty> specialtyLST;
	JList<Subject> subjectLST;
	JList<Exam> examLST;
	JList<Exercise> exerciseLST;
	JList<Student> studentLST;
	JList<Corrector> correctorLST;

	DefaultListModel<Year> yearLSTMDL;
	DefaultListModel<Session> sessionLSTMDL;
	DefaultListModel<Specialty> specialtyLSTMDL;
	DefaultListModel<Subject> subjectLSTMDL;
	DefaultListModel<Exam> examLSTMDL;
	DefaultListModel<Exercise> exerciseLSTMDL;
	DefaultListModel<Student> studentLSTMDL;
	DefaultListModel<Corrector> correctorLSTMDL;

	JComboBox<Year> yearCMB;
	JComboBox<Specialty> specialtyCMB;
	JComboBox<Session> sessionCMB;
	JComboBox<Subject> subjectCMB;
	JComboBox<Exam> examCMB;
	JComboBox<Exercise> exerciseCMB;

	DefaultComboBoxModel<Year> yearCMBMDL;
	DefaultComboBoxModel<Specialty> specialtyCMBMDL;
	DefaultComboBoxModel<Session> sessionCMBMDL;
	DefaultComboBoxModel<Subject> subjectCMBMDL;
	DefaultComboBoxModel<Exam> examCMBMDL;
	DefaultComboBoxModel<Exercise> exerciseCMBMDL;

	JTable questionsTBL;
	DefaultTableModel questionsTBLMDL;
	JScrollPane sp;

	Font font;
	String s, m, o;
	boolean isClicked;
	Year year;
	Session session;
	Specialty specialty;
	Subject subject;
	Exam exam;
	Exercise exercise;
	Student student;
	Corrector corrector;
	Question question;
	File file;

	@SuppressWarnings("deprecation")
	public EmployeeInterface(schoolModel m) {
		super("Employee Frame");
		setLocationRelativeTo(null);
		Container c = getContentPane();
		sm = m;
		sm.addObserver(this);

		isClicked = false;

		employeeTBP = new JTabbedPane();
		employeeTBP.setTabPlacement(JTabbedPane.TOP);
		rwp = new JPanel();
		rwp.setLayout(new FlowLayout());

		p = new JPanel();
		p.setLayout(new BorderLayout());

		p1 = new JPanel();
		p1.setLayout(null);
		p2 = new JPanel();
		p2.setLayout(null);
		p3 = new JPanel();
		p3.setLayout(null);
		p4 = new JPanel();
		p4.setLayout(null);
		p5 = new JPanel();
		p5.setLayout(null);
		p6 = new JPanel();
		p6.setLayout(null);
		p7 = new JPanel();
		p7.setLayout(null);
		p8 = new JPanel();
		p8.setLayout(null);

		readBTN = new JButton("read data");
		writeBTN = new JButton("write data");

		yearLBL = new JLabel("Academic Year");
		year1LBL = new JLabel("Year 1");
		year2LBL = new JLabel("Year 2");
		year1TXT = new JTextField();
		year2TXT = new JTextField();
		newYearBTN = new JButton("New");
		saveYearBTN = new JButton("Save");
		yearLSTMDL = new DefaultListModel<Year>();
		yearLST = new JList<Year>(yearLSTMDL);
		yearCMBMDL = new DefaultComboBoxModel<Year>();
		yearCMB = new JComboBox<Year>(yearCMBMDL);
		yearCMBLBL = new JLabel("Year");

		sessionLBL = new JLabel("Session");
		sessionTypeLBL = new JLabel("Type");
		sessionTXT = new JTextField();
		newSessionBTN = new JButton("New");
		saveSessionBTN = new JButton("Save");
		sessionLSTMDL = new DefaultListModel<Session>();
		sessionLST = new JList<Session>(sessionLSTMDL);
		sessionCMBMDL = new DefaultComboBoxModel<Session>();
		sessionCMB = new JComboBox<Session>();
		examSessionLBL = new JLabel("Session");

		specialtyLBL = new JLabel("Specialty");
		specialtyNameLBL = new JLabel("Name");
		specialtyTXT = new JTextField();
		newSpecialtyBTN = new JButton("New");
		saveSpecialtyBTN = new JButton("Save");
		specialtyLSTMDL = new DefaultListModel<Specialty>();
		specialtyLST = new JList<Specialty>(specialtyLSTMDL);
		specialtyCMBMDL = new DefaultComboBoxModel<Specialty>();
		specialtyCMB = new JComboBox<Specialty>(specialtyCMBMDL);
		specialtyCMBLBL = new JLabel("Specialty");

		subjectLBL = new JLabel("Subject");
		subjectNameLBL = new JLabel("Name");
		subjectTXT = new JTextField();
		newSubjectBTN = new JButton("New");
		saveSubjectBTN = new JButton("Save");
		subjectLSTMDL = new DefaultListModel<Subject>();
		subjectLST = new JList<Subject>(subjectLSTMDL);
		subjectCMBMDL = new DefaultComboBoxModel<Subject>();
		subjectCMB = new JComboBox<Subject>();
		examSubjectLBL = new JLabel("Subject");

		// examLBL, examModalityLBL, examDateLBL, examCoefLBL
		examLBL = new JLabel("Exam");
		examModalityLBL = new JLabel("Exam Modality");
		examDateLBL = new JLabel("Exam Date");
		examCoefLBL = new JLabel("Exam Coef");
		examModalityTXT = new JTextField();
		examDateTXT = new JTextField();
		examCoefTXT = new JTextField();
		newExamBTN = new JButton("New");
		saveExamBTN = new JButton("Save");
		examLSTMDL = new DefaultListModel<Exam>();
		examLST = new JList<Exam>(examLSTMDL);
		exerciseExamLBL = new JLabel("Exam");
		examCMBMDL = new DefaultComboBoxModel<Exam>();
		examCMB = new JComboBox<Exam>(examCMBMDL);

		exerciseLBL = new JLabel("Exercise");
		exerciseNumLBL = new JLabel("Number");
		exerciseNumTXT = new JTextField();
		newExerciseBTN = new JButton("New");
		saveExerciseBTN = new JButton("Save");
		exerciseLSTMDL = new DefaultListModel<Exercise>();
		exerciseLST = new JList<Exercise>(exerciseLSTMDL);

		userLBL = new JLabel("User");
		userFNLBL = new JLabel("First Name");
		userLNLBL = new JLabel("Last Name");
		userFNTXT = new JTextField();
		userLNTXT = new JTextField();
		std = new JRadioButton("student");
		cor = new JRadioButton("corrector");
		groupb = new ButtonGroup();
		groupb.add(std);
		groupb.add(cor);
		newUserBTN = new JButton("New");
		saveUserBTN = new JButton("Save");
		studentLSTMDL = new DefaultListModel<Student>();
		studentLST = new JList<Student>(studentLSTMDL);
		correctorLSTMDL = new DefaultListModel<Corrector>();
		correctorLST = new JList<Corrector>(correctorLSTMDL);

		questionLBL = new JLabel("Questions");
		exerciseQuestionsLBL = new JLabel("exercise");
		exerciseCMBMDL = new DefaultComboBoxModel<Exercise>();
		exerciseCMB = new JComboBox<Exercise>(exerciseCMBMDL);
		questionsTBLMDL = new DefaultTableModel();
		questionsTBLMDL.addColumn("Number");
		questionsTBLMDL.addColumn("Content");
		questionsTBLMDL.addColumn("Scale-Note");
		questionsTBL = new JTable(questionsTBLMDL);
		clearRowsBTN = new JButton("clear");
		saveQuestionsBTN = new JButton("Save");
		addRowBTN = new JButton("add row");
		JScrollPane sp = new JScrollPane(questionsTBL);

		questionsTBL.setCellSelectionEnabled(true);
		ListSelectionModel tableSelectionModel = questionsTBL.getSelectionModel();
		tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// title's font
		Font font = new Font("Serif", Font.BOLD + Font.ITALIC, 25);

		yearLBL.setFont(font);
		sessionLBL.setFont(font);
		specialtyLBL.setFont(font);
		subjectLBL.setFont(font);
		examLBL.setFont(font);
		exerciseLBL.setFont(font);
		userLBL.setFont(font);
		questionLBL.setFont(font);

		readBTN.addActionListener(new Actions());
		writeBTN.addActionListener(new Actions());
		newYearBTN.addActionListener(new Actions());
		saveYearBTN.addActionListener(new Actions());
		newSessionBTN.addActionListener(new Actions());
		saveSessionBTN.addActionListener(new Actions());
		newSpecialtyBTN.addActionListener(new Actions());
		saveSpecialtyBTN.addActionListener(new Actions());
		newSubjectBTN.addActionListener(new Actions());
		saveSubjectBTN.addActionListener(new Actions());
		newExamBTN.addActionListener(new Actions());
		saveExamBTN.addActionListener(new Actions());
		newExerciseBTN.addActionListener(new Actions());
		saveExerciseBTN.addActionListener(new Actions());
		newUserBTN.addActionListener(new Actions());
		saveUserBTN.addActionListener(new Actions());
		clearRowsBTN.addActionListener(new Actions());
		saveQuestionsBTN.addActionListener(new Actions());
		addRowBTN.addActionListener(new Actions());
		clearRowsBTN.addActionListener(new Actions());

		exerciseCMB.addItemListener(new ItemActions());
//		questionsTBLMDL.addTableModelListener(new TableModelListener() {
//			
//		  public void tableChanged(TableModelEvent e) {
//			  	if(e.getSource() == addRowBTN) {return;}
//				validateTableData();
//				
//			}
//		});

		p1.add(yearLBL).setBounds(100, 30, 160, 25);
		p1.add(year1LBL).setBounds(40, 85, 100, 25);
		p1.add(year2LBL).setBounds(40, 120, 100, 25);
		p1.add(year1TXT).setBounds(150, 85, 160, 25);
		p1.add(year2TXT).setBounds(150, 120, 160, 25);
		p1.add(newYearBTN).setBounds(40, 175, 70, 30);
		p1.add(saveYearBTN).setBounds(135, 175, 70, 30);
		p1.add(yearLST).setBounds(450, 35, 300, 240);

		p2.add(sessionLBL).setBounds(100, 30, 160, 25);
		p2.add(yearCMBLBL).setBounds(40, 85, 100, 25);
		p2.add(sessionTypeLBL).setBounds(40, 120, 100, 25);
		p2.add(yearCMB).setBounds(150, 85, 160, 25);
		p2.add(sessionTXT).setBounds(150, 120, 160, 25);
		p2.add(newSessionBTN).setBounds(40, 175, 70, 30);
		p2.add(saveSessionBTN).setBounds(135, 175, 70, 30);
		p2.add(sessionLST).setBounds(450, 35, 300, 240);

		p3.add(specialtyLBL).setBounds(100, 30, 160, 30);
		p3.add(specialtyNameLBL).setBounds(40, 85, 100, 25);
		p3.add(specialtyTXT).setBounds(150, 85, 160, 25);
		p3.add(newSpecialtyBTN).setBounds(40, 175, 70, 30);
		p3.add(saveSpecialtyBTN).setBounds(135, 175, 70, 30);
		p3.add(specialtyLST).setBounds(450, 35, 300, 240);

		p4.add(subjectLBL).setBounds(100, 30, 160, 25);
		p4.add(specialtyCMBLBL).setBounds(40, 85, 100, 25);
		p4.add(subjectNameLBL).setBounds(40, 120, 100, 25);
		p4.add(specialtyCMB).setBounds(150, 85, 160, 25);
		p4.add(subjectTXT).setBounds(150, 120, 160, 25);
		p4.add(newSubjectBTN).setBounds(40, 175, 70, 30);
		p4.add(saveSubjectBTN).setBounds(135, 175, 70, 30);
		p4.add(subjectLST).setBounds(450, 35, 300, 240);

		p5.add(examLBL).setBounds(100, 30, 160, 25);
		p5.add(examSessionLBL).setBounds(40, 70, 100, 25);
		p5.add(examSubjectLBL).setBounds(40, 105, 100, 25);
		p5.add(examModalityLBL).setBounds(40, 140, 100, 25);
		p5.add(examDateLBL).setBounds(40, 175, 100, 25);
		p5.add(examCoefLBL).setBounds(40, 210, 100, 25);
		p5.add(sessionCMB).setBounds(150, 70, 160, 25);
		p5.add(subjectCMB).setBounds(150, 105, 160, 25);
		p5.add(examModalityTXT).setBounds(150, 140, 160, 25);
		p5.add(examDateTXT).setBounds(150, 175, 160, 25);
		p5.add(examCoefTXT).setBounds(150, 210, 160, 25);
		p5.add(newExamBTN).setBounds(40, 255, 70, 30);
		p5.add(saveExamBTN).setBounds(135, 255, 70, 30);
		p5.add(examLST).setBounds(450, 35, 300, 240);

		p6.add(exerciseLBL).setBounds(100, 30, 160, 25);
		p6.add(exerciseExamLBL).setBounds(40, 85, 100, 25);
		p6.add(exerciseNumLBL).setBounds(40, 120, 100, 25);
		p6.add(examCMB).setBounds(150, 85, 160, 25);
		p6.add(exerciseNumTXT).setBounds(150, 120, 160, 25);
		p6.add(newExerciseBTN).setBounds(40, 175, 70, 30);
		p6.add(saveExerciseBTN).setBounds(135, 175, 70, 30);
		p6.add(exerciseLST).setBounds(450, 35, 300, 240);

		p7.add(userLBL).setBounds(100, 30, 160, 25);
		p7.add(userFNLBL).setBounds(40, 85, 100, 25);
		p7.add(userLNLBL).setBounds(40, 120, 100, 25);
		p7.add(userFNTXT).setBounds(150, 85, 160, 25);
		p7.add(userLNTXT).setBounds(150, 120, 160, 25);
		p7.add(std).setBounds(40, 155, 100, 25);
		p7.add(cor).setBounds(150, 155, 100, 25);
		p7.add(newUserBTN).setBounds(40, 190, 70, 30);
		p7.add(saveUserBTN).setBounds(135, 190, 70, 30);
		p7.add(studentLST).setBounds(350, 35, 170, 240);
		p7.add(correctorLST).setBounds(570, 35, 170, 240);

		p8.add(questionLBL).setBounds(100, 25, 160, 30);
		p8.add(exerciseQuestionsLBL).setBounds(250, 30, 100, 25);
		p8.add(exerciseCMB).setBounds(320, 30, 160, 25);
		p8.add(sp).setBounds(40, 85, 700, 200);
		p8.add(addRowBTN).setBounds(500, 30, 100, 25);
		p8.add(clearRowsBTN).setBounds(620, 30, 65, 25);
		p8.add(saveQuestionsBTN).setBounds(710, 30, 65, 25);

		// --- rendering to display the text i want in combo box
		specialtyCMB.setRenderer(new ComboBoxRenderer());
		examCMB.setRenderer(new ComboBoxRenderer());
		exerciseCMB.setRenderer(new ComboBoxRenderer());

		employeeTBP.addTab("Year", p1);
		employeeTBP.add("Session", p2);
		employeeTBP.add("Specialty", p3);
		employeeTBP.add("Subject", p4);
		employeeTBP.add("Exam", p5);
		employeeTBP.add("Exercise", p6);
		employeeTBP.add("User", p7);
		employeeTBP.add("Question", p8);

		add(employeeTBP, BorderLayout.CENTER);

		rwp.add(readBTN, FlowLayout.LEFT);
		rwp.add(writeBTN, FlowLayout.LEFT);
		p.add(rwp, BorderLayout.EAST);

		c.add(p, BorderLayout.NORTH);
		c.add(employeeTBP);

		questionslist = new ArrayList<Question>();

	}

	private class Actions implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			try {

				// --- WRITE button
				if (source == writeBTN) {
					/*
					 * try{ file = new File("school.dat"); if(!file.exists()) file.createNewFile();
					 * FileOutputStream os = new FileOutputStream(file); ObjectOutputStream oos =
					 * new ObjectOutputStream(os); oos.writeObject(sm); oos.flush(); oos.close(); }
					 * catch(IOException ioe) { }
					 */
					try {
						if (isClicked == false) {
							throw new NoValidAction("error, please read data first");
						}
						// --- year file
						file = new File("years.dat");
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.years);
						oos.flush();
						oos.close();

						// --- session file
						file = new File("sessions.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.sessions);
						oos.flush();
						oos.close();

						// --- session file
						file = new File("specialties.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.specialties);
						oos.flush();
						oos.close();

						// --- subject file
						file = new File("subjects.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.subjects);
						oos.flush();
						oos.close();

						// --- exam file
						file = new File("exams.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.exams);
						oos.flush();
						oos.close();

						// --- exercise file
						file = new File("exercises.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.exercises);
						oos.flush();
						oos.close();

						// --- student file
						file = new File("students.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.students);
						oos.flush();
						oos.close();

						// --- corrector file
						file = new File("correctors.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.correctors);
						oos.flush();
						oos.close();

						// --- question file
						file = new File("questions.dat");
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(sm.questions);
						oos.flush();
						oos.close();
					} catch (IOException ioe) {
					}

				}
				// --- READ button
				else if (source == readBTN) {
					try {
						/*
						 * file = new File("school.dat"); FileInputStream is = new
						 * FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is); sm
						 * = (schoolModel) ois.readObject(); ois.close(); populateYears();
						 * populateStudents(); populateSessions(); populateCorrectors();
						 * populateSpecialties(); populateSubjects(); populateExams();
						 * populateExercises(); populateQuestions();
						 * 
						 * } catch(ClassCastException cce) {
						 * JOptionPane.showMessageDialog(null,"Cast Error!");}
						 * catch(ClassNotFoundException cnfe)
						 * {JOptionPane.showMessageDialog(null,"Class not found error!"); }
						 * catch(IOException ioe) {
						 * JOptionPane.showMessageDialog(null,ioe.getMessage());}
						 */

						isClicked = true;
						File dir = new File("d:/programs");
						// dir.mkdir();
						// --- year file
						file = new File("years.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.years = (Set<Year>) ois.readObject();
							populateYears();
							ois.close();
						}
						// --- session file
						file = new File("sessions.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.sessions = (Set<Session>) ois.readObject();
							populateSessions();
							ois.close();
						}
						// --- specialties file
						file = new File("specialties.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.specialties = (Set<Specialty>) ois.readObject();
							populateSpecialties();
							ois.close();
						}
						// --- subjects file
						file = new File("subjects.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.subjects = (Set<Subject>) ois.readObject();
							populateSubjects();
							ois.close();
						}
						// --- exams file
						file = new File("exams.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.exams = (Set<Exam>) ois.readObject();
							System.out.println(examSet);
							populateExams();
							ois.close();
						}

						// --- exercises file
						file = new File("exercises.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.exercises = (ArrayList<Exercise>) ois.readObject();
							populateExercises();
							ois.close();
						}

						// --- students file
						file = new File("students.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.students = (Set<Student>) ois.readObject();
							populateStudents();
							ois.close();
						}

						// --- correctors file
						file = new File("correctors.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.correctors = (Set<Corrector>) ois.readObject();
							populateCorrectors();
							ois.close();
						}

						// --- questions file
						file = new File("questions.dat");
						if (!file.exists())
							file.createNewFile();
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							sm.questions = (ArrayList<Question>) ois.readObject();
							populateQuestions();
							ois.close();
						}

					} catch (ClassCastException cce) {
						JOptionPane.showMessageDialog(null, "Cast Error!");
					} catch (ClassNotFoundException cnfe) {
						JOptionPane.showMessageDialog(null, "Class not found error!");
					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(null, ioe.getMessage());
					}
				}

				// --- save year button
				else if (source == saveYearBTN) {
					if (year1TXT.getText().isEmpty() || year2TXT.getText().isEmpty()) {
						throw new NoValidAction("error, no year selected or year missing");
					}
					year = new Year(year1TXT.getText(), year2TXT.getText());
					sm.addYear(year);
					// populateYears();
					// else {JOptionPane.showMessageDialog(null,"year already
					// exists","error",JOptionPane.ERROR_MESSAGE);}
				}

				// --- new year button
				else if (source == newYearBTN) {
					year1TXT.setText("");
					year2TXT.setText("");
				}
				// --- save session button
				else if (source == saveSessionBTN) {
					if (sessionTXT.getText().isEmpty() || yearCMB.getSelectedIndex() == -1) {
						throw new NoValidAction("error, no year selected or session type missing");
					}
					year = (Year) yearCMB.getSelectedItem();
					s = sessionTXT.getText();
					session = new Session(s, year);
					if (sm.addSession(session))
						return; // {sessionLSTMDL.addElement(session); sm.addSession(session);}
					else {
						JOptionPane.showMessageDialog(null, "session already exists", "error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				// --- new session button
				else if (source == newSessionBTN) {
					sessionTXT.setText("");
					yearCMB.setSelectedIndex(-1);
				}

				// --- save SPECIALTY button
				else if (source == saveSpecialtyBTN) {
					if (specialtyTXT.getText().isEmpty()) {
						throw new NoValidAction("error, specialty name missing");
					}
					s = specialtyTXT.getText();
					specialty = new Specialty(s);
					if (sm.addSpeciality(specialty))
						return; // specialtyLSTMDL.addElement(specialty);
					else {
						JOptionPane.showMessageDialog(null, "specialty already exists", "error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				// --- new SPECIALTY button
				else if (source == newSpecialtyBTN) {
					specialtyTXT.setText("");

				}

				// --- save SUBJECT button
				else if (source == saveSubjectBTN) {
					if (subjectTXT.getText().isEmpty() || specialtyCMB.getSelectedIndex() == -1) {
						throw new NoValidAction("error, no specialty selected or subject name missing");
					}
					specialty = (Specialty) specialtyCMB.getSelectedItem();
					s = subjectTXT.getText();
					subject = new Subject(s);
					if (sm.addSubject(subject))
						specialty.addSubject(subject); // {subjectLSTMDL.addElement(subject);
														// specialty.addSubject(subject);}
					else {
						JOptionPane.showMessageDialog(null, "subject already exists", "error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				// --- new SUBEJCT button
				else if (source == newSubjectBTN) {
					subjectTXT.setText("");

				}

				// --- save Exam button
				else if (source == saveExamBTN) {
					if (examModalityTXT.getText().isEmpty() || examDateTXT.getText().isEmpty()
							|| examCoefTXT.getText().isEmpty() || sessionCMB.getSelectedIndex() == -1
							|| subjectCMB.getSelectedIndex() == -1) {
						throw new NoValidAction("error, one or more required records are missing");
					}
					session = (Session) sessionCMB.getSelectedItem();
					subject = (Subject) subjectCMB.getSelectedItem();
					s = examCoefTXT.getText();
					m = examModalityTXT.getText();
					o = examDateTXT.getText();
					exam = new Exam(o, m, Integer.parseInt(s), session, subject);
					if (sm.addExam(exam))
						return; // {examLSTMDL.addElement(exam); examCMBMDL.addElement(exam);}
					else {
						JOptionPane.showMessageDialog(null, "exam already exists", "error", JOptionPane.ERROR_MESSAGE);
					}
				}

				// --- new Exam button
				else if (source == newExamBTN) {
					examModalityTXT.setText("");
					examDateTXT.setText("");
					examCoefTXT.setText("");
					sessionCMB.setSelectedIndex(-1);
					subjectCMB.setSelectedIndex(-1);

				}
				// --- save EXERCISE button
				else if (source == saveExerciseBTN) {
					if (exerciseNumTXT.getText().isEmpty() || examCMB.getSelectedIndex() == -1) {
						throw new NoValidAction("error, no exam selected or exercise number missing");
					}
					exam = (Exam) examCMB.getSelectedItem();
					s = exerciseNumTXT.getText();
					exercise = new Exercise(Integer.parseInt(s), exam);
					if (sm.addExercise(exercise)) {
						exam.addExercise(exercise);
					} // {exerciseLSTMDL.addElement(exercise); exam.addExercise(exercise);}
					else {
						JOptionPane.showMessageDialog(null, "exercise already exists", "error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				// --- new EXERCISE button
				else if (source == newExerciseBTN) {
					exerciseNumTXT.setText("");
					examCMB.setSelectedIndex(-1);

				}

				// --- save USER button
				else if (source == saveUserBTN) {
					if (userFNTXT.getText().isEmpty() || userLNTXT.getText().isEmpty()
							|| (std.isSelected() == false && cor.isSelected() == false)) {
						throw new NoValidAction("error, no role selected or name missing");
					}
					m = userFNTXT.getText();
					s = userLNTXT.getText();
					if (std.isSelected()) {
						student = new Student(m, s);
						if (sm.addStudent(student)) {
							// studentLSTMDL.addElement(student);
						} else {
							JOptionPane.showMessageDialog(null, "student already exists", "error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						corrector = new Corrector(m, s);
						if (sm.addCorrector(corrector))
							return;
						// correctorLSTMDL.addElement(corrector);
						else {
							JOptionPane.showMessageDialog(null, "corrector already exists", "error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
				// --- new EXERCISE button
				else if (source == newUserBTN) {
					userFNTXT.setText("");
					userLNTXT.setText("");
				}

				// --- add row button
				else if (source == addRowBTN) {
					questionsTBLMDL.addRow(new Object[] { "", "", "" });
				}

				// --- clear rows
				else if (source == clearRowsBTN) {
					questionsTBLMDL.setRowCount(0);
				}

				// --- save rows
				else if (source == saveQuestionsBTN) {
					if (exerciseCMB.getSelectedIndex() == -1) {
						throw new NoValidAction("error, no exercise selected ");
					}
					exercise = (Exercise) exerciseCMBMDL.getSelectedItem();
					System.out.println(exercise);

					// --- check for empty fields first
					if (!validCheck())
						JOptionPane.showMessageDialog(null, "Field is empty");
					else {
						int n = 0;
						double sc = 0;
						for (int i = 0; i < questionsTBL.getRowCount(); i++) {
							System.out.println("row count" + questionsTBL.getRowCount());
							for (int j = 0; j < questionsTBL.getColumnCount(); j++) {
								String value = questionsTBL.getValueAt(i, j).toString();

								if (j == 0) {
									n = Integer.parseInt(value);
								} else if (j == 1) {
									m = value;
								} else if (j == 2) {
									sc = Double.parseDouble(value);
								}

							}
							question = new Question(n, m, sc);
							System.out.println(exercise);
							try {

								exercise.addQuestion(question);
								questionslist.add(question);

							} catch (NullPointerException npe) {
								System.out.println(npe.getMessage());
							}

						}
						sm.setQuestions(questionslist);
					}

				}

			} catch (NoValidAction ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			} catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null, "field must be numeric");
			}
		}

	}

	public void update(Observable obs, Object obj) {

		if (obj instanceof Year)
			populateYears();
		else if (obj instanceof Session)
			populateSessions();
		else if (obj instanceof Specialty)
			populateSpecialties();
		else if (obj instanceof Subject)
			populateSubjects();
		else if (obj instanceof Exam)
			populateExams();
		else if (obj instanceof Exercise)
			populateExercises();
		else if (obj instanceof List)
			populateQuestions();
		else if (obj instanceof Student)
			populateStudents();
		else
			populateCorrectors();

	}

	private class ItemActions implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			exercise = (Exercise) exerciseCMB.getSelectedItem();
			if (exercise == null)
				return;
			questionsTBLMDL.setRowCount(0);
			Iterator<Question> it = (exercise.questionsSet).iterator();
			while (it.hasNext()) {
				question = it.next();
				Object[] rowData = { question.getNum(), question.getContent(), question.getScaleScore() };
				questionsTBLMDL.addRow(rowData);
			}
			questionsTBL.setModel(questionsTBLMDL);

		}

	}

	// --- POPULATE
	public void populateYears() {

		yearLSTMDL.clear();
		yearCMBMDL.removeAllElements();
		yearCMBMDL.addElement(null);
		// Iterator<Year> it = yearSet.iterator();
		Iterator<Year> it = sm.years.iterator();
		while (it.hasNext()) {
			year = it.next();
			yearLSTMDL.addElement(year);
			yearCMBMDL.addElement(year);
		}
		yearLST.setModel(yearLSTMDL);
		yearCMB.setModel(yearCMBMDL);
	}

	public void populateSessions() {
		sessionLSTMDL.clear();
		sessionCMBMDL.removeAllElements();
		sessionCMBMDL.addElement(null);
		// Iterator<Session> it = sessionSet.iterator();
		Iterator<Session> it = sm.sessions.iterator();
		while (it.hasNext()) {
			session = it.next();
			sessionLSTMDL.addElement(session);
			sessionCMBMDL.addElement(session);
		}
		sessionLST.setModel(sessionLSTMDL);
		sessionCMB.setModel(sessionCMBMDL);
	}

	public void populateSpecialties() {
		specialtyLSTMDL.clear();
		specialtyCMBMDL.removeAllElements();
		specialtyCMBMDL.addElement(null);
		// Iterator<Specialty> it = specialtySet.iterator();
		Iterator<Specialty> it = sm.specialties.iterator();
		while (it.hasNext()) {
			specialty = it.next();
			specialtyLSTMDL.addElement(specialty);
			specialtyCMBMDL.addElement(specialty);

		}
		specialtyLST.setModel(specialtyLSTMDL);
		specialtyCMB.setModel(specialtyCMBMDL);
	}

	public void populateSubjects() {
		subjectLSTMDL.clear();
		subjectCMBMDL.removeAllElements();
		subjectCMBMDL.addElement(null);
		Iterator<Subject> it = sm.subjects.iterator();
		while (it.hasNext()) {
			subject = it.next();
			subjectLSTMDL.addElement(subject);
			subjectCMBMDL.addElement(subject);
		}
		subjectLST.setModel(subjectLSTMDL);
		subjectCMB.setModel(subjectCMBMDL);
	}

	public void populateExams() {
		examLSTMDL.clear();
		examCMBMDL.removeAllElements();
		examCMBMDL.addElement(null);
		Iterator<Exam> it = sm.exams.iterator();
		while (it.hasNext()) {
			exam = it.next();
			examLSTMDL.addElement(exam);
			examCMBMDL.addElement(exam);
		}
		examLST.setModel(examLSTMDL);
		examCMB.setModel(examCMBMDL);
	}

	public void populateExercises() {
		exerciseLSTMDL.clear();
		exerciseCMBMDL.removeAllElements();
		exerciseCMBMDL.addElement(null);
		Iterator<Exercise> it = sm.exercises.iterator();
		while (it.hasNext()) {
			exercise = it.next();
			exerciseLSTMDL.addElement(exercise);
			exerciseCMBMDL.addElement(exercise);
		}
		exerciseLST.setModel(exerciseLSTMDL);
		exerciseCMB.setModel(exerciseCMBMDL);
	}

	public void populateStudents() {
		studentLSTMDL.clear();
		Iterator<Student> it = sm.students.iterator();
		while (it.hasNext()) {
			student = it.next();
			studentLSTMDL.addElement(student);
			System.out.println(student);
		}
		studentLST.setModel(studentLSTMDL);
	}

	public void populateCorrectors() {
		correctorLSTMDL.clear();
		Iterator<Corrector> it = sm.correctors.iterator();
		while (it.hasNext()) {
			corrector = it.next();
			correctorLSTMDL.addElement(corrector);
		}
		correctorLST.setModel(correctorLSTMDL);
	}

	public void populateQuestions() {

		questionsTBLMDL.setRowCount(0);
		Iterator<Question> it = sm.questions.iterator();
		while (it.hasNext()) {
			question = it.next();
			Object[] rowData = { question.getNum(), question.getContent(), question.getScaleScore() };
			questionsTBLMDL.addRow(rowData);

		}
		correctorLST.setModel(correctorLSTMDL);
	}

//	public boolean validateTableData() {
//		//try {
//		int rowCount = questionsTBLMDL.getRowCount();
//	     int columnCount = questionsTBLMDL.getColumnCount();
//	     //System.out.println(rowCount +" "+columnCount);
//	     //m = (String) questionsTBLMDL.getValueAt(, );
//	     //System.out.println(m);
//	     for (int row = 0; row < rowCount; row++) {
//	            for (int col = 0; col < columnCount; col++) {
//	                String cellData = (String)questionsTBLMDL.getValueAt(row, col);
//
//	                // Perform validation for each column
//	                if (col == 0) {
//	                    // Validate the question text
//	                    String questionNumber = (String) cellData;
//	                    
//	                    if (questionNumber.isEmpty()) {
//	                    	return false;
//	                    	//throw new NoValidAction("error, "+row+"-"+col+"  question number missing");
//	                        
//	                    }
//	                } else if (col == 1) {
//	                    
//	                    String content = (String) cellData;
//	                    if (content.isEmpty()) {return false;
//	                    	//throw new NoValidAction("error, "+row+"-"+col+"  question content missing");
//	                        
//	                    }
//	                } else if (col == 2) {
//	                    String score = (String) cellData;
//	                    if (score.isEmpty()) {return false;
//	                    	//throw new NoValidAction("error, "+row+"-"+col+" question score missing");
//		                 }
//	                }
//	                
//	            }
//	        }
//	     return true;
//		//}
////		catch(NoValidAction ex) {
////			JOptionPane.showMessageDialog(null, ex.getMessage());
////		}
//	}
	public boolean validCheck() {
		if (questionsTBL.getCellEditor() != null) {
			questionsTBL.getCellEditor().stopCellEditing();
		}
		for (int i = 0; i < questionsTBL.getRowCount(); i++) {
			System.out.println("valid row count:" + questionsTBL.getRowCount());
			for (int j = 0; j < questionsTBL.getColumnCount(); j++) {
				String value = questionsTBL.getValueAt(i, j).toString();
				if (value.trim().length() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private class NoValidAction extends Exception {
		public NoValidAction(String msg) {
			super(msg);
		}
	}

	static class ComboBoxRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Call the parent renderer to get the default rendering
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value instanceof Specialty) {
				Specialty specialty = (Specialty) value;
				// Set the display text to the name of the Specialty
				setText(specialty.getName());
			}
			if (value instanceof Exam) {
				Exam exam = (Exam) value;
				// Set the display text to the name of the Specialty
				setText(exam.getRender());
			}
			if (value instanceof Exercise) {
				Exercise exercise = (Exercise) value;
				// Set the display text to the name of the Specialty
				setText(exercise.getRender());
			}

			return component;
		}
	}

}
