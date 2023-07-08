package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private fun Int.toHeight() = Length.of(this.toString())
private fun Int.toWidth() = Length.of(this.toString())
private fun Int.toCount() = MineCount.of(this.toString())
class MineMapTest : StringSpec({

    "지뢰는 입력한 개수만큼 생성되어야 한다" {
        val mineMap = MineMap(10.toWidth() to 10.toHeight(), 10.toCount())
        var mineCount = 0
        mineMap.mineMap.forEach {
            mineCount += it.filterMine().size
        }
        mineCount shouldBe 10
    }

    "지뢰는 넓이*너비의 수보다 작거나 같아야한다" {
        shouldThrow<IllegalArgumentException> {
            MineMap(1.toWidth() to 1.toHeight(), 10.toCount())
        }

        shouldNotThrow<IllegalArgumentException> {
            MineMap(10.toWidth() to 1.toHeight(), 9.toCount())
        }
    }
})
