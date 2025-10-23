# 🧩 Microservicio del Grupo 5 — Arquitectura de Software

Este proyecto es un **microservicio desarrollado en Java 17 con Spring Boot**, diseñado como parte de la Asignatura **Arquitectura de Software** de la **Universidad de La Sabana**.

El microservicio se ejecuta dentro de un contenedor **Docker**, se despliega en un **cluster de Kubernetes**, y cuenta con un flujo de **Integración Continua (CI)** en **Jenkins** y **Despliegue Continuo (CD)** automatizado mediante **ArgoCD**.

---

## 🚀 Tecnologías utilizadas

| Componente | Descripción |
|-------------|--------------|
| **Java 17** | Lenguaje base del microservicio. |
| **Spring Boot 3.x** | Framework principal para construcción del servicio. |
| **Maven** | Herramienta de construcción y gestión de dependencias. |
| **Docker** | Empaquetado y despliegue del servicio como contenedor. |
| **Kubernetes** | Orquestador de contenedores donde corre el microservicio. |
| **Jenkins** | Pipeline de CI para construir, probar y generar la imagen Docker. |
| **ArgoCD** | Herramienta GitOps utilizada para la entrega y sincronización automática en el cluster. |

---

## ⚙️ Arquitectura del microservicio

El microservicio expone una interfaz web con un mensaje de bienvenida y datos obtenidos dinámicamente desde:

- Un archivo `attached.txt` incluido en el classpath.
- Una variable de entorno llamada `POD_NAME`, que identifica el pod en ejecución.

El servicio está estructurado en tres capas principales:
- **Controller:** expone el endpoint principal `/`.
- **Service:** maneja la lógica de negocio y lectura de recursos.
- **Resources:** contiene el HTML y archivos anexos empaquetados en el JAR.

---

## 🧪 Ejecución local

### 1. Compilar el proyecto
```bash
mvn clean package -DskipTests
```

### 2. Construir la imagen Docker
```bash
docker build -t grupo5-microservice .
```

### 3. Ejecutar el contenedor
```bash
docker run -d --name grupo5-microservice -p 8080:8080 -e POD_NAME=pod-local grupo5-microservice
```

---

## ☸️ Despliegue en Kubernetes

El despliegue se realiza mediante **manifests YAML** que definen los recursos de Kubernetes:

- **Deployment:** define el pod que ejecuta el microservicio.
- **Service:** expone el servicio a la red del cluster.
- **ConfigMap / Secret:** parametrizan variables y configuraciones.

Ejemplo básico:
```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

---

## 🔄 CI/CD Automatizado

### 🧩 Jenkins (CI)
- Compila el código.
- Ejecuta los tests unitarios.
- Construye la imagen Docker.
- Publica la imagen en **Docker Hub**.

### 🚀 ArgoCD (CD)
- Monitorea el repositorio Git.
- Detecta cambios en los manifiestos Kubernetes.
- Sincroniza automáticamente la nueva versión en el cluster.

---

## 👨‍💻 Autores
**Grupo 5** — Asignatura *Arquitectura de Software*  
Universidad de La Sabana

---

## 🏁 Licencia
Este proyecto se distribuye con fines académicos bajo la licencia **MIT**.