package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WinWarn extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WinWarn(String mess) {
		super(Game.getInstance(), false);
		//setModal(true);
		setBounds(Game.getInstance().getBounds().x+(1274-500), Game.getInstance().getBounds().y+33, 500, 30);
		setResizable(false);
		setUndecorated(true);
		

		// Fond d'écran
		JLabel background = new JLabel();
		background.setBackground(Color.darkGray);
		FlowLayout layBack = new FlowLayout();
		layBack.setVgap(0);
		background.setLayout(layBack);
		background.setOpaque(true);
		
		

		
		JPanel infoTemp = new JPanel();
		infoTemp.setBounds(0, 0, 500, 30);
		infoTemp.setPreferredSize(new Dimension(500, 30));
		infoTemp.setOpaque(false);
		
		JLabel labInfoTemp = new JLabel(mess);
		labInfoTemp.setForeground(Color.RED);
		labInfoTemp.setFont(new Font("sherif", 0, 15));
		labInfoTemp.setVerticalAlignment(JLabel.CENTER);
		labInfoTemp.setHorizontalAlignment(JLabel.CENTER);
		labInfoTemp.setPreferredSize(new Dimension(500, 30));
		labInfoTemp.setOpaque(false);
		
		infoTemp.add(labInfoTemp);

		setContentPane(background);
		
		final JDialog that = this;
		
		getContentPane().add(infoTemp);
		
		new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(2000);
				}
				catch (Exception e) {}
				
				that.setVisible(false);
				
			}
		}).start();
		
		setVisible(true);
	}
	
	


}
