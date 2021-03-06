package miljoboven.backend.cases; 
  
import java.util.Calendar; 
import java.util.Date; 
import java.util.Properties; 

import miljoboven.backend.BaseDAO;
  
/** 
 * 
 * @author herman 
 */
public class CaseDAO extends BaseDAO { 
  
    // TODO - store case id on file 
    private int latestCaseId = 0; 
  
    private static final String DATEOFREPORT = "dateOfReport"; 
    private static final String DATEOFCRIME = "dateOfCrime"; 
    private static final String NAMEOFUNIT = "nameOfUnit"; 
    private static final String TYPEOFCRIME = "typeOfCrime"; 
    private static final String ID = "id"; 
    private static final String LOCATIONOFCRIME = "locationOfCrime"; 
    private static final String COMMENTS = "comments"; 
    private static final String NAMEOFREPORTER = "nameOfReporter"; 
    private static final String ADDRESSOFREPORTER = "addressOfReporter"; 
    private static final String PHONEOFREPORTER = "phoneOfReporter";
    private static final String FILENAMES = "fileNames";
    private static final String NOINVESTIGATION = "noInvestigation";
    private static final String STATUS = "status";
  
    private static final String CASE_FILE_PREFIX = "case_"; 
    private static final String CASE_FILE_SUFFIX = ".properties"; 
  
    private static final String HYPHEN = "-"; 
    private static final String CRIME_TYPE_ID = "35"; 
  
    public Case create(Case aCase) { 
  
        String id = generateCaseId(); 
        String caseFile = CASE_FILE_PREFIX + id + CASE_FILE_SUFFIX; 
  
        Properties prop = new Properties(); 
        prop = load(caseFile); 
        prop.setProperty(DATEOFREPORT, aCase.getDateOfReport().toString()); 
        prop.setProperty(DATEOFCRIME, aCase.getDateOfCrime().toString()); 
        prop.setProperty(NAMEOFUNIT, aCase.getNameOfUnit()); 
        prop.setProperty(TYPEOFCRIME, aCase.getTypeOfCrime()); 
        prop.setProperty(ID, id); 
        prop.setProperty(LOCATIONOFCRIME, aCase.getLocationOfCrime()); 
        prop.setProperty(COMMENTS, aCase.getComments()); 
        prop.setProperty(NAMEOFREPORTER, aCase.getNameOfReporter()); 
        prop.setProperty(ADDRESSOFREPORTER, aCase.getAddressOfReporter()); 
        prop.setProperty(PHONEOFREPORTER, aCase.getPhoneOfReporter());
        prop.setProperty(FILENAMES, aCase.getFileNamesAsString());
        prop.setProperty(NOINVESTIGATION, aCase.getNoInvestigationAsString());
        prop.setProperty(STATUS, aCase.getStatus());
  
        store(caseFile, prop, true); 
  
        aCase.setId(id); 
  
        return aCase; 
    } 
  
    private String generateCaseId() { 
        // TODO implementation needed 
        latestCaseId++; 
  
        return Calendar.YEAR + HYPHEN + CRIME_TYPE_ID + HYPHEN + "00" + latestCaseId; 
    } 
  
    public static void main(String[] args) { 
  
        CaseDAO cd = new CaseDAO(); 
        Case c = new Case(); 
        c.setDateOfReport(new Date()); 
        c.setDateOfCrime(new Date()); 
        c.setNameOfUnit("Herman"); 
        c.setTypeOfCrime("Brott"); 
        c.setLocationOfCrime("Uppsala"); 
        c.setComments("En kommentar"); 
        c.setNameOfReporter("Isac"); 
        c.setAddressOfReporter("Brottvagen 1"); 
        c.setPhoneOfReporter("1234567"); 
        c.setFileNames(new String[]{"hej","jag","heeheh"});
        c.setStatus("Klar");
        c.setNoInvestigation(true);
        Case storedCase = cd.create(c); 
        System.out.println(storedCase.toString()); 
    } 
  
} 