import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class csvLoadData {

    private String table;
    
    public  csvLoadData(String table) {
        this.table = table;
    }
    

    public void loadData() {
        
        //jdcbc Java database connectivity, enables LOAD DATA function for MariaDb
        String databaseurl="jdbc:mysql://localhost:3306/casestudy?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowLoadLocalInfile=true";
        String username="root";
        String password="";
    
        Connection connection=null;
        PreparedStatement statement = null;

        try{
            // connect to the mysql database
            connection = DriverManager.getConnection(databaseurl, username, password);
            connection.setAutoCommit(false);

            // String path = System.getProperty("user.dir");
            // System.out.println(path);

            // prepare the sql command to execute on the database
            String sql =  "LOAD DATA LOCAL INFILE '../InterviewCaseStudy/"+ table +".csv' REPLACE " + 
                          "INTO TABLE "+ table + " " +
                          "FIELDS TERMINATED BY ','" +
                          " LINES TERMINATED BY '\\n' " +
                          " IGNORE 1 LINES;"
                        ;              
            
            statement=connection.prepareStatement(sql); 
            statement.addBatch(); 

            // execute the sql command
            statement.executeBatch();
 
            // close the database connection
            connection.commit();
            connection.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
