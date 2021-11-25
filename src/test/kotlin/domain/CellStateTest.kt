package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CellStateTest {
    @DisplayName("CellState 는 최초에 닫혀있고, mine 이 아니다.")
    @Test
    fun initial() {
        val cellState = CellState()
        assertAll(
            { assertThat(cellState.isOpen()).isFalse },
            { assertThat(cellState.hasMine()).isFalse }
        )
    }

    @DisplayName("CellState 를 open 할 수 있다.")
    @Test
    fun open() {
        val cellState = CellState().open()
        assertAll(
            { assertThat(cellState.isOpen()).isTrue },
            { assertThat(cellState.hasMine()).isFalse }
        )
    }

    @DisplayName("CellState 를 mine 으로 지정할 수 있다.")
    @Test
    fun layMine() {
        val cellState = CellState().layMine()
        assertAll(
            { assertThat(cellState.isOpen()).isFalse },
            { assertThat(cellState.hasMine()).isTrue }
        )
    }

    @DisplayName("CellState 를 open 하고 mine 으로 지정할 수 있다.")
    @Test
    fun openAndLayMine() {
        val cellState = CellState().open().layMine()
        assertAll(
            { assertThat(cellState.isOpen()).isTrue },
            { assertThat(cellState.hasMine()).isTrue }
        )
    }

    @DisplayName("상태가 같다면 같은 CellState 로 인식되어야 한다.")
    @Test
    fun equals() {
        assertThat(CellState().open().layMine())
            .isEqualTo(CellState().layMine().open())
    }
}
