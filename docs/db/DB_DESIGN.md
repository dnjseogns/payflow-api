[파티션 및 인덱스 설계]
1. 대표 테이블 PAYMENT 설명
1) 테이블 및 인덱스 구조
   CREATE TABLE PAYMENT (
   merchant_id            VARCHAR2(50),
   base_date              VARCHAR2(8),
   tx_seq                 NUMBER,
   merchant_order_id      VARCHAR2(100),
   merchant_order_date    DATE,
   pay_method             VARCHAR2(20),
   pay_provider           VARCHAR2(10),
   amount                 NUMBER,
   pay_status             VARCHAR2(20),
   created_at             DATE
   )
   PARTITION BY RANGE (base_date)
   (
      PARTITION p_202601 VALUES LESS THAN ('20260201'),
      PARTITION p_202602 VALUES LESS THAN ('20260301'),
      PARTITION p_max VALUES LESS THAN (MAXVALUE)
   );

ALTER TABLE PAYMENT ADD CONSTRAINT PK_PAYMENT PRIMARY KEY (merchant_id, base_date, tx_seq);
CREATE INDEX IDX_PAYMENT_01 ON PAYMENT(base_date, merchant_id);
CREATE INDEX IDX_PAYMENT_02 ON PAYMENT(pay_status, base_date, merchant_id);
CREATE INDEX IDX_PAYMENT_03 ON PAYMENT(pay_method, pay_provider, base_date, merchant_id);

2) 설계이유
(1) PARTITION RANGE(BASE_DATE) 설계 이유
- 관리상 BASE_DATE 용이
- 큰 날짜범위스캔(BETWEEN)이 많으므로 파티션프루닝 효울이 좋다.
- SUBPARTITION HASH(MERCHANT_ID)까지 할 경우 안좋아지는 이유는 BASE_DATE 로만 조회 할 경우 성능이 더 떨어지기 때문이다.
(2) PK(merchant_id, base_date, tx_seq) 설계 이유
- BASE_DATE는 범위스캔(BETWEEN)이라는 가정하여, PK는 BASE_DATE보단 MERCHANT_ID를 선두로 하는 것이 INDEX엑세스 효율이 좋다.
(3) IDX_PAYMENT_01(base_date, merchant_id) 설계 이유
- 날짜 스캔용 인덱스
(4) IDX_PAYMENT_02(pay_status, base_date, merchant_id) 설계 이유
- pay_status(success, fail, cancel)라고 할 때, 데이터의 분포도는 fail, cancel이 매우 적다
- 따라서 fail, calcel 조회 시 매우 좋은 효율을 낼 수 있다
(5) IDX_PAYMENT_03(pay_method, pay_provider, base_date, merchant_id) 설계 이유
- pay_method + pay_proovider의 데이터 분포도는 매우 적기 때문에 매우 좋은 효율을 낼 수 있다.
- 개발 시 주의할 점으로 쿼리 작성 시 pay_method를 생략할 경우 index_ss가 일어나므로 가급적 pay_method를 같이 작성해준다.





[예상 조회패턴]
가맹점(merchant_id) + 날짜범위(base_date between) 조회 시 : merchant_id + base_date -> PK_PAYMENT 인덱스(BEST)
가맹점(merchant_id) + 날짜범위(base_date between) + 결제방법(pay_method) card(30%) 조회 시  -> PK_PAYMENT 인덱스(GOOD)
가맹점(merchant_id) + 날짜범위(base_date between) + 결제방법(pay_method) bank(3%) 조회 시  -> PK_PAYMENT(GOOD) or IDX_PAYMENT_01(GOOD)
가맹점(merchant_id) + 날짜범위(base_date between) + 결제상태(pay_status) success(98%) 조회시 -> PK_PAYMENT 인덱스(GOOD)
가맹점(merchant_id) + 날짜범위(base_date between) + 결제상태(pay_status) fail(1%), cancel(1%) 조회시 -> IDX_PAYMENT_02(BEST)

날짜범위(base_date between) 조회 시 -> IDX_PAYMENT_01(BEST)
날짜범위(base_date between) + 결제방법(pay_method) card(30%) 조회 시 -> IDX_PAYMENT_01 인덱스(FINE)
날짜범위(base_date between) + 결제방법(pay_method) bank(10%) 조회 시 -> IDX_PAYMENT_01 인덱스(GOOD)
날짜범위(base_date between) + 결제상태(pay_status) success(98%) 조회시 -> IDX_PAYMENT_01 인덱스(FINE)
날짜범위(base_date between) + 결제상태(pay_status) fail(3%), cancel(3%) 조회시 -> IDX_PAYMENT_02 인덱스 사용(BEST)

넓은 날짜범위(base_date between) 조회 시 -> partition 프루닝(GOOD)
*/




=========================================================
[DDL 작성 규칙]
1. 테이블 Prefix
- MST      : 마스터성 테이블
- MAP      : 매핑 테이블
- PAYMENT  : 결제 데이터
- AGG      : 집계 데이터

2. 공통 컬럼
   CREATED_AT DATE DEFAULT SYSDATE NOT NULL
   CREATED_BY VARCHAR2(50)
   UPDATED_AT DATE
   UPDATED_BY VARCHAR2(50)

3. 컬럼 네이밍 규칙
- CODE, DATE, NAME 등 의미있는 전체 단어 사용
- CD, DT, NM 과 같은 축약어 지양

4. 기타
- PK/FK 명시
- NOT NULL 적극 사용












