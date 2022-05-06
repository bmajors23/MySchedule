package Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This method serves as a query to perform sql statements on the users of the sql database
 *
 */
public class UsersQuery {

    /** This method determines if a particular user exists based on a userID provided
     * @param userID
     * @return
     * @throws SQLException
     */
    public static boolean selectExists(int userID) throws SQLException {
        String sql = "SELECT * FROM Users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

}
