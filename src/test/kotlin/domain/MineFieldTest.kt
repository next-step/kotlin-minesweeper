package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class MineFieldTest : StringSpec({
    "지정된 높이와 너비만큼의 필드를 생성한다." {
        val mineField = MineField(
            height = Height(3),
            width = Width(4),
            mineCount = 0,
        )
        mineField.display().shouldHaveSize(3)
        mineField.display().all { it.split(" ").size == 4 } shouldBe true
    }

    "지뢰의 개수가 필드 크기 이하이어야 한다." {
        val mineField = MineField(
            height = Height(5),
            width = Width(5),
            mineCount = 10,
        )

        val flattened = mineField.display().flatMap { it.split(" ") }
        val mineCountInField = flattened.count { it == "*" }

        mineCountInField shouldBe 10
    }

    "지뢰가 올바르게 표시된다." {
        val mineField = MineField(
            height = Height(2),
            width = Width(2),
            mineCount = 4,
        )
        val display = mineField.display()
        display.all { it -> it.split(" ").all { it == "*" } } shouldBe true
    }

    "지뢰의 개수가 필드 크기를 초과하면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MineField(
                height = Height(5),
                width = Width(5),
                mineCount = 26,
            )
        }
    }
})
