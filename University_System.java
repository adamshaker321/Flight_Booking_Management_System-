package university_system;

import java.util.Scanner;

public class University_System {

    public static void main(String[] args) {

        Scanner input =new Scanner (System.in);    
    University university=new University();
    
    int choice=0;
        System.out.println("\t\t\t \"Welcome to College_System\"  ");

        do{
        System.out.println("Please choose an option:");
        System.out.println("1. Add a student");
        System.out.println("2. Remove a student");
        System.out.println("3. Add a course");
        System.out.println("4. Remove a course");
        System.out.println("5. sort courses in student ");
        System.out.println("6. sort students in course");
        System.out.println("7. Last student added");
        System.out.println("8. Last course added");
        System.out.println("9. Enroll a student");
        System.out.println("10. Remove enrollment");
        System.out.println("11. List courses by student");
        System.out.println("12. List students by course");
        System.out.println("13. is full course");
        System.out.println("14. is normal student");
        System.out.println("15. Undo");
        System.out.println("16. Redo");
        System.out.println("17. to exiting system.");
            System.out.println("\n\n");
        
            choice = input.nextInt();

            switch (choice) {
            case 1:                                                                                 
                
                System.out.print("Enter student ID: ");
                int Id = input.nextInt();
                university.addstudent(Id);
                break;
            case 2:

                System.out.print("Enter student ID: ");
                int Id2 = input.nextInt();
                university.removestudent(Id2);
                System.out.println("removed successfully\n");              
                break;
            case 3:
               
                System.out.print("Enter course ID: ");
                int courseid1 = input.nextInt();
                university.addcourse(courseid1);
                break;
            case 4:
                System.out.print("Enter course ID: ");
                int courseid2 = input.nextInt();
                university.removecourse(courseid2);
                System.out.println("removed successfully\n");

                break;
                
            case 5:
                System.out.println("Enter student id : ");
                int student_Id=input.nextInt();
                university.sort_courses_in_student(student_Id);
                System.out.println("sorted successfully");
                break;
            case 6:
                System.out.println("Enter course id : ");
                int course_Id=input.nextInt();
                university.sort_students_in_course(course_Id);
                System.out.println("sorted successfully");
                break;
            case 7:
                university.laststudent();
                break;
            case 8:
                university.lastcourse();
                break;
            case 9:
                System.out.print("Enter student ID: ");
                int studentId = input.nextInt();
                System.out.print("Enter course ID: ");
                int courseId = input.nextInt();
                university.enrollStudent(studentId, courseId);
                System.out.println("enrolled successfully\n");
                break;
            case 10:
                System.out.print("Enter student ID: ");
                int studentId2 = input.nextInt();
                System.out.print("Enter course ID: ");
                int courseId2 = input.nextInt();
                university.remove_enrollment(studentId2, courseId2);
                System.out.println("removed successfully\n");

                break;
            case 11:
                System.out.print("Enter student ID: ");
                int studentId3 = input.nextInt();
                university.list_of_Courses_For_Student(studentId3);
                break;
            case 12:
                System.out.print("Enter course ID: ");
                int courseId3 = input.nextInt();
                university.ListStudentinCourse(courseId3);
                break;
            
            case 13:
                System.out.print("Enter course ID: ");
                int courseid5 = input.nextInt();
                university.is_full_course(courseid5);
                break;
            case 14:
                System.out.print("Enter student ID: ");
                int normalstudentid = input.nextInt();
                university.is_normal_student(normalstudentid);
                break;
            case 15:
                university.undo();
                break;
            case 16:
                university.redo();
                break;
            case 17:
                System.out.println("Exiting system...");
        break;
            default :
                System.out.println("\nplease enter a valid number !\n");
        }
        }while(choice!=17);
        
        input.close();
        

    
    
    }
}
        
        
        
    
        
        
        
    

