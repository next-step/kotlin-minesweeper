package model

import kotlin.random.Random

private const val VALUE_STEP_TO_EQUAL = 1
private val RANDOM = Random.Default

fun nextRandomPosition(maxX: Int, maxY: Int): Position {
    require((0 <= maxX) and (0 <= maxY)) {
        "maxX and maxY must be positive. but provided maxX(`$maxX`), maxY(`$maxY`)"
    }
    return Position(
        RANDOM.nextInt(maxX + VALUE_STEP_TO_EQUAL),
        RANDOM.nextInt(maxY + VALUE_STEP_TO_EQUAL)
    )
}
