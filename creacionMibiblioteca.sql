-- ============================================
-- SCRIPT COMPLETO - BASE DE DATOS BIBLIOTECA
-- Estructura con tablas de unión (muchos a muchos)
-- ============================================

-- ============================================
-- 1. CREAR Y SELECCIONAR BASE DE DATOS
-- ============================================
CREATE DATABASE IF NOT EXISTS misLibros_db;
USE misLibros_db;

-- ============================================
-- 2. CREAR TABLAS PRINCIPALES
-- ============================================

-- Tabla de países
CREATE TABLE IF NOT EXISTS pais (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(300) NOT NULL,
    codigo_ISO VARCHAR(3)
);

-- Tabla de géneros
CREATE TABLE IF NOT EXISTS genero (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(300) NOT NULL
);

-- Tabla de autores
CREATE TABLE IF NOT EXISTS autor (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(250) NOT NULL,
    apellido1 VARCHAR(250),
    apellido2 VARCHAR(250),
    year_nacimiento INT,
    year_fallecimiento INT,
    pais_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (pais_id) REFERENCES pais(id)
);

-- Tabla de libros (SIN author_id, SIN genre_id)
CREATE TABLE IF NOT EXISTS libro (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    year_publicacion INT NOT NULL,
    pages INT UNSIGNED NOT NULL,
    description TEXT,
    opinion TEXT
);

-- ============================================
-- 3. CREAR TABLAS DE UNIÓN (MUCHOS A MUCHOS)
-- ============================================

-- Unión libros-autores
CREATE TABLE IF NOT EXISTS autor_libro (
    libro_id INT UNSIGNED NOT NULL,
    autor_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (libro_id, autor_id),
    FOREIGN KEY (libro_id) REFERENCES libro(id) ON DELETE CASCADE,
    FOREIGN KEY (autor_id) REFERENCES autor(id) ON DELETE CASCADE
);

-- Unión libros-géneros
CREATE TABLE IF NOT EXISTS genero_libro (
    libro_id INT UNSIGNED NOT NULL,
    genere_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (libro_id, genere_id),
    FOREIGN KEY (libro_id) REFERENCES libro(id) ON DELETE CASCADE,
    FOREIGN KEY (genere_id) REFERENCES genero(id) ON DELETE CASCADE
);

-- ============================================
-- 4. CREAR ÍNDICES (MEJORAN RENDIMIENTO)
-- ============================================


-- ============================================
-- 5. INSERTAR PAÍSES
-- ============================================

INSERT INTO pais (nombre, codigo_ISO) VALUES
('Reino Unido', 'GBR'),
('Estados Unidos', 'USA'),
('España', 'ESP'),
('Francia', 'FRA'),
('Alemania', 'DEU'),
('Italia', 'ITA'),
('Rusia', 'RUS'),
('Japón', 'JPN'),
('China', 'CHN'),
('Grecia', 'GRC'),
('Colombia', 'COL'),
('Argentina', 'ARG'),
('México', 'MEX'),
('Chile', 'CHL'),
('Perú', 'PER'),
('Austria', 'AUT'),
('Irlanda', 'IRL'),
('Polonia', 'POL'),
('República Checa', 'CZE'),
('Suecia', 'SWE'),
('Noruega', 'NOR'),
('Dinamarca', 'DNK'),
('Países Bajos', 'NLD'),
('Bélgica', 'BEL'),
('Suiza', 'CHE'),
('Portugal', 'PRT'),
('Brasil', 'BRA'),
('Canadá', 'CAN'),
('Australia', 'AUS'),
('Nueva Zelanda', 'NZL'),
('India', 'IND'),
('Corea del Sur', 'KOR'),
('Turquía', 'TUR'),
('Egipto', 'EGY'),
('Israel', 'ISR'),
('Sudáfrica', 'ZAF'),
-- Países faltantes:
('Uruguay', 'URY'),
('Cuba', 'CUB'),
('Líbano', 'LBN'),
('Venezuela', 'VEN'),
('Letonia', 'LVA');
-- ============================================
-- 6. INSERTAR GÉNEROS
-- ============================================

INSERT INTO genero (nombre) VALUES
('Ciencia ficción'),
('Cuentos'),
('Novela'),
('Ciencia'),
('Autoayuda'),
('Infantil'),
('Misterio'),
('Filosofía'),
('Estrategia'),
('Aforismos'),
('Aventura'),
('Coming-of-age'),
('Psicología'),
('Poesía'),
('Política'),
('Religión'),
('Autobiografía'),
('Ensayo'),
('Teatro'),
('Reportaje'),
('Poético'),
('Realismo mágico'),
('Alimentación'),
('Productividad'),
('Alegórica'),
('Lógica'),
('Novela histórica'),
('Salud'),
('Tecnología'),
('Distopía'),
('Sátira'),
('Sociología'),
('Terror'),
('Finanzas'),
('Ventas'),
('Espiritualidad'),
('Feminismo'),
('Economía'),
('Pedagogía'),
('Retórica'),
('Divulgación'),
('Historia'),
('Religión'),
('Sátira'),
('Alegórica');

-- ============================================
-- 7. INSERTAR AUTORES
-- ============================================

INSERT INTO autor (nombre, apellido1, apellido2, year_nacimiento, year_fallecimiento, pais_id) VALUES
('Mary', 'Shelley', NULL, 1797, 1851, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Charles', 'Dickens', NULL, 1812, 1870, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Anónimo', NULL, NULL, 0, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Stephen', 'Hawking', NULL, 1942, 2018, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Robert', 'Fisher', NULL, 1922, 2008, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Edmondo', 'De', 'Amicis', 1846, 1908, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Gabriel', 'García', 'Márquez', 1927, 2014, (SELECT id FROM pais WHERE nombre = 'Colombia')),
('Agatha', 'Christie', NULL, 1890, 1976, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('René', 'Descartes', NULL, 1596, 1650, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Sun', 'Tzu', NULL, -544, -496, (SELECT id FROM pais WHERE nombre = 'China')),
('Baltasar', 'Gracián', NULL, 1601, 1658, (SELECT id FROM pais WHERE nombre = 'España')),
('Joseph', 'Conrad', NULL, 1857, 1924, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Jerome David', 'Salinger', NULL, 1919, 2010, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Sigmund', 'Freud', NULL, 1856, 1939, (SELECT id FROM pais WHERE nombre = 'Austria')),
('Ernest', 'Hemingway', NULL, 1899, 1961, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Pío', 'Baroja', NULL, 1872, 1956, (SELECT id FROM pais WHERE nombre = 'España')),
('Epicteto', NULL, NULL, 50, 135, (SELECT id FROM pais WHERE nombre = 'Grecia')),
('Jules', 'Verne', NULL, 1828, 1905, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Robert', 'Greene', NULL, 1959, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Daniel', 'Greenberg', NULL, 1934, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Robert Louis', 'Stevenson', NULL, 1850, 1894, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Franz', 'Kafka', NULL, 1883, 1924, (SELECT id FROM pais WHERE nombre = 'República Checa')),
('Marco Aurelio', 'Antonino', NULL, 121, 180, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Fyodor', 'Dostoyevsky', NULL, 1821, 1881, (SELECT id FROM pais WHERE nombre = 'Rusia')),
('Miguel', 'Hernández', NULL, 1910, 1942, (SELECT id FROM pais WHERE nombre = 'España')),
('Spencer', 'Johnson', NULL, 1938, 2017, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Ryunosuke', 'Akutagawa', NULL, 1892, 1927, (SELECT id FROM pais WHERE nombre = 'Japón')),
('Lucio Anneo', 'Séneca', NULL, -4, 65, (SELECT id FROM pais WHERE nombre = 'España')),
('Haruki', 'Murakami', NULL, 1949, NULL, (SELECT id FROM pais WHERE nombre = 'Japón')),
('Óscar', 'Calavia', NULL, 1955, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Enrique', 'Páez', NULL, 1955, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Albert', 'Lijanov', NULL, 1935, NULL, (SELECT id FROM pais WHERE nombre = 'Rusia')),
('Lao', 'Tse', NULL, -600, -500, (SELECT id FROM pais WHERE nombre = 'China')),
('Stephen Richards', 'Covey', NULL, 1932, 2012, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Arthur', 'Schopenhauer', NULL, 1788, 1860, (SELECT id FROM pais WHERE nombre = 'Alemania')),
('Viktor', 'Frankl', NULL, 1905, 1997, (SELECT id FROM pais WHERE nombre = 'Austria')),
('León', 'Tolstói', NULL, 1828, 1910, (SELECT id FROM pais WHERE nombre = 'Rusia')),
('Nicolás', 'Maquiavelo', NULL, 1469, 1527, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Friedrich', 'Nietzsche', NULL, 1844, 1900, (SELECT id FROM pais WHERE nombre = 'Alemania')),
('Ricardo', 'Moreno', NULL, 1974, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Carlos', 'Ríos', NULL, 1971, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Luis María', 'Gonzalo', NULL, 1942, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Robert', 'Maurer', NULL, 1950, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Antoine', 'de Saint-Exupéry', NULL, 1900, 1944, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Carlos', 'Vaz', 'Ferreira', 1918, 1992, (SELECT id FROM pais WHERE nombre = 'Uruguay')),
('Massimo', 'Pigliucci', NULL, 1964, NULL, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Michael', 'Morpurgo', NULL, 1943, NULL, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Mark', 'Twain', NULL, 1835, 1910, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Anne', 'de Kervasdoue', NULL, 1946, NULL, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Byung-Chul', 'Han', NULL, 1959, NULL, (SELECT id FROM pais WHERE nombre = 'Corea del Sur')),
('Antonio', 'Martos', NULL, 1959, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Jaron', 'Lanier', NULL, 1960, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('José', 'Ortega y Gasset', NULL, 1883, 1955, (SELECT id FROM pais WHERE nombre = 'España')),
('Robert', 'Kiyosaki', NULL, 1947, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Thomas J.', 'Stanley', NULL, 1944, 2015, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Edgar Allan', 'Poe', NULL, 1809, 1849, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Zygmunt', 'Bauman', NULL, 1925, 2017, (SELECT id FROM pais WHERE nombre = 'Polonia')),
('Cal', 'Newport', NULL, 1982, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('George', 'Orwell', NULL, 1903, 1950, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('William', 'Golding', NULL, 1911, 1993, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Søren', 'Kierkegaard', NULL, 1813, 1855, (SELECT id FROM pais WHERE nombre = 'Dinamarca')),
('Ryan', 'Holiday', NULL, 1987, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Albert', 'Camus', NULL, 1913, 1960, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Marcos', 'Vázquez', NULL, 1972, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Primo', 'Levi', NULL, 1919, 1987, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Aldous', 'Huxley', NULL, 1894, 1963, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Benito', 'Pérez', 'Galdós', 1843, 1920, (SELECT id FROM pais WHERE nombre = 'España')),
('José Raúl', 'Capablanca', NULL, 1888, 1942, (SELECT id FROM pais WHERE nombre = 'Cuba')),
('Nassim Nicholas', 'Taleb', NULL, 1960, NULL, (SELECT id FROM pais WHERE nombre = 'Líbano')),
('Guy', 'de Maupassant', NULL, 1850, 1893, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Luis Alberto', 'Machado', NULL, 1932, NULL, (SELECT id FROM pais WHERE nombre = 'Venezuela')),
('Musashi', 'Miyamoto', NULL, 1584, 1645, (SELECT id FROM pais WHERE nombre = 'Japón')),
('Gary', 'Wilson', NULL, 1950, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Flora', 'Davis', NULL, 1934, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Norman', 'Doidge', NULL, 1950, NULL, (SELECT id FROM pais WHERE nombre = 'Canadá')),
('Valérie', 'Zenatti', NULL, 1970, NULL, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Marcel', 'Brion', NULL, 1895, 1984, (SELECT id FROM pais WHERE nombre = 'Francia')),
('Jack', 'London', NULL, 1876, 1916, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Erving', 'Goffman', NULL, 1922, 1982, (SELECT id FROM pais WHERE nombre = 'Canadá')),
('Natalia', 'de Santiago', NULL, 1975, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Isaiah', 'Berlin', NULL, 1909, 1997, (SELECT id FROM pais WHERE nombre = 'Letonia')),
('George S.', 'Clason', NULL, 1874, 1957, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Brian', 'Tracy', NULL, 1944, NULL, (SELECT id FROM pais WHERE nombre = 'Canadá')),
('Henry', 'Hazlitt', NULL, 1894, 1993, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Alex', 'Dey', NULL, 1976, NULL, (SELECT id FROM pais WHERE nombre = 'México')),
('Willam B.', 'Irvine', NULL, 1952, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Juan Ramón', 'Rallo', NULL, 1971, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Miguel', 'Ruiz', NULL, 1952, NULL, (SELECT id FROM pais WHERE nombre = 'México')),
('Luis', 'Pita', NULL, 1980, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Richard', 'Dawkins', NULL, 1941, NULL, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Virginia', 'Woolf', NULL, 1882, 1941, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Thomas', 'Sowell', NULL, 1930, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Voltaire', NULL, NULL, 1694, 1778, (SELECT id FROM pais WHERE nombre = 'Francia')),
('David Phillip', 'Barash', NULL, 1946, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Ludwig', 'Von Mises', NULL, 1881, 1973, (SELECT id FROM pais WHERE nombre = 'Austria')),
('Herbert', 'Spencer', NULL, 1820, 1903, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Henry David', 'Thoreau', NULL, 1817, 1862, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Axel', 'Kaiser', NULL, 1981, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Pére', 'Stupinya', NULL, 1980, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
('Po', 'Bronson', NULL, 1964, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Arthur Conan', 'Doyle', NULL, 1859, 1930, (SELECT id FROM pais WHERE nombre = 'Reino Unido')),
('Maurizio', 'de Giovani', NULL, 1958, NULL, (SELECT id FROM pais WHERE nombre = 'Italia')),
('Yuval Noah', 'Harari', NULL, 1976, NULL, (SELECT id FROM pais WHERE nombre = 'Israel')),
('Robert', 'Glover', NULL, 1950, NULL, (SELECT id FROM pais WHERE nombre = 'Estados Unidos')),
('Alfredo', 'García', NULL, 1975, NULL, (SELECT id FROM pais WHERE nombre = 'España'));

-- ============================================
-- 8. INSERTAR LIBROS
-- ============================================

INSERT INTO libro (title, year_publicacion, pages, description, opinion) VALUES
('Frankenstein o el moderno prometeo', 1818, 280, 'Un científico crea vida artificial con consecuencias trágicas. Explora temas de responsabilidad, aislamiento y los límites de la ciencia.', NULL),
('Cuento de Navidad', 1843, 104, 'Ebenezer Scrooge, un avaro anciano, es visitado por fantasmas que le muestran su pasado, presente y futuro, transformando su vida.', NULL),
('El Lazarillo de Tormes', 1554, 155, 'Relata las desventuras de Lázaro, un niño que sirve a varios amos en la España del siglo XVI, crítica social satírica.', NULL),
('Breve historia del tiempo', 1988, 256, 'Explica conceptos complejos del universo (big bang, agujeros negros) en lenguaje accesible para el público general.', NULL),
('El caballero de la armadura oxidada', 1990, 112, 'Alegoría sobre un caballero atrapado en su armadura que emprende un viaje de autodescubrimiento para liberarse.', NULL),
('Corazón', 1886, 288, 'Diario de un niño italiano que narra historias sobre su escuela, familia y amigos, enfatizando valores patrióticos y humanos.', NULL),
('Crónica de una muerte anunciada', 1981, 120, 'Reconstrucción periodística de un asesinato en un pueblo caribeño, donde todos sabían que ocurriría pero nadie lo impidió.', NULL),
('Diez negritos', 1939, 264, 'Diez invitados son asesinados uno por uno en una isla remota, según una macabra canción infantil.', NULL),
('Discurso del método. Meditaciones metafísicas', 1637, 200, 'Fundamento del racionalismo moderno. Propone dudar de todo para llegar a verdades ciertas: "Pienso, luego existo".', NULL),
('El arte de la guerra', -500, 112, 'Tratado militar clásico que explora tácticas, estrategias y filosofía de guerra aplicables a conflictos y competencia.', NULL),
('El arte de la prudencia', 1647, 300, '300 aforismos que ofrecen consejo práctico para navegar la complejidad social y alcanzar la excelencia personal.', NULL),
('El corazón de las tinieblas', 1899, 110, 'Un marinero viaja al Congo belga en busca de Kurtz, un comerciante de marfil que ha adoptado prácticas salvajes.', NULL),
('El guardián entre el centeno', 1951, 277, 'Holden Caulfield, adolescente rebelde, deambula por Nueva York tras ser expulsado, cuestionando la falsedad adulta.', NULL),
('El malestar en la cultura', 1930, 120, 'Análisis de la tensión entre los deseos individuales y las restricciones de la civilización, fuente de neurosis.', NULL),
('El viejo y el mar', 1952, 127, 'Un viejo pescador cubano lucha por capturar un enorme pez espada en el Golfo de México, reflexión sobre dignidad y lucha.', NULL),
('El árbol de la ciencia', 1911, 344, 'Sigue la vida de Andrés Hurtado, un estudiante de medicina desencantado, en una España decadente y absurda.', NULL),
('Manual de Epicteto', 135, 80, 'Compilación de enseñanzas estoicas sobre cómo vivir con virtud, aceptar lo que no controlamos y mantener la serenidad.', NULL),
('Escuela de Robinsones', 1882, 240, 'Dos jóvenes naufragan en una isla desierta y deben sobrevivir usando ingenio y conocimiento, inspirado en Robinson Crusoe.', NULL),
('Las 48 leyes del poder', 1998, 452, 'Análisis histórico de estrategias de poder, manipulación y control, con ejemplos de figuras históricas.', NULL),
('Por fin libres', 1987, 174, 'Libro cristiano sobre libertad espiritual y vivir según principios bíblicos.', NULL),
('La isla del tesoro', 1883, 304, 'Jim Hawkins se une a una tripulación en busca de un tesoro pirata, enfrentándose al traicionero Long John Silver.', NULL),
('La metamorfosis', 1915, 80, 'Gregor Samsa despierta convertido en un insecto gigante, explorando alienación, absurdo existencial y rechazo familiar.', NULL),
('Las nieves del Kilimanjaro', 1936, 150, 'Recopilación de cuentos, incluido el famoso sobre un escritor moribundo en África que reflexiona sobre su vida.', NULL),
('Meditaciones de Marco Aurelio', 180, 256, 'Reflexiones personales del emperador romano sobre virtud, resiliencia, muerte y vivir conforme a la naturaleza.', NULL),
('Memorias del subsuelo', 1864, 150, 'Monólogo de un amargado funcionario retirado que critica el racionalismo y defiende la irracionalidad humana.', NULL),
('El rayo que no cesa', 1936, 120, 'Poemas de amor, dolor y protesta social del poeta español, escrito durante la Guerra Civil.', NULL),
('¿Quién se ha llevado mi queso?', 1998, 96, 'Alegoría sobre el cambio: cuatro personajes buscan "queso" (metáfora de metas) en un laberinto, adaptándose o resistiendo.', NULL),
('Rashomon y otros cuentos', 1915, 200, 'Cuentos que exploran la naturaleza humana, verdad subjetiva y moralidad, incluyendo "En el bosque" (base de Rashomon).', NULL),
('Sobre la brevedad de la vida', 49, 80, 'Ensayo que argumenta que la vida es suficientemente larga si se vive sabiamente, criticando el desperdicio del tiempo.', NULL),
('Sobre la felicidad', 58, 90, 'Exposición estoica sobre cómo alcanzar la felicidad mediante la virtud, el control de deseos y la aceptación del destino.', NULL),
('Tokyo Blues', 1987, 400, 'Un hombre de 37 años recuerda su juventud en Tokio, amores perdidos y la búsqueda de identidad en los años 60.', NULL),
('Vida de un loco. Akutagawa', 1927, 150, 'Cuentos autobiográficos que exploran la enfermedad mental, el aislamiento y la creatividad del autor.', NULL),
('Amazonia-China', 2012, 144, '', NULL),
('Un secuestro de película', 1995, 128, '', NULL),
('Eclipse de sol', 1977, 157, '', NULL),
('Tao te Ching', -300, 81, 'Texto fundamental del taoísmo sobre armonía con el Tao (camino), virtud del no actuar y simplicidad.', NULL),
('El discreto', 1646, 200, 'Manual sobre discreción, prudencia y conducta inteligente para destacar en sociedad sin provocar envidia.', NULL),
('7 hábitos de la gente altamente efectiva', 1989, 432, 'Presenta siete principios para el desarrollo personal y profesional basados en la ética del carácter.', NULL),
('De la firmeza del sabio', 55, 70, 'Ensaje sobre la imperturbabilidad del sabio estoico frente a la adversidad y las opiniones ajenas.', NULL),
('El arte de tener la razón', 1831, 150, 'Tratado sobre dialéctica erística: 38 estratagemas para ganar debates, independientemente de la verdad.', NULL),
('De la ira', 45, 120, 'Análisis de la ira como pasión destructiva y cómo el sabio debe evitarla mediante la razón y el autocontrol.', NULL),
('El hombre en busca de sentido', 1946, 165, 'Psiquiatra sobrevive a campos de concentración y desarrolla la logoterapia: la búsqueda de sentido como fuerza motriz.', NULL),
('El poder de las tinieblas', 1886, 120, 'Drama rural sobre corrupción moral, adulterio y asesinato en la Rusia campesina del siglo XIX.', NULL),
('El príncipe', 1532, 140, 'Tratado político sobre cómo obtener y mantener el poder, pragmático y amoral, dirigido a un gobernante.', NULL),
('Relato de un náufrago', 1970, 142, 'Historia real de un marinero colombiano que sobrevivió 10 días a la deriva, crítica velada a la dictadura.', NULL),
('Así habló Zarathustra', 1883, 352, 'Obra filosófica-poética que introduce conceptos como el Superhombre, la muerte de Dios y el eterno retorno.', NULL),
('Lord Edgware Dies', 1933, 256, 'Hercule Poirot investiga el asesinato de Lord Edgware, cuya esposa, una actriz, había declarado públicamente su deseo de matarlo.', NULL),
('Kafka en la orilla', 2002, 608, 'Historia paralela de un adolescente fugitivo y un anciano con habilidades especiales, en un Japón lleno de sueños y profecías.', NULL),
('Trece cartas a Dios', 2015, 112, '', NULL),
('De la tranquilidad del ánimo', 53, 80, 'Ensayo sobre cómo mantener la serenidad interior frente a las preocupaciones y el caos exterior.', NULL),
('Come comida real', 2019, 320, '', NULL),
('Asesinato en el Orient Express', 1934, 256, 'Hercule Poirot investiga un asesinato en el famoso tren, donde todos los pasajeros parecen tener motivos y coartadas sospechosas.', NULL),
('El sueño', 1991, 156, '', NULL),
('El método Kaizen', 2004, 160, 'Método de mejora continua mediante pequeños pasos para lograr cambios sostenibles en hábitos y objetivos.', NULL),
('El Principito', 1943, 96, 'Un piloto perdido en el desierto conoce a un príncipe de otro planeta, reflexionando sobre amor, amistad y sentido de la vida.', NULL),
('Cards on the table', 1936, 272, 'Hercule Poirot investiga un asesinato durante una partida de bridge, donde los cuatro sospechosos son expertos en el juego.', NULL),
('Lógica viva', 1945, 291, '', NULL),
('Cómo ser un estoico', 2017, 288, 'Introducción práctica al estoicismo usando la figura de Epicteto, aplicando sus enseñanzas a la vida moderna.', NULL),
('El reino de Kensuke', 1999, 176, 'Un niño naufraga en una isla del Pacífico donde vive un anciano japonés misterioso, explorando amistad y supervivencia.', NULL),
('Príncipe y mendigo', 1881, 240, 'Un príncipe y un mendigo idénticos intercambian roles, satirizando las desigualdades sociales de la Inglaterra Tudor.', NULL),
('El cuerpo femenino', 1993, 96, '', NULL),
('La vuelta al mundo en 80 días', 1873, 320, 'Phileas Fogg apuesta que puede circunnavegar el globo en 80 días, enfrentando obstáculos con su fiel mayordomo.', NULL),
('De la vida bienaventurada', 58, 90, 'Ensayo sobre la verdadera felicidad, que reside en la virtud y la razón, no en placeres o riquezas externas.', NULL),
('En el enjambre', 2013, 120, 'Análisis de la sociedad digital y las redes sociales, donde la hiperconexión genera aislamiento y pérdida de privacidad.', NULL),
('La sociedad de la transparencia', 2012, 100, 'Crítica a la obsesión contemporánea por la transparencia, que elimina el misterio y favorece el control.', NULL),
('La sociedad del cansancio', 2010, 80, 'Describe la sociedad actual como víctima de un cansancio autoinfligido por la autoexplotación y el rendimiento.', NULL),
('1Q84 (libros 1 y 2)', 2009, 928, 'Una instructora de gimnasia y un aspirante a escritor se entrelazan en un mundo paralelo llamado 1Q84, con misterios y cultos.', NULL),
('1Q84 (libro 3)', 2010, 464, 'Conclusión de la épica historia, donde los destinos de Aomame y Tengo convergen en un enfrentamiento final.', NULL),
('Hasta que la muerte nos separe', 2009, 190, '', NULL),
('10 razones para borrar tus redes sociales de inmediato', 2018, 160, 'Argumentos sobre los efectos negativos de las redes en la democracia, economía y salud mental.', NULL),
('El asesinato de Roger Ackroyd', 1926, 320, 'Hercule Poirot investiga el asesinato de un rico viudo en un pueblo inglés, con un famoso giro narrativo final.', NULL),
('Ideas y creencias', 1940, 200, 'Distinción entre ideas (pensamientos elaborados) y creencias (fondos inconscientes que sostienen nuestra vida).', NULL),
('Padre rico padre pobre', 1997, 336, 'Contraste entre dos mentalidades financieras: la de un padre tradicional y la de un padre emprendedor, enfatizando educación financiera.', NULL),
('El millonario de la puerta de al lado', 1996, 258, 'Estudio de hábitos de millonarios estadounidenses: frugalidad, planificación y evitar apariencias costosas.', NULL),
('Los hermanos Karamazov', 1880, 796, 'Explora conflicto entre fe y razón, moral y libertad, a través de la historia de un parricidio en una familia rusa.', NULL),
('La caída de la casa de Usher (y otros)', 1839, 150, 'Colección de cuentos góticos que incluye el famoso sobre una familia maldita y una casa que se desmorona.', NULL),
('Tiempos líquidos', 2007, 150, 'Análisis de la modernidad líquida: relaciones, miedo e identidad en un mundo globalizado, cambiante e incierto.', NULL),
('Minimalismo digital', 2019, 304, 'Propone reducir el uso de tecnología digital a lo esencial para recuperar concentración y bienestar.', NULL),
('1984', 1949, 328, 'En un estado totalitario, Winston Smith desafía al sistema de vigilancia y control del Gran Hermano.', NULL),
('Rebelión en la granja', 1945, 112, 'Los animales de una granja se rebelan contra los humanos, pero la revolución degenera en una nueva tiranía.', NULL),
('El señor de las moscas', 1954, 224, 'Un grupo de niños náufragos intenta organizarse, pero descienden a la barbarie y la violencia tribal.', NULL),
('Vida líquida', 2005, 160, 'Describe la fragilidad de los vínculos humanos en la modernidad líquida, donde todo es provisional y desechable.', NULL),
('El concepto de la angustia', 1844, 200, 'Análisis de la angustia como experiencia fundamental humana ligada a la libertad y posibilidad.', NULL),
('El ego es el enemigo', 2016, 226, 'Argumenta que el ego obstaculiza el éxito y propone cultivar humildad, disciplina y resiliencia.', NULL),
('Viaje al centro de la tierra', 1864, 400, 'Un profesor y su sobrino descienden por un volcán hacia el interior de la Tierra, descubriendo mundos prehistóricos.', NULL),
('El obstáculo es el camino', 2014, 224, 'Usa la filosofía estoica para mostrar cómo convertir obstáculos en oportunidades de crecimiento y éxito.', NULL),
('El mito de Sísifo', 1942, 160, 'Ensayo sobre el absurdo de la existencia y la respuesta de la rebelión, simbolizada por Sísifo condenado a empujar una roca eternamente.', NULL),
('El extranjero', 1942, 123, 'Meursault, indiferente ante la muerte de su madre y un asesinato, enfrenta juicio y existencia absurda.', NULL),
('El huésped', 1957, 864, 'Un maestro en una remota escuela argelina debe escoltar a un prisionero árabe, enfrentando dilemas morales y políticos.', NULL),
('El misterioso caso de Styles', 1920, 256, 'Primera aparición de Hercule Poirot, investigando el envenenamiento de la rica señora Inglethorp en una mansión.', NULL),
('La muerte feliz', 1971, 200, 'Primera novela de Camus sobre un hombre que busca la felicidad a través del asesinato y el dinero, temas de absurdo.', NULL),
('No-cosas', 2021, 120, 'Crítica a la digitalización que reemplaza las cosas físicas por información, vaciando la realidad y las relaciones.', NULL),
('Salud salvaje', 2018, 264, '', NULL),
('Si esto es un hombre', 1947, 200, 'Testimonio del autor como superviviente de Auschwitz, reflexionando sobre la deshumanización en los campos.', NULL),
('Un mundo feliz', 1932, 268, 'En una sociedad futura donde la estabilidad se logra mediante condicionamiento, drogas y eliminación de emociones fuertes.', NULL),
('Trafalgar (B.P. Galdós)', 1873, 250, 'Primer episodio nacional, recrea la batalla de Trafalgar desde la perspectiva de un niño testigo.', NULL),
('Nociones elementales de ajedrez', 1923, 160, '', NULL),
('Muerte en el Nilo', 1937, 344, 'Hercule Poirot investiga el asesinato de una heredera durante un crucero por el Nilo.', NULL),
('De la providencia', 64, 60, 'Ensayo que defiende que los males son pruebas del destino para fortalecer al sabio, no castigos injustos.', NULL),
('Antifragil', 2012, 544, 'Concepto de sistemas que se benefician del estrés y el desorden (antifrágiles), más allá de la resiliencia.', NULL),
('Pierre et Jean', 1888, 224, 'Dos hermanos descubren que uno es hijo ilegítimo, explorando celos, herencia y relaciones familiares en la burguesía francesa.', NULL),
('La revolución de la inteligencia', 1975, 159, '', NULL),
('El camino de la soledad', 1645, 1, '', NULL),
('Your brain on porn', 2014, 250, 'Explica los efectos neurológicos de la pornografía en internet, argumentando que puede crear adicción y dañar la sexualidad.', NULL),
('La comunicación no verbal', 1973, 320, '', NULL),
('The brain that changes itself', 2007, 440, 'Presenta casos de neuroplasticidad: el cerebro puede reorganizarse y recuperarse de daños o cambiar hábitos.', NULL),
('Une bouteille dans la mer de Gaza', 2005, 192, 'Una joven israelí y un palestino intercambian emails tras lanzar una botella al mar, explorando conflicto y humanidad.', NULL),
('Un avventura di viaggio', 1966, 15, '', NULL),
('Martin Eden', 1909, 400, 'Un marinero sin educación se esfuerza por convertirse en escritor y ganar el amor de una mujer de clase alta, crítica al sueño americano.', NULL),
('La Presentación de la persona en la vida cotidiana', 1956, 272, 'Teoría de la interacción social como una representación teatral, donde gestionamos impresiones en distintos escenarios.', NULL),
('Invierte en ti', 2021, 240, '', NULL),
('La caída', 1956, 147, 'Un ex abogado confiesa su hipocresía y culpa en un bar de Ámsterdam, explorando la condición humana y la moralidad.', NULL),
('El anticristo', 1895, 100, 'Ataque feroz al cristianismo como religión de la debilidad y resentimiento, que niega los valores vitales.', NULL),
('Dos conceptos de libertad', 1958, 100, 'Distinción entre libertad negativa (ausencia de interferencia) y positiva (capacidad de autogobierno).', NULL),
('El hombre más rico de babilonia', 1926, 144, 'Parábolas ambientadas en la antigua Babilonia que enseñan principios de ahorro, inversión y riqueza.', NULL),
('Hable menos y actúe más', 2017, 120, '', NULL),
('La economía en una lección', 1946, 218, 'Exposición de principios económicos básicos, enfatizando las consecuencias no intencionadas de las políticas a largo plazo.', NULL),
('La biblia del vendedor', 1998, 250, 'Principios y técnicas de ventas, comunicación y persuasión para alcanzar el éxito en el ámbito comercial.', NULL),
('El arte de la buena vida', 2017, 368, '', NULL),
('Los 10 principios básicos del orden político liberal', 2019, 272, '', NULL),
('Los 4 acuerdos', 1997, 160, 'Basado en sabiduría tolteca: cuatro principios para la libertad personal (sé impecable con tu palabra, no tomes nada personal, etc.).', NULL),
('Ten peor coche que tu vecino', 2021, 176, '', NULL),
('El gen egoísta', 1976, 360, 'Expone la teoría del gen como unidad de selección natural, donde los organismos son vehículos para genes "egoístas".', NULL),
('Una habitación propia', 1929, 172, 'Reflexión sobre la necesidad de independencia económica y espacio físico para que las mujeres escriban literatura.', NULL),
('La rebelión de las masas', 1930, 250, 'Análisis de la sociedad de masas y el hombre-masa que actúa sin criterio propio, amenazando la excelencia cultural.', NULL),
('Falacias de la justicia social', 2024, 208, '', NULL),
('Tratado sobre la tolerancia', 1763, 150, 'Defensa de la tolerancia religiosa y crítica al fanatismo, inspirado por el caso de ejecución injusta de Jean Calas.', NULL),
('El mito de la monogamia', 2003, 391, '', NULL),
('Seis lecciones sobre el capitalismo', 1959, 128, '', NULL),
('El hombre contra el estado', 1884, 200, 'Defensa del liberalismo clásico y crítica al crecimiento del estado intervencionista, que amenaza la libertad individual.', NULL),
('Walden', 1854, 320, 'Reflexión sobre vida simple en la naturaleza, autosuficiencia y crítica a la sociedad industrial y consumista.', NULL),
('Caminar', 1862, 50, 'Ensaje que celebra el acto de caminar como conexión con la naturaleza y rebelión contra las convenciones sociales.', NULL),
('El crepúsculo de los ídolos', 1889, 160, 'Ataque a las "ídolos" de la cultura occidental (Sócrates, cristianismo, moral tradicional) como decadentes.', NULL),
('El economista callejero', 2022, 144, '', NULL),
('Temor y temblor', 1843, 150, 'Reflexión sobre la fe a través de la historia de Abraham e Isaac, explorando la paradoja de la relación individual con Dios.', NULL),
('La ciencia del sexo', 2023, 288, '', NULL),
('¿Qué hago con mi vida?', 2002, 400, 'Historias reales de personas que buscaron sentido y cambio en sus carreras y vidas, motivando al lector a reflexionar.', NULL),
('Discriminación y disparidades', 2018, 192, 'Análisis de diferencias económicas entre grupos, argumentando que no todas se deben a discriminación sino a múltiples factores.', NULL),
('El sabueso de los Baskerville', 1902, 256, 'Sherlock Holmes investiga la misteriosa muerte de un baronet, aparentemente relacionada con una leyenda de un perro infernal.', NULL),
('Angeli', 2022, 280, '', NULL),
('Invierte con poco', 2022, 320, '', NULL),
('Sapiens: de animales a dioses', 2011, 496, 'Recorrido por la historia humana desde la evolución hasta el presente, examinando revoluciones cognitiva, agrícola y científica.', NULL),
('Basta ya de ser un Tipo Lindo', 2021, 229, '', NULL),
('La energía nuclear salvará el mundo', 2021, 336, 'Divulgación sobre la seguridad y necesidad de adaptar la energía nuclear como la forma más importante de energía para el mundo.', NULL);

-- ============================================
-- 9. CREAR RELACIONES EN TABLAS DE UNIÓN
-- ============================================

-- Insertar relaciones libro-autor (un autor por libro inicialmente)
INSERT INTO autor_libro (libro_id, autor_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20),
(21, 21), (22, 22), (23, 15), (24, 23), (25, 24), (26, 25), (27, 26), (28, 27), (29, 28), (30, 28),
(31, 29), (32, 27), (33, 30), (34, 31), (35, 32), (36, 33), (37, 11), (38, 34), (39, 28), (40, 35),
(41, 28), (42, 36), (43, 37), (44, 38), (45, 7), (46, 39), (47, 8), (48, 29), (49, 40), (50, 28),
(51, 41), (52, 8), (53, 42), (54, 43), (55, 44), (56, 8), (57, 45), (58, 46), (59, 47), (60, 48),
(61, 49), (62, 18), (63, 28), (64, 50), (65, 50), (66, 50), (67, 29), (68, 29), (69, 51), (70, 52),
(71, 8), (72, 53), (73, 54), (74, 55), (75, 24), (76, 56), (77, 57), (78, 58), (79, 59), (80, 59),
(81, 60), (82, 57), (83, 61), (84, 62), (85, 18), (86, 62), (87, 63), (88, 63), (89, 63), (90, 8),
(91, 63), (92, 50), (93, 64), (94, 65), (95, 66), (96, 67), (97, 68), (98, 8), (99, 28), (100, 69),
(101, 70), (102, 71), (103, 72), (104, 73), (105, 74), (106, 75), (107, 76), (108, 77), (109, 78), (110, 79),
(111, 80), (112, 63), (113, 39), (114, 81), (115, 82), (116, 83), (117, 84), (118, 85), (119, 86), (120, 87),
(121, 88), (122, 89), (123, 90), (124, 91), (125, 53), (126, 92), (127, 93), (128, 94), (129, 95), (130, 96),
(131, 97), (132, 97), (133, 39), (134, 98), (135, 61), (136, 99), (137, 100), (138, 92), (139, 101), (140, 102),
(141, 80), (142, 103), (143, 104), (144, 105);

-- Insertar relaciones libro-género (un género por libro inicialmente)
INSERT INTO genero_libro (libro_id, genere_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 3), (8, 7), (9, 8), (10, 9),
(11, 10), (12, 11), (13, 12), (14, 13), (15, 3), (16, 3), (17, 8), (18, 11), (19, 15), (20, 16),
(21, 11), (22, 3), (23, 2), (24, 8), (25, 3), (26, 14), (27, 5), (28, 2), (29, 8), (30, 8),
(31, 12), (32, 17), (33, 18), (34, 6), (35, 6), (36, 8), (37, 10), (38, 5), (39, 8), (40, 41),
(41, 8), (42, 13), (43, 19), (44, 15), (45, 20), (46, 21), (47, 7), (48, 22), (49, 8), (50, 8),
(51, 23), (52, 7), (53, 28), (54, 24), (55, 25), (56, 7), (57, 26), (58, 8), (59, 11), (60, 29),
(61, 28), (62, 11), (63, 8), (64, 8), (65, 8), (66, 8), (67, 22), (68, 22), (69, 3), (70, 30),
(71, 7), (72, 8), (73, 31), (74, 31), (75, 3), (76, 33), (77, 32), (78, 30), (79, 34), (80, 35),
(81, 25), (82, 32), (83, 8), (84, 5), (85, 1), (86, 5), (87, 8), (88, 3), (89, 3), (90, 7),
(91, 3), (92, 8), (93, 28), (94, 42), (95, 1), (96, 29), (97, 43), (98, 7), (99, 8), (100, 36),
(101, 3), (102, 39), (103, 8), (104, 28), (105, 32), (106, 4), (107, 3), (108, 3), (109, 17), (110, 32),
(111, 31), (112, 3), (113, 8), (114, 8), (115, 31), (116, 5), (117, 36), (118, 37), (119, 8), (120, 15),
(121, 38), (122, 31), (123, 4), (124, 39), (125, 32), (126, 32), (127, 8), (128, 18), (129, 36), (130, 8),
(131, 18), (132, 8), (133, 8), (134, 36), (135, 16), (136, 4), (137, 5), (138, 36), (139, 7), (140, 3),
(141, 31), (142, 42), (143, 5), (144, 18);

-- ============================================
-- FIN DEL SCRIPT
-- ============================================