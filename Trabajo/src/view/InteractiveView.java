package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Question;
import model.QuestionBackupIOException;
import model.IRepositoryException;
import model.Option;

import com.coti.tools.Esdia;

public class InteractiveView extends BaseView {
    
    @Override
    protected void showMenu(){
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
                    showDetailQuestion();
                    break;
                case 4:
                    importJSON();
                    break;
                case 5:
                    exportJSON();
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
        try {
            controller.addQuestion(statement, topics, options, author, id);
            showMessage("Pregunta añadida correctamente.");
        } catch (IRepositoryException e) {
            showErrorMessage("Error al añadir la pregunta. " );
            return;
        }
    }

    public List<Option> getOptions(){
        final int NUM_OPTIONS=4;
        int numCorrects=0;
        List<Option> options=new ArrayList<>();
        for(int i=0;i<NUM_OPTIONS;i++){
            String text=Esdia.readString_ne("Introduzca el texto de la opción "+(i+1)+": ");
            String rationale=Esdia.readString_ne("Introduzca la razón de la opción "+(i+1)+": ");
            boolean executionFlag=true;
            boolean correct=false;
            while(executionFlag){
            String correctString=Esdia.readString_ne("¿Es esta opción correcta? (true/false): ");
            if(correctString.toLowerCase().equals("true")){
                correct = true;
                executionFlag=false;
            }
            else if(correctString.toLowerCase().equals("false")){
                correct = false;
                executionFlag=false;
            }
            else{
                showErrorMessage("Valor incorrecto para correcta. Debe ser 'true' o 'false'.");
            }}
            if(correct){
                numCorrects++;
            }
            Option option=new Option(text,rationale,correct);
            controller.addOption(option,options);
        }
        if(numCorrects==0||numCorrects>1){
            showErrorMessage("Ha habido un error: debe haber exactamente una opción correcta.");
            return null;
        }
        if(options.size()!=NUM_OPTIONS){
            showErrorMessage("Ha habido un error al crear las opciones.");
            return null;
        }
        return options;
    }

    public void listQuestions(){
        showMessage("¿Cómo desea listar las preguntas?");
        showMessage("1. Listar todas las preguntas.");
        showMessage("2. Listar preguntas ordenadas por fecha de creación.");
        showMessage("3. Listar preguntas filtradas por tema.");
        int option=Esdia.readInt("Introduzca la opción deseada: ",1,3);
        List<Question> questions=new ArrayList<Question>();
        switch(option){
            case 1:
                try {
                    questions=controller.getAllQuestions();
                } catch (IRepositoryException e) {
                    showErrorMessage("Error al obtener las preguntas.");
                    return;
                }
                break;
            case 2:
                try {
                    questions=controller.getAllQuestionsOrderedByDate();
                } catch (IRepositoryException e) {
                    showErrorMessage("Error al obtener las preguntas.");
                    return;
                }
                break;
            case 3:
                String topic=Esdia.readString_ne("Introduzca el tema por el que filtrar: ");
                topic=topic.trim().toUpperCase(); //En mayúsculas y sin espacios
                try {
                    questions=controller.getQuestionsByTopic(topic);
                } catch (IRepositoryException e) {
                    showErrorMessage("Error al obtener las preguntas.");
                    return;
                }
                break;
            
            default:
                showErrorMessage("Opción no válida.");
                return;
        } 
        if(questions.isEmpty()){
            showMessage("No hay preguntas disponibles.");
            return;
        }
        int i=1;
        for(Question q:questions){

            showMessage("\n=============================================");
            showMessage("           DETALLES DE LA PREGUNTA "+i+"       ");
            showMessage("=============================================");
            showMessage("Pregunta ID: "+q.getId());
            showMessage("Tema: " + q.getTopicsAsString());
            showMessage("Fecha: \n" + q.getCreationDate());
            showMessage("----- PREGUNTA -----");
            showMessage("Enunciado: "+q.getStatement());
            showMessage("----- OPCIONES -----");
            List<Option> opciones = q.getOptions(); 
        
            if (opciones == null || opciones.isEmpty()) {
                showMessage("   [Sin opciones definidas]");
            } else {
                char letra = 'A';
                for (Option opcion : opciones) {
                
                String correct = opcion.isCorrect() ? " [Respuesta Correcta]" : "";
                String lineaOpcion = String.format("   (%c) %s%s", letra++, opcion.getText(), correct);
                showMessage(lineaOpcion);
            }
        }
            i++;
        }

    }

    public void showDetailQuestion(){
        String idString=Esdia.readString_ne("Introduzca el ID de la pregunta que desea ver: ");
        UUID id;
        try{
            id=UUID.fromString(idString);
        }catch(IllegalArgumentException e){
            showErrorMessage("ID no válido.");
            return;
        }
        List<Question> questions;
        try {
            questions = controller.getAllQuestions();
        } catch (IRepositoryException e) {
            showErrorMessage("Error al obtener las preguntas.");
            return;
        }
        Question foundQuestion=null;
        for(Question q:questions){
            if(q.getId().equals(id)){
                foundQuestion=q;
                break;
            }
        }
        if(foundQuestion==null){
            showErrorMessage("Pregunta no encontrada.");
            return;
        }
        showMessage("Detalle de la pregunta:");
        showMessage("ID: "+foundQuestion.getId());
        showMessage("Enunciado: "+foundQuestion.getStatement());
        showMessage("Autor: "+foundQuestion.getAuthor());
        showMessage("Fecha de creación: "+foundQuestion.getCreationDate().toString());
        showMessage("Temas: "+String.join(", ", foundQuestion.getTopics()));
        showMessage("Opciones:");
        List<Option> options=foundQuestion.getOptions();
        for(int i=0;i<options.size();i++){
            Option op=options.get(i);
            showMessage("Opción "+(i+1)+": "+op.getText()+" | Razonamiento: "+op.getRationale()+" | Correcta: "+op.isCorrect());
        }

        showMessage("¿Qué desea hacer?");
        showMessage("1. Modificar algún atributo de la pregunta");
        showMessage("2. Eliminar la pregunta.");
        int option=Esdia.readInt("Introduzca la opción deseada: ",1,2);
        switch(option){
            case 1:
                showMessage("¿Qué atributo desea modificar?");
                showMessage("1. Autor.");
                showMessage("2. Temas.");
                showMessage("3. Enunciado.");
                showMessage("4. Opciones.");
                int atributo=Esdia.readInt("Introduzca la opción deseada: ",1,4);
                switch(atributo){
                    case 1:
                        String newAuthor=Esdia.readString_ne("Introduzca el nuevo autor: ");
                        try {
                            controller.modifyQuestion(atributo, foundQuestion, newAuthor);
                        } catch (IRepositoryException e) {
                            showErrorMessage("Error al modificar el autor.");
                            return;
                        }
                        showMessage("Autor modificado.");
                        break;
                    case 2:
                        String newTopics=Esdia.readString_ne("Introduzca los nuevos temas (separados por comas): ");
                        try {
                            controller.modifyQuestion(atributo, foundQuestion, newTopics);
                        } catch (IRepositoryException e) {
                            showErrorMessage("Error al modificar los temas.");
                            return;
                        }
                        showMessage("Temas modificados.");
                        break;
                    case 3:
                        String newStatement=Esdia.readString_ne("Introduzca el nuevo enunciado: ");
                        try {
                            controller.modifyQuestion(atributo, foundQuestion, newStatement);
                        } catch (IRepositoryException e) {
                            showErrorMessage("Error al modificar el enunciado.");
                            return;
                        }
                        showMessage("Enunciado modificado.");
                        break;
                    case 4:
                        List<Option> newOptions=getOptions();
                        try {
                            controller.modifyOptions(foundQuestion, newOptions);
                        } catch (IRepositoryException e) {
                            showErrorMessage("Error al modificar las opciones.");
                            return;
                        }
                        showMessage("Opciones modificadas.");
                        break;
                    default:
                        showErrorMessage("Opción no válida.");
                }
                break;
            case 2:
                try {
                    controller.deleteQuestion(foundQuestion);
                } catch (IRepositoryException e) {
                    showErrorMessage("Error al eliminar la pregunta.");
                    return;
                }
                showMessage("Pregunta eliminada.");
                break;
            default:
                showErrorMessage("Opción no válida.");
        }

}
public void importJSON(){
    showMessage("Importar preguntas y temas desde JSON:");
    try{
        controller.importQuestionsFromJSON();
        showMessage("Importación realizada correctamente.");
    }catch(QuestionBackupIOException | IRepositoryException e){
        showErrorMessage("Error al importar: "+e.getMessage()); //Ver excepciones
    }
}

public void exportJSON(){
    showMessage("Exportar preguntas y temas a JSON:");
    try{
        controller.exportQuestionsToJSON();
        showMessage("Exportación realizada correctamente.");
    }catch(QuestionBackupIOException | IRepositoryException e){
        showErrorMessage("Error al exportar: "+e.getMessage()); //Ver excepciones
    }
}
}