# 좌표계(Coordinate System)

좌표계는 계층을 나누어 표현하였습니다.

`BaseCoordinateSystem` -> `MineCoordinateSystemDecorator` -> `Components`

- `BaseCoordinateSystem` 에서 크기에 맞는 빈 좌표지를 그립니다.
- `MineCoordinateSystemDecorator` 에서 좌표 내 위치값에 맞는 지뢰를 생성합니다.
- 빈 좌표와 생성된 지뢰의 위치를 중첩해서 표현한 `Components` 객체를 생성합니다.
- UI 애플리케이션은 `Components` 객체로만 좌표와 지뢰의 위치를 확인합니다. 

`MineCoordinateSystemDecorator`는 `BaseCoordinateSystem`를 데코레이터 패턴으로 표현하여
 기본 좌표계 위에 지뢰 위치를 표시하는 로직으로 구현하였습니다. 
