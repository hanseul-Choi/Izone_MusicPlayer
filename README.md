# Toy Project - MiniPlayer
 _MVVM 구조 이해를 위한 개인 프로젝트_
<br><br>
## MVVM
Model, View, ViewModel로 구성된 안드로이드 아키텍쳐 패턴이다. MVC와 MVP의 의존성 및 유지보수성을 개선하기 위해 등장하였다.<br><br>
View : 사용자에게 보여주는 UI부분을 담당 <br>
ViewModel : View에서 사용하는 data처리 및 관리 <br>
Model : DB저장소 접근 데이터 틀 <br>

### MVVM특징
- view와 viewmodel은 n:m 관계로 적용이 가능하다.
- 재사용성이 용이하다.
- 효과적인 역할 분담과 테스트 용이성이 증가한다.

<br>

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

<img src = "./img/Network구조도.PNG" width="90%" height="90%">
<br>

### Spinner

<img src = "./img/Spinner구조.PNG" width="90%" height="90%">
<br>

### RecyclerView

<img src = "./img/Recyclerview구조도.PNG" width="90%" height="90%">
<br>

### Fragment

<img src = "./img/Fragment구조도.PNG" width="90%" height="90%">
<br><br>

## 기능
다음은 music player의 기능이다.
<br><br>
### Music List 

<img src = "./img/KakaoTalk_20210429_231518818.jpg" width="40%" height="40%">

앱 초기화면으로 음악 리스트가 나열된다.<br>
<br>
<img src = "./img/KakaoTalk_20210429_231518620.jpg" width="40%" height="40%">

draggable 가능<br>
<br><br>
### Spinner 

<img src = "./img/KakaoTalk_20210429_231518454.jpg" width="40%" height="40%">

spinner 선택시 음악 list가 바뀐다.<br>
<br>
<img src = "./img/KakaoTalk_20210429_231518297.jpg" width="40%" height="40%">

<br>
<img src = "./img/KakaoTalk_20210429_231518134.jpg" width="40%" height="40%">

다음은 리스트가 변경된 모습이다.<br>
<br><br>
### 하단 플레이어

<img src = "./img/KakaoTalk_20210429_231517979.jpg" width="40%" height="40%">

하단 플레이어로 album image, title, singer와 음악 멈춤 및 다음 노래 기능이 존재한다.<br>
<br><br>

### 추가 기능
- 모든 음악은 끝나면 다음 노래로 자동으로 넘어간다. -> coroutine을 통해 mediaplayer의 state를 들어 노래가 끝나면 자동으로 다음 노래로 넘긴다.
- 마지막 음악에서 다음 노래로 넘어가는 경우 첫 음악으로 돌아간다.
- 음악을 듣다가 spinner로 가수 목록을 변경해도 전 가수의 노래list가 loop된다. 
<br><br><br>
_Contact me :_ &nbsp; 
[![Gmail Badge](https://img.shields.io/badge/Gmail-d14836?style=flat-square&logo=Gmail&logoColor=white&link=mailto:hschoi5542@gmail.com)](mailto:hschoi5542@gmail.com)

<br>

해당 프로젝트에는 API key가 내포되어 있어 보안상의 문제가 염려되어 채용절차가 끝난 후 비공개로 전환할 예정입니다. 참고 부탁드립니다.
