package minesweeper

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineLocationValidatorTest {

    private lateinit var mineLocationValidator: MineLocationValidator

    @BeforeEach
    fun setUp() {
        mineLocationValidator = MineLocationValidator()
    }

    @Test
    fun `지뢰의 위치가 중복된 곳이 있는지 확인한다`() {
        val height = 10
        val width = 10
        val locationList = listOf(
            Location(0, 0),
            Location(1, 6),
            Location(6, 3),
            Location(9, 9)
        )
        var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }

        locationList.forEach {
            val (x, y) = it
            board[x][y] = '*'
        }

        val actual = mineLocationValidator
            .isDuplicatedMineLocation(board, Location(0, 0))

        Assertions.assertThat(actual).isEqualTo(true)
    }
}
