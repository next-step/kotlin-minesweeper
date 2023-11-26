# kotlin-minesweeper

## 기능 목록
- [x] 높이 입력 요청
- [x] 너비 입력 요청
- [x] 지뢰 개수 입력 요청
- [x] 지뢰찾기 게임 시작 출력
- [x] 게임 판 만들기
- [x] 특정 위치에 지뢰 심는다
- [x] 랜덤으로 위치를 생성하는 기능
- [x] 게암 판을 출력하기
- [x] 지뢰의 개수를 포함한 보드를 출력
- [x] 위치 주변에 지뢰 개수를 확인하는 기능
- [x] open 위치 입력 요청
- [x] 지뢰 리스트에 이 위치 포함하는지 확인하는 기능
- [x] 위치 주변에 지뢰 있는지 확인하는 기능
- [ ] 게임을 종료 출력
- [x] open 위치를 추가한다
- [x] 주변에 지뢰가 없는 위치를 open하는 기능
- [x] 포인트 주변의 point를 생성하는 기능
- [ ] open 위치를 출력한다

## 시나리오

### 시나라오 1

Board에서 지뢰 리스트와 열린 위치 리스트 저장

#### 지뢰 판단

지뢰 리스트를 순회하여 지뢰 여부를 확인

- O(N)

#### 주변에 지뢰 있는지 환인

전체 지뢰 리스트 확인과 각 지뢰에 대해 9개 방향을 확인

- O(N) * O(1) = O(N)

#### 그리기

전체 포인트를 확인하고, 각 포인트가 열린 위치에 포함되는지 확인

- O(N) * O(M) = O(N*M)

### 시나리오 2

Board에서 전체 리스트를 관리

#### 지뢰 판단

특정 위치에 대한 지뢰 여부를 직접 확인

- O(1)

#### 주변에 지뢰 있는지 확인

각 포인트에 대해 고정된 수의 이웃을 확인

- O(1)

#### 그리기

포인트의 상태를 직접 알 수 있으므로, 전체 포인트를 한 번 순회

- O(N)
