package model

class GameMap(height: Int, width: Int, mineCount: Int) {
    val map: List<List<MinePoint>>

    init {
        // 랜덤 지뢰 포지션 얻기
        val positions = MutableList(width * height) { i -> i / height to i % height }
        positions.shuffle()
        val mines = positions.take(mineCount)

        // 맵 생성
        map = List(height) { col ->
            List(width) { row ->
                MinePoint(mines.contains(col to row))
            }
        }
    }


}
