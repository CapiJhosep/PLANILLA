package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import model.Empleado;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Planillas extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	JLabel lblMensaje, lblEmpleado, lblPC, lblIP, lblFecha, lblHora;
	JButton btnTrabajadores, btnPlanillas, btnInformes, btnConfiguraciones, btnUtiliarios;
	
	Thread thHora;
	Empleado empleado = new Empleado();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Planillas frame = new Planillas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Planillas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel pnlLogo = new JPanel();
		pnlLogo.setBounds(0, 0, 1024, 60);
		pnlLogo.setBackground(new Color(94, 17, 90));
		pnlLogo.setLayout(null);
		getContentPane().add(pnlLogo);
		
		JLabel imgLogo = new JLabel(new ImageIcon(Login.class.getResource("/ui/img/logo.png")));
		imgLogo.setBounds(10, 7, 208, 43);
		pnlLogo.add(imgLogo);

		JLabel imgSalir = new JLabel(new ImageIcon(Login.class.getResource("/ui/img/salir.png")));
		imgSalir.setBounds(980, 13, 24, 34);
		pnlLogo.add(imgSalir);

		JLabel imgFondo = new JLabel(new ImageIcon(Login.class.getResource("/ui/img/fondoApp.png")));
		imgFondo.setBounds(0, 100, 1024, 628);
		getContentPane().add(imgFondo);
		
		lblMensaje = new JLabel();
		lblMensaje.setBounds(20, 65, 1024, 39);
		getContentPane().add(lblMensaje);
		
		lblEmpleado = new JLabel("Empleado : ");
		lblEmpleado.setBounds(20, 728, 150, 40);
		getContentPane().add(lblEmpleado);
		
		lblPC = new JLabel("PC : ");
		lblPC.setBounds(300, 728, 150, 40);
		getContentPane().add(lblPC);
		
		lblIP = new JLabel("IP : ");
		lblIP.setBounds(500, 728, 150, 40);
		getContentPane().add(lblIP);
		
		lblFecha = new JLabel("Fecha : ");
		lblFecha.setBounds(700, 728, 150, 40);
		getContentPane().add(lblFecha);
		
		lblHora = new JLabel("Hora : ");
		lblHora.setBounds(900, 728, 150, 40);
		getContentPane().add(lblHora);
		
		btnTrabajadores = new JButton("TRABAJADORES");
		btnTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
		btnTrabajadores.setBounds(220, 0, 155, 60);
		btnTrabajadores.setBackground(new Color(94, 17, 90));
		btnTrabajadores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTrabajadores.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTrabajadores.setMnemonic('a');
		btnTrabajadores.setBorderPainted(false);
		btnTrabajadores.setFocusPainted(false);
		btnTrabajadores.setForeground(Color.WHITE);
		getContentPane().add(btnTrabajadores);
		
		btnPlanillas = new JButton("PLANILLAS");
		btnPlanillas.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlanillas.setBounds(375, 0, 110, 60);
		btnPlanillas.setBackground(new Color(94, 17, 90));
		btnPlanillas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPlanillas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPlanillas.setMnemonic('e');
		btnPlanillas.setBorderPainted(false);
		btnPlanillas.setFocusPainted(false);
		btnPlanillas.setForeground(Color.WHITE);
		getContentPane().add(btnPlanillas);
		
		btnInformes = new JButton("INFORMES");
		btnInformes.setHorizontalAlignment(SwingConstants.CENTER);
		btnInformes.setBounds(485, 0, 130, 60);
		btnInformes.setBackground(new Color(94, 17, 90));
		btnInformes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInformes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnInformes.setMnemonic('e');
		btnInformes.setBorderPainted(false);
		btnInformes.setFocusPainted(false);
		btnInformes.setForeground(Color.WHITE);
		getContentPane().add(btnInformes);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) { form_windowOpened(); } });
		
		imgSalir.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { imgSalir_mouseClicked(); } });
		
		btnTrabajadores.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {
				btn_mouseEntered(btnTrabajadores);
			}
			@Override public void mouseExited(MouseEvent e) {
				btn_mouseExited(btnTrabajadores);
			}
		});

		btnPlanillas.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {
				btn_mouseEntered(btnPlanillas);
			}
			@Override public void mouseExited(MouseEvent e) {
				btn_mouseExited(btnPlanillas);
			}
		});
		
	}
	
	
	public void form_windowOpened() {
		lblMensaje.setText("Hola " + empleado.getApellidoPAterno() + ", Bienvenido al sistema de planilla");
		lblEmpleado.setText(String.format("Empleado : %s %s %s", empleado.getNombres(), empleado.getApellidoPAterno(), empleado.getApellidoMAterno()));
		lblPC.setText("PC : ");
		lblIP.setText("IP : ");
		lblFecha.setText(new SimpleDateFormat("'Fecha :' dd/MM/yyyy").format(new Date()) );
		thHora = new Thread(this);
		thHora.start();
	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	protected void imgSalir_mouseClicked() {
		if (JOptionPane.showConfirmDialog(this, "¿Deseas salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
			thHora.interrupt();
			System.exit(0);
    }
	
	protected void btn_mouseEntered(JButton btn) {
		btn.setBackground(new Color(104, 27, 100));
    }

	protected void btn_mouseExited(JButton btn) {
		btn.setBackground(new Color(94, 17, 90));
	}

	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		while (true) {
			lblHora.setText( df.format( new Date() ) );
			try { Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	
}
