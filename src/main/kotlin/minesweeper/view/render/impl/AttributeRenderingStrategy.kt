package minesweeper.view.render.impl

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object AttributeRenderingStrategy : MineRenderingStrategy {

    private val symbolLookup: Map<Attribute, String> = mapOf(
        Attribute.MINE to "*",
        Attribute.FLAG to "F",
        Attribute.NONE to "C",
    )

    override fun symbol(attribute: Attribute, coordinate: Coordinate): String {
        return requireNotNull(symbolLookup[attribute]) { "attribute=[$attribute] 를 표시할 방법이 정의되지 않았습니다" }
    }
}
