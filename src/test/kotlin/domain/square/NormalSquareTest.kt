package domain.square

import domain.position.Position
import domain.square.mine.Mine
import domain.square.mine.Mines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class NormalSquareTest {

    @Test
    fun `주변에 있는 지뢰 개수를 찾는다`() {
        val mine1 = Mine(Position(0, 0))
        val mine2 = Mine(Position(0, 1))
        val mine3 = Mine(Position(0, 2))
        val mine4 = Mine(Position(1, 0))
        val mine5 = Mine(Position(1, 2))
        val mine6 = Mine(Position(2, 0))
        val mine7 = Mine(Position(2, 1))
        val mine8 = Mine(Position(2, 2))
        val mines = Mines(listOf(mine1, mine2, mine3, mine4, mine5, mine6, mine7, mine8))

        val normalSquare = NormalSquare(Position(1, 1), mines)

        Assertions.assertThat(normalSquare.mineCountAround).isEqualTo(8)
    }
}