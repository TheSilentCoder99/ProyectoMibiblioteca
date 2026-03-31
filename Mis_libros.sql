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
    year_nacimiento INT UNSIGNED,
    year_fallecimiento INT UNSIGNED,
    pais_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (pais_id) REFERENCES pais(id)
);

-- Tabla de libros (SIN author_id, SIN genre_id)
CREATE TABLE IF NOT EXISTS libro (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    year_publicacion INT,
    pages INT UNSIGNED,
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

CREATE INDEX IF NOT EXISTS idx_autor_nombre ON autor(nombre);
CREATE INDEX IF NOT EXISTS idx_autor_apellido1 ON autor(apellido1);
CREATE INDEX IF NOT EXISTS idx_libro_title ON libro(title);
CREATE INDEX IF NOT EXISTS idx_libro_year ON libro(year_publicacion);
CREATE INDEX IF NOT EXISTS idx_genero_nombre ON genero(nombre);
CREATE INDEX IF NOT EXISTS idx_pais_nombre ON pais(nombre);

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
('Sudáfrica', 'ZAF');

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
('Anónimo', NULL, NULL, NULL, NULL, (SELECT id FROM pais WHERE nombre = 'España')),
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
('Vida líquida', 200
