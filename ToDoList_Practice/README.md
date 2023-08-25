# 1강 [ToDoListApp 시작하기](https://www.youtube.com/watch?v=alpgDkyjW5s/)

객체 지향으로 코딩을 할 때는 어떻게 데이터를 가공하고, 전반적인 구성을 잘하는 것에 집중해야 할 것 같다. 
## Package todo
### 구성과 역할
- __Main__ 클래스는 어떤 프로그램이 실행되는지 알려주는 시작점의 역할을 한다.
- __TodoMain__ 클래스는 프로그램의 실행과정을 구성함으로서 전반적으로 프로그램이 돌아가는 것을 파악할 수 있다.
### 궁금한점 
- public , static 을 쓰는 이유를 알고 싶다. 
## Package dao 
### 구성과 역할
- __TodoItem__ 클래스는 가장 기본이 되는 원소로서 주로 메소드는 getter 와 setter로 이루어져있다. 
- __TodoList__ 클래스는 프로그램에서 사용되는 데이터이다. 메소드는 리스트의 기능으로서만 할 수 있도록, 더하고, 빼고, 수정하고, 중복 체크 정도로만 이루어져 있다.
### 궁금한점 
- editItem() 에서는 왜 public 을 쓰지 않는 것인가? 
- getList를 할 때는 멤버변수로 return 하는 것이 아닌, 새로운 리스트를 만들어 리턴하는 특별한 이유가 있을까?
- Collection 을 쓰는 이유는 무엇인가? 
## Package service 
### 구성과 역할
- __TodoUtil__ 클래스는 CRUD를 담당하는 클래스이다.
- __TodoSortByName,TodoSortByDate__ 는 comparator 클래스를 implement 한 클래스로서, 기준에 따른 정렬을 하게 해준다.
### 궁금한점 
- __TodoUtil__ 에서는 메소드를 public static 을 쓰는 이유가 있는지 알고 싶다. 
## Package menu
### 구성과 역할
- __Menu__ 클래스는 프로그램의 메뉴를 띄우는 일종의 프론트엔드 역할을 맡는다. 

# 2강 [ToDoListApp 파일기능 추가(1)](https://www.youtube.com/watch?v=hVeF1-WJZJw&list=PLoJdZ7VvEiRM4lw8htJg7qsDoHILpvfLP&index=2)

## IOExeption Class
- 스트림, 파일 및 디렉터리를 사용하여 정보에 액세스하는 동안 throw된 예외에 대한 기본 클래스이다.
- 따라서 파일 기능을 넣을 때는 이 점을 유의해야한다. 
- throw를 사용하기도 하지만, 파일의 상태(ex 비어있음)를 파악하기 위해서  try catch 문을 활용한다. 

## BufferdReader Class 
- 버퍼를 사용하여 읽기를 하는 클래스이다. (<-> BufferdWriter)
- 개행문자만 경계로 인식하고 입력받은 데이터가 String으로 고정된다. 필요에 따라 데이터 가공이 필요하다.
> currentDate의 타입을 String 으로 변화시킨 이유이다. 
> StringTokenizer 를 쓴 이유이다. 
### Buffer
- 버퍼를 사용하지 않는 입력은 키보드의 입력이 키를 누르는 즉시 바로 프로그램에 전달된다. (쓰레기가 생길 때마다 밖에 버리기)
- 버퍼를 사용하는 입력은 버퍼가 가득 차거나 개행 문자가 나타나면 한 번에 프로그램에 전달한다. (쓰레기통이 꽉 차면 한번에 밖에 버리기)
- 하드디스크의 속도가 느리고 데이터 입출력도 시간이 오래걸리기 때문에 버퍼를 두는 것이 더 효율적이고 빠르다. 
### BufferdReader vs Scanner 
Scanner 은 띄어쓰기와 개행문자를 경계로 하여 입력 값을 인식한다. 
또한 원하는 타입의 입력을 받을 수 있다. 
하지만 버퍼 사이즈가 1024 char이기 때문에 많은 입력을 필요로 할 경우에는 BufferedReader (max = 8192 char) 가 유용하다. 

## StringTokenizer Class
- 일정한 형식이 정해진 문자열을 나누기 위해 사용한다. 
Constructor
1. new StringTokenizer(String) : __띄어쓰기__ 기준으로 문자열을 분리
2. new StringTokenizer(String,Separator) : __구분자__ 기준으로 문자열을 분리
3. new StringTokenizer(String,Separator,Bool) : 구분자 또한 토큰에 포함을 시킬지 결정

### BufferedReader 와 StringTokenizer 을 활용하여 파일 읽어오기. 
```
BufferedReader br = new BufferedReader(new FileReader(filename));
String line;
while((line = br.readLine()) != null) {
	StringTokenizer st = new StringTokenizer(line,"##");
	TodoItem item = new TodoItem(st.nextToken(),st.nextToken());
	item.setCurrent_date(st.nextToken());
	l.addItem(item);
}
br.close();
```