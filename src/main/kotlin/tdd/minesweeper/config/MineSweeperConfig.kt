package tdd.minesweeper.config

import tdd.minesweeper.application.MineSweeperService
import tdd.minesweeper.cli.GameCli
import tdd.minesweeper.provider.DefaultRowsProvider
import tdd.minesweeper.provider.RowsProvider
import tdd.minesweeper.repository.MineBoardRepository
import tdd.minesweeper.repository.inmemory.MineBoardInMemoryRepository
import tdd.minesweeper.ui.GameInput
import tdd.minesweeper.ui.GameInputImpl
import tdd.minesweeper.ui.GameInputProxy
import tdd.minesweeper.ui.GameOutput
import tdd.minesweeper.ui.GameOutputImpl
import tdd.minesweeper.ui.MineSweeperController

object MineSweeperConfig {

    val gameCli: GameCli by lazy {
        GameCli(
            gameOutput = gameOutput,
            gameInput = gameInput,
            mineSweeperController = mineSweeperController
        )
    }
    private val mineSweeperController: MineSweeperController by lazy {
        MineSweeperController(
            mineSweeperService = mineSweeperService
        )
    }

    private val mineSweeperService: MineSweeperService by lazy {
        MineSweeperService(
            mineBoardRepository = mineBoardRepository,
            rowsProvider = rowsProvider
        )
    }

    private val mineBoardRepository: MineBoardRepository by lazy {
        MineBoardInMemoryRepository
    }

    private val rowsProvider: RowsProvider by lazy {
        DefaultRowsProvider
    }

    private val gameOutput: GameOutput by lazy {
        GameOutputImpl
    }
    private val gameInput: GameInput by lazy {
        GameInputProxy(target = GameInputImpl)
    }
}
