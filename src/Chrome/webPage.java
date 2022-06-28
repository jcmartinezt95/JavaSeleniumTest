package Chrome;
import Flows.flows;
import static Flows.flows.pageFlow;

public class webPage {

    private flows flows;

    public static void main(String[] args) throws Exception {

        System.out.println("\n\n----- Starting Automation project for Microsoft web page -----\n\n");

        //Go to page flow secuence
        pageFlow("https://www.microsoft.com/es-mx/", "Xbox");

        System.out.println("\n\n----- Ending Automation project for Microsoft web page -----\n\n");

    }


}
