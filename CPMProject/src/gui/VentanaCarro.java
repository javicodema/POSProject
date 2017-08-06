package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Articulo;

import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VentanaCarro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal vP = null;
	private JLabel lblTotalPrice;
	private JLabel lblPointsEarned;
	private JTextField txPrice;
	private JTextField txPoints;
	private JLabel lblShoppingCart;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> listModel = null;
	private JButton btnEliminar;
	private JPanel pnEast;
	private JPanel pnCenterr;
	private JPanel pnCenNorth;
	private JPanel pnCenCen;
	private JButton btnAdd;
	private VentanaArticulo vA;
	private JButton btnBuy;
	private VentanaCompra vComp;
	private HelpBroker hb;
	private HelpSet hs;
	private ResourceBundle mensajes;

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VentanaCarro(VentanaPrincipal vPr) {
		inter(vPr.getLocale());
		setTitle(mensajes.getString("texto1"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCarro.class.getResource("/img/Logo.jpg")));
		setBounds(100, 100, 565, 467);
		vP=vPr;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(30, 144, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnEast(), BorderLayout.EAST);
		contentPanel.add(getPnCenterr(), BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(30, 144, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"carro", hs);
	}
	public VentanaCarro(VentanaArticulo ventanaArticulo) {
		vA=ventanaArticulo;
		vP=vA.getVp();
		inter(vP.getLocale());
		setTitle(mensajes.getString("texto1"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCarro.class.getResource("/img/Logo.jpg")));
		setBounds(100, 100, 565, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(30, 144, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnEast(), BorderLayout.EAST);
		contentPanel.add(getPnCenterr(), BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(30, 144, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		hb=vP.getHb();
		hs=vP.getHs();
		hb.enableHelpKey(getRootPane(),"carro", hs);
	}
	private JLabel getLblTotalPrice() {
		if (lblTotalPrice == null) {
			lblTotalPrice = new JLabel(mensajes.getString("texto2"));
			lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTotalPrice;
	}
	private JLabel getLblPointsEarned() {
		if (lblPointsEarned == null) {
			lblPointsEarned = new JLabel(mensajes.getString("texto3"));
			lblPointsEarned.setHorizontalAlignment(SwingConstants.CENTER);
			lblPointsEarned.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblPointsEarned;
	}
	private JTextField getTxPrice() {
		if (txPrice == null) {
			txPrice = new JTextField();
			txPrice.setBorder(null);
			txPrice.setHorizontalAlignment(SwingConstants.CENTER);
			txPrice.setFont(new Font("Tahoma", Font.BOLD, 21));
			txPrice.setText("0\u20AC");
			txPrice.setBackground(new Color(30, 144, 255));
			txPrice.setEditable(false);
			txPrice.setColumns(10);
			if(vP.getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto10")+vP.getShop().getCartHandler().getPrecio()));
			else txPrice.setText(String.valueOf(vP.getShop().getCartHandler().getPrecio())+mensajes.getString("texto10")); 
		}
		return txPrice;
	}
	private JTextField getTxPoints() {
		if (txPoints == null) {
			txPoints = new JTextField();
			txPoints.setEditable(false);
			txPoints.setHorizontalAlignment(SwingConstants.CENTER);
			txPoints.setText("0");
			txPoints.setFont(new Font("Tahoma", Font.BOLD, 20));
			txPoints.setBackground(new Color(30, 144, 255));
			txPoints.setBorder(null);
			txPoints.setColumns(10);
			txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()));
		}
		return txPoints;
	}
	private JLabel getLblShoppingCart() {
		if (lblShoppingCart == null) {
			lblShoppingCart = new JLabel(mensajes.getString("texto4"));
			lblShoppingCart.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblShoppingCart;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList_1());
		}
		return scrollPane;
	}
	private JList<String> getList_1() {
		if (list == null) {
			listModel = new DefaultListModel<String>();	
			list = new JList<String>(listModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			List<Articulo> lA= vP.getShop().getCartHandler().getCartArt();
			for(Articulo a:lA){
				String linea = a.getString(vP.getLocale());
				listModel.addElement(linea);
			}

		}
		return list;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("");
			btnEliminar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						String art = (String)list.getSelectedValue();
						int row = list.getSelectedIndex();
						if(row!=-1){
							List<Articulo> lA= vP.getShop().getCartHandler().getCartArt();
							String[] info = art.split(";");
							try{
								for(Articulo a:lA){
									if(info[0].equals(a.getDenom())){
										if(a.getUnidades()>1){
											a.setUnidades(a.getUnidades()-1);
											listModel.removeElement(art);
											listModel.addElement(a.getString(vP.getLocale()));
											vP.getShop().getCartHandler().setCartArt(lA);
											vP.getShop().getCartHandler().reiniciarCarro();
										}
										else{
											lA.remove(a);
											vP.getShop().getCartHandler().setCartArt(lA);
											vP.getShop().getCartHandler().reiniciarCarro();
											listModel.removeElement(art);
										}
										if(lA.isEmpty()){
											txPrice.setText("0");
											txPoints.setText("0");
											break;
										}
									}
								}
								if(vA!=null){
									vA.getSpinner().setEnabled(true);
									int stock =vP.getShop().getStock(vA.getArticle());
									vA.getLblStock().setText(mensajes.getString("texto6")+String.valueOf(stock));
									if(stock==0){
										vA.getSpinner().setEnabled(false);
									}
									else{
										vA.getSpinner().setModel(new SpinnerNumberModel(1, 1, stock, 1));
									}
								}
							}catch(Exception exc){}
							if(vP.getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto10")+vP.getShop().getCartHandler().getPrecio()));
							else txPrice.setText(String.valueOf(vP.getShop().getCartHandler().getPrecio())+mensajes.getString("texto10")); 
							txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()));
						}
					}
				}
			});
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(30, 144, 255));
			btnEliminar.setBounds(0, 0, 50, 50);
			btnEliminar.setToolTipText(mensajes.getString("texto5"));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String art = (String)list.getSelectedValue();
					int row = list.getSelectedIndex();
					if(row!=-1){
						List<Articulo> lA= vP.getShop().getCartHandler().getCartArt();
						String[] info = art.split(";");
						try{
							for(Articulo a:lA){
								if(info[0].equals(a.getDenom())){
									if(a.getUnidades()>1){
										a.setUnidades(a.getUnidades()-1);
										listModel.removeElement(art);
										listModel.addElement(a.getString(vP.getLocale()));
										vP.getShop().getCartHandler().setCartArt(lA);
										vP.getShop().getCartHandler().reiniciarCarro();
									}
									else{
										lA.remove(a);
										vP.getShop().getCartHandler().setCartArt(lA);
										vP.getShop().getCartHandler().reiniciarCarro();
										listModel.removeElement(art);
									}
									lA= vP.getShop().getCartHandler().getCartArt();
									if(lA.isEmpty()) break;
								}
								if(vP.getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto10")+vP.getShop().getCartHandler().getPrecio()));
								else txPrice.setText(String.valueOf(vP.getShop().getCartHandler().getPrecio())+mensajes.getString("texto10")); 
								txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()));
							}
							if(vA!=null){
								vA.getSpinner().setEnabled(true);
								int stock =vP.getShop().getStock(vA.getArticle());
								vA.getLblStock().setText(mensajes.getString("texto6")+String.valueOf(stock));
								if(stock==0){
									vA.getSpinner().setEnabled(false);
								}
								else{
									vA.getSpinner().setModel(new SpinnerNumberModel(1, 1, stock, 1));
								}
							}
						}catch(Exception e){}
					}
				}
			});
			vP.setImagenAdaptada(btnEliminar,"/img/trash.jpg");
		}
		return btnEliminar;
	}
	private JPanel getPnEast() {
		if (pnEast == null) {
			pnEast = new JPanel();
			pnEast.setBackground(new Color(30, 144, 255));
			pnEast.setLayout(new GridLayout(0, 1, 0, 5));
			pnEast.add(getBtnAdd());
			pnEast.add(getBtnEliminar());
			pnEast.add(getLblTotalPrice());
			pnEast.add(getTxPrice());
			pnEast.add(getLblPointsEarned());
			pnEast.add(getTxPoints());
			pnEast.add(getBtnBuy());
		}
		return pnEast;
	}
	private JPanel getPnCenterr() {
		if (pnCenterr == null) {
			pnCenterr = new JPanel();
			pnCenterr.setBackground(new Color(30, 144, 255));
			pnCenterr.setLayout(new BorderLayout(0, 0));
			pnCenterr.add(getPnCenNorth(), BorderLayout.NORTH);
			pnCenterr.add(getPnCenCen(), BorderLayout.CENTER);
		}
		return pnCenterr;
	}
	private JPanel getPnCenNorth() {
		if (pnCenNorth == null) {
			pnCenNorth = new JPanel();
			pnCenNorth.setBackground(new Color(30, 144, 255));
			pnCenNorth.add(getLblShoppingCart());
		}
		return pnCenNorth;
	}
	private JPanel getPnCenCen() {
		if (pnCenCen == null) {
			pnCenCen = new JPanel();
			pnCenCen.setLayout(new BorderLayout(0, 0));
			pnCenCen.add(getScrollPane_1());
		}
		return pnCenCen;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("");
			btnAdd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						String art = (String)list.getSelectedValue();
						int row = list.getSelectedIndex();
						if(row!=-1){
							List<Articulo> lA= vP.getShop().getCartHandler().getCartArt();
							String[] info = art.split(";");
							try{
								for(Articulo a:lA){
									if(info[0].equals(a.getDenom())){
										if(a.getUnidades()+1<=a.getStock()){
											a.setUnidades(a.getUnidades()+1);
											listModel.removeElement(art);
											listModel.addElement(a.getString(vP.getLocale()));
											vP.getShop().getCartHandler().setCartArt(lA);
											vP.getShop().getCartHandler().reiniciarCarro();
											lA= vP.getShop().getCartHandler().getCartArt();
										}
										else{
											JOptionPane.showMessageDialog(null, mensajes.getString("texto8"));
										}
										if(lA.isEmpty()) break;
									}
									if(vP.getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto10")+vP.getShop().getCartHandler().getPrecio()));
									else txPrice.setText(String.valueOf(vP.getShop().getCartHandler().getPrecio())+mensajes.getString("texto10")); 
									txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()));
								}
								if(vA!=null){
									int stock =vP.getShop().getStock(vA.getArticle());
									vA.getLblStock().setText(mensajes.getString("texto6")+String.valueOf(stock));
									if(stock==0){
										vA.getSpinner().setEnabled(false);
									}
									else{
										vA.getSpinner().setModel(new SpinnerNumberModel(1, 1, stock, 1));
									}
								}	
							}catch(Exception exc){}
						}
					}
				}
			});
			btnAdd.setToolTipText(mensajes.getString("texto7"));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String art = (String)list.getSelectedValue();
					int row = list.getSelectedIndex();
					if(row!=-1){
						List<Articulo> lA= vP.getShop().getCartHandler().getCartArt();
						String[] info = art.split(";");
						try{
							for(Articulo a:lA){
								if(info[0].equals(a.getDenom())){
									if(a.getUnidades()+1<=a.getStock()){
										a.setUnidades(a.getUnidades()+1);
										listModel.removeElement(art);
										listModel.addElement(a.getString(vP.getLocale()));
										vP.getShop().getCartHandler().setCartArt(lA);
										vP.getShop().getCartHandler().reiniciarCarro();
										lA= vP.getShop().getCartHandler().getCartArt();
									}
									else{
										JOptionPane.showMessageDialog(null, mensajes.getString("texto8"));
									}
									if(lA.isEmpty()) break;
								}
								if(vP.getLocale()==Locale.ENGLISH)txPrice.setText(String.valueOf(mensajes.getString("texto10")+vP.getShop().getCartHandler().getPrecio()));
								else txPrice.setText(String.valueOf(vP.getShop().getCartHandler().getPrecio())+mensajes.getString("texto10")); 
								txPoints.setText(String.valueOf(vP.getShop().getCartHandler().getPoints()));
							}
							if(vA!=null){
								int stock =vP.getShop().getStock(vA.getArticle());
								vA.getLblStock().setText(mensajes.getString("texto6")+String.valueOf(stock));
								if(stock==0){
									vA.getSpinner().setEnabled(false);
								}
								else{
									vA.getSpinner().setModel(new SpinnerNumberModel(1, 1, stock, 1));
								}
							}	
						}catch(Exception e){}
					}
				}
			});
			btnAdd.setBorder(null);
			btnAdd.setBackground(new Color(30, 144, 255));
			btnAdd.setBounds(0,0,50,50);
			vP.setImagenAdaptada(btnAdd, "/img/add.jpg");
		}
		return btnAdd;
	}
	
	
	private JButton getBtnBuy() {
		if (btnBuy == null) {
			btnBuy = new JButton(mensajes.getString("texto9"));
			if(vP.getLocale()==Locale.ENGLISH) btnBuy.setMnemonic('B');
			else btnBuy.setMnemonic('C');
			btnBuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					abrirVentanaCompra();
				}
			});
			btnBuy.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnBuy;
	}
	protected void abrirVentanaCompra() {
		vComp  = new VentanaCompra(vP);
		vComp.setVisible(true);
		vComp.setLocationRelativeTo(this);
		dispose();
	}
	
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosVCart",localizacion); 
	}

}
