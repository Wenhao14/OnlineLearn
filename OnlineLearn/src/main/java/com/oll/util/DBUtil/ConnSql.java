package com.oll.util.DBUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class ConnSql {
    private Connection conn;
	@Value("${spring.datasource.url}")
    private String dbUrl;
	@Value("${spring.datasource.username}")
    private String userName;
	@Value("${spring.datasource.password}")
    private String userPwd;
	@Value("${app.config.sqlDriver}")
    private String driverName;
    public Connection getConn(){
    	try{
    		Class.forName(driverName);//获取驱动
    	    conn=DriverManager.getConnection(dbUrl, userName, userPwd);//建立连接
    	    
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    	return conn;
    }
}
