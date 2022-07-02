package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardGeneratorTest {
    @Test
    internal fun `create()를 하면 결과 list의 size는 gameInfo의 가로 * 세로와 같다`() {
        val gameInfo = GameInfo(5, 5, 10)
        val boardGenerator = BoardGenerator(gameInfo)
        assertThat(boardGenerator.create()).hasSize(25)
    }

    @Test
    internal fun `create()를 하면 결과 list의 mineCount는 gameInfo의 mineCount와 같다`() {
        val gameInfo = GameInfo(5, 5, 10)
        val boardGenerator = BoardGenerator(gameInfo)
        val cells = boardGenerator.create()

        assertThat(cells.filter { it.square is Square.Mine }).hasSize(10)
        assertThat(cells.filter { it.square is Square.NonMine }).hasSize(15)
    }

    private fun GameInfo(vertical: Int, horizontal: Int, mineCount: Int) = GameInfo(
        PositiveNumber(vertical),
        PositiveNumber(horizontal),
        PositiveNumber(mineCount)
    )
}
