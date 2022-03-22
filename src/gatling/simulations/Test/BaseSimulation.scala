package Test

import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import _root_.configuration.Profile

import java.util.concurrent.atomic.AtomicBoolean

class BaseSimulation extends Simulation  {

  val httpProtocol = http
    .baseUrl(Profile.get("Url"))

  // Caso seja utilizado request com headers
      /*val headers_0 = Map(
        "Content-Type" -> Profile.get("Content-Type"),
        "Authorization" -> Profile.get("Authorization"),
      )*/

  //Definições do controle da execução do teste de carga
  def initialUsersQtt: Int = getProperty("initialUsers", "3").toInt
  def totalUsers: Int = getProperty("totalUsers", "3").toInt
  def rampDuration: Int = getProperty("rampDuration", "3").toInt
  val continue = new AtomicBoolean(true)

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

}
