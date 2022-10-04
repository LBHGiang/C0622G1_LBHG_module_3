import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class demo2_stored_procedure {
    // Ten cua driver va dia chi URL cua co so du lieu
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sinhvien?useSSL=false";

    //  Ten nguoi dung va mat khau cua co so du lieu
    static final String USER = "root";
    static final String PASS = "hellomodule3";

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            // Buoc 2: Dang ky Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Buoc 3: Mo mot ket noi
            System.out.println("Dang ket noi toi co so du lieu ...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Buoc 4: Thuc thi truy van
            System.out.println("Tao cac lenh truy van SQL ...");
            String sql = "{call getTenSV (?, ?)}";
            stmt = conn.prepareCall(sql);

            // Dau tien gan ket tham so IN, sau do la tham so OUT
            int MS_SINHVIEN = 5;
            stmt.setInt(1, MS_SINHVIEN); // Lenh nay thiet lap mssv la 2
            // Dang ky tham so thu hai
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            // Su dung phuong thuc execute de chay stored procedure.
            System.out.println("Thuc thi stored procedure ..." );
            stmt.execute();

            // Lay ten sinh vien voi phuong thuc getXXX
            String TEN_SINHVIEN = stmt.getString(2);
            System.out.println("Ten sinh vien co MSSV: " +
                    MS_SINHVIEN + " la " + TEN_SINHVIEN);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // Xu ly cac loi cho JDBC
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }// Ket thuc khoi finally
        }// Ket thuc khoi try
    }// Ket thuc main
}
