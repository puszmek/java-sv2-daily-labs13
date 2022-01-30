package day01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ClassNoteBookTest {

    @Test
    void testAddStudent(){
        ClassNoteBook classNoteBook = new ClassNoteBook();
        Student student = new Student(15,"Fehér Tímea");
        classNoteBook.addStudent(student);

        assertTrue(classNoteBook.getNotebook().keySet().contains(student));
        assertEquals(0, classNoteBook.getNotebook().get(student).size());
    }

    @Test
    void testAddStudentInOrder(){
        ClassNoteBook classNoteBook = new ClassNoteBook();
        Student secondstudent = new Student(2,"Fehér Tímea");
        Student thirdstudent = new Student(3,"Deák Dénes");
        Student firststudent = new Student(1,"Zelk Hanna");
        classNoteBook.addStudent(firststudent);
        classNoteBook.addStudent(secondstudent);
        classNoteBook.addStudent(thirdstudent);

        assertEquals(List.of(firststudent, secondstudent, thirdstudent), new ArrayList<>(classNoteBook.getNotebook().keySet()));

        int i=1;
        for(Map.Entry<Student, List<Integer>> actual : classNoteBook.getNotebook().entrySet()){
            assertEquals(i, actual.getKey().getId());
            i++;
        }
    }

    @Test
    void testAddMark(){
        ClassNoteBook classNotebook = new ClassNoteBook();
        Student secondstudent = new Student(2,"Fehér Tímea");
        Student thirdstudent = new Student(3,"Deák Dénes");
        Student firststudent = new Student(1,"Zelk Hanna");
        classNotebook.addStudent(secondstudent);
        classNotebook.addStudent(thirdstudent);
        classNotebook.addStudent(firststudent);
        classNotebook.addMark(2,5);

        assertEquals(5,classNotebook.getNotebook().get(secondstudent).get(0));
    }
}