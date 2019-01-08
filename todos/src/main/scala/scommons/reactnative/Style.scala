package scommons.reactnative

import scommons.reactnative.Style._

import scala.scalajs.js

trait Style extends js.Object {
  
  val alignItems: js.UndefOr[String] = js.undefined
  val backgroundColor: js.UndefOr[String] = js.undefined
  val borderColor: js.UndefOr[String] = js.undefined
  val borderWidth: js.UndefOr[Int] = js.undefined
  val color: js.UndefOr[String] = js.undefined
  val flex: js.UndefOr[Int] = js.undefined
  val fontSize: js.UndefOr[Int] = js.undefined
  val fontWeight: js.UndefOr[String] = js.undefined
  val height: js.UndefOr[Int] = js.undefined
  val justifyContent: js.UndefOr[String] = js.undefined
  val margin: js.UndefOr[Int] = js.undefined
  val marginBottom: js.UndefOr[Int] = js.undefined
  val marginLeft: js.UndefOr[Int] = js.undefined
  val marginRight: js.UndefOr[Int] = js.undefined
  val marginTop: js.UndefOr[Int] = js.undefined
  val paddingLeft: js.UndefOr[Int] = js.undefined
  val paddingRight: js.UndefOr[Int] = js.undefined
  val paddingTop: js.UndefOr[Int] = js.undefined
  val shadowColor: js.UndefOr[String] = js.undefined
  val shadowOffset: js.UndefOr[ShadowOffset] = js.undefined
  val shadowOpacity: js.UndefOr[Double] = js.undefined
  val shadowRadius: js.UndefOr[Int] = js.undefined
  val textAlign: js.UndefOr[String] = js.undefined
  val width: js.UndefOr[Int] = js.undefined
}

object Style {

  trait ShadowOffset extends js.Object {

    val width: js.UndefOr[Int] = js.undefined
    val height: js.UndefOr[Int] = js.undefined
  }
}