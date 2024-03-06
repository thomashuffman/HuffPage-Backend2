package com.backend.huffPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class HuffPageConnection
{
  private static final String INSERT_NOTE_SQL = "INSERT INTO notes" +
      " (noteid, notedata, notetype, note_uuid, notesport) VALUES " +
      " (?, ?, ?, ?, ?);";
  
  private static final String INSERT_SLIDER_SQL = "INSERT INTO slider" +
	      " (slider_uuid, id, slidervalue, type, sport) VALUES " +
	      " (?, ?, ?, ?, ?);";
  
  private static final String UPDATE_SLIDER_SQL = "UPDATE slider SET slidervalue = ? WHERE id = ? AND sport = ?";
  
  private static final String SELECT_SLIDER_WHERE_SQL = "SELECT * FROM slider WHERE id = ? AND sport = ?";
  
  private static final String DELETE_NOTE_SQL = "DELETE FROM notes WHERE note_uuid = ?";
  
  private static final String GET_ALL_NOTES_SQL = "SELECT * FROM notes WHERE notesport = ?";
  
  private static final String GET_ALL_SLIDERS_SQL = "SELECT * FROM slider WHERE sport = ?";
  
  private static final String GET_GAMEINFO_SQL = "SELECT * FROM gameinfo WHERE sport = ?";
  
  private static final String GET_VOTES = "SELECT slidervalue FROM slider WHERE sport = ? AND id = ?";
  
  private static final String GET_NBAINJURIES_SQL = "SELECT * FROM nbainjuries";
  
  private static final String GET_NBAMODEL_SQL = "SELECT * FROM nbamodel";
  
  public static Connection connect(){
    Connection conn = null;
    //String url = "jdbc:sqlserver://localhost:5433;databaseName=huff2;user=postgres;password=PlaceHolder";
    String url = "jdbc:mysql://localhost:3306/javabase";
    String user = "host";
//    Class.forName("com.mysql.jdbc.Driver");
    try {
//    	try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	conn = DriverManager.getConnection("jdbc:mysql://huffpagemysqldb.cns6vavz3swx.us-east-1.rds.amazonaws.com:3306/huffpagemysqldb",
                "root", "testdbpassword");
        System.out.println("Connected to the PostgreSQL server successfully.");
    } catch (SQLException e) {
        System.out.println("CANNOT CONNECT TO DATABASE" + e.getMessage());
    }

    return conn;
  }
  
  public static String getGameInfo(String sport) throws Exception {
	    Connection connection = connect();
	    PreparedStatement preparedStatement = connection.prepareStatement(GET_GAMEINFO_SQL);
	    //  String s=String.valueOf(l);
	    preparedStatement.setString(1, sport);
	    System.out.println(preparedStatement);
	    // Step 3: Execute the query or update query
	    ResultSet rs = preparedStatement.executeQuery();
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    String gameText = "";
	    while (rs.next()) {
//	        for (int i = 1; i <= columnsNumber; i++) {
//	            if (i > 1) System.out.print(",  ");
//	            String columnValue = rs.getString(i);
//	            System.out.print(columnValue + " " + rsmd.getColumnName(i));
//	        }
//	        System.out.println("");
	    	gameText = rs.getString(1);
	    }
	   // System.out.println(gameText);
	    return gameText;
  }
  public static JSONArray getInjuries() throws Exception{
      
	    Connection connection = connect();
	    PreparedStatement preparedStatement = connection.prepareStatement(GET_NBAINJURIES_SQL);
	    
	    //System.out.println(preparedStatement);
	    return convertToJSON(preparedStatement.executeQuery());
  }
  
  public static JSONArray getNBAModel() throws Exception{
      
	    Connection connection = connect();
	    PreparedStatement preparedStatement = connection.prepareStatement(GET_NBAMODEL_SQL);
	    
	    //System.out.println(preparedStatement);
	    return convertToJSON(preparedStatement.executeQuery());
}
  
  public static void addNote(String noteid, String notedata, String notetype, String notesport) throws SQLException, ClassNotFoundException{
        Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTE_SQL);
//        String s=String.valueOf(l);
        UUID uuid = UUID.randomUUID();
        
        preparedStatement.setString(1, noteid);
        preparedStatement.setString(2, notedata);
        preparedStatement.setString(3, notetype);
        preparedStatement.setString(4, uuid.toString());
        preparedStatement.setString(5, notesport);

        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        preparedStatement.executeUpdate();
  }
  public static void deleteNote(String note_uuid) throws SQLException, ClassNotFoundException{
    Connection connection = connect();
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTE_SQL);
    System.out.println(note_uuid);
//    String s=String.valueOf(l);
    UUID uid = UUID.fromString(note_uuid);
    preparedStatement.setObject(1, note_uuid);
    System.out.println(preparedStatement);
    // Step 3: Execute the query or update query
    preparedStatement.executeUpdate();
}
  
  public static void addSlider(String noteid, String notedata, String notetype, String notesport) throws SQLException, ClassNotFoundException{
	    Connection connection = connect();
	    PreparedStatement preparedStatementSelect = connection.prepareStatement(SELECT_SLIDER_WHERE_SQL);
	    preparedStatementSelect.setString(1, noteid);
	    preparedStatementSelect.setString(2, notesport);
	    ResultSet rs =  preparedStatementSelect.executeQuery();
	    System.out.println(rs);
//	    if(!rs.next()) {
		    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SLIDER_SQL);
		    System.out.println(noteid);
		    UUID uuid = UUID.randomUUID();
	//	    String s=String.valueOf(l);
		    preparedStatement.setString(1, uuid.toString());
	        preparedStatement.setString(2, noteid);
	        preparedStatement.setString(3, notedata);
	        preparedStatement.setString(4, notetype);
	        preparedStatement.setString(5, notesport);
		    System.out.println(preparedStatement);
		    // Step 3: Execute the query or update query
		    preparedStatement.executeUpdate();
//	    }else {
//	    	PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_SLIDER_SQL);
//	    	preparedStatementUpdate.setString(1, notedata);
//	        preparedStatementUpdate.setString(2, noteid);
//	        preparedStatementUpdate.setString(3, notesport);
//	        
//	        System.out.println(preparedStatementUpdate);
//	        preparedStatementUpdate.executeUpdate();
//	        
//	    }
	}
  
  public static JSONArray getAllSliders(String sport) throws Exception
  {
    Connection connection = connect();
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SLIDERS_SQL);
    //  String s=String.valueOf(l);
    preparedStatement.setString(1, sport);
    System.out.println(preparedStatement);
    // Step 3: Execute the query or update query
    return convertToJSON(preparedStatement.executeQuery());
    
  }
  
  public static JSONArray getAllNotes(String notesport) throws Exception
  {
    Connection connection = connect();
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NOTES_SQL);
    //  String s=String.valueOf(l);
    preparedStatement.setString(1, notesport);
    System.out.println(preparedStatement);
    // Step 3: Execute the query or update query
    return convertToJSON(preparedStatement.executeQuery());
    
  }
  
  public static JSONArray convertToJSON(ResultSet resultSet)
      throws Exception {
      JSONArray jsonArray = new JSONArray();
      while (resultSet.next()) {
      int total_columns = resultSet.getMetaData().getColumnCount();
      JSONObject obj = new JSONObject();
      for (int i = 0; i < total_columns; i++) {
          obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
      }
    jsonArray.put(obj);
  }
  return jsonArray;
}

public static int[] getVotes(String slidersport, String index) throws Exception {
	List<String> values = new ArrayList<String>();
	int[] ret = new int[2];
	int retVal = 0;
	Connection connection = connect();
    PreparedStatement preparedStatement = connection.prepareStatement(GET_VOTES);
    //  String s=String.valueOf(l);
    preparedStatement.setString(1, slidersport);
    preparedStatement.setString(2, index);
    System.out.println(preparedStatement);
    ResultSet rs = preparedStatement.executeQuery();
    while (rs.next()) {
        values.add(rs.getString(1));
    }
    for(int i = 0; i < values.size(); i++) {
    	int j = Integer.parseInt(values.get(i));  
    	retVal+=j;
    }
    if(values.size()>0) {
    	ret[0] = retVal/values.size();
    	ret[1] = values.size();
    	return ret;
    }else {
    	return new int[]{50, 0};
    }
}
}
