package com.sist.dao;
import java.util.*;

import org.apache.*;

import java.io.*;
import com.sist.vo.*;

public class CartDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resource.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void goodsCartInsert(CartVO) {
		ssf.openSession(true).insert("goodsCartInsert",vo);
	}
}
