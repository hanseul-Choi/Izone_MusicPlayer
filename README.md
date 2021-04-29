# Toy Project - MiniPlayer
 _MVVM 구조 이해를 위한 개인 프로젝트_
<br><br>

## MVVM
Model, View, ViewModel로 구성된 안드로이드 아키텍쳐 패턴이다. MVC와 MVP의 의존성 및 유지보수성을 개선하기 위해 등장하였다.<br>
View : 사용자에게 보여주는 UI부분을 담당 <br>
ViewModel : View에서 사용하는 data처리 및 관리 <br>
Model : DB저장소 접근 데이터 틀 <br>
<br>
### MVVM특징
- view와 viewmodel은 n:m 관계로 적용이 가능하다.
- 재사용성이 용이하다.
- 효과적인 역할 분담과 테스트 용이성이 증가한다.

<br><br>
## MiniPlayer
본 프로젝트는 Kotlin과 MVVM구조, retrofit, RecyclerView 등 다양한 안드로이드 기술들을 이해하기 위해 진행한 프로젝트입니다. 
<br>
DataSet : 구글링 및 구매 <br>
Tool & FrameWork : Android Studio, Firebase Console <br>
Technology : Kotlin, MVVM, Retrofit, RecyclerView, Coroutine, DataBinding, LiveData, Glide
<br><br>

## 구조도
다음은 제작한 프로젝트의 구조도이며, MVVM구조를 이해하기 위해 그린 그림이다. 
<br>
### Network
![Network구조도](./img/Network구조도.PNG)
<br>
### Spinner
![Spinner구조도](./img/Spinner구조.PNG)
<br>
### RecyclerView
![RecyclerView구조도](./img/Recyclerview구조도.PNG)
<br>
### Fragment
![Fragment구조도](./img/Fragment구조도.PNG)
<br><br>

## 기능
다음은 music player의 기능이다.
<br><br>
### Music List 
![List](./img/KakaoTalk_20210429_231518818.jpg)
앱 초기화면으로 음악 리스트가 나열된다.<br>
<br>
![List](./img/KakaoTalk_20210429_231518620.jpg)
draggable 가능<br>
<br>
### Spinner 
![List](./img/KakaoTalk_20210429_231518454.jpg)
spinner 선택시 음악 list가 바뀐다.<br>
<br>
![List](./img/KakaoTalk_20210429_231518297.jpg)
다음은 리스트가 변경된 모습이다.<br>
<br>
![List](./img/KakaoTalk_20210429_231518134.jpg)
다음은 리스트가 변경된 모습이다.<br>
<br>
### 하단 플레이어
![List](./img/KakaoTalk_20210429_231517979.jpg)
하단 플레이어로 album image, title, singer와 음악 멈춤 및 다음 노래 기능이 존재한다.<br>
<br>

### 추가 기능
- 모든 음악은 끝나면 다음 노래로 자동으로 넘어간다. -> coroutine을 통해 mediaplayer의 state를 듣기 때문이다.
- 마지막 음악에서 다음 노래로 넘어가는 경우 첫 음악으로 돌아간다.
- 음악을 듣다가 spinner로 가수 목록을 변경해도 전 가수의 노래list가 loop된다. 
<br><br>
_Contact me :_ &nbsp; 
[![Gmail Badge](https://img.shields.io/badge/Gmail-d14836?style=flat-square&logo=Gmail&logoColor=white&link=mailto:hschoi5542@gmail.com)](mailto:hschoi5542@gmail.com)
