//package rest;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import dtos.GuideDto;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.parsing.Parser;
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.core.UriBuilder;
//import java.net.URI;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class GuideEndpointTest {
//    private static final int SERVER_PORT = 7777;
//    private static final String SERVER_URL = "http://localhost/api";
//
//    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
//    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//
//    static org.glassfish.grizzly.http.server.HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//
//    @Test
//    public void getAllGuides() throws Exception {
//        List<GuideDto> guidedtos;
//
//        guidedtos = given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("guide/all")
//                .then()
//                .extract().body().jsonPath().getList("", GuideDto.class);
//
//        GuideDto guideDto = new GuideDto();
//        assertThat(guidedtos);
//
//    }
//
//
//}
