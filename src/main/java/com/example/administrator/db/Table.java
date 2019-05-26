package com.example.administrator.db;

import java.util.ArrayList;


public class Table {
	public String name;
	public ArrayList<Feild> feilds;

	public Table(String name, ArrayList<Feild> feilds) {
		super();
		this.name = name;
		this.feilds = feilds;
	}

	public Table(String name) {
		super();
		this.name = name;
		this.feilds = new ArrayList<Feild>();
	}

	public void addFeild(Feild feild) {
		this.feilds.add(feild);
	}
	
	public String createSQL(){
		StringBuffer sb=new StringBuffer();
		sb.append("CREATE TABLE "+this.name);
		sb.append("(");
		
		ArrayList<Feild> fs = this.feilds;
		for(Feild f:fs){
			sb.append(f.name);
			sb.append(" ");
			sb.append(f.type);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
}
