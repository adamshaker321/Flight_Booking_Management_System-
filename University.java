package university_system;

import java.util.Stack;

public class University {

    
    /*StudentList students;
انت كدا عملت متغير اسمه الطلاب من النوع ستيودنت ليست يعني انا كدا عندي متغير اسمه الطلاب هيكون عبارة 
    عن لينكد ليست للطلاب لان ستيودنت ليست دي عبارة اصلا عن لينكد ليست
*/
private Stack<EnrollmentAction> undoStack = new Stack<>();
private Stack<EnrollmentAction> redoStack = new Stack<>();

    
    private StudentList students;       //manages a linked list of students
    private CourseList courses;         //manages a linked list of courses
    
    ///Stack\\\
    
    // The University constructor holds references to StudentList/CourseList objects
    // when create object we can access all methods in student/course class
    public University(){
        ///create object from studentlist/courselist class
        this.students =new StudentList();
        this.courses =new CourseList();
    }
    

    public void addstudent(int id){
        Nodestudent student =students.searchStudent(id);
        if (student !=null) {
            System.out.println("the student "+id+" has already been added!");
        }
        else       { students.addstudent(id);        
        System.out.println("added successfully"); 
        }
    }
    
    public void removestudent(int id){
        students.removestudent(id);
    }
    public void addcourse(int id){
         Nodecourse course = courses.searchCourse(id);
        if (course !=null) {
            System.out.println("the course "+id+" has already been added!");
        }
        else     {   courses.addcourse(id);
            System.out.println("added successfully ");
        }
    }
    public void removecourse(int id){
        courses.removecourse(id);
    }
    public void laststudent(){
        System.out.println("The last student added: "+students.laststudent());
    }
    public void lastcourse(){
        System.out.println("The last course added: "+courses.lastcourse());
    }
    public void enrollStudent(int studentId,int courseId){
        
        //when check if the node found store it 
        Nodestudent student =students.searchStudent(studentId);
        Nodecourse course =courses.searchCourse(courseId);
        
        //validate existence
        if (student ==null||course ==null) {
            System.out.println("Student or course not found");
            return;
        }
        
        //check student limit
        if (course.studentCount >=30) {
            System.out.println("Course "+courseId+" is full");
            return;
        }
        // Check for existing enrollment
        Nodecourse check = student.enrolledCourse;
        while (check != null) {
            if (check.courseId == courseId) {
                System.out.println("Student " + studentId + " is already enrolled in course " + courseId);
                return;
            }
            check = check.next;
        }
        
        //add course to student's list
        Nodecourse newCourseNode =new Nodecourse(courseId);
        newCourseNode.next =student.enrolledCourse;
        student.enrolledCourse =newCourseNode;
        
        //add student to course's list
        Nodestudent newStudentNode =new Nodestudent(studentId);
        newStudentNode.next =course.enrolledStudent;
        course.enrolledStudent =newStudentNode;
        
        //update counters
        student.enrolledCouresCount++;
        course.studentCount++;
        
            undoStack.push(new EnrollmentAction(studentId, courseId, true));    
            redoStack.clear(); // لو عملت حاجة جديدة، نمسح الـ redo القديم
          
    }
    
    
    public void remove_enrollment(int studentId,int courseId){
        Nodestudent student =students.searchStudent(studentId);
        Nodecourse course =courses.searchCourse(courseId);
        //validate existence
        if (student ==null||course ==null) {
            System.out.println("Student or course not found");
            return;
        }
        Nodecourse currentcourse=student.enrolledCourse;

        //remove course from student's list
        //if student in the first node
        if (student.enrolledCourse != null && student.enrolledCourse.courseId == courseId) {
            student.enrolledCourse = student.enrolledCourse.next;
            student.enrolledCouresCount--;
        }else{
            while (currentcourse != null && currentcourse.next != null) {
                if (currentcourse.next.courseId == courseId) {
                    currentcourse.next = currentcourse.next.next;
                    student.enrolledCouresCount--;
                    break; }
                currentcourse = currentcourse.next;
            }
        }
        // remove student from course's list
        //if student in the first node
        if (course.enrolledStudent != null && course.enrolledStudent.studentId == studentId) {
            course.enrolledStudent = course.enrolledStudent.next;
            course.studentCount--;
        } else {
             Nodestudent current = course.enrolledStudent;
            while (current != null && current.next != null) {
                if (current.next.studentId == studentId) {
                    current.next = current.next.next;
                    course.studentCount--;
                    break;
                }
                current = current.next;
                
                
                
            }
        }
        System.out.println("Student " + studentId + " deleted from course " + courseId);

        undoStack.push(new EnrollmentAction(studentId, courseId, false));
        redoStack.clear();

    }
    
    //print all students that enrolled in the course
    public void ListStudentinCourse(int courseId){
        Nodecourse course =courses.searchCourse(courseId);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        Nodestudent current = course.enrolledStudent;
        System.out.println("Students enrolled in course "+course.courseId+" :");
        while (current !=null) {
            System.out.println("Student ID: "+current.studentId);
            current =current.next;
        }
        
    }
    
    
    public void list_of_Courses_For_Student(int studentId) {
        Nodestudent student = students.searchStudent(studentId);

        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        Nodecourse currentCourse = student.enrolledCourse;

        if (currentCourse == null) {
            System.out.println("Student " + studentId + " is not enrolled in any courses.");
            return;
        }

        System.out.println("Courses for student " + studentId + ":");
        while (currentCourse != null) {
            System.out.println("Course ID: " + currentCourse.courseId);
            currentCourse = currentCourse.next;
        }
    }
    public void sort_courses_in_student(int studentId) {
    Nodestudent currentstudent = students.searchStudent(studentId);
    if (currentstudent == null || currentstudent.enrolledCourse == null) {
        return; // No student or no courses to sort
    }

    Nodecourse mainhead = currentstudent.enrolledCourse;
    currentstudent.enrolledCourse = null; // Detach the original list to build a new sorted one

    while (mainhead != null) {
        // Find the node with the minimum courseid in the remaining list
        Nodecourse minPrev = null; // Previous node of the minimum node
        Nodecourse min = mainhead; // Minimum node
        Nodecourse prev = mainhead; // For traversal
        Nodecourse current = mainhead.next;

        while (current != null) {
            if (current.courseId < min.courseId) {
                min = current;
                minPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // Remove the min node from the original list
        if (min == mainhead) {
            mainhead = mainhead.next;
        } else {
            minPrev.next = min.next;
        }

        // Append the min node to the new sorted list
        if (currentstudent.enrolledCourse == null) {
            currentstudent.enrolledCourse = min;
            min.next = null;
        } else {
            // Find the last node in the new list
            Nodecourse last = currentstudent.enrolledCourse;
            while (last.next != null) {
                last = last.next;
            }
            last.next = min;
            min.next = null;
        }
    }
}
   public void sort_students_in_course(int courseId) {
    Nodecourse currentcourse = courses.searchCourse(courseId);
    if (currentcourse == null || currentcourse.enrolledStudent == null) {
        return; // No course or no students to sort
    }

    Nodestudent mainhead = currentcourse.enrolledStudent;
    currentcourse.enrolledStudent = null; // Detach the original list to build a new sorted one

    while (mainhead != null) {
        // Find the node with the minimum studentID in the remaining list
        Nodestudent minPrev = null; // Previous node of the minimum node
        Nodestudent min = mainhead; // Minimum node
        Nodestudent prev = mainhead; // For traversal
        Nodestudent current = mainhead.next;

        while (current != null) {
            if (current.studentId < min.studentId) {
                min = current;
                minPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        
        
        // Remove the min node from the original list
        if (min == mainhead) {
            mainhead = mainhead.next;
        } else {
            minPrev.next = min.next;
        }

        // Append the min node to the new sorted list
        if (currentcourse.enrolledStudent == null) {
            currentcourse.enrolledStudent = min;
            min.next = null;
        } else {
            // Find the last node in the new list
            Nodestudent last = currentcourse.enrolledStudent;
            while (last.next != null) {
                last = last.next;
            }
            last.next = min;
            min.next = null;
        }
        }
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        EnrollmentAction lastAction = undoStack.pop();
        if (lastAction.isEnroll) {
            // لو كانت تسجيل، هنلغيه
            remove_enrollment(lastAction.studentId, lastAction.courseId);
        } else {
            // لو كانت إزالة، هنرجع التسجيل
            enrollStudent(lastAction.studentId, lastAction.courseId);
        }
        redoStack.push(lastAction);
    }

    
    
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }

        EnrollmentAction action = redoStack.pop();
        if (action.isEnroll) {
            enrollStudent(action.studentId, action.courseId);
        } else {
            remove_enrollment(action.studentId, action.courseId);
        }
        undoStack.push(action);
    }
        
    public boolean is_full_course(int courseId){
    Nodecourse currentcourse=courses.searchCourse(courseId);
        if (currentcourse.studentCount>=30){
            System.out.println("This course is full");
            return true;
        } else{
            System.out.println("This course has "+(30-currentcourse.studentCount)+" free sets");
        return false;
        }
      
    }
    public boolean is_normal_student(int studentId){
    Nodestudent currentstudent=students.searchStudent(studentId);
        if (currentstudent.enrolledCouresCount>=7) {
            
            System.out.println("The student reached the maximum enroll");
            return false;
        }
        else if (currentstudent.enrolledCouresCount<2){        
                System.out.println("courses enrolled is less than 2 ");
            return false;
        }
        else{
            System.out.println("The student "+studentId+" not reaced the maximum enroll");
        return true;
        }
    
    }


    
}