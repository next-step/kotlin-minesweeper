package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CellStateTest {
    private lateinit var cellState: CellState

    @BeforeEach
    fun setUp() {
        cellState = CellState()
    }

    @DisplayName("CellState 는 최초에 닫혀되어 있고, mine 이 아니다.")
    @Test
    fun initial() {
        assertAll(
            { assertThat(cellState.isOpen()).isFalse },
            { assertThat(cellState.isMine()).isFalse }
        )
    }

    @DisplayName("CellState 를 open 할 수 있다.")
    @Test
    fun open() {
        cellState = cellState.open()
        assertAll(
            { assertThat(cellState.isOpen()).isTrue },
            { assertThat(cellState.isMine()).isFalse }
        )
    }

    @DisplayName("CellState 가 mine 이 되도록 할 수 있다.")
    @Test
    fun layMine() {
        cellState = cellState.layMine()
        assertAll(
            { assertThat(cellState.isOpen()).isFalse },
            { assertThat(cellState.isMine()).isTrue }
        )
    }
}
