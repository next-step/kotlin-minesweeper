package util

import domain.Location

interface LocationGenerator {
    fun generate(): Location
}
