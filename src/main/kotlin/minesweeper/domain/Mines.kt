package minesweeper.domain

@JvmInline
value class Mines(val mines: List<Mine>) {
    fun count(): Int {
        return mines.size
    }

    fun isMine(position: Position): Boolean {
        return mines.any { it.position.match(position) }
    }

    fun validateMines(mineMap: MineMap) {
        mines.forEach {
            if (!mineMap.isInMap(it.position)) {
                throw IllegalArgumentException("지뢰의 위치가 지뢰찾기 지도를 벗어났습니다.")
            }
        }
    }
}
