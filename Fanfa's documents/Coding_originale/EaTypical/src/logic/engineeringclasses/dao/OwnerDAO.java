package logic.engineeringclasses.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.engineeringclasses.exceptions.AlreadyInUseUsernameException;
import logic.engineeringclasses.exceptions.LoginDBException;
import logic.engineeringclasses.others.Connect;
import logic.engineeringclasses.query.QueryLogin;
import logic.model.Owner;
import logic.model.User;

public class OwnerDAO {
	
	private OwnerDAO() {}
    
    

    public static User selectOwner(String user, String pw) throws ClassNotFoundException, SQLException, LoginDBException 
    {
        Statement stmt = null;
        Connection conn = null;
        User owner;
        String name;
        String surname;
        String username;
        try {
			conn = Connect.getInstance().getDBConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = QueryLogin.loginOwner(stmt, user, pw);		//get the owner data from the database
            if(!rs.first())   //if empty, throw exception, handled by the graphic controller
            {
            	throw new LoginDBException(0);		
            }
            rs.first();
            name=rs.getString("Nome");
            surname=rs.getString("Cognome");
            username=rs.getString("Username");   
        	} finally {	      	
                if (stmt != null)
                    stmt.close();
        	}         
        owner = new Owner(name, surname, username); //use the factory to return a owner object
        return owner;
    }
    
    

    
    //insert a user owner in the db
    public static void insertOwner(User user, String pw) throws ClassNotFoundException, SQLException, AlreadyInUseUsernameException {
        Statement stmt = null;
        Connection conn = null;
        
        try {            
            conn = Connect.getInstance().getDBConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            String username=user.getUsername();
            
            ResultSet rs= QueryLogin.loginOwner(stmt, username);
            if(rs.first())		//if there's already a owner  with this username, throw an exception
            {
            	throw new AlreadyInUseUsernameException("This username is already in use!");		
            }
            
            QueryLogin.registerOwner(stmt, user , pw);
        } finally {    	
                if (stmt != null)
                    stmt.close();
        }
    }
}
