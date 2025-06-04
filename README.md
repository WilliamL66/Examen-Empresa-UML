# Sistema de Gestión de Compras ERP


## Integrantes:
- William Loyola
“Trabajo individual”


## Descripción del Proyecto
Este sistema simula un módulo de compras para un ERP (Planificación de recursos empresariales). Permite registrar proveedores, productos y solicitudes de compra realizadas por distintos departamentos. El sistema incluye funcionalidades para listar, buscar, aprobar/rechazar solicitudes y calcular el total de cada solicitud.

## Instrucciones para Ejecutar el Proyecto
1. Asegúrate de tener Java instalado 
2. Abre la carpeta del proyecto en Visual Studio Code
3. Abre el archivo `SistemaCompras.java`
4. Ejecuta con el botón ▶️ o clic derecho → Run Java
5. Usa el menú en consola para interactuar

## Funcionalidades Principales
- Registro de proveedores y productos
- Creación de solicitudes de compra con múltiples ítems
- Control de estado de solicitudes: `SOLICITADA`, `EN_REVISION`, `APROBADA`, `RECHAZADA`
- Cálculo automático del costo total de cada solicitud
- Listado y búsqueda de proveedores, productos y solicitudes

## Conceptos de Programación Orientada a Objetos aplicados
- Herencia: `Proveedor` hereda de la clase abstracta `Persona`
- Clase abstracta: `Persona` define un método abstracto `mostrarInformacion()`
- Interfaces: `Calculable` define un comportamiento común para clases que calculan costos
- Enum: `EstadoSolicitud` representa los estados posibles de una solicitud
- Polimorfismo dinámico: el sistema procesa listas de objetos `Calculable` para calcular totales

## Tecnologías Utilizadas
- Java 
- Visual Studio Code