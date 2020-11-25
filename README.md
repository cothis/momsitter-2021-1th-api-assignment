
# momsitter-2020-1th-api-assignment  
  
맘시터 백엔드 개발 직무에 지원해주셔서 감사합니다. 본 과제를 통해 향후 맘시터의 동료로서 서비스를 더 잘 이해하고, 맘시터 서비스 개발 업무를 미리 생각해볼 수 있는 좋은 기회가 되기를 희망합니다.  
  
## Description  
맘시터 백엔드 개발자는 맘시터 서비스 운영에 필요한 DB 와 API 설계하고 구현하며, 프론트엔드 개발자와 많은 협업을 이루게 됩니다. 이 과제에서는 맘시터 서비스 개발을 위해 프론트엔드 개발자와 함께 구현해야 할 기능을 이해하고, 필요한 API 를 식별하고 스펙을 정의해 구현하고자 합니다. 아래는 구현해야 할 기능목록입니다.

* **회원가입**
	 * 맘시터 서비스를 이용하기 위해 회원가입을 할 수 있어야 합니다. 사용자는 회원가입시 시터 회원으로 가입할 수도 있고, 부모 회원으로도 가입할 수 있습니다. 회원가입시 제출 받아야 할 구체적인 정보는 아래의 시터회원 / 부모회원 정보 항목을 참고하세요.
 * **로그인**
	 * 회원가입시 제출한 아이디와 비밀번호를 이용해, 다른 API 를 호출 할 수 있는 인증토큰을 발급 받을 수 있습니다.
 * **내 정보 업데이트**
	 * 사용자는 회원가입시 제출한 정보를 수정할 수 있어야 합니다.
	 * 이 API 는 인증토큰을 이용한 사용자 인증 과정이 있어야 합니다.
 * **부모로도 활동하기 / 시터로도 활동하기**
	 * 어떤 사용자는 부모로 회원가입을 하였지만, 시터로도 활동하고 싶어합니다. 반대로 시터로 회원가입 하신 분들이 시간이 지나 부모로도 돌봄을 신청하고 싶어합니다. 따라서 추가적인 정보를 제출 받아서 시터 혹은 부모로도 서비스를 이용할 수 있도록, 지원해야 합니다. 예를 들어 시터로 회원가입을 하신 분은 케어를 원하는 아이 나이와 신청 내용 데이터를 추가적으로 받아서 부모로도 활동하실 수 있도록 해야 합니다.
	 * 이 API 는 인증토큰을 이용한 사용자 인증 과정이 있어야 합니다.
 * **내 정보 보기**
	 * 사용자는 작성한 내 정보를 조회할 수 있어야 합니다. 다만 회원 정보 중에서 비밀번호는 노출되지 않아야 합니다. 예를 들어 시터회원의 경우는 회원번호/이름/생년월일/성별/아이디/이메일/케어 가능한 최소 연령/자기소개 정보가 노출될 것입니다. 단, 추가적으로 부모로도 활동하기를 선택한 시터회원이라면 아이나이/신청 내용 정보가 추가적으로 노출되어야 합니다.
	 * 이 API 는 인증토큰을 이용한 사용자 인증 과정이 있어야 합니다.

아래는 시터회원, 부모회원이 가질 수 있는 정보들입니다.

|시터회원 정보|설명|예|
|---|---|---|
|회원번호|회원가입시 자동으로 부여되는 고유한 번호입니다.|5321
|이름|회원의 이름입니다.|박시터
|생년월일|회원의 YYYYMMDD 포맷의 생년월일입니다. |19980206
|성별|회원의 성별 정보입니다.|여
|아이디|회원이 회원가입시 제출한 고유한 아이디입니다.|wonderfulPark0206
|비밀번호|회원의 계정 비밀번호입니다.|parak0206%^
|이메일|회원의 이메일 정보입니다.|wonderfulPark0206@gmail.com
|케어 가능한 연령정보|시터회원이 케어 가능한 연령 범위 정보입니다.|3세 ~ 5세
|자기 소개|간단한 자기소개 정보입니다.|유아교육과를 전공중인 대학생 시터입니다! 사촌 동생들을 많이 돌본 경험이 있어서 아이랑 잘 놀아줄 수 있어요.|

---

|부모회원 정보|설명|예|
|---|---|---|
|회원번호|회원가입시 자동으로 부여되는 고유한 번호입니다.|5321
|이름|회원의 이름입니다.|박부모
|생년월일|회원의 YYYYMMDD 포맷의 생년월일입니다. |19861019
|성별|회원의 성별 정보입니다.|여
|아이디|회원이 회원가입시 제출한 고유한 아이디입니다.|kimParent86
|비밀번호|회원의 계정 비밀번호입니다.|86!@Kim
|이메일|회원의 이메일 정보입니다.|kim86@gmail.com
|아이정보|부모회원이 돌봄을 원하는 아이 정보입니다. 한명의 부모는 여러명의 아이 정보를 가질 수 있습니다. 아이는 생년월일, 성별 정보를 가질 수 있습니다. ||
|신청 내용|간단한 돌봄 신청 내용입니다.|하루에 2시간 정도 한글놀이를 해 줄 수 있는 시터를 찾습니다 :)|

## 제약사항

### 유효성 검사
API 구현시 아래의 정보들에 대한 유효성을 검증해야 합니다.

* 회원가입/회원정보 수정시 제출한 비밀번호가 비밀번호 정책에 부합하는지 확인해야 합니다. 비밀번호 정책은 임의로 정하면 됩니다. (e.g. 문자+숫자+특수문자, 7자리 이상)

이 이외에도 필요하다고 생각된다면 추가적으로 유효성 검사를 진행하셔도 좋습니다!

### 구현 언어
API 를 구현하는데, 사용하는 언어에는 제약이 없습니다!

### 사용 라이브러리
* NestJs, ExpressJs, Flask, Django, Spring, Laravel 와 같은 프레임워크 및 라이브러리는 사용가능합니다.
* API 구현에 직접적인 솔루션을 제공하는 프레임워크 사용은 제한합니다. (e.g. CRM 솔루션, Wordpress User management module 등)
* 인증토큰을 발급하기 위한 라이브러리는 자유롭게 사용하셔도 됩니다. (e.g. JWT, OAUTH2)
* 필요하다면 추가적으로 필요한 라이브러리를 사용할 수 있습니다. 다만 왜 이 라이브러리를 사용하는지에 대한 설명만 부탁드리겠습니다 :)

## 산출물
이 과제의 결과물로 아래의 산출물을 제출해주시면 됩니다.

### DB ERD, Creation query
DB dump 혹은, 테이블을 생성할 수 있는 쿼리 등의 형태로 제출해주시면 됩니다.

### API 스펙 문서
기능구현을 위해 식별하고 설계한 API 스펙 문서를 제공해주시면 됩니다. 스펙 문서 포맷은 정해져 있지 않습니다.

### 구현 코드
작성하신 코드를 제출해주시면 됩니다.

### 기타
* 부연 설명이 필요하다면 (e.g. 실행환경, README 등), 추가적인 문서를 제공해주셔도 좋습니다.
* 맘시터 서비스는 Typescript 를 사용하지만 과제는 언어사용에 제한이 없습니다.
* 맘시터는 요구사항의 효율적이고 적시적인 구현을 중요하게 생각합니다. 따라서, 사용해보지 않은 최신 기술을 익히는데 시간을 쏟기보다는, 본인에게 가장 효율적인 방법으로 과제를 수행하여 주세요.
* 위 요구사항 내에서는 자유롭게 구현하시면 됩니다! 예를 들어  아이 정보의 생년월일을 DATE로 모델링해도 되고 STRING 으로 모델링할 수도 있습니다. 스스로 판단해보시기에 유연하고, 확장성있는 어프로치를 취해주시면 됩니다.
* 요구사항을 다 구현하지 못하셨다면, 진행하신데까지만이라도 제출해주셔도 됩니다 :)

## Criteria
지원자분이 구현한 코드를 바탕으로 어떤점이 잘되었고 어떤점이 미흡한지 체크해보게 됩니다. 아래는 그 기준입니다.
-   서비스 기획 내용에 대한 정확한 이해 
-   기능 요구사항에 대한 효율적이고 적시적인 구현
-   버그를 최소화하는 높은 코드 품질
-   유연한 변경 사항 대응을 위한 재사용 및 확장 가능한 애플리케이션 구조 설계
-   적절하게 설계된 DB 구조
-   명확한 API 스펙

## Contact
과제 진행 관련하여 궁금하신 사항이 있으시면 언제든지 아래 이메일로 문의 부탁 드립니다.
채용담당자: [apply.momsitter@mfort.co.kr](mailto:apply.momsitter@mfort.co.kr)

**다시 한 번 맘시터에 지원해주시고, 본 과제에 참여해주셔서 진심으로 감사드립니다.**
