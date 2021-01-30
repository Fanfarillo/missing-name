package logic.engineeringclasses.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.engineeringclasses.bean.manageMenu.BeanAddDish;
import logic.engineeringclasses.exceptions.DishAlreadyExists;
import logic.engineeringclasses.exceptions.InvalidDishDelete;
import logic.engineeringclasses.exceptions.InvalidDishModify;
import logic.engineeringclasses.query.QueryRecipe;
import logic.model.Recipe;


public class RecipeDAO {

	/*
	 * Se ho tempo, crea un file di configurazione per le credenziali
	 */
	String connectionString = "jdbc:mysql://localhost:3306/progettoispwfinaledatabase?user=root&password=Monte_2020.&serverTimezone=UTC";
	private static String driverclassname = "com.mysql.jdbc.Driver";
	
	/**
	 * Instaura la connessione al DBMS e richiede la lettura dei possibili piatti tipici
	 * @param username 
	 * @throws ClassNotFoundException 
	 * 
	 */
	
	public List<String> selectAllRecipe() throws ClassNotFoundException
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		ArrayList<String> obs = new ArrayList<>();
		
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
			
			
			//creazione ed esecuzione dell'eliminazione
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);	
			
			
			rs = QueryRecipe.selectDish(stmt);
				
			//scansiono i risultati
			rs.first();
			String recipe;
			do {
				recipe = rs.getString(1);
				obs.add(recipe);
			}
			while(rs.next());
				
			
			
			
		} catch (SQLException e) {				
			e.printStackTrace();
		}finally {
			try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
		}
		
		return obs;
	}
	
	/**
	 * Instaura la connessione al DBMS e richiede l'eliminazione del piatto identificato dai parametri
	 * @param nomeRistorante
	 * @param nomePiatto
	 * @throws ClassNotFoundException 
	 * @throws InvalidDishDelete 
	 * 
	 */
	
	public void deleteRecipe(String nomeRistorante, String nomePiatto) throws ClassNotFoundException, InvalidDishDelete {
		
		
		Connection conn = null;
	
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
						
			
			QueryRecipe.deleteDish(conn, nomeRistorante, nomePiatto);
				
			
			
			
		} catch (SQLException e) {	
			throw new InvalidDishDelete(nomePiatto, nomeRistorante);
		}finally {
			
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
		}
		
	}
	
	/**
	 * Instaura la connessione al DBMS e richiede l'aggiunta del piatto identificato dai parametri
	 * @param nomeRistorante
	 * @param nomePiatto
	 * @throws ClassNotFoundException 
	 * 
	 */
	
	
	public void addDish(Recipe recipe) throws ClassNotFoundException, DishAlreadyExists {	
		Connection conn = null;	
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
			
			//eseguo l'inserimento
			
			
			QueryRecipe.addDish(conn, recipe.getDishName(), recipe.getRestaurant(), recipe.getContenuto(), recipe.getPrice(), recipe.isVegan(),recipe.isCeliac());
			
			
			
		} catch (SQLException e) {		
			
			//lancio l'eccezione per dire che il piatto ÃƒÆ’Ã‚Â¨ stato giÃƒÆ’Ã‚Â  inserito in precedenza
			throw new DishAlreadyExists(recipe.getDishName());
			
		}finally {
			
			//chiudo la connessione al DBMS
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
	}
	
	/**
	 * Instaura la connessione al DBMS e richiede la lettura delle ricette dei ristoranti di un proprietario
	 * @param nomeRistorante
	 * @param nomePiatto
	 * @throws ClassNotFoundException 
	 * 
	 */
	
	public List<String> selectOwnRecipe(String username)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		ArrayList<String> obs = new ArrayList<>();
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
			
			
			//creazione ed esecuzione dell'eliminazione
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);	
			
			
			rs = QueryRecipe.selectOwnDish(stmt, username);
				
			
				
			
			//scansiono i risultati
			rs.first();
			String recipe;
			do {
				recipe = rs.getString(1);
				obs.add(recipe);
			}
			while(rs.next());
				
			
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
		}
		
		return obs;
	}
	
	
	public void updateDishes(BeanAddDish beanAddDish) throws ClassNotFoundException,  InvalidDishModify
	{
		Connection conn = null;	
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
			
		
			QueryRecipe.updateDishes(beanAddDish.getContenuto(),beanAddDish.getRistorante(),conn,beanAddDish.getPiatto(),beanAddDish.getPrezzo(),beanAddDish.isVegano(),beanAddDish.isCeliaco());
			
			
			
			
		} catch (SQLException e) {			
			//eccezione piatto non esistente
			throw new InvalidDishModify(beanAddDish.getPiatto(), beanAddDish.getRistorante());
			
		}finally {
			
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
		}
	}
	
	/**
	 * OTTIENE LE RICETTA CHE NON SONO TRATTATE DAI RISTORANTI DELLO USER
	 */
	
	public List<String> selectNoRecipe(String username) throws ClassNotFoundException
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		ArrayList<String> obs = new ArrayList<>();
		
		
		try {
			
			//loading dinamico del driver del DBMS scelto
			Class.forName(driverclassname);
			
			//apro la connssione verso il DBMS
			conn = DriverManager.getConnection(connectionString);
			
			
			//creazione ed esecuzione dell'eliminazione
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);	
			
			
			rs = QueryRecipe.selectNoDish(stmt,username);
				
			//scansiono i risultati
			rs.first();
			String recipeMancante;
			do {
				recipeMancante = rs.getString(1);
				obs.add(recipeMancante);
			}
			while(rs.next());
				
			
			
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally {
			try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
		}
		
		return obs;
	}
	
}