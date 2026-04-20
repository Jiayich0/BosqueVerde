Aplicación de gestión de librería desarrollada en Java con interfaz gráfica (Swing) y persistencia en base de datos SQLite. El sistema permite realizar operaciones CRUD (Create-Read-Update-Delete) de los subsistemas Cliente, Vendedor, Libro, Categoría, Editorial y Factura.

El proyecto se centra en trasladar a código los modelos y diagramas definidos previamente (casos de uso, diagramas de clases y de secuencia), respetando la arquitectura planteada y manteniendo la coherencia entre análisis, diseño e implementación. Cada funcionalidad corresponde a un caso de uso y sigue el flujo definido en los diagramas.

Patrones usados:
- MVC (patrón arquitectónico)
    Se separa el sistema en Modelo-Vista-Controlador para separar la interfaz y lógica.
        Modelo:     Subsistemas (Cliente, Vendedor, etc.)
        Vista:      Interfaces gráficas (Swing)
        Control:    Lógica (SistemaBDL + ControlSubsistemas)
    En nuestra implementación, SistemaBDL actúa como punto central de coordinación entre la vista y la lógica del sistema. Por ello, además de funcionar como fachada, asume parcialmente el papel de controlador de alto nivel dentro de la arquitectura MVC.
    
- Singleton (patrón creacional)
    El patrón Singleton se utiliza para garantizar que exista una única instancia de una clase durante toda la ejecución de la aplicación.
    En este proyecto se aplica en SistemaBDL, que actúa como punto central del sistema. De esta forma, se evita la creación de múltiples instancias con estados inconsistentes y se centraliza el acceso a la lógica del sistema.
    Para ello se mantiene un contructor privado y se accede mediante un método estático (getInstance()).

- Facade (patrón estructural)
    El patrón Facade se utiliza para proporcionar una interfaz simple y unificada al resto del sistema, ocultando la complejidad interna.
    En este proyecto, SistemaBDL actúa como fachada. Todas las operaciones pasan por SistemaBDL e internamente la fachada delega en las clases de control y DAO.

- DAO (patrón estructural)
    El patrón DAO (Data Access Object) se encarga de separar completamente el acceso a la base de datos del resto de la aplicación. Toda la lógica SQL queda centralizada en una única clase. 
    Cada entidad del sistema tiene su propio DAO que contiene todas las operaciones necesarias para interactuar con la base de datos.

- Strategy (patrón comportamental)
  Se utiliza este patrón para desacoplar distintos criterios de filtrado o búsqueda en operaciones de listado de subsistemas sin modificar la lógica principal. Actualmente se puede ordenar solo por ID. Ampliar ordenación por nombre, DNI, categoría u otros atributos.

