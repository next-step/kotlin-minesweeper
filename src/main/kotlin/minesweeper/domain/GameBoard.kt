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
        fun ready(height: Int, width: Int): GameBoard {
            val size = GameBoardSize(height, width)
            val pins = TwoDimPins.of(size)
            return GameBoard(size, pins)
        }
    }
}
