//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
//import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        Connection conn = Database.getInstance().getConnection();

        try (Statement statement = conn.createStatement()){
            String sql = Files.readString(Path.of("sql/init_db.sql"));
            statement.executeUpdate(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
