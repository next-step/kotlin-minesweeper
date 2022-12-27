package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PositionTest {

    @DisplayName(" 3x3 판에서 0번 위치 검증")
    @Test
    fun top() {
        val position = Position(3, 3, 0)

        position.topLeft shouldBe -1
        position.top shouldBe -1
        position.topRight shouldBe -1
        position.left shouldBe -1
        position.right shouldBe 1
        position.bottomLeft shouldBe -1
        position.bottom shouldBe 3
        position.bottomRight shouldBe 4
    }

    @DisplayName(" 3x3 판에서 3번 위치 검증")
    @Test
    fun left() {
        val position = Position(3, 3, 3)

        position.topLeft shouldBe -1
        position.top shouldBe 0
        position.topRight shouldBe 1
        position.left shouldBe -1
        position.right shouldBe 4
        position.bottomLeft shouldBe -1
        position.bottom shouldBe 6
        position.bottomRight shouldBe 7
    }

    @DisplayName(" 3x3 판에서 4번 중앙 위치 검증")
    @Test
    fun center() {
        val position = Position(3, 3, 4)

        position.topLeft shouldBe 0
        position.top shouldBe 1
        position.topRight shouldBe 2
        position.left shouldBe 3
        position.right shouldBe 5
        position.bottomLeft shouldBe 6
        position.bottom shouldBe 7
        position.bottomRight shouldBe 8
    }

    @DisplayName(" 3x3 판에서 5번 위치 검증")
    @Test
    fun right() {
        val position = Position(3, 3, 5)

        position.topLeft shouldBe 1
        position.top shouldBe 2
        position.topRight shouldBe -1
        position.left shouldBe 4
        position.right shouldBe -1
        position.bottomLeft shouldBe 7
        position.bottom shouldBe 8
        position.bottomRight shouldBe -1
    }

    @DisplayName(" 3x3 판에서 7번 위치 검증")
    @Test
    fun bottom() {
        val position = Position(3, 3, 7)

        position.topLeft shouldBe 3
        position.top shouldBe 4
        position.topRight shouldBe 5
        position.left shouldBe 6
        position.right shouldBe 8
        position.bottomLeft shouldBe -1
        position.bottom shouldBe -1
        position.bottomRight shouldBe -1
    }
}
