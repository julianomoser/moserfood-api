package com.moser.moserfood;

import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.CozinhaRepository;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import com.moser.moserfood.util.DatabaseCleaner;
import com.moser.moserfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Juliano Moser
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteIT {
    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";

    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private String jsonRestauranteCorreto;
    private String jsonRestauranteSemFrete;
    private String jsonRestauranteSemCozinha;
    private String jsonRestauranteComCozinhaInexistente;

    private Restaurante burgerTopRestaurante;

    @BeforeEach
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
                "/json/correto/restaurante-new-york-barbecue.json");

        jsonRestauranteSemFrete = ResourceUtils.getContentFromResource(
                "/json/incorreto/restaurante-new-york-barbecue-sem-frete.json");

        jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
                "/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json");

        jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
                "/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");

        databaseCleaner.clearTables();
        setUpData();
    }

    @DisplayName("Teste retorno status 200 de restaurantes.")
    @Test
    public void shouldReturnStatus200_WhenGetRestaurants() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("Teste retorno salvar restaurante.")
    @Test
    public void shouldReturnStatus201_WhenSaveRestaurant() {
        given()
                .body(jsonRestauranteCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("Teste retorno salvar restaurante sem taxa frete.")
    @Test
    public void shouldReturnStatus400_WhenSaveRestaurantWithoutShippingFee() {
        given()
                .body(jsonRestauranteSemFrete)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @DisplayName("Teste retorno salvar restaurante sem cozinha.")
    @Test
    public void shouldReturnStatus400_WhenSaveRestaurantWithoutKitchen() {
        given()
                .body(jsonRestauranteSemCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @DisplayName("Teste retorno salvar restaurante com cozinha inexistente.")
    @Test
    public void shouldReturnStatus400_WhenSaveRestaurantWithNonExistingKitchen() {
        given()
                .body(jsonRestauranteComCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
    }

    @DisplayName("Teste retorar resposta e estatus corretos.")
    @Test
    public void shouldReturnCorrectResponseAndStatus_WhenGetExistingRestaurantById() {
        given()
                .pathParam("restauranteId", burgerTopRestaurante.getId())
                .accept(ContentType.JSON)
        .when()
                .get("/{restauranteId}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(burgerTopRestaurante.getNome()));
    }

    @DisplayName("Teste retorno 404 quando consultar restaurante inexistente.")
    @Test
    public void shouldReturnStatus404_WhenGetNonExistingRestaurantById() {
        given()
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void setUpData() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        burgerTopRestaurante = new Restaurante();
        burgerTopRestaurante.setNome("Burger Veg Top");
        burgerTopRestaurante.setTaxaFrete(new BigDecimal(10));
        burgerTopRestaurante.setCozinha(cozinhaAmericana);
        restauranteRepository.save(burgerTopRestaurante);

        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(comidaMineiraRestaurante);
    }
}
