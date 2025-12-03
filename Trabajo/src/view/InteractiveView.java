package view;

import java.time.LocalDate;
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
                    showDetailQuestion();
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
        LocalDate creationDate = LocalDate.now();

        controller.addQuestion(statement, topics, options, author, id, creationDate);
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

    public void listQuestions(){
        showMessage("¿Cómo desea listar las preguntas?");
        showMessage("1. Listar todas las preguntas.");
        showMessage("2. Listar preguntas ordenadas por fecha de creación.");
        showMessage("3. Listar preguntas por tema.");
        int option=Esdia.readInt("Introduzca la opción deseada: ",1,3);
        List<Question> questions=new ArrayList<Question>();
        switch(option){
            case 1:
                questions=controller.getAllQuestions();
                break;
            case 2:
                questions=controller.getAllQuestionsOrderedByDate();
                break;
            case 3:
                String topic=Esdia.readString_ne("Introduzca el tema por el que filtrar: ");
                topic=topic.trim().toUpperCase(); //En mayúsculas y sin espacios
                questions=controller.getQuestionsByTopic(topic);
                break;
            
            default:
                showErrorMessage("Opción no válida.");
                return;
        } 
        if(questions.isEmpty()){
            showMessage("No hay preguntas disponibles.");
            return;
        }
        for(Question q:questions){
            showMessage("Pregunta ID: "+q.getId()+" \nEnunciado: "+q.getStatement());
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
        List<Question> questions=controller.getAllQuestions();
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
                        controller.modifyQuestion(atributo, foundQuestion, newAuthor);
                        showMessage("Autor modificado.");
                        break;
                    case 2:
                        String newTopics=Esdia.readString_ne("Introduzca los nuevos temas (separados por comas): ");
                        controller.modifyQuestion(atributo, foundQuestion, newTopics);
                        showMessage("Temas modificados.");
                        break;
                    case 3:
                        String newStatement=Esdia.readString_ne("Introduzca el nuevo enunciado: ");
                        controller.modifyQuestion(atributo, foundQuestion, newStatement);
                        showMessage("Enunciado modificado.");
                        break;
                    case 4:
                        List<Option> newOptions=getOptions();
                        controller.modifyOptions(foundQuestion, newOptions);
                        showMessage("Opciones modificadas.");
                        break;
                    default:
                        showErrorMessage("Opción no válida.");
                }
                break;
            case 2:
                controller.deleteQuestion(foundQuestion);
                showMessage("Pregunta eliminada.");
                break;
            default:
                showErrorMessage("Opción no válida.");
        }

}
}


