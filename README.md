# performance-gatling-test
Utilização do framework do galting em linguagem scala

- **Pré requisitos Gerais**:
    - Instalar Plugin Scala no IntelliJ: `File` > `Settings` > `Plugins` > `Scala`
    - [Configuração JVM](https://gatling.io/docs/current/installation#intellij-idea): `File` > `Settings` > `Scala Compile Server`: `JVM Options` = `-server -Xss100M`
    - Importar o projeto no IntelliJ como projeto gradle


**Executar Teste**:
    ```
    ENV=ambiente ./gradlew clean gatlingRun-Test.RequestCapacityTest -DtotalUsers=2 -DrampDuration=2
    ```

- **Relatório html**:
  > ./results/


#### Referências

- [Gatling](https://gatling.io/)
- [Gatling Cheat Sheet](https://gatling.io/docs/current/cheat-sheet/)
- [Gatling Plugin for Gradle](https://github.com/lkishalmi/gradle-gatling-plugin)
