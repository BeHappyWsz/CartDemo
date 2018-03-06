package wsz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import wsz.entity.Items;
import wsz.util.DBUtil;

public class ItemsDAO {

	/**
	 * 获取
	 * @return
	 */
	public ArrayList<Items> getAllItems(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_items;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getDouble("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,stmt,conn);
		}
		return list;
	}
	
	/**
	 * 获取某一商品
	 * @param id
	 * @return
	 */
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_items where id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getDouble("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,stmt,conn);
		}
		return null;
	}
	
	public ArrayList<Items> getViewList(String list){
		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		int iCount=5; //每次返回前五条记录
		if(list!=null&&list.length()>0)
		{
		    String[] arr = list.split("#");
		    System.out.println("arr.length="+arr.length);
		    //如果商品记录大于等于5条
		    if(arr.length>=5)
		    {
		       for(int i=arr.length-1;i>=arr.length-iCount;i--)
		       {
		    	  itemlist.add(getItemsById(Integer.parseInt(arr[i])));  
		       }
		    }
		    else
		    {
		    	for(int i=arr.length-1;i>=0;i--)
		    	{
		    		itemlist.add(getItemsById(Integer.parseInt(arr[i])));
		    	}
		    }
		    return itemlist;
		}
		return null;
	}
	
}
