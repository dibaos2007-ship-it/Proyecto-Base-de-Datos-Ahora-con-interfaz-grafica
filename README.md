# Proyecto-Base-de-Datos-Ahora-con-interfaz-grafica
Aqui esta mi segunda versión de mi proyecto, ahora contendrá interfaz grafica y un lector de texto. Contendrá la misma base de datos de planetas 


```mermaid
flowchart TD
    A[INICIO] --> B{Conectar a Base de Datos}
    B -->|Error| C[Mostrar Error y Finalizar]
    B -->|Éxito| D[Abrir Ventana Principal]
    
    D --> E{Selección de Opción}
    
    %% OPCIÓN AGREGAR
    E -->|AGREGAR| F[Leer todos los campos]
    F --> G{¿Campos vacíos?}
    G -->|Sí| H[Mostrar: LLENA TODOS LOS CAMPOS] --> D
    G -->|No| I{¿Formato correcto?<br>Números / Fecha / true-false}
    I -->|No| J[Mostrar: ERROR DE FORMATO] --> D
    I -->|Sí| K[Conectar BD<br>INSERT INTO planetas]
    K --> L{¿Operación exitosa?}
    L -->|No| M[Mostrar ERROR BD] --> D
    L -->|Sí| N[Mostrar: PLANETA AGREGADO] --> O[Cerrar Conexión] --> D

    %% OPCIÓN CONSULTAR UNO
    E -->|CONSULTAR UNO| P[Leer campo ID]
    P --> Q{¿ID vacío?}
    Q -->|Sí| R[Mostrar: ESCRIBE UN ID] --> D
    Q -->|No| S{¿Es solo números?}
    S -->|No| T[Mostrar: SOLO NÚMEROS] --> D
    S -->|Sí| U[Conectar BD<br>SELECT * WHERE id = ?]
    U --> V{¿Existe el registro?}
    V -->|No| W[Mostrar: NO EXISTE ESE ID] --> X[Cerrar Conexión] --> D
    V -->|Sí| Y[Mostrar todos los datos] --> X

    %% OPCIÓN CONSULTAR TODOS
    E -->|CONSULTAR TODOS| Z[Conectar BD<br>SELECT * FROM planetas]
    Z --> AA{¿Hay registros?}
    AA -->|No| AB[Mostrar: NO HAY DATOS] --> AC[Cerrar Conexión] --> D
    AA -->|Sí| AD[Leer y armar lista] --> AE[Mostrar resultados<br>Guardar para exportar] --> AC

    %% OPCIÓN FILTRAR
    E -->|FILTRAR| AF[Leer campo TIPO]
    AF --> AG{¿Campo vacío?}
    AG -->|Sí| AH[Mostrar: ESCRIBE UN TIPO] --> D
    AG -->|No| AI[Conectar BD<br>SELECT * WHERE tipo_planeta = ?]
    AI --> AJ{¿Hay coincidencias?}
    AJ -->|No| AK[Mostrar: NO ENCONTRADO] --> AL[Cerrar Conexión] --> D
    AJ -->|Sí| AM[Mostrar lista filtrada<br>Guardar para exportar] --> AL

    %% OPCIÓN EXPORTAR
    E -->|EXPORTAR TXT| AN{¿Hay datos guardados?}
    AN -->|No| AO[Mostrar: SIN DATOS PARA EXPORTAR] --> D
    AN -->|Sí| AP[Crear archivo reporte_planetas.txt]
    AP --> AQ[Escribir datos en archivo]
    AQ --> AR{¿Escritura correcta?}
    AR -->|No| AS[Mostrar ERROR DE ARCHIVO] --> D
    AR -->|Sí| AT[Cerrar archivo<br>Mostrar: EXPORTADO CORRECTO] --> D

    %% FIN
    D --> AU[FIN<br>Al cerrar ventana]
```
