package minesweeper.domain

import minesweeper.domain.pin.MinePin
import minesweeper.domain.pin.Pin
import kotlin.random.Random

class GameBoard private constructor(
    val size: GameBoardSize,
    private val pins: Pins,
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

    fun openPin(height: Int, width: Int): Pin {
        val pin = pins.getPinsAt(height, width)
        openSurroundPin(height, width)
        return pin
    }

    fun isNotContinuable(): Boolean {
        val totalPin = size.height * size.width
        val openPinCount = pins.countOpenedPin()
        val minePinCount = pins.countMinePin()

        return totalPin == (openPinCount + minePinCount)
    }

    private fun openSurroundPin(height: Int, width: Int) {
        try {
            val targetPin = pins.getPinsAt(height, width)
            if (targetPin.isNotOpenable()) return
            if (targetPin.isMinePin()) return
            pins.openPinAt(height, width)
            (0 until DIM).forEach { num ->
                val nextHeight = height + HEIGHT_MOVE[num]
                val nextWidth = width + WIDTH_MOVE[num]
                if (pins.isExistMineSurround(height, width)) return
                openSurroundPin(nextHeight, nextWidth)
            }
        } catch (e: Exception) {
            return
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
            val pins = Pins.of(size)
            return GameBoard(size, pins)
        }
    }
}
