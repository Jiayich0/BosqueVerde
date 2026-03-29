
-------------------------------------------------------------------------------------------------------------
|                                       Configuraciones (Eclipse)                                           |
-------------------------------------------------------------------------------------------------------------
# Configurar SQLite:
Project > Properties > Java Build Path > Libraries > Classpath > Add JARs... > fichero* > Apply and Close
fichero*: BosqueVerde > lib > sqlite-jdbc-3.51.3.0.jar

# Vista de ficheros
Para Eclipse recomendable: Package Explorer (Barra lateral izquierda) > 'tres puntos' > Package Presentation > Hierarchical

-------------------------------------------------------------------------------------------------------------
|                                 Cambios de DCU, DClase y DSec a código                                    |
-------------------------------------------------------------------------------------------------------------
# Cambios/implementación de DClase a Código Java
.view     :   InterfazX   ->  VistaX
.facade   :   ControlX    ->  ControlX
.dao      :   BDX         ->  DAOX
.model    :   X           ->  X

Por ejemplo:
- InterfazVendedor se ha pasado a VistaVendedor
- BDVendedor se ha pasado a DAOVendedor

# Cambios de DClase:DBX a XDAO
añadirX     ->  insertar()
eliminarX   ->  bajaLogica()
actualizarX ->  actualizar()
obtenerX    ->  buscarPorDni()
listar      ->  listarTodas()
existe      ->  reutilizar buscarPorDni() != null (o el siguiente ...DniTotal)
            ->  buscarPorDniTotal(): nuevo para poder reactivar
            ->  reactivar(): es como añadir pero no crea uno nuevo, reactiva uno eliminado

-------------------------------------------------------------------------------------------------------------
|                                         Cosas a tener en cuenta                                           |
-------------------------------------------------------------------------------------------------------------
# Tipo de dato de 'telefono'
Se usa el tipo String(java) / varchar(SQL) porque:
- No se opera con él
- Puede empezar con '0's
- Puede contener +prefijo

# Orden de atributos
- Primero:   id
- Luego:     resto
- Último:    activo

# Ver la BD
Para visualizar las tablas de la Base de Datos se puede usar:
https://sqliteviewer.app/
Como en nuestro programa no se hacen borrados reales (solo lógicos), si se quiere borrar algún dato, hay que
hacerlo a mano con algún script, código o una aplicación como https://sqlitebrowser.org/dl/

-------------------------------------------------------------------------------------------------------------
|                                                   Tareas                                                  |
-------------------------------------------------------------------------------------------------------------
# 1
