package minesweeper.config

import minesweeper.application.MineSweeperService
import minesweeper.strategy.DefaultMineBoardCreateStrategy
import minesweeper.strategy.MineBoardCreateStrategy
import minesweeper.ui.MineSweeperController
import minesweeper.ui.MineSweeperInput
import minesweeper.ui.MineSweeperInputImpl
import minesweeper.ui.MineSweeperInputProxy
import minesweeper.ui.MineSweeperOutput
import minesweeper.ui.MineSweeperOutputImpl

object MineSweeperConfig {

    val mineSweeperController: MineSweeperController by lazy {
        MineSweeperController(
            mineSweeperInput = mineSweeperInput,
            mineSweeperOutput = mineSweeperOutput,
            mineSweeperService = mineSweeperService
        )
    }

    private val mineSweeperInput: MineSweeperInput by lazy {
        MineSweeperInputProxy(MineSweeperInputImpl())
    }
    private val mineSweeperOutput: MineSweeperOutput by lazy {
        MineSweeperOutputImpl
    }
    private val mineSweeperService: MineSweeperService by lazy {
        MineSweeperService(mineBoardCreateStrategy)
    }

    private val mineBoardCreateStrategy: MineBoardCreateStrategy by lazy {
        DefaultMineBoardCreateStrategy()
    }
}
