package test

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef.{http, status, _}

import java.util.UUID


class RequestCapacityTest extends BaseSimulation {

  var requestCounter: Int = 0

  object referenceGenerator {
    def getReference(): String = {
      val uuid = UUID.randomUUID
      val str = uuid.toString
      return "TESTE-PERFOM-UUID-" + str
    }

  }

  /**
   *  Caso seja utilizado uma request de acordo com um JSON e alterando informações deste JSON
   */
  /*val RequestModel = Source.fromFile(Profile.get("json_parametrizado_profile")).getLines.mkString
  var RequestFeed = Iterator.continually(Map("definir_body" -> (RequestModel.replace("%UUID_RANDOM%", referenceGenerator.getReference()).replace("%CUSTOMER%", customer).replace("%SELLER%", seller))))
*/

  def createRequest(): ChainBuilder = {
    //feed(RequestFeed)
    exec(
      http("Request de Test de Performance")
        .get("/api/users?page=2")


        /**
         *  Caso Seja utilizado Headers e Body baseando em um arquivo JSON
         */
        /*.headers(headers_0)
          .header("Authorization", Profile.get("Authorization"))
          .body(StringBody("""${definir_body}"""))*/

        /**
         *  Validações de retorno da request
         */
        .check(status.in(200))
        .check(jsonPath("$.page").is("2"))
        .check(jsonPath("$.per_page").is("6"))
        .check(jsonPath("$.total").is("12"))

        /**
         *  Salvando informações de retorno da Request para imprimir no terminal
         */
        .check(jsonPath("$.page").saveAs("page"))
        .check(status.saveAs("httpStatus"))
    )

      /**
       *  Obter os dados da sessão e imprimir no terminal
       */
      .exec( session =>{
        val page = session("page").asOption[String]
        val httpStatus = session("httpStatus").asOption[String]
        val status = session("status").asOption[String]
        println("Pagina Retornada" + page.getOrElse("[NOT FOUND!]") +
          ", Status: " + status.getOrElse("[NOT FOUND!]") +
          ", is HTTP: " + httpStatus.getOrElse("[NOT FOUND!]"))
        session
      })
  }

  val requisicao: ScenarioBuilder = scenario("Cenario de Teste de performance")
    .exec(createRequest())

  setUp(
    requisicao.inject(constantUsersPerSec(totalUsers) during (rampDuration))
  ).protocols(httpProtocol)
}

