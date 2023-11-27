package minesweeper.view.reder.impl

import minesweeper.model.point.Attribute
import minesweeper.view.reder.MineRenderingStrategy

object AttributeRenderingStrategy : MineRenderingStrategy {

    private val symbolLookup: Map<Attribute, String> = mapOf(
        Attribute.MINE to "*",
        Attribute.FLAG to "F",
        Attribute.NONE to "C",
    )

    override fun symbol(attribute: Attribute): String {
        return requireNotNull(symbolLookup[attribute]) { "attribute=[$attribute] 를 표시할 방법이 정의되지 않았습니다" }
    }
}
