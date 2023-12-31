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
		
		String title, desc,category,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "제목 > ");
		
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();

		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();

		System.out.print("내용 > ");
		desc = sc.nextLine().trim();

		System.out.print("마감일자 > ");
		due_date = sc.next();
		sc.nextLine();

		TodoItem t = new TodoItem(title, desc,category,due_date);
		list.addItem(t);
		System.out.println("추가되었습니다.");
		
	}
	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하시오 > ");
		
		int index = sc.nextInt();
		TodoItem item = l.getItem(index);
		System.out.println(index+". "+item.toString());
		while(true) {
			char chr;
			System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ");
			chr = sc.next().charAt(0);
			if(chr == 'y') {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
			}
			else if(chr == 'n') return;
			else System.out.println("잘못된 문자입니다.");
		}
		
			
	}
	
	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		String new_title,new_category,new_desc,new_due_date;
		
		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하시오 > ");

		int index = sc.nextInt();
		TodoItem item = l.getItem(index);
		System.out.println(index+". "+item.toString());
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		if(l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();

		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();

		System.out.print("새 마감일자 > ");
		new_due_date = sc.next();
		sc.nextLine();
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title, new_desc,new_category,new_due_date);
		l.addItem(t);
		System.out.println("수정되었습니다.");
	}
	
	public static void listAll(TodoList l) {
		int i=1;
		System.out.println("[전체목록, 총 " + l.getSize()+"개]");
		for(TodoItem item : l.getList()) {
			System.out.println(i+". "+item.toString());
			i++;
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
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem item = new TodoItem(title,desc,category,due_date);
				item.setCurrent_date(current_date);
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다.");
		}catch(Exception e){
			System.out.println(filename+" 파일이 없습니다.");
		}
		
		
	}

	public static void findItem(TodoList l,String keyword) {
		int i =1;
		int count =0;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyword) || item.getDesc().contains(keyword)) {
				System.out.println(i+". "+item.toString());
				count ++;
			}
			i++;
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");

	}

	public static void findCategory(TodoList l,String keyword) {
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println(name + " 유루~ ");

		int i =1;
		int count =0;
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println(i+". "+item.toString());
				count ++;
			}
			i++;
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");

	}

	public static void listCategory(TodoList l) {
		List<String> originCate = new ArrayList<String>();

		for(TodoItem item : l.getList()) {
			originCate.add(item.getCategory());
		}

		HashSet<String> distionctData = new HashSet<String>(originCate);
		List<String> resultList = new ArrayList<String>(distionctData);
		for(int i =0;i<resultList.size();i++) {
			if(i+1 == resultList.size()) System.out.println(resultList.get(i));
			else System.out.print(resultList.get(i)+" / ");
		}
		System.out.println("총 "+ resultList.size()+"개의 카테고리가 등록되어 있습니다.");
	}
}