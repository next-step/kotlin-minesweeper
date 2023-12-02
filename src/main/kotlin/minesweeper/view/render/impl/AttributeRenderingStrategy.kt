package minesweeper.view.render.impl

import minesweeper.model.point.TileType
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object AttributeRenderingStrategy : MineRenderingStrategy {

    private val symbolLookup: Map<TileType, String> = mapOf(
        TileType.MINE to "*",
        TileType.FLAG to "F",
        TileType.NONE to "C",
    )

    override fun symbol(tileType: TileType, coordinate: Coordinate): String {
        return requireNotNull(symbolLookup[tileType]) { "attribute=[$tileType] 를 표시할 방법이 정의되지 않았습니다" }
    }
}
