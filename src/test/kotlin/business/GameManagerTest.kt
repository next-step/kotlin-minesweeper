package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameManagerTest {

    @Test
    fun `지뢰가 아니면 open하고 true를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells(5, 5))

        // when
        val open = gameManager.open(Point(0, 2))

        // then
        open shouldBe true
    }

    @Test
    fun `지뢰이면 open하지 않고 false를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells(5, 5))

        // when
        val open = gameManager.open(Point(0, 0))

        // then
        open shouldBe false
    }

    @Test
    fun `지뢰 뺴고 모두 open 되여 있으면 true를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells(2, 2))

        // when
        val isOver = gameManager.isOver()

        // then
        isOver shouldBe true
    }

    @Test
    fun `지뢰 뺴고 모두 open 되여 있지 않으면 false를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells(5, 5))

        // when
        val isOver = gameManager.isOver()

        // then
        isOver shouldBe false
    }

    private fun mines(): Mines = Mines(
        listOf(
            Mine.of(0, 0),
            Mine.of(0, 1),
            Mine.of(1, 0),
            Mine.of(1, 1)
        )
    )
}
