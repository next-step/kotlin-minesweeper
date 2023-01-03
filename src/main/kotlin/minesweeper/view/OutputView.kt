package minesweeper.view

import minesweeper.controller.dto.GameFinishedResponse
import minesweeper.controller.dto.GameMapDisplayResponse

class OutputView {

    fun display(dto: GameMapDisplayResponse) {
        dto.blocksModels.chunked(dto.width).forEach { blockViewModelList ->
            println(blockViewModelList.joinToString(" "))
        }
    }

    fun display(dto: GameFinishedResponse) {
        println(dto.gameFinished.string)
    }
}
