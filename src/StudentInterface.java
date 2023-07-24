import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("deprecation")
public class StudentInterface extends JFrame implements Observer {
	schoolModel sm;

	JLabel studentNameLBL, examLBL, exerciseLBL;
	JPanel p, p1, p2, p3;
	JButton readBTN, saveBTN;

	JComboBox<Student> studentCMB;
	JComboBox<Exam> examCMB;
	JComboBox<Exercise> exerciseCMB;

	DefaultComboBoxModel<Student> studentCMBMDL;
	DefaultComboBoxModel<Exam> examCMBMDL;
	DefaultComboBoxModel<Exercise> exerciseCMBMDL;

	JTable questionsTBL;
	DefaultTableModel questionsTBLMDL;
	JScrollPane sp;

	File file;
	Student student;
	Exam exam;
	Exercise exercise;
	Question question;
	StudentAnswer answer, tempans;

	Object value;

	Set<StudentAnswer> studentanswers;
	double sc;
	String m, s;

	int n;
	boolean isClicked;

	@SuppressWarnings("deprecation")
	StudentInterface(schoolModel model) {
		super("Student Frame");
		setLocationRelativeTo(null);
		Container c = getContentPane();
		sm = model;
		sm.addObserver(this);

		isClicked = false;
		studentanswers = new TreeSet<StudentAnswer>();

		c.setLayout(new BorderLayout());
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3 = new JPanel();
		p2.setLayout(new BorderLayout());

		readBTN = new JButton("read");
		saveBTN = new JButton("save");

		studentNameLBL = new JLabel("Student Name");
		studentCMBMDL = new DefaultComboBoxModel<Student>();
		studentCMB = new JComboBox<Student>(studentCMBMDL);

		examLBL = new JLabel("Exam");
		examCMBMDL = new DefaultComboBoxModel<Exam>();
		examCMB = new JComboBox<Exam>(examCMBMDL);

		exerciseLBL = new JLabel("Exercise");
		exerciseCMBMDL = new DefaultComboBoxModel<Exercise>();
		exerciseCMB = new JComboBox<Exercise>(exerciseCMBMDL);

		questionsTBLMDL = new DefaultTableModel();
		questionsTBLMDL.addColumn("Number");
		questionsTBLMDL.addColumn("Content");
		questionsTBLMDL.addColumn("Scale-Score");
		questionsTBLMDL.addColumn("Your Answer");
		questionsTBL = new JTable(questionsTBLMDL);
		JScrollPane sp = new JScrollPane(questionsTBL);

		studentCMB.setPreferredSize(new Dimension(120, 25));
		examCMB.setPreferredSize(new Dimension(120, 25));
		exerciseCMB.setPreferredSize(new Dimension(120, 25));
		readBTN.setPreferredSize(new Dimension(70, 30));
		saveBTN.setPreferredSize(new Dimension(70, 30));
		sp.setPreferredSize(new Dimension(600, 200));

		// studentCMB.addItemListener(new ItemActions());
		readBTN.addActionListener(new Actions());
		saveBTN.addActionListener(new Actions());

		examCMB.addItemListener(new ItemActions());
		exerciseCMB.addItemListener(new ItemActions());

		// --- rendering to display the text i want in combo box
		studentCMB.setRenderer(new ComboBoxRenderer());
		examCMB.setRenderer(new ComboBoxRenderer());
		exerciseCMB.setRenderer(new ComboBoxRenderer());

		questionsTBLMDL.addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
				System.out.println("column = " + e.getColumn() + " ");

			}

		});

		p.add(readBTN);
		p.add(saveBTN);

		p1.add(studentNameLBL);
		p1.add(studentCMB);
		p1.add(examLBL);
		p1.add(examCMB);
		p1.add(exerciseLBL);
		p1.add(exerciseCMB);

		p2.add(p, BorderLayout.NORTH);
		p2.add(p1, BorderLayout.WEST);

		p3.add(sp, BorderLayout.SOUTH);

		c.add(p2, BorderLayout.NORTH);
		c.add(p3, BorderLayout.SOUTH);

	}

//	private class ItemActions implements ItemListener{
//
//		public void itemStateChanged(ItemEvent e) {
//			Object source = e.getSource();
//			try {
//				if(source == studentCMB) {
//					
//				}
//			}
//			catch{}
//		}
//		}
	private class Actions implements ActionListener {

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == readBTN) {
				try {
					if (isClicked == true) {
						throw new NoValidAction("error, save button is enabled cannot read");
					}
					/*
					 * file = new File("school.dat"); FileInputStream is = new
					 * FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is); sm
					 * = (schoolModel) ois.readObject(); ois.close();
					 * 
					 * populateStudents(); populateExams();
					 */
					file = new File("students.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.students = (Set<Student>) ois.readObject();
						populateStudents();
						ois.close();
					}
					file = new File("exams.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.exams = (Set<Exam>) ois.readObject();
						populateExams();
						ois.close();
					}
					file = new File("exercises.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.exercises = (List<Exercise>) ois.readObject();
						populateExercises();
						ois.close();
					}

					file = new File("students-answers.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.studentanswers = (Set<StudentAnswer>) ois.readObject();

						ois.close();
					}

				} catch (ClassCastException cce) {
					JOptionPane.showMessageDialog(null, "Cast Error!");
				} catch (ClassNotFoundException cnfe) {
					JOptionPane.showMessageDialog(null, "Class not found error!");
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, ioe.getMessage());
				} catch (NoValidAction ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "null pointer exception!");
				}
			}
			if (source == saveBTN) {
				try {
					isClicked = true;
					// questionsTBL.revalidate();

					student = (Student) studentCMB.getSelectedItem();
					if (student == null) {
						throw new NoValidAction("error, no student selected");
					}

					if (!validCheck()) {
						JOptionPane.showMessageDialog(null, "Field is empty");
					} else {
						s = "";
						sc = 0;
						n = 0;
						m = "";
						for (int i = 0; i < questionsTBL.getRowCount(); i++) {
							// System.out.println("rows "+questionsTBL.getRowCount());
							for (int j = 0; j < questionsTBL.getColumnCount(); j++) {
								// System.out.println("columns "+questionsTBL.getColumnCount());
								if (questionsTBL.getValueAt(i, j) == null)
									continue;
								String value = questionsTBL.getValueAt(i, j).toString();

								// String value = questionsTBL.getValueAt(i,j).toString();
								if (j == 0) {
									n = Integer.parseInt(value); // number
								} else if (j == 1) {
									m = (String) value; // content
								} else if (j == 2) {
									sc = Double.parseDouble(value); // score
								} else {
									s = (String) value; // answer
								}

							}
							question = new Question(n, m, sc);
							answer = new StudentAnswer(s, student, question);
							studentanswers.add(answer);
							sm.addStudentAnswer(answer);
						}
						// sm.setStudentAnswer(studentanswers);

						file = new File("students-answers.dat");
						if (!file.exists())
							file.createNewFile();
						FileOutputStream os = new FileOutputStream(file);
						ObjectOutputStream oos = new ObjectOutputStream(os);
						oos.writeObject(sm.studentanswers);
						oos.flush();
						oos.close();
					}
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, ioe.getMessage());
				} catch (NoValidAction ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		}
	}

	private class ItemActions implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			Object source = e.getSource();
			if (source == examCMB) {
				exam = (Exam) examCMB.getSelectedItem();
				if (exam == null)
					return;
				exerciseCMBMDL.removeAllElements();
				exerciseCMBMDL.addElement(null);
				Iterator<Exercise> it = exam.exercisesSet.iterator();
				while (it.hasNext()) {
					exercise = it.next();
					exerciseCMBMDL.addElement(exercise);
				}
				exerciseCMB.setModel(exerciseCMBMDL);
			}

			if (source == exerciseCMB) {
				int newExerciseCount = exerciseCMB.getItemCount();
				if (newExerciseCount > 0) {
					try {
						exercise = (Exercise) exerciseCMB.getSelectedItem();
						student = (Student) studentCMB.getSelectedItem();

						// if(exercise == null)JOptionPane.showMessageDialog(null, "please pick
						// exercise");
						// if(student == null)JOptionPane.showMessageDialog(null, "please pick
						// student");
						// if(exercise.questionsSet == null )return;
						questionsTBLMDL.setRowCount(0);
						// System.out.println(exercise.questionsSet);
						m = "";
						Iterator<Question> it = exercise.questionsSet.iterator();
						while (it.hasNext()) {
							question = it.next();
							Iterator<StudentAnswer> i = sm.studentanswers.iterator();
							System.out.println(sm.studentanswers);
							while (i.hasNext()) {
								answer = i.next();
								if (answer.question.compareTo(question) == 0
										&& answer.student.compareTo(student) == 0) {
									m = answer.getAnswer();
								}
							}
							Object[] rowData = { question.getNum(), question.getContent(), question.getScaleScore(),
									m };
							questionsTBLMDL.addRow(rowData);
						}
						questionsTBL.setModel(questionsTBLMDL);

					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(null, "null pointer exception!");
					}
				}
			}

		}
	}

	public void populateStudents() {
		studentCMBMDL.removeAllElements();
		studentCMBMDL.addElement(null);
		Iterator<Student> it = sm.students.iterator();
		while (it.hasNext()) {
			student = it.next();
			studentCMBMDL.addElement(student);
		}
		studentCMB.setModel(studentCMBMDL);

	}

	public void populateExams() {
		examCMBMDL.removeAllElements();
		examCMBMDL.addElement(null);
		Iterator<Exam> it = sm.exams.iterator();
		while (it.hasNext()) {
			exam = it.next();
			examCMBMDL.addElement(exam);
		}
		examCMB.setModel(examCMBMDL);

	}

	public void populateExercises() {
		exerciseCMBMDL.removeAllElements();
		exerciseCMBMDL.addElement(null);
		Iterator<Exercise> it = sm.exercises.iterator();
		while (it.hasNext()) {
			exercise = it.next();
			exerciseCMBMDL.addElement(exercise);
			System.out.println(exercise.questionsSet);
		}
		exerciseCMB.setModel(exerciseCMBMDL);

	}

	static class ComboBoxRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Call the parent renderer to get the default rendering
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value instanceof Student) {
				Student student = (Student) value;
				// Set the display text to the name of the Specialty
				setText(student.getName());
			}
			if (value instanceof Exam) {
				Exam exam = (Exam) value;
				// Set the display text to the name of the exam
				setText(exam.getRender());
			}

			if (value instanceof Exercise) {
				Exercise exercise = (Exercise) value;
				// Set the display text to the name of the exercise
				setText(exercise.getRender());
			}

			return component;
		}
	}

	public class DisabledCellRenderer extends DefaultTableCellRenderer {
		private int disabledColumnIndex;

		public DisabledCellRenderer(int disabledColumnIndex) {
			this.disabledColumnIndex = disabledColumnIndex;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (column == 0) {
				component.setEnabled(false);
			}
			if (column == 1)
				component.setEnabled(false);
			else {
				component.setEnabled(true);
			}
			return component;
		}
	}

	@SuppressWarnings("deprecation")
	public void update(Observable o, Object obj) {
		if (obj instanceof Student)
			populateStudents();
		else if (obj instanceof Exam)
			populateExams();

	}

	private class NoValidAction extends Exception {
		public NoValidAction(String msg) {
			super(msg);
		}
	}

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
}
