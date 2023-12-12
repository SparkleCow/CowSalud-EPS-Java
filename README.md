# CowSalud EPS (API REST) 😷

<b>DESARROLLO WEB BACK END<br>
<b>Unidad 15<br>
Docente: Norbey Danilo Muñoz

<b>Autor: Jonathan David Ramos Gallego

Hola a todos, bienvenidos a CowSalud EPS. (API REST para manejo de pacientes, doctores y sus respectivas citas)

## ¿Qué tecnologías se emplearon en el proyecto? 🤖

- Java 17
- Spring y SpringBoot
- Spring Security Y JWT
- JPA e Hibernate
- SpringBoot Actuator
- Lombok 
- MySQL
- Visual Studio Code e IntelliJ
- Postman

## Instalación: 

Para ejecutar el API, debes asegurarte previamente de tener maven en tu sistema.

1. Descarga el proyecto: Clona el repositorio con git clone https://github.com/SparkleCow/Cowsalud-EPS.git o descarga el repositorio en formato .rar para poder manipularlo.
2. Configurar el proyecto: Configura las variables de entorno y gestiona el proyecto a través del archivo application.properties, aqui deberas especificar tu base de datos y tus datos para un optimo arranque del servidor.
3. Instalar las dependencias del proyecto: Instala todas las dependencias que se encuentran en el archivo pom.xml con ayuda de maven. 
4. Crea la base de datos: Crea una base de datos para poder implementar en el proyecto. Asegurate de agregar su información en el archivo application.properties
5. Iniciar el proyecto: Ejecuta el proyecto desde la clase CowSaludEpsApplication.

## Modelos de datos: 

Las entidades "Patient" (Paciente) y "Doctor" tienen un estatus equivalente a si se encuentran activos o fueron desvinculados de la EPS. Por otro lado, ambas entidades emplean un codificador para guardar y asegurar la integridad de las contraseñas.
Tambien, ambas entidades implementan la interfaz de UserDetails para poder emplearlas en el contexto de seguridad de Spring
y de igual forma, fueron generadas con ayuda de la libreria de Lombok.<br> 

1. Pacientes <br>
  
  
![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/9cfda2d7-08e7-4325-b7f1-15c6954bd987)
   <br><br>
   Esta entidad representa a los pacientes de la EPS, sin embargo se emplearon distintos DTO con el fin de obtener y mostrar información concreta a la hora de gestionar la información.
   Se creo un DTO para mostrar la información sin datos sensibles como lo es la contraseña, de igual forma se creó un DTO para recibir la información de un nuevo paciente para que de esta manera no se pueda setear
   sus roles o estatus y finalmente se empleo un DTO para la actualización de datos sin otorgar la oportunidad a algún paciente de agregar o cambiar información sensible.
   Otro punto esencial a tener en cuenta es que desde el API se gestiona la creación de los pacientes y se les da como valor predeterminado un rol del tipo "PATIENT" <br><br>
   
2. Doctores
   <br>
   
![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/a77b4b0b-6a6e-4869-81a5-78035c70e32b)
   <br><br>
   La entidad de que representa a los doctores (Doctor) tiene un tratamiento similar al de los pacientes, sin embargo, a diferencia de los pacientes, los doctores tienen una lista de roles que pueden contener el rol "DOCTOR", "CHIEF_DOCTOR" o ambos,
   esto con el fin de dar permisos a información y endpoints cruciales en la logica de negocio.<br><br>
   
3. Citas
   <br>
   
![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/0d1ef236-a46e-4e43-80d3-658d8a713508)
  <br><br>

  La entidad de citas contiene un Id compuesto con el cual mapea a las entidades de doctor y paciente que hacen parte de su relación (ManyToOne). Tambien tiene un DTO para dar respuestas a las solicitudes sin dejar entrever información sensible de los doctores
  ni pacientes. <br><br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/3b446819-fd91-48a9-b7f0-75bf9fd3878b)<br><br>

## Pruebas Postman

# Logueo 

   (No requiere ninguna autorización ni autenticación)
   
   *Contraseña* errada
   <br>
   
   ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/d0e1a224-77fc-4bb7-97d2-83e5333b69ad)<br>

   *Logueo*
   <br>
   
   ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/1890ed9d-55f3-4fdc-9389-fff4c9d3a18e)<br>

# Paciente

   *Crear paciente* (No requiere ninguna autorización ni autenticación)
   <br>
   
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/a40755b9-caac-469b-b90b-6f2eb2f3dce2)<br>

  *Encontrar paciente por ID* (Requiere rol de doctor, doctor jefe o paciente)
  <br>

  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/3eb0f580-0fbe-4fd3-b74d-e9e3fa21994e)<br>

  *Encontrar pacientes* (Requiere rol de doctor o doctor jefe)
  <br>
   
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/29c635e8-8fdf-4156-8441-d4748713e0a8)<br>

  *Encontrar pacientes activos* (Requiere rol de doctor o doctor jefe)
  <br>
  
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/e1904be6-2f66-44d2-98bb-5bac5a7c0b2b)<br>

  *Encontrar paciente por Email* (Requiere rol de doctor, doctor jefe o paciente)
  <br>

  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/9ef227ca-b3e3-40ff-9d10-b9b9c1630f02)<br>

  
  *Borrar paciente* (Requiere rol de doctor jefe. Se realiza un borrado lógico a través del atributo "status" cambiandolo de 1 a 0)
  <br>
  
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/1b615df9-a0b9-4cab-92c4-3613476294c9)<br>

  *Actualizar paciente* (Requiere rol de doctor jefe)<br>
  
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/cb8b6651-8637-4f7d-9384-0730f0378a76)<br>

  
# Doctor

*Registar un nuevo doctor* (Requiere rol de doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/4d876465-cfd9-4fc8-b7d9-eedb3f15b84b)<br>

*Registar un nuevo doctor jefe* (Requiere rol de doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/8aa4223c-385c-47a4-aa0c-3e14cef00b98)<br>

*Obtener todos los doctores* (Requiere rol de doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/e592732d-fd52-4f98-adf1-e03071a5b3ee)<br>

*Obtener todos los doctores activos* (Requiere rol de doctor o doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/a52c36f6-b629-462a-acf8-e39974bd4ad7)

*Obtener un doctor por su ID* (Requiere rol de doctor o doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/5f67fe20-17d1-4142-9043-228407ac1029)

*Obtener un doctor por su Email* (Requiere rol de doctor o doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/6449391d-7155-42fa-a77f-883335423f92)

*Obtener doctores por especialidad* (Requiere rol de doctor, doctor jefe o paciente)
<br>
![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/a4a75277-7e6d-46a0-9018-bb2a40da2037)

*Actualizar un doctor por su ID* (Requiere rol de doctor jefe)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/14f67860-a3ed-4c6e-be22-9572bdd242a3)

*Eliminar un doctor por su ID* (Requiere rol de doctor jefe. Se realiza un borrado lógico a través del atributo "status" cambiandolo de 1 a 0)
<br>

![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/4bb84260-c19b-4108-b79e-b31277efc8d5)



# Citas
   
  *Encontrar citas por medio del ID del paciente* (Requiere rol de doctor, doctor jefe o paciente)
  <br>

  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/c6258043-e8fe-428f-a7eb-6a53d9326fd0)<br>

 *Encontrar citas por medio del ID del doctor*(Requiere rol de doctor o doctor jefe)
  <br>
  
  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/9e3f4796-49d9-4c1e-9bce-fe34affe2ceb)

  *Encontrar todas las citas* (Requiere rol doctor jefe)
  <br>

  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/96ef7a0c-84ff-4d0f-a47a-ff9e06310d5e)

  *Borrar cita por medio de su ID* (Requiere rol doctor jefe)
  <br>

  ![image](https://github.com/SparkleCow/Cowsalud-EPS/assets/55297516/79a95bb1-152c-4e97-992b-e90c2a8241b6)