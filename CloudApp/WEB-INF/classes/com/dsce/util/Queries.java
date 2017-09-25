package com.dsce.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Queries {

	public static String getQuery(String qry) {
		Queries q = new Queries();
		return q.getProperty(qry);
	}

	public String getProperty(String qry) {
		Properties prop = new Properties();
		try {
			// prop.load(new FileInputStream(new
			// File("c:/Ashok/queries.properties")));
			//prop.load(this.getClass().getResourceAsStream("queries.properties"));
			prop.load(this.getClass().getClassLoader().getResourceAsStream("queries.properties"));
			return prop.getProperty(qry);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String arg[]) {
		System.out.println(new Queries().getQuery("ADD_ROLE"));
	}
}
