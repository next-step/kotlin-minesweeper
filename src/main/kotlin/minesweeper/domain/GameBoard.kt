package minesweeper.domain

import kotlin.random.Random

class GameBoard private constructor(
    val size: GameBoardSize,
    private val pins: TwoDimPins,
) {
    init {
        require(pins.getPinsSize() == size.getLinearSize()) { "사이즈가 맞지 않습니다" }
    }

    fun getPinAt(height: Int, width: Int): Pin {
        return pins.getPinsAt(height, width)
    }

    fun repeatPlateMineWithoutDuplication(num: Int) {
        repeat(num) {
            placeMineWithoutDuplicate()
        }
    }

    fun closePinAll() {
        pins.closeAllPin()
    }

    fun openPin(height: Int, width: Int): Boolean {
        val pin = pins.getPinsAt(height, width)
        if (pin.isMinePin()) return true
        if (isWin()) return true
        openSurroundPin(height, width)
        return false
    }

    fun isWin(): Boolean {
        val totalPin = size.height * size.width
        val openPinCount = pins.countOpenedPin()
        val minePinCount = pins.countMinePin()

        return totalPin == (openPinCount + minePinCount)
    }

    private fun openSurroundPin(height: Int, width: Int) {
        val targetPin = pins.getPinsAt(height, width)
        if (!targetPin.isOpenable()) return
        pins.openPinAt(height, width)
        (0 until DIM).forEach { num ->
            val nextHeight = height + HEIGHT_MOVE[num]
            val nextWidth = width + WIDTH_MOVE[num]
            if (openPinWithoutException(nextHeight, nextWidth)) {
                openSurroundPin(nextHeight, nextWidth)
            }
        }
    }

    private fun openPinWithoutException(height: Int, width: Int): Boolean {
        try {
            val pin = pins.getPinsAt(height, width)
            if (!pin.isOpenable()) return false
            pins.openPinAt(height, width)
            return true
        } catch (e: Exception) {
            return false
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

        val pin = pins.getPinsAt(height, width)

        if (pin is MinePin) {
            return false
        }

        pins.changeMine(height, width)
        return true
    }

    companion object {
        const val DIM = 8
        val HEIGHT_MOVE = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val WIDTH_MOVE = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

        fun ready(height: Int, width: Int): GameBoard {
            val size = GameBoardSize(height, width)
            val pins = TwoDimPins.of(size)
            return GameBoard(size, pins)
        }
    }
}
