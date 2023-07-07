package minesweeper.domain.game

import minesweeper.domain.data.PositiveNumber

class Board(private val width: PositiveNumber, private val height: PositiveNumber, private val mine: Mines) {

    init {
        require((width * height) > mine.size) {
            "지뢰 갯수가 많습니다."
        }
    }

    val board: List<Row>
        get() =
            List(height.number) { col ->
                Row(
                    List(width.number) { row ->
                        val coordinate = row position col
                        val isMine = mine.contains(coordinate)
                        val nearMineCount = mine.nearMineCount(coordinate)
                        Cell.of(isMine, nearMineCount)
                    }
                )
            }
}
