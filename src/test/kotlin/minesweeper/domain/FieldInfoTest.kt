package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FieldInfoTest : StringSpec({
    "높이와 너비 정보를 가지고 있다." {
        forAll(
            row(1, 1),
        ) { height, width ->
            val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))

            fieldInfo.getHeight() shouldBe height
            fieldInfo.getWidth() shouldBe width
        }
    }
})
