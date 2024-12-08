package map

import cell.Cell
import io.kotest.matchers.shouldBe
import mine.Mine
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `해당 위치가 지뢰면 true를 반환한다`() {
        val point = Point(point = mock to mock, element = Mine.ready())
        point.isMine() shouldBe true
    }

    @Test
    fun `해당 위치가 지뢰가 아니면 false를 반환한다`() {
        val point = Point(point = mock to mock, element = Cell.ready())
        point.isMine() shouldBe false
    }

    companion object {
        private val mock = null
    }
}
