# WoW Private Server - Java Application Setup

Este proyecto es una aplicación backend para un servidor privado de WoW. Para ejecutar la aplicación, debes configurar algunas variables de entorno y asegurarte de tener instalada una versión de Java 17 o superior.

## Requisitos

- **Java 17 o superior**
- **MySQL** (o compatible) para la base de datos

## Pasos para configurar la aplicación

### 1. Instalación de Java 17 o superior

Para ejecutar esta aplicación, debes tener **Java 17** o superior instalado en tu máquina. Si no tienes Java instalado, sigue los siguientes pasos según tu sistema operativo:

#### En Windows:

1. Descarga la última versión de Java desde el sitio oficial de [AdoptOpenJDK](https://adoptium.net/temurin/releases/) o desde [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Sigue las instrucciones de instalación.
3. Verifica que Java está correctamente instalado abriendo una terminal (cmd) y ejecutando el siguiente comando:

   ```bash
   java -version
   ```
   
Si la instalación fue exitosa, deberías ver algo similar a:

 ```bash
 openjdk version "17.0.1" 2021-10-19
 OpenJDK Runtime Environment (build 17.0.1+12-39)
 OpenJDK 64-Bit Server VM (build 17.0.1+12-39, mixed mode)
```

### Configuración de Variables de Entorno
Antes de ejecutar la aplicación, necesitas configurar las variables de entorno que contienen la configuración de tu base de datos. Esto es necesario para conectar la aplicación con tu base de datos MySQL.

#### Variables de Entorno:

**DB_URL:** La URL de la base de datos.

**DB_USERNAME:** El nombre de usuario para la base de datos.

**DB_PASSWORD:** La contraseña para la base de datos.
Cómo establecer las variables de entorno:

**En Windows:**

- Abre una terminal (cmd) y usa los siguientes comandos para configurar las variables de entorno:

```bash
setx DB_URL "jdbc:mysql://localhost:3306/acore_auth"
setx DB_USERNAME "root"
setx DB_PASSWORD "clave"
```

- Reinicia la terminal para que las variables de entorno se apliquen.

En macOS/Linux:

Abre el terminal y agrega las variables de entorno a tu archivo ~/.bashrc, ~/.zshrc, o ~/.profile (dependiendo del shell que estés utilizando):



```bash
export DB_URL="jdbc:mysql://localhost:3306/acore_auth"
export DB_USERNAME="root"
export DB_PASSWORD="clave"
```

Después de agregar estas líneas, ejecuta:

```bash
source ~/.bashrc
```
O 

```bash
source ~/.zshrc
```


O

```bash
source ~/.profile
```


Para verificar que las variables se han establecido correctamente, ejecuta:

```bash
echo $DB_URL
echo $DB_USERNAME
echo $DB_PASSWORD
```

###  Compilar Aplicacion 
Una vez que Java esté instalado y las variables de entorno configuradas, puedes compilar y ejecutar la aplicación. Desde la raíz del proyecto, ejecuta los siguientes comandos:

- Compilar el proyecto (usando Maven):


```bash
mvn clean install
```
### Omitir Compilacion
Dejo la ultima version compilada subida a mediafire, solo debes descargarlo y ejecutar 

```bash
java -jar app-register-account.jar
```

https://www.mediafire.com/file/0p0090ekk9qzkej/app-register-account.jar/file

### Verificación

Para verificar que la aplicación está funcionando correctamente, abre tu navegador web y accede a:

```bash
http://localhost:8090
```
