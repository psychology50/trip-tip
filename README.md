# 모임 정산 관리 서비스. trip-tip
### 3. Design
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
    <td> 21911407@yu.ac.kr </td>
  </tr>
</table>

## [ Revision history ]

|Revision date|Verision #|Description|Author|
|:---:|:---:|:---:|:---:|
|2023/06/06|v1.0.0|First Writing|YANG JAESEO|
| | | | |
| | | | |
| | | | |
| | | | |

## [ Contents ]
- [1. Introduction](#1-introduction)
- [2. Class diagram](#2-class-diagram)
- [3. Sequence diagram](#3-sequence-diagram)
- [4. State machine diagram](#4-state-machine-diagram)
- [5. Implementation requirements](#5-implementation-requirements)
- [6. Glossary](#6-glossary)
- [7. References](#7-references)

## [ 1. Introduction ]

&nbsp;&nbsp;&nbsp;
모임이나 친구들과의 여행을 하면서 발생한 지출에 대해 정산을 해야 하지만, 이를 지속적으로 관리하기란 상당히 번거롭고 어려운 일이다.
이미 수많은 더치페이 정산 시스템이 마련되어 있지만, 사용자들이 지속적으로 정산을 한 후에 최종적인 청구 기능밖에 존재하지 않는다.
총무라는 역할을 누군가는 수행해야 하지만 그 자리가 주는 스트레스와 부담감으로 인해 누구나 선뜻 자처하려는 사람이 없다.  
&nbsp;&nbsp;&nbsp;
이러한 문제를 해결하기 위하여 모임의 정산 내역을 지속적으로 쉽고 간편하게 관리할 수 있는 정산 서비스 Trip-Tip을 만들게 되었다.  
&nbsp;&nbsp;&nbsp;
본 문서에서는 Analysis에 이어서 Design 단계에 대한 설계를 기술한다. class diagram, sequence diagram, state machine diagram을 통해 설계를 진행하였으며, implementation requirements를 문서화하여 시스템 구현에 필요한 소프트웨어 및 하드웨어 요구사항을 명세한다.

## [ 2. Class diagram ]

![classdiagram](https://github.com/psychology50/trip-tip/assets/96044622/c9f67cdc-0727-4c20-94ea-ae62ebec8c45)
<div align="center"><strong>[그림 1]</strong> Class diagram</div>

Spring MVC 패턴을 사용하여 구현하였다.
개수가 너무 많은 관계로 Spring에서 제공하는 클래스에 대해서는 설명하지 않을 것이다.
또한 유지보수를 위해 Service, DTO를 세분화하였기 때문에 근본적인 목적이 동일한 클래스에 대해서는 추가 설명을 작성하지 않을 것이다.  
설명의 용이성을 위하여 도메인 별로 그룹 지어 설명을 진행한다.
> user, member, group, meeting, receipt, participant, settlement, others  
> 각 도메인은 Domain → DTO → API → Service → DAO 클래스 순서로 기술

### 2.1. User
&nbsp;&nbsp;&nbsp;
2.1.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/01b4a7a7-415d-4ab9-abe5-1f7162f28e68"></img></center>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> User </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> 
    - address: Address : Address 클래스 임베디드 필드<br/>
    - bank: Bank : Band 클래스 임베디드 필드<br/>
    - is_leader: List&lt;Group> : Leader 권한으로 속해있는 Group 리스트<br/>
    - members: List&lt;Member> : 가입해있는 Group들과 매핑된 Member 리스트<br/>
    - nickname: String : 사용자 닉네임<br/>
    - participants: List&lt;Participant> : 정산해야 하는 개별 영수증 비용 리스트<br/>
    - password: String : 사용자 비밀번호<br/>
    - pay_receipts: List&lt;Receipt> : 자신이 결제한 영수증 리스트<br/>
    - phone: Phone : Phone 클래스 임베디드 필드<br/>
    - receivedSettlements: List&lt;Settlement> : 수금해야 하는 Settlement 리스트<br/>
    - role: RoleType : 사용자 역할<br/>
    - sentSettlements: List&lt;Settlement> : 지불해야 하는 Settlement 리스트<br/>
    - user_id: Long : primary key<br/>
    - username: String : 사용자 이름<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> 
    - builder : 자바 클래스 설계 원칙에 의거한 Builder 메서드<br/>
    - getter : 모든 필드 정보 조회에 대한 개별 getter 메서드<br/>
    - toString : 자바 클래스 설계 원칙에 의거한 toString 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 데이터 베이스의 User table과 매핑되는 도메인 클래스에 해당한다. </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/095e82c5-6e82-44fd-b210-2253ad7d055a"></img></center>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Address </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - city: String<br/>
      - country: String<br/>
      - state: String<br/>
      - zipCode: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> builder & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User Domain field에 Embedded하기 위한 클래스 </td>
  </tr>
</table>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Phone </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - local: String<br/>
    - local_prefix: String<br/>
    - local_suffix: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> builder & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User Domain field에 Embedded하기 위한 클래스 </td>
  </tr>
</table>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Bank </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - bank_account: String<br/>
    - bank_name: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> builder & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User Domain field에 Embedded하기 위한 클래스 </td>
  </tr>
</table>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> RoleType </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 
      USER와 ADMIN을 SpringSecurity에서 구분해주기 위한 Enum 타입의 클래스
    </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.1.2. DTO
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/cdedd448-380f-46a5-8907-a87886d8edb8"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserDefaultDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/ecc1aa10-31a0-47b6-b869-a7a2c64b0f86"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/d42e4498-2d40-4ac5-9299-36b626594641"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserLoginDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/c67aa4d5-88f9-4887-a03b-8e2d07b767a8"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserParticipationDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/87479169-7b68-412d-b1fb-88d844f66ea4"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserRegisterDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.1.3. API
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/52265c6c-8d44-4ed7-8fed-a173baa1e97a"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserAPI </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.1.4. Service
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/9ceceaeb-8f22-49b7-bbd1-ff746bd909a6"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/ad099d8b-29f2-470b-b93d-c9d876f5c30b"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserSignService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.2. Member

&nbsp;&nbsp;&nbsp;
2.2.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/d7705fe0-9743-4d12-a3f9-d487c79ecbdf"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Member </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>


### 2.3. Group
&nbsp;&nbsp;&nbsp;
2.3.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/fedc10db-47b9-4280-998a-4f4595dc5716"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Group </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.3.2. DTO
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/77c79207-7143-4aab-bf16-e031073720b4"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupCreateDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/d4090932-3f18-467a-bd5d-ba2748a826c0"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/89c6dbc3-885e-4966-9f4f-ac9d1bfc8014"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupJoinDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/de77638c-0b71-48f2-a8f9-e404201a232b"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> RecentGroupDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>


&nbsp;&nbsp;&nbsp;
2.3.3. API
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/6fe8b8c7-fee3-4755-a609-1e260e9262d4"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupAPI </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.3.4. Service
<center><img src=""></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/aaa4a19d-445c-4f0c-b5ec-91c15a72f710"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupManageService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/4db4d38c-de6e-4a5c-8257-85670a706134"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupJoinService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.4. Meeting
&nbsp;&nbsp;&nbsp;
2.4.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/21c4a7b4-e63e-4eac-a179-ad0ced63ee76"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Meeting </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.2. DTO
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/13075231-47ed-4019-9b92-b235bca7694b"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingListDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/79b787c0-748c-49d8-aad2-8dbbb1e1511b"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.3. API
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/d3cc9e6a-74f6-4e68-
bb40-f7b8fcd55bc9"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingAPI </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.4. Service
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/fd88c948-b22b-4ea6-918e-d47916d1d1b7"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/6efb516c-c806-41f7-b6dc-3fdbb7102367"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingManageService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.5. Receipt
&nbsp;&nbsp;&nbsp;
2.5.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/82491144-759e-4dbb-98d3-bb2cd98ba206"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Receipt </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.2. DTO
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/7be5c743-afcf-4501-a667-e818be29628e"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptCreateDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/2959f91d-5960-48f6-b497-27dce0062576"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.3. API
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/ed29c980-5c08-4fc5-89f8-fb20092afb20"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptAPI </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.4. Service
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/43caeae9-6181-4359-b1e1-13fbde610f12"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/d5733a54-c1da-42a9-ab8f-11c62abeeadf"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptManageService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.6. Participant
&nbsp;&nbsp;&nbsp;
2.6.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/6dd4e361-6fe0-4330-84ea-968c7328e7ac"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Participant </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.6.2. Service
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/f0675108-cd5d-4d10-8a65-7260346425f3"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ParticipantSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.7. Settlement
&nbsp;&nbsp;&nbsp;
2.7.1. Domain
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/51cd40ba-de69-447d-a7a0-75cb272f2ab7"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Settlement </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

### 2.8. Others
&nbsp;&nbsp;&nbsp;
2.8.1. BaseDateEntity
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/8b405ad1-dda7-41a0-8785-4d411b6b1d36"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> BaseDateEntity </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.8.2. CommonAPI
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/0403a326-4890-4f4b-96f0-c9bcf34258a8"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> CommonAPI </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.8.3. SecurityService
<center><img src="https://github.com/psychology50/trip-tip/assets/96044622/e32abfa0-4369-4d6b-9d12-37afee2c1427"></img></center>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> SecurityService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> - </td>
  </tr>
</table>


## [ 3. Sequence diagram ]


## [ 4. State machine diagram ]


## [ 5. Implementation requirements ]


## [ 6. Glossary ]


## [ 7. References ]








