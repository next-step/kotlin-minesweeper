import domain.Ground
import domain.Mine
import domain.MineField
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldTest {
    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot이 Mine인지 확인 할 수 있다`() {
        val mineField = MineField(listOf(mutableListOf(Mine(), Mine(), Mine(), Mine(), Ground())))
        val actualMineResult = mineField.isMine(0, 1)
        val actualGroundResult = mineField.isMine(0, 4)
        assertThat(actualMineResult).isTrue
        assertThat(actualGroundResult).isFalse
    }

    @Test
    fun `x, y(좌표)값이 주어 졌을때 해당 위치 Slot의 Check 여부를 확인 할 수 있다`() {
        val mineField = MineField(listOf(mutableListOf(Mine(), Mine(), Mine(), Mine(), Ground(true))))
        val actualCheckedResult = mineField.isChecked(0, 1)
        val actualUnCheckedResult = mineField.isChecked(0, 4)
        assertThat(actualCheckedResult).isFalse
        assertThat(actualUnCheckedResult).isTrue
    }
}
