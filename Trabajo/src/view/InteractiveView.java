package view;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Question;
import model.Option;

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
                    addQuestion();
                    break;
                case 2:
                    listQuestions();
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

    public void addQuestion(){
        showMessage("Añadir nueva pregunta:");
        String statement = Esdia.readString_ne("Introduzca el enunciado de la pregunta: ");
        String topics=Esdia.readString_ne("Introduzca los temas de la pregunta (separados por comas): ");
        List<Option> options= getOptions();
        if(options==null){
            showErrorMessage("Error al introducir las opciones.");
            return;
        }
        if(options.size()!=4){
            showErrorMessage("Debe introducir exactamente 4 opciones.");
            return;
        }
        String author=Esdia.readString_ne("Introduzca el autor de la pregunta: ");
        UUID id=UUID.randomUUID();
    
        controller.addQuestion(statement, topics, options, author, id);
        showMessage("Pregunta añadida correctamente.");
    }

    public List<Option> getOptions(){
        List<Option> options=new ArrayList<Option>();
        final int NUM_OPTIONS=4;
        int numCorrects=0;
        for(int i=0;i<NUM_OPTIONS;i++){
            String text=Esdia.readString_ne("Introduzca el texto de la opción "+(i+1)+": ");
            String rationale=Esdia.readString_ne("Introduzca la razón de la opción "+(i+1)+": ");
            boolean correct=Esdia.readBoolean("¿Es esta opción correcta? (true/false): ");
            if(correct){
                numCorrects++;
            }
            Option option=new Option(text,rationale,correct);
            options.add(option);
        }
        if(numCorrects==0||numCorrects>1){
            showErrorMessage("Ha habido un error: debe haber exactamente una opción correcta.");
            return null;
        }
        return options;
    }
}


