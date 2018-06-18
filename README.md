# EclipsePlugin

Ingeniería en Computadores
CE-1103 Algoritmos y Estructuras de Datos I
II Semestre 2017
Proyecto programado II

## 1. Objetivo
Diseñar e implementar un plugin de Eclipse para la depuración y análisis visual de
algoritmos utilizando diagramas de flujo y Java Platform Debugger Architecture (JPDA) con
el fin de facilitar el aprendizaje de conceptos básicos de programación y la comprensión de
la complejidad algorítmica de los programas.
## 2. Descripción
El trabajo consiste en desarrollar un plugin de Eclipse (véase información general en
http://wiki.eclipse.org/PDE, tutorial básico sobre creación de un plugin en
http://bit.ly/2fvb8Ue y creación de plugin gráfico https://eclipse.org/swt) para apoyar la
depuración de programas, la enseñanza de programación básica y analizar el tiempo de
ejecución de los sistemas para determinar la complejidad algorítmica de estos (véase
http://bit.ly/2hcCuhI y http://bit.ly/2wneLpx). La herramienta toma como entrada la
secuencia de ejecución del código fuente de programas sencillos y despliega una animación
basada en un diagrama de flujo que utiliza los símbolos básicos que se muestran en la tabla
1 (puede agregar símbolos adicionales si lo considera necesario).
La interface del sistema debe tener varios componentes, en el lado izquierdo debería
presentar el código fuente que está siendo evaluado, mientras que en el centro de la ventana
debe mostrar el diagrama de flujo que corresponde a ese código. Esto requiere determinar el
tipo de instrucción de cada sentencia del programa y hacer la asociación correspondiente en
términos gráficos. Considere que el diagrama de flujo es un grafo dirigido que puede ser
representado utilizando matrices o listas de adyacencia y que su animación requiere realizar
su recorrido en función de las entradas que recibe el algoritmo.
Conforme se hace la depuración el programa hace una animación de la instrucción
que se está ejecutando, tanto en el código fuente como en el diagrama de flujo. Si el programa
llama un método que ha sido definido de forma externa, en otra clase del mismo proyecto o
en la misma clase, el usuario debe tener la posibilidad de elegir si quiere hacer un step into
(investigar sobre métodos de depuración de programas) o step over. Si el usuario elige usar
un step into aparece otro diagrama de flujo con la animación de la ejecución de cada
instrucción del método en el lado derecho del diagrama de flujo principal.
Los valores que toman las variables durante la ejecución de un programa son
desplegados en un panel en el extremo derecho de la ventana, al igual que la complejidad
algorítmica de los bloques que componen el programa bajo estudio.
Para obtener los detalles de la ejecución de las instrucciones de los programas utilice
JPDA y tome como referencia inicial los siguientes enlaces:
1. JPDA: http://bit.ly/2w2HMCS y http://bit.ly/2h9nRiW
2. Ejemplos:
2.1. http://bit.ly/2f5EyYr
2.2. http://bit.ly/2feSEKU
Mientras que para realizar el análisis de la complejidad algorítmica se puede apoyar en
la información del siguiente enlace http://bit.ly/2xr9JI5.

Instrucción
Declaraciones,
asignaciones,
operaciones y
otras
Símbolo
Método externo,
en el mismo
proyecto o clase
Condicional if
Ciclo while
Ciclo while

El diseño arquitectónico del plugin debe estar basado en el patrón MVC y debe
contemplar el uso de los patrones Abstract Factory, Factory Method, Singleton, Composite
e Iterator. Si el diseño no utiliza los patrones indicados se obtendrá una nota de un cero en
todo el proyecto.

