import domain.FieldSize
import domain.Ground
import domain.Mine
import domain.MineField
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldTest {
    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot이 Mine인지 확인 할 수 있다`() {
        val mineField = MineField(listOf(arrayOf(Mine(), Mine(), Mine(), Mine(), Ground(nearMines = 0))))
        val actualMineResult = mineField.isMine(0, 1)
        val actualGroundResult = mineField.isMine(0, 4)
        assertThat(actualMineResult).isTrue
        assertThat(actualGroundResult).isFalse
    }

    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot의 Check 여부를 확인 할 수 있다`() {
        val mineField = MineField(listOf(arrayOf(Mine(), Mine(), Mine(), Mine(), Ground(true, 0))))
        val actualCheckedResult = mineField.isChecked(0, 1)
        val actualUnCheckedResult = mineField.isChecked(0, 4)
        assertThat(actualCheckedResult).isFalse
        assertThat(actualUnCheckedResult).isTrue
    }

    @Test
    fun `Mine이 아닌 Slot은 주변 지뢰따라 slot의 numberOfNearMines 값이 설정 된다`() {
        val fieldSize = FieldSize(5, 3)
        val mineField = MineField.createByIndexs(listOf(0, 2, 4, 6), fieldSize)
        val actualResult = mineField.nearMinesNumberAt(1, 0)
        assertThat(actualResult).isEqualTo(3)
    }
}
