import com.constants.SpiraConstants;
import com.constants.SpiraWorkflowConstants;
import com.csv.CoreCSV;
import com.csv.parser.CSVUtil;
import com.spira.component.Component;
import com.spira.component.ComponentUtil;
import com.spira.testcase.TestCase;
import com.spira.testcase.TestCaseUtil;
import com.spira.testcase.folder.TestCaseFolder;
import com.spira.testcase.folder.TestCaseFolderUtil;

public class Main {
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        CoreCSV coreCSV = new CoreCSV("/home/kyle/Liferay/public/spira-importer/spira/src/main/resources/CoreInfra.csv");
        CSVUtil.parseCSV(coreCSV);
        coreCSV.importRows();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        System.out.println("Job time: " + duration / 1000 + " seconds and " + duration % 1000 + " milliseconds.");


    }
}