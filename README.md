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
  + [2.2) Use case description](#22--use-case-description) 
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
  
![UseCaseDiagram](https://user-images.githubusercontent.com/96044622/235103894-733cd82a-3673-4239-8932-115e7136a54d.png)

### 2.2) Use case description

<!-- 1. Register -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #1 : Register  </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    사용자가 TripTip의 기능들을 사용하기 위해 사용자 정보를 등록하는 기능
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
    TripTip 사용자로 등록되며 기능들을 사용할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    TripTip 사용자로 등록되지 않고 기능들을 사용할 수 없다.
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
    사용자가 TripTip에 회원가입을 한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 회원가입 버튼을 누르면 시작한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    사용자 정보를 입력받기 위한 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 아이디와 이메일 정보를 기입하고 중복체크 버튼을 누르고 사용 가능 메시지를 받는다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    사용자는 제출 양식에 맞게 나머지 필수 항목들을 작성하여 회원가입 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    이 Use case는 회원가입이 성공하면 끝나고, 로그인 페이지로 돌아간다.
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
    로그인 화면에서 닉네임과 비밀번호를 입력하고 로그인을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    회원 인증에 성공하여 권한에 맞는 TripTip 서비스를 사용할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    회원 인증에 실패하여 권한에 맞는 TripTip 서비스를 사용할 수 없다.
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
    Main Page에서 로그인 버튼을 누르거나 세션이 만료되면 Login Page로 이동한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 로그인 화면에서 닉네임과 비밀번호를 입력하고 로그인 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 입력 정보를 DB에서 조회하여 일치하는 회원 정보가 있는지 확인한다. 
    등록된 회원이라면 회원 인증에 성공하고 Home Page로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    이 Use case는 로그인이 성공하면 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    2a. 입력되지 않은 정보가 있는경우<br/>
    &nbsp;&nbsp;
        2a.1. 경고창으로 알린다. 입력되지 않은 폼에 포커싱한다.<br/>
    2b. 회원 정보가 일치하지 않는 경우<br/>
    &nbsp;&nbsp;
        2b.1. 아이디 혹은 비밀번호가 틀렸다는 메시지를 띄운다.<br/>
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    <= 2 Seconds
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 1번
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
    System level
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
    < 2 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 평균 하루 1번
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

<!-- 4. View user profile -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #4 : View user profile </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    유저가 자신의 프로필을 볼 수 있다.
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
    2023.04.28
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
    사용자가 프로필 아이콘을 클릭했을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    자신의 프로필 정보를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    자신의 프로필 정보를 확인하지 못한다.
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
    사용자가 서버에 저장된 자신의 정보를 확인하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 우상단의 프로필 아이콘을 클릭한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 데이터 베이스에서 해당 사용자 정보를 조회한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    이 Use case는 프로필 정보를 화면에 렌더링하면 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    2a. 통신 문제로 DB 조회가 실패하는 경우<br/>
    &nbsp;&nbsp;
    2a.1. 실패 메시지를 띄운다.
    </td>
  </tr>

  <!-- RELATED INFORMATION -->
  <tr><th colspan="2">RELATED INFORMATION</th></tr>
  <tr>
    <td>Performance</td>
    <td>
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 2번 이하
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 한 달 평균 1번
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 한 번
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
    Admin level
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
    < 2 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    Admin level
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
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
    < 1 second
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자 당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    사용자당 하루 평균 5회 이상
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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
    < 1 second
    </td>
  </tr>
  <tr>
    <td>Frequency</td>
    <td>
    None
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


## 3. Domain analysis

![EER Diagram](https://user-images.githubusercontent.com/96044622/235119853-b0a41478-6f40-4703-87a1-0a8da98ae7c6.png)

### 1) Subscriber

&nbsp;&nbsp;&nbsp;
유저의 기본 정보를 가진다. 닉네임, 비밀번호, 프로필 사진 등. 추후 기능 확장성을 고려하여 집 주소 필드를 가지고 있다.<br/>
&nbsp;&nbsp;&nbsp;
Domain 이름이 Subscriber인 것은 User 이름이 이미 MySQL에서 예약어로 사용되고 있기 때문에 대체어로 채택하였다.

### 2) Member

&nbsp;&nbsp;&nbsp;
유저와 그룹의 다대다 관계를 분리하기 위한 중계 테이블이다. 유저가 그룹에 가입하면 Member 테이블에 정보가 추가된다. 유저가 그룹을 탈퇴하면 Member 테이블에서 정보가 삭제된다.

### 3) Group

&nbsp;&nbsp;&nbsp;
그룹 정보를 관리한다. leader 필드가 참조하는 유저는 해당 그룹의 생성자로서, 일반 멤버보다 많은 권한을 수행할 수 있다. 그룹에 소속된 멤버는 단기 모임이 될 수도 있지만 정기적으로 모이는 동호회나 동아리 등의 그룹이 될 수도 있다.

### 4) Meeting

&nbsp;&nbsp;&nbsp;
그룹 내에서 진행되는 다양한 모임을 분류할 수 있으며, 모임 이름과 생성일자 등을 관리하고 있다. 해당 모임에서 발생한 결제 내역에 대한 정산을 실행해도, 그룹 내 다른 진행 중인 모임에 영향을 주지 않는다.

### 5) Receipt

&nbsp;&nbsp;&nbsp;
모임 내에서 발생하는 결제 내역을 관리한다. 내역은 결제한 당사자가 작성하며, 결제 금액은 모임의 리더가 정산을 실행할 때, 모임 내의 멤버들에게 분배된다.

### 6) Participant

&nbsp;&nbsp;&nbsp;
Subscrber와 Receipt의 다대다 관계를 분리하기 위한 중계 테이블이다.

## 4. User Interface prototype

### 1) Home Page : Not Signed

![MainPage](https://user-images.githubusercontent.com/96044622/235105183-ad5d0356-38ce-47cc-9a18-b357db1aa5ed.png)

&nbsp;&nbsp;&nbsp;
사용자가 로그인 하지 않은 상태로 TripTip Main Page에 접속하면 나타나는 UI다. TripTip에 대한 소개와 로그인/회원가입 버튼이 있다.

### 2) Login Page

![login](https://user-images.githubusercontent.com/96044622/235107564-d1d0077c-bfcf-40a2-8919-f4e674c9bf3c.png)

&nbsp;&nbsp;&nbsp;
사용자가 로그인 하기 버튼을 누르거나 세션이 만료된 경우, 다음과 같은 로그인 화면으로 이동한다. 가입된 회원이라면 정보를 입력 후 로그인 버튼을 누르면 메인 화면으로 넘어간다. 가입된 회원이 아닐 때 회원가입 버튼을 누르면 회원가입 화면으로 넘어간다.<br/>
&nbsp;&nbsp;&nbsp;
화면 하단에 소셜 로그인 버튼이 추가되어야 한다.

### 3) Register Page

![회원가입](https://user-images.githubusercontent.com/96044622/235107698-f3c84638-5b56-4778-85b2-7e9cbe81f8a6.png)

&nbsp;&nbsp;&nbsp;
사용자가 회원가입 버튼을 누르면 회원가입 페이지로 이동한다. 회원이 입력한 정보가 유효하지 않을 경우, 회원가입 버튼을 눌러도 회원가입이 되지 않는다. 회원가입이 완료되면 로그인 페이지로 이동한다. <br/>
&nbsp;&nbsp;&nbsp;
페이지 좌상단에 뒤로 가기 버튼이 추가되어야 한다. 해당 버튼을 클릭하면 회원가입을 하지 않고도 다시 로그인 화면으로 이동할 수 있다.

### 4) Home Page : Signed

![SECOND MAIN](https://user-images.githubusercontent.com/96044622/235108431-964ccb28-a3ab-4b48-bdaf-3209e92b6426.png)

&nbsp;&nbsp;&nbsp;
로그인을 하거나, 세션이 만료되지 않은 사용자가 TripTip Main Page에 접근했을 때 나타나는 화면이다. 우상단의 벨 모양 버튼을 누르면 최근 알림을 확인할 수 있고, 프로필 이미지를 누르면 My Profile Page로 이동한다. <br/>
&nbsp;&nbsp;&nbsp;
그룹 참여 및 생성 버튼이 있고 가장 최근에 가입한 4개의 그룹 리스트를 확인할 수 있다. 활동 내역을 클릭하면 전체 그룹 리스트를 확인할 수 있는 Page로 이동한다.

### 5) My Profile Page

![내 정보](https://user-images.githubusercontent.com/96044622/235108527-acf0b4dd-c498-461b-974c-9b7dd7cadbc2.png)

&nbsp;&nbsp;&nbsp;
사용자는 자신의 정보를 확인할 수 있다. 프로필 사진과 이름, 주 계좌번호와 정산 관련 내역들이 한 눈에 보기 쉽도록 나타난다.

### 6) Group List Page

![GroupList](https://user-images.githubusercontent.com/96044622/235108631-8fd549c8-2637-4f63-9245-9b744f53778a.png)

### 7) Group Create Page

![GroupCreate](https://user-images.githubusercontent.com/96044622/235109207-76cb0a71-4709-4418-857b-bced06ead9b2.png)

### 8) User Search Page

![User Search](https://user-images.githubusercontent.com/96044622/235109341-b2d2ccc4-5d38-4d18-89c9-d3b158801b97.png)

### 9) Group Join Page

![그룹 참여](https://user-images.githubusercontent.com/96044622/235109435-29e8dc15-3fed-4a20-ba27-497b9c027a1d.png)

### 10) Group Detail Page

![Group Detail](https://user-images.githubusercontent.com/96044622/235110091-163d094c-4003-4e6f-9253-46593439060c.png)

### 11) Meeting Detail Page

![Meeting Detail](https://user-images.githubusercontent.com/96044622/235110829-7db69308-3c7b-41e9-b17f-dde0586179c2.png)

### 12) Meeting Update Page

![Meeting Update](https://user-images.githubusercontent.com/96044622/235111126-ae6702af-4082-4767-a67d-82ecf82a5270.png)

### 13) Receipt Create Page

![N분의 1](https://user-images.githubusercontent.com/96044622/235111414-769ccd8a-5908-4355-a519-70f343c131c0.png)

![직접 입력](https://user-images.githubusercontent.com/96044622/235111522-130d00bb-6962-4073-aeb1-d1b226884db7.png)

### 14) Receipt Detail Page

![Receipt Detail](https://user-images.githubusercontent.com/96044622/235112003-ef942e8e-d065-43e6-9f33-c9ce12663930.png)

### 15) Clear Page

![CLEAR](https://user-images.githubusercontent.com/96044622/235111631-ec0e8d3d-2f5a-4b06-b6a4-f81e13703a41.png)

### 16) Notification Page

![알림](https://user-images.githubusercontent.com/96044622/235111662-676c5033-ddca-4ee6-be8f-1d384e3e370f.png)

## 5. Glossary

## 6. References

1) 하루 5분 UX, 조엘 마시 지음
