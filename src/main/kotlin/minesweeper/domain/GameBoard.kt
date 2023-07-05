package minesweeper.domain

import kotlin.random.Random

class GameBoard(
    val size: GameBoardSize,
    private val pins: Pins = Pins.of(size)
) {
    init {
        require(pins.getPinsSize() == size.getLinearSize()) { "사이즈가 맞지 않습니다" }
    }

    fun repeatPlateMineWithoutDuplication(num: Int) {
        repeat(num) {
            placeMineWithoutDuplicate()
        }
    }

    private fun placeMineWithoutDuplicate() {
        do {
            val heightPositionStrategy: () -> Int = { Random.nextInt(size.height) }
            val widthPositionStrategy: () -> Int = { Random.nextInt(size.width) }

            val isFinishSettingMine = setMine(heightPositionStrategy, widthPositionStrategy)
        } while (!isFinishSettingMine)
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
        changeMineNumber(height, width)
        return true
    }

    private fun changeMineNumber(height: Int, width: Int) {
        for (dim in 0 until DIMENSION_SIZE) {
            val targetHeight = height + HEIGHT_MOVE[dim]
            val targetWidth = width + WIDTH_MOVE[dim]
            if (targetHeight !in 0 until size.height) continue
            if (targetWidth !in 0 until size.width) continue

            val index = getIndex(targetHeight, targetWidth)
            pins.addMineNumber(index)
        }
    }

    fun getPin(height: Int, width: Int): Pin {
        val index = getIndex(height, width)
        return pins.getPinAt(index)
    }

    private fun getIndex(height: Int, width: Int): Int {
        return size.width * height + width
    }

    companion object {
        private val HEIGHT_MOVE = listOf(-1, 0, 1, 0)
        private val WIDTH_MOVE = listOf(0, 1, 0, -1)
        private const val DIMENSION_SIZE = 4
    }
}
