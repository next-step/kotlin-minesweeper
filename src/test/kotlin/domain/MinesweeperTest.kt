package domain

import enums.MinesweeperShape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

class MinesweeperTest {

    private lateinit var minesweeper: Minesweeper
    private var height by Delegates.notNull<Int>()
    private var width by Delegates.notNull<Int>()

    @BeforeEach
    fun setup() {
        height = 10
        width = 10
        val mineCount = 10

        minesweeper = Minesweeper(height = height, width = width, mines = List(mineCount) { Mine(MinesweeperShape.MINE) })
    }

    @Test
    fun `입력 받은 높이와 너비 값에 따라 만들어진 지뢰 맵의 크기는 높이와 너비의 곱과 같다`() {

        var actual = 0

        minesweeper.mineMap.forEach { row ->
            row.forEach {
                actual++
            }
        }

        actual shouldBe height * width
    }

    @Test
    fun `지뢰 맵에 입력 받은 지뢰 개수 만큼 지뢰가 존재한다`() {

        var actual = 0
        val mineShape = '*'

        minesweeper.distributeMine()

        minesweeper.mineMap.forEach { row ->
            row.forEach {
                if (it == MinesweeperShape.MINE) actual++
            }
        }

        actual shouldBe minesweeper.mines.size
    }
}
