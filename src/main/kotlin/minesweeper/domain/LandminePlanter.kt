package minesweeper.domain

interface LandminePlanter {
    fun plant(location: Location): Landmine
}
