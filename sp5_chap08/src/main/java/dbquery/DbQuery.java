package dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQuery {
    private DataSource dataSource;

    // DataSource타입의 dataSource arg를 받는 DbQuery에 받은 인자값으로 내부에 선언한 private한 dataSource값을 변경한다.
    public DbQuery(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // 반환값이 int인 count선언
    // Connection값을 null로 비워놓은채 열어놓고 dataSource
    public int count() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
