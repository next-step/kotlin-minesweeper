package model

class GameMap(height: Int, width: Int, mineCount: Int) {
    private var _map: MutableList<MutableList<MinePoint>>
    val map: List<List<MinePoint>>
        get() = _map.map { it.toList() }.toList()


    init {
        // 랜덤 지뢰 포지션 얻기
        val positions = MutableList(width * height) { i -> i / height to i % height }
        positions.shuffle()
        val mines = positions.take(mineCount)

        // 맵 생성
        _map = MutableList(height) { col ->
            MutableList(width) { row ->
                MinePoint(mines.contains(col to row))
            }
        }

        // 지뢰 개수 세기
        _map.forEachIndexed { col, colList ->
            colList.forEachIndexed { row, minePoint ->
                _map[col][row]= MinePoint(minePoint.isMine, getNearMineCount(col, row))
            }
        }
    }
    private fun getNearMineCount(col: Int, row: Int): Int {
        var count = 0
        for (i in -1..1) {
            for (j in -1..1) {
                val nearCol = col + i
                val nearRow = row + j
                if (nearCol < 0 || nearRow < 0 || nearCol >= _map.size || nearRow >= _map[0].size) {
                    continue
                }
                if (_map[nearCol][nearRow].isMine) {
                    count++
                }
            }
        }
        return count
    }
}
