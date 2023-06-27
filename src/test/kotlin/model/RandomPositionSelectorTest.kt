package model

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

@DisplayName("랜덤 위치 선택기")
class RandomPositionSelectorTest : StringSpec({

    "주어진 값을 최대로 랜덤한 포지션 반환" {
        (0..10).toList()
            .forAll {
                assertSoftly(nextRandomPosition(it, it)) { position ->
                    position.x shouldBeLessThanOrEqual it
                    position.y shouldBeLessThanOrEqual it
                }
            }
    }
})
