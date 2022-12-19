package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import kotlin.IllegalArgumentException

internal class MineCountTest : StringSpec({
    "지뢰의 개수가 전체 매트릭스의 개수보다 크다면 에러가 발생한다." {
        val row = Row(5)
        val column = Column(5)
        val mineCount = 26

        shouldThrow<IllegalArgumentException> {
            MineCount.from(row, column, mineCount)
        }
    }
    "지뢰의 개수가 0이거나 0보다 작다면 에러가 발생한다." {
        val mineCountList = listOf(0, -1)
        mineCountList.forAll {
            shouldThrow<IllegalArgumentException> {
                MineCount(it)
            }
        }
    }
})
