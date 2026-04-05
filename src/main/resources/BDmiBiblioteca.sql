USE misLibros_db;

CREATE INDEX indice_nombre ON autor(nombre);
CREATE INDEX indice_apellido1 ON autor(apellido1);

-- Búsquedas que combinan nombre y apellido
CREATE INDEX idx_autor_nombre_apellido ON autor(nombre, apellido1);

CREATE INDEX indice_NombreGenero ON genre(nombre);

CREATE INDEX indice_tituloLibro ON libros(title);

CREATE INDEX indice_pais ON pais(nombre);

-- Si filtras por año de publicación
CREATE INDEX indice_yearP_libros ON libros (year_publicacion);

-- Si haces búsquedas como LIKE 'Cien%', este índice ayuda
CREATE INDEX idx_titulo_prefix ON libros(title(25));

SELECT CONCAT_WS(' ',autor.apellido1, autor.nombre) AS nombre, libro.title AS titulo
FROM autor
LEFT JOIN autor_libro
ON autor_libro.author_id = autor.id
LEFT JOIN libro
ON autor_libro.libro_id = libro.id;

-- CREACIÓN DE VISTAS
-- Todos los géneros
CREATE OR REPLACE VIEW todos_los_generos AS
SELECT * FROM genero ORDER BY nombre;

-- Todos los autores
CREATE OR REPLACE VIEW todos_los_autores AS
SELECT CONCAT_WS(' ',autor.nombre, autor.apellido1, autor.apellido2) AS nombre, IFNULL(autor.year_nacimiento,'-') AS nacimiento, IFNULL(autor.year_fallecimiento,'-') AS fallecimiento, pais.nombre AS pais
FROM autor
INNER JOIN pais
ON pais.id = autor.pais_id
ORDER BY nombre;

-- todos los libros
CREATE OR REPLACE VIEW todos_los_libros AS
SELECT libro.title AS titulo, libro.year_publicacion AS publicacion, libro.pages AS n_paginas, autor.nombre, autor.apellido1, autor.apellido2, IFNULL(opinion,'-')
FROM libro 
INNER JOIN autor_libro
ON autor_libro.libro_id = libro.id
INNER JOIN autor
ON autor_libro.author_id = autor.id
ORDER BY autor.apellido1, autor.apellido2, autor.nombre, n_paginas;

-- Libros del S.XXI
CREATE OR REPLACE VIEW libros_SigloXXI AS
SELECT title AS titulo, year_publicacion AS publicacion, pages AS n_paginas, CONCAT_WS(' ',autor.nombre, autor.apellido1, autor.apellido2) AS autor, IFNULL(opinion,'-')
FROM libro 
INNER JOIN autor_libro
ON autor_libro.libro_id = libro.id
INNER JOIN autor
ON autor_libro.author_id = autor.id
WHERE year_publicacion >= 2000
ORDER BY n_paginas;

-- Libros anteriores al año 2000
CREATE OR REPLACE VIEW libros_Anteriores_SigloXXI AS
SELECT title AS titulo, year_publicacion AS publicacion, pages AS n_paginas, CONCAT_WS(' ',autor.nombre, autor.apellido1, autor.apellido2) AS autor, IFNULL(opinion,'-')
FROM libro 
INNER JOIN autor_libro
ON autor_libro.libro_id = libro.id
INNER JOIN autor
ON autor_libro.author_id = autor.id
WHERE year_publicacion <= 1999
ORDER BY n_paginas;

-- Libros del autor más leído

CREATE OR REPLACE VIEW autores_mas_leidos AS
SELECT autor.nombre, autor.apellido1, CASE 
        WHEN autor.apellido2 IS NULL OR autor.apellido2 = '' THEN '-'
        ELSE autor.apellido2
    END AS apellido2, COUNT(autor_libro.author_id) AS num_libros
FROM autor_libro
INNER JOIN autor
ON autor_libro.author_id = autor.id
GROUP BY autor.id
ORDER BY num_libros DESC;
