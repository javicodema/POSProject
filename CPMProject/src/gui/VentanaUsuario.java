package gui;

import java.awt.FlowLayout;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Cliente;

import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class VentanaUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal vP;
	private JPanel pnCard;
	private JPanel pnLog;
	private JPanel pnRegistro;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField psFOriginal;
	private JTextField txtLogUs;
	private JButton btnCancel;
	private JButton btnLogIn;
	private JButton btnCancel_1;
	private JButton btnRegister;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblNif;
	private JLabel lblPhoneNumber;
	private JLabel lblUsername_1;
	private JLabel lblPassword_1;
	private JLabel lblRepeatPassword;
	private JTextField txNIF;
	private JTextField txUsername;
	private JTextField txSurname;
	private JTextField txName;
	private JTextField txPhone;
	
	private JPasswordField psFReg;
	private JPasswordField psFRep;
	private JLabel lblBank;
	private JTextField txBank;
	private JPanel pnButtonsReg;
	private JPanel pnCenter;
	private JPanel pnWest;
	private JPanel pnButton;
	private JPanel pnWesti;
	private JPanel pnCenti;
	private HelpBroker hb;
	private HelpSet hs;
	private ResourceBundle mensajes;

	public VentanaUsuario(VentanaPrincipal ventanaPrincipal) {
		vP = ventanaPrincipal;
		inter(vP.getLocale());
		setResizable(false);
		getContentPane().setBackground(new Color(30, 144, 255));
		setTitle(mensajes.getString("texto1"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 421);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaUsuario.class.getResource("/img/Logo.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getPnCard());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"registro", hs);
	}

	private JPanel getPnCard() {
		if (pnCard == null) {
			pnCard = new JPanel();
			pnCard.setBackground(new Color(30, 144, 255));
			pnCard.setBounds(0, 0, 434, 261);
			pnCard.setLayout(new CardLayout(0, 0));
			pnCard.add(getPnRegistro(), "0");
			pnCard.add(getPnLog(), "1");
		}
		return pnCard;
	}

	public void selectPane(int a) {
		if(a==0){
			CardLayout c = ((CardLayout)pnCard.getLayout());
			c.show(pnCard, "0");
			getRootPane().setDefaultButton(btnRegister);
		}
		else{
			CardLayout c = ((CardLayout)pnCard.getLayout());
			c.show(pnCard, "1");
			getRootPane().setDefaultButton(btnLogIn);
		}
	}
	private JPanel getPnLog() {
		if (pnLog == null) {
			pnLog = new JPanel();
			pnLog.setBackground(new Color(30, 144, 255));
			pnLog.setLayout(new BorderLayout(0, 0));
			pnLog.add(getPanel_1(), BorderLayout.SOUTH);
			pnLog.add(getPanel_1_1(), BorderLayout.WEST);
			pnLog.add(getPanel_1_2(), BorderLayout.CENTER);
		}
		return pnLog;
	}
	private JPanel getPnRegistro() {
		if (pnRegistro == null) {
			pnRegistro = new JPanel();
			pnRegistro.setBackground(new Color(30, 144, 255));
			pnRegistro.setLayout(new BorderLayout(0, 0));
			pnRegistro.add(getPnButtonsReg(), BorderLayout.SOUTH);
			pnRegistro.add(getPnCenter(), BorderLayout.CENTER);
			pnRegistro.add(getPnWest(), BorderLayout.WEST);
		}
		return pnRegistro;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel(mensajes.getString("texto3"));
			lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblUsername.setLabelFor(txtLogUs);
			lblUsername.setDisplayedMnemonic('U');
		}
		return lblUsername;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel(mensajes.getString("texto4"));
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPassword.setLabelFor(psFOriginal);
			if(vP.getLocale()==Locale.ENGLISH) lblPassword.setDisplayedMnemonic('P');
			else lblPassword.setDisplayedMnemonic('C');
		}
		return lblPassword;
	}
	private JPasswordField getPsFOriginal() {
		if (psFOriginal == null) {
			psFOriginal = new JPasswordField();
		}
		return psFOriginal;
	}
	private JTextField getTxtLogUs() {
		if (txtLogUs == null) {
			txtLogUs = new JTextField();
			txtLogUs.setColumns(10);
		}
		return txtLogUs;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(mensajes.getString("texto5"));
			if(vP.getLocale()==Locale.ENGLISH) btnCancel.setMnemonic('C');
			else btnCancel.setMnemonic('N');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnCancel;
	}
	private JButton getBtnLogIn() {
		if (btnLogIn == null) {
			btnLogIn = new JButton(mensajes.getString("texto6"));
			btnLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Cliente c = vP.getShop().getClientHandler().existeUsuario(txtLogUs.getText(),
							String.valueOf(psFOriginal.getPassword()));
					if(c!=null){
						vP.getShop().addUser(c);
						JOptionPane.showMessageDialog(null, mensajes.getString("texto7"));
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(getContentPane(),mensajes.getString("texto8"));
						txtLogUs.setText("");
						psFOriginal.setText("");
					}
					
				}
			});
		}
		return btnLogIn;
	}
	private JButton getBtnCancel_1() {
		if (btnCancel_1 == null) {
			btnCancel_1 = new JButton(mensajes.getString("texto5"));
			if(vP.getLocale()==Locale.ENGLISH) btnCancel_1.setMnemonic('C');
			else btnCancel_1.setMnemonic('L');
			btnCancel_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancel_1;
	}
	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton(mensajes.getString("texto9"));
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String[] lista={txName.getText(),txSurname.getText(),txUsername.getText(),txNIF.getText(),
							txPhone.getText(),String.valueOf(psFReg.getPassword()),
							String.valueOf(psFRep.getPassword()),txBank.getText()};
					String ans = vP.getShop().getClientHandler().registroCorrecto(lista);
					if(ans==""){
						vP.getShop().addUserinHandler();
						JOptionPane.showMessageDialog(null, mensajes.getString("texto7"));
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(contentPanel, ans);
					}
				}
			});
		}
		return btnRegister;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(mensajes.getString("texto10"));
			lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblName.setLabelFor(txName);
			lblName.setDisplayedMnemonic('N');
		}
		return lblName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel(mensajes.getString("texto11"));
			lblSurname.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblSurname.setLabelFor(txSurname);
			if(vP.getLocale()==Locale.ENGLISH) lblSurname.setDisplayedMnemonic('S');
			else lblSurname.setDisplayedMnemonic('A');
		}
		return lblSurname;
	}
	private JLabel getLblNif() {
		if (lblNif == null) {
			lblNif = new JLabel("NIF:");
			lblNif.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNif.setLabelFor(txNIF);
			lblNif.setDisplayedMnemonic('F');
		}
		return lblNif;
	}
	private JLabel getLblPhoneNumber() {
		if (lblPhoneNumber == null) {
			lblPhoneNumber = new JLabel(mensajes.getString("texto12"));
			lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPhoneNumber.setLabelFor(txPhone);
			if(vP.getLocale()==Locale.ENGLISH) lblPhoneNumber.setDisplayedMnemonic('P');
			else lblPhoneNumber.setDisplayedMnemonic('T');
		}
		return lblPhoneNumber;
	}
	private JLabel getLblUsername_1() {
		if (lblUsername_1 == null) {
			lblUsername_1 = new JLabel(mensajes.getString("texto3"));
			lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUsername_1.setLabelFor(txUsername);
			lblUsername_1.setDisplayedMnemonic('U');
		}
		return lblUsername_1;
	}
	private JLabel getLblPassword_1() {
		if (lblPassword_1 == null) {
			lblPassword_1 = new JLabel(mensajes.getString("texto4"));
			lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPassword_1.setLabelFor(psFReg);
			if(vP.getLocale()==Locale.ENGLISH) lblPassword_1.setDisplayedMnemonic('W');
			else lblPassword_1.setDisplayedMnemonic('C');
		}
		return lblPassword_1;
	}
	private JLabel getLblRepeatPassword() {
		if (lblRepeatPassword == null) {
			lblRepeatPassword = new JLabel(mensajes.getString("texto13"));
			lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRepeatPassword.setLabelFor(psFRep);
			lblRepeatPassword.setDisplayedMnemonic('R');
		}
		return lblRepeatPassword;
	}
	private JTextField getTxNIF() {
		if (txNIF == null) {
			txNIF = new JTextField();
			txNIF.setColumns(10);
		}
		return txNIF;
	}
	private JTextField getTxUsername() {
		if (txUsername == null) {
			txUsername = new JTextField();
			txUsername.setColumns(10);
		}
		return txUsername;
	}
	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setColumns(10);
		}
		return txName;
	}
	private JTextField getTxPhone() {
		if (txPhone == null) {
			txPhone = new JTextField();
			txPhone.setColumns(10);
		}
		return txPhone;
	}
	private JPasswordField getPsFReg() {
		if (psFReg == null) {
			psFReg = new JPasswordField();
		}
		return psFReg;
	}
	private JPasswordField getPsFRep() {
		if (psFRep == null) {
			psFRep = new JPasswordField();
		}
		return psFRep;
	}
	private JLabel getLblBank() {
		if (lblBank == null) {
			lblBank = new JLabel(mensajes.getString("texto14"));
			lblBank.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblBank.setLabelFor(txBank);
			if(vP.getLocale()==Locale.ENGLISH) lblBank.setDisplayedMnemonic('B');
			else lblBank.setDisplayedMnemonic('D');
		}
		return lblBank;
	}
	private JTextField getTxBank() {
		if (txBank == null) {
			txBank = new JTextField();
			txBank.setColumns(10);
		}
		return txBank;
	}
	private JPanel getPnButtonsReg() {
		if (pnButtonsReg == null) {
			pnButtonsReg = new JPanel();
			pnButtonsReg.setBackground(new Color(30, 144, 255));
			FlowLayout flowLayout = (FlowLayout) pnButtonsReg.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtonsReg.add(getBtnRegister());
			pnButtonsReg.add(getBtnCancel_1());
		}
		return pnButtonsReg;
	}
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(new Color(30, 144, 255));
			pnCenter.setLayout(new GridLayout(0, 1, 0, 5));
			pnCenter.add(getTxName());
			pnCenter.add(getTxSurname());
			pnCenter.add(getTxUsername());
			pnCenter.add(getTxNIF());
			pnCenter.add(getTxPhone());
			pnCenter.add(getTxBank());
			pnCenter.add(getPsFReg());
			pnCenter.add(getPsFRep());
		}
		return pnCenter;
	}
	private JPanel getPnWest() {
		if (pnWest == null) {
			pnWest = new JPanel();
			pnWest.setBackground(new Color(30, 144, 255));
			pnWest.setLayout(new GridLayout(0, 1, 0, 0));
			pnWest.add(getLblName());
			pnWest.add(getLblSurname());
			pnWest.add(getLblUsername_1());
			pnWest.add(getLblNif());
			pnWest.add(getLblPhoneNumber());
			pnWest.add(getLblBank());
			pnWest.add(getLblPassword_1());
			pnWest.add(getLblRepeatPassword());
		}
		return pnWest;
	}
	private JPanel getPanel_1() {
		if (pnButton == null) {
			pnButton = new JPanel();
			pnButton.setBackground(new Color(30, 144, 255));
			pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnButton.add(getBtnLogIn());
			pnButton.add(getBtnCancel());
		}
		return pnButton;
	}
	private JPanel getPanel_1_1() {
		if (pnWesti == null) {
			pnWesti = new JPanel();
			pnWesti.setBackground(new Color(30, 144, 255));
			pnWesti.setLayout(new GridLayout(5, 1, 0, 0));
			pnWesti.add(getLblUsername());
			pnWesti.add(getLblPassword());
		}
		return pnWesti;
	}
	private JPanel getPanel_1_2() {
		if (pnCenti == null) {
			pnCenti = new JPanel();
			pnCenti.setBackground(new Color(30, 144, 255));
			pnCenti.setLayout(new GridLayout(5, 1, 0, 25));
			pnCenti.add(getTxtLogUs());
			pnCenti.add(getPsFOriginal());
		}
		return pnCenti;
	}
	
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVUs",localizacion); 
	}
}
