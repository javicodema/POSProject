package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Categoria;
import logica.Shop;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel pnNorth;
	private JButton btnLogo;
	private JLabel lblStarkShop;
	private JButton btnUser;
	private JButton btnShoppingCart;
	private JPanel pnNorthLeft;
	private JPanel pnNorthRight;
	private JPanel pnButtons;
	private JButton btnMobiles;
	private JButton btnPhotographyAndVideo;
	private JButton btnComputersAndTablets;
	private JButton btnVideomonitoring;
	private MyListener al = new MyListener();
	private MyKeyListener kl = new MyKeyListener();
	private Shop shop = null;
	
	private VentanaUsuario vR = null;
	private VentanaCarro vCart = null;
	private JButton btnConsoles;
	private VentanaCategoria vCat = null;
	private HelpBroker hb;
	private HelpSet hs;
	private JButton btnEnglishVersion;
	private Locale loc;
	private ResourceBundle mensajes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		loc=new Locale("es","ES");
		inter(loc);
		setBackground(new Color(143, 188, 143));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/Logo.jpg")));
		setTitle("Stark Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnButtons(), BorderLayout.CENTER);
		shop = new Shop();
		shop.getCartHandler().reiniciarCarro();
		cargaAyuda();		
	}
	
	public void setImagenAdaptada(JButton boton, String rutaImagen){
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance((int)(boton.getWidth()),
				(int)(boton.getHeight()), Image.SCALE_FAST);
		boton.setIcon(new ImageIcon(imgEscalada));
		boton.setDisabledIcon(new ImageIcon(imgEscalada));
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
			setImagenAdaptada(btnLogo,"/img/Logo.jpg");
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
	private JButton getBtnUser() {
		if (btnUser == null) {
			btnUser = new JButton();
			btnUser.setToolTipText(mensajes.getString("texto1"));
			btnUser.setBorderPainted(false);
			btnUser.setBackground(Color.BLACK);
			btnUser.setForeground(Color.BLACK);
			btnUser.setBounds(new Rectangle(0, 0, 50, 50));
			setImagenAdaptada(btnUser,"/img/user.png");
			btnUser.setActionCommand("0");
			btnUser.addActionListener(al);
			btnUser.addKeyListener(kl);
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
			setImagenAdaptada(btnShoppingCart,"/img/shop.jpg");
			btnShoppingCart.setActionCommand("1");
			btnShoppingCart.addActionListener(al);
			btnShoppingCart.addKeyListener(kl);
		}
		return btnShoppingCart;
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
			pnNorthRight.add(getBtnEnglishVersion());
			pnNorthRight.add(getBtnUser());
			pnNorthRight.add(getBtnShoppingCart());
		}
		return pnNorthRight;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(new Color(211, 211, 211));
			pnButtons.setLayout(new GridLayout(2, 5, 100, 50));
			pnButtons.add(getBtnConsoles());
			pnButtons.add(getBtnMobiles());
			pnButtons.add(getBtnPhotographyAndVideo());
			pnButtons.add(getBtnComputersAndTablets());
			pnButtons.add(getBtnVideomonitoring());
		}
		return pnButtons;
	}
	private JButton getBtnMobiles() {
		if (btnMobiles == null) {
			btnMobiles = new JButton();
			btnMobiles.setBorder(null);
			btnMobiles.setBackground(new Color(211, 211, 211));
			btnMobiles.setBounds(new Rectangle(0, 0, 150, 150));
			btnMobiles.setToolTipText(mensajes.getString("texto3"));
			setImagenAdaptada(btnMobiles,"/img/TM-XX-003.jpg");
			btnMobiles.setActionCommand("3");
			btnMobiles.addActionListener(al);
			btnMobiles.addKeyListener(kl);
		}
		return btnMobiles;
	}
	private JButton getBtnPhotographyAndVideo() {
		if (btnPhotographyAndVideo == null) {
			btnPhotographyAndVideo = new JButton();
			btnPhotographyAndVideo.setBorder(null);
			btnPhotographyAndVideo.setBackground(new Color(211, 211, 211));
			btnPhotographyAndVideo.setBounds(new Rectangle(0, 0, 150, 150));
			btnPhotographyAndVideo.setToolTipText(mensajes.getString("texto4"));
			setImagenAdaptada(btnPhotographyAndVideo,"/img/FV-VI-001.jpg");
			btnPhotographyAndVideo.setActionCommand("4");
			btnPhotographyAndVideo.addActionListener(al);
			btnPhotographyAndVideo.addKeyListener(kl);
		}
		return btnPhotographyAndVideo;
	}
	private JButton getBtnComputersAndTablets() {
		if (btnComputersAndTablets == null) {
			btnComputersAndTablets = new JButton();
			btnComputersAndTablets.setBorder(null);
			btnComputersAndTablets.setBackground(new Color(211, 211, 211));
			btnComputersAndTablets.setBounds(new Rectangle(0, 0, 150, 150));
			btnComputersAndTablets.setToolTipText(mensajes.getString("texto5"));
			setImagenAdaptada(btnComputersAndTablets,"/img/OT-PO-002.jpg");
			btnComputersAndTablets.setActionCommand("5");
			btnComputersAndTablets.addActionListener(al);
			btnComputersAndTablets.addKeyListener(kl);
		}
		return btnComputersAndTablets;
	}
	private JButton getBtnVideomonitoring() {
		if (btnVideomonitoring == null) {
			btnVideomonitoring = new JButton();
			btnVideomonitoring.setBorder(null);
			btnVideomonitoring.setBackground(new Color(211, 211, 211));
			btnVideomonitoring.setBounds(new Rectangle(0, 0, 150, 150));
			btnVideomonitoring.setToolTipText(mensajes.getString("texto6"));
			setImagenAdaptada(btnVideomonitoring,"/img/VI-CA-001.jpg");
			btnVideomonitoring.setActionCommand("6");
			btnVideomonitoring.addActionListener(al);
			btnVideomonitoring.addKeyListener(kl);
		}
		return btnVideomonitoring;
	}
	
	private JButton getBtnConsoles() {
		if (btnConsoles == null) {
			btnConsoles = new JButton("		");
			btnConsoles.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
				}
			});
			btnConsoles.setBorder(null);
			btnConsoles.setBackground(new Color(211, 211, 211));
			btnConsoles.setBounds(new Rectangle(0, 0, 150, 150));
			btnConsoles.setToolTipText(mensajes.getString("texto7"));
			setImagenAdaptada(btnConsoles,"/img/CV-DS-001.jpg");
			btnConsoles.setActionCommand("2");
			btnConsoles.addActionListener(al);
			btnConsoles.addKeyListener(kl);
		}
		return btnConsoles;
	}
	class MyKeyListener extends KeyAdapter{

		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				JButton button = (JButton) arg0.getSource();
				openPanel(Integer.parseInt(button.getActionCommand()));
			}
		}
	}


	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			openPanel(Integer.parseInt(button.getActionCommand()));
		}

	}

	private void abrirVentanaUsuario(int a){
		vR = new VentanaUsuario(this);
		vR.selectPane(a);
		vR.setModal(true);
		vR.setVisible(true);
		vR.setLocationRelativeTo(this);
	}
	
	private void abrirVentanaCarro(){
		vCart = new VentanaCarro(this);
		vCart.setModal(true);
		vCart.setVisible(true);
		vCart.setLocationRelativeTo(this);
	}

	public void openPanel(int parseInt) {
		switch(parseInt){
		case 0:
			if(shop.getUser()==null){
				JButton b1 = new JButton();
				JButton b2 = new JButton();
				if(this.getLocale()==Locale.ENGLISH){
					b1.setMnemonic('R');
					b2.setMnemonic('L');
				}
				else{
					b1.setMnemonic('R');
					b2.setMnemonic('A');
				}
				b1.setText(mensajes.getString("texto10"));
				b2.setText(mensajes.getString("texto11"));
				b1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Window w = SwingUtilities.getWindowAncestor(b1);
					    if (w != null) {
					      w.dispose();
					    }
						abrirVentanaUsuario(0);
					}
				});
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Window w = SwingUtilities.getWindowAncestor(b2);
					    if (w != null) {
					      w.dispose();
					    }
						abrirVentanaUsuario(1);
					}
				});

				JButton[] buttons = {b1,b2};
				
				int a = JOptionPane.showOptionDialog( null,mensajes.getString("texto8"),
						mensajes.getString("texto9"),JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, buttons,"opcion 1");

				if (a != -1){
					abrirVentanaUsuario(a);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,mensajes.getString("texto12")+shop.getUser().getPuntos());
			}
			break;
		case 1:
			abrirVentanaCarro();
			break;
		case 2:
			abrirVentanaCategoria(Categoria.Consolas);
			break;
		case 3:
			abrirVentanaCategoria(Categoria.Moviles);
			break;
		case 4:
			abrirVentanaCategoria(Categoria.Foto);
			break;
		case 5:
			abrirVentanaCategoria(Categoria.PC);
			break;
		case 6:
			abrirVentanaCategoria(Categoria.VideoM);
			break;
		}
		
	}
	private void abrirVentanaCategoria(Categoria consolas) {
		vCat  = new VentanaCategoria(this,consolas);
		vCat.setVisible(true);
		vCat.setLocationRelativeTo(this);
		this.setVisible(false);	
	}

	public Shop getShop() {
		return shop;
	}


	public ActionListener getAL(boolean a) {
		return al;
	}
	
	public KeyListener getKL() {
		return kl;
	}

	
	public void reset(){
		if(vCat!=null){
			if(vCat.getvArt()!=null) vCat.getvArt().dispose();
		}
		vR=null;
		vCat=null;
		vCart=null;
		shop.resetAll();
	}
	
	public void cargaAyuda(){
		URL hsURL;
		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}catch (Exception e){
			System.out.println("Ayuda no encontrada");
			return;
		}
		hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(),"main", hs);
	}
	
	public HelpBroker getHb(){
		return hb;
	}

	public HelpSet getHs() {
		return hs;
	}

	private JButton getBtnEnglishVersion() {
		if (btnEnglishVersion == null) {
			btnEnglishVersion = new JButton(mensajes.getString("texto13"));
			btnEnglishVersion.setMnemonic('V');
			btnEnglishVersion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(loc==Locale.ENGLISH){
						Locale spanish = new Locale("es", "ES");
						inter(spanish);
					}
					else inter(Locale.ENGLISH);
					resetText();
				}
			});
			btnEnglishVersion.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return btnEnglishVersion;
	}

	protected void inter(Locale string) {
		loc = string;
		this.setLocale(loc);
		mensajes = ResourceBundle.getBundle("textosVP",loc); 
	}

	private void resetText() {
		btnUser.setToolTipText(mensajes.getString("texto1"));
		btnShoppingCart.setToolTipText(mensajes.getString("texto2"));
		btnMobiles.setToolTipText(mensajes.getString("texto3"));
		btnPhotographyAndVideo.setToolTipText(mensajes.getString("texto4"));
		btnComputersAndTablets.setToolTipText(mensajes.getString("texto5"));
		btnVideomonitoring.setToolTipText(mensajes.getString("texto6"));
		btnConsoles.setToolTipText(mensajes.getString("texto7"));
		
		btnEnglishVersion.setText(mensajes.getString("texto13"));
		
	}
}
