package ApiProductoV1.base;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ApiProductoV1.base.config.ApiConfig;
import ApiProductoV1.dto.ProductRequest;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("Requisito 4. Error al crear nuevo producto usando la api /api/v1/product/")
public class CrearProductoFallidoTest extends ApiConfig {
	@BeforeEach
	public void abrirEscenario() {
		OnStage.setTheStage(Cast.ofStandardActors());
	}
	
	@Test
	@DisplayName("Crear un nuevo producto de manera fallida nombre")
	public void crearNuevoProductoFallidoNombre() {
		ProductRequest nuevoProductoFallidoNombre = ProductRequest.builder()
				.name("")
				.description("Telefono de alta gama")
				.price(3000.0f)
				.build();
		
		OnStage.theActorCalled("Tester").whoCan(CallAnApi.at("http://localhost:8080"));
		
		OnStage.theActorInTheSpotlight().attemptsTo(
				Post.to("/api/v1/product/")
					.with(
							request -> request
								.body(nuevoProductoFallidoNombre).log().all()
					)
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El codigo de la respuesta es 400", response -> response.statusCode(400))
				);
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("status", equalTo(false)))
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("message", containsString("El nombre del producto no fue proporcionado")))
				);

	}
	
	@Test
	@DisplayName("Crear un nuevo producto de manera fallida descripcion")
	public void crearNuevoProductoFallidoDescripcion() {
		ProductRequest nuevoProductoFallidoDescripcion = ProductRequest.builder()
				.name("Iphone 2023")
				.description("")
				.price(3000.0f)
				.build();
		
		OnStage.theActorCalled("Tester").whoCan(CallAnApi.at("http://localhost:8080"));
		
		OnStage.theActorInTheSpotlight().attemptsTo(
				Post.to("/api/v1/product/")
					.with(
							request -> request
								.body(nuevoProductoFallidoDescripcion).log().all()
					)
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El codigo de la respuesta es 400", response -> response.statusCode(400))
				);
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("status", equalTo(false)))
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("message", containsString("La descripción del producto no fue proporcionada")))
				);

	}
	
	@Test
	@DisplayName("Crear un nuevo producto de manera fallida precio")
	public void crearNuevoProductoFallidoPrecio() {
		ProductRequest nuevoProductoFallidoPrecio = ProductRequest.builder()
				.name("Iphone 2023")
				.description("Telefono de alta gama")
				.price(0.0F)
				.build();
		
		OnStage.theActorCalled("Tester").whoCan(CallAnApi.at("http://localhost:8080"));
		
		OnStage.theActorInTheSpotlight().attemptsTo(
				Post.to("/api/v1/product/")
					.with(
							request -> request
								.body(nuevoProductoFallidoPrecio).log().all()
					)
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El codigo de la respuesta es 400", response -> response.statusCode(400))
				);
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("status", equalTo(false)))
				);
		
		OnStage.theActorInTheSpotlight().should(
				ResponseConsequence.seeThatResponse("El valor del atributo status debe ser verdadero"
						,response -> response.body("message", containsString("El precio del producto no fue proporcionado")))
				);

	}
}
