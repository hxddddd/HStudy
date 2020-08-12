
package cn.kgc.smbms.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ������:�ṩ���ݿ����ӵĹ���,.���кܺõ���չ��
 *
 * @author 86176
 */
public class DBUtil {
    public static void main(String[] args) {
        System.out.println(getConn());

    }

    //�ĸ���̬����
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    //ʹ�þ�̬���������Ը�ֵ
    static {
        //���ĸ�������Ϣ���ļ��ж�ȡ����
        Properties pro = new Properties();
        ///��pro��������ʽ����db.properties�ļ�
        try {
            pro.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //�ṩһ�������ķ����Թ������û�ȡ���ݿ�����
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //�ṩһ���������ڹر����ݿ�����
    public static void closeConn(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
