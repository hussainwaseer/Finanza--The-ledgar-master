import com.formdev.flatlaf.*;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import javax.swing.*;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.sql.*;
import java.io.File;
import java.util.Vector;

public class Main {
    public static final String databaseURL = "jdbc:sqlite:" + new File("").getAbsolutePath().replace("\\", "/") + "/src/ledgar.db";
    private static Connection databaseConnection;
    public static int userID;
    static {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try {
            Class.forName("org.sqlite.JDBC");
            databaseConnection = DriverManager.getConnection(databaseURL);
            try (Statement smth = databaseConnection.createStatement()) {
                smth.execute("PRAGMA foreign_keys=ON");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkLoginDetailsFromDatabase(String usernameFromUser, String passwordFromUser) {
        String query = "SELECT * FROM users;";
        try (Statement stmt = databaseConnection.createStatement();
             ResultSet databaseResultSet = stmt.executeQuery(query)) {
            while (databaseResultSet.next()) {
                String username = databaseResultSet.getString("USERNAME");
                String password = databaseResultSet.getString("PASSWORD");
                String adminPicturePath = databaseResultSet.getString("PFP_PATH");
                double account_balance = databaseResultSet.getDouble("ACCOUNT_BALANCE");
                double added_balance = databaseResultSet.getDouble("ADDED_BALANCE");
                double withdrawn_balance = databaseResultSet.getDouble("WITHDRAWN_BALANCE");
                int userid = databaseResultSet.getInt("UID");

                if (usernameFromUser.equals(username) && passwordFromUser.equals(password)) {
                    Login.username = usernameFromUser;
                    Login.password = passwordFromUser;
                    Dashboard.adminAccountBalance = account_balance;
                    Dashboard.adminAddedBalance = added_balance;
                    Dashboard.adminWithdrawnBalance = withdrawn_balance;
                    Dashboard.adminProfilePicturePath = adminPicturePath;
                    userID = userid;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean addBalanceOfUser(double balanceFromUser) {
        Dashboard.adminAccountBalance += balanceFromUser;
        Dashboard.adminAddedBalance += balanceFromUser;
        String query = "UPDATE users SET ACCOUNT_BALANCE = ?, ADDED_BALANCE = ? WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setDouble(1, Dashboard.adminAccountBalance);
            stmt.setDouble(2, Dashboard.adminAddedBalance);
            stmt.setString(3, Login.username);
            stmt.setString(4, Login.password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean cutBalanceOfUser(double balanceFromUser) {
        Dashboard.adminAccountBalance -= balanceFromUser;
        Dashboard.adminWithdrawnBalance += balanceFromUser;
        String query = "UPDATE users SET ACCOUNT_BALANCE = ?, WITHDRAWN_BALANCE = ? WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setDouble(1, Dashboard.adminAccountBalance);
            stmt.setDouble(2, Dashboard.adminWithdrawnBalance);
            stmt.setString(3, Login.username);
            stmt.setString(4, Login.password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void changeDetailsOfAccount(String newPath) {
        Dashboard.adminProfilePicturePath = newPath;
        String query = "UPDATE users SET PFP_PATH = ? WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setString(1, Dashboard.adminProfilePicturePath);
            stmt.setString(2, Login.username);
            stmt.setString(3, Login.password);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean changeUsernameAndPassword(String newUserName, String newPassword) {
        String query = "UPDATE users SET USERNAME = ?, PASSWORD = ? WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setString(1, newUserName);
            stmt.setString(2, newPassword);
            stmt.setString(3, Login.username);
            stmt.setString(4, Login.password);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                Login.username = newUserName;
                Login.password = newPassword;
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean createAccount(String username, String password) {
        String query = "INSERT INTO users (USERNAME, PASSWORD,PFP_PATH) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, new File("").getAbsolutePath()+"/src/admin_pic.jpg");
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean deleteExistingAccount() {
        String query = "DELETE FROM users WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setString(1, Login.username);
            stmt.setString(2, Login.password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean transactionOfIncome(Double SEND_AMOUNT, String TYPE, String CATEGORY, String DATE) {
        String query = "INSERT INTO transactions (USER_ID, AMOUNT, TYPE, CATEGORY, DATE) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDouble(2, SEND_AMOUNT);
            stmt.setString(3, TYPE);
            stmt.setString(4, CATEGORY);
            stmt.setString(5, DATE);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                addBalanceOfUser(SEND_AMOUNT);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean transactionOfExpense(double SEND_AMOUNT, String TYPE, String CATEGORY, String DATE) {
        String query = "INSERT INTO transactions (USER_ID, AMOUNT, TYPE, CATEGORY, DATE) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDouble(2, SEND_AMOUNT);
            stmt.setString(3, TYPE);
            stmt.setString(4, CATEGORY);
            stmt.setString(5, DATE);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                cutBalanceOfUser(SEND_AMOUNT);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean addMoneyTransaction(double ADD_AMOUNT) {
        String query = "INSERT INTO transactions (USER_ID, AMOUNT, TYPE, CATEGORY) VALUES (?, ?, 'INCOME', 'DEPOSIT')";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDouble(2, ADD_AMOUNT);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean withdrawMoneyTransaction(double WITHDRAW_AMOUNT) {
        String query = "INSERT INTO transactions (USER_ID, AMOUNT, TYPE, CATEGORY) VALUES (?, ?, 'INCOME', 'WITHDRAW')";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDouble(2, WITHDRAW_AMOUNT);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Vector displayAllTransactions(){
        Vector<Vector<Object>> data = new Vector<>();
        try{
            Statement smth = databaseConnection.createStatement();
            String query = "select * from transactions where USER_ID="+userID+" order by ID desc;";
            ResultSet rs = smth.executeQuery(query);
            while(rs.next()){
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("ID"));
                row.add(rs.getDouble("AMOUNT"));
                row.add(rs.getString("TYPE"));
                row.add(rs.getString("CATEGORY"));
                row.add(rs.getString("DATE"));
                data.add(row);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static boolean deleteTransaction(int idOfTransaction){
        boolean done=false;
        try{
            String query = "delete from transactions where ID="+idOfTransaction+";";
            Statement smth = databaseConnection.createStatement();
            int rows = smth.executeUpdate(query);
            return rows > 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return done;
    }

    public static void generatePDF(String dest) throws Exception {
        PdfWriter writeInPdf =  new PdfWriter(dest);
        PdfDocument documentPdf = new PdfDocument(writeInPdf);
        Document document = new Document(documentPdf);
        InputStream imagePathStream = Main.class.getResourceAsStream("/logo.png");
        ImageData imageData = ImageDataFactory.create(imagePathStream.readAllBytes());
        Image finanzaLogo = new Image(imageData);
        finanzaLogo.setHeight(100);
        finanzaLogo.setBackgroundColor(null);

        finanzaLogo.setWidth(150);
        finanzaLogo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(finanzaLogo);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatTheDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateToShow = dateTimeNow.format(formatTheDateTime);

        document.add(new Paragraph("Username: "+Login.username));
        document.add(new Paragraph("Date: "+formattedDateToShow+"s"));

        document.add(new Paragraph("Current Balance: "+Dashboard.adminAccountBalance));
        document.add(new Paragraph("Amount in: "+Dashboard.adminAddedBalance));
        document.add(new Paragraph("Amount out: "+Dashboard.adminWithdrawnBalance));
        document.add(new Paragraph("Note: This finanza Statment is generated on the request of Mr/Miss "+Login.username+""));


        Table table = new Table(UnitValue.createPercentArray(new float[] {2,2,5,5,5}));
        table.setBorder(new SolidBorder(ColorConstants.BLACK,1));
        table.setWidth(UnitValue.createPercentValue(100));

        Cell idCell = new Cell().add(new Paragraph("ID").setBold()
       .setBackgroundColor(new DeviceRgb(113, 250, 57))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
        );
        table.addCell(idCell);

        Cell amountCell = new Cell().add(new Paragraph("Amount").setBold()
                .setBackgroundColor(new DeviceRgb(113,250,57))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
        );
        table.addCell(amountCell);

        Cell typeCell = new Cell().add(new Paragraph("Type").setBold()
                .setBackgroundColor(new DeviceRgb(113,250,57))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
        );
        table.addCell(typeCell);

        Cell categoryCell = new Cell().add(new Paragraph("Category").setBold()
                .setBackgroundColor(new DeviceRgb(113,250,57))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
        );
        table.addCell(categoryCell);

        Cell dateCell = new Cell().add(new Paragraph("Date").setBold()
                .setBackgroundColor(new DeviceRgb(113,250,57))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
        );
        table.addCell(dateCell);

        Vector<Vector<Object>> transactionData = new Vector<>();
        transactionData = Main.displayAllTransactions();
        for(Vector<Object> row : transactionData){
            table.addCell(row.get(0).toString());
            table.addCell(row.get(1).toString());
            table.addCell(row.get(2).toString());
            table.addCell(row.get(3).toString());
            table.addCell(row.get(4).toString());
        }
        document.add(table);
        document.add(new Paragraph("Generated by Finanza - The ledgar Master"));
        document.close();
    }
    public static void main(String[] args) {
        new SplashScreen();
    }
}
