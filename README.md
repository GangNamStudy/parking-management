# parking-management
주차장 관리 프로그램의 주요 기능 중 입출차 관리

- **기반**: Spring Boot
- **기능**:
  - 차량 입출차 기록 관리
  - 차량별 입출차 이력 조회
  - 검색 조건을 활용한 데이터 필터링

#### **1. 차량 검색 기능 (GET)**

- 특정 차량의 상세 정보를 조회하거나 검색 조건(시간, 날짜, 번호판)으로 데이터를 필터링하는 API

  **조건 검색**
  - **URL**: `/api/vehicles/search`
  - **Query Parameters**:
    - `isParked` (optional): 현재 주차여부
    - `startDate` (optional): 검색 시작 날짜 (YYYY-MM-DD 형식)
    - `endDate` (optional): 검색 종료 날짜 (YYYY-MM-DD 형식)
    - `limit` (optional): 최대 검색 결과 개수
  - **설명**: 입력된 검색 조건을 활용하여 차량 입출차 이력을 조회
  - **예제 cURL 요청**:
    ```bash
    curl -X GET "http://localhost:8080/api/vehicles/search?plate=ABC123&startDate=2025-02-10&endDate=2025-02-15&limit=5" \
    -H "Content-Type: application/json"
    ```
  - **예제 응답**:
    - **HTTP Status**: 200 OK
    ```json
    [
      {
        "id": 123,
        "plate": "ABC123",
        "entryTime": "2025-02-15T14:00:00",
        "exitTime": "2025-02-15T18:00:00",
        "isParked": false
      }
    ]
    ```

#### **2. 개별 차량 및 번호판 검색**

- 특정 차량의 상세 정보를 조회하거나 검색 조건(시간, 날짜, 번호판)으로 데이터를 필터링하는 API

  **개별 차량 조회**
  - **URL**: `/api/vehicles/id/{id}`
  - **Path Parameter**:
    - `{id}`: 조회할 데이터의 고유 ID
  - **설명**: 데이터 entity의 고유 ID를 사용하여 검색
  - **예제 cURL 요청**:
    ```bash
    curl -X GET "http://localhost:8080/api/vehicles/id/123" -H "Content-Type: application/json"
    ```
  - **예제 응답**:
    - **HTTP Status**: 200 OK
    ```json
    {
      "id": 123,
      "plate": "ABC123",
      "entryTime": "2025-02-15T14:00:00",
      "exitTime": "2025-02-15T18:00:00",
      "isParked": false
    }
    ```
  **번호판으로 차량 조회**
  - **URL**: `/api/vehicles/plate/{numberplate}`
  - **Path Parameter**:
    - `{numberplate}`: 조회할 차량의 번호판
  - **설명**: 주차된 차량의 번호판을 사용하여 검색, 출차된 차량의 번호판을 넣으면 검색되지 않음
  - **예제 cURL 요청**:
    ```bash
    curl -X GET "http://localhost:8080/api/vehicles/plate/ABC123" -H "Content-Type: application/json"
    ```
  - **예제 응답**:
    - **HTTP Status**: 200 OK
    ```json
    {
      "id": 123,
      "plate": "ABC123",
      "entryTime": "2025-02-15T14:00:00",
      "exitTime": "2025-02-15T18:00:00",
      "isParked": false
    }
    ```

#### **3. 차량 입차 기능 (POST)**

- **URL**: `/api/vehicles/`
- **Body Parameters**:
  - `plate`: 차량 번호판
  - `entryTime` (optional): 입차 시간 (ISO 8601 형식) 
- **설명**: 차량 번호판을 포함한 요청을 전송하여 입차 기록 생성
- **예제 cURL 요청**:
  ```bash
  curl -X POST "http://localhost:8080/api/vehicles/" \
  -H "Content-Type: application/json" \
  -d '{"plate": "ABC123", "entryTime": "2025-02-15T14:00:00"}'
  ```
- **예제 응답**:
  - **HTTP Status**: 200 OK
  ```json
  {
    "status": "success",
    "plate": "ABC123",
    "entryTime": "2025-02-15T14:00:00"
  }
  ```

  #### **4. 차량 출차 기능 (PUT)**

- **URL**: `/api/vehicles/exit/{plate}`
- **Path Parameter**:
    - `plate`: 차량 번호판
- **Body Parameters**:
  - `exitTime` (optional): 출차 시간 (ISO 8601 형식)
- **설명**: 차량 번호판을 포함한 요청을 전송하여 출차 기록 생성
- **예제 cURL 요청**:
  ```bash
  curl -X POST "http://localhost:8080/api/vehicles/ABC123" \
  -H "Content-Type: application/json"
  ```
- **예제 응답**:
  - **HTTP Status**: 200 OK
  ```json
  {
    "status": "success",
    "plate": "ABC123",
    "entryTime": "2025-02-15T14:00:00",
    "exitTime": "2025-02-15T18:00:00"
  }
  ```

