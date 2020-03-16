package scommons.examples.todos

sealed trait TodoType

object TodoType {

  case object All extends TodoType
  case object Active extends TodoType
  case object Complete extends TodoType
}
