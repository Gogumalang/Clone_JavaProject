package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	//가장 기본 원소가 되는 데이터이다. 
	//멤버변수들을 private 로 두었다. 정확한 이유는 아직은 모르겠다. 
	// getter setter을 두어 멤버변수들을 읽고 쓸 수 있도록하였다. 
	private String title;
	private String desc;
	private String current_date;
	private String category;
	private String due_date;
	SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss");
	public TodoItem(String title, String desc,String category,String due_date) {
		// instance 를 초기할 때 하는 것이다. 
		// this 를 쓴 이유는 중복되는 것을 헷갈리지 않게 하기 위함이다. 
		// 또한 일부러 중복되도록 씀으로서 멤버변수를 다루는 것을 알기쉽게 가독성을 높인다. 
		this.title=title;
		this.desc=desc;
		this.category = category;
		this.due_date = due_date;
		this.current_date=simple.format(new Date());
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}
	
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	public String getCurrent_date() {
		return current_date;
	}
	
	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}
	
	@Override
	// 기존에 존재하던것을 각색할 때는 override (뭐라고 부르더라) 를 붙인다. 
	public String toString() {
		return "[" + category + "] " + title + " - " + desc + " - " + due_date + " - " +current_date;
	}

	public String toSaveString() {
		return category + "##"+ title + "##" + desc + "##" + due_date + "##"+ current_date+"\n";
	}
	
	
}