package com.hand;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        System.out.println("start APP...");
        App app = new App();
        app.select1(Integer.parseInt(args[0]));
        app.select3(Integer.parseInt(args[1]));
//        app.select1(2);
//        app.select3(2);
//        app.select2(2);
    }

    private Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://120.79.66.240:3306/sakila";      // 连接阿里云服务器docker->mysql
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void select1(int country_id) {
        Connection conn = getConn();
//         排序升序：ASC 降序：DESC
//        ORDER BY id ASC
        String sql = "SELECT ci.city_id,ci.city FROM city ci WHERE ci.country_id= ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, country_id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("============================");
            System.out.println("[INFO] Number1");
            System.out.println("country_id：" + country_id + " ");
            while (rs.next()) {
                int city_id = rs.getInt("city_id");
                System.out.print("city_id：" + city_id);

                String city = rs.getString("city");
                System.out.println(" name:" + city);
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void select2(int customer_id) {
        System.out.println("customer_id:"+customer_id);
        Connection conn = getConn();
//         排序升序：ASC 降序：DESC
//        ORDER BY id ASC
        String sql = "select distinct film_id,inv.last_update " +
                "from customer cu,inventory inv " +
                "where customer_id=? AND cu.store_id=inv.store_id " +
                "ORDER BY inv.last_update DESC ";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customer_id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("============================");
            while (rs.next()) {
                int film_id = rs.getInt("film_id");
                Timestamp date=rs.getTimestamp("last_update");
                System.out.print("film_id:"+film_id);
                sql = "SELECT fi.title " +
                        "from film_text fi " +
                        "where fi.film_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, film_id);
                ResultSet rs2 = pstmt.executeQuery();
                while (rs2.next()) {
                    String title = rs2.getString("title");
                    System.out.print(" film name:"+title);
                }
                System.out.println(" 最后一次租用时间为："+date);
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void select3(int customer_id) {
        System.out.println("customer_id:"+customer_id);
        Connection conn = getConn();
        String sql = "select inv.film_id,re.rental_date " +
                "from customer cu,inventory inv,rental re " +
                "where cu.customer_id=? AND cu.store_id=inv.store_id AND re.inventory_id=inv.inventory_id " +
                "ORDER BY re.rental_date DESC ";
        String sql2 = "SELECT fi.title " +
                "from film_text fi " +
                "where fi.film_id=?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customer_id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("============================");
            System.out.println("[INFO] Number2");
            while (rs.next()) {
                int film_id = rs.getInt("film_id");
                Timestamp rental_date=rs.getTimestamp("rental_date");
                System.out.print("film_id:"+film_id);

                PreparedStatement pstmt2;
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, film_id);
                ResultSet rs2 = pstmt2.executeQuery();
                while (rs2.next()) {
                    String title = rs2.getString("title");
                    System.out.print(" film name:"+title);
                }

                System.out.println(" rental date:"+rental_date);
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
