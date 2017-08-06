package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Articulo;
import logica.Categoria;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class VentanaCategoria extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal vP;
	private JButton btnAtras;
	private JPanel pnNorth;
	private JButton btnLogo;
	private JLabel lblStarkShop;
	private JButton btnUser;
	private JButton btnShoppingCart;
	private JPanel pnNorthLeft;
	private JPanel pnNorthRight;
	private JPanel pnCenter;
	private JPanel pnCN;
	private JPanel pnCNL;
	private JPanel pnCNR;
	private JLabel lblCategoria;
	private JComboBox<String> comboBox;
	private List<Articulo> artCat = null;
	private JScrollPane scrollPane;
	private JPanel pnMain;
	private MyArtListener listener = new MyArtListener();
	private MyKeyArtListener kL = new MyKeyArtListener();
	private Categoria cat;
	private VentanaArticulo vArt;
	private HelpBroker hb;
	private HelpSet hs;
	private ResourceBundle mensajes;

	public VentanaArticulo getvArt() {
		return vArt;
	}

	public VentanaCategoria(VentanaPrincipal v, Categoria c) {
		vP=v;
		inter(vP.getLocale());
		setBackground(new Color(143, 188, 143));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/Logo.jpg")));
		setTitle("Stark Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 530);
		cat = c;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnCenter(), BorderLayout.CENTER);
		textLabel(c);
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"categoria", hs);
	}

	private JButton getBtnUser() {
		if (btnUser == null) {
			btnUser = new JButton();
			btnUser.setToolTipText(mensajes.getString("texto1"));
			btnUser.setBorderPainted(false);
			btnUser.setBackground(Color.BLACK);
			btnUser.setForeground(Color.BLACK);
			btnUser.setBounds(new Rectangle(0, 0, 50, 50));
			vP.setImagenAdaptada(btnUser,"/img/user.png");
			btnUser.setActionCommand("0");
			btnUser.addActionListener(vP.getAL(true));
			btnUser.addKeyListener(vP.getKL());
		}
		return btnUser;
	}
	private JButton getBtnShoppingCart() {
		if (btnShoppingCart == null) {
			btnShoppingCart = new JButton();
			btnShoppingCart.setToolTipText(mensajes.getString("texto2"));
			btnShoppingCart.setBorderPainted(false);
			btnShoppingCart.setBackground(Color.BLACK);
			btnShoppingCart.setForeground(Color.BLACK);
			btnShoppingCart.setBounds(new Rectangle(0, 0, 50, 50));
			vP.setImagenAdaptada(btnShoppingCart,"/img/shop.jpg");
			btnShoppingCart.setActionCommand("1");
			btnShoppingCart.addActionListener(vP.getAL(false));
			btnShoppingCart.addKeyListener(vP.getKL());
		}
		return btnShoppingCart;
	}
	
	private void textLabel(Categoria c) {
		String dis = "";
		String cat = "";
		if(c ==Categoria.Consolas) cat = mensajes.getString("texto3");
		else if(c==Categoria.Moviles) cat = mensajes.getString("texto4");
		else if(c==Categoria.Foto) cat = mensajes.getString("texto5");
		else if(c==Categoria.PC) cat = mensajes.getString("texto6");
		else cat = mensajes.getString("texto7");
		if(c==vP.getShop().getDiscounted()){
			dis=mensajes.getString("texto8");
		}
		lblCategoria.setText(cat+dis);
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton();
			btnAtras.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
						vP.setVisible(true);
						dispose();
					}
				}
			});
			btnAtras.setBorder(null);
			btnAtras.setBackground(new Color(255, 255, 255));
			btnAtras.setBounds(0,0,50,50);
			btnAtras.setToolTipText(mensajes.getString("texto9"));
			vP.setImagenAdaptada(btnAtras, "/img/12101.png");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vP.setVisible(true);
					dispose();
				}
			});
		}
		return btnAtras;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBackground(new Color(0, 0, 0));
			pnNorth.setLayout(new GridLayout(0, 2, 0, 0));
			pnNorth.add(getPnNorthLeft());
			pnNorth.add(getPnNorthRight());
		}
		return pnNorth;
	}

	private JButton getBtnLogo() {
		if (btnLogo == null) {
			btnLogo = new JButton("");
			btnLogo.setHorizontalAlignment(SwingConstants.LEFT);
			btnLogo.setBorder(null);
			btnLogo.setForeground(new Color(0, 0, 0));
			btnLogo.setEnabled(false);
			btnLogo.setBackground(new Color(0, 0, 0));
			btnLogo.setBounds(new Rectangle(0, 0, 125, 100));
			vP.setImagenAdaptada(btnLogo,"/img/Logo.jpg");
		}
		return btnLogo;
	}
	private JLabel getLblStarkShop() {
		if (lblStarkShop == null) {
			lblStarkShop = new JLabel("STARK SHOP");
			lblStarkShop.setFont(new Font("Juice ITC", Font.BOLD, 56));
			lblStarkShop.setForeground(Color.ORANGE);
		}
		return lblStarkShop;
	}
	private JPanel getPnNorthLeft() {
		if (pnNorthLeft == null) {
			pnNorthLeft = new JPanel();
			pnNorthLeft.setBackground(Color.BLACK);
			pnNorthLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnNorthLeft.add(getBtnLogo());
			pnNorthLeft.add(getLblStarkShop());
		}
		return pnNorthLeft;
	}
	private JPanel getPnNorthRight() {
		if (pnNorthRight == null) {
			pnNorthRight = new JPanel();
			pnNorthRight.setBackground(Color.BLACK);
			pnNorthRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnNorthRight.add(getBtnUser());
			pnNorthRight.add(getBtnShoppingCart());
		}
		return pnNorthRight;
	}
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(new Color(211, 211, 211));
			pnCenter.setLayout(new BorderLayout(0, 0));
			pnCenter.add(getScrollPane(), BorderLayout.CENTER);
			pnCenter.add(getPnCN(), BorderLayout.NORTH);
		}
		return pnCenter;
	}
	private JPanel getPnCN() {
		if (pnCN == null) {
			pnCN = new JPanel();
			pnCN.setBackground(new Color(211, 211, 211));
			pnCN.setLayout(new BorderLayout(0, 0));
			pnCN.add(getPnCNL(), BorderLayout.WEST);
			pnCN.add(getPnCNR(), BorderLayout.EAST);
		}
		return pnCN;
	}
	private JPanel getPnCNL() {
		if (pnCNL == null) {
			pnCNL = new JPanel();
			pnCNL.setBackground(new Color(211, 211, 211));
			FlowLayout fl_pnCNL = (FlowLayout) pnCNL.getLayout();
			fl_pnCNL.setAlignment(FlowLayout.LEFT);
			pnCNL.add(getBtnAtras());
			pnCNL.add(getLblCategoria());
		}
		return pnCNL;
	}
	private JPanel getPnCNR() {
		if (pnCNR == null) {
			pnCNR = new JPanel();
			pnCNR.setBackground(new Color(211, 211, 211));
			FlowLayout fl_pnCNR = (FlowLayout) pnCNR.getLayout();
			fl_pnCNR.setAlignment(FlowLayout.RIGHT);
			pnCNR.add(getComboBox_1());
		}
		return pnCNR;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel();
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblCategoria;
	}
	private JComboBox<String> getComboBox_1() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reorder();
				}
			});
			comboBox.addItem(mensajes.getString("texto10"));
			comboBox.addItem("<50€");
			comboBox.addItem("50-250");
			comboBox.addItem("250-500€");
			comboBox.addItem("500€>");
		}
		return comboBox;
	}
	
	protected void reorder() {
		if(comboBox.getSelectedIndex()==0){
			pnMain.removeAll();
			artCat=vP.getShop().getCartHandler().getArticulosN(cat);
			createPanelButtons(pnMain);
		}
		else if(comboBox.getSelectedIndex()==1){
			pnMain.removeAll();
			artCat=vP.getShop().getCartHandler().getArticulosN(cat);
			List<Articulo> aux = new ArrayList<Articulo>();
			for(Articulo a:artCat){
				if(a.getValor()<=50){
					aux.add(a);
				}
			}
			artCat=aux;
			createPanelButtons(pnMain);
		}
		else if(comboBox.getSelectedIndex()==2){
			pnMain.removeAll();
			artCat=vP.getShop().getCartHandler().getArticulosN(cat);
			List<Articulo> aux = new ArrayList<Articulo>();
			for(Articulo a:artCat){
				if(a.getValor()>50&&a.getValor()<=250){
					aux.add(a);
				}
			}
			artCat=aux;
			createPanelButtons(pnMain);
		}
		else if(comboBox.getSelectedIndex()==3){
			pnMain.removeAll();
			artCat=vP.getShop().getCartHandler().getArticulosN(cat);
			List<Articulo> aux = new ArrayList<Articulo>();
			for(Articulo a:artCat){
				if(a.getValor()>250&&a.getValor()<=500){
					aux.add(a);
				}
			}
			artCat=aux;
			createPanelButtons(pnMain);
		}
		else if(comboBox.getSelectedIndex()==4){
			pnMain.removeAll();
			artCat=vP.getShop().getCartHandler().getArticulosN(cat);
			List<Articulo> aux = new ArrayList<Articulo>();
			for(Articulo a:artCat){
				if(a.getValor()>500){
					aux.add(a);
				}
			}
			artCat=aux;
			createPanelButtons(pnMain);
		}
	}

	private JButton createButton(Articulo a, Integer position){
		JButton button = new JButton("");
		button.setBackground(new Color(211, 211, 211));
		if(vP.getShop().getUser()!=null){
			if(a.getCodigo().equals("CV-PS-001")) button.setBorder(new LineBorder(Color.YELLOW,2));
			else if(a.getCodigo().equals("FV-CR-001")) button.setBorder(new LineBorder(Color.YELLOW,2));
			else if(a.getCodigo().equals("TM-XX-001")) button.setBorder(new LineBorder(Color.YELLOW,2));
			else if(a.getCodigo().equals("OT-SO-001")) button.setBorder(new LineBorder(Color.YELLOW,2));
			else if(a.getCodigo().equals("VI-CA-001")) button.setBorder(new LineBorder(Color.YELLOW,2));
			else button.setBorder(new LineBorder(Color.BLACK,2));
		}
		else button.setBorder(new LineBorder(Color.BLACK,2));
		button.setBounds(0,0,200,200);
		vP.setImagenAdaptada(button, a.getFoto());
		button.setActionCommand(position.toString());
		button.addActionListener(listener);
		button.addKeyListener(kL);
		return button;
	}
	
	private void createPanelButtons(JPanel panel){
		for(Articulo a:artCat){
			panel.add(createButton(a,artCat.indexOf(a)));
		}
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnMain());
		}
		return scrollPane;
	}
	private JPanel getPnMain() {
		if (pnMain == null) {
			pnMain = new JPanel();
			pnMain.setBackground(new Color(211, 211, 211));
			pnMain.setLayout(new GridLayout(5, 3, 0, 0));
		}
		return pnMain;
	}
	
	class MyArtListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			openArtPanel(Integer.parseInt(button.getActionCommand()));
		}

	}
	
	class MyKeyArtListener extends KeyAdapter{

		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				JButton button = (JButton) arg0.getSource();
				openArtPanel(Integer.parseInt(button.getActionCommand()));
			}
		}
	}

	public void openArtPanel(int parseInt) {
		Articulo a = artCat.get(parseInt);
		abrirVentanaArticulo(a);
	}

	private void abrirVentanaArticulo(Articulo a) {
		vArt  = new VentanaArticulo(this,a);
		vArt.setVisible(true);
		vArt.setLocationRelativeTo(this);
		this.setVisible(false);	
	}
	
	public VentanaPrincipal getVp(){
		return vP;
	}
	
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVCateg",localizacion); 
	}

	
}
