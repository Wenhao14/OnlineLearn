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
     //��װ��ѯ���ܷ��ؽ����
	public ArrayList<Object[]> SqlSelect(String sql, String []paras){
    	 ArrayList<Object[]> al=new ArrayList<Object[]>();
    	 try{
    		 //��ȡ����
	    	 conn = connSql.getConn();
	    	 //���sql��ѯ���
	    	 pre=conn.prepareStatement(sql);
	    	 //��sql����б�����ֵ
			 if(paras != null){
				 for(int i=0;i<paras.length;i++){
					 pre.setString(i+1, paras[i]);
				 }
			 }
	    	 //ִ�в�ѯ
	    	 res=pre.executeQuery();
	    	 //�õ�������Ľṹ
	    	 ResultSetMetaData rems=res.getMetaData();
	    	 //���һ����¼�ж�����
	    	 int columnNum=rems.getColumnCount();
	    	 //�������װ��list��
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
    		 close(conn,res,pre);//�ͷ���Դ
    	 }
    	 return al;//���ؽ��
     }
	//��װ���²�������Ӱ������
	public int updataSql(String sql,String [] paras){ 
		int result = 0;
		try{
			 //��ȡ����
			 conn=new ConnSql().getConn();
			 pre=conn.prepareStatement(sql);
			 //���ʺŸ�ֵ
			 for(int i=0;i<paras.length;i++){
				 pre.setString(i+1, paras[i]);
			 } 
			 //�ύ����
			result= pre.executeUpdate();
			 
		 }catch(SQLException e){
			 e.printStackTrace();
			 throw new RuntimeException(e.getMessage());
		   }finally{
			   //�ر���Դ
			   close(conn,null,pre);
		   }
		return result;
	}
	//�ͷ���Դ����
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

