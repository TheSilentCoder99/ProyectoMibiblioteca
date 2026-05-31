"""
buscar_portadas.py
------------------
Busca el cover_id de OpenLibrary para cada libro de la biblioteca personal.
Genera al final:
  - Un fichero SQL con los UPDATE para añadir el cover_id a cada libro.
  - Un CSV de revisión con todos los resultados.

Uso:
    pip install requests
    python buscar_portadas.py

OpenLibrary Covers API:
    https://covers.openlibrary.org/b/id/{cover_id}-L.jpg
"""

import requests
import time
import csv
import re

# ==============================================================
# LISTA DE LIBROS  (id_bd, titulo, autor)
# El autor ayuda a desambiguar cuando hay muchos resultados
# ==============================================================
LIBROS = [
    (1,   "Frankenstein o el moderno prometeo",              "Mary Shelley"),
    (2,   "Cuento de Navidad",                               "Charles Dickens"),
    (3,   "El Lazarillo de Tormes",                          "Anonimo"),
    (4,   "Breve historia del tiempo",                       "Stephen Hawking"),
    (5,   "El caballero de la armadura oxidada",             "Robert Fisher"),
    (6,   "Corazon",                                         "Edmondo De Amicis"),
    (7,   "Cronica de una muerte anunciada",                 "Gabriel Garcia Marquez"),
    (8,   "Diez negritos",                                   "Agatha Christie"),
    (9,   "Discurso del metodo",                             "Rene Descartes"),
    (10,  "El arte de la guerra",                            "Sun Tzu"),
    (11,  "El arte de la prudencia",                         "Baltasar Gracian"),
    (12,  "El corazon de las tinieblas",                     "Joseph Conrad"),
    (13,  "El guardian entre el centeno",                    "J.D. Salinger"),
    (14,  "El malestar en la cultura",                       "Sigmund Freud"),
    (15,  "El viejo y el mar",                               "Ernest Hemingway"),
    (16,  "El arbol de la ciencia",                          "Pio Baroja"),
    (17,  "Manual de Epicteto",                              "Epicteto"),
    (18,  "Escuela de Robinsones",                           "Jules Verne"),
    (19,  "Las 48 leyes del poder",                          "Robert Greene"),
    (20,  "Por fin libres",                                  "Daniel Greenberg"),
    (21,  "La isla del tesoro",                              "Robert Louis Stevenson"),
    (22,  "La metamorfosis",                                 "Franz Kafka"),
    (23,  "Las nieves del Kilimanjaro",                      "Ernest Hemingway"),
    (24,  "Meditaciones",                                    "Marco Aurelio"),
    (25,  "Memorias del subsuelo",                           "Fyodor Dostoyevsky"),
    (26,  "El rayo que no cesa",                             "Miguel Hernandez"),
    (27,  "Quien se ha llevado mi queso",                    "Spencer Johnson"),
    (28,  "Rashomon y otros cuentos",                        "Ryunosuke Akutagawa"),
    (29,  "Sobre la brevedad de la vida",                    "Seneca"),
    (30,  "Sobre la felicidad",                              "Seneca"),
    (31,  "Tokyo Blues",                                     "Haruki Murakami"),
    (32,  "Vida de un loco",                                 "Ryunosuke Akutagawa"),
    (33,  "Amazonia-China",                                  "Oscar Calavia"),
    (34,  "Un secuestro de pelicula",                        "Enrique Paez"),
    (35,  "Eclipse de sol",                                  "Albert Lijanov"),
    (36,  "Tao te Ching",                                    "Lao Tse"),
    (37,  "El discreto",                                     "Baltasar Gracian"),
    (38,  "7 habitos de la gente altamente efectiva",        "Stephen Covey"),
    (39,  "De la firmeza del sabio",                         "Seneca"),
    (40,  "El arte de tener la razon",                       "Arthur Schopenhauer"),
    (41,  "De la ira",                                       "Seneca"),
    (42,  "El hombre en busca de sentido",                   "Viktor Frankl"),
    (43,  "El poder de las tinieblas",                       "Leon Tolstoi"),
    (44,  "El principe",                                     "Nicolas Maquiavelo"),
    (45,  "Relato de un naufrago",                           "Gabriel Garcia Marquez"),
    (46,  "Asi hablo Zarathustra",                           "Friedrich Nietzsche"),
    (47,  "Lord Edgware Dies",                               "Agatha Christie"),
    (48,  "Kafka en la orilla",                              "Haruki Murakami"),
    (49,  "Trece cartas a Dios",                             "Ricardo Moreno"),
    (50,  "De la tranquilidad del animo",                    "Seneca"),
    (51,  "Come comida real",                                "Carlos Rios"),
    (52,  "Asesinato en el Orient Express",                  "Agatha Christie"),
    (53,  "El sueno",                                        "Luis Maria Gonzalo"),
    (54,  "El metodo Kaizen",                                "Robert Maurer"),
    (55,  "El Principito",                                   "Antoine de Saint-Exupery"),
    (56,  "Cards on the table",                              "Agatha Christie"),
    (57,  "Logica viva",                                     "Carlos Vaz Ferreira"),
    (58,  "Como ser un estoico",                             "Massimo Pigliucci"),
    (59,  "El reino de Kensuke",                             "Michael Morpurgo"),
    (60,  "Principe y mendigo",                              "Mark Twain"),
    (61,  "El cuerpo femenino",                              "Anne de Kervasdoue"),
    (62,  "La vuelta al mundo en 80 dias",                   "Jules Verne"),
    (63,  "De la vida bienaventurada",                       "Seneca"),
    (64,  "En el enjambre",                                  "Byung-Chul Han"),
    (65,  "La sociedad de la transparencia",                 "Byung-Chul Han"),
    (66,  "La sociedad del cansancio",                       "Byung-Chul Han"),
    (67,  "1Q84 libros 1 y 2",                               "Haruki Murakami"),
    (68,  "1Q84 libro 3",                                    "Haruki Murakami"),
    (69,  "Hasta que la muerte nos separe",                  "Antonio Martos"),
    (70,  "10 razones para borrar tus redes sociales",       "Jaron Lanier"),
    (71,  "El asesinato de Roger Ackroyd",                   "Agatha Christie"),
    (72,  "Ideas y creencias",                               "Jose Ortega y Gasset"),
    (73,  "Padre rico padre pobre",                          "Robert Kiyosaki"),
    (74,  "El millonario de la puerta de al lado",           "Thomas Stanley"),
    (75,  "Los hermanos Karamazov",                          "Fyodor Dostoyevsky"),
    (76,  "La caida de la casa de Usher",                    "Edgar Allan Poe"),
    (77,  "Tiempos liquidos",                                "Zygmunt Bauman"),
    (78,  "Minimalismo digital",                             "Cal Newport"),
    (79,  "1984",                                            "George Orwell"),
    (80,  "Rebelion en la granja",                           "George Orwell"),
    (81,  "El senor de las moscas",                          "William Golding"),
    (82,  "Vida liquida",                                    "Zygmunt Bauman"),
    (83,  "El concepto de la angustia",                      "Soren Kierkegaard"),
    (84,  "El ego es el enemigo",                            "Ryan Holiday"),
    (85,  "Viaje al centro de la tierra",                    "Jules Verne"),
    (86,  "El obstaculo es el camino",                       "Ryan Holiday"),
    (87,  "El mito de Sisifo",                               "Albert Camus"),
    (88,  "El extranjero",                                   "Albert Camus"),
    (89,  "El huesped",                                      "Albert Camus"),
    (90,  "El misterioso caso de Styles",                    "Agatha Christie"),
    (91,  "La muerte feliz",                                 "Albert Camus"),
    (92,  "No-cosas",                                        "Byung-Chul Han"),
    (93,  "Salud salvaje",                                   "Marcos Vazquez"),
    (94,  "Si esto es un hombre",                            "Primo Levi"),
    (95,  "Un mundo feliz",                                  "Aldous Huxley"),
    (96,  "Trafalgar",                                       "Benito Perez Galdos"),
    (97,  "Nociones elementales de ajedrez",                 "Jose Raul Capablanca"),
    (98,  "Muerte en el Nilo",                               "Agatha Christie"),
    (99,  "De la providencia",                               "Seneca"),
    (100, "Antifragil",                                      "Nassim Nicholas Taleb"),
    (101, "Pierre et Jean",                                  "Guy de Maupassant"),
    (102, "La revolucion de la inteligencia",                "Luis Alberto Machado"),
    (103, "El camino de la soledad",                         "Musashi Miyamoto"),
    (104, "Your brain on porn",                              "Gary Wilson"),
    (105, "La comunicacion no verbal",                       "Flora Davis"),
    (106, "The brain that changes itself",                   "Norman Doidge"),
    (107, "Une bouteille dans la mer de Gaza",               "Valerie Zenatti"),
    (108, "Un avventura di viaggio",                         "Marcel Brion"),
    (109, "Martin Eden",                                     "Jack London"),
    (110, "La Presentacion de la persona en la vida cotidiana", "Erving Goffman"),
    (111, "Invierte en ti",                                  "Natalia de Santiago"),
    (112, "La caida",                                        "Albert Camus"),
    (113, "El anticristo",                                   "Friedrich Nietzsche"),
    (114, "Dos conceptos de libertad",                       "Isaiah Berlin"),
    (115, "El hombre mas rico de babilonia",                 "George Clason"),
    (116, "Hable menos y actue mas",                         "Brian Tracy"),
    (117, "La economia en una leccion",                      "Henry Hazlitt"),
    (118, "La biblia del vendedor",                          "Alex Dey"),
    (119, "El arte de la buena vida",                        "Willam Irvine"),
    (120, "Los 10 principios basicos del orden politico liberal", "Juan Ramon Rallo"),
    (121, "Los 4 acuerdos",                                  "Miguel Ruiz"),
    (122, "Ten peor coche que tu vecino",                    "Luis Pita"),
    (123, "El gen egoista",                                  "Richard Dawkins"),
    (124, "Una habitacion propia",                           "Virginia Woolf"),
    (125, "La rebelion de las masas",                        "Jose Ortega y Gasset"),
    (126, "Falacias de la justicia social",                  "Thomas Sowell"),
    (127, "Tratado sobre la tolerancia",                     "Voltaire"),
    (128, "El mito de la monogamia",                         "David Barash"),
    (129, "Seis lecciones sobre el capitalismo",             "Ludwig Von Mises"),
    (130, "El hombre contra el estado",                      "Herbert Spencer"),
    (131, "Walden",                                          "Henry David Thoreau"),
    (132, "Caminar",                                         "Henry David Thoreau"),
    (133, "El crepusculo de los idolos",                     "Friedrich Nietzsche"),
    (134, "El economista callejero",                         "Axel Kaiser"),
    (135, "Temor y temblor",                                 "Soren Kierkegaard"),
    (136, "La ciencia del sexo",                             "Pere Stupinya"),
    (137, "Que hago con mi vida",                            "Po Bronson"),
    (138, "Discriminacion y disparidades",                   "Thomas Sowell"),
    (139, "El sabueso de los Baskerville",                   "Arthur Conan Doyle"),
    (140, "Angeli",                                          "Maurizio de Giovani"),
    (141, "Invierte con poco",                               "Natalia de Santiago"),
    (142, "Sapiens de animales a dioses",                    "Yuval Noah Harari"),
    (143, "Basta ya de ser un Tipo Lindo",                   "Robert Glover"),
    (144, "La energia nuclear salvara el mundo",             "Alfredo Garcia"),
    (144, "El hombre del traje color castaño",               "Agatha Christie"),
]

# ==============================================================
# CONFIGURACIÓN
# ==============================================================
BASE_URL  = "https://openlibrary.org/search.json"
COVER_URL = "https://covers.openlibrary.org/b/id/{cover_id}-L.jpg"
PAUSA     = 0.4   # segundos entre peticiones (respeta rate-limit de OL)

OUTPUT_SQL = "portadas_update.sql"
OUTPUT_CSV = "portadas_resultado.csv"


def buscar_cover_id(titulo: str, autor: str) -> tuple[int | None, str]:
    """
    Devuelve (cover_id, url_portada) o (None, '') si no se encuentra.
    Primero busca con título+autor; si no hay cover, reintenta solo con título.
    """
    params = {
        "title":  titulo,
        "author": autor,
        "limit":  5,
        "fields": "title,author_name,cover_i,isbn",
        "lang":   "spa",   # preferencia por ediciones en español
    }

    for intento in range(2):
        try:
            r = requests.get(BASE_URL, params=params, timeout=10)
            r.raise_for_status()
            data = r.json()
            docs = data.get("docs", [])

            for doc in docs:
                cover_id = doc.get("cover_i")
                if cover_id:
                    url = COVER_URL.format(cover_id=cover_id)
                    return cover_id, url

            # segundo intento: sin filtro de autor
            if intento == 0:
                params.pop("author", None)
                params.pop("lang", None)

        except Exception as e:
            print(f"  ⚠ Error en petición: {e}")
            break

    return None, ""


def main():
    resultados = []
    sin_portada = []

    print(f"{'ID':>4}  {'TÍTULO':<55} {'COVER_ID':>10}  ESTADO")
    print("-" * 90)

    for libro_id, titulo, autor in LIBROS:
        cover_id, url = buscar_cover_id(titulo, autor)

        if cover_id:
            estado = "✓ OK"
        else:
            estado = "✗ NO ENCONTRADO"
            sin_portada.append((libro_id, titulo))

        titulo_corto = titulo[:53] + ".." if len(titulo) > 55 else titulo
        print(f"{libro_id:>4}  {titulo_corto:<55} {str(cover_id) if cover_id else '':>10}  {estado}")

        resultados.append({
            "id_bd":    libro_id,
            "titulo":   titulo,
            "autor":    autor,
            "cover_id": cover_id,
            "url":      url,
            "estado":   estado,
        })

        time.sleep(PAUSA)

    # ----------------------------------------------------------
    # Generar SQL
    # ----------------------------------------------------------
    sql_lines = [
        "-- ================================================",
        "-- UPDATE cover_id en tabla libro",
        "-- Ejecutar DESPUÉS de añadir la columna:",
        "--   ALTER TABLE libro ADD COLUMN cover_id INT UNSIGNED;",
        "-- ================================================",
        "",
    ]

    encontrados = [r for r in resultados if r["cover_id"]]
    for r in encontrados:
        sql_lines.append(
            f"UPDATE libro SET cover_id = {r['cover_id']} WHERE id = {r['id_bd']};  -- {r['titulo']}"
        )

    if sin_portada:
        sql_lines += [
            "",
            "-- ================================================",
            "-- Libros SIN portada encontrada (revisar manualmente):",
        ]
        for lid, ltitulo in sin_portada:
            sql_lines.append(f"--   id={lid}  {ltitulo}")
        sql_lines.append("-- ================================================")

    with open(OUTPUT_SQL, "w", encoding="utf-8") as f:
        f.write("\n".join(sql_lines))

    # ----------------------------------------------------------
    # Generar CSV de revisión
    # ----------------------------------------------------------
    with open(OUTPUT_CSV, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=["id_bd", "titulo", "autor", "cover_id", "url", "estado"])
        writer.writeheader()
        writer.writerows(resultados)

    # ----------------------------------------------------------
    # Resumen final
    # ----------------------------------------------------------
    total     = len(LIBROS)
    ok        = len(encontrados)
    fallidos  = total - ok

    print("\n" + "=" * 90)
    print(f"RESUMEN: {ok}/{total} portadas encontradas  |  {fallidos} sin resultado")
    print(f"Archivos generados:")
    print(f"  → {OUTPUT_SQL}   (UPDATE statements listos para ejecutar)")
    print(f"  → {OUTPUT_CSV}   (tabla completa para revisión manual)")
    print("=" * 90)

    if sin_portada:
        print("\nLibros que necesitan revisión manual:")
        for lid, ltitulo in sin_portada:
            print(f"  id={lid:>3}  {ltitulo}")
        print("\nPara buscarlos manualmente:")
        print("  https://openlibrary.org/search  →  copia el cover_id de la URL de la portada")
        print("  y añade: UPDATE libro SET cover_id = <ID> WHERE id = <ID_BD>;")


if __name__ == "__main__":
    main()