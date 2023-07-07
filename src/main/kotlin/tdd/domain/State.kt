package tdd.domain

sealed class State

sealed class Closed : State()

object Empty : Closed()

object Mine : Closed()
