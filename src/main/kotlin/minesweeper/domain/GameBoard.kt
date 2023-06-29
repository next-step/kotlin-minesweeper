package minesweeper.domain

class GameBoard(
    val size: GameBoardSize,
    val pins: Pins = Pins.of(size)
) {
    init {
        require(pins.getPinsSize() == size.getLinearSize()) { "사이즈가 맞지 않습니다" }
    }

    fun setMine(heightPositionStrategy: () -> Int, widthPositionStrategy: () -> Int) {
        val height = heightPositionStrategy()
        val width = widthPositionStrategy()

        require(height < size.height) { "보드에서 높이가 맞지 않습니다" }
        require(width < size.width) { "보드에서 너비가 맞지 않습니다" }

        pins.changeMine(height, width)
    }
}
