package minesweeper.domain

import minesweeper.inputdata.MineGameConfig
import minesweeper.inputdata.PositiveNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BoomPositionMakerTest {

    @Test
    @DisplayName("입력한 폭탄 개수만큼 지뢰 위치를 생성한다")
    fun makePosition() {
        val mineGameCondition = MineGameConfig(
            width = PositiveNumber(3),
            height = PositiveNumber(3),
            mineCount = PositiveNumber(6)
        )
        val boomPosition = BoomPositionMaker.makePosition(mineGameCondition)
        assertThat(boomPosition.size).isEqualTo(6)
    }

    @Test
    @DisplayName("생성된 position들끼리는 겹치는 값이 없다.")
    fun positionNotEquals() {
        val mineGameCondition = MineGameConfig(
            width = PositiveNumber(3),
            height = PositiveNumber(3),
            mineCount = PositiveNumber(6)
        )
        repeat(50) {
            val boomPosition = BoomPositionMaker.makePosition(mineGameCondition)
            assertThat(boomPosition.toSet().size).isEqualTo(6)
        }
    }

    @Test
    @DisplayName("생성된 폭탄의 위치는 맵 위치와 일치한다")
    fun boomPositionMaker() {
        val mineGameCondition = MineGameConfig(
            width = PositiveNumber(2),
            height = PositiveNumber(2),
            mineCount = PositiveNumber(4)
        )
        val boomPosition = BoomPositionMaker.makePosition(mineGameCondition)
        assertThat(boomPosition).contains(
            BoomPosition(0, 0), BoomPosition(0, 1),
            BoomPosition(1, 0), BoomPosition(1, 1)
        )

    }
}
