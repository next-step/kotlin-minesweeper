package domain

import kotlin.random.Random

interface RandomGenerator {
    /**
     * generate random integer [from] ~ [until](inclusive)
     */
    fun random(from: Int, until: Int): Int
}

class DefaultRandomGenerator : RandomGenerator {
    override fun random(from: Int, until: Int): Int {
        return Random.nextInt(from, until + 1)
    }
}
