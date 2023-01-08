package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CoordinateTest {

    @DisplayName("생성된 좌표는 NONE 타입을 가진다.")
    @Test
    fun noneType() {
        val coordinate = Coordinate()

        coordinate.isMine() shouldBe false
    }

    @DisplayName("지뢰 타입으로 생성한 좌표는 MINE 타입을 가진다")
    @Test
    fun mineType() {
        val coordinate = Coordinate(CoordinateType.MINE)

        coordinate.isMine() shouldBe true
    }

    @DisplayName("생성된 좌표를 열면 열린 상태가 된다")
    @Test
    fun open() {
        val coordinate = Coordinate()

        coordinate.open()

        coordinate.isOpen() shouldBe true
    }

    @DisplayName("좌표에서 카운팅을 하면 1씩 증가한다")
    @Test
    fun counting() {
        val coordinate = Coordinate()

        coordinate.counting()

        coordinate.count shouldBe 1
    }
}
