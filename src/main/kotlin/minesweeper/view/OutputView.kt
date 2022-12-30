package minesweeper.view

import minesweeper.controller.dto.GameMapDisplayResponse

class OutputView {

    fun displayMap(dto: GameMapDisplayResponse) {
        dto.blocksModels.chunked(dto.width).forEach { blockViewModelList ->
            println(blockViewModelList.joinToString(" "))
        }
    }
}
