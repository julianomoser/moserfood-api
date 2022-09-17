package com.moser.moserfood;

import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.repository.CozinhaRepository;
import com.moser.moserfood.domain.service.CozinhaService;
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

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CozinhaIntegrationIT {

	@LocalServerPort
	private int port = 8081;

	private static final int COZINHA_ID_INEXISTENTE = 100;


	private Cozinha cozinhaAmericana;
	private int countRegisteredKitchen;
	private String jsonCorretoCozinhaChinesa;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@BeforeEach
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");

		databaseCleaner.clearTables();
		setUpData();
	}

	@Autowired
	private CozinhaService cozinhaService;

	@DisplayName("Teste salvar cozinha.")
	@Test
	public void shouldSaveEntity() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Chinesa");

		cozinha = cozinhaService.salvar(cozinha);

		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}

//	@DisplayName("Teste salvar cozinha sem nome.")
//	@Test
//	public void shouldThrowException_WhenKitchenNameIsEmpty() {
//		Cozinha cozinha = new Cozinha();
//		cozinha.setNome(null);
//		assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(cozinha));
//	}
//
//	@DisplayName("Teste deletar cozinha em uso.")
//	@Test
//	public void shouldThrowException_WhenTryDeleteKitchenInUse() {
//		Cozinha cozinha = new Cozinha();
//		cozinha.setNome(null);
//		assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.excluir(1L),
//                "Teste deve lançar EntidadeEmUsoException " +
//				"quando tentar deletar uma cozinha em uso");
//	}
//
//	@DisplayName("Teste deletar cozinha inesistente.")
//	@Test
//	public void shouldThrowException_WhenTryDeleteNonExistentKitchen() {
//		Cozinha cozinha = new Cozinha();
//		cozinha.setNome(null);
//		assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.excluir(100L),
//                "Teste deve lançar CozinhaNaoEncontradaException " +
//				"quando tentar deletar uma cozinha inexistente");
//	}

	@DisplayName("Teste retorno status 200 de cozinhas.")
	@Test
	public void shouldReturnStatus200_WhenCallGetKitchens() {

		given()
				.accept(ContentType.JSON)
		.when()
				.get()
		.then()
				.statusCode(HttpStatus.OK.value());
	}

    @DisplayName("Teste retorno com quantidade corretas de cozinhas.")
    @Test
    public void shouldReturnCorrectSizerKitchen_WhenCallGetKitchens() {

        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(countRegisteredKitchen));
    }

	@DisplayName("Teste retorno status 201 de cozinhas.")
	@Test
	public void shouldReturnStatus201_WhenSaveNewKitchen() {

		given()
				.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
		.when()
				.post()
		.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@DisplayName("Teste retorno 404 para cozinha inexistente.")
	@Test
	public void shouldReturnStatus404_WhenGetNonExistingKitchenById() {
		given()
				.pathParams("cozinhaId", COZINHA_ID_INEXISTENTE)
				.accept(ContentType.JSON)
		.when()
				.get("/{cozinhaId}")
		.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@DisplayName("Teste retorar resposta e estatus corretos.")
	@Test
	public void shouldReturnCorrectResponseAndStatus_WhenGetExistingKitchenById() {
		given()
				.pathParams("cozinhaId", cozinhaAmericana.getId())
				.accept(ContentType.JSON)
		.when()
				.get("/{cozinhaId}")
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", equalTo(cozinhaAmericana.getNome()));
	}

	private void setUpData() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		countRegisteredKitchen = (int) cozinhaRepository.count();
	}
}
