package DDT;

import java.sql.*;
import java.util.ArrayList;

public class NextUsersDAO {
    public static final String url = "jdbc:sqlite:C:\\Users\\a\\Desktop\\final project\\SqliteFinalProject\\SqliteFinalProject.db";
    public static final Connection con ;
    static {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void creatingNewDataBase() {
        try (Connection con=DriverManager.getConnection(url)) {
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("the driver name is =" + meta.getDriverName());
                System.out.println("a new database has been created.");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }

    public static void creatingTable(){
        Connection con;
        try {
            con = DriverManager.getConnection(url);
            if (con != null){
                String sql2 =" CREATE TABLE IF NOT EXISTS NextUsers1(title TEXT NOT NULL,firstName TEXT NOT NULL,lastName TEXT NOT NULL,email TEXT NOT NULL,password INTEGER NOT NULL ,phone INTEGER NOT NULL, apartment TEXT,houseNumber INTEGER NOT NULL, city TEXT, state TEXT, zipCode INTEGER NOT NULL, country TEXT NOT NULL)";
                Statement stm = con.createStatement();
                stm.executeUpdate(sql2);
                System.out.println("create table = NextUsers");
                System.out.println("the table is created");
            }
        }catch (SQLException E){
            throw new RuntimeException(E);
        }
    }

    public static ArrayList<NextUsers> getAllNextUsers() {
        ArrayList<NextUsers> M = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("get All Next Users");
                String sql = "select * from NextUsers1";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    NextUsers e = new NextUsers(
                            rs.getString("title"),
                            rs.getString("firstname"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("phone"),
                            rs.getString("apartment"),
                            rs.getInt("houseNumber"),
                            rs.getString("city"),
                            rs.getString("state"),
                            rs.getInt("zipCode"),
                            rs.getString("country"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(M);
        return M;
    }

    public static void insertIntoTable(NextUsers NU) throws SQLException {
        String query = "INSERT INTO NextUsers1(title,firstName,lastName,email,password,phone,apartment,houseNumber" +
                ",city,state,zipCode,country)values ('"+NU.getTitle()+"','"+NU.getFirstName()+"','"+NU.getLastName()+"','"+NU.getemail()+"','"
                +NU.getPassword()+"','"+NU.getPhone()+"','"+NU.getApartment()+"','"+NU.getHouseNumber()+"','"
                +NU.getCity()+"','"+NU.getState()+"','"+NU.getzipCode()+"','"+NU.getCountry()+"')";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
        System.out.println("inserted into the table");
        System.out.println("inserted into the table");
    }

    public static void DeleteUserFromTable(NextUsers NU) throws SQLException{
        String delete = "DELETE FROM NextUsers1 WHERE Email='alana@gmail.com'";
        Statement stm = con.createStatement();
        stm.executeUpdate(delete);
        System.out.println("User is deleted from the table...");
        System.out.println("The table is updated!");
    }

    public static void UpdateTable(NextUsers NU) {
        String update = "UPDATE NextUsers1 SET title=?, firstName='?', lastName='?', password='?', phone=?, apartment='?', houseNumber=?, city='?', state='?', zipCode=?, country='?' WHERE  email='?'";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(update);
            st.setString(1, NU.getTitle());
            st.setString(2, NU.getFirstName());
            st.setString(3, NU.getLastName());
            st.setString(4, NU.getemail());
            st.setString(5, NU.getPassword());
            st.setInt(6, NU.getPhone());
            st.setString(7,NU.getApartment());
            st.setInt(8,NU.getHouseNumber());
            st.setString(11,NU.getCity());
            st.setString(12,NU.getState());
            st.setInt(13,NU.getzipCode());
            st.setString(14,NU.getCountry());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean passwordIsStrong(String Password) {
        if (Password.length() >= 8 && Password.length() <= 15) {
            return true;
        } else
            return false;
    }

    public static void main(String[] args) throws SQLException {
        NextUsers Nu = new NextUsers("miss","alma","frhat","alma@gmail.com","ffd6577998",111111111, "sd",00,"haifa","hazfon",12437,"israel");
        NextUsers Nu1 = new NextUsers("mr","forat","frhat","forat@lookmail.com","aj6678678v",222222222,"kh",01,"Jerusalem","merkaz",12345,"israel");
        NextUsers Nu2 = new NextUsers("msr","jon","april","jon@lookmail.com","ajfree666d",233333333,"ty",02,"paris","",34224,"francr");
        NextUsers Nu3 = new NextUsers("ms","nekol","wats","neke@gmailmail.com","adg6789677",233333333,"pp",03,"london","",346264,"UKr");

        NextUsersDAO.creatingNewDataBase();
        NextUsersDAO.creatingTable();
        NextUsersDAO.insertIntoTable(Nu);
        NextUsersDAO.insertIntoTable(Nu1);
        NextUsersDAO.insertIntoTable(Nu2);
        NextUsersDAO.insertIntoTable(Nu3);
        NextUsersDAO.getAllNextUsers();
        NextUsersDAO.DeleteUserFromTable(Nu1);
        NextUsersDAO.getAllNextUsers();


    }

}
