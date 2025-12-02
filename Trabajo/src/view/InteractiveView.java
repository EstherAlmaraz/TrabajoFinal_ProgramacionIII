package view;

import com.coti.tools.Esdia;

public class InteractiveView extends BaseView {
    
    public void showMenu(){
        int option;
        boolean valid=true;
        do {
            showMessage("----- MENÚ -----");
            showMessage("1. Crear nueva pregunta.");
            showMessage("2. Listar preguntas.");
            showMessage("3. Ver detalle de una pregunta.");
            showMessage("4. Importar preguntas y temas desde JSON.");
            showMessage("5. Exportar preguntas y temas a JSON.");
            showMessage("6. Creación de pregunta automática.");
            showMessage("7. Menú modo examen.");
            showMessage("8. Salir.");
            option = Esdia.readInt("Introduzca la opción deseada: ",1,8);
            switch (option) {
                case 1:
                    //Crear nueva pregunta
                    break;
                case 2:
                    //Listar preguntas
                    break;
                case 3:
                    //Ver detalle de una pregunta
                    break;
                case 4:
                    //Importar preguntas y temas desde JSON
                    break;
                case 5:
                    //Exportar preguntas y temas a JSON
                    break;
                case 6:
                    //Creación de pregunta automática
                    break;
                case 7:
                    //Menú modo examen
                    break;
                case 8:
                    showMessage("Saliendo...");
                    valid=false;
                    break;
                default:
                    showErrorMessage("Opción no válida. Inténtelo de nuevo.");
            }
        } while (valid);
    }
}
