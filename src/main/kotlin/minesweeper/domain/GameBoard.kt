package minesweeper.domain

class GameBoard(
    val size: GameBoardSize,
    private val pins: Pins = Pins.of(size)
) {
    init {
        require(pins.getPinsSize() == size.getLinearSize()) { "사이즈가 맞지 않습니다" }
    }

    fun setMine(heightPositionStrategy: () -> Int, widthPositionStrategy: () -> Int): Boolean {
        val height = heightPositionStrategy()
        val width = widthPositionStrategy()

        require(height < size.height) { "보드에서 높이가 맞지 않습니다" }
        require(width < size.width) { "보드에서 너비가 맞지 않습니다" }

        val index = getIndex(height, width)

        if (pins.getPinAt(index) is MinePin) {
            return false
        }

        pins.changeMine(index)
        return true
    }

    fun getPin(height: Int, width: Int): Pin {
        val index = getIndex(height, width)
        return pins.getPinAt(index)
    }

    private fun getIndex(height: Int, width: Int): Int {
        return size.width * height + width
    }
}
