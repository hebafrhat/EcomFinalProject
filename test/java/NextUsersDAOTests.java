import DDT.NextUsers;
import DDT.NextUsersDAO;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class NextUsersDAOTests {
    public static final String url = "jdbc:sqlite:C:\\Users\\a\\Desktop\\final project\\SqliteFinalProject\\SqliteFinalProject.db";
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeClass
    public static void setUp() throws SQLException {
        con = DriverManager.getConnection(url);
    }


    @Test
    public void IsEmailValid() {
        NextUsers NU = new NextUsers("miss","alma","frhat","alma@gmail.com","ffd6577998",111111111, "sd",00,"haifa","hazfon",12437,"israel");
        String S = NU.getemail();
        assertEquals(NU.getemail(), NextUsers.IsEmailValid(S));
    }

    @Test
    public void IsPasswordIsStrong() {
        NextUsers NU = new NextUsers("miss","alma","frhat","alma@gmail.com","ffd6577998",111111111, "sd",00,"haifa","hazfon",12437,"israel");
        String S = NU.getPassword();
        assertEquals(NU.getPassword(), NextUsers.passwordIsStrong(S));
    }


    @Test
    void creatingNewDataBase() {
        Connection con;
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                String sql = "DROP TABLE IF EXISTS NextUsersDAO";
                Statement stm = con.createStatement();
                stm.executeUpdate(sql);
                ResultSet rs = con.getMetaData().getTables(null, null,"NextUsers1", null);
                assertTrue(rs.next());
                assertEquals("SqliteFinalProject", rs.getDate("TABLE_NAME"));
                assertFalse(rs.next());
            }
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        ArrayList<NextUsers> nextUsers = NextUsersDAO.getAllNextUsers();
        if (nextUsers.size() > 0) {
            System.out.println(nextUsers);
        } else System.out.println("no users found");
    }


    @Test
    public void testDeleteFromTable() throws SQLException {
        NextUsers NextUsersDAO1 = new NextUsers("miss","alma","frhat","alma@gmail.com","ffd6577998",111111111, "sd",00,"haifa","hazfon",12437,"israel");
        con = DriverManager.getConnection(url);
        NextUsersDAO.DeleteUserFromTable(NextUsersDAO1);
        String query = "delete (*) FROM NextUsersDAO2 WHERE email = 'alma@gmail.com'";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        assertEquals(0, count);
    }

    @Test
    public void testUpdateTable(NextUsers NU) throws SQLException {
        String update = "UPDATE NextUsersDAO2 SET title='?', firstName='?', lastName='?', password='?', phone=?, apartment='?', houseNumber=?, city='?', state='?', zipCode=?, country='?' WHERE  email='?'";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(update);
            st.setString(1, NU.getTitle());
            st.setString(2, NU.getFirstName());
            st.setString(3, NU.getLastName());
            st.setString(4, NU.getemail());
            st.setString(5, NU.getPassword());
            st.setInt(6, NU.getPhone());
            st.setString(7, NU.getApartment());
            st.setInt(8, NU.getHouseNumber());
            st.setString(11, NU.getCity());
            st.setString(12, NU.getState());
            st.setInt(13, NU.getzipCode());
            st.setString(14, NU.getCountry());
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        String selectQuery = "SELECT * FROM NextUsersDAO2 WHERE email = " + NU.getemail() ;
        PreparedStatement selectPst = con.prepareStatement(selectQuery);
        ResultSet rs = selectPst.executeQuery();
        rs.next();
        assertEquals("title", rs.getString("title"));
        assertEquals("FirstName", rs.getString("firstName"));
        assertEquals("lastName", rs.getString("lastName"));
        assertEquals("email", rs.getString("email"));
        assertEquals("password", rs.getString("password"));
        assertEquals("phone", rs.getInt("phone"));
        assertEquals("apartment", rs.getString("apartment"));
        assertEquals("houseNumber", rs.getInt("houseNumber"));
        assertEquals("city", rs.getString("city"));
        assertEquals("state", rs.getInt("state"));
        assertEquals("zipCode", rs.getInt("zipCode"));
        assertEquals("country", rs.getString("country"));
        String updateQuery = "update (*) FROM NextUsersDAO2 WHERE email = " + NU.getemail() ;
        PreparedStatement updatePst = con.prepareStatement(updateQuery);
        updatePst.executeUpdate();
    }

}
