package minesweeper.view.render.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.CoordinateFixture.toCoordinate
import minesweeper.model.point.TileType
import minesweeper.model.point.toAttribute

class AttributeRenderingStrategyTest : StringSpec({

    "AttributeRenderingStrategy 전략은 Attribute 과 symbolString 이 1:1 매칭 되어야 한다" {
        AttributeRenderingStrategy.symbol(TileType.NONE.toAttribute(), (0 to 0).toCoordinate()) shouldBe "C"
        AttributeRenderingStrategy.symbol(TileType.MINE.toAttribute(), (1 to 1).toCoordinate()) shouldBe "*"
        AttributeRenderingStrategy.symbol(TileType.FLAG.toAttribute(), (2 to 2).toCoordinate()) shouldBe "F"
    }
})
