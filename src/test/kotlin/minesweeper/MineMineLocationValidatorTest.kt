package minesweeper

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineMineLocationValidatorTest {

    private lateinit var mineLocationValidator: MineLocationValidator

    @BeforeEach
    fun setUp() {
        mineLocationValidator = MineLocationValidator()
    }

    @Test
    fun `지뢰의 위치가 중복된 곳이 있는지 확인한다`() {
        val height = 10
        val width = 10
        val mineLocationLists = listOf(
            MineLocation(0, 0),
            MineLocation(1, 6),
            MineLocation(6, 3),
            MineLocation(9, 9)
        )
        var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }

        mineLocationLists.forEach {
            val (x, y) = it
            board[x][y] = '*'
        }

        val actual = mineLocationValidator
            .isDuplicatedMineLocation(board, MineLocation(0, 0))

        Assertions.assertThat(actual).isEqualTo(true)
    }
}
