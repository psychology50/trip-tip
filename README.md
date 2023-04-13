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

<!-- 1. -->
<table style="border: 2px;" width="800">
  <tr><th colspan="2"> Use Case #1 :  </th></tr>
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
    -
    </td>
  </tr>
  <tr>
    <td>Level</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Author</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Last Update</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Status</td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td>Primary Actor</td>
    <td>
    -
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
</table>

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


