import com.constants.SpiraConstants;
import com.constants.SpiraTestTypeConstants;
import com.constants.SpiraWorkflowConstants;
import com.csv.CoreCSV;
import com.csv.parser.CSVUtil;
import com.spira.testcase.TestCase;
import com.spira.testcase.TestCaseUtil;
import com.spira.testcase.folder.TestCaseFolder;
import com.spira.testcase.folder.TestCaseFolderUtil;

public class Main {
    public static void main(String[] args) throws Exception {

        //CoreCSV coreCSV = new CoreCSV("/home/kyle/Liferay/public/spira-importer/spira/src/main/resources/CoreInfrastructure.csv");
        TestCaseFolder testCaseFolder =
                TestCaseFolderUtil.addTestCaseFolder(
                    SpiraConstants.PROJECT_ID, 0, "Test 1", "Test Description");
        TestCaseFolder testCaseFolderChild =
                TestCaseFolderUtil.addTestCaseFolder(
                        SpiraConstants.PROJECT_ID, testCaseFolder.getFolderId(),
                        "Test 1 Child", "Test Description");
        //CSVUtil.parseCSV(coreCSV);
        TestCaseUtil.addTestCase(SpiraConstants.PROJECT_ID, "Test Case Name 1",
                "Description Test 1", "Steps test steps", 3,
                testCaseFolderChild.getFolderId(), SpiraWorkflowConstants.APPROVED,
                SpiraTestTypeConstants.FUNCTIONAL);
    }
}
