package com.alan.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.AutorLibroDAO;
import com.alan.clases.Alertas;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.clases.Autor;
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
    private Button btnCancelar;

    @FXML
    private ComboBox comboBoxAutores;

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


    ObservableList<Autor> autoresObservable = FXCollections.observableArrayList();
    AutorDAO autordao = new AutorDAO();
    AutorLibroDAO autorlibrodao = new AutorLibroDAO();
    List<Integer> actualizarAutorLibro = new ArrayList<>();

    public void initialize() {
        autoresObservable.addAll(autordao.getAllAutores());
        listaTablaAutor.setItems(autoresObservable);
//        IMPLEMENTAR BÚSQUEDA POR LETRA, ES DECIR, QUE AL PULSAR UNA LETRA SALGAN TODOS LOS AUTORES QUE EMPIECEN POR ESA LETRA

//        DEFINO LA LISTVIEW QUE MUESTRA LOS AUTORES COMO DE SELECCIÓN MÚLTIPLE
        listaTablaAutor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void recargarTabla(){
        autoresObservable.clear();
        autoresObservable.addAll(autordao.getAllAutores());
    }

    public void cerrarVentanaLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion, inputDescripcion, inputOpinion));

        if (inputPaginas.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputTitulo.getText().isEmpty()) {
//            OBTENER VENTANA EN LA QUE ESTAS
            Stage stage = (Stage) inputTitulo.getScene().getWindow();
            stage.close();

        } else {
            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
//                    AÑADIR QUE SE BORRE TAMBIÉN EL CONTENIDO DEL TEXTAREA
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
                }
            }
        }
    }

//    MANEJO DE EXCEPCIONES MEJORADO CON CLAUDE
    public void guardarLibro() {
        try {
            String titulo = inputTitulo.getText();
            String yearPublicacion = inputYearPublicacion.getText();
            String paginas = inputPaginas.getText();

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
            if (!paginas.matches("[0-9]+")) {
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

            Alert resultadoIngresoLibro = tipoAlerta.mostrarAlertaConfirmacion("INGRESAR LIBRO", "VAS A INGRESAR EL SIGUIENTE LIBRO EN LA BBDD: " + titulo + " ¿ESTÁS SEGURO DE CONTINUAR?", "INGRESAR NUEVO LIBRO");

            if (resultadoIngresoLibro.getResult() == ButtonType.OK) {
                int idLibro = librodao.insertarLibro(titulo, yearPublicacionParseado, paginasParseadas, descripcion, opinion);

                // PROBLEMA: SI EL USUARIO CANCELA LA CONFIRMACIÓN, idLibro ES 0
                // Y SE INTENTAN INSERTAR AUTORES CON id=0, LO QUE FALLA EN LA BD
                // AHORA SOLO SE INSERTAN AUTORES SI EL LIBRO SE GUARDÓ CORRECTAMENTE
                if (idLibro != -1) {
                    ObservableList<Autor> seleccionados = listaTablaAutor.getSelectionModel().getSelectedItems();
//                    PUEDE SER PORQUE I SIEMPRE ES < QUE SELECCIONADOS???
                    for (int i = 0; i <= seleccionados.size(); i++) {
                        actualizarAutorLibro.add(seleccionados.get(i).getId());
                    }
                    autorlibrodao.actualizarTablaLibroAutor(idLibro, actualizarAutorLibro);
                } else {
                    tipoAlerta.mostrarAlertaError("ERROR AL GUARDAR", "NO SE HA PODIDO GUARDAR EL LIBRO EN LA BASE DE DATOS.", "REVISAR CONEXIÓN");
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

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

        primaryStage.setOnHiding(event -> {
            recargarTabla();
    });
}
}

