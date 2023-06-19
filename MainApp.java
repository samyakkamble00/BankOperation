package BankOperation;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MainApp {

    static Scanner scan=new Scanner(System.in);


private static void operation(){
    System.out.println("Enter username");
    String name=scan.nextLine();
    System.out.println("Enter password");
    String password=scan.nextLine();
    boolean status=true;
    boolean result=BankOperation.validateLogin(name,password);
    if (result==true){
        while (status) {
            System.out.println("Welcome  " + BankOperation.userName);
            System.out.println("1.Deposit amount");
            System.out.println("2.withdraw amount");
            System.out.println("3.check balance");
            System.out.println("4.Account ststement");
            System.out.println("5.logout");
            System.out.println("6.exit");
            System.out.println("select your choice");
            int choice=scan.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter Amount to deposite");
                    double AddAmount=scan.nextDouble();
                    BankOperation.Deposite(AddAmount);
                    break;
                case 2:
                    System.out.println("Enter Amount to Withdrow");
                    double SubAmount=scan.nextDouble();
                    BankOperation.withdrow(SubAmount);
                    break;
                case 3:
                    BankOperation.checkbalane();
                    break;
                case 4:
                    BankOperation.AccountStstement();
                    break;
                case 5:
                    System.out.println("thanks for visiting");
                    BankOperation.accountNumber=0;
                    BankOperation.userName=null;
                    operation();
                    break;
                case 6:
                    System.out.println("thank for you visiting");
                    status = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }else{
        System.out.println("invalid username and password");
        operation();
    }
}
    public static void main(String[] args) {
    operation();
    }
}
