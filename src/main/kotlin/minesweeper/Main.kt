package minesweeper

import minesweeper.domain.service.GameBoardCellOpener
import minesweeper.domain.service.GameBoardCreator
import minesweeper.ui.MinesweeperController

fun main() {
    val controller =
        MinesweeperController(
            gameBoardCreator = GameBoardCreator(),
            gameBoardCellOpener = GameBoardCellOpener(),
        )

    controller.play()
}
