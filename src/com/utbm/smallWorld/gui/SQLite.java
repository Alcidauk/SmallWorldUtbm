/*
 * SQLLite.java
 * 23/05/2012 - Sylob
 */
package com.utbm.smallWorld.gui;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Territoire;

/**
 * Classe de communication avec la base SQLLite
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class SQLite {
	/** Fichier de la bdd */
	private static final String FILENAME = "SmallWorldUTBM.db";
	
	/** Tunnel de communication avec informix */
	private static Connection conn;
	
	/** Connexion */
	static {
		try {
			connect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	/**
	 * Constructeur: Connexion à la base de données
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		return conn = DriverManager.getConnection("jdbc:sqlite:" + FILENAME);
	}
	
	
	/**
	 * Déconnecte le tunnel ouvert
	 * @throws SQLException 
	 */
	public static void disconnect() throws SQLException {
		conn.close();
	}
	
	
	/**
	 * @return La connexion avec la bdd
	 */
	public static Connection conn() {
		return conn;
	}
	
	
	/**
	 * Créé la liste des territoires d'après les tables de la bdd
	 * @param nbPlayer Plateau à utiliser
	 * @return Liste des TerritoireCases avec les Territoires à l'intérieur
	 */
	public static List<TerritoireCase> createTerritoires(int nbPlayer) {
		List<TerritoireCase> terris = new LinkedList<TerritoireCase>();
		
		try {
			// Creation des territoires
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM territoire WHERE plateau = ?");
			ps.setInt(1, nbPlayer);
			
			ResultSet rs = ps.executeQuery();
			
			HashMap<Integer, Territoire> territoires = new HashMap<Integer, Territoire>();
			
			while (rs.next()) {
				Rectangle r = new Rectangle(rs.getInt("x"), rs.getInt("y"), rs.getInt("w"), rs.getInt("h"));
				TerritoireCase tc = new TerritoireCase(r);
				
				Territoire t = new Territoire();
				t.setEnBordure(rs.getBoolean("enbordure"));
				t.setNbUnite(rs.getInt("tribuoubliee"));
				//t.setNom(rs.getString("name")); TODO : Virer l'attribut dans la DB / ajouter l'attribut dans le classe
				
				tc.setTerritoire(t);
				
				territoires.put(rs.getInt("uid"), t);
				terris.add(tc);
			}
			
			rs.close();
			ps.close();
			
			// Creation des adjacences
			ps = conn.prepareStatement("SELECT * FROM adjacence WHERE plateau = ?");
			ps.setInt(1, nbPlayer);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Territoire t1 = territoires.get(rs.getInt("uid_t1"));
				Territoire t2 = territoires.get(rs.getInt("uid_t2"));
				
				t1.addTerritoireAdjacent(t2);
				t2.addTerritoireAdjacent(t1);
			}
			
			rs.close();
			ps.close();
			
			// TODO éléments
			ps = conn.prepareStatement("SELECT * FROM elements WHERE plateau = ?");
			ps.setInt(1, nbPlayer);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Territoire t = territoires.get(rs.getInt("territoire"));
				String elm = rs.getString("nom");
				
				Class<?> elmClass = Element.ELEMENTS.get(elm);
				
				if (elmClass != null) {
					t.addElement((Element) elmClass.getConstructor(Territoire.class).newInstance(t)); // TODO : Simplifier en supprimant l'argument du constructeur ?
				}
			}
			
			rs.close();
			ps.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return terris;
	}
}
