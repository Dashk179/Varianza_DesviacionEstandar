package com.example.varianza_desviacionestandar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public int contador;
    public double media;
    public double Media;
    public double totalDesviaciones;
    public double totalDesviacionesAlCuadrado;
    public double Valorcont[];
    public double Desviaciones[];
    public double dAlCuadrado[];
    public double varianza;
    public double desviacionEstandar;


    @FXML
    private TableColumn<Contenido, Double> dato;

    @FXML
    private TableColumn<Contenido, Integer> num;

    @FXML
    private TableView<Contenido> table;

    @FXML
    private Button bAgregar;

    @FXML
    private TextField campoTexto;

    @FXML
    private Button bLimpiar;

    @FXML
    private Button bCalcular;

    @FXML
    private Text cantidadDatos;

    @FXML
    private Text rMedia;

    @FXML
    private Text tVarianza;

    @FXML
    private Text TdesviacionEstandar;

    ObservableList list = FXCollections.observableArrayList(

    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dato.setCellValueFactory(new PropertyValueFactory<Contenido, Double>("dato"));
        num.setCellValueFactory(new PropertyValueFactory<Contenido, Integer>("num"));
        table.setItems(list);
    }

//Metodo para agregar datos
    public void Adddatos() {
     //   double valor = campoTexto,getValue();
        //Este if sirve en caso de que el usuario no haya ingresado un dato lanza una ventana de advertencia
        if (campoTexto.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Ingresa un dato");
            alert.setContentText("Asegurate de no dejar vacia la caja de texto!");

            alert.showAndWait();

        } else {//En caso de que si ingrese un dato Se agrega a la tabla
            if (true) {//Si es verdad que ingreso 1 dato empieza el contador de la variable N(numero de datos)
                contador++;
            }
            Contenido con = new Contenido();
            con.setDato(Double.parseDouble(campoTexto.getText()));
            con.setNum(contador);
            table.getItems().add(con);
            campoTexto.clear();
        }


    }

    @FXML//Metodo para vaciar la tabla y resetear las variables a valor de 0
    public void vaciarTabla() {//Se crea un for para iterar con cada campo de la tabla y limpiarlo
        for (int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
        if (true) {//Si el metodo se activa osea si es = true entonces llama al metodo que resetea todas las variables
            resetVars();
        }
    }


    @FXML//Metodoo que calcula la media de los datos introduccidos
    public void calcularDatos() {
        cantidadDatos.setText(String.valueOf(contador));//Se imprime N(numero de dotos)

        //Este for me sirve para obtener todos los datos introducidos y sumarlos
        for (int i = 0; i < table.getItems().size(); i++) {
            media = media + Double.valueOf(String.valueOf(table.getColumns().get(1).getCellObservableValue(i).getValue()));


        }
        //Se llama al metodo calcular la Media
        calcularMedia();
        //Se llama al metodo calcular Varianza
        calcularVarianza();

    }

    public double calcularMedia() {
        //La media sera la media entre el contador(Numero de datos introducidos)
        Media = media / contador;

        System.out.println("Metodo Calcular Media: " + Media);
        //Imprimo el resultado en un componente grafico de texto
        rMedia.setText(String.valueOf(Media));
        return Media;

    }

    //Metodo para resetear todos las variables(Datos) esto nos sirve para ocupar el programa las veces que lo necesites
    //Sin la necesidad de cerrar y abrir la aplicacion
    public void resetVars() {
        contador = 0;
        Media = 0;
        media = 0;
        desviacionEstandar = 0;
        varianza = 0;
        totalDesviaciones=0;
        totalDesviacionesAlCuadrado=0;
        rMedia.setText("Resultado");
        cantidadDatos.setText("Resultado");
        TdesviacionEstandar.setText("Resultado");
        tVarianza.setText("Resultado");
        System.out.println(Media);
    }
    //Este metodo nos sirve para calcular la Varianza y Desviacion Estandar
    public void calcularVarianza() {
        //Creamos el formato de los datos para que solo tengamos 4 numeros despues del punto decimal
        DecimalFormat format = new DecimalFormat("#.####");
        //Se declara dos arreglos con el tamano de N(numero de datos)
        Valorcont = new double[contador];
        Desviaciones = new double[contador];
        //Este for nos ayuda a extraer todos los datos de la tabla
        for (int i = 0; i < table.getItems().size(); i++) {
            double extraer = Double.valueOf(String.valueOf(table.getColumns().get(1).getCellObservableValue(i).getValue()));
            System.out.println(i + "Extraer : " + extraer);
            Valorcont[i] = extraer;
        }
        //Este for nos sirve para sacar las desviciones de cada dato y el resultado del total sumado de las desviaciones
        //N-xi
        for (int j = 0; j < Valorcont.length; j++) {
            Media = calcularMedia();
            Desviaciones[j] = Valorcont[j] - Media;
            System.out.println(Valorcont[j]);
            System.out.println("media:" + Media);
            Desviaciones[j] = Double.parseDouble(format.format(Desviaciones[j]));
            System.out.println("Desviaciones: " + Desviaciones[j]);
            totalDesviaciones = totalDesviaciones + Desviaciones[j];
            totalDesviaciones = Double.parseDouble(format.format(totalDesviaciones));
            System.out.println("Total Desviaciones =" + totalDesviaciones);
        }
        //Este for nos sirve para sacar la desviacion al cuadrado de cada dato y el resultado total de las desviaciones
        //al cuadrado
        System.out.println("========================================================================");
        for (int k = 0; k < Valorcont.length; k++) {
            dAlCuadrado = new double[contador];
            dAlCuadrado[k] = Math.pow(Desviaciones[k], 2);
            System.out.println("Desviaciones al cuadrado:" + dAlCuadrado[k]);
            totalDesviacionesAlCuadrado = totalDesviacionesAlCuadrado + dAlCuadrado[k];
            System.out.println("Total desviaciones al cuadrado: " + totalDesviacionesAlCuadrado);
        }
        //Formula para calcular la  varianza
        varianza = ((totalDesviacionesAlCuadrado / (contador - 1)));
        //Se imprime la varianza en el componente de texto
        tVarianza.setText(String.valueOf(format.format(varianza)));
        double prueba = -0.037499999999999645;
        //Se calcula la desviacion estandar sacando raiz a la varianza
        desviacionEstandar = Math.sqrt(varianza);
        //Se imprime la desviaicon estandar en el componente de texto
        TdesviacionEstandar.setText(String.valueOf(format.format(desviacionEstandar)));

        System.out.println(format.format(prueba));
    }

}
