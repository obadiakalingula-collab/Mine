import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Application: VUPay
public class VUPay {

    // Variables used in this program:
    // - moduleName (String)
    // - moduleCode (String)
    // - tuition (int)

    public static void main(String[] args) {
        // Prepare available courses
        Map<String, VUCourses> courses = new HashMap<>();
        courses.put("BSF", new VUCourses("BSc. Software Engineering", "BSF", 900000));
        courses.put("BIT", new VUCourses("BSc. Information Technology", "BIT", 750000));
        courses.put("BCS", new VUCourses("BSc. Computer Science", "BCS", 800000));
        courses.put("BCE", new VUCourses("BSc. Computer Engineering", "BCE", 950000));

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Module Code: ");
        String input = sc.nextLine().trim().toUpperCase();

        VUCourses chosen = courses.get(input);
        if (chosen != null) {
            chosen.displayCourse();
        } else {
            System.out.println("Wrong Module Code details");
        }

        sc.close();
    }
}

class VUCourses {

    String moduleName;
    String moduleCode;
    double tuition;

    // Constructor
    public VUCourses(String moduleName, String moduleCode, int tuition) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.tuition = tuition;
    }

    public void displayCourse() {
        System.out.println("Course Name: " + moduleName);
        System.out.println("Module Code: " + moduleCode);
        System.out.println("Tuition: UGX " + tuition);
    }
}