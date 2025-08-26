(PROMPS DEL TRABAJO)

ISSUE 1: 

Explicaciones de las categorias con enum:

Con enum, el compilador garantiza que solo puedes usar valores válidos.
Autocompletado y refactoring
El IDE puede sugerir automáticamente los valores válidos del enum y hacer refactoring seguro cuando cambies nombres.
Se comparan por referencia (más rápido)
Ocupan menos memoria
No requieren validación en tiempo de ejecución

ISSUE 2: 

Diferencias entre una class y una data class: 

La data class genera automáticamente, usando una class normal, hay que escribir todo manualmente. 
Es un contenedor de datos inmutables, en este caso actua como una plantilla o un modelo de datos que define la estructura común para todos los tipos de materiales reciclables en el sistema.
Todos los materiales tienen exactamente la misma estructura
Facilita operaciones del sistema
Proporciona toda la funcionalidad necesaria (comparación, copia, representación) sin código adicional.
No requieren validación en tiempo de ejecución
