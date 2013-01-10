package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WinWait extends JDialog implements MouseListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public WinWait(String mess, final int time) {
		super(Game.getInstance(), true);
		
		//setModal(true);
		setBounds(Game.getInstance().getBounds());
		setResizable(false);
		setUndecorated(true);

		// Fond d'écran
		JLabel background = new JLabel();
		background.setIcon(Game.BACKGROUND);
		FlowLayout layBack = new FlowLayout();
		layBack.setVgap(0);
		background.setLayout(layBack);
		

		
		JPanel infoTemp = new JPanel();
		infoTemp.setBounds(0, 0, 1274, 740);
		infoTemp.setPreferredSize(new Dimension(1274, 740));
		infoTemp.setOpaque(false);
		
		JLabel labInfoTemp = new JLabel("<html>" + mess.replaceAll("\n", "<br>") + "</html>");
		labInfoTemp.setForeground(Color.WHITE);
		labInfoTemp.setFont(new Font("sherif", 0, 60));
		labInfoTemp.setVerticalAlignment(JLabel.CENTER);
		labInfoTemp.setHorizontalAlignment(JLabel.CENTER);
		labInfoTemp.setPreferredSize(new Dimension(1274, 740));
		labInfoTemp.setOpaque(false);
		
		infoTemp.add(labInfoTemp);

		setContentPane(background);
		
		getContentPane().add(infoTemp);
		getContentPane().addMouseListener(this);
		
		final JDialog that = this;
		
		/*SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				try {
					Thread.sleep(2000);
				}
				catch (Exception e) {}
				
				that.setVisible(false);
			}
		});*/
		
		new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(time);
				}
				catch (Exception e) {}
				
				that.setVisible(false);
			}
		}).start();
		
		
		setVisible(true);
	}

	public void mousePressed(MouseEvent arg0) {
		setVisible(false);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		setVisible(false);
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
