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
