package Template;

import java.util.Scanner;

public class EmployeeReport extends ReportTemplate {
    
    private Scanner sc;

    public EmployeeReport(Scanner sc) {
        this.sc = sc;
    }
    
    @Override
    protected void gatherData() {
        String gatherData = sc.nextLine();
        System.out.println(gatherData);
    }

    @Override
    protected void processData() {
        String processData = sc.nextLine();
        System.out.println(processData);
    }
}