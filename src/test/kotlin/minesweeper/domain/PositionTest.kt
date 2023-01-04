package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PositionTest {

    @DisplayName(" 3x3 판에서 4번 칸에서 좌상단 인덱스는 0이다")
    @Test
    fun topLeft() {
        val position = Position.TopLeft.calculate(3, 3, 4)

        position shouldBe 0
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 상단 인덱스는 1이다")
    @Test
    fun top() {
        val position = Position.Top.calculate(3, 3, 4)

        position shouldBe 1
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 우상단 인덱스는 2이다")
    @Test
    fun topRight() {
        val position = Position.TopRight.calculate(3, 3, 4)

        position shouldBe 2
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 좌측 인덱스는 3이다")
    @Test
    fun left() {
        val position = Position.Left.calculate(3, 3, 4)

        position shouldBe 3
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 우측 인덱스는 5이다")
    @Test
    fun right() {
        val position = Position.Right.calculate(3, 3, 4)

        position shouldBe 5
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 좌하단 인덱스는 6이다")
    @Test
    fun bottomLeft() {
        val position = Position.BottomLeft.calculate(3, 3, 4)

        position shouldBe 6
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 하단 인덱스는 7이다")
    @Test
    fun bottom() {
        val position = Position.Bottom.calculate(3, 3, 4)

        position shouldBe 7
    }

    @DisplayName(" 3x3 판에서 4번 칸에서 우하단 인덱스는 8이다")
    @Test
    fun bottomRight() {
        val position = Position.BottomRight.calculate(3, 3, 4)

        position shouldBe 8
    }

    @DisplayName(" 3x3 판에서 1번 칸에서 좌상단 인덱스는 음수이다")
    @Test
    fun invalidTopLeft() {
        val position = Position.TopLeft.calculate(3, 3, 1)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 1번 칸에서 상단 인덱스는 음수이다")
    @Test
    fun invalidTop() {
        val position = Position.Top.calculate(3, 3, 1)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 1번 칸에서 우상단 인덱스는 음수이다")
    @Test
    fun invalidTopRight() {
        val position = Position.TopRight.calculate(3, 3, 1)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 3번 칸에서 좌측 인덱스는 음수이다")
    @Test
    fun invalidLeft() {
        val position = Position.Left.calculate(3, 3, 3)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 5번 칸에서 우측 인덱스는 음수이다")
    @Test
    fun invalidRight() {
        val position = Position.Right.calculate(3, 3, 5)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 7번 칸에서 좌하단 인덱스는 음수이다")
    @Test
    fun invalidBottomLeft() {
        val position = Position.BottomLeft.calculate(3, 3, 7)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 7번 칸에서 하단 인덱스는 음수이다")
    @Test
    fun invalidBottom() {
        val position = Position.Bottom.calculate(3, 3, 7)

        position shouldBe -1
    }

    @DisplayName(" 3x3 판에서 7번 칸에서 우하단 인덱스는 음수이다")
    @Test
    fun invalidBottomRight() {
        val position = Position.BottomRight.calculate(3, 3, 7)

        position shouldBe -1
    }
}
