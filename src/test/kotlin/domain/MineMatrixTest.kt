package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMatrixTest {

    private fun sut(
        height: Int,
        width: Int,
        mineCount: Int,
        minePositionGenerator: PositionGenerator? = null
    ) = MineMatrix(
        height = height,
        width = width,
        mineCount = mineCount,
        minePositionGenerator = minePositionGenerator ?: randomPositionGenerator(0, width - 1, 0, height - 1),
    )

    private fun randomPositionGenerator(xFrom: Int, xUntil: Int, yFrom: Int, yUntil: Int) = RandomPositionGenerator(
        DefaultRandomGenerator(),
        xFrom,
        xUntil,
        yFrom,
        yUntil
    )

    @Test
    fun `height x weight 만큼의 공간을 생성할 수 있다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val cells = sut.allCells()

        // then
        assertThat(cells.size).isEqualTo(height * width)
    }

    @Test
    fun `지뢰는 생성시 전달한 개수만큼만 생성되어야 한다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val cellMineCount = sut.allCells().filter { it.type.isMine() }.size

        // then
        assertThat(cellMineCount).isEqualTo(10)
    }

    @Test
    fun `0 미만의 높이를 가진 Cell 은 없다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val negativeHeightCellCount = sut.allCells().filter { it.position.y < 0 }.size

        // then
        assertThat(negativeHeightCellCount).isEqualTo(0)
    }

    @Test
    fun `0 미만의 위치를 가진 Cell 은 없다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val negativeWidthCellCount = sut.allCells().filter { it.position.x < 0 }.size

        // then
        assertThat(negativeWidthCellCount).isEqualTo(0)
    }

    @Test
    fun `height 이상의 높이를 가진 Cell 은 없다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val moreThanHeightCellCount = sut.allCells().filter { it.position.y >= height }.size

        // then
        assertThat(moreThanHeightCellCount).isEqualTo(0)
    }

    @Test
    fun `width 이상의 위치를 가진 Cell 은 없다`() {
        // given
        val height = 10
        val width = 10
        val mineCount = 10
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val moreThanWidthCellCount = sut.allCells().filter { it.position.x >= width }.size

        // then
        assertThat(moreThanWidthCellCount).isEqualTo(0)
    }

    @Test
    fun `모든 Cell 반환시 위치 순서는 높이 위치 순서를 따른다`() {
        // given
        val height = 2
        val width = 2
        val mineCount = 2
        val sut = sut(height = height, width = width, mineCount = mineCount)

        // when
        val cells = sut.allCells()

        // then
        assertThat(cells[0].position).isEqualTo(Position(0, 0))
        assertThat(cells[1].position).isEqualTo(Position(0, 1))
        assertThat(cells[2].position).isEqualTo(Position(1, 0))
        assertThat(cells[3].position).isEqualTo(Position(1, 1))
    }

    private fun CellType.isMine() = this == CellType.MINE
}
