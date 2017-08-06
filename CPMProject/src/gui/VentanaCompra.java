package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Articulo;
import logica.Cliente;
import logica.Regalo;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaCompra extends JFrame {

	private JPanel contentPane;
	private JPanel pnButtons;
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel pnMain;
	private JPanel pnRegalos;
	private JPanel pnRegBut;
	private JButton btnAdd;
	private JPanel pnRegList;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> listModel = null;
	private JPanel pnPoints;
	private JPanel pnInfo;
	private JPanel pnLabels;
	private JPanel pnFields;
	private JLabel lblTotalOfPoints;
	private JTextField txPoints;
	private JLabel lblYouWillUse;
	private JPanel pnSpinner;
	private JSpinner spinner;
	private JLabel lblWillHaveAvailable;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblNif;
	private JLabel lblBankAccount;
	private JLabel lblPhoneNumber;
	private JTextField txName;
	private JTextField txSurname;
	private JTextField txNIF;
	private JTextField txBank;
	private JTextField txPhone;
	private VentanaPrincipal vP;
	private Cliente user;
	private VentanaFactura vF;
	private HelpSet hs;
	private HelpBroker hb;
	private ResourceBundle mensajes;

	/**
	 * Create the frame.
	 */
	public VentanaCompra(VentanaPrincipal ven) {
		vP=ven;
		inter(vP.getLocale());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 530);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/Logo.jpg")));
		setTitle(mensajes.getString("texto1"));
		Cliente c = vP.getShop().getUser();
		if(c!=null)user = c;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
		contentPane.add(getPnMain(), BorderLayout.CENTER);
		if(c!=null){
			txName.setText(c.getName());
			txName.setEditable(false);
			txSurname.setText(c.getSurname());
			txSurname.setEditable(false);
			txNIF.setText(c.getNif());
			txNIF.setEditable(false);
			txBank.setText(c.getBankAccount());
			txBank.setEditable(false);
			txPhone.setText(c.getPhone());
			txPhone.setEditable(false);
		}
		else{
			list.setEnabled(false);
			btnAdd.setEnabled(false);
		}
		List<Articulo> lA = vP.getShop().getCartHandler().getCartArt();
		if(lA.isEmpty()) spinner.setEnabled(false);
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"compra", hs);
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(new Color(211, 211, 211));
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.add(getBtnOk());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			getRootPane().setDefaultButton(btnOk);
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Articulo> lA = vP.getShop().getCartHandler().getCartArt();
					List<Regalo> lR = vP.getShop().getCartHandler().getCartReg();
					if(user==null){
						if(!lA.isEmpty()){
							String[] lista={txName.getText(),txSurname.getText(),"sampletext",txNIF.getText(),
									txPhone.getText(),"sampletext1",
									"sampletext1",txBank.getText()};
							String ans = vP.getShop().getClientHandler().registroCorrecto(lista);
							if(ans==""){
								vP.getShop().addUserinHandler();
								abrirVentanaFacturacion();
							}
							else{
								JOptionPane.showMessageDialog(null, ans);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, mensajes.getString("texto2"));
						}
					}
					else{
						if(!lA.isEmpty()||!lR.isEmpty()){
							int a = (Integer)spinner.getValue();
							vP.getShop().setDiscounted(a);
							vP.getShop().actualizarUser();
							abrirVentanaFacturacion();
						}
						else{
							JOptionPane.showMessageDialog(null,mensajes.getString("texto3"));
						}
					}
				}
			});
			btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnOk;
	}
	protected void abrirVentanaFacturacion() {
		vF  = new VentanaFactura(vP);
		vF.setVisible(true);
		vF.setModal(true);
		vF.setLocationRelativeTo(this);
		dispose();
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(mensajes.getString("texto13"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.getShop().getCartHandler().reiniciarRegalos();
					vP.setVisible(true);
					dispose();
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnCancel;
	}
	private JPanel getPnMain() {
		if (pnMain == null) {
			pnMain = new JPanel();
			pnMain.setBackground(new Color(211, 211, 211));
			pnMain.setLayout(new BorderLayout(0, 0));
			pnMain.add(getPnRegalos(), BorderLayout.SOUTH);
			pnMain.add(getPnPoints(), BorderLayout.EAST);
			pnMain.add(getPnInfo(), BorderLayout.CENTER);
		}
		return pnMain;
	}
	private JPanel getPnRegalos() {
		if (pnRegalos == null) {
			pnRegalos = new JPanel();
			pnRegalos.setBackground(new Color(211, 211, 211));
			pnRegalos.setLayout(new BorderLayout(0, 0));
			pnRegalos.add(getPnRegList(), BorderLayout.CENTER);
			pnRegalos.add(getPnRegBut(), BorderLayout.SOUTH);
		}
		return pnRegalos;
	}
	private JPanel getPnRegBut() {
		if (pnRegBut == null) {
			pnRegBut = new JPanel();
			pnRegBut.setBackground(new Color(211, 211, 211));
			pnRegBut.add(getBtnAdd());
		}
		return pnRegBut;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton(mensajes.getString("texto14"));
			btnAdd.setMnemonic('A');
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String art = (String)list.getSelectedValue();
					int row = list.getSelectedIndex();
					if(row!=-1){
						List<Regalo> lA= vP.getShop().getCartHandler().getReg();
						String[] info = art.split(";");
						try{
							for(Regalo a:lA){
								if(info[0].equals(a.getName())){
									if(a.getPuntos()<=(vP.getShop().getCartHandler().getPoints()+user.getPuntos())){
										vP.getShop().getCartHandler().registrarArticulo(a);
									}
									else{
										JOptionPane.showMessageDialog(null,mensajes.getString("texto4"));
									}
								}
							}
							txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()+user.getPuntos()));
							int a = 0;
							if((vP.getShop().getCartHandler().getPoints()+user.getPuntos())<vP.getShop().getCartHandler().getPrecio()) 
								a = vP.getShop().getCartHandler().getPoints()+user.getPuntos();
							else a = (int) vP.getShop().getCartHandler().getPrecio();
							if(user!=null) spinner.setModel(new SpinnerNumberModel(0, 0,
									a, 1));
						}catch(Exception e){}
					}
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnAdd;
	}
	private JPanel getPnRegList() {
		if (pnRegList == null) {
			pnRegList = new JPanel();
			pnRegList.setBackground(new Color(211, 211, 211));
			pnRegList.setLayout(new GridLayout(0, 1, 0, 0));
			pnRegList.add(getScrollPane());
		}
		return pnRegList;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<String> getList() {
		if (list == null) {
			listModel = new DefaultListModel<String>();
			list = new JList<String>(listModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			List<Regalo> lA= vP.getShop().getCartHandler().getReg();
			for(Regalo a:lA){
				String linea = a.getString();
				listModel.addElement(linea);
			}
		}
		return list;
	}
	private JPanel getPnPoints() {
		if (pnPoints == null) {
			pnPoints = new JPanel();
			pnPoints.setBackground(new Color(211, 211, 211));
			pnPoints.setLayout(new GridLayout(0, 1, 0, 0));
			pnPoints.add(getLblTotalOfPoints());
			pnPoints.add(getLblWillHaveAvailable());
			pnPoints.add(getTxPoints());
			pnPoints.add(getLblYouWillUse());
			pnPoints.add(getPnSpinner());
		}
		return pnPoints;
	}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(new Color(211, 211, 211));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getPnLabels(), BorderLayout.WEST);
			pnInfo.add(getPnFields());
		}
		return pnInfo;
	}
	private JPanel getPnLabels() {
		if (pnLabels == null) {
			pnLabels = new JPanel();
			pnLabels.setBackground(new Color(211, 211, 211));
			pnLabels.setLayout(new GridLayout(5, 1, 0, 0));
			pnLabels.add(getLblName());
			pnLabels.add(getLblSurname());
			pnLabels.add(getLblNif());
			pnLabels.add(getLblBankAccount());
			pnLabels.add(getLblPhoneNumber());
		}
		return pnLabels;
	}
	private JPanel getPnFields() {
		if (pnFields == null) {
			pnFields = new JPanel();
			pnFields.setBackground(new Color(211, 211, 211));
			pnFields.setLayout(new GridLayout(5, 1, 0, 25));
			pnFields.add(getTxName());
			pnFields.add(getTxSurname());
			pnFields.add(getTxNIF());
			pnFields.add(getTxBank());
			pnFields.add(getTxPhone());
		}
		return pnFields;
	}
	private JLabel getLblTotalOfPoints() {
		if (lblTotalOfPoints == null) {
			lblTotalOfPoints = new JLabel(mensajes.getString("texto5"));
			lblTotalOfPoints.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblTotalOfPoints;
	}
	private JTextField getTxPoints() {
		if (txPoints == null) {
			txPoints = new JTextField();
			txPoints.setBackground(new Color(211, 211, 211));
			txPoints.setBorder(null);
			txPoints.setFont(new Font("Tahoma", Font.BOLD, 14));
			txPoints.setEditable(false);
			txPoints.setColumns(10);
			if(user!=null) txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()+user.getPuntos()));
			else{
				txPoints.setText("0");
			}
		}
		return txPoints;
	}
	private JLabel getLblYouWillUse() {
		if (lblYouWillUse == null) {
			lblYouWillUse = new JLabel(mensajes.getString("texto6"));
			lblYouWillUse.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblYouWillUse;
	}
	private JPanel getPnSpinner() {
		if (pnSpinner == null) {
			pnSpinner = new JPanel();
			pnSpinner.setBackground(new Color(211, 211, 211));
			pnSpinner.setLayout(new BorderLayout(0, 0));
			pnSpinner.add(getSpinner(), BorderLayout.NORTH);
		}
		return pnSpinner;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			int a = 0;
			if(user!=null){
				if((vP.getShop().getCartHandler().getPoints()+user.getPuntos())<vP.getShop().getCartHandler().getPrecio()) 
					a = vP.getShop().getCartHandler().getPoints()+user.getPuntos();
				else a = (int) vP.getShop().getCartHandler().getPrecio();
				if(user!=null) spinner.setModel(new SpinnerNumberModel(0, 0,
						a, 1));
			}
			else{
				spinner.setModel(new SpinnerNumberModel(0, 0,1, 1));
				spinner.setEnabled(false);
			}

		}
		return spinner;
	}
	private JLabel getLblWillHaveAvailable() {
		if (lblWillHaveAvailable == null) {
			lblWillHaveAvailable = new JLabel(mensajes.getString("texto7"));
			lblWillHaveAvailable.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblWillHaveAvailable;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(mensajes.getString("texto8"));
			lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblName.setLabelFor(txName);
			lblName.setDisplayedMnemonic('N');
		}
		return lblName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel(mensajes.getString("texto9"));
			lblSurname.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSurname.setLabelFor(txSurname);
			if(vP.getLocale()==Locale.ENGLISH) lblSurname.setDisplayedMnemonic('S');
			else lblSurname.setDisplayedMnemonic('P');
		}
		return lblSurname;
	}
	private JLabel getLblNif() {
		if (lblNif == null) {
			lblNif = new JLabel("NIF:");
			lblNif.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNif.setLabelFor(txNIF);
			lblNif.setDisplayedMnemonic('F');
		}
		return lblNif;
	}
	private JLabel getLblBankAccount() {
		if (lblBankAccount == null) {
			lblBankAccount = new JLabel(mensajes.getString("texto10"));
			lblBankAccount.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblBankAccount.setLabelFor(txBank);
			if(vP.getLocale()==Locale.ENGLISH) lblBankAccount.setDisplayedMnemonic('B');
			else lblBankAccount.setDisplayedMnemonic('C');
		}
		return lblBankAccount;
	}
	private JLabel getLblPhoneNumber() {
		if (lblPhoneNumber == null) {
			lblPhoneNumber = new JLabel(mensajes.getString("texto11"));
			lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPhoneNumber.setLabelFor(txPhone);
			if(vP.getLocale()==Locale.ENGLISH) lblPhoneNumber.setDisplayedMnemonic('P');
			else lblPhoneNumber.setDisplayedMnemonic('T');
		}
		return lblPhoneNumber;
	}
	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setColumns(10);
		}
		return txName;
	}
	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	private JTextField getTxNIF() {
		if (txNIF == null) {
			txNIF = new JTextField();
			txNIF.setColumns(10);
		}
		return txNIF;
	}
	private JTextField getTxBank() {
		if (txBank == null) {
			txBank = new JTextField();
			txBank.setColumns(10);
		}
		return txBank;
	}
	private JTextField getTxPhone() {
		if (txPhone == null) {
			txPhone = new JTextField();
			txPhone.setColumns(10);
		}
		return txPhone;
	}
	
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVComp",localizacion); 
	}

}
