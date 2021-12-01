package minesweeper.ui

import global.strategy.OutputStrategy

class OutputView(private val outputStrategy: OutputStrategy) {

    fun startGame() = outputStrategy.execute(START_GAME)

    companion object {
        private const val START_GAME = "지뢰찾기 게임 시작"
    }
}
