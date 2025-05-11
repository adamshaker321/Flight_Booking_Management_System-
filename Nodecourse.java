package university_system;


    public class Nodecourse {

        int courseId;
        Nodestudent enrolledStudent;//list of enrolled students
        Nodecourse next;
        int studentCount;//track number of students
        
        public Nodecourse(int courseId){
            
            this.courseId =courseId;
            this.enrolledStudent =null;
            this.next =null;
            this.studentCount=0;
        }
    }
