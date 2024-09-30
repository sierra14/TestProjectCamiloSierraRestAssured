package com.pruebas.usuarios;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RestTests extends BaseTest{

    private static final Logger logger = Logger.getLogger(RestTests.class.getName());

    @Test
    public void listarUsuarios() {
        logger.info("Iniciando prueba: listarUsuarios");

        Response response = RestAssured.get("/api/users?page=2");

        logger.info("Respuesta obtenida: " + response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());

        logger.info("Estado de la respuesta: " + response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getList("data"));
    }

    @Test
    public void crearUsuario() {
        logger.info("Iniciando prueba: crearUsuario");

        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/api/users");

        logger.info("Respuesta al crear usuario: " + response.getBody().asString());
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    public void actualizarUsuario() {
        logger.info("Iniciando prueba: actualizarUsuario");

        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        logger.info("Cuerpo de la solicitud para actualizar usuario: " + requestBody);

        Response response = request
                .body(requestBody)
                .put("/api/users/2");

        logger.info("Respuesta obtenida al actualizar usuario: " + response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());

        String updatedJob = response.jsonPath().getString("job");
        logger.info("Nuevo trabajo del usuario: " + updatedJob);
        Assert.assertEquals("zion resident", updatedJob);
    }

    @Test
    public void eliminarUsuario() {
        logger.info("Iniciando prueba: eliminarUsuario");

        Response response = request
                .delete("/api/users/2");

        logger.info("Estado de la respuesta al eliminar usuario: " + response.getStatusCode());
        Assert.assertEquals(204, response.getStatusCode());

        logger.info("Usuario eliminado correctamente.");
    }
}
