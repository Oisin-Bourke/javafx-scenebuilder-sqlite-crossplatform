package com.gluonapplication;

import com.gluonapplication.stock.*;
import com.gluonapplication.views.ThirdChildStockPresenter;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.StorageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class DBMethods{

    //db name
    private final static String DB_NAME = "shop.db";//file name of db

    //db credentials:
    private Connection connection = null;
    private Statement stmt;//prep statement
    private ResultSet rs;//result set

    //constructor assigns platform type:
    public DBMethods() {

        try {
            Class c = null;
            if (Platform.isAndroid()) {
                c = Class.forName("org.sqldroid.SQLDroidDriver");
                System.out.println("Android platform db");
            } else if (Platform.isIOS()) {
                c = Class.forName("SQLite.JDBCDriver");
                System.out.println("iOS platform db");
            } else if (Platform.isDesktop()) {
                c = Class.forName("org.sqlite.JDBC");
                System.out.println("Desktop platform db");
            } else if (System.getProperty("os.arch").toUpperCase().contains("ARM")) {
                c = Class.forName("org.sqlite.JDBC");
                System.out.println("Desktop/os.arch platform db");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error class not found " + e);
        }

    }

    //create and connect:
    public void createDB() {
        System.out.println("Creating a Database with SQLite");
        File dir;
        String dbUrl = "jdbc:sqlite:";
        try {
            dir = Services.get(StorageService.class)
                    .map(s -> s.getPrivateStorage().get())
                    .orElseThrow(() -> new IOException("Error: PrivateStorage not available"));
            File db = new File(dir, DB_NAME);
            dbUrl = dbUrl + db.getAbsolutePath();
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
            return;
        }
        try {
            connection = DriverManager.getConnection(dbUrl);
            System.out.println("Connection established: " + dbUrl);
        } catch (SQLException ex) {
            System.out.println("Error establishing connection " + ex.getSQLState());
            return;
        }

    }

    public void createTables(){
        try {
            if (connection != null) {
                stmt = connection.createStatement();
                stmt.setQueryTimeout(30);

                System.out.println("Creating table 'stock'...");

                //stmt.executeUpdate("drop table if exists stock");
                stmt.executeUpdate("create table if not exists stock (id integer primary key autoincrement, " +
                                        "date_stamp datetime default current_timestamp, " +
                                        "category text, type text, condition text,brand text,size text," +
                                        "price text,productCodeString text)");
                System.out.println("End creating table");
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
        }
    }

    public void createItem(Item item){

        System.out.println("create item method called...");
        //get item params
        String a = item.getCategory().toString();
        String b = item.getType().toString();
        String c = item.getCondition().toString();
        String f = String.valueOf(item.getPrice());
        String g = item.getProductCodeString();

        String d = null;
        String e = null;
        if(item instanceof Apparel) {
            d = item.getBrand().toString();
            e = item.getSize().toString();
        }
        //add item to db:
        try {
            if (connection != null) {
                stmt = connection.createStatement();
                stmt.setQueryTimeout(30);

                System.out.println("Adding item to table 'stock'...");

                //insert:
                if (item instanceof Apparel){
                    stmt.executeUpdate("insert into stock (category,type,condition,brand,size,price,productCodeString) " +
                            "values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"')");
                } else {
                    stmt.executeUpdate("insert into stock (category,type,condition,price,productCodeString) " +
                            "values('"+a+"','"+b+"','"+c+"','"+f+"','"+g+"')");
                }

                //will be deleted
                System.out.println("Retrieving records from table 'stock'...");
                rs = stmt.executeQuery("select * from stock ");

                while (rs.next()) {

                    String category = rs.getString("category");
                    String type = rs.getString("type");
                    String condition = rs.getString("condition");
                    String brand = rs.getString("brand");
                    String size = rs.getString("size");

                    String price = rs.getString("price");
                    String code = rs.getString("productCodeString");

                    String id = rs.getString("id");
                    String dateStamp = rs.getString("date_stamp");

                    System.out.println("id: "+id+" date stamp: "+dateStamp+" category:"+category+" type:"+type+" condition:"+condition+" brand:"+brand+" size:"+size+" price:"+price+" p code:"+code);

                }
                System.out.println("End adding item to 'stock'...");
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
        }
    }

    //search item:
    public Item searchStockItem(String productCodeString){
        System.out.println("searching for item...");

        LinkStringEnum link = new LinkStringEnum();

        String category = null;
        String type = null;
        String condition = null;
        String brand = null;
        String size = null;

        try {
            if (connection != null) {
                stmt = connection.createStatement();
                stmt.setQueryTimeout(30);

                //search:
                System.out.println("Retrieving record from table 'stock' where id is "+productCodeString);
                rs = stmt.executeQuery("select category,type,condition,brand,size from stock where productCodeString = '"+productCodeString+"' limit 1");

                while (rs.next()) {
                    category = rs.getString("category");
                    type = rs.getString("type");
                    condition = rs.getString("condition");
                    brand = rs.getString("brand");
                    size = rs.getString("size");

                    System.out.println("category:" + category + " type:" + type + " condition:" + condition + " brand:" + brand + " size:" + size);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
        }

        //return item:
        if(category!=null){
            if(category.equals("Other")){
                return new Item(link.linkCategory(category), link.linkType(type), link.linkCondition(condition));
            } else {
                return new Apparel(link.linkCategory(category), link.linkType(type), link.linkCondition(condition), link.linkBrand(brand), link.linkSize(size));
            }
        }
        else {
            return null;
        }
    }

    //delete item:
    public void deleteStockItem(String productCodeString){
        System.out.println("searching for item to delete...");
        try {
            if (connection != null) {
                stmt = connection.createStatement();
                stmt.setQueryTimeout(30);
                System.out.println("deleting record from table 'stock' where id is "+productCodeString);
                stmt.executeUpdate("delete from stock where productCodeString = '"+productCodeString+"' ");
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
        }
        System.out.println("Item deleted from db...");
    }

    public ArrayList<ThirdChildStockPresenter.StockData> readDB(){

        ArrayList<ThirdChildStockPresenter.StockData> dataDb = new ArrayList<>();

        //add item to db:
        try {
            if (connection != null) {
                stmt = connection.createStatement();
                stmt.setQueryTimeout(30);

                //will be deleted
                System.out.println("Retrieving records from table 'stock'...");
                rs = stmt.executeQuery("select * from stock ");

                while (rs.next()) {
                    String id = rs.getString("id");
                    String dateStamp = rs.getString("date_stamp");
                    String category = rs.getString("category");
                    String type = rs.getString("type");
                    String condition = rs.getString("condition");
                    String brand = rs.getString("brand");
                    String size = rs.getString("size");
                    String price = rs.getString("price");
                    String code = rs.getString("productCodeString");

                    dataDb.add( new ThirdChildStockPresenter.StockData(id,dateStamp,category,type,condition,brand,size,price,code));
                    System.out.println("Item added to dataDb");
                }
                System.out.println("End adding item to 'stock'...");
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL error " + ex.getSQLState());
            }
        }
        return dataDb;
    }


    /*
    //activate db met
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.PERSON_PIN.button());
        appBar.setTitleText("SQLite");
        appBar.getActionItems().addAll(
                MaterialDesignIcon.CREATE_NEW_FOLDER.button(e -> createDB()),
                MaterialDesignIcon.ATTACH_FILE.button(e -> readDB()),
                MaterialDesignIcon.REMOVE.button(e -> {
                    listView.getItems().clear();
                    status.getItems().clear();
                }));
    }
    */

}
