package map

import io.kotest.matchers.shouldBe
import map.move.Position
import mine.Mine
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GridTest {
    private lateinit var grid: Grid

    @BeforeEach
    fun beforeEach() {
        val height = Height(SIZE)
        val width = Width(SIZE)
        grid = Grid(points = Rows.ready(height = height, width = width))

        grid.place(
            Index(value = MINE_INDEX, maxSize = height.size),
            Index(value = MINE_INDEX, maxSize = width.size),
            element = Mine.ready(),
        )
    }

    @Test
    fun `탐색한 위치에 지뢰가 있다면 true를 반환한다`() {
        val searchIndex = MINE_INDEX

        grid.isMine(
            Position(
                row = Index(value = searchIndex, maxSize = SIZE),
                column = Index(value = searchIndex, maxSize = SIZE),
            ),
        ) shouldBe true
    }

    @Test
    fun `탐색한 위치에 지뢰가 없다면 false를 반환한다`() {
        val searchIndex = MINE_INDEX + 1

        grid.isMine(
            Position(
                row = Index(value = searchIndex, maxSize = SIZE),
                column = Index(value = searchIndex, maxSize = SIZE),
            ),
        ) shouldBe false
    }

    @Test
    fun `폭탄을 탐색할 위치가 없으면 false를 반환한다`() {
        grid.isMine(Position(row = null, column = null)) shouldBe false
    }

    companion object {
        private const val SIZE = 5
        private const val MINE_INDEX = 3
    }
}
