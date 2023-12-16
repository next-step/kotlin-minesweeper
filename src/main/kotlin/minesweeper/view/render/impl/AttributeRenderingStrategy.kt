package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object AttributeRenderingStrategy : MineRenderingStrategy {

    private val symbolLookup: Map<Attribute, String> = mapOf(
        Attribute.MINE to "*",
        Attribute.GROUND to "C",
    )

    override fun symbolOf(board: Board, coordinate: Coordinate): String {
        val tileType = board.mines.attribute(coordinate)
        return requireNotNull(symbolLookup[tileType]) { "attribute=[$tileType] 를 표시할 방법이 정의되지 않았습니다" }
    }
}
