package com.oll.util.DBUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;
@Service
public class SqlVehicel {
     private Connection conn;
     private ResultSet  res;
     private PreparedStatement pre;
     @Resource
	 private ConnSql connSql;
     //封装查询功能返回结果集
	public ArrayList<Object[]> SqlSelect(String sql, String []paras){
    	 ArrayList<Object[]> al=new ArrayList<Object[]>();
    	 try{
    		 //获取连接
	    	 conn = connSql.getConn();
	    	 //获得sql查询语句
	    	 pre=conn.prepareStatement(sql);
	    	 //给sql语句中变量赋值
			 if(paras != null){
				 for(int i=0;i<paras.length;i++){
					 pre.setString(i+1, paras[i]);
				 }
			 }
	    	 //执行查询
	    	 res=pre.executeQuery();
	    	 //得到结果集的结构
	    	 ResultSetMetaData rems=res.getMetaData();
	    	 //获得一条记录有多少列
	    	 int columnNum=rems.getColumnCount();
	    	 //将结果封装在list中
	    	 while(res.next()){
	    		 Object[] object=new Object[columnNum];
	    		 for(int i=0;i<columnNum;i++){
	    			 object[i]=res.getObject(i+1);
	    		 }
	    		 al.add(object);
	    	}
    	 }catch(SQLException e){
    		 throw new RuntimeException(e.getMessage());
    	 }finally{
    		 close(conn,res,pre);//释放资源
    	 }
    	 return al;//返回结果
     }
	//封装更新操作返回影响行数
	public int updataSql(String sql,String [] paras){ 
		int result = 0;
		try{
			 //获取连接
			 conn=new ConnSql().getConn();
			 pre=conn.prepareStatement(sql);
			 //给问号赋值
			 for(int i=0;i<paras.length;i++){
				 pre.setString(i+1, paras[i]);
			 } 
			 //提交操作
			result= pre.executeUpdate();
			 
		 }catch(SQLException e){
			 e.printStackTrace();
			 throw new RuntimeException(e.getMessage());
		   }finally{
			   //关闭资源
			   close(conn,null,pre);
		   }
		return result;
	}
	//释放资源函数
	public void close(Connection conn,ResultSet res,PreparedStatement pre){
		 if(res!=null)
			  try{
				  res.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 if(pre!=null)
			 try{
				  pre.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 if(conn!=null)
			 try{
				  conn.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
	}
	
}

