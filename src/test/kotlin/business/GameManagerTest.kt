package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameManagerTest {

    @Test
    fun `open한 cell이 지뢰가 아니고 모든 cell이 모두 열지 않는 상태이면 continue상태를 반환하다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells.of(5, 5))

        // when
        val open = gameManager.open(Point(0, 2))

        // then
        open shouldBe GameStatus.CONTINUE
    }

    @Test
    fun `open한 cell이 지뢰이면 game over상태를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells.of(5, 5))

        // when
        val open = gameManager.open(Point(0, 0))

        // then
        open shouldBe GameStatus.GAME_OVER
    }

    @Test
    fun `지뢰 뺴고 모두 open 되여 있으면 win 상태를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells.of(2, 2))

        // when
        val isOver = gameManager.isOver()

        // then
        isOver shouldBe GameStatus.WIN
    }

    @Test
    fun `지뢰 뺴고 모두 open 되여 있지 않으면 continue 상태를 반환한다`() {
        // given
        val gameManager = GameManager(mines(), OpenedCells.of(5, 5))

        // when
        val isOver = gameManager.isOver()

        // then
        isOver shouldBe GameStatus.CONTINUE
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
