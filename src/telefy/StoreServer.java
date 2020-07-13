package telefy;

import telefy.controller.AboutHandler;
import telefy.controller.LoginHandler;
import telefy.controller.LogoutHandler;
import telefy.controller.FileHandler;
import telefy.controller.TemplateController;
import telefy.controller.IndexHandler;
import telefy.controller.ReloadHandler;
import telefy.model.FileResourceModel;
import telefy.model.AccountsModel;
import telefy.model.ProductsModel;
import telefy.model.SqlAccountsModel;
import telefy.model.SqlProductsModel;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class StoreServer {
	// Port that the webserver is listening on.
	public static final int PORT = 13754;
	// Connection settings to a Microsoft SQL Server
	public static final String SQL_SERVER = "cis55.missioncollege.edu";
	public static final String SQL_PORT = "1433";
	public static final String SQL_DATABASE = "cis55_38";
	public static final String SQL_USER = "cis55_38";
	public static final String SQL_PASSWORD = "sql4u!";
	// Use the relative path from the netbeans working directory.
	public static final String RESOURCE_FOLDER = "resources\\";

	private static Connection sqlServer;
	private static HttpServer webServer;
	private static boolean started = false;

	/**
	 *
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		start();
	}

	public static void start() throws IOException, SQLException {
		if (started) {
			System.err.println("Cannot start a server that has already been started. You must stop it first.");
			System.exit(-1);
		}
		started = true;
		// Create server instances.
		webServer = HttpServer.create(new InetSocketAddress(PORT), 0);
		String url = "jdbc:sqlserver://" + SQL_SERVER + ":" + SQL_PORT + ";databaseName=" + SQL_DATABASE;
		sqlServer = DriverManager.getConnection(url, SQL_USER, SQL_PASSWORD);
		System.out.println("Connected to SQL Server: " + SQL_DATABASE);

		// Create model classes for accessing data.
		AccountsModel accountsModel = new SqlAccountsModel(sqlServer);
		ProductsModel productsModel = new SqlProductsModel(sqlServer);
		FileResourceModel resourceModel = new FileResourceModel(RESOURCE_FOLDER);
		resourceModel.loadResources();
		System.out.println(resourceModel);

		// Create helper classes.
		TemplateController templateController = new TemplateController(resourceModel, accountsModel, productsModel);

		// Create webpages.
		webServer.createContext("/", new IndexHandler(templateController, resourceModel, productsModel));
		webServer.createContext("/login", new LoginHandler(templateController, resourceModel, accountsModel));
		webServer.createContext("/logout", new LogoutHandler());
		webServer.createContext("/res", new FileHandler(resourceModel));
		webServer.createContext("/reload", new ReloadHandler(resourceModel, accountsModel));
		webServer.createContext("/about", new AboutHandler(templateController, resourceModel));

		// Start accepting HTTP requests.
		webServer.setExecutor(Executors.newFixedThreadPool(30));
		webServer.start();
		System.out.println("Server started on port " + PORT);
	}

	public static void stop() throws SQLException {
		webServer.stop(2);
		sqlServer.close();
		started = false;
	}
}
