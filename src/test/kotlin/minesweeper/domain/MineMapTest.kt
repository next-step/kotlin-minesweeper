package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

private fun Int.toHeight() = Height(this.toString())
private fun Int.toWidth() = Width(this.toString())
private fun Int.toCount() = MineCount(this.toString())
class MineMapTest : AnnotationSpec() {

    @Test
    fun `지뢰는 입력한 개수만큼 생성되어야 한다`() {
        val mineMap = MineMap(10.toWidth(), 10.toHeight())
        mineMap.makeMine(10.toCount())
        var mineCount = 0
        mineMap.mineMap.forEach {
            mineCount += it.filter { it.isMine() }.size
        }
        mineCount shouldBe 10
    }

    @Test
    fun `지뢰는 넓이*너비의 수보다 작거나 같아야한다`() {
        shouldThrow<IllegalArgumentException> {
            val mineMap = MineMap(1.toWidth(), 1.toHeight())
            mineMap.makeMine(10.toCount())
        }

        shouldNotThrow<IllegalArgumentException> {
            val mineMap = MineMap(10.toWidth(), 1.toHeight())
            mineMap.makeMine(10.toCount())
        }
    }
}
