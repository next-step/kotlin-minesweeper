package domain.vo

import io.kotest.core.spec.style.StringSpec
import kotlin.random.Random

internal class PointTest : StringSpec({

    "Point 인스턴스를 생성한다" {
        val point = Random.nextInt()
        Point(point)
    }
})
