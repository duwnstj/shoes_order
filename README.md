# ShoesOrder 👟  
신발 주문·관리 API 시스템 (V1)

---

## 📌 프로젝트 개요
ShoesOrder는 신발 매장·상품·주문·리뷰를 관리할 수 있는 API 서버입니다.  
Spring Boot 기반으로 설계되어, **JWT 인증·인가**, **QueryDSL 검색 최적화**, **Redis TTL 관리** 등을 통해 안정성과 확장성을 고려한 구조를 구현했습니다.  
현재 V1 버전(API 기능 개발 완료)이며, 이후 AWS 기반 배포 및 운영 환경 구성 예정입니다.

---

## 🛠 기술 스택

| 영역              | 사용 기술 |
|-------------------|-----------|
| **Language**      | Java 17 |
| **Framework**     | Spring Boot 3.x, Spring Security |
| **DB**            | MySQL, Redis |
| **ORM**           | Spring Data JPA, QueryDSL |
| **Build Tool**    | Gradle |
| **Container**     | Docker |
| **Test**          | JUnit 5, Mockito (준비 중) |
| **Docs**          | Spring REST Docs / Swagger (예정) |

---

## 🔑 주요 기능

### 1. 회원
- 회원 가입 / 로그인 / 로그아웃
- JWT Access·Refresh 토큰 발급, Redis TTL로 세션 관리
- 로그아웃 시 토큰 즉시 무효화

### 2. 주소
- 회원별 배송지 CRUD
- 기본 배송지 설정 기능

### 3. 매장(Store)
- 매장 등록 / 수정 / 삭제
- 매장별 상품 목록 조회

### 4. 상품(Product)
- 상품 등록 / 수정 / 삭제
- 카테고리·판매 여부·매장명 조건 검색(QueryDSL 동적 쿼리)

### 5. 주문(Order)
- 주문 생성 / 취소
- 주문 상태 관리(결제 대기 → 결제 완료 → 배송 중 → 배송 완료)

### 6. 리뷰(Review)
- 리뷰 작성 / 수정 / 삭제
- 상품별 리뷰 조회(페이징 + 정렬 지원)

---

## ⚙️ 아키텍처

```plaintext
[Client] → [Spring Boot API 서버]
           ├─ JWT 인증/인가
           ├─ Controller → Dto → Service → Repository
           ├─ MySQL (데이터 저장)
           └─ Redis (토큰/캐시 관리)
