import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ApplicationInterface extends JFrame{
	JLabel appLBL;
	Font font;
	JPanel p, buttonp;
	schoolModel model ;
	EmployeeInterface ei ;
	StudentInterface si ;
	CorrectorInterface ci;
	public ApplicationInterface() {
		p = new JPanel(new BorderLayout());
		buttonp = new JPanel(new FlowLayout());
		appLBL= new JLabel("Welcome To Google Classroom");
		appLBL.setHorizontalAlignment(SwingConstants.CENTER);
		JButton button1 = new JButton("Employee Interface ");   
        JButton button2 = new JButton("Student Interface ");	
        JButton button3 = new JButton("Corrector Interface ");	
        
        Font font = new Font("Serif", Font.BOLD+Font.ITALIC, 20);
        appLBL.setFont(font);
        p.add(appLBL,BorderLayout.CENTER);
        buttonp.add(button1);
        buttonp.add(button2);
        buttonp.add(button3);
        
        p.add(buttonp,BorderLayout.SOUTH);
        add(p);
        
        model  = new schoolModel();
        ei =  new EmployeeInterface(model);
        si = new StudentInterface(model);
        ci = new CorrectorInterface(model);
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	ei.setSize(800,400);
                ei.setVisible(true);
                ei.setLocation(5,5);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	si.setSize(800,350);
        		si.setLocation(5,430);
        		si.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ci.setSize(800,350);
        		ci.setLocation(750,5);
        		ci.setVisible(true);

            }
        });
	}
}
