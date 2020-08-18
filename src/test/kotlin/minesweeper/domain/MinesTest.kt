package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MinesTest {

    private lateinit var mines: Mines
    private lateinit var minesExpected: List<Mine>

    @BeforeEach
    fun setUp() {
        mines = Mines(
            listOf(
                MinePosition(0, 0),
                MinePosition(1, 1),
                MinePosition(0, 0)
            ).map { Mine(it) }
        )

        minesExpected = listOf(
            MinePosition(0, 0),
            MinePosition(1, 1)
        ).map { Mine(it) }
    }

    @Test
    fun `중복을 검사한다`() {
        assertThat(mines.hasDuplicate()).isTrue()
    }

    @Test
    fun `중복 없는 지뢰 목록을 반환한다`() {
        // when
        val nonDuplicateMines = mines.duplicateRemoved()

        // then
        assertThat(nonDuplicateMines).isEqualTo(minesExpected)
        assertThat(nonDuplicateMines.size).isEqualTo(2)
    }
}
