package minesweeper.view.reder.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.Attribute

class AttributeRenderingStrategyTest : StringSpec({

    "AttributeRenderingStrategy 전략은 Attribute 과 symbolString 이 1:1 매칭 되어야 한다" {
        AttributeRenderingStrategy.symbol(Attribute.NONE) shouldBe "C"
        AttributeRenderingStrategy.symbol(Attribute.MINE) shouldBe "*"
        AttributeRenderingStrategy.symbol(Attribute.FLAG) shouldBe "F"
    }
})
