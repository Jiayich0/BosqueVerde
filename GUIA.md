
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
# Cambios/implementación de nombres de DClase a Código Java
.view     :   InterfazX   ->  VistaX
.facade   :   ControlX    ->  ControlX
.dao      :   BDX         ->  DAOX
.model    :   X           ->  X

Por ejemplo:
- InterfazVendedor se ha pasado a VistaVendedor
- BDVendedor se ha pasado a DAOVendedor

# Cambios de DClase:DBX a DAOX
añadirX     ->  insertar()
eliminarX   ->  bajaLogica()
actualizarX ->  actualizar()
obtenerX    ->  buscarPorDni()
listar      ->  listarTodas()
existe      ->  reutilizar 'buscarPorDni() != null' (o buscarPorDniTotal())
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
Si fuese int / integer no sería lo más adecuado

# Orden de atributos en las clases (mantener siempre para evitar errores)
- Primero:   id
- Luego:     resto
- Último:    activo

# Ver la BD
Para visualizar las tablas de la Base de Datos se puede usar:
    https://sqliteviewer.app/
Como en nuestro programa no se hacen borrados reales (solo lógicos), si se quiere borrar algún dato para
pruebas, hay que hacerlo a mano con alguna una aplicación como 
    https://sqlitebrowser.org/dl/
o también crear vuestro método en ConexionBD.java, solo usarlo cuando se esté haciendo pruebas

-------------------------------------------------------------------------------------------------------------
|                                                   Tareas                                                  |
-------------------------------------------------------------------------------------------------------------
# 0
Para hacerlo con GitHub los pasos son:
> Me mandáis vuestro 'email' o 'username' de GitHub, os añado como colaboradores
> Clonáis el repositorio en vuestro ordenador
> Entráis en el proyecto y creáis vuestra rama (no hagáis en el main)
> Hacéis vuestros cambios y los commiteáis
> Cuando vuestra parte esté lista hacéis un Pull Request hacia main
> Lo reviso para que haya coherencia entre las partes
> Si está bien lo apruebo y hacéis merge al main

# 1

# Base de datos: crear tabla
Crear tu tabla (si es que ningún compañero más que comparta subsistema lo ha creado ya)
> En ConexionDB.java y el método de incializar: crear tu sentencia sql y añadir un execute de esa sentencia
  seguido del anterior
> (Para crear el borrado de depuración añadirlo abajo)