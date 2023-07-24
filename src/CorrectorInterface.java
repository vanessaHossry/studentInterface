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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial", "deprecation" })
public class CorrectorInterface extends JFrame implements Observer {
	schoolModel sm;

	JLabel correctorNameLBL, examLBL, exerciseLBL, questionLBL;
	JPanel p, p1, p2, p3, p4;
	JButton readBTN, saveBTN;
	JRadioButton cor1, cor2;
	ButtonGroup bg;

	JComboBox<Corrector> correctorCMB;
	JComboBox<Exam> examCMB;
	JComboBox<Exercise> exerciseCMB;
	JComboBox<Question> questionCMB;

	DefaultComboBoxModel<Corrector> correctorCMBMDL;
	DefaultComboBoxModel<Exam> examCMBMDL;
	DefaultComboBoxModel<Exercise> exerciseCMBMDL;
	DefaultComboBoxModel<Question> questionCMBMDL;

	JTable answersTBL;
	DefaultTableModel answersTBLMDL;
	JScrollPane sp;

	File file;
	Student student;
	Exam exam;
	Exercise exercise;
	Question question;
	StudentAnswer stanswer;
	Corrector corrector;
	Correction correction, tempcorrection;

	double sc;
	double score1, score2;
	String m, s;
	String sc1, sc2;
	boolean test;

	int n;

	CorrectorInterface(schoolModel model) {
		super("Corrector Frame");
		setLocationRelativeTo(null);
		Container c = getContentPane();
		sm = model;
		sm.addObserver(this);

		c.setLayout(new BorderLayout());
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3 = new JPanel();
		p2.setLayout(new BorderLayout());
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		readBTN = new JButton("read");
		saveBTN = new JButton("save");

		correctorNameLBL = new JLabel("Corrector Name");
		correctorCMBMDL = new DefaultComboBoxModel<Corrector>();
		correctorCMB = new JComboBox<Corrector>(correctorCMBMDL);

		cor1 = new JRadioButton("corrector 1");
		cor2 = new JRadioButton("corrector 2");
		bg = new ButtonGroup();
		bg.add(cor1);
		bg.add(cor2);

		examLBL = new JLabel("Exam");
		examCMBMDL = new DefaultComboBoxModel<Exam>();
		examCMB = new JComboBox<Exam>(examCMBMDL);

		exerciseLBL = new JLabel("Exercise");
		exerciseCMBMDL = new DefaultComboBoxModel<Exercise>();
		exerciseCMB = new JComboBox<Exercise>(exerciseCMBMDL);

		questionLBL = new JLabel("Question");
		questionCMBMDL = new DefaultComboBoxModel<Question>();
		questionCMB = new JComboBox<Question>(questionCMBMDL);

		answersTBLMDL = new DefaultTableModel();
		answersTBLMDL.addColumn("Number");
		answersTBLMDL.addColumn("Content");
		answersTBLMDL.addColumn("Scale-Score");

		answersTBLMDL.addColumn("Student Answer");
		answersTBLMDL.addColumn("score1");
		answersTBLMDL.addColumn("score2");
		answersTBL = new JTable(answersTBLMDL);
		JScrollPane sp = new JScrollPane(answersTBL);

		correctorCMB.setPreferredSize(new Dimension(120, 25));
		examCMB.setPreferredSize(new Dimension(120, 25));
		exerciseCMB.setPreferredSize(new Dimension(120, 25));
		questionCMB.setPreferredSize(new Dimension(120, 25));
		readBTN.setPreferredSize(new Dimension(70, 30));
		saveBTN.setPreferredSize(new Dimension(70, 30));
		sp.setPreferredSize(new Dimension(600, 200));

		// studentCMB.addItemListener(new ItemActions());
		readBTN.addActionListener(new Actions());
		saveBTN.addActionListener(new Actions());

		examCMB.addItemListener(new ItemActions());
		exerciseCMB.addItemListener(new ItemActions());
		questionCMB.addItemListener(new ItemActions());

		// --- rendering to display the text i want in combo box
		correctorCMB.setRenderer(new ComboBoxRenderer());
		examCMB.setRenderer(new ComboBoxRenderer());
		exerciseCMB.setRenderer(new ComboBoxRenderer());
		questionCMB.setRenderer(new ComboBoxRenderer());

		p.add(readBTN);
		p.add(saveBTN);

		p1.add(correctorNameLBL);
		p1.add(correctorCMB);
		p1.add(examLBL);
		p1.add(examCMB);
		p1.add(exerciseLBL);
		p1.add(exerciseCMB);
		p1.add(questionLBL);
		p1.add(questionCMB);

		p4.add(cor1);
		p4.add(cor2);

		p2.add(p, BorderLayout.NORTH);
		p2.add(p1, BorderLayout.WEST);
		p2.add(p4, BorderLayout.SOUTH);

		p3.add(sp, BorderLayout.SOUTH);

		c.add(p2, BorderLayout.NORTH);
		c.add(p3, BorderLayout.SOUTH);
	}

	private class Actions implements ActionListener {

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == readBTN) {
				try {
					file = new File("correctors.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.correctors = (Set<Corrector>) ois.readObject();
						populateCorrectors();
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
						sm.exercises = (ArrayList<Exercise>) ois.readObject();
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

					file = new File("corrections.dat");
					if (!file.exists())
						file.createNewFile();
					else {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						sm.corrections = (Set<Correction>) ois.readObject();
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
			if (source == saveBTN) {
				try {
					if (correctorCMB.getSelectedIndex() == -1
							|| (cor1.isSelected() == false && cor2.isSelected() == false)) {
						throw new NoValidAction("error, no corrector selected ");
					}
					corrector = (Corrector) correctorCMB.getSelectedItem();
					s = "";
					sc = 0;
					n = 0;
					m = "";
					score1 = 0;
					score2 = 0;
					test = false;
					if (!validCheck())
						throw new NoValidAction("field empty ");
					for (int i = 0; i < answersTBL.getRowCount(); i++) {
						for (int j = 0; j < answersTBL.getColumnCount(); j++) {
							if (answersTBL.getValueAt(i, j) == null)
								continue;
							String value = answersTBL.getValueAt(i, j).toString();

							// String value = questionsTBL.getValueAt(i,j).toString();
							if (j == 0) {
								n = Integer.parseInt(value); // number
							} else if (j == 1) {
								m = (String) value; // content
							} else if (j == 2) {
								sc = Double.parseDouble(value); // scale score
							} else if (j == 3) {
								s = (String) value; // answer
							} else if (j == 4) {
								System.out.println(value);
								if (!value.isEmpty())
									score1 = Double.parseDouble(value); // score1
							}

							else if (j == 5) {
								System.out.println(value);
								if (!value.isEmpty())
									score2 = Double.parseDouble(value); // score2
							}

						}
						String[] parts = s.split("/");
						question = new Question(n, m, sc);

						// retrieve student to form correction
						Iterator<StudentAnswer> j = sm.studentanswers.iterator();
						while (j.hasNext()) {
							stanswer = j.next();
							if (stanswer.question.compareTo(question) == 0
									&& stanswer.student.getName().equals(parts[0])) {
								student = stanswer.student;
							}
						}
						stanswer = new StudentAnswer(parts[1], student, question);
						System.out.println(parts[1]);

						if (cor1.isSelected()) {
							correction = new Correction(corrector, score1, stanswer);
							System.out.println(correction);
							sm.corrections.add(correction);
						}

						else if (cor2.isSelected()) {
							Iterator<Correction> it = sm.corrections.iterator();
							while (it.hasNext()) {
								tempcorrection = (Correction) it.next();
								if (tempcorrection.studentAnswer.compareTo(stanswer) == 0) {
									test = true;
									tempcorrection.addCorrector2(corrector, score2);

								}

							}
							if (test == false)
								throw new NoValidAction("please select corrector 1 first");
							// test=false;
						}

					}
					file = new File("corrections.dat");
					if (!file.exists())
						file.createNewFile();
					FileOutputStream os = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(sm.corrections);
					oos.flush();
					oos.close();
				} catch (IOException ioe) {
				} catch (NoValidAction ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		}
	}

	private class ItemActions implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			Object source = e.getSource();
			if (source == correctorCMB) {
				corrector = (Corrector) examCMB.getSelectedItem();
				if (corrector == null)
					return;

				questionCMBMDL.removeAllElements();
				exerciseCMBMDL.removeAllElements();
				examCMBMDL.removeAllElements();

			}
			if (source == examCMB) {
				exam = (Exam) examCMB.getSelectedItem();
				if (exam == null)
					return;
				questionCMBMDL.removeAllElements();
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

						questionCMBMDL.removeAllElements();
						Iterator<Question> it = (exercise.questionsSet).iterator();
						while (it.hasNext()) {
							question = it.next();
							questionCMBMDL.addElement(question);
						}
						questionCMB.setModel(questionCMBMDL);
					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(null, "null pointer exception!" + npe.getMessage());
					}
				}
			}

			if (source == questionCMB) {
				int newQuestionCount = questionCMB.getItemCount();
				if (newQuestionCount > 0) {
					try {
						question = (Question) questionCMB.getSelectedItem();
						corrector = (Corrector) correctorCMB.getSelectedItem();
						// if(question == null)return;
						answersTBLMDL.setRowCount(0);
						Iterator<StudentAnswer> it = sm.studentanswers.iterator();
						sc1 = "";
						sc2 = "";
						while (it.hasNext()) {

							stanswer = it.next();
							if (stanswer.question.compareTo(question) == 0) {
								String studentanswer = stanswer.student.getName() + "/" + stanswer.getAnswer();

								Iterator<Correction> i = sm.corrections.iterator();
								while (i.hasNext()) {

									correction = i.next();

									if (((correction.corrector1 != null) || (correction.corrector2 != null))
											&& correction.studentAnswer.compareTo(stanswer) == 0) {
										if (correction.score1 >= 0)
											sc1 = String.valueOf(correction.score1);
										if (correction.score2 >= 0)
											sc2 = String.valueOf(correction.score2);
									}
								}
								Object[] rowData = { question.num, question.getContent(), question.scale_note,
										studentanswer, sc1, sc2 };
								answersTBLMDL.addRow(rowData);
							}

						}
						answersTBL.setModel(answersTBLMDL);
					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(null, "null pointer exception!" + npe.getMessage());
					}
				}
			}
		}
	}

	public void populateCorrectors() {
		correctorCMBMDL.removeAllElements();
		correctorCMBMDL.addElement(null);
		Iterator<Corrector> it = sm.correctors.iterator();
		while (it.hasNext()) {
			corrector = it.next();
			correctorCMBMDL.addElement(corrector);
		}
		correctorCMB.setModel(correctorCMBMDL);

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
		}
		exerciseCMB.setModel(exerciseCMBMDL);

	}

	static class ComboBoxRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Call the parent renderer to get the default rendering
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value instanceof Corrector) {
				Corrector corrector = (Corrector) value;
				// Set the display text to the name of the Specialty
				setText(corrector.getName());
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
			if (value instanceof Question) {
				Question question = (Question) value;
				// Set the display text to the name of the exercise
				setText(question.getRender());
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
		if (obj instanceof Corrector)
			populateCorrectors();
		else if (obj instanceof Exam)
			populateExams();
		else if (obj instanceof Exercise)
			populateExercises();

	}

	private class NoValidAction extends Exception {
		public NoValidAction(String msg) {
			super(msg);
		}
	}

	public boolean validCheck() {
		if (answersTBL.getCellEditor() != null) {
			answersTBL.getCellEditor().stopCellEditing();
		}
//	      for(int i=0; i < answersTBL.getRowCount(); i++) { 
//	         for(int j=0; j < answersTBL.getColumnCount(); j++) {
//	            String value = answersTBL.getValueAt(i,j).toString();
//	            if(value.trim().length() == 0) {
//	               return false;
//	            }
//	         }
//	      }
		return true;
	}

}
