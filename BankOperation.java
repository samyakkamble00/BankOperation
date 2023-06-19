package BankOperation;

import java.sql.*;
import java.util.Scanner;


public class BankOperation {
    static Connection con=null;
    static String userName;
    static String userpassword;
    static int  accountNumber;

    static  double accountBalance;
    static PreparedStatement pstmt=null;
        static {
            try {
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja9","root","1234");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static boolean validateLogin(String name,String password){
            String query="select * from userlogin where UserName=? and UserPassword=?";
            PreparedStatement pstmt=null;
            ResultSet rs=null;

            try {
                pstmt= con.prepareStatement(query);
                pstmt.setString(1,name);
                pstmt.setString(2,password);
                rs=pstmt.executeQuery();
                if(rs.next()){
                    userName=rs.getString(2);
                    accountNumber=rs.getInt(1);
                    return true;
                }else{
                    return false;
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public static void checkbalane() {
            String query="SELECT TransationAmount FROM bankpassbook FULL JOIN userlogin ON AccountNo=Acc_No  WHERE Acc_No=? ORDER BY TransationId DESC LIMIT 1";
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try{
                pstmt=con.prepareStatement(query);
                pstmt.setInt(1,accountNumber);
                rs=pstmt.executeQuery();
                if (rs.next()){
                    accountBalance=rs.getDouble(1);
                    System.out.println("account blance is"+rs.getDouble(1));
                }else {
                    System.out.println("something went wrong");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public  static void AccountStstement() {
        String query="SELECT * FROM bankpassbook WHERE Acc_No = ?";
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1,accountNumber);
            rs=pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getDouble(2)+"  "+rs.getDouble(3)+"  "+rs.getDouble(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    //Deposite
    public static void Deposite(double addAmount) {
       checkbalane();
       String query="insert into bankpassbook values (?,?,?,?,?)";
       PreparedStatement pstmt=null;
        try {
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1,0);
            pstmt.setDouble(2,accountBalance+addAmount);
            pstmt.setDouble(3,addAmount);
            pstmt.setDouble(4,0);
            pstmt.setInt(5,accountNumber);
            int count= pstmt.executeUpdate();
            if (count>0){
                System.out.println(addAmount + "  amount add suceessfully");
            }else{
                System.out.println("something went wrong");
            }
            checkbalane();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void withdrow(double subAmount) {
        checkbalane();
        String query="insert into bankpassbook values (?,?,?,?,?)";
        PreparedStatement pstmt=null;
        try {
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1,0);
            pstmt.setDouble(2,accountBalance-subAmount);
            pstmt.setDouble(3,0);
            pstmt.setDouble(4,subAmount);
            pstmt.setInt(5,accountNumber);
            int count= pstmt.executeUpdate();
            if (count>0){
                System.out.println(subAmount + "  amount withdraw suceessfully");
            }else{
                System.out.println("something went wrong");
            }
            checkbalane();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
