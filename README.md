# kotlin-minesweeper
1. 전체 칸의 개수를 구한다
2. 지뢰의 위치를 개수만큼 생성한다
3. w * h의 숫자 내에서 랜덤으로 숫자를 지뢰의 개수만큼 뽑는다.
4. 각 숫자를 w로 나눈 값이 row가 된다
5. 각 숫자를 w로 나눈 나머지 값이 col이 된다.
6. 지뢰찾기 게임판을 프린트한다
7. Mine의 position이 private될 수 있는지 확인한다


#Step2
1. Board를 만든다. mines를 가지고 있다.
2. Board는 특정 위치 주변에 mine이 몇 개인지 찾는다. Board.mineCountAround(position): Int
3. 찾은 값을 각 square에 넣는다.(Square.isMine()). Mine(val position: Position), NormalSquare(val position: Position, val mineCount: Int).
4. Board는 square의 2중 list를 갖는다.

#step3
1. board를 생성한 이후에는 open되지 않은 곳은 C로 표시한다. ResultView에서는 isOpen을 보고 표시를 결정한다.
2. open할 값을 받는다.
3. open한 곳이 지뢰이면 lose game을 출력하고 게임을 끝낸다.
4. open한 곳이 지뢰가 아니고 mineCountArount != 0이면 해당 부분만 열린다.
5. open한 곳이 지뢰가 아니고 mineCountArount == 0이면 인접한 곳은 모두 open한다. 만약, open한 위치도 mineCountArount == 0이면 마찬가지로 open한다. 