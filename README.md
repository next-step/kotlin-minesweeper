# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)

### 기능 요구 사항

지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 기능 구현 목록

- 필드 정보 (FieldInfo)
    - [x] 필드의 높이와 너비 정보를 가지고 있다.

- 스팟(Spot)
    - [x] x, y 좌표를 가진다.
    - [x] 지뢰인지 아닌지 여부를 반환할 수 있다.
    - [x] 지뢰 (MineSpot)
        - 지뢰가 있는 스팟을 나타낸다.
    - [x] 안전지대 (SafeSpot)
        - 지뢰가 없는 스팟을 나타낸다.

- 필드 (Field)
    -  [x] 필드 정보의 높이와 너비 만큼의 스팟을 가진다.
    -  [x] 필드 정보와 지뢰 개수를 통해 필드를 만들 수 있다.
    -  [x] 스팟 생성기에 따라 필드에 지뢰를 설치하는 방법이 달라진다.

- 스팟 생성기 (SpotGenerator)
    - [x] 필드 정보와 지뢰 개수를 받아 필드의 각 좌표에 대응될 스팟들을 생성한다.

## 🚀 2단계 - 지뢰 찾기(지뢰 개수)

### 기능 요구 사항

- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

### 기능 구현 목록

- 안전지대 (SafeSpot)
    - [x] 자신 주변에 있는 지뢰의 개수를 가지고 있다.

- 필드 (Field)
    - [x] 필드의 각 SafeSpot에 대해 주변에 있는 지뢰의 개수를 계산한다.
