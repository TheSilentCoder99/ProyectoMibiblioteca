package com.alan.controladores;
//AtlantaFX es una librería de temas visuales para JavaFX. CON ELLA PUEDO CAMBIAR EL TEMA DE LA APP

import atlantafx.base.theme.Dracula;
import com.alan.DataAccesObjects.*;
import com.alan.clases.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class pantallaPrincipalController {

    public MenuItem anteriorAsiglo21;
    public MenuItem siglo21ID;
    public MenuItem librosPorAutor;
    public MenuItem numLibrosPorGenero;
    LibroDAO librodao = new LibroDAO();
    AutorDAO autordao = new AutorDAO();
    GeneroDAO generodao = new GeneroDAO();
    PaisDAO paisdao = new PaisDAO();
    AutorLibroDAO autorlibrodao = new AutorLibroDAO();

    @FXML
    private
    Button botonEliminarElemento;

    @FXML
    private
    Button botonEditarElemento;

    @FXML
    private Button addAutor;

    @FXML
    private Button addGenero;

    @FXML
    private Button addLibro;

    @FXML
    private Button botonMostrarPaises;

    @FXML
    private TextField inputBuscarLibro;

    @FXML
    private TextField inputBuscarAutor;

    @FXML
    private TextField inputBuscarGenero;

    @FXML
    private TextField inputBuscarPais;

    @FXML
    private Button mostrarAllAutores;

    @FXML
    private Button mostrarAllGeneros;

    @FXML
    private Button mostrarAllLibros;

    @FXML
    private VBox panelLateral;

    @FXML
    private Label mostrarDescripcion;

    @FXML
    private Label mostrarOpinion;

    @FXML
    private TableView<Libro> mostrarLibros;
    @FXML
    private TableView<Autor> mostrarAutores;
    @FXML
    private TableView<Genero> mostrarGeneros;
    @FXML
    private TableView<Pais> mostrarPaises;
    @FXML
    private TableView<AutorLibro> mostrarLibroAutor;

    //    COLUMNAS AUTORLIBRO
    @FXML
    private TableColumn<AutorLibro, String> colTituloLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPaginaLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPublicacionLibroAutor;


    //    COLUMNAS TABLA AUTOR
    @FXML
    private TableColumn<Autor, String> colNombreAutor;
    @FXML
    private TableColumn<Autor, String> colApellidoAutor;
    @FXML
    private TableColumn<Autor, String> colApellido2Autor;
    @FXML
    private TableColumn<Autor, String> colNacimiento;
    @FXML
    private TableColumn<Autor, String> colFallecimiento;
    @FXML
    private TableColumn<Autor, String> colPaisAutor;

    @FXML
    private TableColumn<Genero, String> colNombreGenero;
    @FXML
    private TableColumn<Genero, String> colIDGenero;

    //COLUMNAS TABLA LIBRO
    @FXML
    private TableColumn<Libro, Integer> colPaginas;
    @FXML
    private TableColumn<Libro, Integer> colPublicacion;
    @FXML
    private TableColumn<Libro, String> colTitulo;

    // COLUMNAS TABLA PAÍS
    @FXML
    private TableColumn<Pais, Integer> colIdPais;
    @FXML
    private TableColumn<Pais, String> colNombrePais;
    @FXML
    private TableColumn<Pais, String> colCodigoISO;


    FilteredList<Libro> librosFiltrados;
    FilteredList<Autor> autoresFiltrados;
    FilteredList<Genero> generosFiltrados;
    FilteredList<Pais> paisesFiltrados;

    List<AutorLibro> resultadoConsulta;

    ObservableList<Pais> listaPaisesObservable = FXCollections.observableArrayList();
    ObservableList<Genero> listaGenerosObservable = FXCollections.observableArrayList();
    ObservableList<Libro> listaLibrosObservable = FXCollections.observableArrayList();
    ObservableList<Autor> listaAutoresObservable = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // APLICO UN TEMA DE COLOR U OTRO A LA APP USANDO ATLANTAFX
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());

        //        COLUMNAS DE LA TABLAVIEW LIBROS
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
//        MEDIDAS DE LAS COLUMNAS DE LA TABLA LIBROS
        colTitulo.setPrefWidth(400);
        colPublicacion.setPrefWidth(100);
        colPaginas.setPrefWidth(100);

//        COLUMNAS DE LA TABLEVIEW AUTOR
        colNombreAutor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoAutor.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2Autor.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("yearNacimiento"));
        colFallecimiento.setCellValueFactory(new PropertyValueFactory<>("yearFallecimiento"));
        colPaisAutor.setCellValueFactory(new PropertyValueFactory<>("pais_id"));

//        MEDIDAS DE LAS COLUMNAS DE LA TABLA AUTOR
        colNombreAutor.setPrefWidth(200);
        colApellidoAutor.setPrefWidth(200);
        colApellido2Autor.setPrefWidth(200);
        colNacimiento.setPrefWidth(100);
        colFallecimiento.setPrefWidth(120);
        colPaisAutor.setPrefWidth(90);

        //        COLUMNAS DE LA TABLEVIEW GENERO
        colNombreGenero.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIDGenero.setCellValueFactory(new PropertyValueFactory<>("id"));

//        MEDIDAS DE LAS COLUMNAS DE LA TABLA GENERO
        colNombreGenero.setPrefWidth(200);
        colIDGenero.setPrefWidth(90);

        //        COLUMNAS DE MOSTRAR LOS LIBROS DE CADA AUTOR

        // setCellValueFactory le dice a la columna qué datos mostrar (de dónde sacarlos).
        //
        //setCellFactory le dice a la columna cómo mostrar esos datos (qué estilo, qué hacer con ellos).

        colTituloLibroAutor.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPaginaLibroAutor.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colPublicacionLibroAutor.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));

        //        MEDIDAS DE LAS COLUMNAS DE LA TABLA LIBROXAUTOR
        colTituloLibroAutor.setPrefWidth(350);
        colPaginaLibroAutor.setPrefWidth(90);
        colPublicacionLibroAutor.setPrefWidth(80);

        // COLUMNAS DE LA TABLEVIEW PAIS
        colIdPais.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombrePais.setCellValueFactory(new PropertyValueFactory<>("nombrePais"));
        colCodigoISO.setCellValueFactory(new PropertyValueFactory<>("codigo_ISO"));

        //        MEDIDAS DE LAS COLUMNAS DE LA TABLA PAIS
        colIdPais.setPrefWidth(90);
        colNombrePais.setPrefWidth(400);
        colCodigoISO.setPrefWidth(120);

        //     LA OBSERVABLE SE RELLENA CON UN ARRAYLIST, DESPUÉS METES LA OBSERVABLE EN EL TABLEVIEW
        listaLibrosObservable.addAll(librodao.getAllLibros());
        librosFiltrados = new FilteredList<>(listaLibrosObservable);
        mostrarLibros.setItems(librosFiltrados);

//        LÍNEA DE BUSCADORES A LA IZQUIERDA
        inputBuscarLibro.setAlignment(Pos.CENTER_LEFT);
        inputBuscarAutor.setAlignment(Pos.CENTER_LEFT);
        inputBuscarPais.setAlignment(Pos.CENTER_LEFT);
        inputBuscarGenero.setAlignment(Pos.CENTER_LEFT);


//        LISTENER PARA EL BUSCADO POR TITULO
        inputBuscarLibro.textProperty().addListener((observable, oldValue, newValue) -> {
            // en el listener de libros
            panelLateral.setVisible(true);
            panelLateral.setManaged(true);
            filtrarLibros(newValue);
        });

// Listener descripción libro
        mostrarLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) return;  // ← añadir esto PARA PROTEGER EL BUSCADOR DE CUANDO NO SE HAYA SELECCIONADO NINGÚN ELEMENTO AÚN
            panelLateral.setVisible(true);
            panelLateral.setManaged(true);
            if (newValue.getDescripcion() == null) {
                mostrarDescripcion.setText("Información no añadida.".toUpperCase());
            } else {
                mostrarDescripcion.setText(newValue.getDescripcion());
                mostrarOpinion.setText(newValue.getOpinion());
            }
        });

//        OBSERVABLE DE AUTORES
        listaAutoresObservable.addAll(autordao.getAllAutores());
        autoresFiltrados = new FilteredList<>(listaAutoresObservable);
        mostrarAutores.setItems(autoresFiltrados);

//        LISTENER PARA EL BUSCADO POR NOMBRE O APELLIDO
        inputBuscarAutor.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAutores(newValue);
        });

//        OBSERVABLE DE GENEROS
        listaGenerosObservable.addAll(generodao.getAllGeneros());
        generosFiltrados = new FilteredList<>(listaGenerosObservable);
        mostrarGeneros.setItems(generosFiltrados);

        // OBSERVABLE DE PAISES
        listaPaisesObservable.addAll(paisdao.getAllPaises());
        paisesFiltrados = new FilteredList<>(listaPaisesObservable);
        mostrarPaises.setItems(paisesFiltrados);

        // LISTER PARA EL BUSCADO POR NOMBRE
        inputBuscarPais.textProperty().addListener((observable, OldValue, newValue) -> {
            filtrarPaises(newValue);
        });

//        LISTENER PARA BUSCAR EL GÉNERO
        inputBuscarGenero.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarGeneros(newValue);
        });

        //        DECLARO LA OBSERVABLE DE AUTORLIBRO
        ObservableList<AutorLibro> autorLibroObservable = FXCollections.observableArrayList();
        resultadoConsulta = new ArrayList<>();

//        LISTENER PARA TRAER LOS LIBROS DEL AUTOR QUE HAYA SIDO SELECCIONADO EN ESE MOMENTO
// Listener libros por autor
        mostrarAutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;  // ← añadir esto PARA PROTEGER EL BUSCADOR DE CUANDO NO SE HAYA SELECCIONADO NINGÚN ELEMENTO AÚN.
            autorLibroObservable.clear();
            resultadoConsulta = autorlibrodao.getLibrosPorAutor(newValue.getId());
            autorLibroObservable.addAll(resultadoConsulta);
            mostrarLibroAutor.setItems(autorLibroObservable);
        });

//ESTADO INICIAL DE LAS TABLEVIEW
        mostrarLibros.setVisible(true);
        mostrarAutores.setVisible(false);
        mostrarGeneros.setVisible(false);
        mostrarLibroAutor.setVisible(false);

        Platform.runLater(() -> inputBuscarLibro.requestFocus());

    }

    //    MÉTODOS
//    MÉTOoDO PARA DEFINIR EL PREDICADO DE FILTRADO
    @FXML
    void filtrarLibros(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            librosFiltrados.setPredicate(l -> true);  // Muestra todos
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            librosFiltrados.setPredicate(l ->
                    l.getTitulo().toLowerCase().contains(busqueda)
            );
        }
    }

//    CONSIDERO QUE ESTA ES LA ÚNICA LISTA QUE TIENE SENTIDO EN LA CUAL IMPLEMENTAR TAMBIÉN LA BÚSQUEDA POR ID, YA QUE ALGUNAS OTRAS TABLAS MUESTRAN EL ID DEL PAÍS Y NO EL NOMBRE
    public void filtrarPaises(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            paisesFiltrados.setPredicate(l -> true);
        } else {
            String busqueda = textoBusqueda.toLowerCase();
//            SETPREDICATE LO QUE HACE ES QUE EJECUTA EL CÓDIGO DE DENTRO PARA CADA ELEMENTO DE LA LISTA. APLICA UN FILTRO (EL CÓDIGO DE DENTRO) Y LOS ELEMENTOS QUE LO PASAN, SE MUESTRAN Y LOS QUE NO, PUES NO SE MUESTRAN.
            paisesFiltrados.setPredicate(l -> {

//                SI ES TRUE, EL PAÍS SE MUESTRA, SI ES FALSE, NO SE MUESTRA
                boolean coincideNombre = l.getNombrePais().toLowerCase().contains(busqueda);
                boolean coincideISO = l.getCodigo_ISO().toLowerCase().contains(busqueda);

//                DAMOS POR HECHO QUE EL ID NO COINCIDE
                boolean coincideId = false;
                try {
//                    SI RESULTA QUE EL ID BUSCADO COINCIDE CON ALGÚN PAÍS, ÉSTE SE MOSTRARÁ, SINO NO.
                    coincideId = Integer.parseInt(textoBusqueda) == l.getId();
                } catch (NumberFormatException e) {
                    // No es un número, simplemente coincideId se queda en false
                }
                return coincideNombre || coincideId || coincideISO;
            });
        }
    }

    void filtrarAutores(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            autoresFiltrados.setPredicate(a -> true);
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            autoresFiltrados.setPredicate(a ->
                    a.getNombre().toLowerCase().contains(busqueda) ||
                            (a.getApellido1() != null && a.getApellido1().toLowerCase().contains(busqueda))
            );
        }
    }


    public void filtrarGeneros(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            generosFiltrados.setPredicate(a -> true);
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            generosFiltrados.setPredicate(a ->
                    a.getNombre().toLowerCase().contains(busqueda)
            );
        }
    }

    @FXML
    public void mostrarLibros() {
        panelLateral.setVisible(false);
        panelLateral.setManaged(false);

        botonEliminarElemento.setText("Eliminar libro");
        botonEditarElemento.setVisible(true);
        botonEditarElemento.setText("Editar libro");
        mostrarGeneros.setVisible(false);
        mostrarGeneros.setManaged(false);

        mostrarAutores.setVisible(false);
        mostrarAutores.setManaged(false);

        mostrarLibroAutor.setVisible(false);
        mostrarLibroAutor.setManaged(false);

        inputBuscarAutor.setVisible(false);
        inputBuscarGenero.setVisible(false);
        inputBuscarPais.setVisible(false);

        mostrarPaises.setVisible(false);
        mostrarPaises.setManaged(false);


        //        SOLO MUESTRO LOS LIBROS Y EL BUSCADOR DE LIBROS
        mostrarLibros.setVisible(true);
        mostrarLibros.setManaged(true);

        inputBuscarLibro.setVisible(true);
        inputBuscarLibro.setManaged(true);
        mostrarDescripcion.setVisible(true);
        mostrarDescripcion.setManaged(true);
//        NO TENÍA SENTIDO PEDIR LA OPINIÓN AL CREAR EL LIBRO PERO NO MOSTRARLA AL MOSTRAR TODOS LOS LIBROS
        mostrarOpinion.setVisible(true);
        mostrarOpinion.setManaged(true);
    }


    public void mostrarAutores() {
        panelLateral.setVisible(true);
        panelLateral.setManaged(true);

        botonEliminarElemento.setText("Eliminar autor");
        botonEditarElemento.setVisible(true);
        botonEditarElemento.setText("Editar autor");
        mostrarLibros.setVisible(false);
        mostrarLibros.setManaged(false);
        mostrarGeneros.setVisible(false);
        mostrarGeneros.setManaged(false);
        inputBuscarLibro.setVisible(false);
        inputBuscarGenero.setVisible(false);
        inputBuscarPais.setVisible(false);
        mostrarDescripcion.setVisible(false);
        mostrarDescripcion.setManaged(false);
        mostrarPaises.setVisible(false);
        mostrarPaises.setManaged(false);

        mostrarAutores.setVisible(true);
        mostrarAutores.setManaged(true);
        inputBuscarAutor.setVisible(true);
        inputBuscarAutor.setManaged(true);


        // ↓ ESTO ES LO QUE FALTABA

        mostrarLibroAutor.setVisible(true);
        mostrarLibroAutor.setManaged(true);  // ← esto también faltaba
    }

    public void mostrarGeneros() {
        panelLateral.setVisible(false);
        panelLateral.setManaged(false);

        botonEliminarElemento.setText("Eliminar género");
        botonEditarElemento.setVisible(false);
        mostrarLibros.setVisible(false);
        mostrarLibros.setManaged(false);

        mostrarAutores.setVisible(false);
        mostrarAutores.setManaged(false);

        mostrarLibroAutor.setVisible(false);
        mostrarLibroAutor.setManaged(false);

        inputBuscarAutor.setVisible(false);
        inputBuscarLibro.setVisible(false);
        inputBuscarPais.setVisible(false);
        mostrarDescripcion.setVisible(false);

        mostrarPaises.setVisible(false);
        mostrarPaises.setManaged(false);

        //        SOLO MUESTRO LOS GENEROS Y EL BUSCADOR DE GENEROS
        mostrarGeneros.setVisible(true);
        mostrarGeneros.setManaged(true);

        inputBuscarGenero.setVisible(true);
        inputBuscarGenero.setManaged(true);
    }

    public void mostrarPaises() {
        panelLateral.setVisible(false);
        panelLateral.setManaged(false);

        botonEliminarElemento.setText("Eliminar país");
        botonEditarElemento.setVisible(false);
        mostrarLibros.setVisible(false);
        mostrarLibros.setManaged(false);

        mostrarAutores.setVisible(false);
        mostrarAutores.setManaged(false);

        mostrarLibroAutor.setVisible(false);
        mostrarLibroAutor.setManaged(false);

        inputBuscarAutor.setVisible(false);
        inputBuscarLibro.setVisible(false);
        mostrarDescripcion.setVisible(false);
        //        SOLO MUESTRO LOS GENEROS Y EL BUSCADOR DE GENEROS
        mostrarGeneros.setVisible(false);
        mostrarGeneros.setManaged(false);

        inputBuscarGenero.setVisible(false);
        inputBuscarGenero.setManaged(false);

        inputBuscarPais.setVisible(true);
        inputBuscarPais.setManaged(true);
        mostrarPaises.setVisible(true);
        mostrarPaises.setManaged(true);

    }

    public void cambiarVentanas(Event e) throws IOException {
        Button botonVentana = (Button) e.getSource();
        String rutaVentana = "";
        Stage primaryStage = new Stage();

        int anchoVentana = 0, altoVentana = 0;

        switch (botonVentana.getId()) {
            case "addLibro":
                rutaVentana = "/ventanaAddLibro.fxml";
                primaryStage.setTitle("AÑADIR LIBRO");
                anchoVentana = 1024;
                altoVentana = 768;
                break;
            case "addAutor":
                rutaVentana = "/ventanaAddAutor.fxml";
                primaryStage.setTitle("AÑADIR AUTOR");
                anchoVentana = 1024;
                altoVentana = 700;
                break;
            case "addGenero":
                rutaVentana = "/ventanaAddGenero.fxml";
                primaryStage.setTitle("AÑADIR GÉNERO");
                anchoVentana = 800;
                altoVentana = 300;
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentana));
        Parent root = loader.load();

        Scene scene = new Scene(root, anchoVentana, altoVentana);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        // AL CERRARSE CUALQUIERA DE LAS VENTANAS, ESTA LÍNEA SE DISPARA Y LO QUE HACE ES EJECUTAR LO QUE SEA QUE LO PONGAMOS DENTRO.
        primaryStage.setOnHiding(event -> {
            //System.out.println("¡HOLA! LA VENTANA SE CERRÓ Y ESTO SE ESCRIBIÓ");
            actualizarVentana();
        });
    }

    public void eliminarElemento() {

        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        Alertas alertainfo = new Alertas();

        //ELIMINAR LIBRO
        if (mostrarLibros.isVisible()) {
            Libro seleccionado = mostrarLibros.getSelectionModel().getSelectedItem();

            alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
            alertaConfirmacion.setContentText("¿ELIMINAR LIBRO " + seleccionado.getTitulo() + "?");
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                librodao.borrarLibro(seleccionado.getId());
                listaLibrosObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo("SE HA ELIMINADO EL LIBRO", seleccionado.getTitulo(), "LIBRO ELIMINADO");

            }
        }

        //ELIMINAR AUTOR
        if (mostrarAutores.isVisible()) {
            Autor seleccionado = mostrarAutores.getSelectionModel().getSelectedItem();
            alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
            alertaConfirmacion.setContentText("ELIMINAR AUTOR " + seleccionado.getNombre() + " " + seleccionado.getApellido1());
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                autordao.borrarAutor(seleccionado.getId());
                listaAutoresObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo("AUTOR ELIMINADO", seleccionado.getNombre() + " " + seleccionado.getApellido1(), "EL AUTOR SE HA ELIMINADO");
            }
        }
        //ELIMINAR GÉNERO
        if (mostrarGeneros.isVisible()) {
            Genero seleccionado = mostrarGeneros.getSelectionModel().getSelectedItem();
            alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
            alertaConfirmacion.setContentText("ELIMINAR GÉNERO " + seleccionado.getNombre() + " ?");
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                generodao.borrarGenero(seleccionado.getId());
                listaGenerosObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo("SE HA ELIMINADO EL GÉNERO LITERARIO ", seleccionado.getNombre(), " ESTE GÉNERO SE ELIMINÓ");
            }
        }

        //ELIMINAR PAÍS
        if (mostrarPaises.isVisible()) {
            Pais seleccionado = mostrarPaises.getSelectionModel().getSelectedItem();

            alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
            alertaConfirmacion.setContentText("¿ELIMINAR PAÍS " + seleccionado.getNombrePais() + " ?");
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                paisdao.borrarPais(seleccionado.getId());
                listaPaisesObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo("SE HA ELIMINADO EL PAÍS ", seleccionado.getNombrePais(), " PAÍS ELIMINADO");
            }
        }
    }

    public void AbrirVentanaEditarElemento() throws IOException {

//        ESTA VENTANA SOLAMENTE SE ENCARGA DE ABRIR UNA VENTANA DE EDICIÓN U OTRA SEGÚN EL ELEMENTO SE ESTÉ PULSANDO EN ESE MOMENTO, MÁS BIEN, SEGÚN LA LISTA QUE SE ESTÉ MOSTRANDO EN ESE MOMENTO. EL MÉThODO DE EDICIÓN EN SÍ SE HACE EN LOS CONTROLADORES DE CADA VENTANA. CREO QUE NO TIENE SENTIDO QUERER EDITAR UNA TABLA CATÁLOGO COMO LISTA O PAÍS... ES MÁS FÁCIL BORRAR Y VOLVER A AÑADIR LO QUE SEA QUE SE QUIERE MODIFICAR.

        String rutaVentana = " ";
        Stage primaryStage = new Stage();

        //EDITAR LIBRO
        if (mostrarLibros.getSelectionModel().getSelectedItem() != null && mostrarLibros.isVisible()) {
            rutaVentana = "/ventanaEditarLibro.fxml";
        }
        // EDITAR AUTOR
        else if (mostrarAutores.getSelectionModel().getSelectedItem() != null && mostrarAutores.isVisible()) {
            rutaVentana = "/ventanaEditarAutor.fxml";

        } else {
//            PARA CUALQUIER OTRA SITUACIÓN
            Alertas alerta = new Alertas();
            alerta.mostrarAlertaInfo("FALTAN LIBRO O AUTOR", "DEBES ELEGIR UN LIBRO O AUTOR A EDITAR", "ELIGE UN ELEMENTO");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentana));
        Parent root = loader.load();

//        PARA PASARME EL ELEMENTO SELECCIONADO DESDE ESTE CONTROLADOR A LOS CONTROLADORES DE EDICIÓN, DECLARO UN TIPO OBJECT Y DESPUÉS, DECIDO QUÉ TIPO DE ELEMENTO ES CON INSTANCE OF.
//        ¿CÓMO OBTENGO EL TIPO? CON LOADER.GETCONTROLLER. ESTO TOMA EL CONTROLADOR DE LA VENTANA QUE SE HA CARGADO
        // INYECCIÓN: se decide el tipo en tiempo de ejecución con instanceof
        Object controller = loader.getController();

        if (controller instanceof ventanaEditarLibroController c) {
            c.setLibro(mostrarLibros.getSelectionModel().getSelectedItem());
        } else if (controller instanceof ventanaEditarAutorController c) {
            c.setAutor(mostrarAutores.getSelectionModel().getSelectedItem());
        }

        Scene scene = new Scene(root, 1024, 700);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

        primaryStage.setOnHiding(event -> actualizarVentana());
    }

    public void actualizarVentana() {
        listaLibrosObservable.clear();
        listaLibrosObservable.addAll(librodao.getAllLibros());

        listaAutoresObservable.clear();
        listaAutoresObservable.addAll(autordao.getAllAutores());

        listaGenerosObservable.clear();
        listaGenerosObservable.addAll(generodao.getAllGeneros());
    }

    public void cerrarVentanaPrincipal(){
        Alertas alertas = new Alertas();

        Alert confirmacionCerrarVentana = alertas.mostrarAlertaConfirmacion("SALIR","¿ESTÁS SEGURO DE QUE QUIERES SALIR?",null);

        if(confirmacionCerrarVentana.getResult() == ButtonType.OK){
            Platform.exit();
        }
    }

//    ABRIR EL MANUAL DE USUARIO CREADO CON CLAUDE Y MOSTRARLO EN UNA VENTANA MODAL
    public void redirigirManualUsuario() throws IOException {
        Stage stage = new Stage();
        WebView webView = new WebView();
        webView.getEngine().loadContent(
                new String(
                        getClass().getResourceAsStream("/manual_usuario_biblioteca.html").readAllBytes(),
                        StandardCharsets.UTF_8
                ),
                "text/html");

        Scene scene = new Scene(webView, 1000, 800);
        stage.setTitle("Manual de usuario");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

//        MÉTODOS QUE SE ACTIVAN AL PULSAR UN MENÚ ITEM U OTRO. REALMENTE SOLO EXISTEN PARA EVITAR REPETIR EL CÓDIGO DE APERTURA DE LA VENTANA. CADA UNO DE ELLOS LLAMA AL MÉTHODO QUE ABRE UNA U OTRA VENTANA.
    public void numLibrosPorGenero() throws IOException {
        abrirVistaResumen("numLibrosPorGenero","Nº DE LIBROS POR GÉNERO");
    }

    public void numlibrosPorAutor() throws IOException {
        abrirVistaResumen("librosPorAutor","Nº DE LIBROS POR AUTOR");
    }

    public void librosSXXI() throws IOException {
        abrirVistaResumen("siglo21","LIBROS DEL SIGLO XXI");
    }


    public void librosAnterioresSXXI() throws IOException {
        abrirVistaResumen("anteriorAsiglo21","LIBROS ANTERIORES AL SIGLO XXI");
    }

    //    MÉTHODO QUE ABRE UNA VENTANA U OTRA DEPENDIENDO DE LA VISTA QUE LA HAYA LLAMADO.
    public void abrirVistaResumen(String vista,String titulo) throws IOException {
        String rutaVentana = "";
        Stage primaryStage = new Stage();

        switch (vista) {

            case "numLibrosPorGenero":
                rutaVentana = "/ventanaCantidadLibrosPorGenero.fxml";
                primaryStage.setTitle(titulo);

                break;
            case "librosPorAutor":
                rutaVentana = "/ventanaAutoresMasLeidos.fxml";
                primaryStage.setTitle(titulo);

                break;
            case "siglo21":
                rutaVentana = "/ventanaLibrosPosteriores.fxml";
                primaryStage.setTitle(titulo);
                break;

            case "anteriorAsiglo21":
                rutaVentana = "/ventanaLibrosAnteriores.fxml";
                primaryStage.setTitle(titulo);
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentana));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

}