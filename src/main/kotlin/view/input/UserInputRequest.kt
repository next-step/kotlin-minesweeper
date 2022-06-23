package view.input

import view.input.converter.InputConverter

data class UserInputRequest<T>(
    val message: String,
    val inputConverter: InputConverter<T>
)
