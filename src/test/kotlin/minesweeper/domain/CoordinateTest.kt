package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CoordinateTest {

    @DisplayName("지뢰 좌표 확인")
    @Test
    fun name() {
        val coordinate = Coordinate(Position(3, 3, 3), CoordinateType.MINE)

        coordinate.isMine() shouldBe true
    }
}
