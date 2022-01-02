import domain.FieldSize
import domain.Ground
import domain.Mine
import domain.MineField
import domain.MineLine
import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldTest {
    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot이 Mine인지 확인 할 수 있다`() {
        val mineField = MineField(
            listOf(
                MineLine(
                    listOf(
                        Mine(point = Point(0, 0)),
                        Mine(point = Point(1, 0)),
                        Mine(point = Point(2, 0)),
                        Mine(point = Point(3, 0)),
                        Ground(true, Point(4, 0))
                    )
                )
            ),
            FieldSize(5, 1)
        )
        val actualMineResult = mineField.isMine(Point(1, 0))
        val actualGroundResult = mineField.isMine(Point(4, 0))
        assertThat(actualMineResult).isTrue
        assertThat(actualGroundResult).isFalse
    }

    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot의 Check 여부를 확인 할 수 있다`() {
        val mineField = MineField(
            listOf(
                MineLine(
                    listOf(
                        Mine(point = Point(0, 0)),
                        Mine(point = Point(1, 0)),
                        Mine(point = Point(2, 0)),
                        Mine(point = Point(3, 0)),
                        Ground(true, Point(4, 0))
                    )
                )
            ),
            FieldSize(5, 1)
        )
        val actualCheckedResult = mineField.isChecked(Point(1, 0))
        val actualUnCheckedResult = mineField.isChecked(Point(4, 0))
        assertThat(actualCheckedResult).isFalse
        assertThat(actualUnCheckedResult).isTrue
    }

    @Test
    fun `Mine이 아닌 Slot은 주변 지뢰따라 slot의 numberOfNearMines 값이 설정 된다`() {
        val fieldSize = FieldSize(5, 3)
        val mineField = MineField.createByIndexs(
            setOf(
                Point(0, 0),
                Point(2, 0),
                Point(4, 0),
                Point(1, 1)
            ),
            fieldSize
        )
        mineField.setNearMines()
        val actualResult = mineField.nearMinesNumberAt(Point(1, 0))
        assertThat(actualResult).isEqualTo(3)
    }
}
