package minesweeper_refactor.domain.board

import minesweeper_refactor.domain.coordinate.Coordinate

class BoardSize(private val width: Int, private val height: Int) {

    init {
        require(value = width in BOARD_SIZE_RANGE && height in BOARD_SIZE_RANGE) {
            "점은 $BOARD_SIZE_RANGE 사이에 위치해야 합니다. 입력값 : $width, $height"
        }
    }

    val area: Int = width * height

    fun toBlockGenerator(): BlockGenerator = (START_INDEX until width)
        .flatMap { Coordinate.createCoordinates(x = it, yRange = (START_INDEX until height)) }
        .shuffled()
        .toCollection(destination = ArrayDeque())
        .run(::BlockGenerator)

    companion object {
        private const val START_INDEX: Int = 0
        private val BOARD_SIZE_RANGE: IntRange = 0..100
    }
}
