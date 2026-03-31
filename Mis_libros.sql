CREATE DATABASE IF NOT EXISTS misLibros_db;

USE misLibros_db;

CREATE TABLE pais(id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(300)
);

CREATE TABLE genre(id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR (300)
);

CREATE TABLE author(id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(250) NOT NULL,
apellido1 VARCHAR(250),
apellido2 VARCHAR(250),
edad INT UNSIGNED NOT NULL,
pais_id INT UNSIGNED NOT NULL,

FOREIGN KEY(pais_id) REFERENCES pais(id)

);

CREATE TABLE libros(id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(250) NOT NULL,
author_id INT UNSIGNED NOT NULL,
year_publicacion INT,
genre_id INT UNSIGNED NOT NULL,
pages INT UNSIGNED,
description TEXT,
opinion TEXT,

FOREIGN KEY (author_id) REFERENCES author (id),
FOREIGN KEY (genre_id) REFERENCES genre (id)
);

-- añadiendo los libros

-- 1. Frankenstein o el moderno prometeo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Frankenstein o el moderno prometeo',
    (SELECT id FROM author WHERE Nombre = 'Mary' AND Apellido1 = 'Shelley'),
    1818,
    (SELECT id FROM genre WHERE nombre = 'Ciencia ficción'),
    280,
    'Un científico crea vida artificial con consecuencias trágicas. Explora temas de responsabilidad, aislamiento y los límites de la ciencia.'
);


-- 1. Frankenstein o el moderno prometeo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Frankenstein o el moderno prometeo',
    (SELECT id FROM author WHERE Nombre = 'Mary' AND Apellido1 = 'Shelley'),
    1818,
    (SELECT id FROM genre WHERE nombre = 'Ciencia ficción'),
    280,
    'Un científico crea vida artificial con consecuencias trágicas. Explora temas de responsabilidad, aislamiento y los límites de la ciencia.'
);

-- 2. Cuento de Navidad
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Cuento de Navidad',
    (SELECT id FROM author WHERE Nombre = 'Charles' AND Apellido1 = 'Dickens'),
    1843,
    (SELECT id FROM genre WHERE nombre = 'Cuentos'),
    104,
    'Ebenezer Scrooge, un avaro anciano, es visitado por fantasmas que le muestran su pasado, presente y futuro, transformando su vida.'
);

-- 3. El Lazarillo de Tormes
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El Lazarillo de Tormes',
    (SELECT id FROM author WHERE Nombre = 'Anónimo'),
    1554,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    155,
    'Relata las desventuras de Lázaro, un niño que sirve a varios amos en la España del siglo XVI, crítica social satírica.'
);

-- 4. Breve historia del tiempo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Breve historia del tiempo',
    (SELECT id FROM author WHERE Nombre = 'Stephen' AND Apellido1 = 'Hawking'),
    1988,
    (SELECT id FROM genre WHERE nombre = 'Ciencia'),
    256,
    'Explica conceptos complejos del universo (big bang, agujeros negros) en lenguaje accesible para el público general.'
);

-- 5. El caballero de la armadura oxidada
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El caballero de la armadura oxidada',
    (SELECT id FROM author WHERE Nombre = 'Robert' AND Apellido1 = 'Fisher'),
    1990,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    112,
    'Alegoría sobre un caballero atrapado en su armadura que emprende un viaje de autodescubrimiento para liberarse.'
);

-- 6. Corazón
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Corazón',
    (SELECT id FROM author WHERE Nombre = 'Edmondo' AND Apellido1 = 'De' AND Apellido2 = 'Amicis'),
    1886,
    (SELECT id FROM genre WHERE nombre = 'Infantil'),
    288,
    'Diario de un niño italiano que narra historias sobre su escuela, familia y amigos, enfatizando valores patrióticos y humanos.'
);

-- 7. Crónica de una muerte anunciada
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Crónica de una muerte anunciada',
    (SELECT id FROM author WHERE Nombre = 'Gabriel' AND Apellido1 = 'García' AND Apellido2 = 'Márquez'),
    1981,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    120,
    'Reconstrucción periodística de un asesinato en un pueblo caribeño, donde todos sabían que ocurriría pero nadie lo impidió.'
);

-- 8. Diez negritos
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Diez negritos',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1939,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    264,
    'Diez invitados son asesinados uno por uno en una isla remota, según una macabra canción infantil.'
);

-- 9. Discurso del método. Meditaciones metafísicas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Discurso del método. Meditaciones metafísicas',
    (SELECT id FROM author WHERE Nombre = 'René' AND Apellido1 = 'Descartes'),
    1637,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    200,
    'Fundamento del racionalismo moderno. Propone dudar de todo para llegar a verdades ciertas: "Pienso, luego existo".'
);

-- 10. El arte de la guerra
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El arte de la guerra',
    (SELECT id FROM author WHERE Nombre = 'Sun' AND Apellido1 = 'Tzu'),
    -500,
    (SELECT id FROM genre WHERE nombre = 'Estrategia'),
    112,
    'Tratado militar clásico que explora tácticas, estrategias y filosofía de guerra aplicables a conflictos y competencia.'
);

-- 11. El arte de la prudencia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El arte de la prudencia',
    (SELECT id FROM author WHERE Nombre = 'Baltasar' AND Apellido1 = 'Gracián'),
    1647,
    (SELECT id FROM genre WHERE nombre = 'Aforismos'),
    300,
    '300 aforismos que ofrecen consejo práctico para navegar la complejidad social y alcanzar la excelencia personal.'
);

-- 12. El corazón de las tinieblas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El corazón de las tinieblas',
    (SELECT id FROM author WHERE Nombre = 'Joseph' AND Apellido1 = 'Conrad'),
    1899,
    (SELECT id FROM genre WHERE nombre = 'Aventura'),
    110,
    'Un marinero viaja al Congo belga en busca de Kurtz, un comerciante de marfil que ha adoptado prácticas salvajes.'
);

-- 13. El guardián entre el centeno
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El guardián entre el centeno',
    (SELECT id FROM author WHERE Nombre = 'Jerome David' AND Apellido1 = 'Salinger'),
    1951,
    (SELECT id FROM genre WHERE nombre = 'Coming-of-age'),
    277,
    'Holden Caulfield, adolescente rebelde, deambula por Nueva York tras ser expulsado, cuestionando la falsedad adulta.'
);

-- 14. El malestar en la cultura
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El malestar en la cultura',
    (SELECT id FROM author WHERE Nombre = 'Sigmund' AND Apellido1 = 'Freud'),
    1930,
    (SELECT id FROM genre WHERE nombre = 'Psicología'),
    120,
    'Análisis de la tensión entre los deseos individuales y las restricciones de la civilización, fuente de neurosis.'
);

-- 15. El viejo y el mar
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El viejo y el mar',
    (SELECT id FROM author WHERE Nombre = 'Ernest' AND Apellido1 = 'Hemingway'),
    1952,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    127,
    'Un viejo pescador cubano lucha por capturar un enorme pez espada en el Golfo de México, reflexión sobre dignidad y lucha.'
);

-- 16. El árbol de la ciencia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El árbol de la ciencia',
    (SELECT id FROM author WHERE Nombre = 'Pío' AND Apellido1 = 'Baroja'),
    1911,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    344,
    'Sigue la vida de Andrés Hurtado, un estudiante de medicina desencantado, en una España decadente y absurda.'
);

-- 17. Manual de Epicteto
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Manual de Epicteto',
    (SELECT id FROM author WHERE Nombre = 'Epicteto'),
    135,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    80,
    'Compilación de enseñanzas estoicas sobre cómo vivir con virtud, aceptar lo que no controlamos y mantener la serenidad.'
);

-- 18. Escuela de Robinsones
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Escuela de Robinsones',
    (SELECT id FROM author WHERE Nombre = 'Jules' AND Apellido1 = 'Verne'),
    1882,
    (SELECT id FROM genre WHERE nombre = 'Aventura'),
    240,
    'Dos jóvenes naufragan en una isla desierta y deben sobrevivir usando ingenio y conocimiento, inspirado en Robinson Crusoe.'
);

-- 19. Las 48 leyes del poder
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Las 48 leyes del poder',
    (SELECT id FROM author WHERE Nombre = 'Robert' AND Apellido1 = 'Greene'),
    1998,
    (SELECT id FROM genre WHERE nombre = 'Política'),
    452,
    'Análisis histórico de estrategias de poder, manipulación y control, con ejemplos de figuras históricas.'
);

-- 20. Por fin libres
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Por fin libres',
    (SELECT id FROM author WHERE Nombre = 'Daniel' AND Apellido1 = 'Greenberg'),
    1987,
    (SELECT id FROM genre WHERE nombre = 'Religión'),
    174,
    'Libro cristiano sobre libertad espiritual y vivir según principios bíblicos.'
);

-- 21. La isla del tesoro
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La isla del tesoro',
    (SELECT id FROM author WHERE Nombre = 'Robert Louis' AND Apellido1 = 'Stevenson'),
    1883,
    (SELECT id FROM genre WHERE nombre = 'Aventura'),
    304,
    'Jim Hawkins se une a una tripulación en busca de un tesoro pirata, enfrentándose al traicionero Long John Silver.'
);

-- 22. La metamorfosis
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La metamorfosis',
    (SELECT id FROM author WHERE Nombre = 'Franz' AND Apellido1 = 'Kafka'),
    1915,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    80,
    'Gregor Samsa despierta convertido en un insecto gigante, explorando alienación, absurdo existencial y rechazo familiar.'
);

-- 23. Las nieves del Kilimanjaro
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Las nieves del Kilimanjaro',
    (SELECT id FROM author WHERE Nombre = 'Ernest' AND Apellido1 = 'Hemingway'),
    1936,
    (SELECT id FROM genre WHERE nombre = 'Cuentos'),
    150,
    'Recopilación de cuentos, incluido el famoso sobre un escritor moribundo en África que reflexiona sobre su vida.'
);

-- 24. Meditaciones de Marco Aurelio
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Meditaciones de Marco Aurelio',
    (SELECT id FROM author WHERE Nombre = 'Marco Aurelio' AND Apellido1 = 'Antonino'),
    180,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    256,
    'Reflexiones personales del emperador romano sobre virtud, resiliencia, muerte y vivir conforme a la naturaleza.'
);

-- 25. Memorias del subsuelo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Memorias del subsuelo',
    (SELECT id FROM author WHERE Nombre = 'Fyodor' AND Apellido1 = 'Dostoyevsky'),
    1864,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    150,
    'Monólogo de un amargado funcionario retirado que critica el racionalismo y defiende la irracionalidad humana.'
);

-- 26. El rayo que no cesa
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El rayo que no cesa',
    (SELECT id FROM author WHERE Nombre = 'Miguel' AND Apellido1 = 'Hernández'),
    1936,
    (SELECT id FROM genre WHERE nombre = 'Poesía'),
    120,
    'Poemas de amor, dolor y protesta social del poeta español, escrito durante la Guerra Civil.'
);

-- 27. ¿Quién se ha llevado mi queso?
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '¿Quién se ha llevado mi queso?',
    (SELECT id FROM author WHERE Nombre = 'Spencer' AND Apellido1 = 'Johnson'),
    1998,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    96,
    'Alegoría sobre el cambio: cuatro personajes buscan "queso" (metáfora de metas) en un laberinto, adaptándose o resistiendo.'
);

-- 28. Rashomon y otros cuentos
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Rashomon y otros cuentos',
    (SELECT id FROM author WHERE Nombre = 'Ryunosuke' AND Apellido1 = 'Akutagawa'),
    1915,
    (SELECT id FROM genre WHERE nombre = 'Cuentos'),
    200,
    'Cuentos que exploran la naturaleza humana, verdad subjetiva y moralidad, incluyendo "En el bosque" (base de Rashomon).'
);

-- 29. Sobre la brevedad de la vida
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Sobre la brevedad de la vida',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    49,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    80,
    'Ensayo que argumenta que la vida es suficientemente larga si se vive sabiamente, criticando el desperdicio del tiempo.'
);

-- 30. Sobre la felicidad
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Sobre la felicidad',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    58,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    90,
    'Exposición estoica sobre cómo alcanzar la felicidad mediante la virtud, el control de deseos y la aceptación del destino.'
);

-- 31. Tokyo Blues
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Tokyo Blues',
    (SELECT id FROM author WHERE Nombre = 'Haruki' AND Apellido1 = 'Murakami'),
    1987,
    (SELECT id FROM genre WHERE nombre = 'Coming-of-age'),
    400,
    'Un hombre de 37 años recuerda su juventud en Tokio, amores perdidos y la búsqueda de identidad en los años 60.'
);

-- 32. Vida de un loco. Akutagawa
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Vida de un loco. Akutagawa',
    (SELECT id FROM author WHERE Nombre = 'Ryunosuke' AND Apellido1 = 'Akutagawa'),
    1927,
    (SELECT id FROM genre WHERE nombre = 'Autobiografía'),
    150,
    'Cuentos autobiográficos que exploran la enfermedad mental, el aislamiento y la creatividad del autor.'
);

-- 33. Amazonia-China
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Amazonia-China',
    (SELECT id FROM author WHERE Nombre = 'Óscar' AND Apellido1 = 'Calavia'),
    2012,
    (SELECT id FROM genre WHERE nombre = 'Ensayo'),
    144,
    ''
);

-- 34. Un secuestro de película
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Un secuestro de película',
    (SELECT id FROM author WHERE Nombre = 'Enrique' AND Apellido1 = 'Páez'),
    1995,
    (SELECT id FROM genre WHERE nombre = 'Infantil'),
    128,
    ''
);

-- 35. Eclipse de sol
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Eclipse de sol',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Lijanov'),
    1977,
    (SELECT id FROM genre WHERE nombre = 'Infantil'),
    157,
    ''
);

-- 36. Tao te Ching
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Tao te Ching',
    (SELECT id FROM author WHERE Nombre = 'Lao' AND Apellido1 = 'Tse'),
    -300,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    81,
    'Texto fundamental del taoísmo sobre armonía con el Tao (camino), virtud del no actuar y simplicidad.'
);

-- 37. El discreto
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El discreto',
    (SELECT id FROM author WHERE Nombre = 'Baltasar' AND Apellido1 = 'Gracián'),
    1646,
    (SELECT id FROM genre WHERE nombre = 'Aforismos'),
    200,
    'Manual sobre discreción, prudencia y conducta inteligente para destacar en sociedad sin provocar envidia.'
);

-- 38. 7 hábitos de la gente altamente efectiva
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '7 hábitos de la gente altamente efectiva',
    (SELECT id FROM author WHERE Nombre = 'Stephen Richards' AND Apellido1 = 'Covey'),
    1989,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    432,
    'Presenta siete principios para el desarrollo personal y profesional basados en la ética del carácter.'
);

-- 39. De la firmeza del sabio
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'De la firmeza del sabio',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    55,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    70,
    'Ensaje sobre la imperturbabilidad del sabio estoico frente a la adversidad y las opiniones ajenas.'
);

-- 40. El arte de tener la razón
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El arte de tener la razón',
    (SELECT id FROM author WHERE Nombre = 'Arthur' AND Apellido1 = 'Schopenhauer'),
    1831,
    (SELECT id FROM genre WHERE nombre = 'Retórica'),
    150,
    'Tratado sobre dialéctica erística: 38 estratagemas para ganar debates, independientemente de la verdad.'
);

-- 41. De la ira
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'De la ira',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    45,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    120,
    'Análisis de la ira como pasión destructiva y cómo el sabio debe evitarla mediante la razón y el autocontrol.'
);

-- 42. El hombre en busca de sentido
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El hombre en busca de sentido',
    (SELECT id FROM author WHERE Nombre = 'Viktor' AND Apellido1 = 'Frankl'),
    1946,
    (SELECT id FROM genre WHERE nombre = 'Psicología'),
    165,
    'Psiquiatra sobrevive a campos de concentración y desarrolla la logoterapia: la búsqueda de sentido como fuerza motriz.'
);

-- 43. El poder de las tinieblas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El poder de las tinieblas',
    (SELECT id FROM author WHERE Nombre = 'León' AND Apellido1 = 'Tolstói'),
    1886,
    (SELECT id FROM genre WHERE nombre = 'Teatro'),
    120,
    'Drama rural sobre corrupción moral, adulterio y asesinato en la Rusia campesina del siglo XIX.'
);

-- 44. El príncipe
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El príncipe',
    (SELECT id FROM author WHERE Nombre = 'Nicolás' AND Apellido1 = 'Maquiavelo'),
    1532,
    (SELECT id FROM genre WHERE nombre = 'Política'),
    140,
    'Tratado político sobre cómo obtener y mantener el poder, pragmático y amoral, dirigido a un gobernante.'
);

-- 45. Relato de un náufrago
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Relato de un náufrago',
    (SELECT id FROM author WHERE Nombre = 'Gabriel' AND Apellido1 = 'García' AND Apellido2 = 'Márquez'),
    1970,
    (SELECT id FROM genre WHERE nombre = 'Reportaje'),
    142,
    'Historia real de un marinero colombiano que sobrevivió 10 días a la deriva, crítica velada a la dictadura.'
);

-- 46. Así habló Zarathustra
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Así habló Zarathustra',
    (SELECT id FROM author WHERE Nombre = 'Friedrich' AND Apellido1 = 'Nietzsche'),
    1883,
    (SELECT id FROM genre WHERE nombre = 'Poético'),
    352,
    'Obra filosófica-poética que introduce conceptos como el Superhombre, la muerte de Dios y el eterno retorno.'
);

-- 47. Lord Edgware Dies
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Lord Edgware Dies',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1933,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    256,
    'Hercule Poirot investiga el asesinato de Lord Edgware, cuya esposa, una actriz, había declarado públicamente su deseo de matarlo.'
);

-- 48. Kafka en la orilla
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Kafka en la orilla',
    (SELECT id FROM author WHERE Nombre = 'Haruki' AND Apellido1 = 'Murakami'),
    2002,
    (SELECT id FROM genre WHERE nombre = 'Realismo mágico'),
    608,
    'Historia paralela de un adolescente fugitivo y un anciano con habilidades especiales, en un Japón lleno de sueños y profecías.'
);

-- 49. Trece cartas a Dios
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Trece cartas a Dios',
    (SELECT id FROM author WHERE Nombre = 'Ricardo' AND Apellido1 = 'Moreno'),
    2015,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    112,
    ''
);

-- 50. De la tranquilidad del ánimo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'De la tranquilidad del ánimo',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    53,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    80,
    'Ensayo sobre cómo mantener la serenidad interior frente a las preocupaciones y el caos exterior.'
);

-- 51. Come comida real
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Come comida real',
    (SELECT id FROM author WHERE Nombre = 'Carlos' AND Apellido1 = 'Ríos'),
    2019,
    (SELECT id FROM genre WHERE nombre = 'Alimentación'),
    320,
    ''
);

-- 52. Asesinato en el Orient Express
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Asesinato en el Orient Express',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1934,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    256,
    'Hercule Poirot investiga un asesinato en el famoso tren, donde todos los pasajeros parecen tener motivos y coartadas sospechosas.'
);

-- 53. El sueño
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El sueño',
    (SELECT id FROM author WHERE Nombre = 'Luis María' AND Apellido1 = 'Gonzalo'),
    1991,
    (SELECT id FROM genre WHERE nombre = 'Salud'),
    156,
    ''
);

-- 54. El método Kaizen
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El método Kaizen',
    (SELECT id FROM author WHERE Nombre = 'Robert' AND Apellido1 = 'Maurer'),
    2004,
    (SELECT id FROM genre WHERE nombre = 'Productividad'),
    160,
    'Método de mejora continua mediante pequeños pasos para lograr cambios sostenibles en hábitos y objetivos.'
);

-- 55. El Principito
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El Principito',
    (SELECT id FROM author WHERE Nombre = 'Antoine' AND Apellido1 = 'de Saint-Exupéry'),
    1943,
    (SELECT id FROM genre WHERE nombre = 'Alegórica'),
    96,
    'Un piloto perdido en el desierto conoce a un príncipe de otro planeta, reflexionando sobre amor, amistad y sentido de la vida.'
);

-- 56. Cards on the table
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Cards on the table',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1936,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    272,
    'Hercule Poirot investiga un asesinato durante una partida de bridge, donde los cuatro sospechosos son expertos en el juego.'
);

-- 57. Lógica viva
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Lógica viva',
    (SELECT id FROM author WHERE Nombre = 'Carlos' AND Apellido1 = 'Vaz' AND Apellido2 = 'Ferreira'),
    1945,
    (SELECT id FROM genre WHERE nombre = 'Lógica'),
    291,
    ''
);

-- 58. Cómo ser un estoico
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Cómo ser un estoico',
    (SELECT id FROM author WHERE Nombre = 'Massimo' AND Apellido1 = 'Pigliucci'),
    2017,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    288,
    'Introducción práctica al estoicismo usando la figura de Epicteto, aplicando sus enseñanzas a la vida moderna.'
);

-- 59. El reino de Kensuke
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El reino de Kensuke',
    (SELECT id FROM author WHERE Nombre = 'Michael' AND Apellido1 = 'Morpurgo'),
    1999,
    (SELECT id FROM genre WHERE nombre = 'Aventura'),
    176,
    'Un niño naufraga en una isla del Pacífico donde vive un anciano japonés misterioso, explorando amistad y supervivencia.'
);

-- 60. Príncipe y mendigo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Príncipe y mendigo',
    (SELECT id FROM author WHERE Nombre = 'Mark' AND Apellido1 = 'Twain'),
    1881,
    (SELECT id FROM genre WHERE nombre = 'Novela histórica'),
    240,
    'Un príncipe y un mendigo idénticos intercambian roles, satirizando las desigualdades sociales de la Inglaterra Tudor.'
);

-- 61. El cuerpo femenino
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El cuerpo femenino',
    (SELECT id FROM author WHERE Nombre = 'Anne' AND Apellido1 = 'de Kervasdoue'),
    1993,
    (SELECT id FROM genre WHERE nombre = 'Salud'),
    96,
    ''
);

-- 62. La vuelta al mundo en 80 días
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La vuelta al mundo en 80 días',
    (SELECT id FROM author WHERE Nombre = 'Jules' AND Apellido1 = 'Verne'),
    1873,
    (SELECT id FROM genre WHERE nombre = 'Aventura'),
    320,
    'Phileas Fogg apuesta que puede circunnavegar el globo en 80 días, enfrentando obstáculos con su fiel mayordomo.'
);

-- 63. De la vida bienaventurada
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'De la vida bienaventurada',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    58,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    90,
    'Ensayo sobre la verdadera felicidad, que reside en la virtud y la razón, no en placeres o riquezas externas.'
);

-- 64. En el enjambre
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'En el enjambre',
    (SELECT id FROM author WHERE Nombre = 'Byung-Chul' AND Apellido1 = 'Han'),
    2013,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    120,
    'Análisis de la sociedad digital y las redes sociales, donde la hiperconexión genera aislamiento y pérdida de privacidad.'
);

-- 65. La sociedad de la transparencia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La sociedad de la transparencia',
    (SELECT id FROM author WHERE Nombre = 'Byung-Chul' AND Apellido1 = 'Han'),
    2012,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    100,
    'Crítica a la obsesión contemporánea por la transparencia, que elimina el misterio y favorece el control.'
);

-- 66. La sociedad del cansancio
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La sociedad del cansancio',
    (SELECT id FROM author WHERE Nombre = 'Byung-Chul' AND Apellido1 = 'Han'),
    2010,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    80,
    'Describe la sociedad actual como víctima de un cansancio autoinfligido por la autoexplotación y el rendimiento.'
);

-- 67. 1Q84 (libros 1 y 2)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '1Q84 (libros 1 y 2)',
    (SELECT id FROM author WHERE Nombre = 'Haruki' AND Apellido1 = 'Murakami'),
    2009,
    (SELECT id FROM genre WHERE nombre = 'Realismo mágico'),
    928,
    'Una instructora de gimnasia y un aspirante a escritor se entrelazan en un mundo paralelo llamado 1Q84, con misterios y cultos.'
);

-- 68. 1Q84 (libro 3)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '1Q84 (libro 3)',
    (SELECT id FROM author WHERE Nombre = 'Haruki' AND Apellido1 = 'Murakami'),
    2010,
    (SELECT id FROM genre WHERE nombre = 'Realismo mágico'),
    464,
    'Conclusión de la épica historia, donde los destinos de Aomame y Tengo convergen en un enfrentamiento final.'
);

-- 69. Hasta que la muerte nos separe
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Hasta que la muerte nos separe',
    (SELECT id FROM author WHERE Nombre = 'Antonio' AND Apellido1 = 'Martos'),
    2009,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    190,
    ''
);

-- 70. 10 razones para borrar tus redes sociales de inmediato
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '10 razones para borrar tus redes sociales de inmediato',
    (SELECT id FROM author WHERE Nombre = 'Jaron' AND Apellido1 = 'Lanier'),
    2018,
    (SELECT id FROM genre WHERE nombre = 'Tecnología'),
    160,
    'Argumentos sobre los efectos negativos de las redes en la democracia, economía y salud mental.'
);

-- 71. El asesinato de Roger Ackroyd
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El asesinato de Roger Ackroyd',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1926,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    320,
    'Hercule Poirot investiga el asesinato de un rico viudo en un pueblo inglés, con un famoso giro narrativo final.'
);

-- 72. Ideas y creencias
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Ideas y creencias',
    (SELECT id FROM author WHERE Nombre = 'José' AND Apellido1 = 'Ortega y Gasset'),
    1940,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    200,
    'Distinción entre ideas (pensamientos elaborados) y creencias (fondos inconscientes que sostienen nuestra vida).'
);

-- 73. Padre rico padre pobre
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Padre rico padre pobre',
    (SELECT id FROM author WHERE Nombre = 'Robert' AND Apellido1 = 'Kiyosaki'),
    1997,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    336,
    'Contraste entre dos mentalidades financieras: la de un padre tradicional y la de un padre emprendedor, enfatizando educación financiera.'
);

-- 74. El millonario de la puerta de al lado
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El millonario de la puerta de al lado',
    (SELECT id FROM author WHERE Nombre = 'Thomas J.' AND Apellido1 = 'Stanley'),
    1996,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    258,
    'Estudio de hábitos de millonarios estadounidenses: frugalidad, planificación y evitar apariencias costosas.'
);

-- 75. Los hermanos Karamazov
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Los hermanos Karamazov',
    (SELECT id FROM author WHERE Nombre = 'Fyodor' AND Apellido1 = 'Dostoyevsky'),
    1880,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    796,
    'Explora conflicto entre fe y razón, moral y libertad, a través de la historia de un parricidio en una familia rusa.'
);

-- 76. La caída de la casa de Usher (y otros)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La caída de la casa de Usher (y otros)',
    (SELECT id FROM author WHERE Nombre = 'Edgar Allan' AND Apellido1 = 'Poe'),
    1839,
    (SELECT id FROM genre WHERE nombre = 'Terror'),
    150,
    'Colección de cuentos góticos que incluye el famoso sobre una familia maldita y una casa que se desmorona.'
);

-- 77. Tiempos líquidos
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Tiempos líquidos',
    (SELECT id FROM author WHERE Nombre = 'Zygmunt' AND Apellido1 = 'Bauman'),
    2007,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    150,
    'Análisis de la modernidad líquida: relaciones, miedo e identidad en un mundo globalizado, cambiante e incierto.'
);

-- 78. Minimalismo digital
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Minimalismo digital',
    (SELECT id FROM author WHERE Nombre = 'Cal' AND Apellido1 = 'Newport'),
    2019,
    (SELECT id FROM genre WHERE nombre = 'Tecnología'),
    304,
    'Propone reducir el uso de tecnología digital a lo esencial para recuperar concentración y bienestar.'
);

-- 79. 1984
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '1984',
    (SELECT id FROM author WHERE Nombre = 'George' AND Apellido1 = 'Orwell'),
    1949,
    (SELECT id FROM genre WHERE nombre = 'Distopía'),
    328,
    'En un estado totalitario, Winston Smith desafía al sistema de vigilancia y control del Gran Hermano.'
);

-- 80. Rebelión en la granja
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Rebelión en la granja',
    (SELECT id FROM author WHERE Nombre = 'George' AND Apellido1 = 'Orwell'),
    1945,
    (SELECT id FROM genre WHERE nombre = 'Sátira'),
    112,
    'Los animales de una granja se rebelan contra los humanos, pero la revolución degenera en una nueva tiranía.'
);
    
-- 81. El señor de las moscas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El señor de las moscas',
    (SELECT id FROM author WHERE Nombre = 'William' AND Apellido1 = 'Golding'),
    1954,
    (SELECT id FROM genre WHERE nombre = 'Alegórica'),
    224,
    'Un grupo de niños náufragos intenta organizarse, pero descienden a la barbarie y la violencia tribal.'
);

-- 82. Vida líquida
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Vida líquida',
    (SELECT id FROM author WHERE Nombre = 'Zygmunt' AND Apellido1 = 'Bauman'),
    2005,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    160,
    'Describe la fragilidad de los vínculos humanos en la modernidad líquida, donde todo es provisional y desechable.'
);

-- 83. El concepto de la angustia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El concepto de la angustia',
    (SELECT id FROM author WHERE Nombre = 'Søren' AND Apellido1 = 'Kierkegaard'),
    1844,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    200,
    'Análisis de la angustia como experiencia fundamental humana ligada a la libertad y posibilidad.'
);

-- 84. El ego es el enemigo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El ego es el enemigo',
    (SELECT id FROM author WHERE Nombre = 'Ryan' AND Apellido1 = 'Holiday'),
    2016,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    226,
    'Argumenta que el ego obstaculiza el éxito y propone cultivar humildad, disciplina y resiliencia.'
);

-- 85. Viaje al centro de la tierra
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Viaje al centro de la tierra',
    (SELECT id FROM author WHERE Nombre = 'Jules' AND Apellido1 = 'Verne'),
    1864,
    (SELECT id FROM genre WHERE nombre = 'Ciencia ficción'),
    400,
    'Un profesor y su sobrino descienden por un volcán hacia el interior de la Tierra, descubriendo mundos prehistóricos.'
);

-- 86. El obstáculo es el camino
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El obstáculo es el camino',
    (SELECT id FROM author WHERE Nombre = 'Ryan' AND Apellido1 = 'Holiday'),
    2014,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    224,
    'Usa la filosofía estoica para mostrar cómo convertir obstáculos en oportunidades de crecimiento y éxito.'
);

-- 87. El mito de Sísifo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El mito de Sísifo',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Camus'),
    1942,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    160,
    'Ensayo sobre el absurdo de la existencia y la respuesta de la rebelión, simbolizada por Sísifo condenado a empujar una roca eternamente.'
);

-- 88. El extranjero
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El extranjero',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Camus'),
    1942,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    123,
    'Meursault, indiferente ante la muerte de su madre y un asesinato, enfrenta juicio y existencia absurda.'
);

-- 89. El huésped (de Albert Camus)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El huésped',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Camus'),
    1957,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    864,
    'Un maestro en una remota escuela argelina debe escoltar a un prisionero árabe, enfrentando dilemas morales y políticos.'
);

-- 90. El misterioso caso de Styles
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El misterioso caso de Styles',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1920,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    256,
    'Primera aparición de Hercule Poirot, investigando el envenenamiento de la rica señora Inglethorp en una mansión.'
);

-- 91. La muerte feliz
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La muerte feliz',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Camus'),
    1971,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    200,
    'Primera novela de Camus sobre un hombre que busca la felicidad a través del asesinato y el dinero, temas de absurdo.'
);

-- 92. No-cosas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'No-cosas',
    (SELECT id FROM author WHERE Nombre = 'Byung-Chul' AND Apellido1 = 'Han'),
    2021,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    120,
    'Crítica a la digitalización que reemplaza las cosas físicas por información, vaciando la realidad y las relaciones.'
);

-- 93. Salud salvaje
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Salud salvaje',
    (SELECT id FROM author WHERE Nombre = 'Marcos' AND Apellido1 = 'Vázquez'),
    2018,
    (SELECT id FROM genre WHERE nombre = 'Salud'),
    264,
    ''
);

-- 94. Si esto es un hombre
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Si esto es un hombre',
    (SELECT id FROM author WHERE Nombre = 'Primo' AND Apellido1 = 'Levi'),
    1947,
    (SELECT id FROM genre WHERE nombre = 'Historia'),
    200,
    'Testimonio del autor como superviviente de Auschwitz, reflexionando sobre la deshumanización en los campos.'
);

-- 95. Un mundo feliz
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Un mundo feliz',
    (SELECT id FROM author WHERE Nombre = 'Aldous' AND Apellido1 = 'Huxley'),
    1932,
    (SELECT id FROM genre WHERE nombre = 'Ciencia ficción'),
    268,
    'En una sociedad futura donde la estabilidad se logra mediante condicionamiento, drogas y eliminación de emociones fuertes.'
);

-- 96. Trafalgar (B.P. Galdós)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Trafalgar (B.P. Galdós)',
    (SELECT id FROM author WHERE Nombre = 'Benito' AND Apellido1 = 'Pérez' AND Apellido2 = 'Galdós'),
    1873,
    (SELECT id FROM genre WHERE nombre = 'Novela histórica'),
    250,
    'Primer episodio nacional, recrea la batalla de Trafalgar desde la perspectiva de un niño testigo.'
);

-- 97. Nociones elementales de ajedrez
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Nociones elementales de ajedrez',
    (SELECT id FROM author WHERE Nombre = 'José Raúl' AND Apellido1 = 'Capablanca'),
    1923,
    (SELECT id FROM genre WHERE nombre = 'Divulgación'),
    160,
    ''
);

-- 98. Muerte en el Nilo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Muerte en el Nilo',
    (SELECT id FROM author WHERE Nombre = 'Agatha' AND Apellido1 = 'Christie'),
    1937,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    344,
    'Hercule Poirot investiga el asesinato de una heredera durante un crucero por el Nilo.'
);

-- 99. De la providencia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'De la providencia',
    (SELECT id FROM author WHERE Nombre = 'Lucio Anneo' AND Apellido1 = 'Séneca'),
    64,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    60,
    'Ensayo que defiende que los males son pruebas del destino para fortalecer al sabio, no castigos injustos.'
);

-- 100. Antifragil
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Antifragil',
    (SELECT id FROM author WHERE Nombre = 'Nassim Nicholas' AND Apellido1 = 'Taleb'),
    2012,
    (SELECT id FROM genre WHERE nombre = 'Economía'),
    544,
    'Concepto de sistemas que se benefician del estrés y el desorden (antifrágiles), más allá de la resiliencia.'
);

-- 101. Pierre et Jean
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Pierre et Jean',
    (SELECT id FROM author WHERE Nombre = 'Guy' AND Apellido1 = 'de Maupassant'),
    1888,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    224,
    'Dos hermanos descubren que uno es hijo ilegítimo, explorando celos, herencia y relaciones familiares en la burguesía francesa.'
);

-- 102. La revolución de la inteligencia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La revolución de la inteligencia',
    (SELECT id FROM author WHERE Nombre = 'Luis Alberto' AND Apellido1 = 'Machado'),
    1975,
    (SELECT id FROM genre WHERE nombre = 'Pedagogía'),
    159,
    ''
);

-- 103. El camino de la soledad
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El camino de la soledad',
    (SELECT id FROM author WHERE Nombre = 'Musashi' AND Apellido1 = 'Miyamoto'),
    1645,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    1,
    ''
);

-- 104. Your brain on porn
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Your brain on porn',
    (SELECT id FROM author WHERE Nombre = 'Gary' AND Apellido1 = 'Wilson'),
    2014,
    (SELECT id FROM genre WHERE nombre = 'Salud'),
    250,
    'Explica los efectos neurológicos de la pornografía en internet, argumentando que puede crear adicción y dañar la sexualidad.'
);

-- 105. La comunicación no verbal
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La comunicación no verbal',
    (SELECT id FROM author WHERE Nombre = 'Flora' AND Apellido1 = 'Davis'),
    1973,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    320,
    ''
);

-- 106. The brain that changes itself
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'The brain that changes itself',
    (SELECT id FROM author WHERE Nombre = 'Norman' AND Apellido1 = 'Doidge'),
    2007,
    (SELECT id FROM genre WHERE nombre = 'Ciencia'),
    440,
    'Presenta casos de neuroplasticidad: el cerebro puede reorganizarse y recuperarse de daños o cambiar hábitos.'
);

-- 107. Une bouteille dans la mer de Gaza
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Une bouteille dans la mer de Gaza',
    (SELECT id FROM author WHERE Nombre = 'Valérie' AND Apellido1 = 'Zenatti'),
    2005,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    192,
    'Una joven israelí y un palestino intercambian emails tras lanzar una botella al mar, explorando conflicto y humanidad.'
);

-- 108. Un avventura di viaggio
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Un avventura di viaggio',
    (SELECT id FROM author WHERE Nombre = 'Marcel' AND Apellido1 = 'Brion'),
    1966,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    15,
    ''
);

-- 109. Martin Eden
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Martin Eden',
    (SELECT id FROM author WHERE Nombre = 'Jack' AND Apellido1 = 'London'),
    1909,
    (SELECT id FROM genre WHERE nombre = 'Autobiografía'),
    400,
    'Un marinero sin educación se esfuerza por convertirse en escritor y ganar el amor de una mujer de clase alta, crítica al sueño americano.'
);

-- 110. La Presentación de la persona en la vida cotidiana
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La Presentación de la persona en la vida cotidiana',
    (SELECT id FROM author WHERE Nombre = 'Erving' AND Apellido1 = 'Goffman'),
    1956,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    272,
    'Teoría de la interacción social como una representación teatral, donde gestionamos impresiones en distintos escenarios.'
);

-- 111. Invierte en ti
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Invierte en ti',
    (SELECT id FROM author WHERE Nombre = 'Natalia' AND Apellido1 = 'de Santiago'),
    2021,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    240,
    ''
);

-- 112. La caída (Albert Camus)
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La caída',
    (SELECT id FROM author WHERE Nombre = 'Albert' AND Apellido1 = 'Camus'),
    1956,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    147,
    'Un ex abogado confiesa su hipocresía y culpa en un bar de Ámsterdam, explorando la condición humana y la moralidad.'
);

-- 113. El anticristo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El anticristo',
    (SELECT id FROM author WHERE Nombre = 'Friedrich' AND Apellido1 = 'Nietzsche'),
    1895,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    100,
    'Ataque feroz al cristianismo como religión de la debilidad y resentimiento, que niega los valores vitales.'
);

-- 114. Dos conceptos de libertad
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Dos conceptos de libertad',
    (SELECT id FROM author WHERE Nombre = 'Isaiah' AND Apellido1 = 'Berlin'),
    1958,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    100,
    'Distinción entre libertad negativa (ausencia de interferencia) y positiva (capacidad de autogobierno).'
);

-- 115. El hombre más rico de babilonia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El hombre más rico de babilonia',
    (SELECT id FROM author WHERE Nombre = 'George S.' AND Apellido1 = 'Clason'),
    1926,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    144,
    'Parábolas ambientadas en la antigua Babilonia que enseñan principios de ahorro, inversión y riqueza.'
);

-- 116. Hable menos y actúe más
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Hable menos y actúe más',
    (SELECT id FROM author WHERE Nombre = 'Brian' AND Apellido1 = 'Tracy'),
    2017,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    120,
    ''
);

-- 117. La economía en una lección
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La economía en una lección',
    (SELECT id FROM author WHERE Nombre = 'Henry' AND Apellido1 = 'Hazlitt'),
    1946,
    (SELECT id FROM genre WHERE nombre = 'Economía'),
    218,
    'Exposición de principios económicos básicos, enfatizando las consecuencias no intencionadas de las políticas a largo plazo.'
);

-- 118. La biblia del vendedor
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La biblia del vendedor',
    (SELECT id FROM author WHERE Nombre = 'Alex' AND Apellido1 = 'Dey'),
    1998,
    (SELECT id FROM genre WHERE nombre = 'Ventas'),
    250,
    'Principios y técnicas de ventas, comunicación y persuasión para alcanzar el éxito en el ámbito comercial.'
);

-- 119. El arte de la buena vida
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El arte de la buena vida',
    (SELECT id FROM author WHERE Nombre = 'Willam B.' AND Apellido1 = 'Irvine'),
    2017,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    368,
    ''
);

-- 120. Los 10 principios básicos del orden político liberal
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Los 10 principios básicos del orden político liberal',
    (SELECT id FROM author WHERE Nombre = 'Juan Ramón' AND Apellido1 = 'Rallo'),
    2019,
    (SELECT id FROM genre WHERE nombre = 'Política'),
    272,
    ''
);

-- 121. Los 4 acuerdos
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Los 4 acuerdos',
    (SELECT id FROM author WHERE Nombre = 'Miguel' AND Apellido1 = 'Ruiz'),
    1997,
    (SELECT id FROM genre WHERE nombre = 'Espiritualidad'),
    160,
    'Basado en sabiduría tolteca: cuatro principios para la libertad personal (sé impecable con tu palabra, no tomes nada personal, etc.).'
);

-- 122. Ten peor coche que tu vecino
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Ten peor coche que tu vecino',
    (SELECT id FROM author WHERE Nombre = 'Luis' AND Apellido1 = 'Pita'),
    2021,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    176,
    ''
);

-- 123. El gen egoísta
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El gen egoísta',
    (SELECT id FROM author WHERE Nombre = 'Richard' AND Apellido1 = 'Dawkins'),
    1976,
    (SELECT id FROM genre WHERE nombre = 'Ciencia'),
    360,
    'Expone la teoría del gen como unidad de selección natural, donde los organismos son vehículos para genes "egoístas".'
);

-- 124. Una habitación propia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Una habitación propia',
    (SELECT id FROM author WHERE Nombre = 'Virginia' AND Apellido1 = 'Woolf'),
    1929,
    (SELECT id FROM genre WHERE nombre = 'Feminismo'),
    172,
    'Reflexión sobre la necesidad de independencia económica y espacio físico para que las mujeres escriban literatura.'
);

-- 125. La rebelión de las masas
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La rebelión de las masas',
    (SELECT id FROM author WHERE Nombre = 'José' AND Apellido1 = 'Ortega y Gasset'),
    1930,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    250,
    'Análisis de la sociedad de masas y el hombre-masa que actúa sin criterio propio, amenazando la excelencia cultural.'
);

-- 126. Falacias de la justicia social
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Falacias de la justicia social',
    (SELECT id FROM author WHERE Nombre = 'Thomas' AND Apellido1 = 'Sowell'),
    2024,
    (SELECT id FROM genre WHERE nombre = 'Sociología'),
    208,
    ''
);

-- 127. Tratado sobre la tolerancia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Tratado sobre la tolerancia',
    (SELECT id FROM author WHERE Nombre = 'Voltaire'),
    1763,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    150,
    'Defensa de la tolerancia religiosa y crítica al fanatismo, inspirado por el caso de ejecución injusta de Jean Calas.'
);

-- 128. El mito de la monogamia
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El mito de la monogamia',
    (SELECT id FROM author WHERE Nombre = 'David Phillip' AND Apellido1 = 'Barash'),
    2003,
    (SELECT id FROM genre WHERE nombre = 'Ensayo'),
    391,
    ''
);

-- 129. Seis lecciones sobre el capitalismo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Seis lecciones sobre el capitalismo',
    (SELECT id FROM author WHERE Nombre = 'Ludwig' AND Apellido1 = 'Von Mises'),
    1959,
    (SELECT id FROM genre WHERE nombre = 'Economía'),
    128,
    ''
);

-- 130. El hombre contra el estado
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El hombre contra el estado',
    (SELECT id FROM author WHERE Nombre = 'Herbert' AND Apellido1 = 'Spencer'),
    1884,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    200,
    'Defensa del liberalismo clásico y crítica al crecimiento del estado intervencionista, que amenaza la libertad individual.'
);

-- 131. Walden
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Walden',
    (SELECT id FROM author WHERE Nombre = 'Henry David' AND Apellido1 = 'Thoreau'),
    1854,
    (SELECT id FROM genre WHERE nombre = 'Ensayo'),
    320,
    'Reflexión sobre vida simple en la naturaleza, autosuficiencia y crítica a la sociedad industrial y consumista.'
);

-- 132. Caminar
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Caminar',
    (SELECT id FROM author WHERE Nombre = 'Henry David' AND Apellido1 = 'Thoreau'),
    1862,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    50,
    'Ensaje que celebra el acto de caminar como conexión con la naturaleza y rebelión contra las convenciones sociales.'
);

-- 133. El crepúsculo de los ídolos
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El crepúsculo de los ídolos',
    (SELECT id FROM author WHERE Nombre = 'Friedrich' AND Apellido1 = 'Nietzsche'),
    1889,
    (SELECT id FROM genre WHERE nombre = 'Filosofía'),
    160,
    'Ataque a las "ídolos" de la cultura occidental (Sócrates, cristianismo, moral tradicional) como decadentes.'
);

-- 134. El economista callejero
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El economista callejero',
    (SELECT id FROM author WHERE Nombre = 'Axel' AND Apellido1 = 'Kaiser'),
    2022,
    (SELECT id FROM genre WHERE nombre = 'Economía'),
    144,
    ''
);

-- 135. Temor y temblor
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Temor y temblor',
    (SELECT id FROM author WHERE Nombre = 'Søren' AND Apellido1 = 'Kierkegaard'),
    1843,
    (SELECT id FROM genre WHERE nombre = 'Religión'),
    150,
    'Reflexión sobre la fe a través de la historia de Abraham e Isaac, explorando la paradoja de la relación individual con Dios.'
);

-- 136. La ciencia del sexo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La ciencia del sexo',
    (SELECT id FROM author WHERE Nombre = 'Pére' AND Apellido1 = 'Stupinya'),
    2023,
    (SELECT id FROM genre WHERE nombre = 'Ciencia'),
    288,
    ''
);

-- 137. ¿Qué hago con mi vida?
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    '¿Qué hago con mi vida?',
    (SELECT id FROM author WHERE Nombre = 'Po' AND Apellido1 = 'Bronson'),
    2002,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    400,
    'Historias reales de personas que buscaron sentido y cambio en sus carreras y vidas, motivando al lector a reflexionar.'
);

-- 138. Discriminación y disparidades
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Discriminación y disparidades',
    (SELECT id FROM author WHERE Nombre = 'Thomas' AND Apellido1 = 'Sowell'),
    2018,
    (SELECT id FROM genre WHERE nombre = 'Economía'),
    192,
    'Análisis de diferencias económicas entre grupos, argumentando que no todas se deben a discriminación sino a múltiples factores.'
);

-- 139. El sabueso de los Baskerville
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'El sabueso de los Baskerville',
    (SELECT id FROM author WHERE Nombre = 'Arthur Conan' AND Apellido1 = 'Doyle'),
    1902,
    (SELECT id FROM genre WHERE nombre = 'Misterio'),
    256,
    'Sherlock Holmes investiga la misteriosa muerte de un baronet, aparentemente relacionada con una leyenda de un perro infernal.'
);

-- 140. Angeli
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Angeli',
    (SELECT id FROM author WHERE Nombre = 'Maurizio' AND Apellido1 = 'de Giovani'),
    2022,
    (SELECT id FROM genre WHERE nombre = 'Novela'),
    280,
    ''
);

-- 141. Invierte con poco
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Invierte con poco',
    (SELECT id FROM author WHERE Nombre = 'Natalia' AND Apellido1 = 'de Santiago'),
    2022,
    (SELECT id FROM genre WHERE nombre = 'Finanzas'),
    320,
    ''
);

-- 142. Sapiens: de animales a dioses
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Sapiens: de animales a dioses',
    (SELECT id FROM author WHERE Nombre = 'Yuval Noah' AND Apellido1 = 'Harari'),
    2011,
    (SELECT id FROM genre WHERE nombre = 'Historia'),
    496,
    'Recorrido por la historia humana desde la evolución hasta el presente, examinando revoluciones cognitiva, agrícola y científica.'
);

-- 143. Basta ya de ser un Tipo Lindo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'Basta ya de ser un Tipo Lindo',
    (SELECT id FROM author WHERE Nombre = 'Robert' AND Apellido1 = 'Glover'),
    2021,
    (SELECT id FROM genre WHERE nombre = 'Autoayuda'),
    229,
    ''
);

-- 144. La energía nuclear salvará el mundo
INSERT INTO libros (title, author_id, year_publicacion, genre_id, pages, description) 
VALUES (
    'La energía nuclear salvará el mundo',
    (SELECT id FROM author WHERE Nombre = 'Alfredo' AND Apellido1 = 'García'),
    2021,
    (SELECT id FROM genre WHERE nombre = 'Ensayo'),
    336,
    'Divulgación sobre la seguridad y necesidad de adaptar la energía nuclear como la forma más importante de energía para el mundo.'
);

-- CREANDO TABLAS INTERMEDIAS

CREATE TABLE LibroxAutor (
    libro_id INT UNSIGNED,
    author_id INT UNSIGNED,
    PRIMARY KEY (libro_id, author_id),
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE Libroxgenre (
    libro_id INT UNSIGNED,
    genre_id INT UNSIGNED,
    PRIMARY KEY (libro_id, genre_id),
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
);

INSERT INTO LibroxAutor(libro_id,author_id)
SELECT libros.id, author.id
FROM libros
INNER JOIN author ON libros.author_id = author.id;


INSERT INTO Libroxgenre(libro_id,genre_id)
SELECT libros.id, genre.id
FROM libros
INNER JOIN genre ON libros.genre_id = genre.id;

-- practicando INNER JOIN

SELECT title, nombre, apellido1 FROM libros
INNER JOIN author 
ON libros.author_id = author.id;


SELECT title, author.nombre, author.apellido1, pais.nombre
FROM libros
INNER JOIN author ON libros.author_id = author.id
INNER JOIN pais ON author.pais_id = pais.id;

SELECT title, genre.nombre
FROM libros
INNER JOIN genre ON libros.genre_id = genre.id;

SELECT title, author.nombre, author.apellido1, pais.nombre AS pais, genre.nombre AS género
FROM libros
INNER JOIN author ON libros.author_id = author.id
INNER JOIN pais ON author.pais_id = pais.id
INNER JOIN genre ON libros.genre_id = genre.id;

SELECT title, author.nombre, author.apellido1, genre.nombre AS género, year_publicacion AS publicación
FROM libros
INNER JOIN author ON libros.author_id = author.id
INNER JOIN genre ON libros.genre_id = genre.id
WHERE year_publicacion > 2000;

-- practicando LEFT JOIN

SELECT author.nombre, author.apellido1, libros.title AS titulo, pais.nombre AS pais
FROM author
LEFT JOIN libros ON libros.author_id = author.id
LEFT JOIN pais ON author.pais_id = pais.id;


SELECT author.nombre, author.apellido1, libros.title
FROM author
LEFT JOIN libros ON libros.author_id = author.id
WHERE title IS NULL;


SELECT libros.title AS titulo, genre.nombre AS genero
FROM libros
LEFT JOIN genre ON libros.genre_id = genre.id;


SELECT libros.title AS titulo, author.nombre, pais.nombre AS pais
FROM libros
LEFT JOIN author ON libros.author_id = author.id
LEFT JOIN pais ON author.pais_id = pais.id;

SELECT author.nombre, author.apellido1, libros.title, libros.year_publicacion AS publicacion
FROM author
LEFT JOIN libros ON libros.author_id = author.id
AND libros.year_publicacion > 2000
LEFT JOIN pais ON author.pais_id = pais.id;

-- practicando VISTAS y JOIN
-- Vista que guarda titulo, nombre y primer apellido del autor

CREATE VIEW libros_autores AS
SELECT
l.title as titulo,
a.nombre as nombre_autor,
a.apellido1 as apellido1_autor
FROM libros l
INNER JOIN autor a ON l.author_id = a.id;

-- Vista que guarda cada libro con su género

CREATE VIEW libros_genero AS
SELECT
l.title AS titulo,
g.nombre AS Genero
FROM libros l
INNER JOIN genre g ON l.genre_id = g.id;

-- Vista de catálogo completo

CREATE VIEW catalogo_libros AS
SELECT 
l.title AS titulo,
a.nombre AS nombre,
a.apellido1 AS apellido1,
a.apellido2 AS apellido2,
p.nombre AS pais,
g.nombre AS genero,
l.year_publicacion AS publicacion
FROM libros l
INNER JOIN autor a ON a.id = l.author_id
INNER JOIN pais p ON p.id = a.pais_id
INNER JOIN genre g ON g.id = l.genre_id;

-- Autores con sus libros

CREATE VIEW autores_libros AS
SELECT
a.nombre AS nombre,
a.apellido1 AS apellido1,
l.title AS titulo
FROM autor a
LEFT JOIN libros l ON a.id = l.author_id;

CREATE VIEW autores_sinlibro AS
SELECT
a.nombre AS nombre,
a.apellido1 AS apellido1,
p.nombre AS pais
FROM autor a
LEFT JOIN libros l ON l.author_id = a.id
INNER JOIN pais p ON a.pais_id = p.id
WHERE l.id IS NULL;

-- Libros del siglo XXI

CREATE VIEW librosxxi AS
SELECT
l.title AS titulo,
a.nombre AS nombre,
l.year_publicacion AS publicacion,
g.nombre AS genero
FROM libros l
INNER JOIN autor a ON l.author_id = a.id
INNER JOIN genre g ON g.id = l.genre_id
WHERE l.year_publicacion >=2000;

-- Libros recientes

CREATE VIEW libros_recientes AS
SELECT
l.title AS titulo,
l.year_publicacion AS anio_publicacion
FROM libros l
WHERE l.year_publicacion>=2010;

-- Autores jovenes

CREATE VIEW autores_jovenes AS
SELECT
nombre,
apellido1,
YEAR(CURDATE()) - autor.year_nacimiento AS edad
FROM autor
WHERE YEAR(CURDATE()) - autor.year_nacimiento <= 30;

-- Libros largos

CREATE OR REPLACE VIEW libros_largos AS
SELECT
title AS titulo,
pages AS paginas
FROM libros
WHERE pages > 350;

SELECT * FROM catalogo_libros;


