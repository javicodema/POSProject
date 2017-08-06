
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.Articulo;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VentanaArticulo extends JFrame {

	private JPanel contentPane;
	private Articulo page;
	private VentanaCategoria vCC;
	private JButton btnLogo;
	private JPanel pnNorth;
	private JLabel lblStarkShop;
	private JButton btnUser;
	private JButton btnShoppingCart;
	private JPanel pnNorthLeft;
	private JPanel pnNorthRight;
	private JPanel pnWest;
	private JButton btnAtras;
	private JPanel pnCenter;
	private JLabel lblPoints;
	private JTextField txPoints;
	private JPanel pnCN;
	private JLabel lblName;
	private JLabel lblCategoria;
	private JPanel pnCC;
	private JPanel pnCC1;
	private JPanel pnCC2;
	private JButton btnIcon;
	private JPanel pnPrice;
	private JLabel lblPrice;
	private JTextField txPrice;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JPanel pnBuy;
	private JPanel pnBut;
	private JButton btnCart;
	private JPanel pnUnits;
	private JLabel lblUnits;
	private JSpinner spinner;
	private JLabel lblStock;
	private VentanaCarro vCart;
	private HelpBroker hb;
	private HelpSet hs;
	private ResourceBundle mensajes;

	/**
	 * Create the frame.
	 */
	public VentanaArticulo(VentanaCategoria vC,Articulo a) {
		vCC=vC;
		inter(vCC.getVp().getLocale());
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaArticulo.class.getResource("/img/Logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 530);
		page=a;
		setTitle(page.getDenom());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnWest(), BorderLayout.WEST);
		contentPane.add(getPnCenter(), BorderLayout.CENTER);
		hb=vCC.getVp().getHb();
		hs=vCC.getVp().getHs();
		hb.enableHelpKey(getRootPane(),"articulo", hs);
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
			vCC.getVp().setImagenAdaptada(btnLogo,"/img/Logo.jpg");
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
			vCC.getVp().setImagenAdaptada(btnUser,"/img/user.png");
			btnUser.setActionCommand("0");
			btnUser.addActionListener(vCC.getVp().getAL(true));
			btnUser.addKeyListener(vCC.getVp().getKL());
		}
		return btnUser;
	}
	private JButton getBtnShoppingCart() {
		if (btnShoppingCart == null) {
			btnShoppingCart = new JButton();
			btnShoppingCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					abrirVentanaCarro();
				}
			});
			btnShoppingCart.setToolTipText(mensajes.getString("texto2"));
			btnShoppingCart.setBorderPainted(false);
			btnShoppingCart.setBackground(Color.BLACK);
			btnShoppingCart.setForeground(Color.BLACK);
			btnShoppingCart.setBounds(new Rectangle(0, 0, 50, 50));
			vCC.getVp().setImagenAdaptada(btnShoppingCart,"/img/shop.jpg");
			btnShoppingCart.setActionCommand("1");
			btnShoppingCart.addKeyListener(vCC.getVp().getKL());
			
		}
		return btnShoppingCart;
	}
	protected void abrirVentanaCarro() {
		vCart = new VentanaCarro(this);
		vCart.setModal(true);
		vCart.setVisible(true);
		vCart.setLocationRelativeTo(this);
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
	
	private JPanel getPnWest() {
		if (pnWest == null) {
			pnWest = new JPanel();
			pnWest.setBackground(new Color(211, 211, 211));
			pnWest.setLayout(new GridLayout(5, 1, 0, 0));
			pnWest.add(getBtnAtras());
			pnWest.add(getLblPoints());
			pnWest.add(getTxPoints());
		}
		return pnWest;
	}
	
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton();
			btnAtras.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						vCC.setVisible(true);
						dispose();
					}
				}
			});
			btnAtras.setBorder(null);
			btnAtras.setBackground(new Color(211, 211, 211));
			btnAtras.setBounds(0,0,50,50);
			btnAtras.setToolTipText(mensajes.getString("texto3"));
			vCC.getVp().setImagenAdaptada(btnAtras, "/img/12101.png");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vCC.setVisible(true);
					dispose();
				}
			});
		}
		return btnAtras;
	}
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(new Color(211, 211, 211));
			pnCenter.setLayout(new BorderLayout(0, 0));
			pnCenter.add(getPnCN(), BorderLayout.NORTH);
			pnCenter.add(getPnCC(), BorderLayout.CENTER);
		}
		return pnCenter;
	}
	private JLabel getLblPoints() {
		if (lblPoints == null) {
			lblPoints = new JLabel(mensajes.getString("texto4"));
			lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
			lblPoints.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPoints;
	}
	private JTextField getTxPoints() {
		if (txPoints == null) {
			txPoints = new JTextField();
			txPoints.setBorder(null);
			txPoints.setFont(new Font("Tahoma", Font.BOLD, 20));
			txPoints.setEditable(false);
			txPoints.setBackground(new Color(211, 211, 211));
			txPoints.setHorizontalAlignment(SwingConstants.CENTER);
			txPoints.setColumns(10);
			txPoints.setText(String.valueOf(page.getPuntos()));
		}
		return txPoints;
	}
	private JPanel getPnCN() {
		if (pnCN == null) {
			pnCN = new JPanel();
			pnCN.setBackground(new Color(211, 211, 211));
			pnCN.setLayout(new GridLayout(2, 1, 0, 0));
			pnCN.add(getLblName());
			pnCN.add(getLblCategoria());
		}
		return pnCN;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("");
			lblName.setBackground(new Color(211, 211, 211));
			lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblName.setText(page.getDenom());
		}
		return lblName;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("");
			lblCategoria.setBackground(new Color(211, 211, 211));
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblCategoria.setText(page.getStrCat(page.getCat(),vCC.getVp().getLocale())+" > "+page.getScat());
		}
		return lblCategoria;
	}
	private JPanel getPnCC() {
		if (pnCC == null) {
			pnCC = new JPanel();
			pnCC.setBackground(new Color(211, 211, 211));
			pnCC.setLayout(new GridLayout(2, 1, 0, 0));
			pnCC.add(getPnCC1());
			pnCC.add(getPnCC2());
		}
		return pnCC;
	}
	private JPanel getPnCC1() {
		if (pnCC1 == null) {
			pnCC1 = new JPanel();
			pnCC1.setBackground(new Color(211, 211, 211));
			pnCC1.add(getBtnIcon());
			pnCC1.add(getPnPrice());
		}
		return pnCC1;
	}
	private JPanel getPnCC2() {
		if (pnCC2 == null) {
			pnCC2 = new JPanel();
			pnCC2.setBackground(new Color(211, 211, 211));
			pnCC2.setLayout(new GridLayout(2, 1, 0, 0));
			pnCC2.add(getScrollPane());
			pnCC2.add(getPnBuy());
		}
		return pnCC2;
	}
	private JButton getBtnIcon() {
		if (btnIcon == null) {
			btnIcon = new JButton("");
			btnIcon.setEnabled(false);
			btnIcon.setBounds(0, 0, 150, 150);
			btnIcon.setBorder(null);
			vCC.getVp().setImagenAdaptada(btnIcon, page.getFoto());
			btnIcon.setBackground(new Color(211, 211, 211));
		}
		return btnIcon;
	}
	private JPanel getPnPrice() {
		if (pnPrice == null) {
			pnPrice = new JPanel();
			pnPrice.setBackground(new Color(211, 211, 211));
			pnPrice.setLayout(new GridLayout(2, 1, 0, 0));
			pnPrice.add(getLblPrice());
			pnPrice.add(getTxPrice());
		}
		return pnPrice;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(mensajes.getString("texto5"));
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPrice;
	}
	private JTextField getTxPrice() {
		if (txPrice == null) {
			txPrice = new JTextField();
			txPrice.setBorder(null);
			txPrice.setBackground(new Color(211, 211, 211));
			txPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
			txPrice.setEditable(false);
			txPrice.setColumns(10);
			if(vCC.getVp().getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto11")+page.getValor()));
			else txPrice.setText(String.valueOf(page.getValor()+mensajes.getString("texto11")));
		}
		return txPrice;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
			textArea.setBackground(new Color(211, 211, 211));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setText(page.getDescr());
		}
		return textArea;
	}
	private JPanel getPnBuy() {
		if (pnBuy == null) {
			pnBuy = new JPanel();
			pnBuy.setBackground(new Color(211, 211, 211));
			pnBuy.setLayout(new BorderLayout(0, 0));
			pnBuy.add(getPnBut(), BorderLayout.EAST);
			pnBuy.add(getPnUnits(), BorderLayout.CENTER);
		}
		return pnBuy;
	}
	private JPanel getPnBut() {
		if (pnBut == null) {
			pnBut = new JPanel();
			pnBut.setBackground(new Color(211, 211, 211));
			pnBut.setLayout(new GridLayout(0, 1, 0, 0));
			pnBut.add(getBtnCart());
		}
		return pnBut;
	}
	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton(mensajes.getString("texto6"));
			btnCart.setMnemonic('A');
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i=(Integer)spinner.getValue();
					JOptionPane.showMessageDialog(null, mensajes.getString("texto7")+i+" "+mensajes.getString("texto8"));
					while(i!=0){
						vCC.getVp().getShop().getCartHandler().registrarArticulo(page);
						i--;
					}
					int stock =vCC.getVp().getShop().getStock(page);
					lblStock.setText(mensajes.getString("texto9")+String.valueOf(stock));
					if(stock==0){
						spinner.setEnabled(false);
					}
					else{
						spinner.setModel(new SpinnerNumberModel(1, 1, stock, 1));
					}
					
				}
			});
			btnCart.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnCart;
	}
	private JPanel getPnUnits() {
		if (pnUnits == null) {
			pnUnits = new JPanel();
			FlowLayout fl_pnUnits = (FlowLayout) pnUnits.getLayout();
			fl_pnUnits.setHgap(15);
			fl_pnUnits.setVgap(25);
			pnUnits.setBackground(new Color(211, 211, 211));
			pnUnits.add(getLblUnits());
			pnUnits.add(getSpinner());
			pnUnits.add(getLblStock());
		}
		return pnUnits;
	}
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel(mensajes.getString("texto10"));
			lblUnits.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblUnits;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, 1, vCC.getVp().getShop().getStock(page), 1));
			spinner.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return spinner;
	}
	public JLabel getLblStock() {
		if (lblStock == null) {
			lblStock = new JLabel();
			lblStock.setFont(new Font("Tahoma", Font.BOLD, 18));
			String st = mensajes.getString("texto9")+String.valueOf(vCC.getVp().getShop().getStock(page));
			lblStock.setText(st);
		}
		return lblStock;
	}

	public VentanaPrincipal getVp() {
		return vCC.getVp();
	}

	public Articulo getArticle() {
		return page;
	}
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVArt",localizacion); 
	}

}
