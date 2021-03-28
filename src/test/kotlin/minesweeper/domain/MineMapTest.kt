package minesweeper.domain

import minesweeper.inputdata.MineGameCondition
import minesweeper.inputdata.PositiveNumber
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class MineMapTest {

    @Test
    @DisplayName("지뢰의 위치를 입력받은 지뢰맵을 생성할 수 있다.")
    fun openMap() {
        val mineGameCondition = MineGameCondition(PositiveNumber(3), PositiveNumber(3), PositiveNumber(3))
    }
}
