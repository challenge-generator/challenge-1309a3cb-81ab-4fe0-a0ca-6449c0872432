# Implementación de Paradigma Reactivo en Sistema de Procesamiento de Pagos

En un sistema de procesamiento de pagos de una fintech, se requiere implementar un paradigma de programación reactivo para mejorar la escalabilidad y resiliencia del sistema. El sistema debe manejar múltiples solicitudes de pago simultáneamente, garantizando la consistencia de los datos y la idempotencia de las transacciones. Los actores involucrados son el originador de créditos, el motor antifraude, el buró de riesgos y el gateway de pagos. El sistema debe procesar un mínimo de 10 000 solicitudes por segundo con una latencia máxima de 500 ms.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 1 semana |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Modelado del Dominio

**Objetivo:** Definir el modelo de datos y las reglas de negocio para el procesamiento de pagos.

**Tiempo estimado:** 2 días

**Instrucciones:**

- Identificar los actores y las interacciones en el dominio de procesamiento de pagos.
- Definir las entidades y las relaciones entre ellas.
- Establecer las reglas de negocio y los criterios de validación para las solicitudes de pago.

**Entregable:** Modelo de datos y reglas de negocio documentadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los cuatro pilares del manifiesto de sistemas reactivos: respuesta, elasticidad, resiliencia y mensajería.
- Piensa en cómo garantizar la idempotencia de las transacciones y la consistencia de los datos.

</details>

### Fase 2: Implementación del Paradigma Reactivo

**Objetivo:** Implementar el paradigma reactivo en el sistema de procesamiento de pagos.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Diseña la arquitectura reativa del sistema, considerando los cuatro pilares del manifiesto de sistemas reactivos.
- Implementa los operadores básicos del paradigma reactivo para manejar las solicitudes de pago.
- Garantiza la idempotencia de las transacciones y la consistencia de los datos.

**Entregable:** Sistema de procesamiento de pagos implementado con paradigma reactivo.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza operadores reactivos para manejar el backpressure y la elasticidad del sistema.
- Considera cómo implementar la resiliencia y la mensajería en el sistema.

</details>

### Fase 3: Evaluación y Optimización

**Objetivo:** Evaluar el rendimiento y la escalabilidad del sistema reactivo y realizar optimizaciones necesarias.

**Tiempo estimado:** 2 días

**Instrucciones:**

- Realiza pruebas de carga y rendimiento para evaluar el sistema reactivo.
- Identifica cuellos de botella y realiza optimizaciones para mejorar el rendimiento y la escalabilidad.
- Documenta las mejoras realizadas y los resultados obtenidos.

**Entregable:** Informe de evaluación y optimización del sistema reactivo.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas de carga y rendimiento para evaluar el sistema.
- Considera técnicas de optimización como la paralelización y la distribución de carga.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el paradigma reactivo y cuáles son sus cuatro pilares?
- **paraQueSirve**: ¿Para qué sirve implementar el paradigma reactivo en el sistema de procesamiento de pagos?
- **comoSeUsa**: ¿Cómo se utilizan los operadores básicos del paradigma reactivo para manejar las solicitudes de pago?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar el paradigma reactivo y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación del paradigma reactivo en términos de rendimiento, escalabilidad y resiliencia?

## Criterios de Evaluacion

- Modelo de datos y reglas de negocio documentadas.
- Sistema de procesamiento de pagos implementado con paradigma reactivo.
- Informe de evaluación y optimización del sistema reactivo.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
