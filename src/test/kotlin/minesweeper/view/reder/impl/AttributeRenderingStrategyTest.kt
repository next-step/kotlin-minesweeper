package minesweeper.view.reder.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.Attribute
import minesweeper.model.point.CoordinateFixture.toCoordinate

class AttributeRenderingStrategyTest : StringSpec({

    "AttributeRenderingStrategy 전략은 Attribute 과 symbolString 이 1:1 매칭 되어야 한다" {
        AttributeRenderingStrategy.symbol(Attribute.NONE, (0 to 0).toCoordinate()) shouldBe "C"
        AttributeRenderingStrategy.symbol(Attribute.MINE, (1 to 1).toCoordinate()) shouldBe "*"
        AttributeRenderingStrategy.symbol(Attribute.FLAG, (2 to 2).toCoordinate()) shouldBe "F"
    }
})
