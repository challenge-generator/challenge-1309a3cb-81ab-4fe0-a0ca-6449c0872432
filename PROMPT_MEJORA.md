# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/fintech/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/payments/PaymentsApplication.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/domain/port/PaymentRepositoryPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/domain/port/AntifraudServicePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/domain/port/RiskBureauPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/domain/port/PaymentGatewayPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `reactor.util.function`: El import reactor.util.function.Tuple3 pertenece a reactor.util.function, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.RetryBackoffSpec pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.RetryBackoffSpec pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.http`: El import reactor.netty.http.client.HttpClient pertenece a reactor.netty.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.resources`: El import reactor.netty.resources.ConnectionProvider pertenece a reactor.netty.resources, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.transactionId`: Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.sourceAccount`: Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.destinationAccount`: Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.customerId`: Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.paymentType`: Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.metadata`: Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `AntifraudServicePort.evaluateRisk`: Se invoca `evaluateRisk` sobre `AntifraudServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `PaymentRequest.status`: Se invoca `status` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java` — `RiskBureauPort.queryRiskScore`: Se invoca `queryRiskScore` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.transactionId`: Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.metadata`: Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.sourceAccount`: Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.destinationAccount`: Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.customerId`: Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.paymentType`: Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentRequest.status`: Se invoca `status` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java` — `PaymentStatus.name`: Se invoca `name` sobre `PaymentStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.transactionId`: Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.sourceAccount`: Se invoca `sourceAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.destinationAccount`: Se invoca `destinationAccount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.customerId`: Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.paymentType`: Se invoca `paymentType` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java` — `PaymentRequest.metadata`: Se invoca `metadata` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `PaymentRequest.transactionId`: Se invoca `transactionId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findById`: Se invoca `findById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findByCustomerId`: Se invoca `findByCustomerId` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findByStatus`: Se invoca `findByStatus` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.findAll`: Se invoca `findAll` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java` — `ProcessPaymentUseCase.deleteById`: Se invoca `deleteById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `AntifraudServicePort.validate`: Se invoca `validate` sobre `AntifraudServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `RiskBureauPort.assess`: Se invoca `assess` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `PaymentGatewayPort.process`: Se invoca `process` sobre `PaymentGatewayPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findById`: Se invoca `findById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findByCustomerId`: Se invoca `findByCustomerId` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findByStatus`: Se invoca `findByStatus` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.findAll`: Se invoca `findAll` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java` — `ProcessPaymentUseCase.deleteById`: Se invoca `deleteById` sobre `ProcessPaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`: io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Implementa un paradigma de programación distinto al imperativo, como el paradigma reactivo o el paradigma funcional. Domina los cuatro pilares especificados en el manifiesto de sistemas reactivos, favoreciendo mejor rendimiento, una mayor escalabilidad y una mayor resiliencia. Conoce las ventajas, desventajas y operadores básicos en la implementación de este paradigma.

### Misión / candidato
Candidato Senior con experiencia avanzada en Backend.

### Reto
- Tema: Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Paradigma Reactivo en Sistema de Procesamiento de Pagos
- Tiempo estimado: 1 semana

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Modelado del Dominio — objetivo: Definir el modelo de datos y las reglas de negocio para el procesamiento de pagos. — entregable (NO resolver): Modelo de datos y reglas de negocio documentadas.
- Fase 2: Implementación del Paradigma Reactivo — objetivo: Implementar el paradigma reactivo en el sistema de procesamiento de pagos. — entregable (NO resolver): Sistema de procesamiento de pagos implementado con paradigma reactivo.
- Fase 3: Evaluación y Optimización — objetivo: Evaluar el rendimiento y la escalabilidad del sistema reactivo y realizar optimizaciones necesarias. — entregable (NO resolver): Informe de evaluación y optimización del sistema reactivo.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.fintech</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de procesamiento de pagos con paradigma reactivo</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <reactor.version>3.6.8</reactor.version>
    </properties>

    <dependencies>
        <!-- Spring WebFlux -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>

        <!-- R2DBC para persistencia reactiva -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <version>1.0.5.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Reactor Core -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
            <version>${reactor.version}</version>
        </dependency>

        <!-- Resilience4j para patrones de resiliencia -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Actuator para métricas -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <version>${reactor.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/fintech/payments/PaymentsApplication.java ===
package com.fintech.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Hooks;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import java.time.Duration;

@SpringBootApplication
public class PaymentsApplication {
    public static void main(String[] args) {
        // Configuración global para debugging reactivo
        Hooks.onOperatorDebug();
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(5)
                .recordExceptions(
                        org.springframework.web.reactive.function.client.WebClientResponseException.class,
                        java.util.concurrent.TimeoutException.class,
                        java.io.IOException.class
                )
                .build();
    }

    @Bean
    public CircuitBreaker circuitBreaker(CircuitBreakerConfig circuitBreakerConfig) {
        return CircuitBreaker.of("paymentServiceCircuitBreaker", circuitBreakerConfig);
    }

    @Bean
    public CircuitBreakerOperator<Object> circuitBreakerOperator(CircuitBreaker circuitBreaker) {
        return CircuitBreakerOperator.of(circuitBreaker);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_password
    pool:
      enabled: true
      initial-size: 10
      max-size: 30
      max-idle-time: 30m

  webflux:
    base-path: /api/payments

server:
  port: 8080
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus,circuitbreakers
  endpoint:
    health:
      show-details: always
    metrics:
      enabled: true

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 1000
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        recordExceptions:
          - org.springframework.web.reactive.function.client.WebClientResponseException
          - java.util.concurrent.TimeoutException
          - java.io.IOException
    instances:
      antifraudService:
        baseConfig: default
      riskBureau:
        baseConfig: default
      paymentGateway:
        baseConfig: default

  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 500
        retryExceptions:
          - org.springframework.web.reactive.function.client.WebClientResponseException
          - java.util.concurrent.TimeoutException
          - java.io.IOException
    instances:
      antifraudService:
        baseConfig: default
      riskBureau:
        baseConfig: default
      paymentGateway:
        baseConfig: default"

// === ARCHIVO: src/main/java/com/fintech/payments/domain/model/PaymentRequest.java ===
package com.fintech.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRequest(
    @NotNull(message = "El ID de la transacción es obligatorio")
    UUID transactionId,

    @NotNull(message = "El ID del cliente es obligatorio")
    UUID customerId,

    @NotNull(message = "El ID del comercio es obligatorio")
    UUID merchantId,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    String currency,

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String description,

    @NotNull(message = "La fecha de creación es obligatoria")
    LocalDateTime createdAt,

    @NotBlank(message = "El método de pago es obligatorio")
    String paymentMethod,

    @NotBlank(message = "El token de pago es obligatorio")
    @Size(min = 16, max = 255, message = "El token de pago debe tener entre 16 y 255 caracteres")
    String paymentToken,

    PaymentStatus status
) {
    public PaymentRequest {
        if (transactionId == null) {
            transactionId = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
    }

    public PaymentRequest withStatus(PaymentStatus newStatus) {
        return new PaymentRequest(
            this.transactionId,
            this.customerId,
            this.merchantId,
            this.amount,
            this.currency,
            this.description,
            this.createdAt,
            this.paymentMethod,
            this.paymentToken,
            newStatus
        );
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/domain/model/PaymentStatus.java ===
package com.fintech.payments.domain.model;

public enum PaymentStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    REJECTED,
    REFUNDED,
    CANCELLED;

    public boolean isTerminal() {
        return this == COMPLETED || this == FAILED || this == REJECTED || this == REFUNDED || this == CANCELLED;
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/domain/port/PaymentRepositoryPort.java ===
package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface PaymentRepositoryPort {
    Mono<PaymentRequest> save(PaymentRequest paymentRequest);

    Mono<PaymentRequest> findById(UUID transactionId);

    Flux<PaymentRequest> findByCustomerId(UUID customerId);

    Flux<PaymentRequest> findByStatus(PaymentStatus status);

    Mono<PaymentRequest> updateStatus(UUID transactionId, PaymentStatus newStatus);

    Mono<Void> deleteById(UUID transactionId);

    Flux<PaymentRequest> findAll();
}

// === ARCHIVO: src/main/java/com/fintech/payments/domain/port/AntifraudServicePort.java ===
package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Puerto para la comunicación con el motor antifraude.
 * Define el contrato que la capa de dominio utiliza para validar
 * transacciones contra el servicio de detección de fraude.
 */
public interface AntifraudServicePort {

    /**
     * Valida una solicitud de pago contra el motor antifraude.
     * @param paymentRequest la solicitud de pago a validar
     * @return Mono que emite true si la transacción pasa la validación antifraude,
     *         o false si es rechazada por riesgo de fraude
     */
    Mono<Boolean> validateTransaction(PaymentRequest paymentRequest);

    /**
     * Consulta el historial de fraude de un cliente específico.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el número de intentos de fraude registrados
     */
    Mono<Integer> getFraudAttemptsCount(UUID customerId);

    /**
     * Reporta un intento de fraude asociado a una transacción.
     * @param transactionId el identificador de la transacción sospechosa
     * @param customerId el identificador del cliente involucrado
     * @param reason la razón por la que se reporta el fraude
     * @return Mono que completa sin error si el reporte fue exitoso
     */
    Mono<Void> reportFraudAttempt(UUID transactionId, UUID customerId, String reason);

    /**
     * Obtiene el nivel de riesgo分数 de un cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite un valor entre 0 y 100 representando el nivel de riesgo
     */
    Mono<Integer> getRiskScore(UUID customerId);

    /**
     * Verifica si un cliente está en la lista de bloqueo por fraude.
     * @param customerId el identificador único del cliente
     * @return Mono que emite true si el cliente está bloqueado, false en caso contrario
     */
    Mono<Boolean> isCustomerBlocked(UUID customerId);
}

// === ARCHIVO: src/main/java/com/fintech/payments/domain/port/RiskBureauPort.java ===
package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto para la comunicación con el buró de riesgos.
 * Proporciona acceso a información crediticia y evaluación
 * de riesgo financiero de los clientes.
 */
public interface RiskBureauPort {

    /**
     * Consulta el puntaje crediticio de un cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el puntaje crediticio (mayor es mejor)
     */
    Mono<Integer> getCreditScore(UUID customerId);

    /**
     * Verifica la capacidad financiera del cliente para un monto específico.
     * @param customerId el identificador único del cliente
     * @param amount el monto a evaluar
     * @return Mono que emite true si el cliente tiene capacidad, false si no
     */
    Mono<Boolean> checkFinancialCapacity(UUID customerId, BigDecimal amount);

    /**
     * Obtiene el historial de pagos del cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el número de pagos exitosos en los últimos 12 meses
     */
    Mono<Integer> getPaymentHistoryCount(UUID customerId);

    /**
     * Consulta deuda activa del cliente en el buró.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el monto total de deuda activa
     */
    Mono<BigDecimal> getActiveDebt(UUID customerId);

    /**
     * Evalúa el riesgo general de una transacción.
     * @param paymentRequest la solicitud de pago a evaluar
     * @return Mono que emite un nivel de riesgo (LOW, MEDIUM, HIGH)
     */
    Mono<RiskLevel> evaluateTransactionRisk(PaymentRequest paymentRequest);

    /**
     * Enum que representa los niveles de riesgo evaluados por el buró.
     */
    enum RiskLevel {
        LOW,
        MEDIUM,
        HIGH
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/domain/port/PaymentGatewayPort.java ===
package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto para la comunicación con el gateway de pagos externo.
 * Abstrae las operaciones de procesamiento real del dinero a través
 * de providers de pago externos (tarjetas, transferencias, etc.).
 */
public interface PaymentGatewayPort {

    /**
     * Procesa un pago a través del gateway.
     * @param paymentRequest la solicitud de pago con los datos de la transacción
     * @return Mono que emite el identificador de autorización del gateway
     */
    Mono<String> processPayment(PaymentRequest paymentRequest);

    /**
     * Cancela una transacción previamente autorizada.
     * @param transactionId el identificador de la transacción a cancelar
     * @return Mono que completa sin error si la cancelación fue exitosa
     */
    Mono<Void> cancelPayment(UUID transactionId);

    /**
     * Consulta el estado de una transacción en el gateway.
     * @param transactionId el identificador de la transacción
     * @return Mono que emite el estado actual de la transacción
     */
    Mono<PaymentGatewayStatus> getPaymentStatus(UUID transactionId);

    /**
     * Reembolsa una transacción completada.
     * @param transactionId el identificador de la transacción a reembolsar
     * @param amount el monto a reembolsar (puede ser parcial)
     * @return Mono que emite el identificador del reembolso
     */
    Mono<String> refundPayment(UUID transactionId, BigDecimal amount);

    /**
     * Verifica la disponibilidad del gateway.
     * @return Mono que emite true si el gateway está disponible, false en caso contrario
     */
    Mono<Boolean> isGatewayAvailable();

    /**
     * Enum que representa los estados posibles de una transacción en el gateway.
     */
    enum PaymentGatewayStatus {
        PENDING,
        AUTHORIZED,
        CAPTURED,
        DECLINED,
        CANCELLED,
        REFUNDED,
        FAILED
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/application/usecase/ProcessPaymentUseCase.java ===
package com.fintech.payments.application.usecase;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.port.RiskBureauPort;
import com.fintech.payments.domain.port.PaymentGatewayPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;
import java.util.UUID;

@Service
public class ProcessPaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessPaymentUseCase.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 100L;

    private final PaymentRepositoryPort paymentRepository;
    private final AntifraudServicePort antifraudService;
    private final RiskBureauPort riskBureau;
    private final PaymentGatewayPort paymentGateway;

    public ProcessPaymentUseCase(
            PaymentRepositoryPort paymentRepository,
            AntifraudServicePort antifraudService,
            RiskBureauPort riskBureau,
            PaymentGatewayPort paymentGateway) {
        this.paymentRepository = paymentRepository;
        this.antifraudService = antifraudService;
        this.riskBureau = riskBureau;
        this.paymentGateway = paymentGateway;
    }

    public Mono<PaymentRequest> execute(PaymentRequest paymentRequest) {
        log.info("Iniciando procesamiento de pago para transactionId: {}", paymentRequest.transactionId());
        
        return validateAndEnrichPayment(paymentRequest)
            .flatMap(this::performAntifraudCheck)
            .flatMap(this::performRiskBureauCheck)
            .flatMap(this::executePaymentGateway)
            .flatMap(this::persistPaymentWithStatus)
            .doOnSuccess(result -> log.info("Pago procesado exitosamente: {}", result.transactionId()))
            .doOnError(error -> log.error("Error en procesamiento de pago: {}", error.getMessage()));
    }

    private Mono<PaymentRequest> validateAndEnrichPayment(PaymentRequest paymentRequest) {
        return Mono.just(paymentRequest)
            .filter(req -> req.transactionId() != null)
            .switchIfEmpty(Mono.defer(() -> {
                PaymentRequest enriched = new PaymentRequest(
                    UUID.randomUUID(),
                    paymentRequest.amount(),
                    paymentRequest.currency(),
                    paymentRequest.sourceAccount(),
                    paymentRequest.destinationAccount(),
                    paymentRequest.customerId(),
                    paymentRequest.paymentType(),
                    PaymentStatus.PENDING,
                    paymentRequest.metadata()
                );
                return Mono.just(enriched);
            }))
            .filter(req -> req.amount() != null && req.amount().compareTo(java.math.BigDecimal.ZERO) > 0)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Monto inválido para la transacción")));
    }

    private Mono<PaymentRequest> performAntifraudCheck(PaymentRequest paymentRequest) {
        log.debug("Ejecutando verificación antifraude para: {}", paymentRequest.transactionId());
        
        return antifraudService.evaluateRisk(paymentRequest)
            .flatMap(antifraudResult -> {
                if (antifraudResult.isApproved()) {
                    log.info("Verificación antifraude aprobada para: {}", paymentRequest.transactionId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_APPROVED));
                } else {
                    log.warn("Verificación antifraude rechazada para: {}. Razón: {}", 
                        paymentRequest.transactionId(), antifraudResult.rejectionReason());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en servicio antifraude: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_FAILED));
            });
    }

    private Mono<PaymentRequest> performRiskBureauCheck(PaymentRequest paymentRequest) {
        log.debug("Consultando buró de riesgos para: {}", paymentRequest.transactionId());
        
        if (paymentRequest.status() == PaymentStatus.ANTIFRAUD_REJECTED) {
            return Mono.just(paymentRequest);
        }
        
        return riskBureau.queryRiskScore(paymentRequest.customerId())
            .flatMap(riskScore -> {
                if (riskScore.getScore() <= riskScore.getThreshold()) {
                    log.info("Buró de riesgos aprobado para cliente: {}", paymentRequest.customerId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_APPROVED));
                } else {
                    log.warn("Buró de riesgos rechazado para cliente: {}. Score: {}", 
                        paymentRequest.customerId(), riskScore.getScore());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en buró de riesgos: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_FAILED));
            });
    }

    private Mono<PaymentRequest> executePaymentGateway(PaymentRequest paymentRequest) {
        log.debug("Ejecutando gateway de pagos para: {}", paymentRequest.transactionId());
        
        PaymentStatus currentStatus = paymentRequest.status();
        if (currentStatus == PaymentStatus.ANTIFRAUD_REJECTED || 
            currentStatus == PaymentStatus.RISK_BUREAU_REJECTED) {
            return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_SKIPPED));
        }
        
        return paymentGateway.processPayment(paymentRequest)
            .flatMap(gatewayResponse -> {
                if (gatewayResponse.isSuccessful()) {
                    log.info("Gateway de pagos aprobó transacción: {}", paymentRequest.transactionId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_APPROVED));
                } else {
                    log.warn("Gateway de pagos rechazado para: {}. Código: {}", 
                        paymentRequest.transactionId(), gatewayResponse.getErrorCode());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en gateway de pagos: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_FAILED));
            });
    }

    private Mono<PaymentRequest> persistPaymentWithStatus(PaymentRequest paymentRequest) {
        log.debug("Persistiendo estado final del pago: {} con estado: {}", 
            paymentRequest.transactionId(), paymentRequest.status());
        
        return paymentRepository.save(paymentRequest)
            .flatMap(saved -> {
                if (saved.status().isTerminal()) {
                    log.info("Transacción {} completada con estado terminal: {}", 
                        saved.transactionId(), saved.status());
                    return Mono.just(saved);
                } else {
                    log.warn("Transacción {} finalizada con estado no terminal: {}", 
                        saved.transactionId(), saved.status());
                    return Mono.just(saved);
                }
            });
    }

    public Mono<PaymentRequest> getPaymentById(UUID transactionId) {
        log.info("Consultando pago por ID: {}", transactionId);
        return paymentRepository.findById(transactionId)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Pago no encontrado: " + transactionId)));
    }

    public Flux<PaymentRequest> getPaymentsByCustomer(UUID customerId) {
        log.info("Consultando pagos para cliente: {}", customerId);
        return paymentRepository.findByCustomerId(customerId);
    }

    public Flux<PaymentRequest> getPaymentsByStatus(PaymentStatus status) {
        log.info("Consultando pagos con estado: {}", status);
        return paymentRepository.findByStatus(status);
    }

    public Mono<PaymentRequest> updatePaymentStatus(UUID transactionId, PaymentStatus newStatus) {
        log.info("Actualizando estado de pago {} a {}", transactionId, newStatus);
        return paymentRepository.updateStatus(transactionId, newStatus)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Pago no encontrado para actualizar: " + transactionId)));
    }

    public Mono<Void> cancelPayment(UUID transactionId) {
        log.info("Cancelando pago: {}", transactionId);
        return paymentRepository.findById(transactionId)
            .flatMap(payment -> {
                if (payment.status().isTerminal()) {
                    return Mono.error(new IllegalStateException("No se puede cancelar un pago en estado terminal: " + payment.status()));
                }
                return paymentRepository.updateStatus(transactionId, PaymentStatus.CANCELLED);
            })
            .then();
    }

    public Flux<PaymentRequest> getAllPayments() {
        log.info("Consultando todos los pagos");
        return paymentRepository.findAll();
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/adapter/PaymentRepositoryAdapter.java ===
package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import io.r2dbc.postgresql.codec.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentRepositoryAdapter.class);
    private static final String TABLE_NAME = "payments";

    private final DatabaseClient databaseClient;

    public PaymentRepositoryAdapter(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
        initializeTable();
    }

    private void initializeTable() {
        databaseClient.sql("""
            CREATE TABLE IF NOT EXISTS """ + TABLE_NAME + """ (
                transaction_id UUID PRIMARY KEY,
                amount DECIMAL(19,4) NOT NULL,
                currency VARCHAR(3) NOT NULL,
                source_account VARCHAR(50) NOT NULL,
                destination_account VARCHAR(50) NOT NULL,
                customer_id UUID NOT NULL,
                payment_type VARCHAR(20) NOT NULL,
                status VARCHAR(30) NOT NULL,
                metadata JSONB,
                created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
                updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
            )
            """)
            .fetch()
            .rowsUpdated()
            .subscribe(
                rows -> log.debug("Tabla {} verificada/creada", TABLE_NAME),
                error -> log.error("Error al inicializar tabla: {}", error.getMessage())
            );
    }

    @Override
    public Mono<PaymentRequest> save(PaymentRequest paymentRequest) {
        log.debug("Guardando pago: {}", paymentRequest.transactionId());
        
        String metadataJson = paymentRequest.metadata() != null 
            ? new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(paymentRequest.metadata())
            : null;
        
        return databaseClient.sql("""
            INSERT INTO """ + TABLE_NAME + """
            (transaction_id, amount, currency, source_account, destination_account, 
             customer_id, payment_type, status, metadata, created_at, updated_at)
            VALUES (:transactionId, :amount, :currency, :sourceAccount, :destinationAccount,
                    :customerId, :paymentType, :status, :metadata, :createdAt, :updatedAt)
            ON CONFLICT (transaction_id) DO UPDATE SET
                status = EXCLUDED.status,
                metadata = EXCLUDED.metadata,
                updated_at = EXCLUDED.updated_at
            RETURNING *
            """)
            .bind("transactionId", paymentRequest.transactionId())
            .bind("amount", paymentRequest.amount().doubleValue())
            .bind("currency", paymentRequest.currency())
            .bind("sourceAccount", paymentRequest.sourceAccount())
            .bind("destinationAccount", paymentRequest.destinationAccount())
            .bind("customerId", paymentRequest.customerId())
            .bind("paymentType", paymentRequest.paymentType())
            .bind("status", paymentRequest.status().name())
            .bind("metadata", metadataJson != null ? Json.of(metadataJson) : null)
            .bind("createdAt", Instant.now())
            .bind("updatedAt", Instant.now())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(saved -> log.info("Pago guardado exitosamente: {}", saved.transactionId()))
            .doOnError(error -> log.error("Error al guardar pago: {}", error.getMessage()));
    }

    @Override
    public Mono<PaymentRequest> findById(UUID transactionId) {
        log.debug("Buscando pago por ID: {}", transactionId);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE transaction_id = :transactionId")
            .bind("transactionId", transactionId)
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(result -> log.debug("Pago encontrado: {}", transactionId))
            .doOnError(error -> log.error("Error al buscar pago por ID: {}", error.getMessage()));
    }

    @Override
    public Flux<PaymentRequest> findByCustomerId(UUID customerId) {
        log.debug("Buscando pagos para cliente: {}", customerId);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE customer_id = :customerId ORDER BY created_at DESC")
            .bind("customerId", customerId)
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Búsqueda de pagos por cliente completada: {}", customerId));
    }

    @Override
    public Flux<PaymentRequest> findByStatus(PaymentStatus status) {
        log.debug("Buscando pagos con estado: {}", status);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE status = :status ORDER BY created_at DESC")
            .bind("status", status.name())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Búsqueda de pagos por estado completada: {}", status));
    }

    @Override
    public Mono<PaymentRequest> updateStatus(UUID transactionId, PaymentStatus newStatus) {
        log.debug("Actualizando estado de {} a {}", transactionId, newStatus);
        
        return databaseClient.sql("""
            UPDATE """ + TABLE_NAME + """
            SET status = :newStatus, updated_at = :updatedAt
            WHERE transaction_id = :transactionId
            RETURNING *
            """)
            .bind("transactionId", transactionId)
            .bind("newStatus", newStatus.name())
            .bind("updatedAt", Instant.now())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(updated -> log.info("Estado actualizado a {} para {}", newStatus, transactionId))
            .doOnError(error -> log.error("Error al actualizar estado: {}", error.getMessage()));
    }

    @Override
    public Mono<Void> deleteById(UUID transactionId) {
        log.debug("Eliminando pago: {}", transactionId);
        
        return databaseClient.sql("DELETE FROM " + TABLE_NAME + " WHERE transaction_id = :transactionId")
            .bind("transactionId", transactionId)
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> rows > 0 ? Mono.empty() : Mono.error(
                new IllegalArgumentException("Pago no encontrado para eliminación: " + transactionId)))
            .then()
            .doOnSuccess(v -> log.info("Pago eliminado: {}", transactionId))
            .doOnError(error -> log.error("Error al eliminar pago: {}", error.getMessage()));
    }

    @Override
    public Flux<PaymentRequest> findAll() {
        log.debug("Consultando todos los pagos");
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " ORDER BY created_at DESC")
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Consulta de todos los pagos completada"));
    }

    private PaymentRequest mapRowToPaymentRequest(org.springframework.r2dbc.core.Row row) {
        UUID transactionId = row.get("transaction_id", UUID.class);
        BigDecimal amount = BigDecimal.valueOf(row.get("amount", Double.class));
        String currency = row.get("currency", String.class);
        String sourceAccount = row.get("source_account", String.class);
        String destinationAccount = row.get("destination_account", String.class);
        UUID customerId = row.get("customer_id", UUID.class);
        String paymentType = row.get("payment_type", String.class);
        String statusStr = row.get("status", String.class);
        PaymentStatus status = PaymentStatus.valueOf(statusStr);
        
        Map<String, Object> metadata = null;
        Json metadataJson = row.get("metadata", Json.class);
        if (metadataJson != null) {
            try {
                metadata = new com.fasterxml.jackson.databind.ObjectMapper().readValue(
                    metadataJson.asString(), Map.class);
            } catch (Exception e) {
                log.warn("Error al deserializar metadata para {}: {}", transactionId, e.getMessage());
            }
        }
        
        return new PaymentRequest(
            transactionId,
            amount,
            currency,
            sourceAccount,
            destinationAccount,
            customerId,
            paymentType,
            status,
            metadata
        );
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/adapter/AntifraudServiceAdapter.java ===
package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.model.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

@Component
public class AntifraudServiceAdapter implements AntifraudServicePort {

    private static final Logger log = LoggerFactory.getLogger(AntifraudServiceAdapter.class);
    private static final String SERVICE_NAME = "antifraud-service";
    private static final String ENDPOINT_PATH = "/api/v1/antifraud/evaluate";
    private static final Duration TIMEOUT = Duration.ofMillis(500);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final WebClient webClient;

    public AntifraudServiceAdapter(@Qualifier("antifraudWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "evaluateRiskFallback")
    @Retry(name = SERVICE_NAME)
    public Mono<AntifraudResult> evaluateRisk(PaymentRequest paymentRequest) {
        log.debug("Evaluando riesgo antifraude para transacción: {}", paymentRequest.transactionId());
        
        Map<String, Object> requestBody = buildRequestBody(paymentRequest);
        
        return webClient.post()
            .uri(ENDPOINT_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(AntifraudResult.class)
            .timeout(TIMEOUT)
            .doOnSuccess(result -> log.info("Evaluación antifraude completada para {}: approved={}", 
                paymentRequest.transactionId(), result.isApproved()))
            .doOnError(error -> log.error("Error en evaluación antifraude para {}: {}", 
                paymentRequest.transactionId(), error.getMessage()));
    }

    private Mono<AntifraudResult> evaluateRiskFallback(PaymentRequest paymentRequest, Throwable error) {
        log.warn("Fallback de antifraude activado para {}: {}", paymentRequest.transactionId(), error.getMessage());
        
        if (error instanceof java.util.concurrent.TimeoutException) {
            return Mono.just(new AntifraudResult(true, "TIMEOUT_FALLBACK", "Timeout en servicio antifraude -approved por defecto"));
        }
        if (error instanceof org.springframework.web.reactive.function.client.WebClientResponseException) {
            org.springframework.web.reactive.function.client.WebClientResponseException wcre = 
                (org.springframework.web.reactive.function.client.WebClientResponseException) error;
            if (wcre.getStatusCode().is5xxServerError()) {
                return Mono.just(new AntifraudResult(true, "SERVER_ERROR_FALLBACK", "Error 5xx en antifraude - aprobado por defecto"));
            }
        }
        return Mono.just(new AntifraudResult(true, "UNKNOWN_ERROR_FALLBACK", "Error desconocido en antifraude - aprobado por defecto"));
    }

    private Map<String, Object> buildRequestBody(PaymentRequest paymentRequest) {
        Map<String, Object> body = new HashMap<>();
        body.put("transactionId", paymentRequest.transactionId().toString());
        body.put("amount", paymentRequest.amount());
        body.put("currency", paymentRequest.currency());
        body.put("sourceAccount", paymentRequest.sourceAccount());
        body.put("destinationAccount", paymentRequest.destinationAccount());
        body.put("customerId", paymentRequest.customerId().toString());
        body.put("paymentType", paymentRequest.paymentType());
        
        if (paymentRequest.metadata() != null) {
            body.put("metadata", paymentRequest.metadata());
        }
        return body;
    }

    public static class AntifraudResult {
        private final boolean approved;
        private final String rejectionReason;
        private final String riskScore;

        public AntifraudResult(boolean approved, String rejectionReason, String riskScore) {
            this.approved = approved;
            this.rejectionReason = rejectionReason;
            this.riskScore = riskScore;
        }

        public boolean isApproved() {
            return approved;
        }

        public String getRejectionReason() {
            return rejectionReason;
        }

        public String getRiskScore() {
            return riskScore;
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/adapter/RiskBureauAdapter.java ===
package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.RiskBureauPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "riskBureau";
    private static final String RETRY_NAME = "riskBureauRetry";

    private final WebClient webClient;
    private final String bureauBaseUrl;
    private final int timeoutSeconds;

    public RiskBureauAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.risk-bureau.base-url:http://localhost:8083}") String bureauBaseUrl,
            @Value("${external-services.risk-bureau.timeout-seconds:5}") int timeoutSeconds) {
        this.webClient = webClientBuilder.baseUrl(bureauBaseUrl).build();
        this.bureauBaseUrl = bureauBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackRiskAssessment")
    @Retry(name = RETRY_NAME)
    public Mono<RiskAssessmentResult> assessRisk(UUID customerId, BigDecimal amount) {
        log.info("Consultando buró de riesgos para cliente {} por monto {}", customerId, amount);
        
        Map<String, Object> requestBody = Map.of(
            "customerId", customerId.toString(),
            "requestedAmount", amount.doubleValue(),
            "currency", "USD",
            "timestamp", java.time.Instant.now().toString()
        );

        return webClient.post()
            .uri("/api/v1/risk/assess")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(RiskAssessmentResult.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(result -> log.info("Evaluación de riesgo completada: score={}, riskLevel={}", 
                result.score(), result.riskLevel()))
            .doOnError(error -> log.error("Error en evaluación de riesgo para cliente {}: {}", 
                customerId, error.getMessage()));
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackCreditLimit")
    @Retry(name = RETRY_NAME)
    public Mono<CreditLimitResult> getCreditLimit(UUID customerId) {
        log.info("Consultando límite de crédito para cliente {}", customerId);

        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v1/risk/credit-limit")
                .queryParam("customerId", customerId.toString())
                .build())
            .retrieve()
            .bodyToMono(CreditLimitResult.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(result -> log.info("Límite de crédito obtenido: approved={}, limit={}", 
                result.approved(), result.limit()))
            .doOnError(error -> log.error("Error consultando límite de crédito para cliente {}: {}", 
                customerId, error.getMessage()));
    }

    private Mono<RiskAssessmentResult> fallbackRiskAssessment(UUID customerId, BigDecimal amount, Throwable throwable) {
        log.warn("Fallback activado para assessRisk. Cliente: {}, causa: {}", customerId, throwable.getMessage());
        return Mono.just(new RiskAssessmentResult(
            0,
            "HIGH",
            "FALLBACK_ACTIVE",
            "No se pudo obtener evaluación de riesgo del buró",
            java.time.Instant.now()
        ));
    }

    private Mono<CreditLimitResult> fallbackCreditLimit(UUID customerId, Throwable throwable) {
        log.warn("Fallback activado para getCreditLimit. Cliente: {}, causa: {}", customerId, throwable.getMessage());
        return Mono.just(new CreditLimitResult(
            customerId,
            false,
            BigDecimal.ZERO,
            "FALLBACK_ACTIVE",
            java.time.Instant.now()
        ));
    }

    public record RiskAssessmentResult(
        int score,
        String riskLevel,
        String status,
        String message,
        java.time.Instant evaluatedAt
    ) {}

    public record CreditLimitResult(
        UUID customerId,
        boolean approved,
        BigDecimal limit,
        String status,
        java.time.Instant evaluatedAt
    ) {}
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/adapter/PaymentGatewayAdapter.java ===
package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.PaymentGatewayPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetryBackoffSpec;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentGatewayAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "paymentGateway";
    private static final String RETRY_NAME = "paymentGatewayRetry";

    private final WebClient webClient;
    private final String gatewayBaseUrl;
    private final int timeoutSeconds;
    private final boolean simulationMode;

    public PaymentGatewayAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.payment-gateway.base-url:http://localhost:8084}") String gatewayBaseUrl,
            @Value("${external-services.payment-gateway.timeout-seconds:10}") int timeoutSeconds,
            @Value("${external-services.payment-gateway.simulation-mode:false}") boolean simulationMode) {
        this.webClient = webClientBuilder.baseUrl(gatewayBaseUrl).build();
        this.gatewayBaseUrl = gatewayBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
        this.simulationMode = simulationMode;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackProcessPayment")
    @Retry(name = RETRY_NAME)
    public Mono<PaymentGatewayResponse> processPayment(PaymentGatewayRequest request) {
        log.info("Procesando pago a través del gateway. TransactionId: {}, Amount: {}", 
            request.transactionId(), request.amount());

        if (simulationMode) {
            return simulateGatewayResponse(request);
        }

        Map<String, Object> gatewayRequestBody = Map.of(
            "transactionId", request.transactionId().toString(),
            "customerId", request.customerId().toString(),
            "amount", request.amount().doubleValue(),
            "currency", request.currency(),
            "paymentMethod", request.paymentMethod(),
            "merchantId", request.merchantId(),
            "description", request.description() != null ? request.description() : "",
            "metadata", request.metadata() != null ? request.metadata() : Map.of()
        );

        return webClient.post()
            .uri("/api/v1/payments/process")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(gatewayRequestBody)
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.info("Pago procesado exitosamente. TransactionId: {}, Status: {}", 
                response.transactionId(), response.status()))
            .doOnError(error -> log.error("Error procesando pago. TransactionId: {}, Error: {}", 
                request.transactionId(), error.getMessage()))
            .onErrorResume(error -> handleGatewayError(request, error));
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackRefundPayment")
    @Retry(name = RETRY_NAME)
    public Mono<PaymentGatewayResponse> refundPayment(UUID transactionId, BigDecimal amount, String reason) {
        log.info("Procesando reembolso. TransactionId: {}, Amount: {}, Reason: {}", 
            transactionId, amount, reason);

        if (simulationMode) {
            return simulateRefundResponse(transactionId, amount);
        }

        Map<String, Object> refundRequestBody = Map.of(
            "originalTransactionId", transactionId.toString(),
            "refundAmount", amount.doubleValue(),
            "reason", reason != null ? reason : "Customer request",
            "timestamp", java.time.Instant.now().toString()
        );

        return webClient.post()
            .uri("/api/v1/payments/refund")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(refundRequestBody)
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.info("Reembolso procesado. TransactionId: {}, Status: {}", 
                response.transactionId(), response.status()))
            .doOnError(error -> log.error("Error en reembolso. TransactionId: {}, Error: {}", 
                transactionId, error.getMessage()));
    }

    @Override
    public Mono<PaymentGatewayResponse> getPaymentStatus(UUID transactionId) {
        log.info("Consultando estado del pago. TransactionId: {}", transactionId);

        if (simulationMode) {
            return simulateStatusCheck(transactionId);
        }

        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v1/payments/status")
                .queryParam("transactionId", transactionId.toString())
                .build())
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.debug("Estado del pago: {}", response.status()))
            .doOnError(error -> log.error("Error consultando estado: {}", error.getMessage()));
    }

    private Mono<PaymentGatewayResponse> handleGatewayError(PaymentGatewayRequest request, Throwable error) {
        if (error instanceof org.springframework.web.reactive.function.client.WebClientResponseException) {
            WebClientResponseException wcre = (WebClientResponseException) error;
            log.error("Error HTTP del gateway: {} - {}", wcre.getStatusCode(), wcre.getResponseBodyAsString());
            return Mono.just(new PaymentGatewayResponse(
                request.transactionId(),
                "FAILED",
                "GATEWAY_ERROR: " + wcre.getStatusCode(),
                wcre.getResponseBodyAsString(),
                java.time.Instant.now()
            ));
        }
        return Mono.error(error);
    }

    private Mono<PaymentGatewayResponse> simulateGatewayResponse(PaymentGatewayRequest request) {
        log.debug("Modo simulación activado para processPayment");
        return Mono.delay(Duration.ofMillis(500))
            .map(tick -> new PaymentGatewayResponse(
                request.transactionId(),
                "APPROVED",
                "SIMULATION_SUCCESS",
                "Pago aprobado en modo simulación",
                java.time.Instant.now()
            ));
    }

    private Mono<PaymentGatewayResponse> simulateRefundResponse(UUID transactionId, BigDecimal amount) {
        log.debug("Modo simulación activado para refundPayment");
        return Mono.delay(Duration.ofMillis(300))
            .map(tick -> new PaymentGatewayResponse(
                transactionId,
                "REFUNDED",
                "SIMULATION_SUCCESS",
                "Reembolso procesado en modo simulación",
                java.time.Instant.now()
            ));
    }

    private Mono<PaymentGatewayResponse> simulateStatusCheck(UUID transactionId) {
        log.debug("Modo simulación activado para getPaymentStatus");
        return Mono.just(new PaymentGatewayResponse(
            transactionId,
            "PENDING",
            "SIMULATION",
            "Estado simulado",
            java.time.Instant.now()
        ));
    }

    private Mono<PaymentGatewayResponse> fallbackProcessPayment(PaymentGatewayRequest request, Throwable throwable) {
        log.warn("Fallback activado para processPayment. TransactionId: {}, causa: {}", 
            request.transactionId(), throwable.getMessage());
        return Mono.just(new PaymentGatewayResponse(
            request.transactionId(),
            "PENDING",
            "CIRCUIT_OPEN",
            "Gateway no disponible, pago en cola para retry",
            java.time.Instant.now()
        ));
    }

    private Mono<PaymentGatewayResponse> fallbackRefundPayment(UUID transactionId, BigDecimal amount, 
                                                                 String reason, Throwable throwable) {
        log.warn("Fallback activado para refundPayment. TransactionId: {}, causa: {}", 
            transactionId, throwable.getMessage());
        return Mono.just(new PaymentGatewayResponse(
            transactionId,
            "PENDING",
            "CIRCUIT_OPEN",
            "Gateway no disponible para reembolso",
            java.time.Instant.now()
        ));
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/config/WebClientConfig.java ===
package com.fintech.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${webclient.connect-timeout-ms:5000}")
    private int connectTimeoutMs;

    @Value("${webclient.response-timeout-ms:15000}")
    private int responseTimeoutMs;

    @Value("${webclient.pool.max-connections:100}")
    private int maxConnections;

    @Value("${webclient.pool.pending-queue-size:200}")
    private int pendingQueueSize;

    @Value("${webclient.buffer-size-bytes:262144}")
    private int bufferSizeBytes;

    @Bean
    public ConnectionProvider connectionProvider() {
        log.info("Configurando ConnectionProvider con maxConnections={}, pendingQueueSize={}", 
            maxConnections, pendingQueueSize);

        return ConnectionProvider.builder("payment-service-pool")
            .maxConnections(maxConnections)
            .pendingAcquireMaxCount(pendingQueueSize)
            .pendingAcquireTimeout(Duration.ofMillis(responseTimeoutMs))
            .maxIdleTime(Duration.ofMillis(connectTimeoutMs))
            .maxLifeTime(Duration.ofMinutes(5))
            .evictInBackground(Duration.ofSeconds(30))
            .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        log.info("Configurando HttpClient con connectTimeout={}ms, responseTimeout={}ms", 
            connectTimeoutMs, responseTimeoutMs);

        return HttpClient.create(connectionProvider)
            .responseTimeout(Duration.ofMillis(responseTimeoutMs))
            .compress(true)
            .keepAlive(true)
            .doOnConnected(conn -> conn
                .addHandlerLast(new reactor.netty.handler.logging.LoggingHandler("reactor.netty"))
            );
    }

    @Bean
    public WebClient.Builder webClientBuilder(HttpClient httpClient) {
        log.info("Configurando WebClient con bufferSize={} bytes", bufferSizeBytes);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(bufferSizeBytes))
            .build();

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(exchangeStrategies)
            .defaultHeader("Accept", "application/json")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("X-Service-Origin", "payment-service")
            .filter((request, next) -> {
                log.debug("WebClient request: {} {}", request.method(), request.url());
                return next.exchange(request)
                    .doOnSuccess(response -> 
                        log.debug("WebClient response: {} {}", response.statusCode(), request.url()))
                    .doOnError(error -> 
                        log.error("WebClient error: {} - {}", request.url(), error.getMessage()));
            })
            .filter((request, next) -> 
                next.exchange(request)
                    .onErrorResume(org.springframework.web.reactive.function.client.WebClientResponseException.class,
                        error -> {
                            log.error("Error en respuesta HTTP: {} {}", error.getStatusCode(), error.getMessage());
                            return reactor.core.publisher.Mono.error(error);
                        })
            );
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        log.info("Configurando CircuitBreakerRegistry con configuración por defecto");

        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .slowCallRateThreshold(80)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slowCallDurationThreshold(Duration.ofSeconds(5))
            .permittedNumberOfCallsInHalfOpenState(10)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5)
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
            .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(defaultConfig);

        registry.getEventPublisher()
            .onEvent(event -> log.info("CircuitBreaker event: {} - {}", 
                event.getCircuitBreakerName(), event.getEventType()));

        return registry;
    }

    @Bean
    public RetryRegistry retryRegistry() {
        log.info("Configurando RetryRegistry con configuración por defecto");

        RetryConfig defaultConfig = RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(500))
            .retryExceptions(
                org.springframework.web.reactive.function.client.WebClientRequestException.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable.class
            )
            .ignoreExceptions(
                org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.Unauthorized.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.Forbidden.class
            )
            .build();

        RetryRegistry registry = RetryRegistry.of(defaultConfig);

        registry.getEventPublisher()
            .onEvent(event -> log.info("Retry event: {} - attempt {}", 
                event.getRetryName(), event.getAttemptNumber()));

        return registry;
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/config/Resilience4jConfig.java ===
package com.fintech.payments.infrastructure.config;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerConfig paymentCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
    }

    @Bean
    public RetryConfig paymentRetryConfig() {
        return RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(Exception.class)
                .build();
    }

    @Bean
    public BulkheadConfig paymentBulkheadConfig() {
        return BulkheadConfig.custom()
                .maxConcurrentCalls(100)
                .maxWaitDuration(Duration.ofMillis(200))
                .build();
    }
}

// === ARCHIVO: src/main/java/com/fintech/payments/infrastructure/controller/PaymentController.java ===
package com.fintech.payments.infrastructure.controller;

import com.fintech.payments.application.usecase.ProcessPaymentUseCase;
import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentRequest>> processPayment(@RequestBody PaymentRequest request) {
        log.info("Received payment request for transaction: {}", request.transactionId());
        return processPaymentUseCase.execute(request)
                .map(payment -> ResponseEntity.status(HttpStatus.CREATED).body(payment))
                .onErrorResume(error -> {
                    log.error("Error processing payment: {}", error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/{transactionId}")
    public Mono<ResponseEntity<PaymentRequest>> getPayment(@PathVariable UUID transactionId) {
        log.info("Fetching payment for transaction: {}", transactionId);
        return processPaymentUseCase.findById(transactionId)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public Flux<PaymentRequest> getPaymentsByCustomer(@PathVariable UUID customerId) {
        log.info("Fetching payments for customer: {}", customerId);
        return processPaymentUseCase.findByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public Flux<PaymentRequest> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        log.info("Fetching payments with status: {}", status);
        return processPaymentUseCase.findByStatus(status);
    }

    @GetMapping
    public Flux<PaymentRequest> getAllPayments() {
        log.info("Fetching all payments");
        return processPaymentUseCase.findAll();
    }

    @DeleteMapping("/{transactionId}")
    public Mono<ResponseEntity<Void>> deletePayment(@PathVariable UUID transactionId) {
        log.info("Deleting payment for transaction: {}", transactionId);
        return processPaymentUseCase.deleteById(transactionId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(error -> {
                    log.error("Error deleting payment: {}", error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}

// === ARCHIVO: src/test/java/com/fintech/payments/application/usecase/ProcessPaymentUseCaseTest.java ===
package com.fintech.payments.application.usecase;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import com.fintech.payments.domain.port.PaymentGatewayPort;
import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.port.RiskBureauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessPaymentUseCase Tests")
class ProcessPaymentUseCaseTest {

    @Mock
    private PaymentRepositoryPort paymentRepositoryPort;

    @Mock
    private PaymentGatewayPort paymentGatewayPort;

    @Mock
    private AntifraudServicePort antifraudServicePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    private ProcessPaymentUseCase processPaymentUseCase;

    @BeforeEach
    void setUp() {
        processPaymentUseCase = new ProcessPaymentUseCase(
                paymentRepositoryPort,
                paymentGatewayPort,
                antifraudServicePort,
                riskBureauPort
        );
    }

    @Test
    @DisplayName("Should process payment successfully when all validations pass")
    void shouldProcessPaymentSuccessfully() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest approvedRequest = savedRequest.withStatus(PaymentStatus.APPROVED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(riskBureauPort.assess(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(paymentGatewayPort.process(any(PaymentRequest.class))).thenReturn(Mono.just(approvedRequest));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(approvedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.APPROVED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should reject payment when antifraud validation fails")
    void shouldRejectPaymentWhenAntifraudFails() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest rejectedRequest = savedRequest.withStatus(PaymentStatus.REJECTED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(false));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.REJECTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should reject payment when risk bureau assessment fails")
    void shouldRejectPaymentWhenRiskBureauFails() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest rejectedRequest = savedRequest.withStatus(PaymentStatus.REJECTED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(riskBureauPort.assess(any(PaymentRequest.class))).thenReturn(Mono.just(false));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.REJECTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payment when found by transaction ID")
    void shouldReturnPaymentWhenFoundById() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findById(transactionId)).thenReturn(Mono.just(request));

        StepVerifier.create(processPaymentUseCase.findById(transactionId))
                .expectNext(request)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return empty when payment not found by transaction ID")
    void shouldReturnEmptyWhenPaymentNotFound() {
        UUID transactionId = UUID.randomUUID();

        when(paymentRepositoryPort.findById(transactionId)).thenReturn(Mono.empty());

        StepVerifier.create(processPaymentUseCase.findById(transactionId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payments when found by customer ID")
    void shouldReturnPaymentsWhenFoundByCustomerId() {
        UUID customerId = UUID.randomUUID();
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();

        PaymentRequest request1 = new PaymentRequest(
                transactionId1,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        PaymentRequest request2 = new PaymentRequest(
                transactionId2,
                customerId,
                new BigDecimal("200.00"),
                "USD",
                PaymentStatus.PROCESSING,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findByCustomerId(customerId))
                .thenReturn(Flux.just(request1, request2));

        StepVerifier.create(processPaymentUseCase.findByCustomerId(customerId))
                .expectNext(request1)
                .expectNext(request2)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payments when found by status")
    void shouldReturnPaymentsWhenFoundByStatus() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findByStatus(PaymentStatus.APPROVED))
                .thenReturn(Flux.just(request));

        StepVerifier.create(processPaymentUseCase.findByStatus(PaymentStatus.APPROVED))
                .expectNext(request)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return all payments")
    void shouldReturnAllPayments() {
        UUID customerId = UUID.randomUUID();
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();

        PaymentRequest request1 = new PaymentRequest(
                transactionId1,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        PaymentRequest request2 = new PaymentRequest(
                transactionId2,
                customerId,
                new BigDecimal("200.00"),
                "USD",
                PaymentStatus.REJECTED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findAll())
                .thenReturn(Flux.just(request1, request2));

        StepVerifier.create(processPaymentUseCase.findAll())
                .expectNext(request1)
                .expectNext(request2)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should delete payment successfully")
    void shouldDeletePaymentSuccessfully() {
        UUID transactionId = UUID.randomUUID();

        when(paymentRepositoryPort.deleteById(transactionId)).thenReturn(Mono.empty());

        StepVerifier.create(processPaymentUseCase.deleteById(transactionId))
                .verifyComplete();
    }
}

// === ARCHIVO: README.md ===
# Sistema de Procesamiento de Pagos - Payments Service

## Descripción del Proyecto

Este proyecto implementa un sistema de procesamiento de pagos con paradigma reactivo para una fintech. El sistema está diseñado para manejar múltiples solicitudes de pago simultáneamente, garantizando consistencia de datos, idempotencia de transacciones y alta disponibilidad.

## Capacidades del Sistema

- Procesamiento de solicitudes de pago de forma reactiva y no bloqueante
- Integración con servicios externos: Motor Antifraude, Buró de Riesgos, Gateway de Pagos
- Persistencia reactiva con R2DBC y PostgreSQL
- Patrones de resiliencia: Circuit Breaker, Retry con Resilience4j
- Métricas y monitoreo con Spring Boot Actuator
- Objetivo: procesar mínimo 10,000 solicitudes por segundo con latencia máxima de 500ms

## Stack Tecnológico

- **Lenguaje**: Java 21
- **Framework**: Spring WebFlux 3.5.6
- **Persistencia**: R2DBC con PostgreSQL
- **Resiliencia**: Resilience4j 2.2.0
- **Reactividad**: Project Reactor 3.6.8
- **Build**: Maven
- **Testing**: JUnit 5, Reactor Test

## Estructura del Proyecto

```
payments/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/fintech/payments/
│   │   │   ├── PaymentsApplication.java
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── PaymentRequest.java
│   │   │   │   │   └── PaymentStatus.java
│   │   │   │   └── port/
│   │   │   │       ├── PaymentRepositoryPort.java
│   │   │   │       ├── AntifraudServicePort.java
│   │   │   │       ├── RiskBureauPort.java
│   │   │   │       └── PaymentGatewayPort.java
│   │   │   ├── application/
│   │   │   │   └── usecase/
│   │   │   │       └── ProcessPaymentUseCase.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── PaymentRepositoryAdapter.java
│   │   │   │   │   ├── AntifraudServiceAdapter.java
│   │   │   │   │   ├── RiskBureauAdapter.java
│   │   │   │   │   └── PaymentGatewayAdapter.java
│   │   │   │   ├── config/
│   │   │   │   │   ├── WebClientConfig.java
│   │   │   │   │   └── Resilience4jConfig.java
│   │   │   │   └── controller/
│   │   │   │       └── PaymentController.java
│   │   │   └── infrastructure/
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/fintech/payments/
│           └── application/usecase/
│               └── ProcessPaymentUseCaseTest.java
```

## Arquitectura

El proyecto sigue el patrón de **Arquitectura Hexagonal** (Ports and Adapters) con las siguientes capas:

### Capa de Dominio (`domain/`)
Contiene la lógica de negocio pura sin dependencias externas:
- **Modelos**: PaymentRequest, PaymentStatus
- **Puertos**: Interfaces que definen la comunicación con el exterior
  - PaymentRepositoryPort: Persistencia de pagos
  - AntifraudServicePort: Integración con motor antifraude
  - RiskBureauPort: Integración con buró de riesgos
  - PaymentGatewayPort: Integración con gateway de pagos

### Capa de Aplicación (`application/`)
Contiene los casos de uso que orquestan la lógica de negocio:
- ProcessPaymentUseCase: Orquesta el flujo completo de procesamiento de un pago

### Capa de Infraestructura (`infrastructure/`)
Implementa los adaptadores que conectan con sistemas externos:
- **Adapters**: Implementaciones concretas de los puertos
- **Config**: Configuración de WebClient y Resilience4j
- **Controller**: Endpoints REST para exponer el servicio

### Flujo de Procesamiento

1. El cliente envía una solicitud de pago al endpoint REST
2. El controlador recibe la petición y delega al caso de uso
3. El caso de uso valida la solicitud y persiste el estado inicial
4. Se invoca al servicio de antifraude de forma reactiva
5. Se consulta al buró de riesgos para evaluación de riesgo
6. Se procesa el pago a través del gateway de pagos
7. Se actualiza el estado final del pago en la base de datos

## Configuración

### Prerequisites

- JDK 21 o superior
- Maven 3.8+
- PostgreSQL 14+ (para desarrollo local)
- Docker (opcional, para contenedor de base de datos)

### Configuración de Base de Datos

El proyecto usa R2DBC con PostgreSQL. Configure las siguientes variables de entorno o actualice `application.yml`:

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/payments
    username: postgres
    password: postgres
    pool:
      initial-size: 10
      max-size: 50
```

### Configuración de Servicios Externos

Configure los endpoints de los servicios externos en `application.yml`:

```yaml
services:
  antifraud:
    url: http://localhost:8081/api/antifraud
  risk-bureau:
    url: http://localhost:8082/api/risk
  payment-gateway:
    url: http://localhost:8083/api/gateway
```

### Configuración de Resiliencia

Resilience4j está configurado con Circuit Breaker y Retry para los adaptadores externos. Los valores pueden ajustarse en `application.yml` bajo la sección `resilience4j`.

## Ejecución del Proyecto

### Compilación

```bash
mvn clean compile
```

### Ejecución

```bash
mvn spring-boot:run
```

### Ejecución de Tests

```bash
mvn test
```

### Construcción del JAR

```bash
mvn clean package -DskipTests
```

## Endpoints REST

### Procesar Pago

```http
POST /api/payments
Content-Type: application/json

{
  "customerId": "uuid-del-cliente",
  "amount": 1000.00,
  "currency": "USD",
  "description": "Pago de servicio"
}
```

### Consultar Pago por ID

```http
GET /api/payments/{transactionId}
```

### Listar Pagos por Cliente

```http
GET /api/payments?customerId={customerId}
```

### Listar Pagos por Estado

```http
GET /api/payments?status={status}
```

### Métricas

```http
GET /actuator/metrics
GET /actuator/health
```

## Patrones y Convenciones

### Programación Reactiva

El proyecto utiliza operadores reactivos de Project Reactor:
- `flatMap`: Para transformaciones asíncronas en cadena
- `zip`: Para combinar múltiples flujos reactivos
- `onErrorResume`: Manejo de errores reactivo
- `Mono`/`Flux`: Tipos reactivos para operaciones uni/multivalor

### Inyección de Dependencias

Se utiliza inyección por constructor con `@Autowired` en Spring WebFlux:

```java
@Service
public class ProcessPaymentUseCase {
    private final PaymentRepositoryPort paymentRepository;
    private final AntifraudServicePort antifraudService;
    private final RiskBureauPort riskBureau;
    private final PaymentGatewayPort paymentGateway;

    @Autowired
    public ProcessPaymentUseCase(
            PaymentRepositoryPort paymentRepository,
            AntifraudServicePort antifraudService,
            RiskBureauPort riskBureau,
            PaymentGatewayPort paymentGateway) {
        this.paymentRepository = paymentRepository;
        this.antifraudService = antifraudService;
        this.riskBureau = riskBureau;
        this.paymentGateway = paymentGateway;
    }
}
```

### Records para DTOs

Se utilizan records de Java 21 para DTOs inmutables:

```java
public record PaymentRequest(
    UUID transactionId,
    UUID customerId,
    BigDecimal amount,
    String currency,
    String description,
    PaymentStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public PaymentRequest withStatus(PaymentStatus newStatus) {
        return new PaymentRequest(
            transactionId, customerId, amount, currency,
            description, newStatus, createdAt, LocalDateTime.now()
        );
    }
}
```

## Manejo de Errores

El sistema implementa manejo de errores reactivo:
- Validación de solicitudes con mensajes descriptivos
- Manejo de fallos en servicios externos con Circuit Breaker
- Reintentos automáticos configurables
- Logging estructurado para auditoría

## Consideraciones de Rendimiento

- El sistema está diseñado para procesar 10,000+ solicitudes por segundo
- La latencia objetivo es menor a 500ms por transacción
- El pool de conexiones R2DBC está configurado para alta concurrencia
- Circuit Breaker previene fallos en cascada
- WebClient usa estrategias de exchange para backpressure

## Contribución

Para contribuir al proyecto:
1. Fork del repositorio
2. Crear una rama para la feature (`git checkout -b feature/nueva-feature`)
3. Commit de los cambios (`git commit -m 'Agrega nueva feature'`)
4. Push a la rama (`git push origin feature/nueva-feature`)
5. Crear un Pull Request

## Licencia

Este proyecto es propiedad de Fintech Payments y está bajo licencia propietaria.
```
