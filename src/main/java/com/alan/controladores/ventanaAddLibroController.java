package com.alan.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.AutorLibroDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.Alertas;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.clases.Autor;
import com.alan.clases.Genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ventanaAddLibroController {

    LibroDAO librodao = new LibroDAO();
    Alertas tipoAlerta = new Alertas();

    @FXML
    private Button insertarGenero;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Autor> comboBoxAutores;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private TextArea inputOpinion;

    @FXML
    private TextField inputPaginas;

    @FXML
    private TextField inputTitulo;

    @FXML
    private TextField inputYearPublicacion;

    @FXML
    private Label lblMensaje;

    @FXML
    private ListView<Autor> listaTablaAutor;

    @FXML
    private ListView<Genero> listaTablaGenero;


    ObservableList<Autor> autoresObservable = FXCollections.observableArrayList();
    AutorDAO autordao = new AutorDAO();

    ObservableList<Genero> generosObservable = FXCollections.observableArrayList();
    GeneroDAO generodao = new GeneroDAO();

    AutorLibroDAO autorlibrodao = new AutorLibroDAO();
    List<Integer> listaAutoresParaLibro = new ArrayList<>();
    List<Integer> listaGenerosParaLibro = new ArrayList<>();

    public void initialize() {
        autoresObservable.addAll(autordao.getAllAutores());
        listaTablaAutor.setItems(autoresObservable);

        generosObservable.addAll(generodao.getAllGeneros());
        listaTablaGenero.setItems(generosObservable);

//        DEFINO LA LISTVIEW QUE MUESTRA LOS AUTORES y LA DE GÉNEROS COMO DE SELECCIÓN MÚLTIPLE PARA QUE UN MISMO LIBRO PUEDA PERTENECER A VARIOS GÉNEROS
        listaTablaAutor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaTablaGenero.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void recargarTabla() {
        autoresObservable.clear();
        autoresObservable.addAll(autordao.getAllAutores());
    }

    //    MANEJO DE EXCEPCIONES
    public void guardarLibro() {
        try {
            String titulo = inputTitulo.getText();
            String yearPublicacion = inputYearPublicacion.getText();
            String paginas = inputPaginas.getText();

//            Guardo estas listas para trabajar con más comodidad, pero no son modificables
            ObservableList<Autor> autoresSeleccionados = listaTablaAutor.getSelectionModel().getSelectedItems();
            ObservableList<Genero> generosSeleccionados = listaTablaGenero.getSelectionModel().getSelectedItems();

            //            COMPROBAR QUE SE HA ELEGIDO AL MENOS UN GÉNERO Y AUTOR PARA EL LIBRO INSERTADO
            if(listaTablaAutor.getSelectionModel().getSelectedItem() == null || listaTablaGenero.getSelectionModel().getSelectedItem() == null){
                tipoAlerta.mostrarAlertaError("FALTAN DATOS","DEBES SELECCIONAR AL MENOS UN GÉNERO Y UN AUTOR PARA EL LIBRO","INGRESAR GÉNERO Y AUTOR");
                return;
            }

            // VALIDACIONES SEPARADAS PARA DAR MENSAJES MÁS ESPECÍFICOS
            if (titulo.length() < 2) {
                tipoAlerta.mostrarAlertaWarning("TÍTULO INCORRECTO", "EL TÍTULO DEBE TENER AL MENOS 2 CARACTERES.", "RELLENAR CAMPO TÍTULO");
                return;
            }
            if (paginas.isEmpty()) {
                tipoAlerta.mostrarAlertaWarning("PÁGINAS VACÍAS", "DEBES INGRESAR EL Nº DE PÁGINAS.", "RELLENAR CAMPO PÁGINAS");
                return;
            }
            if (yearPublicacion.isEmpty()) {
                tipoAlerta.mostrarAlertaWarning("AÑO VACÍO", "DEBES INGRESAR EL AÑO DE PUBLICACIÓN.", "RELLENAR CAMPO AÑO");
                return;
            }
            // VALIDAR QUE PÁGINAS Y AÑO SON NÚMEROS ANTES DE PARSEAR
            if (!paginas.matches("[0-9]+") || Integer.parseInt(paginas) < 1) {
                tipoAlerta.mostrarAlertaWarning("FORMATO INCORRECTO", "EL Nº DE PÁGINAS SOLO PUEDE CONTENER NÚMEROS.", "REVISAR CAMPO PÁGINAS");
                return;
            }
            if (!yearPublicacion.matches("-?[0-9]+")) { // -? PERMITE AÑOS NEGATIVOS (a.C.)
                tipoAlerta.mostrarAlertaWarning("FORMATO INCORRECTO", "EL AÑO DE PUBLICACIÓN SOLO PUEDE CONTENER NÚMEROS.", "REVISAR CAMPO AÑO");
                return;
            }

            int yearPublicacionParseado = Integer.parseInt(yearPublicacion);
            int paginasParseadas = Integer.parseInt(paginas);

            String descripcion = inputDescripcion.getText().isEmpty() ? " " : inputDescripcion.getText();
            String opinion = inputOpinion.getText().isEmpty() ? " " : inputOpinion.getText();

            Alert resultadoIngresoLibro = tipoAlerta.mostrarAlertaConfirmacion("INGRESAR LIBRO", "VAS A INGRESAR EL SIGUIENTE LIBRO EN LA BBDD: " + titulo.toUpperCase() + " ¿ESTÁS SEGURO DE CONTINUAR?", "INGRESAR NUEVO LIBRO");
            if (resultadoIngresoLibro.getResult() == ButtonType.OK) {
                int idLibro = librodao.insertarLibro(titulo, yearPublicacionParseado, paginasParseadas, descripcion, opinion);

                // PROBLEMA: SI EL USUARIO CANCELA LA CONFIRMACIÓN, idLibro ES 0
                // Y SE INTENTAN INSERTAR AUTORES CON id=0, LO QUE FALLA EN LA BD
                // AHORA SOLO SE INSERTAN AUTORES SI EL LIBRO SE GUARDÓ CORRECTAMENTE (EN LIBRODAO SE DEVUELVE EL ID DEL LIBRO SI LA INSERCIÓN FUE CORRECTA O -1 SI FALLÓ)
                if (idLibro != -1) {

                        for (int i = 0; i < autoresSeleccionados.size(); i++) {
                            listaAutoresParaLibro.add(autoresSeleccionados.get(i).getId());
                        }

                        for (int i = 0; i < generosSeleccionados.size(); i++) {
                            listaGenerosParaLibro.add(generosSeleccionados.get(i).getId());
                        }

                    autorlibrodao.actualizarTablaLibroAutor(idLibro, listaAutoresParaLibro);
                    generodao.actualizarTablaGeneroLibro(idLibro, listaGenerosParaLibro);

                    tipoAlerta.mostrarAlertaInfo("LIBRO INSERTADO", "SE HA INSERTADO EL LIBRO: " + titulo.toUpperCase(), "INSERTAR LIBRO.");


                    //                VACÍO LAS LISTAS DE GÉNERO Y AUTORES PARA QUE NO SE ACUMULEN
                    listaAutoresParaLibro.clear();
                    listaGenerosParaLibro.clear();
                    listaTablaAutor.getSelectionModel().clearSelection();
                    listaTablaGenero.getSelectionModel().clearSelection();

                } else {
                    tipoAlerta.mostrarAlertaError("ERROR AL GUARDAR", "NO SE HA PODIDO GUARDAR EL LIBRO. REVISA LA CONEXIÓN A LA BASE DE DATOS.", "REVISAR CONEXIÓN.");
                    return;
                }

                // LIMPIAR CAMPOS TRAS AÑADIR UN LIBRO
                List<Node> elementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion));
                for (Node elemento : elementosVentana) {
                    if (elemento instanceof TextField tf) tf.clear();
                    else if (elemento instanceof TextArea ta) ta.clear();
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            tipoAlerta.mostrarAlertaError("HA OCURRIDO UN ERROR", "SE HA INGRESADO UN FORMATO ERRÓNEO EN ALGÚN CAMPO.", "REVISAR CAMPOS");
        }

    }

    public void abrirInsertarAutor() throws IOException {
        Stage primaryStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanaAddAutor.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

        primaryStage.setOnHiding(event -> {
            recargarTabla();
        });
    }

    public void abrirInsertarGenero() throws IOException {
        Stage primaryStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanaAddGenero.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

        primaryStage.setOnHiding(event -> {
            recargarTabla();
        });
    }

    public void cerrarVentanaLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion, inputDescripcion, inputOpinion));

        if (inputPaginas.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputTitulo.getText().isEmpty()) {
            Stage stage = (Stage) inputTitulo.getScene().getWindow();
            stage.close();

        } else {
            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
                }
            }
        }
    }
}

