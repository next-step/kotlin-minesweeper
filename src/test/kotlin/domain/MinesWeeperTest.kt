package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MinesWeeperTest {
    @Test
    internal fun `지뢰찾기는 입력한 높이와 너비의 모양으로 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.size shouldBe 10
        game.boards[0].size shouldBe 5
        game.mines.values.size shouldBe 3
    }

    @Test
    internal fun `지뢰 자리에는 *이 들어간다`() {
        val array = Array(10) { Array(10) { "C" } }
        val mines = Mines(listOf(Location(0, 1), Location(2, 3)))
        val game = MinesWeeper(array, mines)

        game.boards[1][0] shouldBe "*"
        game.boards[3][2] shouldBe "*"
    }

    @Test
    internal fun `지뢰외의 자리에는 C가 들어간다`() {
        val array = Array(10) { Array(10) { "C" } }
        val mines = Mines(listOf(Location(0, 1), Location(2, 3)))
        val game = MinesWeeper(array, mines)

        game.boards[4][3] shouldBe "C"
        game.boards[5][2] shouldBe "C"
    }
}
