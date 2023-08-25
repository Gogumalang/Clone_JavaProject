package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;


public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "제목 > ");
		
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("추가되었습니다.");
		
	}
	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 제목을 입력하시오 > ");
		
		String title = sc.next();
		
		for(TodoItem item : l.getList()) {
			if(title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				break;
			}
		}	
	}
	
	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n" + "수정할 항목의 제목을 입력하시오 > ");
		
		String title = sc.next().trim();
		if(!l.isDuplicate(title)) {
			System.out.println("없는 제목입니다!");
			return;
		}
		
		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if(l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		for(TodoItem item : l.getList()) {
			if(item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.println("[전체목록]");
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void saveList(TodoList l, String filename)  {
		try {
			FileWriter fw = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				fw.write(item.toSaveString());
			}
			fw.close();
			System.out.println("모든 데이터가 저장되었습니다.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
		// unhandled exception type IOException이 발생하여서 try catch 문으로 해결했다. 
		// 또는 함수 명 옆에 throws IOException 을 적어도 해결 가능하다. 
		
	}

	public static void loadList(TodoList l, String filename) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int count=0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,"##");
				TodoItem item = new TodoItem(st.nextToken(),st.nextToken());
				item.setCurrent_date(st.nextToken());
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다.");
		}catch(Exception e){
			System.out.println(filename+" 파일이 없습니다.");
		}
		
		
	}
}