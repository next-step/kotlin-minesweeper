package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionTest {

    private lateinit var position: Position

    @BeforeEach
    fun setup() {
        position = Position(2, 2)
    }

    @Test
    fun `Position은 Row와 Column을 가진다`() {
        assertAll(
            { assertThat(position.row).isEqualTo(Row(2)) },
            { assertThat(position.column).isEqualTo(Column(2)) }
        )
    }

    @Test
    fun `Position의 위치 이동 기능 테스트`() {
        assertAll(
            { assertThat(position.topLeft()).isEqualTo(Position(1, 1)) },
            { assertThat(position.top()).isEqualTo(Position(1, 2)) },
            { assertThat(position.topRight()).isEqualTo(Position(1, 3)) },
            { assertThat(position.left()).isEqualTo(Position(2, 1)) },
            { assertThat(position.right()).isEqualTo(Position(2, 3)) },
            { assertThat(position.bottomLeft()).isEqualTo(Position(3, 1)) },
            { assertThat(position.bottom()).isEqualTo(Position(3, 2)) },
            { assertThat(position.bottomRight()).isEqualTo(Position(3, 3)) },
        )
    }
}
