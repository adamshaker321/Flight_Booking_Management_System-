package university_system;



public class Nodestudent {

    
    
int studentId;
        Nodecourse enrolledCourse;//list of enrolled courses
        Nodestudent next;
        int enrolledCouresCount;//track number of courses
        
        public Nodestudent(int studentId){
            this.studentId =studentId;
            this.enrolledCourse =null;
            this.next =null;
            this.enrolledCouresCount =0;
        }
    
}
