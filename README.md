# 모임 정산 관리 서비스. trip-tip
Software Design Web Service

<div align="center">
  <image src="./src/main/resources/static/img/trip_tip_logo.png" alt="logo" widt="200" height="200"/>
</div>

<table style="border: 1px;" align="center">
  <tr>
    <td> <b>Student No</b> </td>
    <td> 21911407 </td>
  </tr>
  <tr>
    <td> <b>Name</b> </td>
    <td> 양재서 </td>
  </tr>
  <tr>
    <td> <b>E-Mail</b> </td>
    <td> qud1251@gmail.com </td>
  </tr>
</table>

## [ Revision history ]

|Revision date|Verision #|Description|Author|
|:---:|:---:|:---:|:---:|
|2023/04/05|v1.0.0-alpha|First Writing & Modeling||
| | | | |
| | | | |
| | | | |
| | | | |

## [ Contents ]

- [1. Introduction](#1-introduction)
  + [1.1) Summary](#11--summary)
  + [1.2) Business Goals](#12--business-goals)
  + [1.3) Technical Goals](#1-3--technical-goals)
- [2. Use case analysis](#2-use-case-analysis)
  + [2.1) Use case diaram](#21--use-case-diagram)
  + [2.1) Use case description](#22--use-case-description) 
- [3. Domain analysis](#3-domain-analysis)
- [4. User Interface prototype](#4-user-interface-prototype)
- [5. Glossary](#5-glossary)
- [6. References](#6-references)
  
## 1. Introduction
### 1.1) Summary  

&nbsp;&nbsp;&nbsp;
모임이나 친구들과의 여행을 하면서 발생한 지출에 대해 정산을 해야 하지만, 이를 지속적으로 관리하기란 상당히 번거롭고 어려운 일이다.
이미 수많은 더치페이 정산 시스템이 마련되어 있지만, 사용자들이 지속적으로 정산을 한 후에 최종적인 청구 기능밖에 존재하지 않는다.
총무라는 역할을 누군가는 수행해야 하지만 그 자리가 주는 스트레스와 부담감으로 인해 누구나 선뜻 자처하려는 사람이 없다.  
  
&nbsp;&nbsp;&nbsp;
이러한 문제를 해결하기 위하여 모임의 정산 내역을 지속적으로 쉽고 간편하게 관리할 수 있는 정산 서비스 Trip-Tip을 만들게 되었다.
  
### 1.2) Business Goals  

&nbsp;&nbsp;&nbsp;
Trip-Tip 서비스는 모임에 참여하는 인원들이 결제한 기록들을 갱신해주기만 하면, 총무의 역할을 대신하여 정산 비용을 계산하고 이를 지속적으로 관리해주는 것이 목적이다. 그리고 모든 결제 내역들을 시간 순으로 나열하여 상세 정보를 파악할 수 있도록 하여, 총무가 제대로 계산했는지에 대한 서로의 불신을 없앨 수 있다. "누가, 언제, 어디서, 무엇을, 어떻게, 왜" 결제하였는지 모임 인원 전체가 확인할 수 있도록 하여 모임 기간 동안의 불필요한 스트레스를 제거할 수 있게 한다.
  
### 1.3) Technical Goals  

&nbsp;&nbsp;&nbsp;
최종 목표는 AWS 혹은 Oracle Cloude에 개발한 Application을 올려 하나의 서버를 구축하는 것이다. Server는 회원 정보와 정산 비용, 결제 내역, 멤버 현황 등 모임에 관련된 정보를 DB를 통해 관리하며, 인증·인가 과정을 통해 적합한 서비스 사용자인지 구분한다. DB의 데이터는 ADMIN 역할을 부여받은 계정만이 접근할 수 있도록 한다.  
&nbsp;&nbsp;&nbsp;
사용자는 일반 멤버일 수도, 모임을 생성한 모임장일 수도 있다. 이 둘을 구분하기 위해서 Manager 역할을 부하기 보다는 Group 테이블의 Foreign key를 이용하여 구분할 수 있도록 할 것이다. 결제 내역이 추가·수정·삭제 될 때마다 최종 정산 결과가 올바른 값이 나올 수 있도록 적합한 알고리즘을 선택한다. 또한 누구나 쉽고 간단하게 사용할 수 있는 UX/UI 설계를 통해, 서비스 접근성을 높인다.

## 2. Use case analysis

### 2.1) Use case diagram
  
![UseCaseDiagram](https://user-images.githubusercontent.com/96044622/231636424-81f79902-9bca-430d-9b39-af34292699e0.png)

### 2.2) Use case description

<!-- 1. Register -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #1 : Register  </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    클라이언트가 TripTip의 기능들을 사용하기 위해 사용자 정보를 등록하는 기능
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.13
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    TripTip System이 실행되어야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    Main Page 혹은 Login Page에서 회원가입 버튼을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    TripTip 클라이언트로 등록되며 기능들을 사용할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    TripTip 클라이언트로 등록되지 않고 기능들을 사용할 수 없다.
    </td>
  </tr>
  
  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    클라이언트가 TripTip에 회원가입을 한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    클라이언트가 회원가입 버튼을 누르면 시작한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    클라이언트 정보를 입력받기 위한 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    클라이언트는 아이디와 이메일 정보를 기입하고 중복체크 버튼을 누르고 사용 가능 메시지를 받는다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    클라이언트는 제출 양식에 맞게 나머지 필수 항목들을 작성하여 회원가입 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    회원가입이 완료되고, 로그인 페이지로 돌아간다.
    </td>
  </tr>
  
  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    3a. 닉네임 혹은 이메일이 중복되는 경우 <br/>
    &nbsp;&nbsp;
      3a.1. 사용할 수 없는 닉네임 혹은 이메일임을 경고창으로 알린다.<br/>
    &nbsp;&nbsp;
      3a.2. 회원가입 페이지로 돌아온다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 필수 항목을 모두 기입하지 않은 경우 <br/> 
    &nbsp;&nbsp;
      4a.1. 기입하지 않은 입력폼이 있음을 경고창으로 알린다.<br/>
    &nbsp;&nbsp;
      4a.2. 미기입 항목이 복수 개라면 최상단의 항목으로 포커싱한다.<br/>
    4b. 유효한 패스워드 패턴이 아닌 경우 <br/>
    &nbsp;&nbsp;
      4b.1. 잘못된 패스워드 패턴임을 경고창으로 알린다.<br/>
    &nbsp;&nbsp;
      4b.2. 패스워드 입력 항목으로 포커싱한다.<br/>
    4c. 기존 회원 정보가 존재하는 경우 <br/>
    &nbsp;&nbsp;
      4c.1. 같은 이메일로 등록된 계정일 경우 회원 생성 불가 문구를 경고창으로 알린다.<br/>
    &nbsp;&nbsp;
      4c.2. 회원가입 창으로 리다이렉션한다.
    </td>
  </tr>
  
  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    < 2 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자 당 1번
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    No Limit
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 2. Log in -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #2 : Log in </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    사용자 모두가 TripTip의 기능들을 사용하기 위해 회원 인증을 받는 기능
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.26
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 TripTip DB에 회원 정보가 등록되어 있어야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 3. Social login -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #3 : Social login </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 4. View user profile -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #4 : View user profile </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 5. Update user profile -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #5 : Update user profile </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 6. Delete user profile -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #6 : Delete user profile </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 7. View client list -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #7 : View client list </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    Admin Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    Administrator
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    어드민은 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 8. Delete client -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #8 : Delete client </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    Admin Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    Administrator
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    어드민은 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 9. Create group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #9 : Create group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 10. Search user -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #10 : Search user </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 11. Join group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #11 : Join group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 12. View group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #12 : View group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 13. Search group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #13 : Search group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 14. Exit group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #14 : Exit group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 15. Create meeting -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #15 : Create meeting </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 16. View meeting -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #16 : View meeting </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 17. Search meeting -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #17 : Search meeting </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 18. Update meeting -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #18 : Update meeting </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 19. Delete meeting -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #19 : Delete meeting </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 20. Add receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #20 : Add receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 21. View receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #21 : View receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 22. Search receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #22 : Search receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 23. Update receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #23 : Update receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 24. Delete receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #24 : Delete receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 25. Delete group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #25 : Delete group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    Leader
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임의 리더 권한을 가지고 있으며 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 26. Add member -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #26 : Add member </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임의 리더 권한을 가지고 있으며 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 27. Expel member -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #27 : Expel member </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임의 리더 권한을 가지고 있으며 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>

<!-- 28. Calculate cost -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #28 : Calculate cost </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Scope</td>
    <td>
    TripTip
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    User Level
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    YANG JAESEO
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    2023.04.
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    Analysis
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    User
    </td>
  </tr>
  <tr>
    <td>Preconditions</td>
    <td>
    사용자는 해당 모임의 리더 권한을 가지고 있으며 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    -
    </td>
  </tr>

  <!-- MAIN SUCCESS SCENARIO -->
  <tr><th colspan="2">MAIN SUCCESS SCENARIO</th></tr>
  <tr>
    <td>Step</td>
    <td>Action</td>
  </tr>
  <tr>
    <td>S</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    -
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    -
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Concurrency</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Due Date</td>
    <td>
    -
    </td>
  </tr>
</table><br/>


## 3. Domain analysis

### 1) Subscriber

### 2) Member

### 3) Group

### 4) Meeting

### 5) Receipt

### 6) Participant

### 7) BaseDateEntity


## 4. User Interface prototype

### 1) Main Page

### 2) Login Page

### 3) Register Page

### 4) Create Group

### 5) User Profile Page

### 6) Group Page

### 7) Join Group Page

### 8) 


## 5. Glossary



## 6. References


