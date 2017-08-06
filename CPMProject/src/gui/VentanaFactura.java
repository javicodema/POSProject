package gui;


import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class VentanaFactura extends JDialog {

	private VentanaPrincipal vP;
	private JPanel panel;
	private JButton btnOk;
	private JCheckBox chckbxDoYouWant;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private HelpSet hs;
	private HelpBroker hb;
	private ResourceBundle mensajes;
	
	/**
	 * Create the dialog.
	 */
	public VentanaFactura(VentanaPrincipal v) {
		vP=v;
		inter(vP.getLocale());
		setTitle(mensajes.getString("texto1"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFactura.class.getResource("/img/Logo.jpg")));
		getContentPane().setBackground(new Color(211, 211, 211));
		setBounds(100, 100, 600, 500);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"categoria", hs);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(211, 211, 211));
			panel.add(getChckbxDoYouWant());
			panel.add(getBtnOk());
		}
		return panel;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			getRootPane().setDefaultButton(btnOk);
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxDoYouWant.isSelected()){
						vP.getShop().generarFactura(vP.getLocale());
					}
					vP.setVisible(true);
					vP.reset();
					dispose();
				}
			});
		}
		return btnOk;
	}
	private JCheckBox getChckbxDoYouWant() {
		if (chckbxDoYouWant == null) {
			chckbxDoYouWant = new JCheckBox(mensajes.getString("texto2"));
			chckbxDoYouWant.setBackground(new Color(211, 211, 211));
		}
		return chckbxDoYouWant;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setText(vP.getShop().getFactura(vP.getLocale()));
		}
		return textArea;
	}
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVF",localizacion); 
	}
}
