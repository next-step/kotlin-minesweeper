package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CellTest {
    private lateinit var cell: Cell

    @BeforeEach
    fun setUp() {
        cell = Cell()
    }

    @DisplayName("Cell 은 초반에 닫혀있고, mine 이 아니며, mineNumber 가 0 이다.")
    @Test
    fun initial() {
        assertAll(
            { assertThat(cell.isOpen()).isFalse },
            { assertThat(cell.isMine()).isFalse },
            { assertThat(cell.mineNumber()).isZero }
        )
    }

    @DisplayName("Cell 은 open 할 수 있다.")
    @Test
    fun open() {
        cell.open()
        assertAll(
            { assertThat(cell.isOpen()).isTrue },
            { assertThat(cell.isMine()).isFalse },
            { assertThat(cell.mineNumber()).isZero }
        )
    }

    @DisplayName("Cell 을 mine 으로 지정할 수 있다.")
    @Test
    fun layMine() {
        cell.layMine()
        assertAll(
            { assertThat(cell.isOpen()).isFalse },
            { assertThat(cell.isMine()).isTrue },
            { assertThat(cell.mineNumber()).isZero }
        )
    }

    @DisplayName("Cell 의 mineNumber 를 증가시킬 수 있다.")
    @Test
    fun increaseMineNumber() {
        cell.increaseMineNumber()
        assertAll(
            { assertThat(cell.isOpen()).isFalse },
            { assertThat(cell.isMine()).isFalse },
            { assertThat(cell.mineNumber()).isEqualTo(1) }
        )
    }
}
