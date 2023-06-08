# 모임 정산 관리 서비스. trip-tip
### 2. Analysis
Software Design Web Service

<div align="center">
  <image src="https://user-images.githubusercontent.com/96044622/235833899-f051ffa9-55f4-4b6c-ad7a-1a563fe15741.png" alt="logo" widt="200" height="200"/>
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

| Revision date |  Verision #  |       Description        |   Author    |
|:-------------:|:------------:|:------------------------:|:-----------:|
|  2023/04/05   | v1.0.0-alpha | First Writing & Modeling | YANG JAESEO |
|  2023/06/08   |    v1.1.0    | Update Use case Diagram  | YANG JAESEO |
|               |              |                          |             |
|               |              |                          |             |
|               |              |                          |             |

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
  
![UseCaseDiagram](https://github.com/psychology50/trip-tip/assets/96044622/10e4ef87-9224-4986-80a5-09f31b5a0316)

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
    사용자가 OAuth를 통해 소셜 로그인을 하는 기능
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
    2023.05.03
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
    사용자는 해당 소셜 서비스에 가입되어 있어야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    로그인 화면에서 소셜 로그인 버튼을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    소셜 로그인에 성공하여 권한에 맞는 TripTip 서비스를 사용할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    소셜 로그인에 실패하여 권한에 맞는 TripTip 서비스를 사용할 수 없다.
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
    사용자가 소셜 로그인을 통해 TripTip 서비스에 로그인하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    Login Page에서 소셜 로그인 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    소셜 로그인을 위한 인증 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 소셜 로그인을 위한 인증 페이지에서 소셜 로그인을 한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
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
    2a. 소셜 로그인을 위한 인증 페이지로 이동하지 못하는 경우<br/>
      &nbsp;&nbsp;
      2a.1. 해당 계정으로 로그인할 수 없다는 팝업을 띄운다.<br/>
      &nbsp;&nbsp;
      2a.2. 로그인 페이지로 이동한다.<br/>
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    3a. 소셜 로그인을 실패한 경우<br/>
      &nbsp;&nbsp;
      3a.1. 해당 계정으로 로그인할 수 없다는 팝업을 띄운다.<br/>
      &nbsp;&nbsp;
      3a.2. 로그인 페이지로 이동한다.<br/>
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
    2a.1. 시스템은 사용자에게 프로필 조회 실패 메시지를 띄운다.<br/>
    &nbsp;&nbsp;
    2a.2. 시스템은 현재 사용자 페이지를 유지한다.
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
    사용자가 자신의 프로필을 수정하는 기능
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
    2023.05.02
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
    프로필 수정 페이지에서 수정 버튼을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    수정한 정보가 반영되어 사용자 프로필이 갱신된다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    수정한 정보가 반영되지 않고 기존 사용자 프로필 정보를 유지한다.
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
    User Profile Page에서 수정 버튼을 누르면 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 기존 정보가 입력되어 있는 폼을 돌려받는다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    수정하고 싶은 자신의 정보를 재작성하고 수정 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 입력 정보를 데이터 베이스에 반영하고, 사용자는 프로필 페이지로 리다이렉션 한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    이 Use case는 정보 수정이 성공하면 끝난다.
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
    2a. 사용자가 입력한 정보가 유효하지 않은 경우<br/>
    &nbsp;&nbsp;
    2a.1. 시스템은 사용자에게 유효하지 않은 정보임을 알리는 메시지를 띄운다.<br/>
    &nbsp;&nbsp;
    2a.2. 시스템은 정보 수정을 거부하고 사용자가 정보를 수정하던 페이지를 유지한다.
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
    사용자가 TripTip 서비스를 더이상 사용하지 않기 위해 데이터 베이스에서 계정 정보를 제거하는 기능
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
    2023.05.02
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
    사용자 프로필 페이지에서 계정 삭제 버튼을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자의 계정 정보가 데이터 베이스에서 제거되고 더이상 TripTip 서비스를 이용할 수 없다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자의 계정 정보가 데이터 베이스에서 제거되지 않는다.
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
    사용자가 계정을 삭제하고 싶을 때 시작한다
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 Profile Page에서 계정 삭제 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자에게 계정 삭제를 확인하는 메시지를 띄운다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자가 계정 삭제를 확인하는 메시지를 확인하고 계정 삭제를 진행한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자의 계정 정보를 데이터 베이스에서 제거한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    시스템은 사용자에게 계정 삭제가 완료되었음을 알리는 메시지를 띄운다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    시스템은 사용자를 로그아웃 시키고 메인 페이지로 이동시킨다.
    </td>
  </tr>
  <tr>
    <td>7</td>
    <td>
    이 Use Case는 사용자의 계정이 삭제되면 끝난다.
    </td>
  </tr>


  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 사용자의 계정 정보를 데이터 베이스에서 제거하는데 실패한다.<br/>
    &nbsp;&nbsp;
    4a.1. 시스템은 사용자에게 계정 삭제가 실패했음을 알리는 메시지를 띄운다.<br/>
    &nbsp;&nbsp;
    4a.2. 시스템은 아무런 작업도 수행하지 않고 Profile Page를 유지한다.<br/>
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
    시스템 관리자가 TripTip 서비스를 이용하는 사용자들의 목록을 확인한다.
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
    2023.05.02
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
    어드민은 관리자 권한을 가진 계정으로 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    어드민이 사용자 목록을 확인하고 싶을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    어드민은 사용자 목록을 확인한다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    어드민은 사용자 목록을 확인하지 못한다.
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
    어드민이 사용자 목록을 확인하고 싶어할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    어드민은 관리자 페이지로 이동한다. 
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    어드민은 관리자 페이지에서 회원 관리 페이지로 이동한다.
    </td>'
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 회원 정보를 모두 탐색하여 어드민에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    이 Use case는 회원 정보 리스트가 어드민에게 보여지면 끝난다.
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
      1a. 어드민이 권한 인증에 실패한다. <br/>
      &nbsp;&nbsp;
      1a.1. 시스템은 어드민에게 권한 인증에 실패했음을 알리는 메시지를 띄운다. <br/>
      &nbsp;&nbsp;
      1a.2. 어드민은 관리자 페이지 접근에 실패한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    3a. 시스템은 회원 정보를 탐색하는데 실패한다. <br/>
    &nbsp;&nbsp;
    3a.1. 시스템은 어드민에게 회원 정보 탐색에 실패했음을 알리는 메시지를 띄운다. <br/>
    &nbsp;&nbsp;
    3a.2. 어드민은 회원 정보를 확인하지 못하고 관리자 페이지로 돌아간다.
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
    어드민은 사용자를 삭제한다.
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
    2023.05.02
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
    어드민은 관리자 권한을 가진 계정으로 로그인된 상태이고 통신이 가능해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    어드민이 사용자를 삭제하고 싶어할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    어드민은 사용자를 삭제한다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    어드민은 사용자를 삭제하지 못한다.
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
    어드민이 특정 사용자를 삭제하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    어드민은 TripTip 회원 정보를 조회한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    어드민은 특정 사용자를 선택하고 삭제 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 사용자를 DB에서 제거하고 어드민에게 사용자 삭제가 완료되었음을 알린다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    이 Use case는 성공적으로 선택한 유저가 DB에서 삭제되면 끝난다.
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
      1a. 시스템은 회원 정보를 탐색하는데 실패한다. <br/>
        &nbsp;&nbsp;
        1a.1. 시스템은 어드민에게 회원 정보 탐색에 실패했음을 알리는 메시지를 띄운다. <br/>
        &nbsp;&nbsp;
        1a.2. 어드민은 회원 정보를 확인하지 못하고 관리자 페이지로 돌아간다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
      2a. 시스템은 사용자를 DB에서 제거하는데 실패한다. <br/>
        &nbsp;&nbsp;
        2a.1. 시스템은 어드민에게 사용자 삭제에 실패했음을 알리는 메시지를 띄운다. <br/>
        &nbsp;&nbsp;
        2a.2. 어드민은 사용자를 삭제하지 못하고 유저 조회 페이지를 유지한다.
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
    유저가 그룹을 생성한다.
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
    2023.05.02
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
    사용자가 그룹을 생성하고 싶을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    그룹이 생성되고 그룹에 대한 정보가 DB에 저장된다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    그룹이 생성되지 않고 DB에 저장되지 않는다.
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
    사용자가 그룹을 생성하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 Main Page에서 그룹 생성 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    사용자는 그룹 이름과 멤버를 선택하고 생성 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 입력 정보를 토대로 그룹 데이터를 DB에 등록하고 그룹 코드를 생성한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 초대된 사용자들에게 그룹에 초대되었음을 알리는 메시지를 보낸다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    사용자는 그룹 생성이 성공하면, 생성된 Group Page로 이동한다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    이 Use case는 그룹이 생성되면 끝난다.
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
    3a. 사용자가 그룹 이름을 입력하지 않은 경우 <br/>
      &nbsp;&nbsp;
      3a.1. 시스템은 사용자에게 그룹 이름을 입력하라는 메시지를 띄운다.<br/>
      &nbsp;&nbsp;
      3a.2. 그룹 입력 폼으로 포커싱한다.
    3b. 사용자가 그룹 멤버를 선택하지 않은 경우<br/>
      &nbsp;&nbsp;
      3a.1. 시스템은 사용자에게 그룹 멤버를 적어도 1명 선택하라는 메시지를 띄운다.
    </td>
  </tr>
  <tr>
    <td>4</td>
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
    사용자가 그룹에 초대할 사용자를 검색한다.
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
    2023.05.02
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
    사용자가 그룹에 초대할 사용자를 검색하고 싶을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자가 그룹에 초대할 사용자를 검색한다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자가 그룹에 초대할 사용자를 검색하지 못한다.
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
    사용자가 그룹에 초대할 사용자를 검색하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    그룹 생성 페이지에서 사용자는 멤버 편집 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자가 그룹에 초대할 사용자를 검색할 수 있는 검색창과 선택된 멤버 리스트를 띄운다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 검색창에 유저 닉네임을 입력하고 엔터 혹은 검색 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 입력한 글자를 포함하는 사용자 닉네임을 검색하고 결과를 보여준다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    사용자는 검색 결과에서 그룹에 초대할 사용자를 선택하고 추가를 누른다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    시스템은 사용자가 선택한 사용자를 멤버 리스트에 추가한다.
    </td>
  </tr>
  <tr>
    <td>7</td>
    <td>
    사용자는 잘못 추가한 멤버를 삭제하고 싶다면 멤버 리스트에서 해당 사용자를 선택하고 삭제 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>8</td>
    <td>
    시스템은 사용자가 선택한 사용자를 멤버 리스트에서 삭제한다.
    </td>
  </tr>
  <tr>
    <td>9</td>
    <td>
    사용자는 완료 버튼을 누르면 멤버 편집 화면에서 나가고 그룹 생성 페이지로 돌아간다.
    </td>
  </tr>
  <tr>
    <td>10</td>
    <td>
    이 Use case는 선택한 멤버 리스트가 결과 리스트에 반영되면 종료된다.
    </td>
  </tr>
  

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 사용자가 입력한 글자를 포함하는 사용자 닉네임이 없는 경우 <br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 빈 결과 화면을 렌더링한다.<br/>
    4b. 네트워크 문제등의 사유로 조회가 지연되는 경우<br/>
      &nbsp;&nbsp;
      4b.1. 비동기 작업으로 데이터가 도착하기 전까지 로딩 화면을 띄운다.<br/>
      &nbsp;&nbsp;
      4b.2. 데이터가 도착하면 로딩 화면을 제거하고 결과 화면을 렌더링한다.
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

<!-- 11. Join group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #11 : Join group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    사용자가 이미 존재하는 그룹에 가입하기 위한 기능
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
    2023.05.02
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
    사용자는 로그인된 상태이고 통신이 가능해야 한다. <br/>
    가입하려는 그룹이 존재해야 하며, 그룹 코드가 유효해야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    그룹 코드를 입력하고 참여 버튼을 눌렀을 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에 가입되고, 그룹 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에 가입되지 않고, 실패 메시지를 받는다. 
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
    사용자가 기존의 그룹에 참여하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 참여하고자 하는 그룹 코드를 입력하고 참여 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 그룹 코드와 매칭되는 그룹을 탐색하고 사용자를 멤버로 등록한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 사용자를 해당 그룹의 페이지로 이동시킨다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    이 Use case는 사용자가 해당 그룹의 멤버로 등록되면 끝난다.
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
    2a. 그룹 코드가 유효하지 않은 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 그룹이 존재하지 않는다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      2a.2. 그룹 참여 페이지를 유지하고 다시 그룹 코드를 입력하도록 한다.
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
    사용자가 그룹 페이지를 조회하는 기능
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
    2023.05.02
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
    사용자가 그룹 페이지를 조회하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 그룹 페이지를 조회하고, 그룹 페이지에 있는 정보를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 그룹 페이지를 조회하지 못한다.
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
    사용자가 소속되어 있는 그룹 페이지를 조회하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 조회하고자 하는 그룹 이미지 혹은 이름을 클릭한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자가 선택한 그룹 정보를 DB에서 조회하여 사용자에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자가 해당 그룹 페이지로 이동하면 이 Use case는 끝난다.
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
    2a. 통신 문제로 인해 그룹 정보를 조회하지 못하는 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 통신 문제가 발생했다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      2a.2. 현재 페이지를 유지하고 다시 그룹 정보를 조회하도록 한다.
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
    사용자가 소속된 그룹을 검색하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹 리스트에서 특정 그룹을 검색하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 검색한 그룹을 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 검색한 그룹을 확인할 수 없다.
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
    사용자가 소속된 그룹 리스트에서 특정 그룹을 검색하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 검색창에 검색하고자 하는 그룹의 이름을 입력하고 검색 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템이 사용자가 입력한 그룹 이름을 DB에서 조회하여 사용자에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    이 Use case는 사용자가 원하는 그룹 검색에 성공하면 끝난다.
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
    1a. 사용자가 검색창에 아무것도 입력하지 않은 경우<br/>
      &nbsp;&nbsp;
      1a.1. 시스템은 사용자에게 검색어를 입력하라는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      1a.2. 검색창에 포커싱하여 다시 이름을 검색하도록 유도한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    2a. 통신 문제로 인해 그룹 정보를 조회하지 못하는 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 통신 문제가 발생했다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      2a.2. 현재 페이지를 유지하고 다시 그룹 정보를 조회하도록 한다.<br/>
    2b. 매칭되는 그룹명이 존재하지 않는 경우<br/>
      &nbsp;&nbsp;
      2b.1. 시스템은 사용자에게 빈 화면을 보여준다.
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
    사용자가 소속된 그룹에서 탈퇴하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 탈퇴하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 탈퇴하고 DB의 멤버 정보가 삭제된다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 탈퇴하지 못한다.
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
    사용자가 소속된 그룹에서 탈퇴하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 그룹 탈퇴 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자에게 정말로 탈퇴하시겠습니까? 라는 메시지를 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 예 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 소속된 그룹에서 탈퇴하고 DB의 멤버 정보가 삭제된다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    시스템은 사용자에게 탈퇴가 완료되었다는 메시지를 보여주고 그룹 목록 화면으로 이동하면 이 Use case는 종료된다.
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
    3a. 사용자가 '아니오' 버튼을 선택한 경우<br/>
      &nbsp;&nbsp;
      3a.1. 그룹 탈퇴 프로세스가 종료되며, 해당 그룹 페이지로 돌아간다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 통신 문제로 인해 DB에 멤버 정보가 삭제되지 않은 경우<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 사용자에게 탈퇴가 실패하였다는 메시지를 보여준다.
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
    사용자가 그룹 내에서 모임을 생성하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 모임을 생성하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 생성하고 DB에 모임 정보가 저장된다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 생성하지 못한다.
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
    멤버가 그룹 내에서 모임을 생성하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 모임 생성 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자 모임 생성을 위한 정보를 입력받기 위해 Create Meeting Page로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 모임 이름과 미팅 날짜를 선택하고 생성 버튼을 누른다. (default는 오늘 날짜)
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 입력한 정보를 DB에 모임 테이블에 저장한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    이 Use case는 모임이 생성되면 종료된다.
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
    3a. 사용자가 모임 정보를 모두 입력하지 않은 경우<br/>
      &nbsp;&nbsp;
      3a.1. 시스템은 사용자에게 해당 정보를 입력하라는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      3a.2. 입력하지 않은 폼을 포커싱한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. DB에 모임 정보를 저장하는 과정에서 오류가 발생한 경우<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 사용자에게 모임 정보 저장에 실패했다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      4a.2. 사용자는 모임 생성 페이지로 돌아가 다시 모임 생성을 시도할 수 있다.
    </td>
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
    사용자가 그룹 내에서 모임을 조회하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임을 조회하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 조회하고 모임 정보를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 조회하지 못한다.
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
    사용자가 그룹 내에서 모임을 조회하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 그룹 내에서 조회하고 싶은 모임을 클릭한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자가 선택한 모임의 정보를 DB에서 데이터를 조회하여 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자가 해당 모임 페이지로 이동하면 이 Use case는 끝난다.
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
    2a. DB에서 데이터를 조회하는 과정에서 오류가 발생한 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 모임 정보 조회에 실패했다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      2a.2. 사용자는 모임 조회 페이지로 돌아가 다시 모임 조회를 시도할 수 있다.
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
    사용자가 모임을 검색하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임을 검색하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 검색하고 검색 결과를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 검색하지 못한다.
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
    사용자가 그룹 내에서 모임을 검색하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 그룹 내에서 검색하고 싶은 모임의 이름을 입력하거나 날짜를 선택한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자 원하는 미팅 정보를 DB에서 데이터를 조회하여 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자가 해당 모임 정보를 확인하면 이 Use case는 끝난다.
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
    1a. 사용자가 검색하고자 하는 모임의 이름을 입력하지 않은 경우<br/>
      &nbsp;&nbsp;
      1a.1. 시스템은 사용자에게 모임 이름을 입력하라는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      1a.2. 모임 입력 폼에 포커스를 준다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    2a. 시스템이 통신 문제로 인해 DB에서 해당 모임 정보를 찾지 못한 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 모임 정보 조회에 실패했다는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      2a.2. 사용자는 모임 조회 페이지로 돌아가 다시 모임 조회를 시도할 수 있다.
    </td>
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
    사용자가 모임을 정보를 수정하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임을 정보 수정하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 수정하고 수정 결과를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 수정하지 못한다.
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
    사용자가 소속된 그룹에서 특정 모임 정보를 수정하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 모임 수정 버튼을 누르면 모임 정보 수정 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 기존 모임 정보가 입력폼에 들어가있는 상태로 모임 정보 수정 페이지를 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 모임 정보를 수정하고 수정 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 입력한 모임 정보를 DB에 저장하고 해당 모임 페이지로 이동시킨다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    이 Use case는 변경사항이 반영되면 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 시스템이 통신 문제로 인한 DB 수정이 실패한 경우 <br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 사용자에게 모임 정보 수정 실패를 알린다. <br/>
      &nbsp;&nbsp;
      4a.2. 시스템은 모임 정보 수정 페이지로 이동시킨다.
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
    사용자가 모임을 삭제하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임을 삭제하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 삭제하고 삭제 결과를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임을 삭제하지 못한다.
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
    사용자가 소속된 그룹에서 특정 모임을 삭제하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 모임 삭제 버튼을 누르면 모임 삭제 확인 팝업이 뜬다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    사용자가 모임 삭제 확인 팝업에서 확인 버튼을 누르면 시스템은 해당 모임을 삭제한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 해당 모임을 삭제하고 삭제 결과를 사용자에게 알린다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    모임 정보가 DB에서 삭제되고 사용자가 소속된 그룹 페이지로 이동하면 Use case가 끝난다.
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
    2a. 사용자가 모임 삭제 확인 팝업에서 취소 버튼을 누른 경우 <br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 모임 삭제 프로세스를 취소하고 해당 모임 페이지를 유지한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    3a. 시스템이 통신 문제로 인한 DB 삭제가 실패한 경우 <br/>
      &nbsp;&nbsp;
      3a.1. 시스템은 사용자에게 모임 삭제 실패를 알린다. <br/>
      &nbsp;&nbsp;
      3a.2. 해당 모임 페이지를 유지한다.
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
    사용자가 영수증을 추가하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임에 영수증을 추가하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 모임에 영수증을 추가하고 데이터가 DB에 반영된다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임에 영수증을 추가하지 못한다.
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
    사용자가 소속된 그룹에서 특정 모임에 영수증을 추가하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자가 특정 모임 내에서 영수증 추가 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    사용자는 영수증 추가 화면으로 이동하고, 결제자로 취급된다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    결제자는 비용을 청구할 멤버를 선택한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    결제자는 청구할 멤버별로 비용을 기입하거나 더치 페이를 선택한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    결제자는 실물 영수증을 추가하고 싶은 경우, 앨범 아이콘을 눌러 사진을 첨부한다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    결제자는 영수증 이름과 결제 시간을 작성한다.
    </td>
  </tr>
  <tr>
    <td>7</td>
    <td>
    등록 버튼을 누르면 시스템은 영수증 정보를 DB에 저장하고 사용자에게 영수증 추가 성공을 알린다.
    </td>
  </tr>
  <tr>
    <td>8</td>
    <td>
    이 Use Case는 영수증 정보가 DB에 저장되고, 사용자가 모임 페이지로 리다이렉션하면 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>7</td>
    <td>
    7a. 통신 문제로 인해 DB 저장이 실패한 경우<br/>
      &nbsp;&nbsp;
      7a.1. 시스템은 사용자에게 영수증 추가에 실패했음을 알린다.<br/>
      &nbsp;&nbsp;
      7a.2. 시스템은 사용자에게 영수증 추가 화면으로 리다이렉션 한다.<br/>
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
    사용자가 영수증을 조회하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임의 영수증을 조회하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 모임의 영수증을 조회하여 정보를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임의 영수증을 조회하지 못한다.
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
    사용자가 영수증 세부 정보를 확인하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 모임 페이지에서 세부 정보를 확인하고 싶은 영수증을 클릭한다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 해당 영수증의 세부 정보를 사용자에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    이 Use Case는 세부 정보를 보여주고 끝난다.
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
    2a. 통신 문제로 인해 DB 조회가 실패한 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 사용자에게 영수증 조회에 실패했음을 알린다.<br/>
      &nbsp;&nbsp;
      2a.2. 시스템은 사용자에게 모임 페이지로 리다이렉션 한다.<br/>
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

<!-- 22. Update receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #22 : Update receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    사용자가 영수증을 정보를 수정하는 기능
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
    2023.05.02
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
    사용자는 해당 모임에 소속되어 있고 통신이 가능해야 한다. <br/>
    해당 영수증 정보를 생성한 사람, 즉 결제를 진행한 사람이어야 한다.
    </td>
  </tr>
  <tr>
    <td>Trigger</td>
    <td>
    사용자가 소속된 그룹에서 특정 모임의 영수증을 수정하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 모임의 영수증을 수정하여 정보를 확인할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임의 영수증을 수정하지 못한다.
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
    사용자가 영수증을 수정하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 수정하고자 하는 영수증 상세 페이지에서 수정 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 해당 영수증의 기존 정보가 입력되어 있는 폼을 사용자에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 정보를 수정하고 수정 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 입력한 정보로 DB의 데이터를 수정한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    사용자는 영수증 상세 페이지로 리다이렉션 하고, 이 Use case는 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 통신 문제로 인해 DB의 데이터를 수정하지 못하는 경우<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 사용자에게 수정을 실패했음을 알린다.<br/>
      &nbsp;&nbsp;
      4a.2. 사용자는 영수증 수정 페이지를 유지한다.
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

<!-- 23. Delete receipt -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #23 : Delete receipt </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    사용자가 영수증을 삭제하는 기능
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
    2023.05.02
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
    사용자가 소속된 그룹에서 특정 모임의 영수증을 삭제하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    사용자는 해당 모임의 영수증을 삭제하고 DB에서 제거할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    사용자는 해당 그룹에서 모임의 영수증을 삭제하지 못한다.
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
    사용자가 영수증을 삭제하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    사용자는 삭제하고자 하는 영수증 상세 페이지에서 삭제 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 사용자에게 삭제 여부를 묻는 팝업을 띄운다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    사용자는 확인 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 사용자가 삭제한 영수증을 DB에서 제거한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    시스템은 사용자에게 영수증 삭제가 완료되었음을 알리고 영수증 목록 페이지로 리다이렉션 한다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    해당 영수증이 DB에서 제거되면 이 Use case는 끝난다.
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
    3a. 사용자가 취소 버튼을 누른 경우<br/>
      &nbsp;&nbsp;
      3a.1. 영수증 삭제 프로세스가 종료되고 화면을 유지한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 통신문제로 인한 DB 제거 실패 시<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 사용자에게 영수증 제거 실패를 알린다.<br/>
      &nbsp;&nbsp;
      4a.2. 사용자는 페이지 이동없이 현재 화면을 유지한다.
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

<!-- 24. Delete group -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #24 : Delete group </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    그룹의 리더가 그룹을 삭제하는 기능
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
    2023.05.02
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
    그룹의 리더가 소속된 그룹에서 특정 모임을 삭제하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    그룹의 리더는 해당 모임과 관련된 모든 정보를 DB에서 제거하고 그룹을 삭제할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    그룹의 리더는 해당 그룹에서 모임을 삭제하지 못한다.
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
    그룹의 리더가 그룹을 삭제하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    리더가 그룹 페이지에서 그룹 삭제 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 리더에게 그룹 삭제 여부를 묻는 팝업을 띄운다. <br/>
    미정산 결제 내역이 포함되어 있으면 팝업 내용에 포함한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    리더가 그룹 삭제를 승인하면 미정산 결제 내역에 대한 정산 진행 여부를 묻는 팝업을 띄운다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    리더가 선택을 하면 시스템은 DB에서 그룹과 관련된 모든 정보를 제거한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    시스템은 그룹 내 모든 멤버에게 그룹 삭제 알림 메시지를 전송한다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    리더였던 사용자가 TripTip 메인 페이지로 이동하면, 이 Use case는 끝난다.
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
    2a. 리더가 취소를 누른 경우<br/>
      &nbsp;&nbsp;
      2a.1. 시스템은 그룹 삭제 프로세스를 중단하고 그룹 페이지를 유지한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    3a. 리더가 미정산 결제 내역 정산에 대해 '예' 버튼을 누른 경우<br/>
      &nbsp;&nbsp;
      3a.1. 시스템은 그룹 내 모든 미팅의 영수증 정보를 조회하여 정산을 수행하고 DB의 로그를 유지한다.<br/>
      &nbsp;&nbsp;
      3a.2. 정산이 완료되면 그룹 내 모든 멤버에게 정산 결과 알림을 전송한다.<br/>
      &nbsp;&nbsp;
      3a.3. 24-4 시나리오로 이동한다.<br/>
    3b. 리더가 미정산 결제 내역 정산에 대해 '아니오' 버튼을 누른 경우<br/>
      &nbsp;&nbsp;
      3b.1. 시스템은 그룹 내 결제 내역에 대한 정산을 수행하지 않는다.<br/>
      &nbsp;&nbsp;
      3b.2. 시스템은 DB에서 결제 내역 데이터를 모두 삭제한다.<br/> 
      &nbsp;&nbsp;
      3b.3. 24-4 시나리오로 이동한다.<br/>
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 통신 문제로 인해 그룹 삭제가 실패한 경우<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 그룹 삭제 실패 메시지를 띄운다.<br/>
      &nbsp;&nbsp;
      4a.2. 시스템은 그룹 페이지를 유지한다. <br/> 
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

<!-- 25. Add member -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #25 : Add member </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    그룹의 리더가 그룹에 멤버를 추가하는 기능
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
    2023.05.02
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
    그룹의 리더가 소속된 그룹에 멤버를 추가하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    그룹의 리더는 해당 그룹에 멤버를 추가할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    그룹의 리더는 해당 그룹에 멤버를 추가하지 못한다.
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
    그룹의 리더가 그룹에 멤버를 추가하고 싶을 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    그룹의 리더는 그룹 페이지에서 '멤버 추가' 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    리더는 유저 검색 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    리더는 검색바에 추가하고자 하는 유저의 닉네임 혹은 이름을 입력하고 검색버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 DB를 조회하여 매칭되는 유저 리스트를 보여준다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    리더는 추가하고자 하는 유저를 선택하고 '추가' 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    시스템은 해당 유저를 그룹의 멤버로 추가하고, 리더는 그룹 페이지로 이동한다.
    </td>
  </tr>
  <tr>
    <td>7</td>
    <td>
    이 Use case는 해당 유저가 그룹의 멤버로 등록되면 끝난다.
    </td>
  </tr>

  <!-- EXTENSION SCENARIOS -->
  <tr><th colspan="2">EXTENSION SCENARIOS</th></tr>
  <tr>
    <td>Step</td>
    <td>Branching Action</td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    4a. 매칭되는 유저가 없는 경우<br/>
      &nbsp;&nbsp;
      4a.1. 시스템은 검색 결과에 빈 화면을 보여준다.<br/>
    4b. 검색바에 한 글자 이하만 입력한 경우<br/>
      &nbsp;&nbsp;
      4b.1. 시스템은 사용자에게 검색어를 2글자 이상 입력하라는 메시지를 보여준다.<br/>
      &nbsp;&nbsp;
      4b.2. 시스템은 검색바를 포커싱한다.<br/>
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    5a. 해당 유저가 이미 그룹의 멤버인 경우<br/>
      &nbsp;&nbsp;
      5a.1. 시스템은 사용자에게 해당 유저를 비활성화 처리하여 선택할 수 없게 만든다.<br/>
  </tr>
  <tr>
    <td>6</td>
    <td>
    6b. 통신 문제로 인한 실패<br/>
      &nbsp;&nbsp;
      6b.1. 시스템은 사용자에게 멤버 추가가 실패했음을 알린다.<br/>
      &nbsp;&nbsp;
      6b.2. 시스템은 유저 검색 페이지를 유지한다.<br/>
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

<!-- 26. Expel member -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #26 : Expel member </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    그룹의 리더가 그룹에서 멤버를 제외하는 기능
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
    2023.05.02
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
    그룹의 리더가 소속된 그룹에서 멤버를 제외하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    그룹의 리더는 해당 그룹에서 멤버를 제외할 수 있다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    그룹의 리더는 해당 그룹에서 멤버를 제외하지 못한다.
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
    그룹의 리더가 특정 그룹의 멤버를 제외하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    리더가 그룹 편집 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 그룹 편집 페이지를 보여준다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    리더는 제외할 멤버를 선택하고 추방 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 리더에게 해당 멤버를 추방하겠냐는 확인창을 띄운다.<br/>
    중도에 추방시킬 시 정산 청구가 불가능 하다는 내용을 알린다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    리더가 확인 버튼을 누르면 시스템은 해당 멤버를 DB에서 삭제한다.
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
    시스템은 리더와 해당 사용자에게 추방이 완료되었음을 알린다.
    </td>
  <tr>
  <tr>
    <td>7</td>
    <td>
    이 Use case는 선택한 사용자가 DB 상에서 그룹과 연관관계가 없어지면 끝난다.
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
    3a. 리더가 취소 버튼을 누른 경우<br/>
    &nbsp;&nbsp;
    3a.1. 시스템은 멤버 추방 프로세스를 중단하고, 현재 페이지를 유지한다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    5a. 통신 문제로 인한 DB 데이터 삭제가 실패한 경우<br/>
      &nbsp;&nbsp;
      5a.1. 시스템은 리더에게 멤버 추방이 실패했음을 알린다.<br/>
      &nbsp;&nbsp;
      5a.2. 시스템은 현재 페이지를 유지한다.
    5b. 추방한 유저에게 미정산 결제 내역이 남아있던 경우<br/>
      &nbsp;&nbsp;
      5b.1. 시스템은 해당 유저 결제 내역을 삭제한다.<br/>
      &nbsp;&nbsp;
      5b.2. 시스템은 그룹 내 전체 영수증을 조회하여 다시 현재 정산 현황을 계산한다.
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

<!-- 27. Calculate cost -->
<table style="border: 2px;">
  <tr><th colspan="2"> Use Case #27 : Calculate cost </th></tr>
  <!-- GENERAL CHARACTERISTICS -->
  <tr><th colspan="2">GENERAL CHARACTERISTICS</th></tr>
  <tr>
    <td>Summary</td>
    <td>
    여행의 총 비용을 계산하는 기능
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
    2023.05.02
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
    리더가 그룹 내의 모든 결제 내역들을 종합하여 최종 정산을 실행하고자 할 때
    </td>
  </tr>
  <tr>
    <td>Success Post Condition</td>
    <td>
    그룹 내 모든 멤버에게 '누가', '누구에게', '얼마'를 보내야 하는지 정산 결과를 전송하고, 그룹 내 모든 결제 내역을 리셋한다.
    </td>
  </tr>
  <tr>
    <td>Failed Post Condition</td>
    <td>
    정산이 실행되지 않으며, 그룹 내 결제 내역을 유지한다.
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
    리더가 그룹 활동이 종료되어 정산을 실행하고자 할 때 시작한다.
    </td>
  </tr>
  <tr>
    <td>1</td>
    <td>
    리더가 정산 버튼을 누른다.
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
    시스템은 그룹 내의 정산 중인 결제 내역들을 종합하여 최종 정산을 실행한다.
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
    시스템은 그룹 내의 모든 멤버에게 '누가', '누구에게', '얼마'를 보내야 하는지 정산 결과를 전송한다.
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
    시스템은 그룹 내 모든 진행 중인 결제 내역을 리셋하고, 정산이 완료 페이지를 리더에게 보여준다.
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
    이 Use case는 정산 결과가 모든 멤버에게 통지되면 끝난다.
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
    3a. 정산 결과를 전송하는데 실패한 경우
      &nbsp;&nbsp;
      3a.1. 시스템은 정산이 실패했다는 메시지를 리더에게 보여주고 다시 실행을 요구한다.
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

&nbsp;&nbsp;&nbsp;
사용자는 자신이 속한 그룹을 확인할 수 있다. 그룹의 이름과 그룹의 리더가 누구인지 확인할 수 있다. 그룹의 상세 정보를 확인하려면 그룹 이미지 박스를 클릭하면 된다. <br/>

### 7) Group Create Page

![GroupCreate](https://user-images.githubusercontent.com/96044622/235109207-76cb0a71-4709-4418-857b-bced06ead9b2.png)

&nbsp;&nbsp;&nbsp;
사용자는 그룹을 생성할 수 있다. 그룹의 이름과 그룹의 설명, 그룹의 이미지를 설정할 수 있다. 그룹의 리더는 자신이 된다. 그룹 생성 버튼을 누르면 그룹이 생성되고, 그룹 리스트 페이지로 이동한다.

### 8) User Search Page

![User Search](https://user-images.githubusercontent.com/96044622/235109341-b2d2ccc4-5d38-4d18-89c9-d3b158801b97.png)

&nbsp;&nbsp;&nbsp;
사용자는 그룹에 참여할 다른 사용자를 검색할 수 있다. 검색한 사용자의 이름과 프로필 사진이 나타난다. 사용자를 클릭하면 사용자의 상세 정보를 확인할 수 있는 페이지로 이동한다.

### 9) Group Join Page

![그룹 참여](https://user-images.githubusercontent.com/96044622/235109435-29e8dc15-3fed-4a20-ba27-497b9c027a1d.png)

&nbsp;&nbsp;&nbsp;
사용자는 그룹에 참여할 수 있다. 그룹의 리더는 그룹에 참여할 사용자를 검색할 수 있는 페이지(User Search Page)로 이동한다. 그룹에 초대할 사용자를 선택하고, 그룹 참여 버튼을 누르면 초대가 가능하다.

### 10) Group Detail Page

![Group Detail](https://user-images.githubusercontent.com/96044622/235110091-163d094c-4003-4e6f-9253-46593439060c.png)

&nbsp;&nbsp;&nbsp;
사용자는 그룹의 상세 정보를 확인할 수 있다. 그룹의 이름과 설명, 그룹의 리더, 그룹의 멤버들을 확인할 수 있다. 그룹의 리더는 그룹의 이름과 설명을 수정할 수 있다. 그룹의 리더는 그룹의 멤버들을 강퇴시킬 수 있다. 그룹의 리더는 그룹을 삭제할 수 있다. 그룹의 리더가 아닌 사용자는 그룹을 나갈 수 있다. 그룹의 리더가 아닌 사용자는 그룹을 삭제할 수 없다. <br/>
&nbsp;&nbsp;&nbsp;
최종 정산 결정권은 그룹의 리더에게만 권한이 부여된다.

### 11) Meeting Detail Page

![Meeting Detail](https://user-images.githubusercontent.com/96044622/235110829-7db69308-3c7b-41e9-b17f-dde0586179c2.png)

&nbsp;&nbsp;&nbsp;
사용자는 미팅의 상세 정보를 확인할 수 있다. 미팅의 이름, 날짜 등을 확인할 수 있으며, 해당 미팅에 등록된 영수증 리스트를 확인할 수 있다.

### 12) Meeting Update Page

![Meeting Update](https://user-images.githubusercontent.com/96044622/235111126-ae6702af-4082-4767-a67d-82ecf82a5270.png)

&nbsp;&nbsp;&nbsp;
사용자는 미팅의 상세 정보를 수정할 수 있다. 미팅의 이름과 설명, 날짜 등을 수정할 수 있다. 미팅 수정 버튼을 누르면 미팅의 상세 정보가 수정되고, 미팅 상세 정보 페이지로 이동한다.

### 13) Receipt Create Page

![N분의 1](https://user-images.githubusercontent.com/96044622/235111414-769ccd8a-5908-4355-a519-70f343c131c0.png)

![직접 입력](https://user-images.githubusercontent.com/96044622/235111522-130d00bb-6962-4073-aeb1-d1b226884db7.png)

&nbsp;&nbsp;&nbsp;
사용자는 영수증을 생성할 수 있다. 영수증의 이름과 설명, 영수증의 이미지를 설정할 수 있다. 영수증의 가격을 직접 입력할 수도 있고, N분의 1로 입력할 수도 있다. 영수증 생성 버튼을 누르면 영수증이 생성되고 해당 내역을 결제한 사람으로 취급된다. 최종적으로 영수증 리스트 페이지로 이동한다.

### 14) Receipt Detail Page

![Receipt Detail](https://user-images.githubusercontent.com/96044622/235112003-ef942e8e-d065-43e6-9f33-c9ce12663930.png)

&nbsp;&nbsp;&nbsp;
사용자는 영수증의 상세 정보를 확인할 수 있다. 영수증의 이름과 설명, 영수증의 이미지, 결제한 사람 등 영수증과 관련된 모든 정보를 확인할 수 있다. <br/>
수정 버튼을 누르면 Receipt Update Page로 이동하고, 삭제 버튼을 누르면 재확인을 위한 팝업창을 띄운다.

### 15) Clear Page

![CLEAR](https://user-images.githubusercontent.com/96044622/235111631-ec0e8d3d-2f5a-4b06-b6a4-f81e13703a41.png)

&nbsp;&nbsp;&nbsp;
그룹의 리더가 최종 정산을 결정했을 때 나타나는 로딩 화면이다. 3초 이내에 작업이 수행되어야 하며, 정산이 끝나면 완료 버튼을 눌러 그룹 상세 정보 페이지로 이동할 수 있다.

### 16) Notification Page

![알림](https://user-images.githubusercontent.com/96044622/235111662-676c5033-ddca-4ee6-be8f-1d384e3e370f.png)

&nbsp;&nbsp;&nbsp;
사용자는 알림을 확인할 수 있다. 알림의 내용과 알림이 온 시간을 확인할 수 있다. 알림을 클릭하면 해당 알림과 관련된 페이지로 이동한다.

## 5. Glossary

<table style="border: 2px;">
  <tr><th colspan="2"><center>가</center></th></tr>
  <tr>
    <td>그룹</td>
    <td>
    그룹은 여러 사람들이 모여서 결제한 모임을 관리하는 단위이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>다</center></th></tr>
  <tr>
    <td>데이터 베이스</td>
    <td>
    사용자의 정보와 그룹의 정보 데이터들을 저장하는 공간이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>라</center></th></tr>
  <tr>
    <td>리더</td>
    <td>
    그룹의 리더는 그룹의 모든 권한을 가지고 있는 사용자이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>마</center></th></tr>
  <tr>
    <td>미팅</td>
    <td>
    미팅은 그룹의 일정을 관리하는 단위이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>사</center></th></tr>
  <tr>
    <td>사용자</td>
    <td>
    사용자는 서비스에 권한을 인증받고 이용하는 모든 사람들을 포괄한다.
    </td>
  </tr>
  <tr>
    <td>시스템 관리자</td>
    <td>
    시스템 관리자는 서비스의 모든 권한을 가지고 있는 사용자이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>아</center></th></tr>
  <tr>
    <td>영수증</td>
    <td>
    영수증은 미팅에서 발생한 비용을 관리하는 단위이다.
    </td>
  </tr>
  <tr><th colspan="2"><center>자</center></th></tr>
  <tr>
    <td>정산</td>
    <td>
    정산은 그룹 내에서 발생한 모든 비용을 기반으로 청구서를 그룹 멤버들에게 전송하는 작업이다.
    </td>
  </tr>
  <tr>
    <td>중계 테이블</td>
    <td>
    중계 테이블은 테이블 간의 다대다 관계를 제거하기 위한 테이블이다.
    </td>
  </tr>
</table>

## 6. References

1) 하루 5분 UX, 조엘 마시 지음
