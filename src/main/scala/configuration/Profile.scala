package configuration

object Profile {

  var ambiente: Map[String, String] = {
    Map(
      "Url" -> "https://reqres.in",
      "Content-Type" -> "application/json",
      "Authorization" -> "Bearer XXXXX",
      "json_parametrizado_profile"->  "../performance-gatling-test/src/gatling/Requests/requestTest.json")
  }

  var environment: String = sys.env.getOrElse("ENV", "ambiente")

  def get(key: String): String = {
    if (environment == "ambiente") {
      ambiente(key)
    } else {
      "Sem profile"
    }
  }

}
