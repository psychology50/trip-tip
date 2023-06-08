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
|2023/06/08|v1.0.1|Update Class diagram|YANG JAESEO|
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
<br/>

## [ 2. Class diagram ]

![classdiagram](https://github.com/psychology50/trip-tip/assets/96044622/a2dc56db-6904-471d-a4a9-19c5a311cdc7)
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
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/01b4a7a7-415d-4ab9-abe5-1f7162f28e68"></img></div>

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
    + builder : 자바 클래스 설계 원칙에 의거한 Builder 메서드<br/>
    + getter : 모든 필드 정보 조회에 대한 개별 getter 메서드<br/>
    + toString : 자바 클래스 설계 원칙에 의거한 toString 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 데이터 베이스의 User table과 매핑되는 도메인 클래스에 해당한다. </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/095e82c5-6e82-44fd-b210-2253ad7d055a"></img></div>

<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Address </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - city: String : 시<br/>
      - country: String : 도<br/>
      - state: String : 군/구<br/>
      - zipCode: String : 우편번호<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User Domain field에 Embedded하기 위한 클래스</td>
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
    <td> + builder & getter </td>
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
    - bank_account: String : 정산을 청구할 계좌 번호<br/>
    - bank_name: String : 은행 이름<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder & getter </td>
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
    <td>
    + ROLE_USER: 사용자<br/>
    + ROLE_ADMIN: 관리자<br/>
    </td>
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
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/cdedd448-380f-46a5-8907-a87886d8edb8"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserDefaultDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - nickname: String<br/>
      - user_id: Long<br/>
      - username: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> 
      + builder & getter
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 그룹 생성 페이지에서 리더가 될 유저의 정보를 담은 Dto </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/ecc1aa10-31a0-47b6-b869-a7a2c64b0f86"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - bank_account: String<br/>
      - bank_name: String<br/>
      - groupList: List&lt;Group> : 가장 최근에 생성한 3개의 그룹 정보<br/>
      - local: String<br/>
      - local_prefix: String<br/>
      - local_suffix: String<br/>
      - nickname: String<br/>
      - username: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      + getter & builder & toString<br/>
      + addGroup(group: Group) : void : 가장 최근에 생성한 Group을 groupList에 추가<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 로그인한 유저 정보를 메인 페이지에 전달하는 DTO </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/d42e4498-2d40-4ac5-9299-36b626594641"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserLoginDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - nickname: String<br/>
      - password: String
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 사용자가 로그인할 때 사용되는 Dto </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/c67aa4d5-88f9-4887-a03b-8e2d07b767a8"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserParticipationDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - cost: Double : 사용한 금액 <br/>
    - nickname: String : 정산 참가자 닉네임 <br/>
    - selected: Boolean : 정산 참가 여부 <br/>
    - user_id: Long : 정산 참가자 primary key <br/>
    - username: String : 정산 참가자 이름 <br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      + builder & getter & toString<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 
      영수증을 생성할 때, ReceiptCreateDto의 필드로 포함될 유저 정보 및 개별 사용한 비용 정보 
    </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/87479169-7b68-412d-b1fb-88d844f66ea4"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserRegisterDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - bank_account: String<br/>
    - bank_name: String<br/>
    - city: String<br/>
    - country: String<br/>
    - id: Long<br/>
    - local: String<br/>
    - local_prefix: String<br/>
    - local_suffix: String<br/>
    - nickname: String<br/>
    - password: String<br/>
    - state: String<br/>
    - username: String<br/>
    - zipCode: String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> 
    - builder & getter & toString <br/>
    - toEntity(): User : 사용자에게 입력받은 데이터 기반의 User 객체 생성
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 회원 가입 시 사용하는 DTO </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.1.3. API
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/52265c6c-8d44-4ed7-8fed-a173baa1e97a"></img></div>
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
    <td>
    + accessDenied(String, Model): String : Spring Security 인증 과정에서 오류 발생 시 handler에 의해 요청되는 페이지 호출<br/>
    + deleteRequest(Model): RedirectView : 유저 삭제 요청<br/>
    + loginRequest(String, String, Model): String : 로그인 요청<br/>
    + logoutRequest(HttpServletRequest, HttpServletResponse): String : 로그아웃 요청<br/>
    + notifyPageRequest(Model): String : 알림 페이지로 이동<br/>
    + profileRequest(Model): String : 프로필 페이지로 이동<br/>
    + registerRequest(Model): String : 회원가입 페이지로 이동<br/>
    + registerResponse(UserRegisterDto): RedirectView : 회원가입 요청 처리<br/>
    + searchUser(String): ResponseEntity&lt;UserDefaultDto> : 유저 검색 기능<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User Domain과 관련된 Http Request와 인증, 파라미터를 처리하는 Controller </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.1.4. Service
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/7e86e7bf-8553-45f8-8f32-7b57ace30fb1"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> UserSearchService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    -
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + findUserAll(): List&lt;User> : DB 상의 모든 User를 탐색한다.<br/>
    + findUserByGroupId(Long): List&lt;User> : id에 매칭되는 Group에 속한 User를 탐색한다.<br/>
    + findUserById(Long): Optional&lt;User> : id로 특정 User를 탐색한다.<br/>
    + findUserByNickname(String): Optional&lt;User> : nickname으로 특정 User를 탐색한다.<br/>   
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User 도메인과 관련된 비니지스 로젝을 처리하는 클래스 </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/ad099d8b-29f2-470b-b93d-c9d876f5c30b"></img></div>
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
    <td> 
      + register(UserRegisterDto): User : 회원가입 요청을 처리한다.<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User의 정보를 입력받아 Database에 저장하는 과정에서 비지니스 로직을 처리하는 클래스. 로그인, 로그아웃은 SpringSecurity에서 제공하는 기능을 이용하므로 필요 없다. </td>
  </tr>
</table>

### 2.2. Member

&nbsp;&nbsp;&nbsp;
2.2.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/d7705fe0-9743-4d12-a3f9-d487c79ecbdf"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Member </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group: Group<br/>
    - member_id: Long<br/>
    - user: User<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder & getter & toString<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> User와 Group의 다대다 관계를 분리하기 위한 중계 테이블 </td>
  </tr>
</table>


### 2.3. Group
&nbsp;&nbsp;&nbsp;
2.3.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/fedc10db-47b9-4280-998a-4f4595dc5716"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Group </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group_code: String : 그룹 생성 시, 시스템 내부에서 생성되는 그룹 고유 코드.<br/>
    - group_id: Long : 그룹의 primary key<br/>
    - group_name: String : 그룹 이름<br/>
    - leader: User : 그룹 생성자<br/>
    - meetings: List&lt;Meeting> : 그룹 내의 meeting 리스트<br/>
    - members: List&lt;Member> : 그룹에 속한 Member 리스트<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder & toString & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 여러 모임을 진행하는 동안 함께 하는 인원들이 속해있는 Group의 데이터를 담은 도메인 클래스 </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.3.2. DTO
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/77c79207-7143-4aab-bf16-e031073720b4"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupCreateDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group_code: String<br/>
    - group_id: Long<br/>
    - group_name: String<br/>
    - members: List&lt;User><br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder(): GroupCreateDtoBuilder<br/>
    + toEntity(String): Group : 랜덤 생성 code 결과 값 주입을 위해 매개변수가 지정된 toEntity<br/>
    + toString(): String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 그룹 생성 페이지를 구성하고 결과를 받아오기 위한 Dto </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/d4090932-3f18-467a-bd5d-ba2748a826c0"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group_code: String<br/>
    - group_id: Long<br/>
    - group_img: String<br/>
    - group_name: String<br/>
    - leader_id: Long : 그룹의 리더 아이디<br/>
    - leader_username: String : 그룹의 리더 이름<br/>
    - meetings: List&lt;Meeting> : 그룹에 생성된 모임 리스트<br/>
    - total: Integer : 그룹 내 전체 결제 금액 합<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder & toEntity & getter </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 그룹 상세 페이지 구성을 위한 Dto </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/89c6dbc3-885e-4966-9f4f-ac9d1bfc8014"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> GroupJoinDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group_code: String : 가입할 그룹의 코드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + getGroup_code() : String </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 사용자가 가입하고자 하는 Group의 코드 정보 </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/de77638c-0b71-48f2-a8f9-e404201a232b"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> RecentGroupDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - group_image: String <br/>
    - group_name: String <br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder(): RecentGroupDtoBuilder <br/>
    + toString(): String
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 로그인한 메인 페이지 구성을 위한 가장 최근 가입한 Group 3개가 담긴 리스트 </td>
  </tr>
</table>


&nbsp;&nbsp;&nbsp;
2.3.3. API
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/6fe8b8c7-fee3-4755-a609-1e260e9262d4"></img></div>
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
    <td>
    + groupCreateRequest(Authentication, Model): String : 그룹 생성 페이지를 반환해주는 메서드
    + groupDeleteRequest(Long, Authentication): RedirectView : 그룹 삭제 Request에 대한 Service를 호출하는 메서드<br/>
    + groupDetailRequest(Long, Model): String : 특정 그룹 정보 Request에 대한 Service를 호출하는 메서드<br/>
    + groupJoinRequest(GroupJoinDto, Authentication): RedirectView : 그룹 참가에 대한 Service를 호출하는 메서드<br/>
    + groupListRequest(Authentication, Model): String : 사용자가 가입한 모든 그룹 리스트에 대한 Service를 호출하는 메서드<br/>
    + groupSaveRequest(GroupCreateDto, Authentication): ResponseEntity&lt;String> :  그룹 생성 Request에 대한 Service를 호출하는 메서드<br/>
    + groupSettleRequest(Long, Authentication): ResponseEntity&lt;String> : 그룹 정산 Request에 대한 Service를 호출하는 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Group 도메인 관련 Client Http 요청을 적절한 비지니스 로직과 매핑하고 응답을 내려주는 Controller  </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.3.4. Service
<div align="center"><img src=""></img></div>
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
    <td>
    + findGroupAll(): List&lt;Group><br/>
    + findGroupById(Long): Optional&lt;Group><br/>
    + findGroupByUser(User): List&lt;Group><br/>
    + findRecentGroupByUser(User): List&lt;Group><br/>
    + getTotalGroupCost(Long): Integer<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Group 검색과 관련한 비지니스 로직을 담당하는 Service 클래스 </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/aaa4a19d-445c-4f0c-b5ec-91c15a72f710"></img></div>
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
    <td>
      + deleteGroup(Long): void<br/>
      - generateGroupCode(): String<br/>
      + save(User, GroupCreateDto): Optional&lt;Group><br/>
      + settleGroup(Long): void<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Group 생성, 삭제 기능 관련 비지니스 로직을 담당하는 Service 클래스 </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/4db4d38c-de6e-4a5c-8257-85670a706134"></img></div>
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
    <td> + joinGroup(User, GroupJoinDto): boolean : 기존에 있는 그룹에 참여하는 비지니스 로직을 담당하는 Service 클래스 </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Group 참가 관련 비지니스 로직을 담당하는 Service 클래스 </td>
  </tr>
</table>

### 2.4. Meeting
&nbsp;&nbsp;&nbsp;
2.4.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/21c4a7b4-e63e-4eac-a179-ad0ced63ee76"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Meeting </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - group: Group : 모임이 속해있는 Group과 매핑된 foreign key<br/>
      - is_clear: Boolean : 모임 종료 여부<br/>
      - meeting_day: LocalDate : 모임이 진행된 날짜<br/>
      - meeting_id: Long : 모임의 primary key<br/>
      - meeting_name: String : 모임 이름<br/>
      - receipts: List&lt;Receipt> : 모임에 등록된 영수증 리스트<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder(): MeetingBuilder<br/>
    + toString(): String<br/>
    + getter
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 그룹 내에서 모임을 날짜별로 구분하기 위한 도메인 클래스 </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.2. DTO
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/13075231-47ed-4019-9b92-b235bca7694b"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingListDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - meeting_day: LocalDate <br/>
      - meeting_id: Long
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder(): MeetingListDtoBuilder <br/>
    + getter
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Group Detail Page 내에 보여질 간략한 Meeting List 정보 </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/79b787c0-748c-49d8-aad2-8dbbb1e1511b"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> MeetingDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - group: Group<br/>
      - leader_id: Long<br/>
      - leader_username: String<br/>
      - meeting_day: LocalDate<br/>
      - meeting_id: Long<br/>
      - receipts: List&lt;Receipt><br/>
      - total: Integer<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder(): MeetingDetailDtoBuilder </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Meeting Detail Page 구성을 위한 DTO </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.3. API
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/d3cc9e6a-74f6-4e68-
bb40-f7b8fcd55bc9"></img></div>
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
    <td>
    + meetingDeleteRequest(Long, Long, Authentication): ResponseEntity&lt;String> : 미팅 삭제 Request와 관련된 Service와 매핑시켜주는 메서드 <br/>
    + meetingDetailPageRequest(Long, Long, Model): String : meeting의 상세 정보 Request와 관련된 Service를 매핑시켜주고 페이지를 내려주는 메서드 <br/>
    + meetingSaveRequest(Long): ResponseEntity&lt;MeetingListDto> : meeting 저장 Request를 관련된 Service와 매핑시켜주는 메서드 <br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Meeting 도메인 관련 Client Http 요청을 적절한 비지니스 로직과 매핑하고 응답을 내려주는 Controller </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.4.4. Service
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/fd88c948-b22b-4ea6-918e-d47916d1d1b7"></img></div>
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
    <td>
      + findMeetingAll(): List&lt;Meeting> : DB에 존재하는 모든 Meeting을 탐색하는 메서드<br/>
      + findMeetingById(Long): Optional&lt;Meeting> : 특정 meeting 정보를 탐색하는 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Meeting 도메인 관련 검색 비지니스 로직을 담당하는 Service </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/6efb516c-c806-41f7-b6dc-3fdbb7102367"></img></div>
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
    <td>
      + deleteMeeting(User, Long): void : 특정 meeting을 삭제하기 위한 메서드<br/>
      + save(Long): Meeting : meeting을 생성하기 위한 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Meeting 도메인의 생성과 삭제 비지니스 로직을 담당하는 Service </td>
  </tr>
</table>

### 2.5. Receipt
&nbsp;&nbsp;&nbsp;
2.5.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/82491144-759e-4dbb-98d3-bb2cd98ba206"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Receipt </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - is_clear: Boolean : 영수증 정산 여부<br/>
      - meeting: Meeting : 영수증이 등록된 meeting을 가리키는 foreign key<br/>
      - participants: List&lt;Participant> : 영수증에 참가한 멤버 정보<br/>
      - payer: User : 영수증 비용을 지불한 멤버 정보<br/>
      - receipt_id: Long : 영수증 primary key<br/>
      - receipt_name: String : 영수증 이름<br/>
      - total: Double : 영수증 총 지불 금액<br/>    
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      + builder(): ReceiptBuilder<br/>
      + toString(): String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 모임에서 진행된 결제 내역을 저장하기 위한 도메인 </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.2. DTO
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/7be5c743-afcf-4501-a667-e818be29628e"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptCreateDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - is_clear: Boolean<br/>
      - meeting: Meeting<br/>
      - participationUsers: List&lt;UserParticipationDto><br/>
      - payer: User<br/>
      - receipt_id: Long<br/>
      - receipt_name: String<br/>
      - total: Double<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      + builder(): ReceiptCreateDtoBuilder<br/>
      + toEntity(): Receipt<br/>
      + toString(): String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 레시피 생성을 위한 DTO </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/2959f91d-5960-48f6-b497-27dce0062576"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> ReceiptDetailDto </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - is_clear: Boolean<br/>
      - meeting: Meeting<br/>
      - participants: List&lt;Participant><br/>
      - payer_cost: Integer : 영수증 생성한 멤버가 사용한 비용<br/>
      - payer_id: Long<br/>
      - payer_nickname: String<br/>
      - payer_username: String<br/>
      - receipt_id: Long<br/>
      - receipt_name: String<br/>
      - total: Integer<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> + builder(): ReceiptDetailDtoBuilder </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 영수증 상세 페이지 구성을 위한 Dto </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.3. API
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/ed29c980-5c08-4fc5-89f8-fb20092afb20"></img></div>
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
    <td>
      + create(Long, Long, Authentication, Model): String : Receipt 생성을 위한 페이지 반환<br/>
      + create(Long, Long, ReceiptCreateDto): ResponseEntity&lt;Receipt> : Receipt 생성을 위해 사용자 Form을 구성할 정보를 반환하는 Service와 매핑하는 메서드<br/>
      + createForm(Authentication, Long, Long): ResponseEntity&lt;ReceiptCreateDto> : Receipt 생성 Request를 담당하는 Service와 매핑하는 메서드<br/>
      + delete(Long, Long, Long): ResponseEntity&lt;String> : 해당 Receipt를 삭제하는 Service와 매핑하는 메서드<br/>
      + detail(Long, Long, Long, String, Model): String : Receipt 상세 정보를 담당하는 Service와 매핑하고 페이지를 내려주는 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> Receipt 도메인 관련 Client Http 요청을 적절한 비지니스 로직과 매핑하고 응답을 내려주는 Controller </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.5.4. Service
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/43caeae9-6181-4359-b1e1-13fbde610f12"></img></div>
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
    <td>
      + findReceiptAll(): List&lt;Receipt> : DB 상의 모든 Receipt 정보를 조회한다.<br/>
      + findReceiptById(Long): Optional&lt;Receipt> : 특정 Receipt 정보를 조회한다.<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 
      Receipt 도메인 관련 검색 비지니스 로직을 담당하는 Service<br/>
    </td>
  </tr>
</table>

<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/d5733a54-c1da-42a9-ab8f-11c62abeeadf"></img></div>
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
    <td>
      + delete(Long): void : Receipt 삭제 비지니스 로직을 처리하는 메서드<br/>
      + save(ReceiptCreateDto): Receipt : Receipt 저장하는 메서드
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 
      Receipt 도메인의 생성과 삭제 비지니스 로직을 담당하는 Service<br/>
    </td>
  </tr>
</table>

### 2.6. Participant
&nbsp;&nbsp;&nbsp;
2.6.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/6dd4e361-6fe0-4330-84ea-968c7328e7ac"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Participant </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
      - cost: Double : 해당 영수증에서 사용한 금액<br/>
      - is_clear: Boolean : 정산 여부<br/>
      - participant_id: Long : 참가자 테이블 primary key<br/>
      - receipt: Receipt : 매핑된 영수증을 참조하는 Foreign key<br/>
      - user: User : 매핑된 사용자를 참조하는 Foreign key<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      + builder(): ParticipantBuilder<br/>
      + toString(): String
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td>Receipt에 참여한 User의 다대다 관계를 제거하기 위한 중개 테이블</td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.6.2. Service
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/f0675108-cd5d-4d10-8a65-7260346425f3"></img></div>
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
    <td>
      + findParticipantByMeetingId(Long): List&lt;Participant> : 해당 모임에 속한 모든 참가자 정보를 조회하는 메서드<br/>
      + findParticipantsByReceiptId(Long): List&lt;Participant> : 해당 영수증에 속한 모든 참가자 정보를 조회하는 메서드<br/>
      + getCostByReceiptIdAndUserId(Long, Long): Integer : 특정 영수증에 참가한 사용자가 사용한 cost 필드 정보를 가져오는 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td>  </td>
  </tr>
</table>

### 2.7. Settlement
&nbsp;&nbsp;&nbsp;
2.7.1. Domain
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/51cd40ba-de69-447d-a7a0-75cb272f2ab7"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> Settlement </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - amount: Double : 지불 금액<br/>
    - id: Long : settlement primary key<br/>
    - isSent: Boolean : 지불 여부<br/>
    - receiver: User : 받는 사람 Foreign key<br/>
    - sender: User : 보내는 사람 Foreign key<br/>
    - sentTime: LocalDateTime : 지불한 시간<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
    + builder(): SettlementBuilder<br/>
    + markAsSent(): void : 정상적으로 정산이 실행되었으면 isSent 필드를 true로 바꾸는 메서드<br/>
    + toString(): String<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 그룹 정산이 실행되고 최종적으로 그룹의 멤버들에게 통지되는 정산해야 할 내역 정보를 담은 도메인 </td>
  </tr>
</table>

### 2.8. Others
&nbsp;&nbsp;&nbsp;
2.8.1. BaseDateEntity
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/8b405ad1-dda7-41a0-8785-4d411b6b1d36"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> BaseDateEntity </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>
    - created_date : LocalDateTime : 생성 일자<br/>
    - last_modified_date : LocalDateTime : 수정 일자
    </td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td> - </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 
      도메인에 공통적으로 적용되는 생성, 수정 시간 필드를 추가하기 위한 상속용 클래스.<br/>primary field는 원래 상속이 불가능 하지만 java에서 제공하는 기능을 사용하여 주입하였다. 생성일과 수정일에 대한 관리는 JPA Auditing을 통해 자동으로 관리된다.<br/>
      </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.8.2. CommonAPI
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/0403a326-4890-4f4b-96f0-c9bcf34258a8"></img></div>
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
    <td>
      + home(): String : "/"로 GET 요청시 index()로 리다이렉트 한다.<br/>
      + index(Authentication, Model): String : Main Page를 구성하는 메서드<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 특정 Domain에 종속되지 않고 root 단에서 매핑이 되어야 하는 Controller </td>
  </tr>
</table>

&nbsp;&nbsp;&nbsp;
2.8.3. SecurityService
<div align="center"><img src="https://github.com/psychology50/trip-tip/assets/96044622/e32abfa0-4369-4d6b-9d12-37afee2c1427"></img></div>
<table align="center">
  <tr>
    <td> <b>ClassName</b> </td>
    <td> SecurityService </td>
  </tr>
  <tr>
    <td> <b>Attributes</b> </td>
    <td>-</td>
  </tr>
  <tr>
    <td> <b>Methods</b> </td>
    <td>
      - exception_handling(): void : 권한 없는 유저에 대한 예외 처리 로직<br/>
      + isLeader(Long): boolean : Request한 유저가 그룹의 리더인지 판단<br/>
      + isMember(Long): boolean : Request한 유저가 그룹의 멤버인지 판단<br/>
      + isPayer(Long): boolean  : Request한 유저가 영수증의 payer인지 판단<br/>
    </td>
  </tr>
  <tr>
    <td> <b>Description</b> </td>
    <td> 메서드 요청에 대해 보다 상세한 권한 인증을 위한 Security Service </td>
  </tr>
</table>


## [ 3. Sequence diagram ]


## [ 4. State machine diagram ]

![stateMachineDiagram](https://github.com/psychology50/trip-tip/assets/96044622/a34d9ec2-e17a-42e0-90c4-6235ca3796fb)

&nbsp;&nbsp;&nbsp;
Client가 TripTip 서비스 이용시 발생하는 시스템 흐름도이다. 해당 서비스를 이용하기 위해서는 반드시 Login 과정을 거쳐야 한다. 만약, 아직 TripTip 서비스에 계정이 없다면 회원가입을 통해 계정을 등록해야 한다.<br/>
&nbsp;&nbsp;&nbsp;
인증에 성공하면 사용자는 가장 최근에 가입한 그룹 리스트 3개를 확인할 수 있다. 모든 리스트를 확인하고 싶다면 활동내역을 클릭하여 그룹 리스트 페이지로 이동한다. 소속 그룹이 없으면 아무 그룹도 나타나지 않는다. 그럴 때는 기존에 생성된 그룹의 코드를 입력하여 참가하거나, 새로운 그룹을 생성해야 한다. 그룹 생성 페이지에서는 그룹의 이름과 초대할 사용자를 검색하여 추가한 후 생성하기 버튼을 누르면 그룹이 생성된다.<br/>
&nbsp;&nbsp;&nbsp;
그룹 내에서는 날짜 별로 모임을 생성할 수 있고, 만약 오늘 날짜의 모임이 이미 만들어져 있다면 중복 생성은 불가능하다. 개별 모임 안에서는 그 날에 발생한 지출 내역에 대한 영수증을 등록할 수 있다.<br/>
&nbsp;&nbsp;&nbsp;
그룹 내 모든 모임의 활동이 끝났다고 판단하면, 리더 권한(그룹을 생성한 유저)을 가진 사용자가 정산하기 버튼을 누르면 각 멤버에게 "누가" "누구에게" "얼마"를 보내야 하는지 계산하여 알려준다. 정산 내역에 대해 각 멤버에게 공지되어 notify page에서 확인할 수 있다. (시간 부족으로 인해 기능을 구현하지는 못했다.)
&nbsp;&nbsp;&nbsp;
그룹 삭제, 모임 삭제, 영수증 삭제는 각각 리더 권한, 멤버 권한, payer 권한이 있어야 가능하다. 리더 권한은 그룹 생성자에게 부여되며, Request 요청자가 그룹 테이블의 리더 필드에 매칭된 유저 아이디와 같다면 리더 권한을 가진다. 멤버 권한은 그룹에 속한 유저 중 리더 권한을 가진 유저를 포함한 모든 유저가 가진다. payer 권한은 모임에 속한 유저 중 지출 내역을 등록한 유저가 가진다.

## [ 5. Implementation requirements ]

### 5.1. 개발환경
- IntelliJ IDEA 2023.1.2
- Spring Boot 3.0.5
- Oracle OpenJDK version 17
- MySQL Workbench

### 5.2. Build Tool
- Gradle


## [ 6. Glossary ]
<table>
  <thead>
  <tr>
    <th>terms</th>
    <th>description</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>API</td>
    <td>응용 프로그램 간에 상호 작용하고 통신할 수 있도록 하는 규칙과 프로토콜의 모음</td>
  </tr>
  <tr>
    <td>MVC Pattern</td>
    <td>소프트웨어 아키텍처 설계 패턴으로, 애플리케이션의 로직을 모델, 뷰, 컨트롤러라는 세 가지 상호 연결된 구성 요소로 분리</td>
  </tr>
  </tbody>
</table>

## [ 7. References ]








