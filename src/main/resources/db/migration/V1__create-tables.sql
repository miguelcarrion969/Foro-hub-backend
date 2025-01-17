CREATE TABLE perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    contrasenha VARCHAR(300) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    perfil_id BIGINT NOT NULL,

    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estado VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE respuesta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor_id BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL,
    respuesta_padre_id BIGINT NULL, -- Permitir que respuesta_padre_id pueda ser NULL para respuestas directas

    FOREIGN KEY (topico_id) REFERENCES topico(id) ON DELETE CASCADE,
    FOREIGN KEY (autor_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (respuesta_padre_id) REFERENCES respuesta(id) ON DELETE CASCADE -- Relación con la misma tabla
);