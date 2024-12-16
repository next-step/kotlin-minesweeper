package minesweeper.domain

class Vulture : LandminePlanter {
    override fun plant(location: Location): Landmine = Landmine(location)
}
