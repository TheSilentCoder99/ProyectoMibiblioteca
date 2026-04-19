package com.alan.controladores;
//AtlantaFX es una librería de temas visuales para JavaFX. CON ELLA PUEDO CAMBIAR EL TEMA DE LA APP
import atlantafx.base.theme.Dracula;
import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.*;
import com.alan.DataAccesObjects.AutorLibroDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pantallaPrincipalController {

    LibroDAO librodao = new LibroDAO();
    AutorDAO autordao = new AutorDAO();
    GeneroDAO generodao = new GeneroDAO();
    AutorLibroDAO autorlibrodao = new AutorLibroDAO();

    @FXML
    private
    Button botonEliminarElemento;

    @FXML
    private Button addAutor;

    @FXML
    private Button addGenero;

    @FXML
    private Button addLibro;

    @FXML
    private TextField inputBuscarLibro;

    @FXML
    private TextField inputBuscarAutor;

    @FXML
    private TextField inputBuscarGenero;

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
    private TableView<Libro> mostrarLibros;
    @FXML
    private TableView<Autor> mostrarAutores;
    @FXML
    private TableView<Genero> mostrarGeneros;
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

    FilteredList<Libro> librosFiltrados;
    FilteredList<Autor> autoresFiltrados;
    FilteredList<Genero> generosFiltrados;
    List<AutorLibro> resultadoConsulta;
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


//        COLUMNAS DE LA TABLEVIEW AUTOR
        colNombreAutor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoAutor.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2Autor.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("yearNacimiento"));
        colFallecimiento.setCellValueFactory(new PropertyValueFactory<>("yearFallecimiento"));
        colPaisAutor.setCellValueFactory(new PropertyValueFactory<>("pais_id"));


        //        COLUMNAS DE LA TABLEVIEW GENERO
        colNombreGenero.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIDGenero.setCellValueFactory(new PropertyValueFactory<>("id"));

        //        COLUMNAS DE MOSTRAR LOS LIBROS DE CADA AUTOR
        colTituloLibroAutor.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPaginaLibroAutor.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colPublicacionLibroAutor.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));


        //     LA OBSERVABLE SE RELLENA CON UN ARRAYLIST, DESPUÉS METES LA OBSERVABLE EN EL TABLEVIEW
        listaLibrosObservable.addAll(librodao.getAllLibros());
        librosFiltrados = new FilteredList<>(listaLibrosObservable);
        mostrarLibros.setItems(librosFiltrados);


//        LISTENER PARA EL BUSCADO POR TITULO
        inputBuscarLibro.textProperty().addListener((observable, oldValue, newValue) -> {
            // en el listener de libros
            panelLateral.setVisible(true);
            panelLateral.setManaged(true);
            filtrarLibros(newValue);
        });

//        LISTENER PARA LA DESCRIPCIÓN
        mostrarLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            // en el listener de autores
            panelLateral.setVisible(true);
            panelLateral.setManaged(true);
            if(newValue.getDescripcion() == null){
                mostrarDescripcion.setText("Descripción no añadida.");
            } else{
                mostrarDescripcion.setText(newValue.getDescripcion());
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

//        LISTENER PARA BUSCAR EL GÉNERO
        inputBuscarGenero.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarGeneros(newValue);
        });


        //        DECLARO LA OBSERVABLE DE AUTORLIBRO
        ObservableList<AutorLibro> autorLibroObservable = FXCollections.observableArrayList();
        resultadoConsulta = new ArrayList<>();

//        LISTENER PARA TRAER LOS LIBROS DEL AUTOR QUE HAYA SIDO SELECCIONADO EN ESE MOMENTO
        mostrarAutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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

    void filtrarAutores(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            autoresFiltrados.setPredicate(a -> true);  // Muestra todos
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            autoresFiltrados.setPredicate(a ->
                    a.getNombre().toLowerCase().contains(busqueda) ||
                            a.getApellido1().toLowerCase().contains(busqueda)
            );
        }
    }

    void filtrarGeneros(String textoBusqueda) {
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
        botonEliminarElemento.setText("Eliminar libro");
        mostrarGeneros.setVisible(false);
        mostrarGeneros.setManaged(false);

        mostrarAutores.setVisible(false);
        mostrarAutores.setManaged(false);

        mostrarLibroAutor.setVisible(false);
        mostrarLibroAutor.setManaged(false);

        inputBuscarAutor.setVisible(false);
        inputBuscarGenero.setVisible(false);

        //        SOLO MUESTRO LOS LIBROS Y EL BUSCADOR DE LIBROS
        mostrarLibros.setVisible(true);
        mostrarLibros.setManaged(true);

        inputBuscarLibro.setVisible(true);
        mostrarDescripcion.setVisible(true);
    }


    public void mostrarAutores() {
        botonEliminarElemento.setText("Eliminar autor");
        mostrarLibros.setVisible(false);
        mostrarLibros.setManaged(false);

        mostrarGeneros.setVisible(false);
        mostrarGeneros.setManaged(false);

        inputBuscarLibro.setVisible(false);
        inputBuscarGenero.setVisible(false);
        mostrarDescripcion.setVisible(false);

//        SOLO MUESTRO LOS AUTORES Y EL BUSCADOR DE AUTORES
        mostrarAutores.setVisible(true);
        mostrarAutores.setManaged(true);

        inputBuscarAutor.setVisible(true);
        mostrarLibroAutor.setVisible(true);
    }

    public void mostrarGeneros() {
        botonEliminarElemento.setText("Eliminar género");
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
        mostrarGeneros.setVisible(true);
        mostrarGeneros.setManaged(true);

        inputBuscarGenero.setVisible(true);
    }

    public void cambiarVentanas(Event e) throws IOException {
        Button botonVentana = (Button) e.getSource();
        String rutaVentana = "";
        Stage primaryStage = new Stage();

        switch (botonVentana.getId()) {
            case "addLibro":
                rutaVentana = "/ventanaAddLibro.fxml";
                primaryStage.setTitle("AÑADIR LIBRO");
                break;
            case "addAutor":
                rutaVentana = "/ventanaAddAutor.fxml";
                primaryStage.setTitle("AÑADIR AUTOR");
                break;
            case "addGenero":
                rutaVentana = "/ventanaAddGenero.fxml";
                primaryStage.setTitle("AÑADIR GÉNERO");
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentana));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
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
                    alertainfo.mostrarAlertaInfo( "SE HA ELIMINADO EL LIBRO",seleccionado.getTitulo(),"LIBRO ELIMINADO");

                }

            }

        //ELIMINAR AUTOR
        if (mostrarAutores.isVisible()) {
            Autor seleccionado = mostrarAutores.getSelectionModel().getSelectedItem();
                alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
                alertaConfirmacion.setContentText("ELIMINAR AUTOR " + seleccionado.getNombre() +" "+ seleccionado.getApellido1());
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                autordao.borrarAutor(seleccionado.getId());
                listaAutoresObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo( "AUTOR ELIMINADO",seleccionado.getNombre() + " "+ seleccionado.getApellido1(),"EL AUTOR SE HA ELIMINADO");
            }
        }
            //ELIMINAR GÉNERO
        if (mostrarGeneros.isVisible()) {
            Genero seleccionado = mostrarGeneros.getSelectionModel().getSelectedItem();
            alertaConfirmacion.setHeaderText("CONFIRMAR ELIMINACIÓN");
            alertaConfirmacion.setContentText("ELIMINAR GÉNERO" + seleccionado.getNombre() + "?");
            alertaConfirmacion.showAndWait();
            if (alertaConfirmacion.getResult() == ButtonType.OK) {
                generodao.borrarGenero(seleccionado.getId());
                listaGenerosObservable.remove(seleccionado);
                alertainfo.mostrarAlertaInfo( "SE HA ELIMINADO EL GÉNERO LITERARIO", seleccionado.getNombre(),"ESTE GÉNERO SE ELIMINÓ");
            }
        }

        }

        public void actualizarVentana(){

            listaLibrosObservable.clear();
            listaLibrosObservable.addAll(librodao.getAllLibros());

            listaAutoresObservable.clear();
            listaAutoresObservable.addAll(autordao.getAllAutores());

            listaGenerosObservable.clear();
            listaGenerosObservable.addAll(generodao.getAllGeneros());
        }
    }