package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class MineFieldTest : StringSpec({
    "지정된 높이와 너비만큼의 필드를 생성한다." {
        val mineField = MineField(3, 4, 0)
        mineField.display().shouldHaveSize(3)
        mineField.display().all { it.split(" ").size == 4 } shouldBe true
    }

    "지뢰의 개수가 필드 크기 이하이어야 한다." {
        val height = 5
        val width = 5
        val mineCount = 10
        val mineField = MineField(height, width, mineCount)

        val flattened = mineField.display().flatMap { it.split(" ") }
        val mineCountInField = flattened.count { it == "*" }

        mineCountInField shouldBe mineCount
    }

    "지뢰가 올바르게 표시된다." {
        val mineField = MineField(2, 2, 4)
        val display = mineField.display()
        display.all { it.split(" ").all { it == "*" } } shouldBe true
    }
})
