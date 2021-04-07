package minesweeper.domain

import minesweeper.inputdata.MineGameConfig
import minesweeper.inputdata.PositiveNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class MineMapTest {

    private lateinit var mineMap: MineMap

    @BeforeEach
    internal fun setUp() {
        val mineGameCondition = MineGameConfig(PositiveNumber(3), PositiveNumber(3), PositiveNumber(3))
        val boomPositions = listOf(
            BoomPosition(1, 2),
            BoomPosition(2, 1)
        )
        mineMap = MineMap(mineGameCondition, boomPositions)
    }

    @Test
    @DisplayName("지뢰의 위치를 입력받은 지뢰맵을 방어적 복사를 이용해 가져온다..")
    fun getMap() {
        val copiedMineMap = mineMap.getMap()
        assertThat(mineMap).isNotEqualTo(copiedMineMap)
    }

    @Test
    @DisplayName("open 키워드를 이용해 지정한 위치의 지뢰 여부를 알 수 있다.")
    fun openMap() {
        assertThat(mineMap.open(BoomPosition(1, 2))).isEqualTo(Mine.BOOM)
        assertThat(mineMap.open(BoomPosition(2, 1))).isEqualTo(Mine.BOOM)
        assertThat(mineMap.open(BoomPosition(0, 0))).isEqualTo(Mine.SAFE)
    }

    @Test
    @DisplayName("지뢰 위치와 맞지 않는 곳을 오픈하면 에러를 던진다.")
    fun openMapValidate() {
        assertThrows<IllegalArgumentException> {
            mineMap.open(BoomPosition(-1, 2))
        }
        assertThrows<IllegalArgumentException> {
            mineMap.open(BoomPosition(100, 100))
        }
    }
}
