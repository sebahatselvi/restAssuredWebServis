import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class RestAssureTest {

    private String url="http://ergast.com/api/f1/2016/drivers.json";
    public String calisanID;
    @BeforeTest
    public void Baslangic()
    {
        RestAssured.rootPath = "MRData.DriverTable.Drivers";
        System.out.println("Test Baslangıcı");
    }
    // dizinin ikinci elemanın çalışanının ID kontrolu
     @Test
    public String Test1(String calisanID)
    {

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        Response response=null;
        try {
            response=RestAssured.given().when().get("/employees");
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        JsonPath jsonObje=response.jsonPath();
        calisanID=jsonObje.get("id[1]");
        System.out.println(calisanID);
        System.out.println(response.asString().contains("employee_Salary"));
        assertEquals("6571", jsonObje.get("id[1]"));
        return calisanID;
    }

    //calışan Sayısını veren sorgu
    @Test
    public void Test2()
    {
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        Response response=null;
        try {
            response=RestAssured.given().when().get("/employees");

        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        JsonPath jsonObje=response.jsonPath();
        List<String > calisanSayisi=jsonObje.getList("$");
        assertEquals(320,calisanSayisi.size());
    }
    @Test
    public void Test8()
    {
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        Response response=null;
        try {
            response=RestAssured.given().when().get("/employees");

        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        JsonPath jsonObje=response.jsonPath();
        List<String > calisanIsim=jsonObje.getList("employee_name");
        for (String i:calisanIsim)
        {
            System.out.println(i);
        }
    }

    @Test
    public void Test3()
    {
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        Response response=null;
        try {
            response=RestAssured.given().when().get("/employees");
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        JsonPath jsonObje=response.jsonPath();
        assertEquals("moi",jsonObje.get("employee_name[0]"));
    }

    @Test
    public void Test4()
    {
        RestAssured.baseURI="https://reqres.in/api";
        Response response=null;
        try {
            response=RestAssured.given().when().queryParam("page","2").queryParam("id",5).get("/users");
            }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(response.asString().contains("George")); //dogru cevap Charles
    }

    //Çalışan ekleme
    @Test
    public void Test5()
    {
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        String requestBody = "{\n"+"\"name\":\"Sebahat\",\n"+"\"salary\":\"5000\",\n"+"\"age\":\"20\"\n"+
                "}";
        Response response = null;
        try{
            response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/create");
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        System.out.println("'Sebahat' diye biri var mı? :" + response.asString().contains("Sebolas"));
        assertEquals(200,response.getStatusCode());
    }

    @AfterTest
    public void Bitis(){
         System.out.println("Test Bitisi");
     }
}
