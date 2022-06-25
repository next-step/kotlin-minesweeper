package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LocationTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "0:0",
            "0:1",
            "1:0",
            "10:10",
            "100:1"
        ],
        delimiter = ':'
    )
    fun `생성 성공 확인`(x: Int, y: Int) {
        assertDoesNotThrow { Location(x, y) }
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "-1:0",
            "-1:-1",
            "1:-1",
            "-10:-10"
        ],
        delimiter = ':'
    )
    fun `생성 실패 확인`(x: Int, y: Int) {
        assertThrows<IllegalArgumentException>("입력 좌표가 0 이상이어야 합니다.") {
            Location(x, y)
        }
    }

    @Test
    fun `pick 메서드 호출전 후 isMine 프로퍼티 확인`() {
        val sampleLocation = Location(1, 2)

        assertThat(sampleLocation.isMine).isFalse

        sampleLocation.pick()

        assertThat(sampleLocation.isMine).isTrue
    }

    @Test
    fun `pick 두번 호출시 예외 발생 확인`() {
        val sampleLocation = Location(1, 2)

        sampleLocation.pick()

        assertThrows<IllegalStateException>("이미 지뢰로 선택된 위치입니다.") {
            sampleLocation.pick()
        }
    }
}
