# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Paradigma Reactivo en Sistema de Procesamiento de Pagos**.

| | |
|---|---|
| Tema | Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring WebFlux |
| Patron arquitectonico | microservicio reactivo con arquitectura hexagonal |
| Tiempo estimado | 1 semana |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-r2dbc 3.5.6
- io.r2dbc:r2dbc-postgresql 1.0.5.RELEASE
- io.projectreactor:reactor-core 3.6.8
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-reactor 2.2.0
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-test n/a
- io.projectreactor:reactor-test 3.6.8
- org.postgresql:postgresql n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Modelado del Dominio**: Modelo de datos y reglas de negocio documentadas.
- **Fase 2 — Implementación del Paradigma Reactivo**: Sistema de procesamiento de pagos implementado con paradigma reactivo.
- **Fase 3 — Evaluación y Optimización**: Informe de evaluación y optimización del sistema reactivo.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/fintech/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (68)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/payments/PaymentsApplication.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/domain/port/PaymentRepositoryPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/domain/port/AntifraudServicePort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/domain/port/RiskBureauPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/domain/port/PaymentGatewayPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `reactor.util.function`
      El import reactor.util.function.Tuple3 pertenece a reactor.util.function, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `reactor.util.retry`
      El import reactor.util.retry.RetryBackoffSpec pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.util.retry`
      El import reactor.util.retry.RetryBackoffSpec pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.http`
      El import reactor.netty.http.client.HttpClient pertenece a reactor.netty.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.resources`
      El import reactor.netty.resources.ConnectionProvider pertenece a reactor.netty.resources, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.transactionId`
      Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.amount`
      Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.currency`
      Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.sourceAccount`
      Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.destinationAccount`
      Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.customerId`
      Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.paymentType`
      Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.metadata`
      Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `AntifraudServicePort.evaluateRisk`
      Se invoca `evaluateRisk` sobre `AntifraudServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.status`
      Se invoca `status` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `RiskBureauPort.queryRiskScore`
      Se invoca `queryRiskScore` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.transactionId`
      Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.metadata`
      Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.amount`
      Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.currency`
      Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.sourceAccount`
      Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.destinationAccount`
      Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.customerId`
      Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.paymentType`
      Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.status`
      Se invoca `status` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentStatus.name`
      Se invoca `name` sobre `PaymentStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.transactionId`
      Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.amount`
      Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.currency`
      Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.sourceAccount`
      Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.destinationAccount`
      Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.customerId`
      Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.paymentType`
      Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.metadata`
      Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `PaymentRequest.transactionId`
      Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findById`
      Se invoca `findById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findByCustomerId`
      Se invoca `findByCustomerId` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findByStatus`
      Se invoca `findByStatus` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findAll`
      Se invoca `findAll` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.deleteById`
      Se invoca `deleteById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `AntifraudServicePort.validate`
      Se invoca `validate` sobre `AntifraudServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `RiskBureauPort.assess`
      Se invoca `assess` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `PaymentGatewayPort.process`
      Se invoca `process` sobre `PaymentGatewayPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findById`
      Se invoca `findById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findByCustomerId`
      Se invoca `findByCustomerId` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findByStatus`
      Se invoca `findByStatus` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findAll`
      Se invoca `findAll` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.deleteById`
      Se invoca `deleteById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`
      io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/fintech/payments/PaymentsApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/fintech/payments/domain/model/PaymentRequest.java`
- `src/main/java/com/fintech/payments/domain/model/PaymentStatus.java`
- `src/main/java/com/fintech/payments/domain/port/PaymentRepositoryPort.java`
- `src/main/java/com/fintech/payments/domain/port/AntifraudServicePort.java`
- `src/main/java/com/fintech/payments/domain/port/RiskBureauPort.java`
- `src/main/java/com/fintech/payments/domain/port/PaymentGatewayPort.java`
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java`
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java`
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java`
- `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java`
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java`
- `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java`
- `src/main/java/com/fintech/payments/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java`
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/payments`
- `src/main/java/com/fintech/payments/domain`
- `src/main/java/com/fintech/payments/domain/model`
- `src/main/java/com/fintech/payments/domain/port`
- `src/main/java/com/fintech/payments/application`
- `src/main/java/com/fintech/payments/application/usecase`
- `src/main/java/com/fintech/payments/infrastructure`
- `src/main/java/com/fintech/payments/infrastructure/adapter`
- `src/main/java/com/fintech/payments/infrastructure/config`
- `src/main/java/com/fintech/payments/infrastructure/controller`
- `src/main/resources`
- `src/test/java/com/fintech/payments`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **microservicio reactivo con arquitectura hexagonal**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Implementa un paradigma de programación distinto al imperativo, como el paradigma reactivo o el paradigma funcional. Domina los cuatro pilares especificados en el manifiesto de sistemas reactivos, favoreciendo mejor rendimiento, una mayor escalabilidad y una mayor resiliencia. Conoce las ventajas, desventajas y operadores básicos en la implementación de este paradigma.
- Mision: Candidato Senior con experiencia avanzada en Backend.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
