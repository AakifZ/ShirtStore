import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AakifsShoes {
	static Connection connection = null;

	/**
	 * Method has menu options as well as runs the connector and reader methods
	 * which are the 'brains' of everything
	 * 
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		while (true) {
			try {
				connector();
				break;
			} catch (SQLException e) {
				System.out.println("Login Not Correct ");
				continue;
			}
		}
		reader(connection);

		Scanner Menu = new Scanner(System.in);
		System.out.println("");

		int choice = -1;
		while (choice != 0) {

			System.out.println("Choose one of the following: ");
			System.out.println("1. Find Employee");
			System.out.println("2. Average Cost");
			System.out.println("3. Descending Transaction List");
			System.out.println("4. Inner Join");
			System.out.println("5. Descending Inventory");
			System.out.println("6. Latest Transaction");
			System.out.println("7. Left Join");
			System.out.println("8. Loyalty InnerJoin");
			System.out.println("9. Ordered Last Name");
			System.out.println("10. Phone Num Null");
			System.out.println("11. Right Join");
			System.out.println("12. Shirt Sizes");
			System.out.println("13. Average Cost vs. Shirts");
			System.out.println("14. Customer with Loyalty Points");
			System.out.println("15. Test1 (Displays Columns for Employees Table)");
			System.out.println("16. Test2 (Displays Everything in Transaction Table)");
			System.out.println("17. Test3 (Displays all the contraints)");
			System.out.println("18. Exit...");
			System.out.println("");
			choice = Menu.nextInt();

			switch (choice) {
			// Queries
			case 1:
				findemployee(connection);
				System.out.println("");
				continue;
			case 2:
				averageCost(connection);
				System.out.println("");
				continue;
			case 3:
				descendingTransaction(connection);
				System.out.println("");
				continue;
			case 4:
				innerJoin(connection);
				System.out.println("");
				continue;
			case 5:
				descInventory(connection);
				System.out.println("");
				continue;
			case 6:
				latestTransaction(connection);
				System.out.println("");
				continue;
			case 7:
				leftJoin(connection);
				System.out.println("");
				continue;
			case 8:
				loyaltyInner(connection);
				System.out.println("");
				continue;
			case 9:
				lastNameOrder(connection);
				System.out.println("");
				continue;
			case 10:
				phoneNumNull(connection);
				System.out.println("");
				continue;
			case 11:
				rightJoin(connection);
				System.out.println("");
				continue;
			case 12:
				shirtSize(connection);
				System.out.println("");
				continue;
			case 13:
				subQuery1(connection);
				System.out.println("");
				continue;
			case 14:
				subQuery2(connection);
				System.out.println("");
				continue;
			case 15:
				Test1(connection);
				System.out.println("");
				continue;
			case 16:
				Test2(connection);
				System.out.println("");
				continue;
			case 17:
				Test3(connection);
				System.out.println("");
				continue;
			case 18:
				choice = 0;
				break;
			}

		}
		Menu.close();
	}

	/**
	 * Method for the login of the database telling the user to enter a username and
	 * password of the database to access
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connector() throws ClassNotFoundException, SQLException {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Console console = System.console();
		System.out.println("Please Enter Username: ");

		String user = scanner.next();

		System.out.println("Please Enter Password: ");

		if (console != null) {
			char[] pass = console.readPassword();
			String password = new String(pass);

			// establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/checkpoint2";
			// String user = "root";
			// String password = "Marshallaw1";

			connection = DriverManager.getConnection(url, user, password);

		}

	}

	/**
	 * Methods reads the SQL Creation Script and populates it so it can be used
	 * through the program
	 * 
	 * @param connection1
	 * @throws IOException
	 */

	public static void reader(Connection connection1) throws IOException {
		try {
			Statement statement = connection1.createStatement();

			String strCurrentLine = null;

			BufferedReader sqlReader = new BufferedReader(new FileReader(
					"C:\\Users\\aakif\\OneDrive\\Desktop\\Summer Class\\Project\\Checkpoint2\\Checkpoint2(new).sql"));
			while ((strCurrentLine = sqlReader.readLine()) != null) {
				statement.executeUpdate(strCurrentLine);
			}

			sqlReader.close();

		} catch (SQLException a) {
			System.out.println("Issue with reading through the queries.");
			a.printStackTrace();
		} catch (FileNotFoundException b) {
			System.out.println("File is not found, please get another file.");
		} catch (Exception e) {
			System.out.println("Unexpected Error has Occured.");
		}

	}

	public static void findemployee(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("select * from employees  where employeesID = 12034 or employeesID = 5");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Employee not found");
		}
	}

	public static void averageCost(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"select avg(shirtCost) AS AverageCost from shirts where (shirtID = 11111 or shirtID = 100) ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("AvgCost not found");
		}
	}

	public static void descendingTransaction(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("select * from Transaction \r\n" + "order by transactionID\r\n" + "desc limit 4,3");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Transaction not found");
		}
	}

	public static void innerJoin(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT online_order.online_OrderID AS OnlineOrder, shirts.shirtName AS ShirtName, online_order.shirt_color AS ShirtColor\r\n"
							+ "FROM online_order\r\n" + "INNER JOIN shirts ON online_order.shirtID=shirts.ShirtID;");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Shirts not found");
		}
	}

	public static void descInventory(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("select * from inventory \r\n" + "order by shirtName\r\n" + "desc");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Inventory is not descending");
		}
	}

	public static void latestTransaction(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"select transaction.FirstName, transaction.LastName, max(transactionID) AS Latest_Transaction from transaction ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("The latest transaction not found");
		}
	}

	public static void leftJoin(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT inventory.Brand,shirts.shirtColor,inventory.ShirtID\r\n"
					+ "	FROM inventory\r\n" + "	LEFT JOIN shirts\r\n" + "	ON inventory.shirtID = shirts.shirtID;");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Inventory Shirts not found");
		}
	}

	public static void loyaltyInner(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT customer.FirstName, Customer.LastName,loyaltyprogram.customerID,loyaltyprogram.loyaltyID\r\n"
							+ "	FROM Customer\r\n"
							+ "	INNER JOIN loyaltyprogram ON loyaltyprogram.customerID=customer.customerID");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Loyalty Customer not found");
		}
	}

	public static void lastNameOrder(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT employees.FirstName,employees.LastName,employees.Address,employees.employeesID FROM employees \r\n"
							+ "Order By employees.LastName \r\n" + "Asc ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Last Name was not ordered");
		}
	}

	public static void phoneNumNull(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("select * from transaction where PhoneNumber is Null ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Phone Numbers did not load");
		}
	}

	public static void rightJoin(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT register.TransactionsID,shirts.shirtID, shirts.shirtcost, register.customerID\r\n"
							+ "FROM shirts\r\n" + "RIGHT JOIN register ON shirts.shirtID = register.shirtID\r\n"
							+ "ORDER BY shirts.shirtID; ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Transaction shirts did not load");
		}
	}

	public static void shirtSize(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"select shirtSize, Count(*) AS Total \r\n" + "	FROM shirts\r\n" + "	group by shirtSize ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Shirt sizes not displayed");
		}
	}

	public static void subQuery1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"select shirts.shirtName, shirts.shirtCost from Shirts where shirts.shirtcost < (select avg(shirtCost) From shirts) ");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Shirts under Avg Cost not displaying");
		}
	}

	public static void subQuery2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"select customer.FirstName,customer.LastName,loyaltyprogram.customerID,loyaltyprogram.Have500points\r\n"
							+ "from customer\r\n"
							+ "right join loyaltyprogram on customer.customerID = loyaltyprogram.customerID where Have500points=true\r\n"
							+ "order by loyaltyprogram.Have500points \r\n" + "");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Loyalty Customer with 500 Points not displaying");
		}
	}

	public static void Test1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("describe employees");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Employee Table not being displayed");
		}
	}

	public static void Test2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("select * from Transaction");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Transaction Table not found");
		}
	}

	public static void Test3(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT * FROM information_schema.columns WHERE table_schema = 'checkpoint2'");
			// print results
			while (results.next()) {
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					System.out.print(results.getString(i) + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Database not found");
		}
	}

	/*
	 * THE INSERT RETRIEVE UPDATE DELETE QUERIES
	 */

	public static void insert1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"insert into customer values('Samuel','Drake','1976-05-01','123 Uncharted Avenue',00555);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into employees values('Cat','Di','54 Field Lane','630222111',00666);");
			// print results

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert3(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into inventory values('Nike','Teal','L','OffWhite Champion',07777);");
			// print results
		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert4(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into loyaltyprogram values(True,'15%',00888,00049,False);");
			// print results
		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert5(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into shirts values('City','Green','M',50,09990);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert6(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into returns values(00731,'2018-05-01','Cash',05123,False);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert7(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"insert into loyaltycustomer values('Mary','Lamb','2010-2-05','1666 Farm Ville',12486);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert8(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into transaction values('Gon','Frechs',99012,3111233);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert9(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into online_order values('Red Lyon CE','Red','L',02234,00112);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert10(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into manager values('Jon','Nunes','114 Johnny Park',1112136587,01147);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void insert11(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into register values(01023,'Credit',True,00431,00021);");

		} catch (Exception e) {
			System.out.println("Query not inserted");
		}
	}

	public static void select1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from customer where customerID=00555;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from employees where employeesID=00666;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select3(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from inventory where shirtID=07777;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select4(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from loyaltyprogram where customerID=00888;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select5(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from shirts where shirtID=09990;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select6(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from returns where customerID=00731;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select7(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from register where shirtID=01023;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select8(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from loyaltycustomer where loyaltycustomerID=12486;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select9(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from transaction where transactionID=99012;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select10(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from online_order where online_orderID=00112;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void select11(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from manager where managerID=01147;");

		} catch (Exception e) {
			System.out.println("Query not selected");
		}
	}

	public static void update1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE customer SET Address='1234 Playstation Lane' WHERE customerID = 00555;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}

	}

	public static void update2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE employees SET Address='69 Market Avenue' WHERE employeesID = 00666;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update3(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE inventory SET Size='L' WHERE shirtID = 07777;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update4(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE loyaltyprogram SET Have500points=True WHERE customerID = 00888;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update5(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE shirts SET shirtSize='XXL' WHERE shirtID = 09990;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update6(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE returns SET PaymentMethod='Credit' WHERE customerID = 00731;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update7(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE register SET customerID=00012 WHERE shirtID = 01023;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update8(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"UPDATE loyaltycustomer SET Address='6643 Pig Avenue' WHERE loyaltycustomerID = 12486;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update9(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE transaction SET PhoneNumber = 1257123 WHERE transactionID=99012;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update10(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE online_order SET Shirt_Color='Green' WHERE shirtID = 02234;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void update11(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE manager SET Address='7896 Longcommon Drive' WHERE managerID = 01147;");

		} catch (Exception e) {
			System.out.println("Query not updated");
		}
	}

	public static void delete1(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM customer WHERE customerID=00555;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete2(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM employees WHERE employeesID=00666;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete3(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM inventory WHERE shirtID=07777;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete4(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM loyaltyprogram WHERE customerID=00888;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete5(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM shirts WHERE shirtID=09990;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete6(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM returns WHERE customerID=00731;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete7(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM register WHERE shirtID=01023;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete8(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM loyaltycustomer WHERE loyaltycustomerID=12486;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete9(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM transaction WHERE transactionID=99012;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete10(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM online_order WHERE shirtID=02234;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

	public static void delete11(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM manager WHERE managerID=01147;");

		} catch (Exception e) {
			System.out.println("Query did not get deleted");
		}
	}

}
